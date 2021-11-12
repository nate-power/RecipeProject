package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.services.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ForgotPasswordController {

    private final JavaMailSender mailSender;
    private final UserService userService;

    public ForgotPasswordController(JavaMailSender mailSender, UserService userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }

    @GetMapping("/forgotpassword")
    public String forgotPassword() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/recipes";
        }
        return "/forgotpassword";
    }

    @PostMapping("/forgotpassword")
    public String forgotPassword(Model model, @RequestParam("email") String email, HttpServletRequest request) {
        String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (email.length() < 5 || email.length() > 70 || !matcher.matches()) {
            model.addAttribute("message", "Please enter a valid email between 5 and 70 characters.");
            return "/forgotpassword";
        }
        if (!userService.userExistsEmail(email)) {
            model.addAttribute("message", "This email is not in our system. Please try again.");
            return "/forgotpassword";
        }

        String token = RandomString.make(60);
        try {
            userService.setResetPasswordToken(token, email);
            String resetLink = request.getHeader("host") + "/resetpassword?token=" + token;
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom("admin@nullrecipes.com", "Null Recipes Support");
            helper.setTo(email);
            String subject = "Null Recipes - Reset Password";
            String link = "<a href=\"http://" + resetLink + "\" name=\"http://" + resetLink + "\" id=\"http://" + resetLink + "\">Change my password</a>";
            String content = "<p>Hello User,</p>" +
                    "<p>You have requested to reset your password.</p>" +
                    "<p>Click the link below to change your password:</p>" +
                    "<p>" + link + "</p>" +
                    "<br><p>Ignore this email if you have not made the request.</p>";
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            model.addAttribute("confirmed", "We have sent a reset password link to your email. " +
                    "Please check spam if you do not see it in your Inbox.");
        } catch (MessagingException | UnsupportedEncodingException e) {
            model.addAttribute("message", "There has been an error trying to send your password" +
                    " recovery email. Please try again later.");
        }
        return "/forgotpassword";
    }

    @GetMapping("/resetpassword")
    public String resetPassword(@Param("token") String token, Model model) {
        if (token == null) {
            return "/login";
        }
        User user = userService.findUserByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (user == null) {
            model.addAttribute("invalid", "Invalid Token");
        }
        else {
            model.addAttribute("verified", "Token Verified!");
            model.addAttribute("user", user);
        }
        return "/resetpassword";
    }

    @PostMapping("/resetpassword")
    public String resetPassword(Model model, RedirectAttributes redirectAttributes, @RequestParam("password") String password,
                                @RequestParam("Cpassword") String cPassword, @RequestParam("token") String token) {
        User user = userService.findUserByResetPasswordToken(token);
        if (!password.equals(cPassword)) {
            redirectAttributes.addFlashAttribute("message", "Passwords do not match");
            return "redirect:/resetpassword" + "?token=" + token;
        }

        if (password.length() > 16 || password.length() < 4) {
            redirectAttributes.addFlashAttribute("message", "Password must be more than 4 characters and less than 16 characters.");
            return "redirect:/resetpassword" + "?token=" + token;
        }

        if (password.contains(" ")) {
            redirectAttributes.addFlashAttribute("message", "Password cannot contain a space.");
            return "redirect:/resetpassword" + "?token=" + token;
        }

        if (user == null) {
            redirectAttributes.addFlashAttribute("invalid", "Invalid Token");
            return "redirect:/resetpassword" + "?token=" + token;
        }
        else {
            user.setPassword(password);
            userService.save(user);
            model.addAttribute("confirmed", "You have changed your password!");
            return "/login";
        }
    }
}

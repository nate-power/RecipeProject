package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.services.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistration(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/recipes";
        }
        else {
            model.addAttribute("user", new User());
            return "/registration";
        }
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid User user, BindingResult result, @RequestParam("Cpassword") String cPassword) {

        // check if username already exists
        if (userService.userExistsUsername(user.getUsername())) {
            result.addError(new FieldError("user", "username", "Username already exists."));
        }
        // check if username has any spaces
        if (user.getUsername().contains(" ")) {
            result.addError(new FieldError("user", "username", "Username cannot contain any spaces."));
        }
        // check if email already exists
        if (userService.userExistsEmail(user.getEmail())) {
            result.addError(new FieldError("user", "email", "Email already in use by another account."));
        }
        // check password for any spaces
        if (user.getPassword().contains(" ")) {
            result.addError(new FieldError("user", "password", "Password cannot contain any spaces.") );
        }
        // check if password given is over 16 characters or under 4 characters
        if (user.getPassword().length() > 16 || user.getPassword().length() < 4) {
            result.addError(new FieldError("user", "password", "Password must be between 4 and 16 characters."));
        }
        // check if password is same as confirm password
        if (!user.getPassword().equals(cPassword)) {
            result.addError(new FieldError("user", "password", "Passwords do not match."));
        }
        if (result.hasErrors()) {
            return "/registration";
        }
        userService.save(user);
        return "redirect:/registration?success";
    }
}

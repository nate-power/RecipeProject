package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.services.UserServiceImpl;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserServiceImpl userService;

    public UserRegistrationController(UserServiceImpl userService) {
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
            return "registration";
        }
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") User user) {
        userService.save(user);
        Iterable<User> users = userService.findAll();
        for (User person : users) {
            System.out.println(person.getUsername());
            System.out.println(person.getPassword());
        }
        return "redirect:/registration?success";
    }
}

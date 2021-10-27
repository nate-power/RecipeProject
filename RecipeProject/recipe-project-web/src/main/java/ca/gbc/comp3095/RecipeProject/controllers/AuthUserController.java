package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.security.UserPrincipal;
import ca.gbc.comp3095.RecipeProject.services.RecipeDateServiceImpl;
import ca.gbc.comp3095.RecipeProject.services.RecipeServiceImpl;
import ca.gbc.comp3095.RecipeProject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class AuthUserController {

    private final UserServiceImpl userService;
    private final RecipeDateServiceImpl recipeDateService;
    private final RecipeServiceImpl recipeService;

    public AuthUserController(UserServiceImpl userService, RecipeDateServiceImpl recipeDateService, RecipeServiceImpl recipeService) {
        this.userService = userService;
        this.recipeDateService = recipeDateService;
        this.recipeService = recipeService;
    }

    @GetMapping("/profile/{username}")
    public String getProfile(Model model, @PathVariable String username) {
        if (userService.findUser().getUsername().equals(username)) {
            model.addAttribute("user", userService.findUser());
            return "/user/profile";
        }
        return "errors/error-404";
    }

    @GetMapping("/schedule/{username}")
    public String getSchedule(Model model, @PathVariable String username) {
        if (userService.findUser().getUsername().equals(username)) {
            User user = userService.findUser();
            model.addAttribute("user", user);
            model.addAttribute("meals", recipeDateService.findAllByUser(user));
            model.addAttribute("recipes", recipeService);
            return "/user/schedule";
        }
        return "errors/error-404";
    }

    @GetMapping("/schedule/add/{recipeId}/{username}")
    public String addToSchedule(Model model, @PathVariable String username, @PathVariable Long recipeId, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (userService.findUser().getUsername().equals(username)) {
            User user = userService.findUser();
            recipeDateService.save(user.addToSchedule(date, recipeId));
            return "redirect:/schedule/{username}";
        }
        return "errors/error-404";
    }
}

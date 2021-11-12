//*********************************************************************************//
//* Project: Null Recipes
//        * Assignment: Assignment #1
//        * Author(s): Nathan Power
//        * Student Number: 101247770
//        * Date: October 26th, 2021
//        * Description: This controller handles routing to profile of the signed-in user, and handles the calls to the logic
//        * for adding and deleting favourite recipes for the signed-in user.
//*********************************************************************************//

package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.services.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthUserController {

    private final UserService userService;
    private final RecipeService recipeService;

    public AuthUserController(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping("/profile/{username}")
    public String getProfile(Model model, @PathVariable String username) {
        if (userService.findUser().getUsername().equals(username)) {
            model.addAttribute("user", userService.findUser());
            model.addAttribute("recipes", recipeService.findAllByUser(userService.findUser()));
            model.addAttribute("favourites", userService.findUser().getFavouriteRecipes());
            return "/user/profile";
        }
        return "errors/error-404";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        model.addAttribute("user", userService.findUser());
        return "/user/edit";
    }

    @GetMapping("profile/change-password")
    public String changePassword(Model model) {
        model.addAttribute("user", userService.findUser());
        return "/user/change-password";
    }

    @PostMapping("profile/change-password")
    public String changePassword(@RequestParam("old-password") String oldPassword, @RequestParam("new-password") String newPassword,
                                 @RequestParam("confirm-password") String confirmPassword, BindingResult result) {
        User user = userService.findUser();
        return "k";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@ModelAttribute("user") @Valid User user, BindingResult result) {
        assert user != null;
        // check if username already exists
        if (userService.userExistsUsername(user.getUsername()) && !user.getUsername().equals(userService.findUser().getUsername())) {
            result.addError(new FieldError("user", "username", "Username already exists for another account."));
        }
        // check if username has any spaces
        if (user.getUsername().contains(" ")) {
            result.addError(new FieldError("user", "username", "Username cannot contain any spaces."));
        }
        // check if email already exists
        if (userService.userExistsEmail(user.getEmail()) && !user.getEmail().equals(userService.findUser().getEmail())) {
            result.addError(new FieldError("user", "email", "Email already in use by another account."));
        }

        if (result.hasErrors()) {
            return "user/edit";
        }
        userService.save(user);
        userService.setUser(user);
        return "redirect:/profile/" + user.getUsername();
    }

    @PostMapping("/favourite/add")
    public String addFavourite(@RequestParam("id") Long id) {
        User user = userService.findUser();
        Recipe recipe = recipeService.findById(id);
        user.getFavouriteRecipes().add(recipe);
        recipe.getUserFavourites().add(user);
        userService.save(user);
        recipeService.save(recipe);
        return "redirect:/recipe/" + recipe.getId();
    }

    @PostMapping("/favourite/delete")
    public String deleteFavourite(@RequestParam("id") Long id) {
        User user = userService.findUser();
        Recipe recipe = recipeService.findById(id);
        user.getFavouriteRecipes().remove(recipe);
        recipe.getUserFavourites().remove(user);
        userService.save(user);
        recipeService.save(recipe);
        return "redirect:/recipe/" + recipe.getId();
    }
}
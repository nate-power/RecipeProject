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

import ca.gbc.comp3095.RecipeProject.models.Recipe;
import ca.gbc.comp3095.RecipeProject.models.User;
import ca.gbc.comp3095.RecipeProject.services.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Controller
public class AuthUserController {

    private final UserService userService;
    private final RecipeService recipeService;
    private final PasswordEncoder passwordEncoder;
    private final EventService eventService;

    public AuthUserController(UserService userService, RecipeService recipeService, PasswordEncoder passwordEncoder, EventService eventService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.passwordEncoder = passwordEncoder;
        this.eventService = eventService;
    }

    @GetMapping("/profile/{username}")
    public String getProfile(Model model, @PathVariable String username) {
        if (userService.findUser().getUsername().equals(username)) {
            String list = userService.findUser().getShoppingList();
            List<String> shoppingList = list.equals("") ? new ArrayList<>() : Arrays.asList(list.split("\n"));
            model.addAttribute("user", userService.findUser());
            model.addAttribute("recipes", recipeService.findAllByUser(userService.findUser()));
            model.addAttribute("favourites", userService.findUser().getFavouriteRecipes());
            model.addAttribute("shoppinglist", shoppingList);
            model.addAttribute("events", eventService.findAllByUser(userService.findUser()));
            model.addAttribute("today", LocalDate.now());
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
                                 @RequestParam("confirm-password") String confirmPassword, Model model) {
        User user = userService.findUser();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            model.addAttribute("message", "Old Password is incorrect.");
            return "/user/change-password";
        }
        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            model.addAttribute("message", "Please create a new password different than the old one.");
            return "/user/change-password";
        }
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("message", "New Password amd Confirmed Password do not match.");
            return "/user/change-password";
        }
        if (newPassword.length() > 16 || newPassword.length() < 4) {
            model.addAttribute("message", "Password must be between 4 and 16 characters.");
            return "/user/change-password";
        }
        if (newPassword.contains(" ")) {
            model.addAttribute("message", "Password cannot contain spaces.");
            return "/user/change-password";
        }
        user.setPassword(newPassword);
        userService.save(user);
        return "redirect:/logout";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@ModelAttribute("user") @Valid User user, BindingResult result,
                              @RequestParam("image") MultipartFile multipartFile) throws IOException {
        assert user != null;
        if (multipartFile.getContentType().equals("image/jpeg") || multipartFile.getContentType().equals("image/jpg")
                || multipartFile.getContentType().equals("image/png")) {
            if (multipartFile.getSize() > 2097152) {
                result.addError(new FieldError("user", "photoData", "Please upload an image that is less than 2MB!"));
            }
            else {
                user.setPhotoData(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
            }
        }
        else {
            if (!multipartFile.isEmpty()) {
                result.addError(new FieldError("user", "photoData", "Uploaded image must be a jpeg or png."));
            }
        }
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

    @PostMapping("/profile/shoppinglist/add")
    public String addToShoppingList(@RequestParam("ingredients") Optional<String[]> ingredients, @RequestParam("recipeId") long recipeId) {
        User user = userService.findUser();

        if (ingredients.isPresent()) {
            for (String ingredient:
                    ingredients.get()) {
                userService.addToShoppingList(user, ingredient);
            }
            userService.save(user);
        }
        return "redirect:/recipe/" + recipeId;
    }

    @PostMapping("/profile/shoppinglist/edit")
    public String editShoppingList(@RequestParam("shoppingEdit") String shoppingList) {
        User user = userService.findUser();
        user.setShoppingList(shoppingList.trim() + "\n");
        userService.save(user);
        return "redirect:/profile/" + user.getUsername();
    }

    @GetMapping("/profile/shoppinglist/download")
    @ResponseBody
    public FileSystemResource download() throws IOException {
        User user = userService.findUser();
        File list_file = new File("Shopping List.txt");
        FileOutputStream outputStream = new FileOutputStream(list_file);
        outputStream.write(user.getShoppingList().getBytes());
        outputStream.close();

        return new FileSystemResource(list_file);
    }
}
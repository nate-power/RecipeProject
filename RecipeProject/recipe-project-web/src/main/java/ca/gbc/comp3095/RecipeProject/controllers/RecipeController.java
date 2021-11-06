//*********************************************************************************
//* Project: < Null Recipes >
//        * Assignment: assignment #1
//        * Author(s): Justin Bartlett
//        * Student Number: 101246661
//        * Date: October 26 2021
//        * Description: This controller handles routing to pages related to adding, viewing, and searching for recipes.
//                      It also interacts with the recipeService to handle CRUD operations related to recipes.
//*********************************************************************************

package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;
import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.RecipeDate;
import ca.gbc.comp3095.RecipeProject.services.RecipeService;
import ca.gbc.comp3095.RecipeProject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Map;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final UserService userService;

    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping({"/", "/recipes", "/recipes/", "/recipes/{category}"} )
    public String listRecipes(Model model, @PathVariable(required = false) String category) {
        String categoryPath;
        if (category != null) {
            boolean isCategory = false;
            for (RecipeCategories categoryName : RecipeCategories.values()) {
                if (categoryName.getDisplay().equalsIgnoreCase(category)) {
                    isCategory = true;
                    model.addAttribute("recipes", recipeService.findAllByCategory(categoryName));
                    break;
                }
            }
            categoryPath = Recipe.uppercaseName(category);
            if (!isCategory) {
                return "errors/error-404";
            }
        }
        else {
            model.addAttribute("recipes", recipeService.findAll());
            categoryPath = "All Recipes";
        }
        model.addAttribute("category", categoryPath);
        return "recipes/index";
    }

    @GetMapping("recipe/{id}")
    public String viewRecipe(Model model, @PathVariable Long id)
    {
        Recipe recipe = recipeService.findById(id);
        String[] ingredients = recipe.getIngredients().split("\\n");
        String[] steps = recipe.getSteps().split("\\n");

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("steps", steps);
        model.addAttribute("user", userService.findUser());
        model.addAttribute("recipeDate", new RecipeDate());
        model.addAttribute("favourites", userService.findUser().getFavouriteRecipes());

        return "recipes/view";
    }

    @GetMapping("/recipes/add" )
    public String createRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", RecipeCategories.values());
        return "recipes/add";
    }

    @GetMapping("/search")
    public String searchRecipe() {
        return "recipes/search";
    }

    @GetMapping("/recipes/search")
    public String retrieveRecipeSearch(@RequestParam("query") String query, Model model) {
        model.addAttribute("recipes", recipeService.findByQuery(query));
        model.addAttribute("query", query);
        return "recipes/search";
    }

    @PostMapping("/recipes/add" )
    public String saveRecipe(@ModelAttribute("recipe") @Valid Recipe recipe, BindingResult result,
                             @RequestParam Map<String, String> stringMap, @RequestParam("category") RecipeCategories category,
                             Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (multipartFile.getContentType().equals("image/jpeg") || multipartFile.getContentType().equals("image/jpg")
                || multipartFile.getContentType().equals("image/png")) {
            if (multipartFile.getSize() > 2097152) {
                result.addError(new FieldError("recipe", "photoData", "Please upload an image that is less than 2MB!"));
            }
        }
        else {
            if (!multipartFile.isEmpty()) {
                result.addError(new FieldError("recipe", "photoData", "Uploaded image must be a jpeg or png."));
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("categories", RecipeCategories.values());
            return "recipes/add";
        }

        if (multipartFile.isEmpty()) {
            recipe.setPhotoData("");
        } else {
            recipe.setPhotoData(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        }
        recipe.setName(Recipe.uppercaseName(recipe.getName()));
        recipe.setUser(userService.findUser());
        recipe.setDateCreated(LocalDate.now());

        recipeService.save(recipe);
        return "redirect:/recipes/" + category.getDisplay().toLowerCase();
    }
}
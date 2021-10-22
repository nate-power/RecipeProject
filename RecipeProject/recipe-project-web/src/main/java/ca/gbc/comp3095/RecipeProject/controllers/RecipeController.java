package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecipeController {

    public final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/recipes", "/recipes/"} )
    public String listRecipes() {
        return "recipes/index";
    }

    @GetMapping({"/recipe/add", "/recipe/add/"} )
    public String createRecipe() {
        return "recipes/add";
    }

    @PostMapping({"/recipe/add", "/recipe/add/"} )
    public String saveRecipe() {

        return "recipes/index";
    }
}

package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

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

    @RequestMapping({"/recipe/add", "/recipe/add/"} )
    public String createRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipes/add";
    }

    @RequestMapping("/createRecipe" )
    public String saveRecipe(Recipe recipe) {
        System.out.println(recipe.getName());

        recipe.setDateUpdated(LocalDate.now());

        recipeService.addRecipe(recipe);

        return "redirect:/recipes";
    }
}

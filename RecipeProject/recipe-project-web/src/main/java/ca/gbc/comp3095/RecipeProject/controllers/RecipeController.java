package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.security.UserPrincipal;
import ca.gbc.comp3095.RecipeProject.services.RecipeServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;

@Controller
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeServiceImpl) {
        this.recipeService = recipeServiceImpl;
    }

    @GetMapping({"/", "/recipes", "/recipes/", "/recipes/index"} )
    public String listRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipes/index";
    }

    @GetMapping("recipe/{name}/{id}")
    public String viewRecipe(Model model, @PathVariable String name, @PathVariable Long id)
    {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipes/view";
    }

    @GetMapping({"/recipe/add", "/recipe/add/"} )
    public String createRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipes/add";
    }

    @PostMapping("/createRecipe" )
    public String saveRecipe(Recipe recipe, @RequestParam Map<String, String> stringMap) {
        String ingredients = stringMap.get("ingredients");
        String[] ingredientsArr = ingredients.split("\\n");
        ingredients = String.join("^", ingredientsArr);
        recipe.setIngredients(ingredients);

        String steps = stringMap.get("steps");
        String[] stepsArr = steps.split("\\n");
        steps = String.join("^", stepsArr);
        recipe.setSteps(steps);

        recipe.setName(Recipe.uppercaseName(recipe.getName()));

        recipeService.save(recipe);
        return "redirect:/recipes";
    }
}

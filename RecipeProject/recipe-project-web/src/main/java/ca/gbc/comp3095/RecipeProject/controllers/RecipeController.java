package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;
import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.RecipeDate;
import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.services.RecipeDateServiceImpl;
import ca.gbc.comp3095.RecipeProject.services.RecipeServiceImpl;
import ca.gbc.comp3095.RecipeProject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

@Controller
public class RecipeController {

    private final RecipeServiceImpl recipeService;
    private final UserServiceImpl userService;
    private RecipeDateServiceImpl recipeDateService;

    public RecipeController(RecipeServiceImpl recipeService, UserServiceImpl userService, RecipeDateServiceImpl recipeDateService) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.recipeDateService = recipeDateService;
    }

    @GetMapping({"/", "/recipes", "/recipes/", "/recipes/{category}"} )
    public String listRecipes(Model model, @PathVariable(required = false) String category) {
        model.addAttribute("users", userService.findAll());
        String categoryPath;
        if (category != null) {
            boolean isCategory = false;
            for (RecipeCategories categoryName : RecipeCategories.values()) {
                if (categoryName.getDisplay().toLowerCase().equals(category.toLowerCase())) {
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

    @GetMapping("recipe/{name}/{id}")
    public String viewRecipe(Model model, @PathVariable Long id)
    {
        Recipe recipe = recipeService.findById(id);
        String[] ingredients = recipe.getIngredients().split("\\^");
        String[] steps = recipe.getSteps().split("\\^");

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("steps", steps);
        model.addAttribute("user", userService.findUser());
        model.addAttribute("recipeDate", new RecipeDate());

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
        return "recipes/index";
    }

    @PostMapping("/recipes/add" )
    public String saveRecipe(@ModelAttribute("recipe") @Valid Recipe recipe, BindingResult result,
                             @RequestParam Map<String, String> stringMap, @RequestParam("category") RecipeCategories category, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", RecipeCategories.values());
            return "recipes/add";
        }
        String ingredients = stringMap.get("ingredients");
        String[] ingredientsArr = ingredients.split("\\n");
        ingredients = String.join("^", ingredientsArr);
        recipe.setIngredients(ingredients);

        String steps = stringMap.get("steps");
        String[] stepsArr = steps.split("\\n");
        steps = String.join("^", stepsArr);
        recipe.setSteps(steps);

        recipe.setName(Recipe.uppercaseName(recipe.getName()));
        recipe.setUserId(userService.findUser().getId());
        recipe.setDateCreated(LocalDate.now());

        recipeService.save(recipe);
        return "redirect:/recipes/" + category.getDisplay().toLowerCase();
    }
}

package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.services.RecipeServiceImpl;
import ca.gbc.comp3095.RecipeProject.services.UserServiceImpl;
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
    private final UserServiceImpl userService;

    public RecipeController(RecipeServiceImpl recipeServiceImpl, UserServiceImpl userService) {
        this.recipeService = recipeServiceImpl;
        this.userService = userService;
    }

    @GetMapping({"/", "/recipes", "/recipes/", "/recipes/index"} )
    public String listRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        model.addAttribute("users", userService.findAll());
        return "recipes/index";
    }

    @GetMapping("recipe/{name}/{id}")
    public String viewRecipe(Model model, @PathVariable String name, @PathVariable Long id)
    {
        Recipe recipe = recipeService.findById(id);
        String[] ingredients = recipe.getIngredients().split("\\^");
        String[] steps = recipe.getSteps().split("\\^");

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("steps", steps);

        return "recipes/view";
    }

    @GetMapping({"/recipe/add", "/recipe/add/"} )
    public String createRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
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
        recipe.setUserId(userService.findUser().getId());
        recipe.setDateCreated(LocalDate.now());

        recipeService.save(recipe);
        return "redirect:/recipes";
    }
}

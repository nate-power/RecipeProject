package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> result = (List<Recipe>) recipeRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Recipe>();
        }
    }

    public Recipe addRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe;
    }
}

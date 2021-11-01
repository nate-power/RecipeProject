package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;
import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.orElse(null);
    }

    @Override
    public Recipe save(Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe;
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAllByOrderByDateCreatedDesc();
    }

    @Override
    public List<Recipe> findByQuery(String query) {
        return recipeRepository.findAllByQuery(query);
    }

    @Override
    public List<Recipe> findAllByCategory(RecipeCategories category) {
        return recipeRepository.findAllByCategoryOrderByDateCreatedDesc(category);
    }

    @Override
    public List<Recipe> findAllByUser(User user) {
        return recipeRepository.findAllByUserOrderByDateCreatedDesc(user);
    }


}
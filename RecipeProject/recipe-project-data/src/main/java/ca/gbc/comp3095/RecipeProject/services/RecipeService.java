package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;
import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.User;

import java.util.List;
import java.util.Set;

public interface RecipeService {
    List<Recipe> findByQuery(String query);
    List<Recipe> findAllByCategory(RecipeCategories category);
    List<Recipe> findAllByUser(User user);
}

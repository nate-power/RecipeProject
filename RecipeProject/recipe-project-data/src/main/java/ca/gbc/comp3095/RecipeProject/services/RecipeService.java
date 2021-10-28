package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;
import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.User;

import java.util.Set;

public interface RecipeService {
    Iterable<Recipe> findByQuery(String query);
    Iterable<Recipe> findAllByCategory(RecipeCategories category);
    Iterable<Recipe> findAllByUser(User user);
}

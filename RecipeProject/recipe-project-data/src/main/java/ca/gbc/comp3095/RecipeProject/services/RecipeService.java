package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.model.Recipe;

public interface RecipeService {
    Iterable<Recipe> findByQuery(String query);
}

package ca.gbc.comp3095.RecipeProject.repository;

import ca.gbc.comp3095.RecipeProject.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}

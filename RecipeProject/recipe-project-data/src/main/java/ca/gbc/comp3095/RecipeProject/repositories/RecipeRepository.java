package ca.gbc.comp3095.RecipeProject.repositories;

import ca.gbc.comp3095.RecipeProject.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Iterable<Recipe> findAllByOrderByDateCreatedDesc();
    @Query("select r from Recipe r where lower(r.name) like lower(concat('%', concat(:query, '%'))) or " +
            "lower(r.description) like lower(concat('%', concat(:query, '%'))) or " +
            "lower(r.category) like lower(concat('%', concat(:query, '%')))")
    List<Recipe> findAllByQuery(@Param("query") String query);
}

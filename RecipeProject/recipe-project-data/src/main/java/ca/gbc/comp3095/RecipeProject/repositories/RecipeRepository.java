//*********************************************************************************
//* Project: < Null Recipes >
//        * Assignment: assignment #1
//        * Author(s): Justin Bartlett
//        * Student Number: 101246661
//        * Date: October 24 2021
//        * Description: This is the repository for the Recipe entity, which uses JpaRepository functionality to define
//                      methods that retrieve methods by different criteria, such as category, user, or query.
//*********************************************************************************

package ca.gbc.comp3095.RecipeProject.repositories;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;
import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByOrderByDateCreatedDesc();
    @Query("select r from Recipe r where lower(r.name) like lower(concat('%', concat(:query, '%'))) or " +
            "lower(r.description) like lower(concat('%', concat(:query, '%'))) or " +
            "lower(r.category) like lower(concat('%', concat(:query, '%')))")
    List<Recipe> findAllByQuery(@Param("query") String query);
    List<Recipe> findAllByCategoryOrderByDateCreatedDesc(RecipeCategories category);
    List<Recipe> findAllByUserOrderByDateCreatedDesc(User user);
}
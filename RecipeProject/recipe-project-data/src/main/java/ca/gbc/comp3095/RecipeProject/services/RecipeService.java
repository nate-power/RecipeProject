//*********************************************************************************
//* Project: < Null Recipes >
//        * Assignment: assignment #1
//        * Author(s): Justin Bartlett
//        * Student Number: 101246661
//        * Date: October 26 2021
//        * Description: This is the Recipe Service which extends the basic CRUD Service by adding methods for
//                      retrieving Recipes by query, category, or user.
//*********************************************************************************

package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;
import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.User;

import java.util.List;

public interface RecipeService extends CrudService<Recipe, Long>{
    List<Recipe> findByQuery(String query);
    List<Recipe> findAllByCategory(RecipeCategories category);
    List<Recipe> findAllByUser(User user);
}
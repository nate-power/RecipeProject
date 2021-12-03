//*********************************************************************************//
//* Project: Null Recipes
//        * Assignment: Assignment #1
//        * Author(s): Nathan Power
//        * Student Number: 101247770
//        * Date: October 28th, 2021
//        * Description: This service creates a contract with the implementation to include all methods for finding
//        * all recipes for a user that are planned for the current date, for this week, in the past, in the future and
//        to delete them.
//*********************************************************************************//

package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.models.RecipeDate;
import ca.gbc.comp3095.RecipeProject.models.User;

import java.time.LocalDate;
import java.util.List;

public interface RecipeDateService extends CrudService<RecipeDate, Long>{
    List<RecipeDate> findAllByUser(User user, LocalDate date1, LocalDate date2);
    List<RecipeDate> findAllByUserToday(User user, LocalDate date);
    List<RecipeDate> findAllByUserLess(User user, LocalDate date);
    List<RecipeDate> findAllByUserGreater(User user, LocalDate date);
    void deleteById(Long id);
}
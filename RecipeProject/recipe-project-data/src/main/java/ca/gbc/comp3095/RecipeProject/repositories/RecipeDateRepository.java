//*********************************************************************************
//* Project: < Null Recipes >
//        * Assignment: assignment #1
//        * Author(s): Justin Bartlett
//        * Student Number: 101246661
//        * Date: October 27 2021
//        * Description: This is the repository for the RecipeDate entity, which uses JpaRepository functionality to
//                      define methods which retrieve RecipeDates in certain time ranges to display the meal planner.
//*********************************************************************************

package ca.gbc.comp3095.RecipeProject.repositories;

import ca.gbc.comp3095.RecipeProject.models.RecipeDate;
import ca.gbc.comp3095.RecipeProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecipeDateRepository extends JpaRepository<RecipeDate, Long> {
    List<RecipeDate> findAllByUserAndDateOrderByDate(User user, LocalDate date);
    List<RecipeDate> findAllByUserAndDateGreaterThanEqualAndDateLessThanEqualOrderByDate(User user, LocalDate date1,
                                                                                         LocalDate date2);
    List<RecipeDate> findAllByUserAndDateLessThanOrderByDate(User user, LocalDate date);
    List<RecipeDate> findAllByUserAndDateGreaterThanOrderByDate(User user, LocalDate date);
}
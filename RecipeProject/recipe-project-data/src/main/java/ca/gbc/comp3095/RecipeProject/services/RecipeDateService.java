package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.model.RecipeDate;
import ca.gbc.comp3095.RecipeProject.model.User;

import java.time.LocalDate;
import java.util.List;

public interface RecipeDateService extends CrudService<RecipeDate, Long>{
    List<RecipeDate> findAllByUser(User user, LocalDate date1, LocalDate date2);
    List<RecipeDate> findAllByUserToday(User user, LocalDate date);
    List<RecipeDate> findAllByUserLess(User user, LocalDate date);
    List<RecipeDate> findAllByUserGreater(User user, LocalDate date);
    void deleteById(Long id);
}
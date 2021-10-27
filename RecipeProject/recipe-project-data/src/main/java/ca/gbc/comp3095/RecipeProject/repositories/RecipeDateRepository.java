package ca.gbc.comp3095.RecipeProject.repositories;

import ca.gbc.comp3095.RecipeProject.model.RecipeDate;
import ca.gbc.comp3095.RecipeProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecipeDateRepository extends JpaRepository<RecipeDate, Long> {
    List<RecipeDate> findAllByUserAndDateOrderByDate(User user, LocalDate date);
    List<RecipeDate> findAllByUserAndDateGreaterThanEqualAndDateLessThanEqualOrderByDate(User user, LocalDate date1, LocalDate date2);
    List<RecipeDate> findAllByUserAndDateLessThanOrderByDate(User user, LocalDate date);
    List<RecipeDate> findAllByUserAndDateGreaterThanOrderByDate(User user, LocalDate date);
}
package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.model.RecipeDate;
import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.repositories.RecipeDateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RecipeDateServiceImpl implements CrudService<RecipeDate, Long>, RecipeDateService {

    private final RecipeDateRepository recipeDateRepository;

    public RecipeDateServiceImpl(RecipeDateRepository recipeDateRepository) {
        this.recipeDateRepository = recipeDateRepository;
    }

    @Override
    public RecipeDate save(RecipeDate recipeDate) {
        recipeDateRepository.save(recipeDate);
        return recipeDate;
    }

    @Override
    public Iterable<RecipeDate> findAll() {
        return recipeDateRepository.findAll();
    }

    @Override
    public RecipeDate findById(Long recipeDateId) {
        Optional<RecipeDate> recipeDate = recipeDateRepository.findById(recipeDateId);
        return recipeDate.orElse(null);
    }

    public Iterable<RecipeDate> findAllByUser(User user, LocalDate date1, LocalDate date2) {
        return recipeDateRepository.findAllByUserAndDateGreaterThanEqualAndDateLessThanEqualOrderByDate(user, date1, date2);
    }

    public Iterable<RecipeDate> findAllByUserToday(User user, LocalDate date) {
        return recipeDateRepository.findAllByUserAndDateOrderByDate(user, date);
    }

    public Iterable<RecipeDate> findAllByUserLess(User user, LocalDate date) {
        return recipeDateRepository.findAllByUserAndDateLessThanOrderByDate(user, date);
    }

    public Iterable<RecipeDate> findAllByUserGreater(User user, LocalDate date) {
        return recipeDateRepository.findAllByUserAndDateGreaterThanOrderByDate(user, date);
    }

    public void deleteById(Long id) {
        recipeDateRepository.deleteById(id);
    }
}
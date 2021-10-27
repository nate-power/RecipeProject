package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.model.RecipeDate;
import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.repositories.RecipeDateRepository;
import org.springframework.stereotype.Service;

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

    public Iterable<RecipeDate> findAllByUser(User user) {
        return recipeDateRepository.findAllByUserOrderByDate(user);
    }
}
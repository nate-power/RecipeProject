//***************************//
//* Project: Null Recipes
//        * Assignment: Assignment 1
//        * Author(s): Alberto Dos Reis
//        * Student Number: 101232584
//        * Date: October 28, 2021
//        * Description: This service will be recognized by Spring to provide the functionality for the
//              the methods contracted in the Service interface for RecipeDate object and for the CrudService.
//***************************//

package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.models.RecipeDate;
import ca.gbc.comp3095.RecipeProject.models.User;
import ca.gbc.comp3095.RecipeProject.repositories.RecipeDateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeDateServiceImpl implements RecipeDateService {

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
    public List<RecipeDate> findAll() {
        return recipeDateRepository.findAll();
    }

    @Override
    public RecipeDate findById(Long recipeDateId) {
        Optional<RecipeDate> recipeDate = recipeDateRepository.findById(recipeDateId);
        return recipeDate.orElse(null);
    }

    public List<RecipeDate> findAllByUser(User user, LocalDate date1, LocalDate date2) {
        return recipeDateRepository.findAllByUserAndDateGreaterThanEqualAndDateLessThanEqualOrderByDate(user, date1, date2);
    }

    public List<RecipeDate> findAllByUserToday(User user, LocalDate date) {
        return recipeDateRepository.findAllByUserAndDateOrderByDate(user, date);
    }

    public List<RecipeDate> findAllByUserLess(User user, LocalDate date) {
        return recipeDateRepository.findAllByUserAndDateLessThanOrderByDate(user, date);
    }

    public List<RecipeDate> findAllByUserGreater(User user, LocalDate date) {
        return recipeDateRepository.findAllByUserAndDateGreaterThanOrderByDate(user, date);
    }

    public void deleteById(Long id) {
        recipeDateRepository.deleteById(id);
    }
}
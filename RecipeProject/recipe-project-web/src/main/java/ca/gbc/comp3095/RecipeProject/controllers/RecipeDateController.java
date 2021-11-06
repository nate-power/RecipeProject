//***************************//
//* Project: Null Recipes
//        * Assignment: Assignment 1
//        * Author(s): Alberto Dos Reis
//        * Student Number: 101232584
//        * Date: October 28, 2021
//        * Description: Will direct user to the meal planner page, allow user to add a Recipe Date and delete a Recipe
//              Date. Recipe Dates are displayed by current date, for the week, for the future, and for the past, and
//              passed to Thymeleaf with model attributes.
//***************************//

package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.RecipeDate;
import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.services.*;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.previous;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Controller
public class RecipeDateController {

    private final UserService userService;
    private final RecipeDateService recipeDateService;
    private final RecipeService recipeService;

    public RecipeDateController(UserService userService, RecipeDateService recipeDateService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeDateService = recipeDateService;
        this.recipeService = recipeService;
    }

    @GetMapping("/meal-planner")
    public String getMealPlanner(Model model) {
        User user = userService.findUser();
        LocalDate sunday;
        LocalDate saturday;

        // set correct sunday for week planner
        if (LocalDate.now().getDayOfWeek() == SUNDAY) {
            sunday = LocalDate.now().with(SUNDAY);
        }
        else {
            sunday = LocalDate.now().with(previous(SUNDAY));
        }
        String sundayStr = sunday.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));

        // set correct saturday for week planner
        if (LocalDate.now().getDayOfWeek() == SATURDAY) {
            saturday = LocalDate.now().with(SATURDAY);
        }
        else {
            saturday = LocalDate.now().with(next(SATURDAY));
        }
        String saturdayStr = saturday.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));

        model.addAttribute("current_day", LocalDate.now());
        model.addAttribute("meals_today", recipeDateService.findAllByUserToday(user, LocalDate.now()));
        model.addAttribute("meals_this_week", recipeDateService.findAllByUser(user, sunday, saturday));
        model.addAttribute("meals_future", recipeDateService.findAllByUserGreater(user, saturday));
        model.addAttribute("meals_past", recipeDateService.findAllByUserLess(user, sunday));
        model.addAttribute("sunday", sundayStr);
        model.addAttribute("saturday", saturdayStr);
        return "user/schedule";
    }

    @PostMapping("/meal-planner")
    public String setRecipeDate(@ModelAttribute("recipeDate") RecipeDate recipeDate, @RequestParam("recipeId") Long recipeId,
                                @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        Recipe recipe = recipeService.findById(recipeId);
        User user = userService.findUser();

        recipeDate.setDate(date);
        recipeDate.setRecipe(recipe);
        recipeDate.setUser(user);
        recipeDateService.save(recipeDate);
        return "redirect:/meal-planner";
    }

    @PostMapping("/meal-planner/delete/{id}")
    public String deleteRecipeDate(@PathVariable Long id) {
        recipeDateService.deleteById(id);
        return "redirect:/meal-planner";
    }
}
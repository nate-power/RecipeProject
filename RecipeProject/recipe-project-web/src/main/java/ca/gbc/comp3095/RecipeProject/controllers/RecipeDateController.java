package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.RecipeDate;
import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.services.CrudService;
import ca.gbc.comp3095.RecipeProject.services.RecipeDateServiceImpl;
import ca.gbc.comp3095.RecipeProject.services.RecipeServiceImpl;
import ca.gbc.comp3095.RecipeProject.services.UserServiceImpl;
import org.springframework.security.access.prepost.PostAuthorize;
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

    private final UserServiceImpl userService;
    private final RecipeDateServiceImpl recipeDateService;
    private final RecipeServiceImpl recipeService;

    public RecipeDateController(UserServiceImpl userService, RecipeDateServiceImpl recipeDateService, RecipeServiceImpl recipeService) {
        this.userService = userService;
        this.recipeDateService = recipeDateService;
        this.recipeService = recipeService;
    }

    @GetMapping("/meal-planner")
    public String getMealPlanner(Model model) {
        User user = userService.findUser();

        LocalDate sunday = LocalDate.now().with(previous(SUNDAY));
        String sundayStr = sunday.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));

        LocalDate saturday = LocalDate.now().with(next(SATURDAY));
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
    public String setRecipeDate(@ModelAttribute("recipeDate") RecipeDate recipeDate, @RequestParam("recipeId") Long recipeId) {
        Recipe recipe = recipeService.findById(recipeId);
        User user = userService.findUser();

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

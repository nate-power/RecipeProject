//*********************************************************************************
//* Project: < Null Recipes >
//        * Assignment: assignment #1
//        * Author(s): Justin Bartlett
//        * Student Number: 101246661
//        * Date: October 27 2021
//        * Description: This is the entity model that represents a planned meal for the meal planner functionality.
//          It holds a recipe, the user who has added it to their schedule, and the date that the user plans to make the recipe.
//*********************************************************************************

package ca.gbc.comp3095.RecipeProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class RecipeDate extends BaseEntity{

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="recipe_recipe_date")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "user_recipe_date")
    private User user;

    public RecipeDate() {}

    // used for bootstrap data
    public RecipeDate(LocalDate date, Recipe recipe, User user) {
        this.date = date;
        this.recipe = recipe;
        this.user= user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public User getUser() {
        return user;
    }
}
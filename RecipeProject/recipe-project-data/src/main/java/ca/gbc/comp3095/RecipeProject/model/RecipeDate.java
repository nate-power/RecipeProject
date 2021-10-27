package ca.gbc.comp3095.RecipeProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class RecipeDate extends BaseEntity{

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="recipe_recipe_date")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "user_recipe_date")
    private User user;

    public RecipeDate() {}

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

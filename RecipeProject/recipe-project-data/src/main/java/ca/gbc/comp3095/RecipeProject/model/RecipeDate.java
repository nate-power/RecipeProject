package ca.gbc.comp3095.RecipeProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class RecipeDate extends BaseEntity{
    private Long recipeId;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public RecipeDate(Long recipeId, LocalDate date, User user) {
        this.recipeId = recipeId;
        this.date = date;
        this.user = user;
    }

    public RecipeDate() {}

    public Long getRecipeId() {
        return recipeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }
}

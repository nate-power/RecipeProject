package ca.gbc.comp3095.RecipeProject.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Recipe {

    private String name;
    private User user;
    private String description;
    private final LocalDate dateCreated = LocalDate.now();
    private LocalDate dateUpdated;
    // make enum for Accessors (public private)
    // make enum for Categories
    private int prepTime;
    private int cookTime;
    private int serving;
    private List<Rating> ratingList;
    private Set<String> ingredientSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public Set<String> getIngredientSet() {
        return ingredientSet;
    }

    public void setIngredientSet(Set<String> ingredientSet) {
        this.ingredientSet = ingredientSet;
        // this setter will take a value, unit of measurement, and ingredient name
        // combine them into one string comma seperating the three components
        // ex. "1/2, cup, flour"
    }
}

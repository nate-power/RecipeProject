package ca.gbc.comp3095.RecipeProject.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Recipe extends BaseEntity{

    private String name;
    private Long userId;
    private String description;
    private final LocalDate dateCreated = LocalDate.now();
    // make enum for Accessors (public private)
    // make enum for Categories
    private int prepTime;
    private int cookTime;
    private int serving;
//    private Set<String> ingredientSet;


    public Recipe() {
    }

    // used for bootstrap data
    public Recipe(String name, Long userId, String description, int prepTime, int cookTime, int serving) {
        this.name = name;
        this.userId = userId;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.serving = serving;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

//    public List<Rating> getRatingList() {
//        return ratingList;
//    }
//
//    public void setRatingList(List<Rating> ratingList) {
//        this.ratingList = ratingList;
//    }
//
//    public Set<String> getIngredientSet() {
//        return ingredientSet;
//    }

//    public void setIngredientSet(Set<String> ingredientSet) {
//        this.ingredientSet = ingredientSet;
//        // this setter will take a value, unit of measurement, and ingredient name
//        // combine them into one string comma seperating the three components
//        // ex. "1/2, cup, flour"
//    }
}

package ca.gbc.comp3095.RecipeProject.model;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Recipe extends BaseEntity{

    private String name;
    private Long userId;
    private String description;
    private LocalDate dateCreated;
    @Enumerated
    private RecipeCategories category;
    private int prepTime;
    private int cookTime;
    private int serving;
    private String ingredients;
    private String steps;

    public Recipe() {
    }

    // used for bootstrap data
    public Recipe(String name, Long userId, String description, LocalDate dateCreated, RecipeCategories category, int prepTime, int cookTime, int serving, String ingredients, String steps) {
        this.name = name;
        this.userId = userId;
        this.description = description;
        this.dateCreated = dateCreated;
        this.category = category;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.serving = serving;
        this.ingredients = ingredients;
        this.steps = steps;
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

    public void setDateCreated(LocalDate dateCreated) { this.dateCreated = dateCreated; }

    public RecipeCategories getCategory() { return category; }

    public void setCategory(RecipeCategories category) { this.category = category; }

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

    public String getIngredients() { return ingredients; }

    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public String getSteps() { return steps; }

    public void setSteps(String steps) { this.steps = steps; }

    public static String uppercaseName(String name) {
        String uppercaseWord = "";
        String[] words = name.toLowerCase().split("\\s");

        for (String word : words) {
            String firstLetter = word.substring(0, 1);
            String restOfWord = word.substring(1);
            uppercaseWord += firstLetter.toUpperCase() + restOfWord + " ";
        }
        return uppercaseWord.trim();
    }

}

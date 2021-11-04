package ca.gbc.comp3095.RecipeProject.model;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Recipe extends BaseEntity{

    @NotEmpty(message = "Recipe name is mandatory")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @NotEmpty(message = "Recipe description is mandatory")
    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate dateCreated;

    @Enumerated
    private RecipeCategories category;

    @NotNull
    @Min(value = 1)
    private int prepTime;

    @NotNull
    @Min(value = 1)
    private int cookTime;

    @NotNull
    @Min(value = 1)
    private int serving;

    @NotEmpty(message = "You must include ingredients!")
    @Column(columnDefinition = "TEXT")
    private String ingredients;

    @NotEmpty(message = "You must include steps!")
    @Column(columnDefinition = "TEXT")
    private String steps;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeDate> recipeDates = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "favourite_recipes", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userFavourites = new HashSet<>();

    @Lob
    @Column(nullable=true)
    private String photoData;

    public Recipe() { }

    // used for bootstrap data
    public Recipe(String name, User user, String description, LocalDate dateCreated,
                  RecipeCategories category, int prepTime, int cookTime, int serving, String ingredients,
                  String steps) {
        this.name = name;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) { this.user = user; }

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

    public Set<RecipeDate> getRecipeDates() {
        return recipeDates;
    }

    public void setRecipeDates(Set<RecipeDate> recipeDates) {
        this.recipeDates = recipeDates;
    }

    public Set<User> getUserFavourites() {
        return userFavourites;
    }

    public void setUserFavourites(Set<User> userFavourites) {
        this.userFavourites = userFavourites;
    }

    public String getPhotoData() {
        return Objects.equals(photoData, "") ? photoData : "data:image/jpeg;charset=utf-8;base64," + photoData;
    }

    public void setPhotoData(String photoData) { this.photoData = photoData; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(getId(), recipe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

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

    public static String hyphenateName(String name) {
        String word = "";
        String[] words = name.toLowerCase().split(" ");
        return String.join("-", words);
    }
}

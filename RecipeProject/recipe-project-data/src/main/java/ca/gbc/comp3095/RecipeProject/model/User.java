//***************************//
//* Project: Null Recipes
//        * Assignment: Assignment 1
//        * Author(s): Alberto Dos Reis
//        * Student Number: 101232584
//        * Date: October 24, 2021
//        * Description: Model entity for registered users. Defines properties to be held in USER database table. Also
// defines relationship to joining tables for the user's created recipes, favourite recipes, and planned meals.
//***************************//

package ca.gbc.comp3095.RecipeProject.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email", "username"}))
public class User extends BaseEntity {

    @NotEmpty
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters long.")
    private String username;

    @NotEmpty
    private String password;

    @Email(message = "Please enter valid email format.")
    @NotEmpty
    @Size(min = 5, max = 70, message = "Email must be between 5 and 70 characters long.")
    private String email;

    @NotEmpty
    @Size(min = 2, max = 24, message = "First name must be between 2 and 24 characters long.")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 24, message = "Last name must be between 2 and 24 characters long.")
    private String lastName;

    @Lob
    @Column(nullable=true)
    private String photoData;

    @OneToMany(mappedBy = "user")
    private Set<RecipeDate> recipeDates = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Recipe> recipeSet = new HashSet<>();

    @ManyToMany(mappedBy = "userFavourites", fetch = FetchType.EAGER)
    private Set<Recipe> favouriteRecipes = new HashSet<>();

    @Column(columnDefinition = "TEXT")
    private String shoppingList;

    private String resetPasswordToken;

    public User() { }

    // used for dataloader
    public User(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shoppingList = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhotoData() {
        return Objects.equals(photoData, null) ? null : "data:image/jpeg;charset=utf-8;base64," + photoData;
    }

    public void setPhotoData(String photoData) {
        this.photoData = photoData;
    }

    public Set<RecipeDate> getRecipeDates() { return recipeDates; }

    public void setRecipeDates(Set<RecipeDate> recipeDates) { this.recipeDates = recipeDates; }

    public Set<Recipe> getRecipeSet() { return recipeSet; }

    public void setRecipeSet(Set<Recipe> recipeSet) { this.recipeSet = recipeSet; }

    public Set<Recipe> getFavouriteRecipes() { return favouriteRecipes; }

    public void setFavouriteRecipes(Set<Recipe> favouriteRecipes) { this.favouriteRecipes = favouriteRecipes; }

    public String getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(String shoppingList) {
        this.shoppingList = shoppingList;
    }

    public String getResetPasswordToken() { return resetPasswordToken; }

    public void setResetPasswordToken(String resetPasswordToken) { this.resetPasswordToken = resetPasswordToken; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
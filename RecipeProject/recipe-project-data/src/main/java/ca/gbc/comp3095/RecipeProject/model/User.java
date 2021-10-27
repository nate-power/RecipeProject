package ca.gbc.comp3095.RecipeProject.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User extends BaseEntity {

    @Column(name = "username")
    @NotEmpty
    @Size(min = 5)
    private String username;

    @Column(name = "password")
    @NotEmpty
    @Size(min = 4)
    private String password;

    @Column(name = "email")
    @Email
    @NotEmpty
    private String email;

    @Column(name = "firstname")
    @NotEmpty
    @Size(min = 2, max = 24)
    private String firstName;

    @Column(name = "lastname")
    @NotEmpty
    @Size(min = 2, max = 24)
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Recipe> favouriteRecipes;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Recipe> createdRecipes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<RecipeDate> schedule = new ArrayList<>();

    public User() { }

    public User(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Set<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(Set<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }

    public Set<Recipe> getCreatedRecipes() {
        return createdRecipes;
    }

    public void setCreatedRecipes(Set<Recipe> createdRecipes) {
        this.createdRecipes = createdRecipes;
    }

    public RecipeDate addToSchedule(LocalDate date, Long recipeId) {
        RecipeDate recipeDate = new RecipeDate(recipeId, date, this);
        schedule.add(recipeDate);
        return recipeDate;
    }

    public List<RecipeDate> getSchedule() {
        return schedule;
    }
}

package ca.gbc.comp3095.RecipeProject.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User extends BaseEntity {

    @Column(name = "username")
    @NotEmpty
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters long.")
    private String username;

    @Column(name = "password")
    @NotEmpty
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;

    @Column(name = "email")
    @Email(message = "Please enter valid email format.")
    @NotEmpty
    private String email;

    @Column(name = "firstname")
    @NotEmpty
    @Size(min = 2, max = 24, message = "First name must be between 2 and 24 characters long.")
    private String firstName;

    @Column(name = "lastname")
    @NotEmpty
    @Size(min = 2, max = 24, message = "Last name must be between 2 and 24 characters long.")
    private String lastName;

    @OneToMany(mappedBy = "user")
    private Set<RecipeDate> recipeDates = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Recipe> recipeSet = new HashSet<>();

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

    public Set<RecipeDate> getRecipeDates() { return recipeDates; }

    public void setRecipeDates(Set<RecipeDate> recipeDates) { this.recipeDates = recipeDates; }

    public Set<Recipe> getRecipeSet() { return recipeSet; }

    public void setRecipeSet(Set<Recipe> recipeSet) { this.recipeSet = recipeSet; }

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

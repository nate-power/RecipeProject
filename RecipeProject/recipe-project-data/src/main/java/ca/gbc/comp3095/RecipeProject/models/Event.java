//*********************************************************************************//
//* Project: Null Recipes
//        * Assignment: Assignment #2
//        * Author(s): Nathan Power
//        * Student Number: 101247770
//        * Date: November 20th, 2021
//        * Description: Event is an entity model that extends BaseEntity to get an ID. It contains properties
//        * for Event object in the system, such as name, description and date of event, and the getters/setters.
//*********************************************************************************//

package ca.gbc.comp3095.RecipeProject.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Event extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_event")
    private User user;

    @NotEmpty(message = "Event name is mandatory.")
    @Size(min = 5, max = 20, message = "Event Name must be between 5 and 20 characters.")
    private String name;

    @NotEmpty(message = "Event description is mandatory.")
    @Column(columnDefinition = "TEXT")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    // used for bootstrapping data
    public Event(User user, String name, String description, LocalDate date) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Event() { }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

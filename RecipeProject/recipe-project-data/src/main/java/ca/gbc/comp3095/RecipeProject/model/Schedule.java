package ca.gbc.comp3095.RecipeProject.model;

import java.time.LocalDate;
import java.util.Set;

public class Schedule {

    private LocalDate startDate;
    private LocalDate endDate;
    private Set<RecipeDate> recipeDateSet;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<RecipeDate> getRecipeDateSet() {
        return recipeDateSet;
    }

    public void setRecipeDateSet(Set<RecipeDate> recipeDateSet) {
        this.recipeDateSet = recipeDateSet;
    }
}

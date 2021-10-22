package ca.gbc.comp3095.RecipeProject.model;

public class RecipeDate {
    private Recipe recipe;
    private Schedule schedule;
    // make enum for days (Sunday-Saturday)
    // make enum for meal types (bfast, lunch, dinner, snack)

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}

package ca.gbc.comp3095.RecipeProject.model;

public class Rating {

    private User user;
    private Recipe recipe;
    // make enum of ratings (1-5)

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}

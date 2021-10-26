package ca.gbc.comp3095.RecipeProject.enumerations;

public enum RecipeCategories {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    APPETIZERS("Appetizers"),
    DESSERTS("Desserts"),
    SALADS("Salads"),
    SOUPS("Soups"),
    BAKERY("Bakery"),
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan");

    private final String display;

    RecipeCategories(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}

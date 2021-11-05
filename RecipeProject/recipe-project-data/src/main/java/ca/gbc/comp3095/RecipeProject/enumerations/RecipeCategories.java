//*********************************************************************************//
//* Project: Null Recipes
//        * Assignment: Assignment #1
//        * Author(s): Nathan Power
//        * Student Number: 101247770
//        * Date: October 26th, 2021
//        * Description: This file is simply an Enumeration that is used for the category of recipe in the database
//        * as well as for listing the different categories in the navbar of authenticated users to choose from.
//*********************************************************************************//

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
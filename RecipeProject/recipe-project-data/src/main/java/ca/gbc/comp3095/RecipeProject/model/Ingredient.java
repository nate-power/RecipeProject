package ca.gbc.comp3095.RecipeProject.model;

public class Ingredient {
    private String name;
    private double measurement;
    // make enum for units (lb, oz, etc.)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMeasurement() {
        return measurement;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }
}

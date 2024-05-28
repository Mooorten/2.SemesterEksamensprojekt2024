package com.example2semestereksamensprojekt2024.model;

// Definition af Meal-klassen
public class Meal {

    // Attributter for måltidet
    private Long mealid; // Unik identifikator for måltidet
    private String name; // Navn på måltidet
    private String ingredients; // Ingredienser til måltidet
    private String procedure; // Fremgangsmåde til tilberedning af måltidet
    private String duration; // Varighed for tilberedning af måltidet
    private String difficulty; // Sværhedsgrad for tilberedning af måltidet
    private String categories; // Kategorier, som måltidet tilhører

    // Default konstruktør
    public Meal() {
    }

    // Getter og setter metoder for attributterne
    public Long getMealid() {
        return mealid;
    }

    public void setMealid(Long mealid) {
        this.mealid = mealid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    // toString metode for at konvertere objektet til en strengrepræsentation
    @Override
    public String toString() {
        return "Meal{" +
                "mealid=" + mealid +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", procedure='" + procedure + '\'' +
                ", duration='" + duration + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", categories='" + categories + '\'' +
                '}';
    }
}
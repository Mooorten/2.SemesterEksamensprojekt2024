package com.example2semestereksamensprojekt2024.model;

public class Meal {
    private Long mealid;

    private String name;

    private String ingredients;

    private String procedure;
    
    private String duration;

    private String difficulty;
    
    private String categories;

    public Meal() {
    }

    public Meal(Long mealid, String name, String ingredients, String procedure, String duration, String difficulty, String categories) {
        this.mealid = mealid;
        this.name = name;
        this.ingredients = ingredients;
        this.procedure = procedure;
        this.duration = duration;
        this.difficulty = difficulty;
        this.categories = categories;
    }

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

    @Override
    public String toString() {
        return "Meal{" +
                "mealid=" + mealid +
                ", name='" + name + '\'' +
                ", recipe='" + ingredients + '\'' +
                ", procedure='" + procedure + '\'' +
                ", duration='" + duration + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", categories='" + categories + '\'' +
                '}';
    }
}

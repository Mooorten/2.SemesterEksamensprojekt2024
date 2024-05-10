package com.example2semestereksamensprojekt2024.model;

public class Ingredients {
    private Long ingredientsid;
    private String name;
    private String unit;

    public Ingredients() {
    }

    public Ingredients(Long ingredientsid, String name, String unit) {
        this.ingredientsid = ingredientsid;
        this.name = name;
        this.unit = unit;
    }

    public Long getIngredientsid() {
        return ingredientsid;
    }

    public void setIngredientsid(Long ingredientsid) {
        this.ingredientsid = ingredientsid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "ingredientid=" + ingredientsid +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
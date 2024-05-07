package com.example2semestereksamensprojekt2024.model;

public class ingredients {
    private Long ingredientid;
    private String name;
    private String unit;

    public ingredients() {
    }

    public ingredients(Long ingredientid, String name, String unit) {
        this.ingredientid = ingredientid;
        this.name = name;
        this.unit = unit;
    }

    public Long getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(Long ingredientid) {
        this.ingredientid = ingredientid;
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
        return "ingredients{" +
                "ingredientid=" + ingredientid +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
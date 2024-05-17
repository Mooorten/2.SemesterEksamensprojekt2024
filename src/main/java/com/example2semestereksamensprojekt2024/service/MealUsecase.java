package com.example2semestereksamensprojekt2024.service;
import java.util.List;
import java.util.Optional;

import com.example2semestereksamensprojekt2024.model.Meal;
import com.example2semestereksamensprojekt2024.DBController.MealDbSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealUsecase {
    @Autowired
    private MealDbSql mealDbSql;

    public void createMeal(Meal meal) {
        mealDbSql.createMeal(meal);
    }

    public void updateMeal(Meal meal) {
        mealDbSql.updateMeal(meal);
    }

    public void deleteMeal(Long id) {
        mealDbSql.deleteMeal(id);
    }

    public List<Meal> findAllMeals() {
        return mealDbSql.findAllMeals();
    }

    public Optional<Meal> findMealByID(Long id){
        return mealDbSql.findMealByID(id);
    }
}
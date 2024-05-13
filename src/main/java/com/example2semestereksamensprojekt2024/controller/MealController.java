package com.example2semestereksamensprojekt2024.controller;

import com.example2semestereksamensprojekt2024.model.Meal;
import com.example2semestereksamensprojekt2024.service.MealUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class  MealController {

    @Autowired
    private MealUsecase mealUsecase;

    @PostMapping("/createMeal")
    public String createMeal(@ModelAttribute Meal meal) {
        mealUsecase.createMeal(meal);
        return "adminmenu";
    }


   @GetMapping("/Monday/meals")
    public String mondayMeals() {
        return "Monday/meals";
    }

    @GetMapping("/Tuesday/meals")
    public String tuesdayMeals() {
        return "Tuesday/meals";
    }

    @GetMapping("/Wednesday/meals")
    public String wednesdayMeals() {
        return "Wednesday/meals";
    }

    @GetMapping("/Thursday/meals")
    public String ThursdayMeals() {
        return "Thursday/meals";
    }

    @GetMapping("/Friday/meals")
    public String FridayMeals() {
        return "Friday/meals";
    }

    @GetMapping("/Saturday/meals")
    public String SaturdayMeals() {
        return "Saturday/meals";
    }

    @GetMapping("/Sunday/meals")
    public String SundayMeals() {
        return "Sunday/meals";
    }

    @GetMapping("/Monday/Pmeals")
    public String previousMondayMeals() {
        return "Monday/Pmeals";
    }

    @GetMapping("/Tuesday/Pmeals")
    public String previousTuesdayMeals() {
        return "Tuesday/Pmeals";
    }

    @GetMapping("/Wednesday/Pmeals")
    public String previousWednesdayMeals() {
        return "Wednesday/Pmeals";
    }

    @GetMapping("/Thursday/Pmeals")
    public String previousThursdayMeals() {
        return "Thursday/Pmeals";
    }

    @GetMapping("/Friday/Pmeals")
    public String previousFridayMeals() {
        return "Friday/Pmeals";
    }

    @GetMapping("/Saturday/Pmeals")
    public String previousSaturdayMeals() {
        return "Saturday/Pmeals";
    }

    @GetMapping("/Sunday/Pmeals")
    public String previousSundayMeals() {
        return "Sunday/Pmeals";
    }
}


    /*@GetMapping("/{day}/meals")
    public String dayMeals(@PathVariable String day) {
        return day + "meals";
    }*/

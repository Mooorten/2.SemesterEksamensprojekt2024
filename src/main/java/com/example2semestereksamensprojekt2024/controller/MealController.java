package com.example2semestereksamensprojekt2024.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class  MealController {
    @GetMapping("/Mandag/meals")
    public String mondayMeals() {
        return "Mandag/meals";
    }

    @GetMapping("/Tirsdag/meals")
    public String tuesdayMeals() {
        return "Tirsdag/meals";
    }

    @GetMapping("/Onsdag/meals")
    public String wednesdayMeals() {
        return "Onsdag/meals";
    }

    @GetMapping("/Torsdag/meals")
    public String ThursdayMeals() {
        return "Torsdag/meals";
    }

    @GetMapping("/Fredag/meals")
    public String FridayMeals() {
        return "Fredag/meals";
    }

    @GetMapping("/Lørdag/meals")
    public String SaturdayMeals() {
        return "Lørdag/meals";
    }

    @GetMapping("/Søndag/meals")
    public String SundayMeals() {
        return "Søndag/meals";
    }
}

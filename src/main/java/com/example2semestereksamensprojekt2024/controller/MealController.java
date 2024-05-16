package com.example2semestereksamensprojekt2024.controller;

import com.example2semestereksamensprojekt2024.model.Meal; // Importerer Meal-modelklassen
import com.example2semestereksamensprojekt2024.service.MealUsecase; // Importerer MealUsecase-serviceklassen
import org.springframework.beans.factory.annotation.Autowired; // Importerer Autowired-annotationen
import org.springframework.stereotype.Controller; // Importerer Controller-annotationen
import org.springframework.web.bind.annotation.GetMapping; // Importerer GetMapping-annotationen
import org.springframework.web.bind.annotation.ModelAttribute; // Importerer ModelAttribute-annotationen
import org.springframework.web.bind.annotation.PostMapping; // Importerer PostMapping-annotationen

@Controller // Fortæller Spring, at dette er en controller-klasse
public class MealController {

    @Autowired // Indsprøjter en instans af MealUsecase
    private MealUsecase mealUsecase;

    @GetMapping("/createmealAdmin") // Mapping for at hente siden til oprettelse af måltider for admin
    public String createMeals() {
        return "createmealAdmin"; // Returnerer navnet på HTML-siden
    }

    @PostMapping("/createMeal") // Mapping for at oprette et måltid
    public String createMeal(@ModelAttribute Meal meal) {
        mealUsecase.createMeal(meal); // Kalder metoden createMeal i MealUsecase og sender måltidet som parameter
        return "redirect:/adminmenu"; // Brug redirect her // Returnerer navnet på HTML-siden for adminmenuen
    }

    // Mappings for at vise måltider for hver dag i ugen
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

    // Mappings for at vise tidligere uges måltider for hver dag i ugen
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

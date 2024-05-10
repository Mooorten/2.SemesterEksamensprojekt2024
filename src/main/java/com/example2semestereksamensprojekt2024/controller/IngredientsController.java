package com.example2semestereksamensprojekt2024.controller;

import com.example2semestereksamensprojekt2024.model.Ingredients;
import com.example2semestereksamensprojekt2024.service.IngredientsUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientsController {
    @Autowired
    private IngredientsUsecase ingredientsUsecase;

    @PostMapping("/createIngredient")
    public String createIngredient(@ModelAttribute Ingredients ingredients) {
        ingredientsUsecase.createIngredient(ingredients);
        return "adminmenu";
    }
}

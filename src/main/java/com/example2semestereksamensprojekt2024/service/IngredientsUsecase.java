package com.example2semestereksamensprojekt2024.service;
import java.util.List;
import java.util.Optional;

import com.example2semestereksamensprojekt2024.model.Ingredients;
import com.example2semestereksamensprojekt2024.repository.IngredientsDbSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientsUsecase {
    @Autowired
    private IngredientsDbSql ingredientsDbSql;

    public void createIngredient(Ingredients ingredient) {
        ingredientsDbSql.createIngredient(ingredient);
    }

    public void updateIngredient(Ingredients ingredient) {
        ingredientsDbSql.updateIngredient(ingredient);
    }

    public void deleteIngredient(Long id) {
        ingredientsDbSql.deleteIngredient(id);
    }

    public List<Ingredients> findAllIngredients() {
        return ingredientsDbSql.findAllIngredients();
    }

    public Optional<Ingredients> findIngredientByID(Long id){
        return ingredientsDbSql.findIngredientByID(id);
    }
}
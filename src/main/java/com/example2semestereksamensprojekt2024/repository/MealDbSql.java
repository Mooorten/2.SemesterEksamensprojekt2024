package com.example2semestereksamensprojekt2024.repository;

import com.example2semestereksamensprojekt2024.model.Meal;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MealDbSql {

    private JdbcTemplate jdbcTemplate;

    public MealDbSql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createMeal(Meal meal) {
        try {
            String sql = "INSERT INTO meal (name, ingredients, `procedure`, duration, difficulty, categories) VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, meal.getName(), meal.getIngredients(), meal.getProcedure(), meal.getDuration(), meal.getDifficulty(), meal.getCategories());
        } catch (RuntimeException e) {
            throw new RuntimeException("Fejl under oprettelse af måltid", e);
        }

    }

    // Opdaterer et måltid i databasen
    public void updateMeal(Meal meal) {
        try {
            String sql = "UPDATE meal SET name = ?, recipe = ?, procedure = ?, duration = ?, difficulty = ?, categories = ?";
            jdbcTemplate.update(sql, meal.getName(), meal.getIngredients(), meal.getProcedure(), meal.getDuration(), meal.getDifficulty(), meal.getDuration());
        } catch (RuntimeException e) {
            throw new RuntimeException("Fejl under opdatering af måltid", e);
        }
    }

    // Sletter et måltid fra databasen
    public void deleteMeal(Long id) {
        try {
            String sql = "DELETE FROM meal WHERE mealid = ?";
            jdbcTemplate.update(sql, id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Fejl under sletning af måltid", e);
        }
    }

    // Finder et måltid i databasen ud fra måltidets ID
    public Optional<Meal> findMealByID(Long mealID) {
        try {
            String sql = "SELECT * FROM meal WHERE mealid = ?";
            Meal meal = jdbcTemplate.queryForObject(sql, new Object[]{mealID}, mealRowMapper());
            return Optional.ofNullable(meal);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // Return empty Optional if no user is found
        } catch (DataAccessException e) {
            throw new RuntimeException("Måltidet kunne ikke findes", e);
        }
    }

    // Finder alle måltider i databasen
    public List<Meal> findAllMeals() {
        try {
            String sql = "SELECT * FROM meal";
            return jdbcTemplate.query(sql, mealRowMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException("Måltidet findes ikke", e);
        }
    }

    // Mapper rækker fra databasen til Meal-objekter
    public RowMapper<Meal> mealRowMapper() {
        return (rs, rowNum) -> {
            Meal meal = new Meal();
            meal.setMealid(rs.getLong("mealid"));
            meal.setName(rs.getString("name"));
            meal.setIngredients(rs.getString("ingredients"));
            meal.setProcedure(rs.getString("procedure"));
            meal.setDuration(rs.getString("duration"));
            meal.setDifficulty(rs.getString("difficulty"));
            meal.setCategories(rs.getString("categories"));
            return meal;
        };
    }
}

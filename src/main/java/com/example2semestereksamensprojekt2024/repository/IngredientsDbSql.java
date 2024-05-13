package com.example2semestereksamensprojekt2024.repository;

import com.example2semestereksamensprojekt2024.model.Ingredients;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IngredientsDbSql {

    private JdbcTemplate jdbcTemplate;

    public IngredientsDbSql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Opretter en ingrediens i databasen
    public void createIngredient(Ingredients ingredients) {
        try {
            String sql = "INSERT INTO ingredients (name, unit) VALUES (?, ?)";
            jdbcTemplate.update(sql, ingredients.getName(), ingredients.getUnit());
        } catch (RuntimeException e) {
            throw new RuntimeException("Fejl under oprettelse af ingrediens", e);
        }
    }

    // Opdaterer en ingrediens i databasen
    public void updateIngredient(Ingredients ingredients) {
        try {
            String sql = "UPDATE meal SET name = ?, unit = ?";
            jdbcTemplate.update(sql, ingredients.getName(), ingredients.getUnit());
        } catch (RuntimeException e) {
            throw new RuntimeException("Fejl under opdatering af ingrediens", e);
        }
    }

    // Sletter en ingrediens fra databasen
    public void deleteIngredient(Long id) {
        try {
            String sql = "DELETE FROM ingredients WHERE ingredientsid = ?";
            jdbcTemplate.update(sql, id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Fejl under sletning af ingrediens", e);
        }
    }

    // Finder en ingrediens i databasen ud fra ingrediensens ID
    public Optional<Ingredients> findIngredientByID(Long ingredientsID) {
        try {
            String sql = "SELECT * FROM ingredients WHERE ingredientsid = ?";
            Ingredients ingredients = jdbcTemplate.queryForObject(sql, new Object[]{ingredientsID}, ingredientsRowMapper());
            return Optional.ofNullable(ingredients);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new RuntimeException("Ingrediensen kunne ikke findes", e);
        }
    }

    // Finder alle ingredienser i databasen
    public List<Ingredients> findAllIngredients() {
        try {
            String sql = "SELECT * FROM ingredients";
            return jdbcTemplate.query(sql, ingredientsRowMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException("Fejl under indlæsning af alle ingredienser", e);
        }
    }

    // Mapper rækker fra databasen til Ingredients-objekter
    public RowMapper<Ingredients> ingredientsRowMapper() {
        return (rs, rowNum) -> {
            Ingredients ingredients = new Ingredients();
            ingredients.setIngredientsid(rs.getLong("ingredientsid"));
            ingredients.setName(rs.getString("name"));
            ingredients.setUnit(rs.getString("unit"));
            return ingredients;
        };
    }
}

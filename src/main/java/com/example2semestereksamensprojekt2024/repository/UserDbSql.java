package com.example2semestereksamensprojekt2024.repository;

import com.example2semestereksamensprojekt2024.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDbSql {
    private JdbcTemplate jdbcTemplate;

    public UserDbSql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Opretter en bruger i databasen
    public void createUser(User user) {
        try {
            String sql = "INSERT INTO user (email, password, name, surname, phone, weight, height, age, gender, goals, activitylevel, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getName(), user.getSurname(), user.getPhone(), user.getWeight(), user.getHeight(), user.getAge(), user.getGender(), user.getGoals(), user.getActivitylevel(), "user");
        } catch (RuntimeException e) {
            throw new RuntimeException("Fejl under oprettelse af bruger", e);
        }
    }

    // Opdaterer en bruger i databasen
    public void updateUser(User userToUpdate, User currentUser) {
        if ("admin".equals(currentUser.getRole())) {
            if (currentUser.getUserid().equals(userToUpdate.getUserid())) {
                try {
                    String sql = "UPDATE user SET name = ?, surname = ?, email = ?, password = ?, phone = NULL, weight = NULL, height = NULL, age = NULL, gender = NULL, goals = NULL, activitylevel = NULL WHERE userid = ?";
                    jdbcTemplate.update(sql, userToUpdate.getName(), userToUpdate.getSurname(), userToUpdate.getEmail(), userToUpdate.getPassword(), userToUpdate.getUserid());
                } catch (RuntimeException e) {
                    throw new RuntimeException("Fejl under opdatering af bruger", e);
                }
            } else {
                throw new RuntimeException("Administratorer kan kun opdatere deres egne oplysninger.");
            }
        } else {
            try {
                String sql = "UPDATE user SET name = ?, surname = ?, email = ?, password = ?, phone = ?, weight = ?, height = ?, age = ?, gender = ?, goals = ?, activitylevel = ? WHERE userid = ?";
                jdbcTemplate.update(sql, userToUpdate.getName(), userToUpdate.getSurname(), userToUpdate.getEmail(), userToUpdate.getPassword(), userToUpdate.getPhone(), userToUpdate.getWeight(), userToUpdate.getHeight(), userToUpdate.getAge(), userToUpdate.getGender(), userToUpdate.getGoals(), userToUpdate.getActivitylevel(), userToUpdate.getUserid());
            } catch (RuntimeException e) {
                throw new RuntimeException("Fejl under opdatering af bruger", e);
            }
        }
    }

    // Sletter en bruger fra databasen
    public void deleteUser(Long id) {
        try {
            String sql = "DELETE FROM user WHERE userid = ?";
            jdbcTemplate.update(sql, id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Fejl under sletning af bruger", e);
        }
    }

    // Finder en bruger i databasen ud fra brugerens ID
    public Optional<User> findUserByID(Long userId) {
        try {
            String sql = "SELECT * FROM user WHERE userid = ?";
            User user = jdbcTemplate.queryForObject(sql, new Object[]{userId}, userRowMapper());
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // Return empty Optional if no user is found
        } catch (DataAccessException e) {
            throw new RuntimeException("Brugeren findes ikke", e);
        }
    }

    // Finder alle brugere i databasen
    public List<User> findAllUsers() {
        try {
            String sql = "SELECT * FROM user";
            return jdbcTemplate.query(sql, userRowMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException("Fejl under indlæsning af alle brugere", e);
        }
    }

    // Finder en bruger i databasen baseret på email og adgangskode til login
    public User findLogin(String email, String password) {
        try {
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            List<User> users = jdbcTemplate.query(sql, new Object[]{email, password}, userRowMapper());
            if (!users.isEmpty()) {
                return users.get(0);
            } else {
                return null;
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Mapper rækker fra databasen til User-objekter
    public RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserid(rs.getLong("userid"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            user.setWeight(rs.getString("weight"));
            user.setHeight(rs.getString("height"));
            user.setAge(rs.getInt("age"));
            user.setGender(rs.getString("gender"));
            user.setGoals(rs.getString("goals"));
            user.setActivitylevel(rs.getString("activitylevel"));
            user.setRole(rs.getString("role"));
            return user;
        };
    }
}

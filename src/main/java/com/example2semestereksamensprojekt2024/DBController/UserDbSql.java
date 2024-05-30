package com.example2semestereksamensprojekt2024.DBController;

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
            String sql = "INSERT INTO user (email, password, name, surname, phone, weight, height, age, gender, goals, activitylevel, role, bmr) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getName(), user.getSurname(), user.getPhone(), user.getWeight(), user.getHeight(), user.getAge(), user.getGender(), user.getGoals(), user.getActivitylevel(), "user", user.getBmr());
        } catch (RuntimeException e) {
            throw new RuntimeException("Fejl under oprettelse af bruger", e);
        }
    }

    public void updateUser(User userToUpdate, User currentUser) {
        if ("user".equals(currentUser.getRole())) {
            try {
                String sql = "UPDATE user SET name = ?, surname = ?, email = ?, password = ?, phone = ?, weight = ?, height = ?, age = ?, gender = ?, goals = ?, activitylevel = ?, bmr = ? WHERE userid = ?";
                double bmr = calculateBMR(userToUpdate.getUserid()); // Beregn BMR
                jdbcTemplate.update(sql, userToUpdate.getName(), userToUpdate.getSurname(), userToUpdate.getEmail(), userToUpdate.getPassword(), userToUpdate.getPhone(), userToUpdate.getWeight(), userToUpdate.getHeight(), userToUpdate.getAge(), userToUpdate.getGender(), userToUpdate.getGoals(), userToUpdate.getActivitylevel(), bmr, userToUpdate.getUserid());
            } catch (RuntimeException e) {
                throw new RuntimeException("Fejl under opdatering af bruger", e);
            }
        } else if ("admin".equals(currentUser.getRole())) {
            try {
                String sql = "UPDATE user SET name = ?, surname = ?, email = ?, password = ? WHERE userid = ?";
                jdbcTemplate.update(sql, userToUpdate.getName(), userToUpdate.getSurname(), userToUpdate.getEmail(), userToUpdate.getPassword(), userToUpdate.getUserid());
            } catch (RuntimeException e) {
                throw new RuntimeException("Fejl under opdatering af admin", e);
            }
        } else {
            throw new IllegalArgumentException("Ugyldig brugerrolle");
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
        // Vi bruger optional fordi vi kan returnere 0 uden at programmet går ned
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

    // Finder en bruger i databasen baseret på email og adgangskode til login
    public User findLogin(String email, String password) {
        try {
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            List<User> users = jdbcTemplate.query(sql, new String[]{email, password}, userRowMapper());
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
        // En anononym funktion som tager 2 parametre ResultSet og int
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
            user.setBmr(rs.getDouble("bmr"));
            return user;
        };
    }

    public double calculateBMR(Long userId) {
        Optional<User> optionalUser = findUserByID(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Beregning af BMR baseret på Harris-Benedict formel
            double bmr;
            if ("Mand".equals(user.getGender())) {
                bmr = (10 * Double.parseDouble(user.getWeight())) + (6.25 * Double.parseDouble(user.getHeight())) - (5 * user.getAge()) + 5;
            } else if ("Kvinde".equals(user.getGender())) {
                bmr = (10 * Double.parseDouble(user.getWeight())) + (6.25 * Double.parseDouble(user.getHeight())) - (5 * user.getAge()) - 161;
            } else {
                throw new IllegalArgumentException("Ugyldig køn");
            }

            // Justering baseret på aktivitetsniveau
            double activityLevelMultiplier;
            switch (user.getActivitylevel()) {
                case "Stillesiddende":
                    activityLevelMultiplier = 1.2;
                    break;
                case "Let aktiv":
                    activityLevelMultiplier = 1.5;
                    break;
                case "Moderat aktiv":
                    activityLevelMultiplier = 1.7;
                    break;
                case "Meget aktiv":
                    activityLevelMultiplier = 1.9;
                    break;
                case "Atlet":
                    activityLevelMultiplier = 2.4;
                    break;
                default:
                    throw new IllegalArgumentException("Ugyldigt aktivitetsniveau");
            }

            // Justering baseret på brugerens mål
            double goalAdjustment;
            switch (user.getGoals()) {
                case "Tab dig":
                    goalAdjustment = -500;
                    break;
                case "Tag på":
                    goalAdjustment = 500;
                    break;
                case "Hold vægten":
                    goalAdjustment = 0;
                    break;
                case "Opbyg muskel":
                    goalAdjustment = 300;
                    break;
                default:
                    throw new IllegalArgumentException("Ugyldigt mål");
            }

            // Beregning af endelig BMR inklusive måljustering
            bmr = (bmr * activityLevelMultiplier) + goalAdjustment;
            return bmr;
        } else {
            throw new IllegalArgumentException("Brugeren med det angivne ID blev ikke fundet");
        }
    }
}
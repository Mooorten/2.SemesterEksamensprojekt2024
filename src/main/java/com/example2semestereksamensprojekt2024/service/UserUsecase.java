package com.example2semestereksamensprojekt2024.service;

import java.util.Optional;
import com.example2semestereksamensprojekt2024.model.User;
import com.example2semestereksamensprojekt2024.DBController.UserDbSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUsecase {

    @Autowired
    private UserDbSql userDbSql;

    public void createUser(User user) {
        userDbSql.createUser(user);
    }

    public void updateUser(User userToUpdate, User currentUser) {
        userDbSql.updateUser(userToUpdate, currentUser);
    }

    public void deleteUser(Long id) {
        userDbSql.deleteUser(id);
    }

    public Optional<User> findUserByID(Long id) {
        return userDbSql.findUserByID(id);
    }

    public User findLogin(String email, String password) {
        return userDbSql.findLogin(email, password);
    }

    public double calculateBMR(Long id) {
        return userDbSql.calculateBMR(id);
    }
}
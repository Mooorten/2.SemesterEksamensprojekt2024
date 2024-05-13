package com.example2semestereksamensprojekt2024.service;

import java.util.List;
import java.util.Optional;

import com.example2semestereksamensprojekt2024.model.User;
import com.example2semestereksamensprojekt2024.repository.UserDbSql;
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
        if ("admin".equals(currentUser.getRole())) {
            if (currentUser.getUserid().equals(userToUpdate.getUserid())) {
                userDbSql.updateUser(userToUpdate, currentUser);
            } else {
                throw new RuntimeException("Administratorer kan kun opdatere deres egne oplysninger.");
            }
        } else {
            userDbSql.updateUser(userToUpdate, currentUser);
        }
    }

    public void deleteUser(Long id) {
        userDbSql.deleteUser(id);
    }

    public User findLogin(String email, String password) {
        return userDbSql.findLogin(email, password);
    }

    public List<User> findAllUsers() {
        return userDbSql.findAllUsers();
    }

    public Optional<User> findUserByID(Long id){
        return userDbSql.findUserByID(id);
    }
}
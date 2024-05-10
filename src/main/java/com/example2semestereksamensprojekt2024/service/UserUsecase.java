package com.example2semestereksamensprojekt2024.service;

import java.util.List;
import java.util.Optional;

import com.example2semestereksamensprojekt2024.model.User;
import com.example2semestereksamensprojekt2024.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUsecase {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public void updateUser(User userToUpdate, User currentUser) {
        if ("admin".equals(currentUser.getRole())) {
            if (currentUser.getUserid().equals(userToUpdate.getUserid())) {
                userRepository.updateUser(userToUpdate, currentUser);
            } else {
                throw new RuntimeException("Administratorer kan kun opdatere deres egne oplysninger.");
            }
        } else {
            userRepository.updateUser(userToUpdate, currentUser);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    public User findLogin(String email, String password) {
        return userRepository.findLogin(email, password);
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    public Optional<User> findUserByID(Long id){
        return userRepository.findUserByID(id);
    }
}
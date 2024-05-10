package com.example2semestereksamensprojekt2024.controller;

import com.example2semestereksamensprojekt2024.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example2semestereksamensprojekt2024.service.UserUsecase;

@Controller
public class UserController {
    @Autowired
    private UserUsecase userUsecase;

    @GetMapping("/")
    public String loginForm() {
        return "homepage";
    }

    @GetMapping("/saveUser")
    public String saveUserForm(Model model) {
        model.addAttribute("user", new User());
        return "saveUser";
    }

    @GetMapping("/editUser")
    public String editUserForm() {
        return "editUser";
    }

    @GetMapping("/editAdmin")
    public String editAdminForm() {
        return "editAdmin";
    }

    @GetMapping("/payment")
    public String paymentForm() {
        return "payment";
    }

    @GetMapping("/thisweek")
    public String thisweek() {
        return "thisweek";
    }

    @GetMapping("/previousweeks")
    public String previousweeks() {
        return "previousweeks";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute User user) {
        userUsecase.createUser(user);
        return "login";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:/login";
        }
        userUsecase.updateUser(user, currentUser);
        return "redirect:/login";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userUsecase.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/user/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        userUsecase.findUserByID(id).ifPresent(user -> model.addAttribute("user", user));
        return "editUser";
    }

    @GetMapping("/user/editAdmin/{id}")
    public String showEditAdminForm(@PathVariable Long id, Model model) {
        userUsecase.findUserByID(id).ifPresent(user -> model.addAttribute("user", user));
        return "editAdmin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpSession session) {
        User authenticatedUser = userUsecase.findLogin(user.getEmail(), user.getPassword());
        if (authenticatedUser != null) {
            // Tilføj den autentificerede bruger til sessionen
            session.setAttribute("currentUser", authenticatedUser);
            if (authenticatedUser.getRole().equals("admin")) {
                return "redirect:/adminmenu";
            } else if (authenticatedUser.getRole().equals("user")) {
                return "redirect:/menu";
            } else {
                model.addAttribute("error", "Ugyldig brugerrolle");
                return "login";
            }
        } else {
            model.addAttribute("error", "Ugyldig email eller kodeord");
            return "login";
        }
    }

    @GetMapping("/login")
    public String redirectToLogin() {
        return "login";
    }

    @GetMapping("/menu")
    public String getMenuPage(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null && currentUser.getRole().equals("user")) {
            model.addAttribute("currentUser", currentUser);
            return "menu";
        } else {
            // til login-siden, hvis ingen autentificeret bruger findes eller hvis brugeren ikke har medlemsrolle
            return "redirect:/login";
        }
    }

    @GetMapping("/adminmenu")
    public String getAdminMenuPage(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null && currentUser.getRole().equals("admin")) {
            model.addAttribute("currentUser", currentUser);
            return "adminmenu";
        } else {
            // til login-siden, hvis ingen autentificeret bruger findes eller hvis brugeren ikke har adminrolle
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
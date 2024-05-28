package com.example2semestereksamensprojekt2024.UIController;

import com.example2semestereksamensprojekt2024.model.User;
import com.example2semestereksamensprojekt2024.service.UserUsecase;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserUsecase userUsecase;

    @GetMapping("/")
    public String homePage() {
        return "homepage";
    }

    @GetMapping("/saveUser")
    public String saveUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @GetMapping("/editUser")
    public String editUserForm(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            return "editUser";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/editAdmin")
    public String editAdminForm(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null && "admin".equals(currentUser.getRole())) {
            model.addAttribute("user", currentUser);
            return "editAdmin";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/payment")
    public String paymentForm(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            return "payment";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/thisweek")
    public String thisweek(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            return "thisweek";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/previousweeks")
    public String previousweeks(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            return "previousweeks";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute User user, Model model) {
        String errorMessage = validateUser(user);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("user", user);
            return "createUser";
        }

        double bmr = 0.0;
        if ("user".equals(user.getRole())) {
            bmr = userUsecase.calculateBMR(user.getUserid());
        }
        user.setBmr(bmr);
        userUsecase.createUser(user);
        return "login";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        String errorMessage = validateUser(user);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("user", user);
            return "editUser";
        }

        if ("user".equals(currentUser.getRole())) {
            userUsecase.updateUser(user, currentUser);
        } else {
            userUsecase.updateUser(user, currentUser);
        }
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
    public String findLogin(@ModelAttribute User user, Model model, HttpSession session) {
        User authenticatedUser = userUsecase.findLogin(user.getEmail(), user.getPassword());
        if (authenticatedUser != null) {
            userUsecase.updateUser(authenticatedUser, authenticatedUser);
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
        if (currentUser != null && "user".equals(currentUser.getRole())) {
            model.addAttribute("currentUser", currentUser);
            return "menu";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/adminmenu")
    public String getAdminMenuPage(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null && "admin".equals(currentUser.getRole())) {
            model.addAttribute("currentUser", currentUser);
            return "adminmenu";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    private String validateUser(User user) {
        if (user.getName() == null || !user.getName().matches("^[a-zA-ZæøåÆØÅ\\s]+$")) {
            return "Navn må kun indeholde bogstaver og mellemrum.";
        }
        if (user.getSurname() == null || !user.getSurname().matches("^[a-zA-ZæøåÆØÅ\\s]+$")) {
            return "Efternavn må kun indeholde bogstaver og mellemrum.";
        }
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            return "Email skal indeholde '@'.";
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return "Kodeord kan ikke være tom.";
        }
        if (user.getPhone() == null || !user.getPhone().matches("^\\d{8}$")) {
            return "Telefonnummer skal være 8 cifre.";
        }
        try {
            int weight = Integer.parseInt(user.getWeight());
            if (weight < 30 || weight > 200) {
                return "Vægt skal være et tal mellem 30 og 200.";
            }
        } catch (NumberFormatException e) {
            return "Vægt skal være et tal mellem 30 og 200.";
        }
        try {
            int height = Integer.parseInt(user.getHeight());
            if (height < 120 || height > 250) {
                return "Højde skal være et tal mellem 120 og 250.";
            }
        } catch (NumberFormatException e) {
            return "Højde skal være et tal mellem 120 og 250.";
        }
        try {
            if (user.getAge() < 15 || user.getAge() > 100) {
                return "Alder skal være et tal mellem 15 og 100.";
            }
        } catch (NumberFormatException e) {
            return "Alder skal være et tal mellem 15 og 100.";
        }
        if (user.getGender() == null || user.getGender().isEmpty()) {
            return "Køn kan ikke være tom.";
        }
        if (user.getGoals() == null || user.getGoals().isEmpty()) {
            return "Mål kan ikke være tom.";
        }
        if (user.getActivitylevel() == null || user.getActivitylevel().isEmpty()) {
            return "Aktivitetsniveau kan ikke være tom.";
        }
        return null; // No validation errors
    }
}

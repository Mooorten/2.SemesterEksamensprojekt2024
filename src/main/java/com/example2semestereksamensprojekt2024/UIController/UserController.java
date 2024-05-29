package com.example2semestereksamensprojekt2024.UIController;

import com.example2semestereksamensprojekt2024.model.User; // Importerer User-modelklassen
import com.example2semestereksamensprojekt2024.service.UserUsecase;
import jakarta.servlet.http.HttpSession; // Importerer HttpSession fra Jakarta EE
import org.springframework.beans.factory.annotation.Autowired; // Importerer Autowired-annotationen
import org.springframework.stereotype.Controller; // Importerer Controller-annotationen
import org.springframework.ui.Model; // Importerer Model-klassen
import org.springframework.web.bind.annotation.*; // Importerer alle annoteringer fra Spring Web
import org.springframework.web.bind.annotation.PostMapping;

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
    public String createUser(@ModelAttribute User user) {
        double bmr = 0.0;
        if ("user".equals(user.getRole())) {
            bmr = userUsecase.calculateBMR(user.getUserid());
        }
        user.setBmr(bmr);
        userUsecase.createUser(user);
        return "login";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
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
            // Opdater brugeroplysninger ved login
            userUsecase.updateUser(authenticatedUser, authenticatedUser);

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
}
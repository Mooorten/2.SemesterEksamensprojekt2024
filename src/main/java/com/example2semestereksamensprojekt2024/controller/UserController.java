package com.example2semestereksamensprojekt2024.controller;

import com.example2semestereksamensprojekt2024.model.User; // Importerer User-modelklassen
import com.example2semestereksamensprojekt2024.service.UserUsecase;
import jakarta.servlet.http.HttpSession; // Importerer HttpSession fra Jakarta EE
import org.springframework.beans.factory.annotation.Autowired; // Importerer Autowired-annotationen
import org.springframework.stereotype.Controller; // Importerer Controller-annotationen
import org.springframework.ui.Model; // Importerer Model-klassen
import org.springframework.web.bind.annotation.*; // Importerer alle annoteringer fra Spring Web

@Controller // Fortæller Spring, at dette er en controller-klasse
public class UserController {

    @Autowired // Indsprøjter en instans af UserUsecase
    private UserUsecase userUsecase;

    // Mapping for at vise loginformularen på startsiden
    @GetMapping("/")
    public String loginForm() {
        return "homepage"; // Returnerer navnet på HTML-siden
    }

    // Mapping for at vise formularen til oprettelse af bruger
    @GetMapping("/saveUser")
    public String saveUserForm(Model model) {
        model.addAttribute("user", new User()); // Tilføjer en tom bruger til modellen
        return "saveUser"; // Returnerer navnet på HTML-siden
    }

    // Mapping for at vise formularen til redigering af brugeroplysninger
    @GetMapping("/editUser")
    public String editUserForm() {
        return "editUser"; // Returnerer navnet på HTML-siden
    }

    // Mapping for at vise formularen til redigering af adminoplysninger
    @GetMapping("/editAdmin")
    public String editAdminForm() {
        return "editAdmin"; // Returnerer navnet på HTML-siden
    }

    // Mapping for at vise betalingsformularen
    @GetMapping("/payment")
    public String paymentForm() {
        return "payment"; // Returnerer navnet på HTML-siden
    }

    // Mapping for at vise ugens menu
    @GetMapping("/thisweek")
    public String thisweek() {
        return "thisweek"; // Returnerer navnet på HTML-siden
    }

    // Mapping for at vise tidligere ugers menuer
    @GetMapping("/previousweeks")
    public String previousweeks() {
        return "previousweeks"; // Returnerer navnet på HTML-siden
    }

    // Mapping for at oprette en ny bruger
    @PostMapping("/createUser")
    public String createUser(@ModelAttribute User user, HttpSession session) {
        // Beregn BMR-værdien, hvis brugeren er en "user"
        double bmr = 0.0; // Default-værdi
        if ("user".equals(user.getRole())) {
            bmr = userUsecase.calculateBMR(user.getUserid());
        }

        // Opdater brugerdataene, herunder BMR, i brugerobjektet
        user.setBmr(bmr);

        // Opret brugeren i databasen
        userUsecase.createUser(user);

        return "login"; // Returnerer navnet på HTML-siden for login
    }

    // Mapping for at opdatere brugeroplysninger
    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if(currentUser == null) {
            return "redirect:/login"; // Redirecter til login-siden, hvis ingen bruger er logget ind
        }
        userUsecase.updateUser(user, currentUser); // Opdaterer brugeroplysninger og sender nuværende bruger som parameter

        // Opdater BMR-værdien, hvis brugeren er en "user" og har ændret sine oplysninger
        if ("user".equals(currentUser.getRole())) {
            double bmr = userUsecase.calculateBMR(currentUser.getUserid());
            currentUser.setBmr(bmr);
            // Opdater brugerdataene, herunder BMR, i databasen
            userUsecase.updateUser(currentUser, currentUser);
        }

        return "redirect:/login"; // Redirecter til login-siden
    }

    // Mapping for at slette en bruger
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userUsecase.deleteUser(id); // Sletter brugeren med det angivne id
        return "redirect:/"; // Redirecter til startsiden
    }

    // Mapping for at vise formularen til redigering af brugeroplysninger baseret på id
    @GetMapping("/user/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        userUsecase.findUserByID(id).ifPresent(user -> model.addAttribute("user", user)); // Finder brugeren med det angivne id og tilføjer den til modellen
            return "editUser"; // Returnerer navnet på HTML-siden for redigering af brugeroplysninger
    }

    // Mapping for at vise formularen til redigering af adminoplysninger baseret på id
    @GetMapping("/user/editAdmin/{id}")
    public String showEditAdminForm(@PathVariable Long id, Model model) {
        userUsecase.findUserByID(id).ifPresent(user -> model.addAttribute("user", user)); // Finder brugeren med det angivne id og tilføjer den til modellen
        return "editAdmin"; // Returnerer navnet på HTML-siden for redigering af adminoplysninger
    }

    // Mapping for at håndtere login-processen
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpSession session) {
        User authenticatedUser = userUsecase.findLogin(user.getEmail(), user.getPassword()); // Finder den autentificerede bruger baseret på email og kodeord
        if (authenticatedUser != null) {
            session.setAttribute("currentUser", authenticatedUser); // Tilføjer den autentificerede bruger til sessionen
            if (authenticatedUser.getRole().equals("admin")) {
                return "redirect:/adminmenu"; // Redirecter til adminmenuen, hvis brugeren er en admin
            } else if (authenticatedUser.getRole().equals("user")) {
                return "redirect:/menu"; // Redirecter til brugermenuen, hvis brugeren er en almindelig bruger
            } else {
                model.addAttribute("error", "Ugyldig brugerrolle"); // Tilføjer en fejlmeddelelse, hvis brugerrollen er ugyldig
                return "login"; // Returnerer navnet på HTML-siden for login
            }
        } else {
            model.addAttribute("error", "Ugyldig email eller kodeord"); // Tilføjer en fejlmeddelelse, hvis email eller kodeord er ugyldige
            return "login"; // Returnerer navnet på HTML-siden for login
        }
    }

    // Mapping for at vise login-siden
    @GetMapping("/login")
    public String redirectToLogin() {
        return "login"; // Returnerer navnet på HTML-siden for login
    }

    // Mapping for at vise brugermenuen
    @GetMapping("/menu")
    public String getMenuPage(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser"); // Finder den aktuelle bruger fra sessionen
        if (currentUser != null && currentUser.getRole().equals("user")) {
            model.addAttribute("currentUser", currentUser); // Tilføjer den aktuelle bruger til modellen
            return "menu"; // Returnerer navnet på HTML-siden for brugermenuen
        } else {
            // Til login-siden, hvis ingen bruger er logget ind eller hvis brugeren ikke har brugerrolle
            return "redirect:/login";
        }
    }

    // Mapping for at vise adminmenuen
    @GetMapping("/adminmenu")
    public String getAdminMenuPage(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser"); // Finder den aktuelle bruger fra sessionen
        if (currentUser != null && currentUser.getRole().equals("admin")) {
            model.addAttribute("currentUser", currentUser); // Tilføjer den aktuelle bruger til modellen
            return "adminmenu"; // Returnerer navnet på HTML-siden for adminmenuen
        } else {
            // Til login-siden, hvis ingen bruger er logget ind eller hvis brugeren ikke har adminrolle
            return "redirect:/login";
        }
    }

    // Mapping for at logge ud
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Ugyldiggør sessionen (logger brugeren ud)
        return "login"; // Returnerer navnet på HTML-siden for login
    }
}
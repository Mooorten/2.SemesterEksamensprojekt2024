package com.example2semestereksamensprojekt2024.controller;

import com.example2semestereksamensprojekt2024.model.member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example2semestereksamensprojekt2024.service.usecase;

import java.util.List;

@Controller
public class memberController {
    @Autowired
    private usecase usecase;

    @GetMapping("/")
    public String loginForm(Model model) {
        model.addAttribute("member", new member());
        return "login";
    }

    @GetMapping("/saveMember")
    public String saveMemberForm(Model model) {
        model.addAttribute("member", new member());
        return "saveMember";
    }

    @PostMapping("/saveMember")
    public String saveMember(@ModelAttribute member member) {
        usecase.saveMember(member);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute member member, Model model) {
        boolean authenticated = usecase.findLogin(member.getEmail(), member.getPassword());
        if (authenticated) {
            return "redirect:/menu";
        } else {
            model.addAttribute("error", "Unreachable email or password");
            return "login";
        }
    }

    @GetMapping("/loggedin")
    public String loggedIn(Model model) {
        return "menu";
    }

    @GetMapping("/indexMembers")
    public String showAllMembers(Model model) {
        List<member> members = usecase.findAllMembers();
        model.addAttribute("member", members);
        return "indexMember";
    }

    @GetMapping("/login")
    public String redirectToLogin() {
        return "login";
    }

    @GetMapping("/menu")
    public String getMenuPage(Model model) {
        model.addAttribute("member", new member());
        return "menu";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
package com.example2semestereksamensprojekt2024.controller;

import com.example2semestereksamensprojekt2024.model.Member;
import com.example2semestereksamensprojekt2024.repository.DbSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example2semestereksamensprojekt2024.service.Usecase;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private Usecase usecase;
    @Autowired
    private DbSql dbSql;

    @GetMapping("/")
    public String loginForm() {
        return "homepage";
    }

    @GetMapping("/saveMember")
    public String saveMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "saveMember";
    }

    @GetMapping("/editMember")
    public String editMemberForm() {
        return "editMember";
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

    @PostMapping("/saveMember")
    public String saveMember(@ModelAttribute Member member) {
        usecase.saveMember(member);
        return "login";
    }

    @PostMapping("/")
    public String deleteMember(@RequestParam("memberid") Long id) {
        usecase.delete(id);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Member member, Model model) {
        Member authenticatedMember = usecase.findLogin(member.getEmail(), member.getPassword());
        if (authenticatedMember != null) {
            // Tilføj den autentificerede bruger til modelen
            model.addAttribute("loggedInMember", authenticatedMember);

            // Send brugeren videre til menu-siden
            return "redirect:/menu";
        } else {
            // Hvis brugeren ikke kunne autentificeres, tilføj en fejlmeddelelse til modelen
            model.addAttribute("error", "Unreachable email or password");

            // Returner login-siden igen med fejlmeddelelsen
            return "login";
        }
    }

    @GetMapping("/login")
    public String redirectToLogin() {
        return "login";
    }

    @GetMapping("/menu")
    public String getMenuPage(Model model) {
        model.addAttribute("loggedInMember", new Member());
        return "menu";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
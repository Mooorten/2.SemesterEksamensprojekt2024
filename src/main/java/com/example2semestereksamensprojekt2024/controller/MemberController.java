package com.example2semestereksamensprojekt2024.controller;

import com.example2semestereksamensprojekt2024.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example2semestereksamensprojekt2024.service.usecase;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private usecase usecase;

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
        boolean authenticated = usecase.findLogin(member.getEmail(), member.getPassword());
        if (authenticated) {
            System.out.println(member.getEmail());
            return "redirect:/menu";
        } else {
            model.addAttribute("error", "Unreachable email or password");
            return "login";
        }
    }

    @GetMapping("/loggedin")
    public String loggedIn() {
        boolean authenticated = usecase.findLogin("dj@gmail.com", "123456");
        return "menu";
    }

    @GetMapping("/indexMembers")
    public String showAllMembers(Model model) {
        List<Member> Members = usecase.findAllMembers();
        model.addAttribute("member", Members);
        return "indexMember";
    }

    @GetMapping("/login")
    public String redirectToLogin() {
        return "login";
    }

    @GetMapping("/menu")
    public String getMenuPage(Model model) {
        model.addAttribute("member", new Member());
        return "menu";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
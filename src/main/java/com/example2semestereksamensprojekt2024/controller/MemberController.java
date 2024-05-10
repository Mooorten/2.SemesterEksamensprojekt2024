package com.example2semestereksamensprojekt2024.controller;

import com.example2semestereksamensprojekt2024.model.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example2semestereksamensprojekt2024.service.MemberUsecase;

@Controller
public class MemberController {
    @Autowired
    private MemberUsecase memberUsecase;

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

    @PostMapping("/createMember")
    public String createMember(@ModelAttribute Member member) {
        memberUsecase.createMember(member);
        return "login";
    }

    @PostMapping("/updateMember")
    public String updateMember(@ModelAttribute Member member) {
        memberUsecase.updateMember(member);
        return "redirect:/login";
    }

    @GetMapping("/member/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberUsecase.deleteMember(id);
        return "redirect:/";
    }

    @GetMapping("/member/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        memberUsecase.findUserByID(id).ifPresent(member -> model.addAttribute("member", member));
        return "editMember";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Member member, Model model, HttpSession session) {
        Member authenticatedMember = memberUsecase.findLogin(member.getEmail(), member.getPassword());
        if (authenticatedMember != null) {
            // Tilføj den autentificerede bruger til sessionen
            session.setAttribute("loggedInMember", authenticatedMember);
            return "redirect:/menu";
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
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");
        if (loggedInMember != null) {
            model.addAttribute("loggedInMember", loggedInMember);
            return "menu";
        } else {
            // til login-siden, hvis ingen autentificeret bruger findes
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
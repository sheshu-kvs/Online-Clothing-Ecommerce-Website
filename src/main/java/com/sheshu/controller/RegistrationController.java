package com.sheshu.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;  // Correct import
import org.springframework.validation.BindingResult;

import com.sheshu.model.User;
import com.sheshu.service.UserService1;

import jakarta.validation.Valid;
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService1 userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") @Valid User user, 
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        
        try {
            user.setRoles(Set.of("USER"));
            userService.saveUser(user);
            return "redirect:/login?registered";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed. Please try again.");
            return "register";
        }
    }
}
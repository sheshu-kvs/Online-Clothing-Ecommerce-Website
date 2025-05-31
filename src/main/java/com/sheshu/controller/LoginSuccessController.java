package com.sheshu.controller;

//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.sheshu.model.User;
//import com.sheshu.service.UserService1;
//
//import jakarta.servlet.http.HttpSession;
//@Controller
//public class LoginSuccessController {
//
//    @GetMapping("/default")
//    public String redirectAfterLogin(Authentication authentication) {
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return "redirect:/login";
//        }
//        
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        boolean isAdmin = authorities.stream()
//            .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
//        
//        return isAdmin ? "redirect:/admin/products" : "redirect:/products";
//    }
//}
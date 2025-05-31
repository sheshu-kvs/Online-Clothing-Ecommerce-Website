package com.sheshu.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class RedirectController {
    
    @GetMapping("/redirect")
    public String redirectAfterLogin(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        
        return authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) 
                ? "redirect:/admin/dashboard" 
                : "redirect:/products";
    }
}
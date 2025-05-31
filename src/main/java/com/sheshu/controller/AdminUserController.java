package com.sheshu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sheshu.service.UserService1;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    // Inject your user service
    @Autowired
    private UserService1 userService;

    // Delete user handler
    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";  // redirect back to user list page after delete
    }
    
    @PostMapping("/{id}/role")
    public String updateUserRole(@PathVariable("id") Long id, @RequestParam("role") String role) {
        userService.updateUserRole(id, role);
        return "redirect:/admin/users";  // back to users page after update
    }

    // ... other methods ...
}


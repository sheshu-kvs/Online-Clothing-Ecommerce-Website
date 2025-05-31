package com.sheshu.controller;

import com.sheshu.model.User;
import com.sheshu.model.UserRole;
import com.sheshu.service.UserService1;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService1 userService;

	// List all users
	@GetMapping("/users")
	public String adminUsers(Model model) {
	    model.addAttribute("users", userService.getAllUsers());
	    return "admin-users";
	}

//	// Show add user form
//	@GetMapping("/users/add")
//	public String showAddUserForm(Model model) {
//	    User user = new User();
//	    user.setRole(UserRole.USER); // Default role
//	    model.addAttribute("user", user);
//	    return "admin-user-form";
//	}

//	// Process add user form
//	@PostMapping("/users/add")
//	public String addUser(@ModelAttribute("user") @Valid User user,
//	                    BindingResult result,
//	                    @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile,
//	                    RedirectAttributes redirectAttributes) {
//	    
//	    if (result.hasErrors()) {
//	        return "admin-user-form";
//	    }
//
//	    try {
//	        if (avatarFile != null && !avatarFile.isEmpty()) {
//	            String avatarUrl = saveAvatar(avatarFile);
//	            user.setAvatarUrl(avatarUrl);
//	        }
//	        
//	        userService.saveUser(user);
//	        redirectAttributes.addFlashAttribute("success", "User added successfully!");
//	        return "redirect:/admin/users";
//	    } catch (Exception e) {
//	        redirectAttributes.addFlashAttribute("error", "Failed to add user: " + e.getMessage());
//	        return "admin-user-form";
//	    }
//	}
//
//	// Show edit user form
//	@GetMapping("/users/edit/{id}")
//	public String showEditUserForm(@PathVariable Long id, Model model) {
//	    User user = userService.getUserById(id);
//	    model.addAttribute("user", user);
//	    return "admin-user-form";
//	}

//	// Process edit user form
//	@PostMapping("/users/edit/{id}")
//	public String editUser(@PathVariable("id") Long id,
//	                     @ModelAttribute("user") @Valid User user,
//	                     BindingResult result,
//	                     @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile,
//	                     RedirectAttributes redirectAttributes) {
//	    
//	    if (result.hasErrors()) {
//	        return "admin-user-form";
//	    }
//
//	    try {
//	        if (avatarFile != null && !avatarFile.isEmpty()) {
//	            String avatarUrl = saveAvatar(avatarFile);
//	            user.setAvatarUrl(avatarUrl);
//	        }
//	        
//	        user.setUserid(id);
//	        userService.saveUser(user);
//	        redirectAttributes.addFlashAttribute("success", "User updated successfully!");
//	        return "redirect:/admin/users";
//	    } catch (Exception e) {
//	        redirectAttributes.addFlashAttribute("error", "Failed to update user: " + e.getMessage());
//	        return "admin-user-form";
//	    }
//	}
//
//	// Delete user
//	@GetMapping("/users/delete/{id}")
//	public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//	    try {
//	        userService.deleteUser(id);
//	        redirectAttributes.addFlashAttribute("success", "User deleted successfully!");
//	    } catch (Exception e) {
//	        redirectAttributes.addFlashAttribute("error", "Failed to delete user: " + e.getMessage());
//	    }
//	    return "redirect:/admin/users";
//	}
//
//	private String saveAvatar(MultipartFile avatarFile) throws IOException {
//	    String staticDir = new ClassPathResource("static/").getFile().getAbsolutePath();
//	    Path uploadPath = Paths.get(staticDir, "avatars");
//	    
//	    if (!Files.exists(uploadPath)) {
//	        Files.createDirectories(uploadPath);
//	    }
//	    
//	    String fileName = System.currentTimeMillis() + "_" + 
//	                     avatarFile.getOriginalFilename();
//	    Path filePath = uploadPath.resolve(fileName);
//	    
//	    Files.copy(avatarFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//	    return fileName;
//	}
}
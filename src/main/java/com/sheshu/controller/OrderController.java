package com.sheshu.controller;

import com.sheshu.dto.CheckoutForm;

import com.sheshu.model.CartItem;
import com.sheshu.model.OrderStatus;
import com.sheshu.model.Orders;
import com.sheshu.model.User;
import com.sheshu.service.CartService;
import com.sheshu.service.OrderService;
import com.sheshu.service.UserService1;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    

    @Autowired
    private CartService cartService; 
    
    // To manage the user's cart (if you have one)

    @Autowired
    private UserService1 userService;
    
    
    
    
   


  

    // ✅ Get all orders (admin access)
    @GetMapping("/all")
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    // ✅ Get orders by user ID (user-specific view)
    @GetMapping("/user/{userId}")
    public List<Orders> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }
    
    
   
    
 // For HTML view
    @GetMapping("/{orderId}")
    public String viewOrder(@PathVariable Long orderId, Model model) {
        Orders order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "order-confirmation";
    }

    
    
   

    // API Endpoints (return JSON)
 
    @ResponseBody
    @GetMapping("/api/{id}")  // Changed path
    public Orders getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // HTML Endpoints (return Thymeleaf templates)
    @GetMapping("/order-confirmation/{id}")
    public String showOrderConfirmation(@PathVariable Long id, Model model) {
        Orders order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "order-confirmation"; 
    }


    // List all orders
    @GetMapping("/orders")
    public String adminOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin-orders";
    }

 

    // Update order status
    @PostMapping("/orders/{id}/update")
    public String updateOrderStatus(@PathVariable Long id,
                                  @RequestParam("status") OrderStatus status,
                                  RedirectAttributes redirectAttributes) {
        try {
            orderService.updateOrderStatus(id, status);
            redirectAttributes.addFlashAttribute("success", "Order status updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update order status: " + e.getMessage());
        }
        return "redirect:/admin/orders/" + id;
    }
}
   
    

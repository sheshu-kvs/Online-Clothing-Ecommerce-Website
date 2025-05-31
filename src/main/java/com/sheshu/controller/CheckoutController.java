package com.sheshu.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sheshu.model.CartItem;
import com.sheshu.model.Orders;
import com.sheshu.model.User;
import com.sheshu.service.CartService;
import com.sheshu.service.OrderService;
import com.sheshu.service.UserService1;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService1 userService;

    // Show Checkout Page
    @GetMapping
    public String showCheckoutPage(Model model, HttpSession session, Principal principal) {
        List<CartItem> cartItems = cartService.getCart(session);

        if (cartItems == null || cartItems.isEmpty()) {
            return "redirect:/cart?error=Your cart is empty";
        }

        BigDecimal total = cartService.getTotal(session);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", total);

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            if (user != null) {
                model.addAttribute("user", user);
            } else {
                // User not found, redirect to login
                return "redirect:/login?redirect=/checkout";
            }
        } else {
            return "redirect:/login?redirect=/checkout";
        }

        return "products/checkout"; // Make sure this Thymeleaf template exists
    }

    // Place Order
    @PostMapping("/place-order")
    public String placeOrder(
            @RequestParam String fullName,
            @RequestParam String address,
            @RequestParam String city,
            HttpSession session,
            Principal principal,
            Model model) {

        if (principal == null) {
            return "redirect:/login?redirect=/checkout";
        }

        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return "redirect:/login?redirect=/checkout";
        }

        List<CartItem> cartItems = cartService.getCart(session);
        if (cartItems == null || cartItems.isEmpty()) {
            model.addAttribute("error", "Your cart is empty");
            return "redirect:/cart";
        }

        try {
            // Optionally, you can set user shipping info here if needed
            // e.g. user.setFullName(fullName); user.setAddress(address); etc.
            // userService.save(user);

            Orders order = orderService.placeOrder(user, cartItems);

            // Clear cart after successful order
            cartService.clearCart(session);

            // Pass order details to confirmation page
            model.addAttribute("order", order);

            return "order-confirmation"; // Make sure order-confirmation.html exists

        } catch (Exception e) {
            model.addAttribute("error", "Order failed: " + e.getMessage());

            // Also add cart details again to repopulate the checkout page
            BigDecimal total = cartService.getTotal(session);
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("cartTotal", total);
            model.addAttribute("user", user);

            return "products/checkout";
        }
    }
}
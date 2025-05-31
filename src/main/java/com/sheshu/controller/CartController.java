package com.sheshu.controller;


import java.util.List;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sheshu.model.CartItem;
import com.sheshu.model.Orders;
import com.sheshu.model.Product;
import com.sheshu.model.User;
import com.sheshu.service.CartService;
import com.sheshu.service.OrderService;
import com.sheshu.service.ProductService;
import com.sheshu.service.UserService1;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/cart")
public class CartController {
	
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;
    
    
    @Autowired
    private UserService1 userService;

    // View cart contents
    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        model.addAttribute("cartItems", cartService.getCart(session));
        model.addAttribute("cartTotal", cartService.getTotal(session));
        return "cart/view";
    }

//    // Add item to cart
//    @PostMapping("/add")
//    public String addToCart(
//            @RequestParam Long productId,
//            @RequestParam(defaultValue = "1") int quantity,
//            HttpSession session) {
//
//        Product product = productService.getProductById(productId);
////            .orElseThrow(() -> new RuntimeException("Product not found"));
//        
//        cartService.addItem(session, product, quantity);
//        return "redirect:/cart";
//    }
    
    @PostMapping("/add")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantity,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        try {
            Product product = productService.getProductById(productId);
            cartService.addItem(session, product, quantity);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Product not found");
        }
        return "redirect:/cart";
    }

    // Update item quantity
    @PostMapping("/update")
    public String updateCartItem(
            @RequestParam Long productId,
            @RequestParam int quantity,
            HttpSession session) {

        cartService.updateQuantity(session, productId, quantity);
        return "redirect:/cart";
    }

    // Remove item from cart
    @PostMapping("/remove")
    public String removeFromCart(
            @RequestParam Long productId,
            HttpSession session) {

        cartService.removeItem(session, productId);
        return "redirect:/cart";
    }
    
//    
//    @PostMapping("/checkout")
//    public String checkout(HttpSession session, 
//                         Authentication auth,
//                         RedirectAttributes redirect) {
//        
//        try {
//            // 1. Get current user
//            String username = auth.name(); // Fixed: should be getName()
//            User user = userService.findByUsername(username);
//            
//            // 2. Get cart items
//            List<CartItem> cartItems = cartService.getCart(session);
//            if (cartItems.isEmpty()) {
//                redirect.addFlashAttribute("error", "Cart is empty");
//                return "redirect:/cart";
//            }
//            
//            // 3. Place order
//            Orders order = orderService.placeOrder(user, cartItems);
//            
//            // 4. Clear cart
//            cartService.clearCart(session);
//            
//            return "redirect:/orders/" + order.getOrderid();
//            
//        } catch (Exception e) {
//            redirect.addFlashAttribute("error", "Checkout failed: " + e.getMessage());
//            return "redirect:/cart";
//        }
//    }
    
 
}

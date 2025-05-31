package com.sheshu.service;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sheshu.model.CartItem;
import com.sheshu.model.Product;

import jakarta.servlet.http.HttpSession;

@Service
public class CartService {

    // Key for storing cart in session
    private static final String CART_SESSION_KEY = "SHOPPING_CART";

    /**
     * Get the current cart from session (or create if empty)
     */
    public List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    /**
     * Add a product to cart (or increase quantity if already exists)
     */
    public void addItem(HttpSession session, Product product, int quantity) {
        List<CartItem> cart = getCart(session);
        
        // Check if product already in cart
        for (CartItem item : cart) {
            if (item.getProductId().equals(product.getProductid())) {
                item.setQuantity(item.getQuantity() + quantity);
                return; // Exit after updating
            }
        }
        
        // If not found, add new item
        cart.add(new CartItem(product, quantity));
    }

    /**
     * Remove an item from cart
     */
    public void removeItem(HttpSession session, Long productId) {
        List<CartItem> cart = getCart(session);
        cart.removeIf(item -> item.getProductId().equals(productId));
    }

    /**
     * Update item quantity
     */
    public void updateQuantity(HttpSession session, Long productId, int newQuantity) {
        List<CartItem> cart = getCart(session);
        cart.stream()
            .filter(item -> item.getProductId().equals(productId))
            .findFirst()
            .ifPresent(item -> item.setQuantity(newQuantity));
    }

    /**
     * Calculate cart total
     */
    public BigDecimal getTotal(HttpSession session) {
        return getCart(session).stream()
            .map(CartItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Clear the cart (after checkout)
     */
   
    
    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }
}

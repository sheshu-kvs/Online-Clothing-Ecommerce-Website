package com.sheshu.dto;

import com.sheshu.model.CartItem;
import java.util.ArrayList;
import java.util.List;

public class CheckoutForm {

    private Long userId;

    // Initialize the list to avoid NullValueInNestedPathException
    private List<CartItem> cartItems = new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}

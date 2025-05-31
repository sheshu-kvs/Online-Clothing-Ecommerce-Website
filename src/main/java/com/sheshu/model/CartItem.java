package com.sheshu.model;

import java.math.BigDecimal;

public class CartItem {
    private Long productId;
    private Product product;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String imageUrl; // For displaying product image

    // Constructor
    public CartItem(Product product, int quantity) {
        this.productId = product.getProductid();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = quantity;
        this.imageUrl = product.getImageurl();
    }

    
    public CartItem() {}
    // Getters (No setters to prevent accidental modification)
//    public Long getProductId() { return productId; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getImageUrl() { return imageUrl; }

    // Special setter only for quantity (since it can change)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Calculate item subtotal
    public BigDecimal getSubtotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

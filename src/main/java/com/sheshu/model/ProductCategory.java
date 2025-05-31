package com.sheshu.model;

public enum ProductCategory {
    MEN("MEN"),
    WOMEN("WOMEN"),
    KIDS("KIDS"),
    ELECTRONICS("ELECTRONICS"),
    CLOTHING("CLOTHING"),
    ACCESSORIES("Accessories");
	
    
    private final String displayName;
    
    ProductCategory(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    // Optional: Add this method for Thymeleaf compatibility
    public static ProductCategory[] getAllCategories() {
        return ProductCategory.values();
    }
}

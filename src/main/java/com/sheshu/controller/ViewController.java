package com.sheshu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sheshu.model.Product;
import com.sheshu.service.ProductService;

@Controller
@RequestMapping("/view")
public class ViewController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }
    
    @GetMapping("/admin/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }
    
    @PostMapping("/admin/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/view/products";
    }
}

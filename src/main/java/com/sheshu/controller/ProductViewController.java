package com.sheshu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sheshu.model.Product;
import com.sheshu.service.ProductService;
@Controller
@RequestMapping("/products")
public class ProductViewController {
	@Autowired
	private ProductService productService;



	    // Change return values to match actual templates
	    @GetMapping
	    public String showProducts(Model model) {
	        model.addAttribute("products", productService.getAllProducts());
	        return "products/list"; // Changed
	    }

	    @GetMapping("/list")
	    public String showProductList(Model model) {
	        model.addAttribute("products", productService.getAllProducts());
	        return "products/list"; // Changed
	    }

	    @GetMapping("/{id}")
	    public String viewProductDetails(@PathVariable Long id, Model model) {
	        Product product = productService.getProductById(id);
	        model.addAttribute("product", product);
	        return "products/detail"; // Changed
	    }
	}
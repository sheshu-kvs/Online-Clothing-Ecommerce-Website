//package com.sheshu.controller;
//
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sheshu.model.Product;
//import com.sheshu.repository.ProductRepository;
//import com.sheshu.service.ProductService;
//
//@Controller
//@RequestMapping("/products")
//public class ProductController {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//
//    // Display single product details
//    @GetMapping("/detail")
//    public String getProductDetail(@RequestParam Long id, Model model) {
//        Product product = productRepository.findById(id).orElseThrow();
//        model.addAttribute("product", product);
//        return "product-detail"; // templates/product-detail.html
//    }
//    
////    @GetMapping
////    public String getAllProducts(Model model) {
////    	    try {
////    	        List<Product> products = productRepository.findAll();
////    	        if (products.isEmpty()) {
////    	            System.err.println("⚠️ No products found in database!");
////    	        } else {
////    	            System.out.println("✅ Fetched products: " + products.size());
////    	            products.forEach(p -> System.out.println(p.getName() + " - " + p.getImageurl()));
////    	        }
////    	        model.addAttribute("products", products);
////    	    } catch (Exception e) {
////    	        System.err.println("❌ Database error: " + e.getMessage());
////    	        model.addAttribute("error", "Failed to load products");
////    	    }
////    	    return "products";
////    	}
//    
//    @GetMapping
//    public String getAllProducts(Model model) {
//        List<Product> products = productRepository.findAll();
//        
//        // Add detailed debug logs
//        System.out.println("==== DEBUG START ====");
//        System.out.println("Product Repository: " + productRepository.getClass().getName());
//        System.out.println("Found " + products.size() + " products");
//        products.forEach(p -> System.out.println(
//            "ID: " + p.getProductid() + 
//            ", Name: " + p.getName() + 
//            ", Image: " + p.getImageurl()
//        ));
//        System.out.println("==== DEBUG END ====");
//        
//        model.addAttribute("products", products);
//        return "products";
//    }
//    }
//

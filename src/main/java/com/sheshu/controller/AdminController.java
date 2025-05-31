package com.sheshu.controller;




import org.springframework.ui.Model;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sheshu.dto.DashboardStats;
import com.sheshu.model.OrderStatus;
import com.sheshu.model.Orders;
import com.sheshu.model.Product;
import com.sheshu.model.ProductCategory;
import com.sheshu.repository.OrderRepository;
import com.sheshu.service.OrderService;
import com.sheshu.service.ProductService;
import com.sheshu.service.UserService1;

import jakarta.validation.Valid;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private UserService1 userService;
    
    @Autowired
    private OrderService orderService;

    // Image upload directory
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

 // Admin Dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        DashboardStats stats = new DashboardStats();
        stats.setTotalProducts(productService.countAllProducts());
        stats.setTotalOrders(orderService.countAllOrders());
        stats.setRevenue(orderService.getMonthlyRevenue());
        stats.setTotalCustomers(userService.countAllUsers());
        
        model.addAttribute("stats", stats);
        return "admin/dashboard"; // Return correct template path
    }

    // Product Management
    @GetMapping("/products")
    public String adminProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/products";
    }

    // Show Add Product Form
    @GetMapping("/products/add")
    public String showAddForm(Model model) {
        Product product = new Product();
        product.setCategory(ProductCategory.MEN); 
        model.addAttribute("product", product);
        return "admin/product-form";
    }

    
//    viewOrders and update Status
    
    
   
    
    // Process Add Product Form
    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product,
                           BindingResult result,
                           @RequestParam("imageFile") MultipartFile imageFile,
                           RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "admin/product-form";
        }

        try {
            if (!imageFile.isEmpty()) {
                String imageUrl = saveImage(imageFile);
                product.setImageurl(imageUrl);
            }
            
            productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("success", "Product added successfully!");
            return "redirect:/admin/products";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add product: " + e.getMessage());
            return "admin/product-form";
        }
    }

    // Show Edit Product Form
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product-form";
    }

    // Process Edit Product Form
    @PostMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") Long id,
                            @ModelAttribute("product") @Valid Product product,
                            BindingResult result,
                            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                            RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "admin/product-form";
        }

        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = saveImage(imageFile);
                product.setImageurl(imageUrl);
            }
            
            product.setProductid(id);
            productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("success", "Product updated successfully!");
            return "redirect:/admin/products";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update product: " + e.getMessage());
            return "admin/product-form";
        }
    }

    // Delete Product
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("success", "Product deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete product: " + e.getMessage());
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/orders")
    public String adminOrders(Model model) {
        List<Orders> orders = orderService.getAllOrders();
        System.out.println("=== DEBUG ===");
        System.out.println("Orders count: " + orders.size());
        System.out.println("First order status: " + (orders.isEmpty() ? "N/A" : orders.get(0).getStatus()));
        model.addAttribute("orders", orders);
        return "admin/orders"; 
    }

    @GetMapping("/admin/orders")
    public String viewOrders(Model model) {
        List<Orders> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }

    @PostMapping("/orders/update")
    public String updateOrderStatus(@RequestParam("orderId") Long orderId,
            @RequestParam("status") String status) {
          try {
             OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
             orderService.updateOrderStatus(orderId, newStatus);
           } catch (IllegalArgumentException e) {
          System.out.println("Invalid order status: " + status);
           }

           return "redirect:/admin/orders";
           }
    
    
    // User Management
    @GetMapping("/users")
    public String adminUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    // Helper method for image upload
    private String saveImage(MultipartFile imageFile) throws IOException {
        String staticDir = new ClassPathResource("static/").getFile().getAbsolutePath();
        Path uploadPath = Paths.get(staticDir, "images");
        
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        String fileName = System.currentTimeMillis() + "_" + 
                         imageFile.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
}
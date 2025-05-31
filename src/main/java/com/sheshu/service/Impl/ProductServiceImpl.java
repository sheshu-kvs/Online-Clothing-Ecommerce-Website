package com.sheshu.service.Impl;
import com.sheshu.model.Product;

import com.sheshu.repository.ProductRepository;
import com.sheshu.service.ProductService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Value("${upload.directory}") // Add this to application.properties
    private String uploadDirectory;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public void saveProduct(Product product, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String imageName = storeImage(file);
            product.setImageurl(imageName);
        }
        productRepository.save(product);
    }

    private String storeImage(MultipartFile file) throws IOException {
        // Create upload directory if it doesn't exist
        File uploadPath = new File(uploadDirectory);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        // Generate unique filename
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(uploadPath + "/" + fileName);
        
        // Save file
        file.transferTo(dest);
        
        return fileName; // Return only the filename (not full path)
    }

	public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

	@Override
	@Transactional
	public void updateProduct(Long id, Product product, MultipartFile file) throws IOException {
		Product existingProduct = productRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found"));
	        
	        // Update fields
	        existingProduct.setName(product.getName());
	        existingProduct.setDescription(product.getDescription());
	        existingProduct.setPrice(product.getPrice());
	        existingProduct.setStock_quantity(product.getStock_quantity());
	        existingProduct.setCategory(product.getCategory());
	        
	        
	        
	     // Handle new image if provided
	        if (file != null && !file.isEmpty()) {
	            String newImageName = storeImage(file);
	            existingProduct.setImageurl(newImageName);
	        }
	        
	        productRepository.save(existingProduct);
	    }
	
	
	
    public String saveProductImage(MultipartFile imageFile) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDirectory);
        
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        try (InputStream inputStream = imageFile.getInputStream()) {
            Files.copy(inputStream, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        }
        
        return fileName;
    }

	@Override
	public long countAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.count();
	}
	    
	}
   

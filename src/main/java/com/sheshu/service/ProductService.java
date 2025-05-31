package com.sheshu.service;

import com.sheshu.model.Product;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    Product addProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void deleteProduct(Long id);
    Product saveProduct(Product product);
	void saveProduct(Product product, MultipartFile file) throws IOException;
	void updateProduct(Long id, Product product, MultipartFile file) throws IOException;
	public String saveProductImage(MultipartFile file) throws IOException ;
	long countAllProducts();
}

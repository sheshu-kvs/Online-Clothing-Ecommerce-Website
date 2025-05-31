package com.sheshu.service.Impl;


import java.math.BigDecimal;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheshu.model.CartItem;
import com.sheshu.model.OrderItem;
import com.sheshu.model.OrderStatus;
import com.sheshu.model.Orders;
import com.sheshu.model.Product;
import com.sheshu.model.User;
import com.sheshu.repository.OrderRepository;
import com.sheshu.repository.ProductRepository;
import com.sheshu.repository.UserRepository;
import com.sheshu.service.OrderService;
import com.sheshu.service.ProductService;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Orders placeOrder(User user, List<CartItem> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart cannot be empty");
        }

        Orders order = new Orders();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            Product product = productRepository.findById(cartItem.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + cartItem.getProductId()));

            if (product.getStock_quantity() < cartItem.getQuantity()) {
                throw new IllegalArgumentException(
                    "Insufficient stock for product: " + product.getName() + 
                    ". Available: " + product.getStock_quantity()
                );
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice());
            
            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            orderItem.setSubtotal(subtotal);
            
            total = total.add(subtotal);
            orderItems.add(orderItem);

            product.setStock_quantity(product.getStock_quantity() - cartItem.getQuantity());
            productRepository.save(product);
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(total);

        return orderRepository.save(order);
    }

    @Override
    public Orders getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Orders> getOrdersByUserId(Long userId) {
        return orderRepository.findByUser_Userid(userId);
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {
        Orders order = getOrderById(id);
        order.setStatus(status);             

        orderRepository.save(order);
    }

    @Override
    public long countAllOrders() {
        return orderRepository.count();
    }
    public BigDecimal getMonthlyRevenue() {
        LocalDateTime startDate = LocalDate.now()
            .withDayOfMonth(1)
            .atStartOfDay();
        
        LocalDateTime endDate = LocalDate.now()
            .with(TemporalAdjusters.lastDayOfMonth())
            .atTime(23, 59, 59, 999999999);

        // Handle null case explicitly
        BigDecimal revenue = orderRepository.getRevenueBetweenDates(startDate, endDate);
        return revenue != null ? revenue : BigDecimal.ZERO;
    }
}
package com.sheshu.service;

import com.sheshu.model.Orders;
import com.sheshu.model.User;
import com.sheshu.model.CartItem;
import com.sheshu.model.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
	    Orders getOrderById(Long id);
	    List<Orders> getAllOrders();
	    List<Orders> getOrdersByUserId(Long userId);
	    // Add this new method

	    Orders placeOrder(User user, List<CartItem> cartItems);
	    void updateOrderStatus(Long id, OrderStatus status);
	    long countAllOrders();
	    BigDecimal getMonthlyRevenue();
//		Orders placeOrder(Long userId, List<CartItem> cartItems);
}
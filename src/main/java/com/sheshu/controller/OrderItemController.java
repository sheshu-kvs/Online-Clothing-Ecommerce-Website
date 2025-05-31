package com.sheshu.controller;

import com.sheshu.model.OrderItem;
import com.sheshu.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // ✅ Get all order items
//    @GetMapping("/all")
//    public List<OrderItem> getAllOrderItems() {
//        return orderItemService.getAllOrderItems();
//    }

    // ✅ Get order items by order ID
    @GetMapping("/order/{orderId}")
    public List<OrderItem> getItemsByOrderId(@PathVariable Long orderId) {
        return orderItemService.getOrderItemsByOrderId(orderId);
    }
}

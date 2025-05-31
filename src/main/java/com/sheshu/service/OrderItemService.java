package com.sheshu.service;

import com.sheshu.model.OrderItem;
import java.util.List;

public interface OrderItemService {
    OrderItem addOrderItem(OrderItem orderItem);
    List<OrderItem> getOrderItemsByOrderId(Long orderId);
}

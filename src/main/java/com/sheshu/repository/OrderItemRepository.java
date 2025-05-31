package com.sheshu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sheshu.model.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder_Orderid(Long orderid);
}

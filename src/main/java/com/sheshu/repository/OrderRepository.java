package com.sheshu.repository;

import com.sheshu.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    
	    
	    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Orders o WHERE o.orderDate BETWEEN :start AND :end")
	    BigDecimal getRevenueBetweenDates(
	        @Param("start") LocalDateTime start,
	        @Param("end") LocalDateTime end
	    );
	    
	    List<Orders> findByUser_Userid(Long userId);
	}

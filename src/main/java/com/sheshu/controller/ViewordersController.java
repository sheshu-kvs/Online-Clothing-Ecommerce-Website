//package com.sheshu.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.sheshu.model.OrderStatus;
//import com.sheshu.service.OrderService;
//
//public class ViewordersController {
//	@Autowired
//	private OrderService orderService;
//	 
//	@GetMapping("/admin/orders")
//	public String viewAllOrders(Model model) {
//	    model.addAttribute("orders", orderService.getAllOrders());
//	    return "admin-orders";
//	}
//
//	@PostMapping("/admin/orders/update")
//	public String updateOrderStatus(@RequestParam Long orderId, @RequestParam OrderStatus status) {
//	    orderService.updateOrderStatus(orderId, status);
//	    return "redirect:/admin/orders";
//	}
//    
//
//}

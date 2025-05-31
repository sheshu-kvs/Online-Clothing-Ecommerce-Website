package com.sheshu.dto;

import java.math.BigDecimal;

public class DashboardStats {
	    private long totalProducts;
	    private long totalOrders;
	    private BigDecimal revenue;
	    private long totalCustomers;
	    
	    
	    public DashboardStats() {
	    	
	    }
		public DashboardStats(long totalProducts, long totalOrders, BigDecimal revenue, long totalCustomers) {
			super();
			this.totalProducts = totalProducts;
			this.totalOrders = totalOrders;
			this.revenue = revenue;
			this.totalCustomers = totalCustomers;
		}
		public long getTotalProducts() {
			return totalProducts;
		}
		public void setTotalProducts(long totalProducts) {
			this.totalProducts = totalProducts;
		}
		public long getTotalOrders() {
			return totalOrders;
		}
		public void setTotalOrders(long totalOrders) {
			this.totalOrders = totalOrders;
		}
		public BigDecimal getRevenue() {
			return revenue;
		}
		public void setRevenue(BigDecimal revenue) {
			this.revenue = revenue;
		}
		public long getTotalCustomers() {
			return totalCustomers;
		}
		public void setTotalCustomers(long totalCustomers) {
			this.totalCustomers = totalCustomers;
		}
	    

}

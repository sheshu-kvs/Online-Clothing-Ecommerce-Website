package com.sheshu.model;

import java.math.BigDecimal;

import jakarta.persistence.*;




@Entity
@Table(name = "orderitem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderitemid;
    
    @ManyToOne
    @JoinColumn(name = "orderid")
    private Orders order;
    
    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;
    
    @Column(nullable = false)
    private int quantity;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(name = "subtotal", precision = 10, scale = 2)
    private BigDecimal subtotal;

	public Long getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(Long orderitemid) {
		this.orderitemid = orderitemid;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
    
    // Getters and setters...
    
    
    
    
    
    
    
}



























//@Entity
//@Table(name = "orderitem")
//public class OrderItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long orderitemid;
//
//    @ManyToOne
//    @JoinColumn(name = "orderid")
//    private Orders order;
//
//    @ManyToOne
//    @JoinColumn(name = "productid")
//    private Product product;
//
//    @Column(nullable = false)
//    private int quantity;
//
//    @Column(nullable = false, precision = 10, scale = 2)
//    private BigDecimal price;
//
//    @Column(name = "subtotal", precision = 10, scale = 2)
//    private BigDecimal subtotal;
//
//    
//    public OrderItem() {
//    	
//    }
//    // ✅ Always call this after setting price and quantity
//    public void calculateSubtotal() {
//        if (price != null && quantity > 0) {
//            this.subtotal = price.multiply(BigDecimal.valueOf(quantity));
//        } else {
//            this.subtotal = BigDecimal.ZERO;
//        }
//    }
//
//    // Getters and Setters
//    public Long getOrderitemid() {
//        return orderitemid;
//    }
//
//    public void setOrderitemid(Long orderitemid) {
//        this.orderitemid = orderitemid;
//    }
//
//    public Orders getOrder() {
//        return order;
//    }
//
//    public void setOrder(Orders order) {
//        this.order = order;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public BigDecimal getSubtotal() {
//        return subtotal;
//    }
//
//    public void setSubtotal(BigDecimal subtotal) {
//        this.subtotal = subtotal;
//    }
//}
    
    

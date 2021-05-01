package com.hms.gateway.model;

import java.time.LocalDateTime;
import java.util.List;





public class ProductModel {

	private long productId;
	
	private String productName;
	
	private double quantity;
	
	private double pricePerUnit;
	
	private double totalAmount;
	
	private LocalDateTime modifiedAt;
	
	private List<OrderModel> order;
	
	//Noargs Constructor
	public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	//AllArgs Constructor
	public ProductModel(long productId, String productName, double quantity, double pricePerUnit, double totalAmount,
			LocalDateTime modifiedAt, List<OrderModel> order) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
		this.totalAmount = totalAmount;
		this.modifiedAt = modifiedAt;
		this.order = order;
	}
	
	//Getters and Setters
	public ProductModel(List<OrderModel> order) {
		super();
		this.order = order;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public List<OrderModel> getOrder() {
		return order;
	}

	public void setOrder(List<OrderModel> order) {
		this.order = order;
	}

	
	
	
}

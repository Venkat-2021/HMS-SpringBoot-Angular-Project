package com.hms.inventory.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.MongoId;


public class OrderModel {

	@Transient //  by default all private fields are mapped to the document, this annotation excludes this field where it is applied from being stored in the database
	public static final String SEQUENCE_NAME = "issueitems_sequence";
	
	@MongoId
	private long orderId;
	
	private double quantityRemoved;
	
	private double quantityAdded;
	
	private LocalDateTime dateOfIssue;

	private String supplier;
	
	private long employeeId;
	
	private String employeeName;
	
	private String roomNo;
	
	//NoArgsConstructor
	public OrderModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	//AllArgsConstructor
	public OrderModel(long orderId, double quantityRemoved, double quantityAdded, LocalDateTime dateOfIssue,
			String supplier, long employeeId, String employeeName, String roomNo) {
		super();
		this.orderId = orderId;
		this.quantityRemoved = quantityRemoved;
		this.quantityAdded = quantityAdded;
		this.dateOfIssue = dateOfIssue;
		this.supplier = supplier;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.roomNo = roomNo;
	}

	//Getters and Setters
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getQuantityRemoved() {
		return quantityRemoved;
	}

	public void setQuantityRemoved(double quantityRemoved) {
		this.quantityRemoved = quantityRemoved;
	}

	public double getQuantityAdded() {
		return quantityAdded;
	}

	public void setQuantityAdded(double quantityAdded) {
		this.quantityAdded = quantityAdded;
	}

	public LocalDateTime getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDateTime dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}



}

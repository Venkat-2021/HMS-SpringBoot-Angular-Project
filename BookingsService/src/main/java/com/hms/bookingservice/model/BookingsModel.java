package com.hms.bookingservice.model;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;



@Document(collection="bookings")
public class BookingsModel {
	
	@Transient//  by default all private fields are mapped to the document, this annotation excludes this field where it is applied from being stored in the database
	public static final String SEQUENCE_NAME = "bookings_sequence";
	
	@MongoId
	private long id;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int roomNo;
	private String roomName;
	private double price;
	private int numberOfNights;
	private List<GuestModel> guestDetails;
	private double totalAmount;
	private String paymentMode;
	private String transactionId;
	private String status;
	private long number;
	
	//NoArgs Constructor
	public BookingsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	//AllArgs Constructor
	public BookingsModel(long id, LocalDate checkInDate, LocalDate checkOutDate, int roomNo, String roomName,
			double price, int numberOfNights, List<GuestModel> guestDetails, double totalAmount, String paymentMode,
			String transactionId, String status,long number) {
		super();
		this.id = id;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomNo = roomNo;
		this.roomName = roomName;
		this.price = price;
		this.numberOfNights = numberOfNights;
		this.guestDetails = guestDetails;
		this.totalAmount = totalAmount;
		this.paymentMode = paymentMode;
		this.transactionId = transactionId;
		this.status = status;
		this.number=number;
	}
	


	//Guest Details Constructor
	public BookingsModel(List<GuestModel> guestDetails) {
		super();
		this.guestDetails = guestDetails;
	}

	//Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberOfNights() {
		return numberOfNights;
	}

	public void setNumberOfNights(int numberOfNights) {
		this.numberOfNights = numberOfNights;
	}

	public List<GuestModel> getGuestDetails() {
		return guestDetails;
	}

	public void setGuestDetails(List<GuestModel> guestDetails) {
		this.guestDetails = guestDetails;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
	
	
	
	

}

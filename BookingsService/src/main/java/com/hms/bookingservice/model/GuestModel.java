package com.hms.bookingservice.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.MongoId;



public class GuestModel {
	
	@Transient//  by default all private fields are mapped to the document, this annotation excludes this field where it is applied from being stored in the database
	public static final String SEQUENCE_NAME = "guest_sequence";
	
	@MongoId
	private long id;
	private String guestName;
	private String gender;
	private String designation;
	private long mobile;
	private String email;
	private String address;
	private String identityType;
	private String identityNumber;
	
	//NoArgs Constructor
	public GuestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	//AllArgs Constructor
	public GuestModel(long id, String guestName, String gender, String designation, long mobile, String email,
			String address, String identityType, String identityNumber) {
		super();
		this.id = id;
		this.guestName = guestName;
		this.gender = gender;
		this.designation = designation;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.identityType = identityType;
		this.identityNumber = identityNumber;
	}

	//Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	
	
	
	

}

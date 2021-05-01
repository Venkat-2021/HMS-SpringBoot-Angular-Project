package com.hms.bookingservice.exception;

public class BookingCollectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BookingCollectionException(String message) {
		super(message);
	}
	
	public static String IdNotFoundException(long id) {
		return "Booking with "+id+" not Found";
	}
	
	public static String IdNotFoundException(String status) {
		return "Booking with "+status+" not Found";
	}
	
	public static String BookingAlreadyExists() {
		return "Booking Already Exist";
	}

}

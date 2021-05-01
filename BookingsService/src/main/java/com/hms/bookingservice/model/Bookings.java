package com.hms.bookingservice.model;

import java.time.LocalDate;

import org.springframework.context.annotation.Configuration;


@Configuration
public class Bookings {

	private long bookingId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	
	public Bookings() {
		super();
	}

	public Bookings(long bookingId, LocalDate checkInDate, LocalDate checkOutDate) {
		super();
		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
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


	
}

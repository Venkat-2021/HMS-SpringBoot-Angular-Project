package com.hms.bookingservice.service;

import java.util.List;
import java.util.Optional;
import com.hms.bookingservice.model.BookingsModel;
import com.hms.bookingservice.model.GuestModel;


public interface BookingsService {
	
	public BookingsModel createBooking(BookingsModel booking);
	
	public List<BookingsModel> getAllBookings();
	
	public Optional<BookingsModel> getBookingById(long id);
	
	public BookingsModel updateBookingStatus(long id, String status);
	
	public List<GuestModel> getGuestById(long id);
	
	public long deleteBookingById(long id);
	
	public Optional<BookingsModel> getBookingsByStatus(String Status);
	
}

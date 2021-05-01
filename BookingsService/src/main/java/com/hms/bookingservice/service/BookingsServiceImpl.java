package com.hms.bookingservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.hms.bookingservice.config.SMSConfig;
import com.hms.bookingservice.model.BookingsModel;
import com.hms.bookingservice.model.GuestModel;
import com.hms.bookingservice.repository.BookingsRepository;

@Service
public class BookingsServiceImpl implements BookingsService {
	
	@Autowired
	private BookingsRepository repository;

	
	@Autowired
	private SequenceGeneratorService sequenceService;

	@Override
	public BookingsModel createBooking(BookingsModel booking) {
		booking.setId(sequenceService.generateSequence(BookingsModel.SEQUENCE_NAME));
		return repository.save(booking);
	}

	@Override
	public List<BookingsModel> getAllBookings() {
		return repository.findAll();
	}

	@Override
	public Optional<BookingsModel> getBookingById(long id) {
		return repository.findById(id);
	}

	@Override
	public BookingsModel updateBookingStatus(long id, String status) {
		BookingsModel booking = repository.findById(id).get();
		
		booking.setStatus(status);
		return repository.save(booking);
	}

	@Override
	public List<GuestModel> getGuestById(long id) {
		BookingsModel booking = repository.findById(id).get();
		return booking.getGuestDetails();
	}

	@Override
	public long deleteBookingById(long id) {
		repository.deleteById(id);
		return id;
	}

	@Override
	public Optional<BookingsModel> getBookingsByStatus(String status) {
		return  repository.findByStatus(status);
		
	}

}

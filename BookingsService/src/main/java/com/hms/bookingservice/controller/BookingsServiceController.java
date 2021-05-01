package com.hms.bookingservice.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hms.bookingservice.exception.BookingCollectionException;
import com.hms.bookingservice.model.BookingsModel;
import com.hms.bookingservice.service.BookingsServiceImpl;
import com.hms.bookingservice.service.SequenceGeneratorService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/booking")
public class BookingsServiceController {
	
	@Autowired
	private BookingsServiceImpl bservice;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Operation(summary = "Get All Bookings")
	@GetMapping("/get")
	public ResponseEntity<?> getBookings(){
		List<BookingsModel> bookings = bservice.getAllBookings();
		
		return new ResponseEntity<>(bookings, bookings.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND  );
	
	}
	
	@Operation(summary = "Add New Booking")
	@PostMapping("/add")
	public ResponseEntity<String> addBooking( @Validated @RequestBody BookingsModel booking) {
		try {
			bservice.createBooking(booking);
			return ResponseEntity.ok("Suceesfully created Booking with Id: "+ booking.getId());
			}
			catch (Exception e) {
			sequenceGeneratorService.decrementSequence(BookingsModel.SEQUENCE_NAME);
				 return new ResponseEntity<>("Booking Already Exist", HttpStatus.BAD_REQUEST);
			}
	}
	
	@Operation(summary = "Get Booking by ID")
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getBooingById(@PathVariable("id") long id){
		Optional<BookingsModel> bookings = bservice.getBookingById(id);
		
		if (!bookings.isPresent()) {
			return new ResponseEntity<>(BookingCollectionException.IdNotFoundException(id), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(bookings,HttpStatus.OK);
		}
		
	}
	
	@Operation(summary = "Change Booking Status by ID")
	@PutMapping("/change/{id}")
	public ResponseEntity<?> changeStatusById(@PathVariable("id") long id){
		Optional<BookingsModel> bookings = bservice.getBookingById(id);
		
		if (!bookings.isPresent()) {
			return new ResponseEntity<>(BookingCollectionException.IdNotFoundException(id), HttpStatus.NOT_FOUND);
		} else {
			bservice.updateBookingStatus(id, "Occupied");
			return new ResponseEntity<>("Booking Updated Successfully",HttpStatus.OK);
		}
		
	}
	
	
	@Operation(summary = "Delete Booking by Id")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBookingById(@PathVariable(value="id")long id) {
		  Optional<BookingsModel> bookings = bservice.getBookingById(id);
		  try {
			  if(bookings.isEmpty()) {
				  return new ResponseEntity<>(BookingCollectionException.IdNotFoundException(id), HttpStatus.NOT_FOUND);
		 }
			  bservice.deleteBookingById(id);
			  return ResponseEntity.ok("Booking Deleted Sucessfully For Id"+id);
			 
	  }
		  catch (Exception e) {
			  return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		  }
	  }
	
	@Operation(summary = "Get Booking by Status")
	@GetMapping("/findstatus/{status}")
	public ResponseEntity<?> getBooingByStatus(@PathVariable("status") String status){
		Optional<BookingsModel> bookings = bservice.getBookingsByStatus(status);
		
		if (!bookings.isPresent()) {
			return new ResponseEntity<>(BookingCollectionException.IdNotFoundException(status), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(bookings,HttpStatus.OK);
		}
		
	}
	
	

}

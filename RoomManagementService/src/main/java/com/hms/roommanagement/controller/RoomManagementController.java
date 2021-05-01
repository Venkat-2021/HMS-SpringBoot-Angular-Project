package com.hms.roommanagement.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hms.roommanagement.exception.RoomsCollectionException;
import com.hms.roommanagement.model.Bookings;
import com.hms.roommanagement.model.RoomsModel;
import com.hms.roommanagement.service.RoomsServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/rooms")
public class RoomManagementController {
	
	@Autowired
	private RoomsServiceImpl roomService;
	
	
	//To Create Room
	@Operation(summary = "Create a new Room")
	@PostMapping("/add")
	public ResponseEntity<?> createRoom(@RequestBody RoomsModel room ) throws RoomsCollectionException{
		Optional<RoomsModel> rooms = roomService.getRoomById(room.getId());
		if (rooms.isPresent()) {
			throw new RoomsCollectionException(RoomsCollectionException.RoomAlreadyExists());
		} else {
			roomService.createRoom(room);
			return new ResponseEntity<>("Successfully Created Room with Room Number :: " + room.getId(), HttpStatus.OK);
		}
	}
	
	//To Get List of All Rooms
	@Operation(summary = "Get List of all Rooms")
	@GetMapping("/get")
	public ResponseEntity<?> getAllRooms() {
		List<RoomsModel> roomsList = roomService.getAllRooms();
				
		return new ResponseEntity<>(roomsList, roomsList.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND );
	}
		
	
	//To Get Room by Specific Room Number
	@Operation(summary = "Get Room by Room Number")
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getSingleRoom(@PathVariable("id") int id){
		Optional<RoomsModel> rooms = roomService.getRoomById(id);
		if (!rooms.isPresent()) {
			return new ResponseEntity<>(RoomsCollectionException.NotFoundException(id), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(rooms,HttpStatus.OK);
		}
	}
	
	// To Update the room by roomID
	@Operation(summary = "Update Room by Room Number")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateRoomById(@PathVariable("id") int id, @RequestBody RoomsModel room){
		try {
			
			 RoomsModel rooms=roomService.updateRoom(id, room);
			 return ResponseEntity.ok("Successfully updated Room with Room Number :: "+ rooms.getId()); 
		 }
		 catch(Exception e) {
			return e.getMessage().contains("duplicate") ? new ResponseEntity<>(RoomsCollectionException.RoomAlreadyExists(),
					HttpStatus.BAD_REQUEST): new ResponseEntity<>(RoomsCollectionException.NotFoundException(id),HttpStatus.NOT_FOUND);
		 }
	}
	
	//To Delete Specific Room By Id
	@Operation(summary = "Delete Room by Room Number")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRoomById(@PathVariable("id") int id){
		try {
			roomService.deleteRoomById(id);
			return new ResponseEntity<>("Successfully deleted the room with id"+id, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	//To update the status and insert Booking Details
	@Operation(summary = "Update Room status and insert Booking Details by Room Number")
	@PostMapping("/update/{id}/{status}")
	public ResponseEntity<?> updateRoomStatus(@PathVariable("id") int id,@PathVariable("status") String status, @RequestBody Bookings booking){
		Optional<RoomsModel> rooms = roomService.getRoomById(id);
		if (!rooms.isPresent()) {
			return new ResponseEntity<>(RoomsCollectionException.NotFoundException(id), HttpStatus.OK);
		} else {
			roomService.updateRoomStatus(id, status, booking);
			return new ResponseEntity<>("Status Updated and Booking Details added", HttpStatus.OK);
		}
		
	}
	
	//To get Details using checkInDate, checkOutDate
	@GetMapping("/search/{checkInDate}/{checkOutDate}/{status}")
	public ResponseEntity<?> searchRoom(
		@PathVariable("status") String status,	@PathVariable("checkInDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkInDate, @PathVariable("checkOutDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOutDate) {
	 Optional<RoomsModel> get = roomService.getRoomStatusWithBookingDetails(status, checkInDate, checkOutDate);
	 return  ResponseEntity.ok(get);
		
	}
	
	//To get Bookings By Room Number
	@GetMapping("/bookings/{id}")
	public ResponseEntity<?> getListofBookings(@PathVariable("id") int id){
		Optional<RoomsModel> rooms = roomService.getRoomById(id);
		if (!rooms.isPresent()) {
			return new ResponseEntity<>(RoomsCollectionException.NotFoundException(id),HttpStatus.NOT_FOUND);
		} else {
			List<Bookings> booking = roomService.getBookingsByRoomNo(id);
			return new ResponseEntity<>(booking,HttpStatus.OK);
		}
	}
	
	
	
	
	
	
	
	
}

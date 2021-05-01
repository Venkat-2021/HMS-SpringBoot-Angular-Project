package com.hms.roommanagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.hms.roommanagement.model.Bookings;
import com.hms.roommanagement.model.RoomsModel;

public interface RoomsService {

	//Method to create the Room
	public RoomsModel createRoom (RoomsModel room);
	
	//Method to Get List of all Available Rooms
	public List<RoomsModel> getAllRooms();
	
	//Method to Get Specific Room Details by RoomNo(id)
	public Optional<RoomsModel> getRoomById(int id);
	
	//Method to update a Document by id
	public RoomsModel updateRoom(int id, RoomsModel room );
	
	//Method to delete room by id
	public int deleteRoomById (int id);
	
	//Method to update the Room status and enter Booking Details
	public void updateRoomStatus(int id, String status, Bookings booking);
	
	//Method to get Room Status, checkIn and checkOut Date.
	public Optional<RoomsModel> getRoomStatusWithBookingDetails (String status, LocalDate checkInDate, LocalDate checkOutDate);
	
	public List<Bookings> getBookingsByRoomNo(int id);
	
	//public void checkIn(int id,String status,LocalDate checkInDate);
	
	
	
}

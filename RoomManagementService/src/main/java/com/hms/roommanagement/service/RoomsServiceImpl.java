package com.hms.roommanagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.roommanagement.model.Bookings;
import com.hms.roommanagement.model.RoomsModel;
import com.hms.roommanagement.repository.RoomsRepository;

@Service
public class RoomsServiceImpl implements RoomsService {

	@Autowired
 	private RoomsRepository repository;

	@Override
	public RoomsModel createRoom(RoomsModel room) {
		return repository.save(room);
	}

	@Override
	public List<RoomsModel> getAllRooms() {
		return repository.findAll();
	}

	@Override
	public Optional<RoomsModel> getRoomById(int id) {
		return repository.findById(id);
	}

	@Override
	public RoomsModel updateRoom(int id, RoomsModel room) {
		Optional<RoomsModel> rooms = repository.findById(id);
		
		RoomsModel update = rooms.get();
		update.setRoomName(room.getRoomName());
		update.setBedType(room.getBedType());
		update.setDescription(room.getDescription());
		update.setPrice(room.getPrice());
		update.setStatus(room.getStatus());
		return repository.save(room);
	}

	@Override
	public int deleteRoomById(int id) {
		repository.deleteById(id);
		return id;
	}

	@Override
	public void updateRoomStatus(int id, String status, Bookings booking) {

		RoomsModel room = repository.findById(id).get();
		List<Bookings> roomWithId = room.getBookings();
		
		roomWithId.add(booking);
		
		room.setBookings(roomWithId);
		room.setStatus(status);
		repository.save(room);
		
	}

	@Override
	public Optional<RoomsModel> getRoomStatusWithBookingDetails(String status,LocalDate checkInDate, LocalDate checkOutDate) {
		
		/*
		 * List<RoomsModel> room =
		 * repository.findAll().stream().forEach(o->o.getBookings()).collect(Collectors.
		 * toList()); List<Bookings> book = new list<Bookings>(room);
		 * 
		 * Boolean rooms = repository.findAll().stream().anyMatch(b ->
		 * (checkInDate.after( b.getBookings().forEach(o->o.getCheckInDate())) ||
		 * checkInDate.isEqual( b.getBookings().forEach(o->o.getCheckInDate())) &&
		 * (checkOutDate.isBefore( b.getBookings().forEach(o->o.getCheckOutDate())) ||
		 * checkOutDate.isEqual( b.getBookings().forEach(o->o.getCheckOutDate())))));
		 */
		
		Optional<RoomsModel> room = repository.findByStatus(status);
		room.filter(a->a.isAvailabeBetween(checkInDate, checkOutDate));
		return room;
		
		
		
		
	}

	@Override
	public List<Bookings> getBookingsByRoomNo(int id) {
		RoomsModel rooms = repository.findById(id).get();
		return rooms.getBookings();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

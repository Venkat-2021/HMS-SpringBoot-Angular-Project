package com.hms.roommanagement;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import com.hms.roommanagement.model.Bookings;
import com.hms.roommanagement.model.RoomsModel;
import com.hms.roommanagement.repository.RoomsRepository;


@DataMongoTest
public class RoomsRepositoryTest {
	
	@Autowired
	private RoomsRepository repository;
	
	List<Bookings> book = new ArrayList<Bookings>();
	LocalDate date = LocalDate.now();
	
	String roomName = "Premium";
	
	String bedType= "Single";
	
	double price = 800.00;
	
	String description = "Single Room with All Aminities";
	
	@Test
	void itShouldSelectRoomByNameExists() {
		Bookings book1 = new Bookings(21015, date, date);
		book.add(book1);
		
		RoomsModel room = new RoomsModel(21015, roomName, bedType, price, description, "Occupied", book);
	
		repository.save(room);
		Optional<RoomsModel> optionalroom = repository.findByRoomName(roomName);
		assertThat(optionalroom).isPresent();
	}

	@Test
	void itShouldSelectRoomByStatusExists() {
		Bookings book1 = new Bookings(21015, date, date);
		book.add(book1);
		
		RoomsModel room = new RoomsModel(21015, roomName, bedType, price, description, "Occupied", book);
	
		repository.save(room);
		Optional<RoomsModel> optionalroom = repository.findByStatus("Occupied");
		assertThat(optionalroom).isPresent();
	}
	
	
	
	
	
	
	
	
	
	
}

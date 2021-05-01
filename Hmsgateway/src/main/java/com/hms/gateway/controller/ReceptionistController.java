package com.hms.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import com.hms.gateway.model.RoomsModel;

@RequestMapping("/receptionist")
public class ReceptionistController {
	
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	/*Receptionist Operations
	 * ADD    -  BOOKING
	 * VIEW   -  ROOMS/BOOKINGS
	 * Cannot Perform Operations of Employee With Role "ADMIN && MANAGER"
	 */
	

	/* * LIST OF SERVICES *     
	 *		ROOMS-SERVICE
	 * 		BOOKINGS-SERVICE
	 *   */	

	
								/*       * ROOM SERVICE API CALLS *       */	
	
	public String GET_ALLROOMS = "http://ROOMS-SERVICE/rooms/get";
	public String GET_ROOMBYID = "http://ROOMS-SERVICE/rooms/find/{id}";

	
								/*       * BOOKING SERVICE API CALLS *       */	
	
	public String ADD_BOOKING = "http://BOOKING-SERVICE/booking/get";
	public String GET_BOOKINGS = "http://BOOKING-SERVICE/booking/find/{id}";


	
								/*       * ROOM SERVICE API CALL METOHDS WITH REST TEMPLATE *       */
	
	@GetMapping("/rooms")
	public ResponseEntity<?> getallRooms() {
	try {
	  RoomsModel room = restTemplate.getForObject( GET_ALLROOMS, RoomsModel.class);
		return ResponseEntity.ok(room) ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
		}
	}

}

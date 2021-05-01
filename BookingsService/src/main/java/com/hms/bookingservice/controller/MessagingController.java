package com.hms.bookingservice.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hms.bookingservice.model.Bookings;

@RestController
public class MessagingController {
	
	@Autowired
	Bookings booking;
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/customer/{id}/{status}")
	public String createOrder(@PathVariable("id") int id, @PathVariable("status") String status, @RequestBody Bookings booking ,@RequestParam("exchangename")String exchange, @RequestParam("routingkey")String key) {
		template.convertAndSend(exchange,
				key,booking);
		return "Bookings Updated";
		
	}

}

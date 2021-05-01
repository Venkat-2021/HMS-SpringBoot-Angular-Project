package com.hms.roommanagement.consumer;



import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hms.roommanagement.config.MessagingConfig;
import com.hms.roommanagement.model.Bookings;
import com.hms.roommanagement.repository.RoomsRepository;
//import com.hms.roommanagement.service.RoomsServiceImpl;

@Component
public class MessagingConsumer {
	
//	@Autowired
//	private RoomsServiceImpl roomService;
	
	@Autowired
	private RoomsRepository repo;
	
	 @RabbitListener(queues = MessagingConfig.QUEUE)
	 public void consumeMessageFromQueue(int id,String status,Bookings booking) {
		 List<Bookings> book = repo.findById(id).get().getBookings();
				 book.add(booking);
		 System.out.println(id);
	    	
	 }

}

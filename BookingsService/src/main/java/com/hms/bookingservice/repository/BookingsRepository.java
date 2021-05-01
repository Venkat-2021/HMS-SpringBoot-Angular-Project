package com.hms.bookingservice.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.hms.bookingservice.model.BookingsModel;

@Repository
public interface BookingsRepository extends MongoRepository<BookingsModel, Long> {
	
	Optional<BookingsModel> findByStatus(String status);

}

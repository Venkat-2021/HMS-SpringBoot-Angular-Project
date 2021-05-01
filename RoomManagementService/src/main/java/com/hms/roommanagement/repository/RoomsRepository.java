package com.hms.roommanagement.repository;


import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.hms.roommanagement.model.RoomsModel;

@Repository
public interface RoomsRepository extends MongoRepository<RoomsModel, Integer> {


	Optional<RoomsModel> findByRoomName(String roomName);
	
	Optional<RoomsModel> findByStatus(String status);
	

}

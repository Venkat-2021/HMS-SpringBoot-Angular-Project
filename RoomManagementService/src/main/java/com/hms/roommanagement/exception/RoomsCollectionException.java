package com.hms.roommanagement.exception;



public class RoomsCollectionException extends Exception{
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public RoomsCollectionException(String message){
        super(message);
    }
    
    public static String NotFoundException(int id) {
		return "Room with room number "+id+" not Found";
	}

    public static String NotFoundException(String status) {
		return "Room with room Status "+status+" not Found";
	}
    
	public static String RoomAlreadyExists() {
		return "Room Already Exist";
	}
}
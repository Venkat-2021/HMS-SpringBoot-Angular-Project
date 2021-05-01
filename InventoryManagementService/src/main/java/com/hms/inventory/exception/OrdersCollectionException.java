package com.hms.inventory.exception;



public class OrdersCollectionException extends Exception{
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public OrdersCollectionException(String message){
        super(message);
    }
    
    public static String NotFoundException(long id) {
		return "Order with "+id+" not Found";
	}
	
	public static String OrderAlreadyExists() {
		return "Order Already Exist";
	}
}
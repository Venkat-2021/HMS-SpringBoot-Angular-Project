package com.hms.inventory.exception;



public class ProductCollectionException extends Exception{
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public ProductCollectionException(String message){
        super(message);
    }
    
    public static String NotFoundException(long id) {
		return "Product with "+id+" not Found";
	}
	
	public static String ProductAlreadyExists() {
		return "Product Already Exist";
	}
}
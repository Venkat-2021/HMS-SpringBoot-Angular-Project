package com.hms.employeeservice.exception;

public class EmployeesCollectionException extends Exception{
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public EmployeesCollectionException(String message){
        super(message);
    }
    
    public static String RoleNotFoundException(String role) {
		return "Employee with Id :: "+ role +" is not Found";
	}
    
    public static String EmailNotFoundException(String Email) {
		return "Employee with Id :: "+ Email +" is not Found";
	}

	public static String IdNotFoundException(long id) {
		return "Employee with Id :: "+id+" is not Found";
	}
    
	
	public static String EmployeeAlreadyExists() {
		return "Employee Already Exist";
	}
}
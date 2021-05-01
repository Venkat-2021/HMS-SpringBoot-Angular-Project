package com.hms.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hms.gateway.model.ProductModel;

@Service
public class ManagerService {
	
	@Autowired
	private RestTemplate restTemplate;
	

	/* * LIST OF SERVICES *     
	 * 		EMPLOYEE-SERVICE
	 * 		INVENTORY-SERVICE
	 *		ROOMS-SERVICE
	 * 		BOOKINGS-SERVICE
	 *   */	
	
								/*       * EMPLOYEE SERVICE API CALLS *       */
	
	public String ADD_EMPLOYEE = "http://EMPLOYEE-SERVICE/employee/add";
	public String GET_ALLEMPLOYEES = "http://EMPLOYEE-SERVICE/employee/get";
	public String GET_EMPLOYEEBYID = "http://EMPLOYEE-SERVICE/employee/getby/{id}";
	public String GET_EMPLOYEEBYROLE = "http://EMPLOYEE-SERVICE/employee/findby/{role}";
	public String GET_EMPLOYEEBYEMAIL = "http://EMPLOYEE-SERVICE/employee/get/{email}";
	public String GET_TASKBYID = "http://EMPLOYEE-SERVICE/employee/get/task/{id}";
	public String ADD_TASKBYID = "http://EMPLOYEE-SERVICE/employee/task/{id}";
	public String UPDATE_EMPLOYEEBYID = "http://EMPLOYEE-SERVICE/employee/update/{id}";
	
	
								/*       * INVENTORY SERVICE API CALLS *       */	
	
	public String ADD_PRODUCT = "http://INVENTORY-SERVICE/product/add";
	public String GET_ALLPRODUCTS = "http://INVENTORY-SERVICE/product/get";
	public String GET_PRODUCTBYID = "http://INVENTORY-SERVICE/product/find/{id}";
	public String UPDATE_PRODUCTBYID = "http://INVENTORY-SERVICE/product/update/{id}";
	public String DELETE_PRODUCTBYID = "http://INVENTORY-SERVICE/product/delete/{id}";
	public String ADD_ORDER = "http://INVENTORY-SERVICE/product/order/{id}";
	public String GET_ALLORDERS = "http://INVENTORY-SERVICE/product/orders/get/{id}";
	
								/*       * ROOM SERVICE API CALLS *       */	
	
	public String ADD_ROOM = "http://ROOMS-SERVICE/rooms/add";
	public String GET_ALLROOMS = "http://ROOMS-SERVICE/rooms/get";
	public String GET_ROOMBYID = "http://ROOMS-SERVICE/rooms/find/{id}";
	public String UPDATE_ROOMBYID = "http://ROOMS-SERVICE/rooms/update/{id}";
	public String DELETE_ROOMBYID = "http://ROOMS-SERVICE/rooms/delete/{id}";
	public String UPDATE_ROOMSTATUS = "http://ROOMS-SERVICE/rooms/update/{id}/{status}";
	
	
								/*       * EMPLOYEE SERVICE API CALL METOHDS WITH REST TEMPLATE *       */
	
	public ProductModel getallProducts( ) {
	  ProductModel product = restTemplate.getForObject("http://INVENTORY-SERVICE/product/find/4", ProductModel.class);
		return product ;
	}

	
	
	
	

}

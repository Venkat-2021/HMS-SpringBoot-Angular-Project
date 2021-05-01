package com.hms.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hms.gateway.model.EmployeeModel;
import com.hms.gateway.model.ProductModel;
import com.hms.gateway.model.RoomsModel;


@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private RestTemplate restTemplate;
	
	/*Manager Operations
	 * ADD    -  ROOM/INVENTORY/EMPLOYEE
	 * UPDATE -  ROOM/INVENTORY/EMPLOYEE
	 * DELETE -  ROOM/INVENTORY
	 * Cannot Perform Operations of Employee With Role "ADMIN"
	 */
	

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
	
	
								/*       * SERVICE GET API CALL METOHDS WITH REST TEMPLATE *       */
	
	@GetMapping("/products")
	public ResponseEntity<?> getallProducts() {
	try {
	  ProductModel product = restTemplate.getForObject(GET_ALLPRODUCTS, ProductModel.class);
		return ResponseEntity.ok(product) ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@GetMapping("/employess")
	public ResponseEntity<?> getallEmployees() {
	try {
	  EmployeeModel employee = restTemplate.getForObject(GET_ALLEMPLOYEES, EmployeeModel.class);
		return ResponseEntity.ok(employee) ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
		}
	}
	
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

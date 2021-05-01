package com.hms.gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hms.gateway.model.ProductModel;

@RestController
@RequestMapping("/admin")
public class AdminController {
	

	/*ADMIN Operations
	 * ADD    -  ROOM/INVENTORY/EMPLOYEE
	 * UPDATE -  ROOM/INVENTORY/EMPLOYEE
	 * DELETE -  ROOM/INVENTORY
	 * Can Perform ALL Operations
	 */
	

	/* * LIST OF SERVICES *     
	 * 		EMPLOYEE-SERVICE
	 * 		INVENTORY-SERVICE
	 *		ROOMS-SERVICE
	 * 		BOOKINGS-SERVICE
	 *   */	
	
	

	@Autowired
	private RestTemplate restTemplate;
	
						/*       * EMPLOYEE SERVICE API CALLS *       */
	
			public String DELETE_EMPLOYEEBYID = "http://EMPLOYEE-SERVICE/employee/delete/{id}";
	
	
	@GetMapping("/products")
	public ResponseEntity<?> getallProducts( ) {
	try {
	  ProductModel product = restTemplate.getForObject("http://INVENTORY-SERVICE/product/find/4", ProductModel.class);
		return ResponseEntity.ok(product) ;
        }
	catch(Exception e) {
		return ResponseEntity.ok(e.getMessage());
		}
	}
	
	
}

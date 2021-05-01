package com.hms.inventory.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hms.inventory.exception.ProductCollectionException;
import com.hms.inventory.model.OrderModel;
import com.hms.inventory.model.ProductModel;
import com.hms.inventory.service.ProductServiceImpl;
import com.hms.inventory.service.SequenceGeneratorService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductServiceImpl pservice;
	
	@Autowired
	private SequenceGeneratorService seqservice;
	
	@Operation(summary = "Add New Product")
	@PostMapping("/add")
	public ResponseEntity<?> addProduct( @Validated @RequestBody ProductModel product){
		try {
			pservice.createProduct(product);
			return  ResponseEntity.ok("Successfully added product with id :: " + product.getProductId());
		} catch (Exception e) {
			seqservice.decrementSequence(ProductModel.SEQUENCE_NAME);
			return new ResponseEntity<>("Product Already Exist",HttpStatus.CONFLICT);
		}
	}
	
	@Operation(summary = "Get All Products")
	@GetMapping("/get")
	public ResponseEntity<?> getAllProducts(){
		List<ProductModel> productList = pservice.getAllProducts();
		return new ResponseEntity<>(productList, productList.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@Operation(summary = "Get Product by Id")
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getProductById (@PathVariable("id") long id){
		Optional<ProductModel> products = pservice.getProductById(id);
		if (!products.isPresent()) {
			return new ResponseEntity<>(ProductCollectionException.NotFoundException(id), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(products,HttpStatus.OK);
		}
	}
	
	@Operation(summary = " Update Product by Id")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProductById(@PathVariable("id") long id,@Validated @RequestBody ProductModel product){
		try {
			pservice.updateProduct(id, product);
			return new ResponseEntity<>("Updated the product with id " + id, HttpStatus.OK);
		} catch(Exception e) {
			return e.getMessage().contains("duplicate") ? new ResponseEntity<>(ProductCollectionException.ProductAlreadyExists(),
					HttpStatus.BAD_REQUEST): new ResponseEntity<>(ProductCollectionException.NotFoundException(id),HttpStatus.NOT_FOUND);
		 }
	}
	
	@Operation(summary = "Delete Product by Id")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable("id") long id){
		Optional<ProductModel> products = pservice.getProductById(id);
		try {
			if (!products.isPresent()) {
				return new ResponseEntity<>(ProductCollectionException.NotFoundException(id), HttpStatus.NOT_FOUND);
			}
			pservice.deleteProductById(id);
			return new ResponseEntity<>("Successfully deleted the product with id " + id, HttpStatus.OK );
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@Operation(summary = "Add Order Details")
	@PostMapping("/order/{id}")
	public ResponseEntity<?> addOrder(@PathVariable("id") long id,@RequestBody OrderModel order){
		Optional<ProductModel> products = pservice.getProductById(id);
		if (!products.isPresent()) {
			return new ResponseEntity<>(ProductCollectionException.NotFoundException(id),HttpStatus.NOT_FOUND);
		} else {
			pservice.createOrderByProductId(id, order);
			return new ResponseEntity<>("New Order Updated Successfully",HttpStatus.OK);

		}
	}
	
	
	@Operation(summary="Get the list Orders by Product Id")
	@GetMapping("/orders/get/{id}")
	public ResponseEntity<?> getOrderDetails(@PathVariable("id") long id){
		Optional<ProductModel> products = pservice.getProductById(id);
		if (!products.isPresent()) {
			return new ResponseEntity<>(ProductCollectionException.NotFoundException(id),HttpStatus.NOT_FOUND);
		} else {
			pservice.getOrdersByProductId(id);
			return new ResponseEntity<>(products.get().getOrder(),HttpStatus.OK);

		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

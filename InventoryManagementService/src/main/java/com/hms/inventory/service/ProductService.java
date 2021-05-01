package com.hms.inventory.service;

import java.util.List;
import java.util.Optional;
import com.hms.inventory.model.OrderModel;
import com.hms.inventory.model.ProductModel;



public interface ProductService {

	//Method to create the Product
	public ProductModel createProduct (ProductModel product);
	
	//Method to Get List of all Available Products
	public List<ProductModel> getAllProducts();
	
	//Method to Get Specific Product Details by Product ID
	public Optional<ProductModel>  getProductById(long id);
	
	//Method to update a Document by ID
	public ProductModel updateProduct(long id, ProductModel product);
	
	//Method to delete product by id
	public long deleteProductById(long id);
	
	//Method to create Order by Product Id
	public void createOrderByProductId(long id, OrderModel order);
	
	//Method to Get List of Orders
	public List<OrderModel> getOrdersByProductId(long id);
}

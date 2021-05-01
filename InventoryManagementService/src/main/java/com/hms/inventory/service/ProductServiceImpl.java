package com.hms.inventory.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.inventory.model.OrderModel;
import com.hms.inventory.model.ProductModel;
import com.hms.inventory.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
 	private ProductRepository repository;
	
	@Autowired
	private SequenceGeneratorService sequenceService;
	
	LocalDateTime dateTime = LocalDateTime.now();

	@Override
	public ProductModel createProduct(ProductModel product) {
		product.setProductId(sequenceService.generateSequence(ProductModel.SEQUENCE_NAME));
		product.setModifiedAt(dateTime);
		product.setTotalAmount(product.getQuantity() * product.getPricePerUnit());
		return repository.save(product);
	}

	@Override
	public List<ProductModel> getAllProducts() {
		return repository.findAll();
	}

	@Override
	public Optional<ProductModel> getProductById(long id) {
		return repository.findById(id);
	}

	@Override
	public ProductModel updateProduct(long id, ProductModel product) {
		Optional<ProductModel> products = repository.findById(id);
		ProductModel update = products.get();
		
		update.setProductName(product.getProductName());
		update.setQuantity(product.getQuantity());
		update.setPricePerUnit(product.getPricePerUnit());
		update.setModifiedAt(product.getModifiedAt());
		return repository.save(update);
	}

	@Override
	public long deleteProductById(long id) {
		repository.deleteById(id);
		return id;
	}

	@Override
	public void createOrderByProductId(long id, OrderModel order) {
		ProductModel product = repository.findById(id).get();
		List<OrderModel> orders = product.getOrder();
		LocalDateTime dateTime = LocalDateTime.now();

		order.setOrderId(sequenceService.generateSequence(OrderModel.SEQUENCE_NAME));
		orders.add(order);
		

		product.setQuantity(product.getQuantity()+ order.getQuantityAdded()-order.getQuantityRemoved());
		product.setModifiedAt(dateTime);
		product.setTotalAmount(product.getQuantity()*product.getPricePerUnit());
		repository.save(product);
	}

	
	@Override
	public List<OrderModel> getOrdersByProductId(long id) {
		ProductModel product = repository.findById(id).get();
		return product.getOrder();
	}


}

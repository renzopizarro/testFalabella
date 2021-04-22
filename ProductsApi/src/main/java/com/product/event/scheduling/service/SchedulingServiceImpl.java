package com.product.event.scheduling.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.commons.exception.ApiProductException;
import com.product.entity.Product;
import com.product.repository.ProductRepository;
import com.product.service.ProductManagementService;


@Service
public class SchedulingServiceImpl implements SchedulingService {
	Logger logger = LoggerFactory.getLogger(SchedulingServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductManagementService productManagementService;
	
	
	@Override
	public void manageProduct() throws ApiProductException{
		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			Product productResult = new Product();
			productResult.setId(product.getId());
			productResult.setName(product.getName());
			productResult.setPrice(product.getPrice());
			productResult.setSellIn(product.getSellIn());
			productManagementService.calculatePriceVariation(product, productResult);
			productRepository.save(productResult);
		}
		logger.info("DESPUES DEL UPDATE");
		products = productRepository.findAll();
		logger.info(products.toString());
	}
}
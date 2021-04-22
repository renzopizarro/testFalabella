package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.commons.exception.ApiProductException;
import com.product.entity.Product;
import com.product.entity.Sale;
import com.product.resource.ProductType;
import com.product.service.ProductManagementService;


@RestController
@RequestMapping("/api")
public class ApiProductController {
	@Autowired
	private ProductManagementService productManagementService;

	@GetMapping("/sale")
	public ResponseEntity<Object> sale(@RequestParam(required = true) String productName) {
		try {
			if(Boolean.TRUE.equals(productManagementService.saleProduct(productName))){
				return ResponseEntity.notFound().build();
			}
		} catch (final ApiProductException e) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok().build();
	}

	@GetMapping("/sales")
	public List<Sale> retrieveAllSales() {
		return productManagementService.getAllSales();
	}
	
	@GetMapping("/products")
	public List<Product> retrieveAllProducts() {
		return productManagementService.getAllProducts();
	}
	
	@GetMapping("/evaluateProducts/{days}")
	public List<ProductType> evaluateProducts(@PathVariable Integer days) {
		return productManagementService.evaluateProducts(days);
	}

}

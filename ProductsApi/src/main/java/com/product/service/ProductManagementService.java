package com.product.service;

import java.util.List;

import com.product.commons.exception.ApiProductException;
import com.product.entity.Product;
import com.product.entity.Sale;
import com.product.resource.ProductType;

public interface ProductManagementService {
	public List<Product> getAllProducts() throws ApiProductException;

	public List<Sale> getAllSales() throws ApiProductException;

	public boolean saleProduct(String name) throws ApiProductException;

	public List<ProductType> evaluateProducts(Integer days) throws ApiProductException;

	public void calculatePriceVariation(Product productDAO, Product productrResult)
			throws ApiProductException;
}

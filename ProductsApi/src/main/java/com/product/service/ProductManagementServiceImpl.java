package com.product.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.commons.GlobalConstants;
import com.product.commons.exception.ApiProductException;
import com.product.entity.Product;
import com.product.entity.Sale;
import com.product.repository.ProductRepository;
import com.product.repository.SalesRepository;
import com.product.resource.ProductType;

@Service
public class ProductManagementServiceImpl implements ProductManagementService {
	Logger logger = LoggerFactory.getLogger(ProductManagementServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SalesRepository salesRepository;

	@Override
	public List<Sale> getAllSales() {
		return salesRepository.findAll();
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public boolean saleProduct(String name) throws ApiProductException {
		final List<Product> productToSaleList = productRepository.findByName(name);
		if (productToSaleList.isEmpty()) {
			return false;
		}
		final Sale sale = new Sale();
		sale.setPrice(productToSaleList.get(0).getPrice());
		sale.setName(name);
		salesRepository.save(sale);
		return true;
	}

	@Override
	public List<ProductType> evaluateProducts(Integer days) throws ApiProductException {
		List<Product> allProducts = getAllProducts();
		List<ProductType> productsResponse = new ArrayList<>();
		ProductType productResponse = new ProductType();
		productResponse.setDay(0);
		productResponse.setListProduct(allProducts);
		productsResponse.add(productResponse);
		for (int i = 1; i < days; i++) {
			productResponse= new ProductType();
			productResponse.setDay(i);
			// lista de salida anterior
			List<Product> previousListProduct = productsResponse.get(productsResponse.size()-1).getListProduct();
			for (Product productDAO : previousListProduct) {
				Product productResult = new Product();
				
				productResult.setId(productDAO.getId());
				productResult.setName(productDAO.getName());
				productResult.setSellIn(productDAO.getSellIn());
				productResult.setPrice(productDAO.getPrice());
				// logica de cambio de productos;precio:
				calculatePriceVariation(productDAO, productResult);
				productResponse.getListProduct().add(productResult);
				
			}
			
			productsResponse.add(productResponse);
		}

		return productsResponse;
	}

	@Override
	public void calculatePriceVariation(Product productDAO, Product productResult)
			throws ApiProductException {
		final String name = productDAO.getName();
		Integer price = productDAO.getPrice();
		Integer sellInDecresed = productDAO.getSellIn() - 1;
		productResult.setSellIn(sellInDecresed);

		if (GlobalConstants.MEGA_COBERTURA.equals(name)) {
			productResult.setPrice(GlobalConstants.MEGA_COBERTURA_PRICE);
			return;
		}

		if (price == 0 || price == 100) {
			return;
		}
		if (GlobalConstants.FULL_COBERTURA_SUPER_DUPER.equals(name) || GlobalConstants.FULL_COBERTURA.equals(name)) {
			if (sellInDecresed > 5 && sellInDecresed <= 10) {
				price = price + 2;
			} else if (sellInDecresed > 0 && sellInDecresed <= 5) {
				price = price + 3;
			} else if (sellInDecresed == 0) {
				price = 0;
			}
		} else if (GlobalConstants.SUPER_AVANCE.equals(name) || sellInDecresed < 0) {
			price = price - 2;
		} else {
			price = price-1;
		}
		productResult.setPrice(price);

	}
}

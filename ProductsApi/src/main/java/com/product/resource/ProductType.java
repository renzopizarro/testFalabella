package com.product.resource;

import java.util.ArrayList;
import java.util.List;

import com.product.entity.Product;

public class ProductType {

	private Integer day;
	private List<Product> listProduct = new ArrayList<>();

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

}
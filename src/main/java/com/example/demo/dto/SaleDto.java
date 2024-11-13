package com.example.demo.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SaleDto {
 
	private Date date;
	
	private List<ProductDto> product = new ArrayList<>();

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ProductDto> getProduct() {
		return product;
	}

	public void setProduct(List<ProductDto> product) {
		this.product = product;
	}

	public SaleDto(Date date, List<ProductDto> product) {
		super();
		this.date = date;
		this.product = product;
	}

	public SaleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

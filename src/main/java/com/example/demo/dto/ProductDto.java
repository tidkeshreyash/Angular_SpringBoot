package com.example.demo.dto;

public class ProductDto {
	
	private int product_id;
	private  int sale_quantity;
	private int store_id;
	private double mrp;
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getSale_quantity() {
		return sale_quantity;
	}
	public void setSale_quantity(int sale_quantity) {
		this.sale_quantity = sale_quantity;
	}
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public ProductDto(int product_id, int sale_quantity, int store_id, double mrp) {
		super();
		this.product_id = product_id;
		this.sale_quantity = sale_quantity;
		this.store_id = store_id;
		this.mrp = mrp;
	}
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

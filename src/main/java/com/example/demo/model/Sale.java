package com.example.demo.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Sale {
	
	private int product_id;
	private int sale_quantity;
	private int store_id;
	private Date date;
	private double mrp;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
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
	public Sale(int product_id, int sale_quantity, int store_id, Date date, double mrp) {
		super();
		this.product_id = product_id;
		this.sale_quantity = sale_quantity;
		this.store_id = store_id;
		this.date = date;
		this.mrp = mrp;
	}
	public Sale() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}

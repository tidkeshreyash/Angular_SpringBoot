package com.example.demo.model;

public class Product {
	
	private int id;
	private String pname;
	private double mrp;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public Product(int id, String pname, double mrp) {
		super();
		this.id = id;
		this.pname = pname;
		this.mrp = mrp;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}

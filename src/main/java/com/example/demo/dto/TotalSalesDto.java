package com.example.demo.dto;



public class TotalSalesDto {
	

	private String pname;
	private int totalSales;
	private double mrp;
	private double amount;
	
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public TotalSalesDto(String pname, int totalSales, double mrp, double amount) {
		super();
		this.pname = pname;
		this.totalSales = totalSales;
		this.mrp = mrp;
		this.amount = amount;
	}
	public TotalSalesDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	

}

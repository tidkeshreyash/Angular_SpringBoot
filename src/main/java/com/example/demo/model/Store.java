package com.example.demo.model;

public class Store {
	
	private int id;
	
	private String sname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Store(int id, String sname) {
		super();
		this.id = id;
		this.sname = sname;
	}

	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

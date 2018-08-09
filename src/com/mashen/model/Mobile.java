package com.mashen.model;

import com.mashen.service.BusinessSocketService;

public class Mobile {
	private String phoneNumber;
	private String address;
	private String brand;
	
	public Mobile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mobile(String phoneNumber, String address, String brand) {
		super();
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.brand = brand;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Override
	public String toString() {
		String content="";
		content+="手机号码"+phoneNumber+BusinessSocketService.wrap;
		content+="归属地"+address+BusinessSocketService.wrap;
		content+="品牌"+brand+BusinessSocketService.wrap;
		return content;
	}
	
}

package com.mashen.model;

import com.mashen.service.BusinessSocketService;

public class IDCard {
	private String idcardNumer;
	private String sex;
	private String birthday;
	private String address;
	public IDCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IDCard(String idcardNumer, String sex, String birthday, String address) {
		super();
		this.idcardNumer = idcardNumer;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
	}
	public String getIdcardNumer() {
		return idcardNumer;
	}
	public void setIdcardNumer(String idcardNumer) {
		this.idcardNumer = idcardNumer;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		String content="";
		content+="身份证号："+idcardNumer+BusinessSocketService.wrap;
		content+="性别："+sex+BusinessSocketService.wrap;
		content+="生日："+birthday+BusinessSocketService.wrap;
		content+="地址："+address+BusinessSocketService.wrap;
		return content;
	}
}

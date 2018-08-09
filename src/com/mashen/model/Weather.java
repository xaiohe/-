package com.mashen.model;

import com.mashen.service.BusinessSocketService;

public class Weather {
	private  String high;
	private  String low;
	private  String type;
	private  String notice;
	private  String city;
	private  String date;
	public Weather() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Weather(String high, String low, String type, String notice,
			String city, String date) {
		super();
		this.high = high;
		this.low = low;
		this.type = type;
		this.notice = notice;
		this.city = city;
		this.date = date;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		String content="";
		content+="城市："+city+BusinessSocketService.wrap;
		content+="日期："+city+BusinessSocketService.wrap;
		content+="最高温度："+high+BusinessSocketService.wrap;
		content+="最低温度："+low+BusinessSocketService.wrap;
		content+="天气："+type+BusinessSocketService.wrap;
		content+="注意事项："+notice+BusinessSocketService.wrap;
		return content;
		
		
		
	}
}

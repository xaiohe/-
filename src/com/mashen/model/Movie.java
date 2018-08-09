package com.mashen.model;

import com.mashen.service.BusinessSocketService;

public class Movie {
	private String name;
	private String title;
	private String url;
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Movie(String name, String title, String url) {
		super();
		this.name = name;
		this.title = title;
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		String content="";
		content+="电影名称"+name+BusinessSocketService.wrap;
		content+="标题"+name+BusinessSocketService.wrap;
		content+="下载地址"+name+BusinessSocketService.wrap;
		return content;
	}
}

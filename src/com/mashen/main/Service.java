package com.mashen.main;

import com.mashen.service.BusinessSocketService;

public class Service {
	public static void main(String[] args) {
		System.out.println("百事通项目启动平台，端口号为6688");
		BusinessSocketService.startServer();
	}
}

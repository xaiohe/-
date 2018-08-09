package com.mashen.service;

import java.io.PrintWriter;
import java.util.Scanner;

public interface IpService {
	/*
	 *
	 * ip地址查询的主方法   进入到这个方法中   我们可以跟客户端进行交互  可接受客户端的输入，也可以向客户端输出内容
	 */
	
	public void mainMethod(Scanner scanner,PrintWriter pw);
	public String getContent(Scanner scanner,PrintWriter pw,String ip);
}

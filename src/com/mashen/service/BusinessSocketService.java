package com.mashen.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * 主要处理网络相关的业务
 */
public class BusinessSocketService extends Thread{
	Socket mySocket ;
	public BusinessSocketService(Socket mySocket) {
		this.mySocket = mySocket;
	}
	public static String  wrap="\r\n";
	
	public static void startServer() {
		ServerSocket serverSocket=null;
		try {
			serverSocket = new ServerSocket(6688);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				// 服务端会给客户端输出下面的一段话 便于客户端给出相应的回复(mainMenu方法)
				BusinessSocketService bs=new BusinessSocketService(socket);
				bs.start();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
	//输出主菜单给用户的客户端
	public static void mainMenu(PrintWriter pw){
		String content = "欢迎进入码神百事通平台"+BusinessSocketService.wrap;
		content += "1.输入1,进入ip查询功能"+BusinessSocketService.wrap;
		content += "2.输入2,进入身份证号码查询功能"+BusinessSocketService.wrap;
		content += "3.输入3,进入手机号码查询功能"+BusinessSocketService.wrap;
		content += "4.输入4,进入电影地址下载功能"+BusinessSocketService.wrap;
		content += "5.输入5,进入天气查询功能"+BusinessSocketService.wrap;
		pw.println(content);
		pw.flush();
	}
	//处理用户输入
	public static void dealUserInput(Scanner scanner,PrintWriter pw, String command) {
		switch (command) {
		case "1":
			// 进入查询IP地址的功能
//			pw.println("进入查询IP地址的功能");
//			pw.flush();
			//
			IpService ipService=new IpServiceImpl();
			ipService.mainMethod(scanner, pw);
			break;
		case "2":
			// 进入查询身份证号码的功能
			IdCardService idcardService=new IdCardServiceJsoup(); 
			idcardService.mainMethod(scanner, pw);
			break;
		case "3":
			// 进入查询手机号码的功能
			MobilePhoneService mo=new MobilePhoneServiceImpl();
			mo.mainMethod(scanner, pw);
			break;
		case "4":
			// 进入电影地址下载功能
			MovieService ms=new MovieServiceImpl();
			ms.mainMethod(scanner,pw);
			break;
		case "5":
			// 进入天气查询的功能
			WeatherService op=new WeatherServiceFastJson();
			op.mainMehtod(scanner, pw);
			break;

		default:
			mainMenu(pw);
			break;
		}
	}
	@Override
	public void run() {
		
		PrintWriter pw=null;
		try {
			pw = new PrintWriter(mySocket.getOutputStream());
			mainMenu(pw);
			// 客户会输入对应的数字 服务端会拿到对应的数字
			Scanner s = new Scanner(mySocket.getInputStream());
			String command = s.nextLine();
			dealUserInput(s, pw, command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

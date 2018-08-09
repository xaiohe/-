package com.mashen.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class IpServiceImpl implements IpService {
	/*
	 * 定义接口的实现类方法在下面的方法中能够具体体现
	 */
	@Override
	public void mainMethod(Scanner scanner, PrintWriter pw) {
		pw.print("请输入ip地址" + BusinessSocketService.wrap);
		pw.print("输入y返回到主菜单" + BusinessSocketService.wrap);
		pw.flush();
		String ip = scanner.nextLine();
		// 如果输入一个y就返回到主菜单
		if(ip.equalsIgnoreCase("y")){
			BusinessSocketService.mainMenu(pw);
			String msg=scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);
		}
		// 检查IP地址是否合法
		boolean sign=checkIPIsValied(ip);
		if(!sign){
			//如果不合法  就提示用户不合法
			pw.println("您输入的ip地址不合法，请重新输入");
			pw.flush();
			mainMethod(scanner, pw);
		} 
			// 如果合法 调用一个业务方法
		 //ip=scanner.nextLine();
		String content=getContent(scanner, pw,ip);
		pw.println(content);
		pw.flush();
		mainMethod(scanner, pw);
	}
	// 检查IP地址是否合法
	// 返回false就为合法 返回true就不合法
	public boolean checkIPIsValied(String ip) {
		boolean sign = true;
		// 分析ip地址 255.255.255.255 1.1.1.1
		// 一个IP地址一共有四段，总长度为15，其中包括三个点、且每一段的长度范围是0-255
		if (ip.length()>15) {
			sign = false;
		}
		String[] ipArray=ip.split("\\.");
		if(ipArray.length!=4){
			sign = false;
		}
		for(int i=0;i<ipArray.length;i++){
			Integer num=0;
			try {
				num=Integer.parseInt(ipArray[i]);
			} catch (Exception e) {
				sign=false;
			}
			if(num>255||num<0){
				sign=false;
			}
		}
		return sign;
	}

	@Override
	public String getContent(Scanner scanner, PrintWriter pw,String ip) {
		//String ip=scanner.nextLine();
		//发起一个网络连接，获取网页的内容
		String content=getContentFromURL(ip);
		//从网页里取出运营商
		String spName=getSpNameFromContent(content);
		return spName;
	}
	/*
	 * 获取ip地址和运营商名称
	 * @param webUrl  ip地址
	 */
	public String getContentFromURL(String ip){
		StringBuffer sb=new StringBuffer();
		//http://ip138.com/ips138.asp?ip=120.230.101.10&action=2
		String webUrl="http://ip138.com/ips138.asp?ip="+ip+"&action=2";
		try {
			URL url=new URL(webUrl);
			URLConnection conn=url.openConnection();
			Scanner s=new Scanner(conn.getInputStream(),"gb2312");
			
			while(s.hasNextLine()){
				sb.append(s.nextLine()).append("\r\n");
			}
			s.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	/*
	 * 功能：从一个网页内容获取出除运营商的名称
	 * content  网页的内容
	 */
	public String getSpNameFromContent(String content){
		String spName="";
		if(content.contains("本站数据：")&&content.contains("</li>")){
		int beginIndex=content.indexOf("本站数据：");
		int endIndex=content.indexOf("</li>");
		spName=content.substring(beginIndex+5, endIndex);
		}
		return spName;
		
	}
}

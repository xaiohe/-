package com.mashen.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mashen.model.Mobile;

public class MobilePhoneServiceImpl implements MobilePhoneService{

	@Override
	public void mainMethod(Scanner scanner, PrintWriter pw) {
		pw.print("请输入手机号码" + BusinessSocketService.wrap);
		pw.print("输入y返回到主菜单" + BusinessSocketService.wrap);
		pw.flush();
		String input = scanner.nextLine();
		// 如果输入一个y就返回到主菜单
		if(input.equalsIgnoreCase("y")){
			BusinessSocketService.mainMenu(pw);
			String msg=scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);
		}
		// 检查手机号是否合法
		boolean sign=checkMobileIsValid(input);
		if(!sign){
			//如果不合法  就提示用户不合法
			pw.println("您输入的手机号不合法，请重新输入");
			pw.flush();
			mainMethod(scanner, pw);
		} 
			// 如果合法 调用一个业务方法
		String content=getContent(input);
		pw.println(content);
		pw.flush();
		mainMethod(scanner, pw);
	}
	/*
	 * 检查手机号码是否匹配
	 * mobile  手机号码
	 * 
	 */
	public boolean checkMobileIsValid(String mobile){
		boolean sign=true;
		if(mobile.length()!=11){
			sign=false;
		}
		String[] numArray=mobile.split("");
		for(int i=0;i<numArray.length;i++){
			try {
				Integer.parseInt(numArray[i]);
			} catch (Exception e) {
				return false;
			}
		}
		if(!mobile.startsWith("1")){
			sign=false;
		}
		return sign;
	}
	public String getContent(String mobile){
		String content="";
		String url="http://www.ip138.com:8080/search.asp?mobile="+mobile+"&action=mobile";
		Mobile mobileObject=new Mobile();
		mobileObject.setPhoneNumber(mobile);
		try {
			Document doc=Jsoup.connect(url).get();
			Elements elements=doc.getElementsByAttributeValue("class", "tdc2");
			if(elements.size()>2){
				String address=elements.get(1).text();
				String brand=elements.get(2).text();
				mobileObject.setAddress(address);
				mobileObject.setBrand(brand);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		content=mobileObject.toString();
		return content;
		
	}
}

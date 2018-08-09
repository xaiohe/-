package com.mashen.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mashen.model.IDCard;

public class IdCardServiceJsoup implements IdCardService{

	@Override
	public void mainMethod(Scanner scanner, PrintWriter pw) {
		pw.print("请输入身份证号" + BusinessSocketService.wrap);
		pw.print("输入y返回到主菜单" + BusinessSocketService.wrap);
		pw.flush();
		String input = scanner.nextLine();
		// 如果输入一个y就返回到主菜单
		if(input.equalsIgnoreCase("y")){
			BusinessSocketService.mainMenu(pw);
			String msg=scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);
		}
		// 检查身份证号是否合法
		boolean sign=checkIdCardIsVaild(input);
		if(!sign){
			//如果不合法  就提示用户不合法
			pw.println("您输入的身份证号不合法，请重新输入");
			pw.flush();
			mainMethod(scanner, pw);
		} 
			// 如果合法 调用一个业务方法
		 //ip=scanner.nextLine();
		String content=getContent(input);
		pw.println(content);
		pw.flush();
		mainMethod(scanner, pw);
	}
	/*
	 * 检查身份证号码是否合法
	 */
	public boolean checkIdCardIsVaild(String idCard){
		boolean sign=true;
		if(idCard.length()!=18){
			sign=false;
		}
		return sign;
	}
	/*
	 * 使用jsoup获取身份信息
	 * idcardNumber 身份证号码
	 */
	public String  getContent(String idcardNumber){
		IDCard idcard=new IDCard();
		idcard.setIdcardNumer(idcardNumber);
		String content="";
		String url="http://qq.ip138.com/idsearch/index.asp?userid="+idcardNumber+"&action=idcard&B1=%B2%E9+%D1%AF";
		try {
			Document doc= Jsoup.connect(url).get();
			Elements elements=doc.getElementsByAttributeValue("class" , "tdc2");
			if(elements.size()>=3){
				idcard.setSex(elements.get(0).text());
				idcard.setBirthday(elements.get(1).text());
				idcard.setAddress(elements.get(2).text());
				
			}
			content=idcard.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
		
	}
}

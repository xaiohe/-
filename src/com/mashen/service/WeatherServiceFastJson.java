package com.mashen.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.mashen.model.Forecast;
import com.mashen.model.Result;
import com.mashen.model.Weather;

public class WeatherServiceFastJson	implements WeatherService{

	@Override
	public void mainMehtod(Scanner scanner, PrintWriter pw) {
		pw.print("请输入城市名字" + BusinessSocketService.wrap);
		pw.print("输入y返回到主菜单" + BusinessSocketService.wrap);
		pw.flush();
		String input = scanner.nextLine();
		// 如果输入一个y就返回到主菜单
		if(input.equalsIgnoreCase("y")){
			BusinessSocketService.mainMenu(pw);
			String msg=scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);
		}
		//如果合法，调用一个业务方法
		String content =getContent(input);
		pw.println(content);
		pw.flush();
		mainMehtod(scanner,pw);
	}
	/*
	 * 获取天气预报的内容
	 * 参数是 input
	 */
	public String getContent(String input){
		Weather weather=getWeather(input.trim());
		return weather.toString();
	}
	/*
	 * 根据城市名称获取天气预报
	 * 参数是city
	 */
	
	public String getContentFromURL(String city){
		StringBuffer sb=new StringBuffer();
		String webUrl="https://www.sojson.com/open/api/weather/json.shtml?city=" + city;
		try {
			URL url=new URL(webUrl);
			URLConnection conn=url.openConnection();
			Scanner s=new Scanner(conn.getInputStream());
			while(s.hasNextLine()){
				sb.append(s.nextLine()).append("\r\n");
			}
			s.close();
		} catch ( MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	/*获取天气对象
	 * 参数是city
	 */
	public Weather getWeather(String city){
		Weather weather =new Weather();
	    String content=getContentFromURL(city);
	    Result result=JSON.parseObject(content,Result.class);
	    Forecast f=result.getData().getForecast().get(0);
	    weather.setDate(f.getDate());
		weather.setCity(city);
		weather.setHigh(f.getHigh());
		weather.setLow(f.getLow());
		weather.setType(f.getType());
		weather.setNotice(f.getNotice());
//		String date = getDate(content);
//		weather.setDate(date);
//		weather.setCity(city);
//		weather.setHigh(getHigh(content));
//		weather.setLow(getLow(content));
//		weather.setType(getType(content));
//		weather.setNotice(getNotice(content));
		return weather;
	}
	/*
	 * 获取天气预报里的日期
	 * 参数是 content
	 */
//	public String getDate(String content){
//		int beginIndex=content.indexOf("forecast");
//		int dateBeginIndex=content.indexOf("date",beginIndex);
//		int dateEndIndex=content.indexOf("\",",dateBeginIndex);
//		String date=content.substring(dateBeginIndex+7,dateEndIndex);
//		return date;
//	}
//	/*
//	 * 获取最高温度
//	 */
//	public String getHigh(String content){
//		int beginIndex=content.indexOf("forecast");
//		int dateBeginIndex=content.indexOf("high",beginIndex);
//		int dateEndIndex=content.indexOf("\",",dateBeginIndex);
//		String str=content.substring(dateBeginIndex+7,dateEndIndex);
//		return str;
//	}
//	/*获取最低温度
//	 * 
//	 */
//	public String getLow(String content){
//		int beginIndex=content.indexOf("forecast");
//		int dateBeginIndex=content.indexOf("low",beginIndex);
//		int dateEndIndex=content.indexOf("\",",dateBeginIndex);
//		String str=content.substring(dateBeginIndex+7,dateEndIndex);
//		return str;
//	}
//	/*
//	 * 获取天气
//	 */
//	public String getType(String content){
//		int beginIndex=content.indexOf("forecast");
//		int dateBeginIndex=content.indexOf("type",beginIndex);
//		int dateEndIndex=content.indexOf("\",",dateBeginIndex);
//		String str=content.substring(dateBeginIndex+7,dateEndIndex);
//		return str;
//	}
//	/*
//	 * 获取提示
//	 * 
//	 */
//	public String getNotice(String content){
//		int beginIndex=content.indexOf("forecast");
//		int dateBeginIndex=content.indexOf("notice",beginIndex);
//		int dateEndIndex=content.indexOf("\",",dateBeginIndex);
//		String str=content.substring(dateBeginIndex+9,dateEndIndex);
//		return str;
//	}
}

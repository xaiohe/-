package com.mashen.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mashen.model.Movie;

public class MovieServiceImpl implements MovieService{

	@Override
	public void mainMethod(Scanner scanner, PrintWriter pw) {
		pw.print("请输入带你赢名字" + BusinessSocketService.wrap);
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
		mainMethod(scanner,pw);
	}
	public String getContent(String movieName){
		String content="";
		List<Movie>list=this.getMovieList(movieName);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Movie movie = (Movie) iterator.next();
			content+=movie.toString();
			
		}
		return content;
	}
	public String getDownLoadUrlFromWeburl(String webUrl){
		String content="";
		Document doc;
		try {
			doc = Jsoup.connect(webUrl).get();
			Elements element=doc.getElementsByAttributeValue("bgcolor", "#fdfddf");
			if(element.size()==1){
				Elements es=element.get(0).getElementsByTag("a");
				content=es.get(0).attr("href");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	public List<Movie> getMovieList(String name){
		List<Movie> movieList=new ArrayList<>();
		String url="";
		try {
			url = "http://s.ygdy8.com/plus/so.php?kwtype=0&searchtype=title&keyword="+URLEncoder.encode(name,"gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(url);
		try {
			Document doc=Jsoup.connect(url).get();
			Elements element=doc.getElementsByAttributeValue("class", "co_content8");
			if(element.size()==1){
				Elements es=element.get(0).getElementsByAttributeValueStarting("href", "/html");
				for (int i = 0; i < es.size(); i++) {
					String href=es.get(i).attr("href");
					System.out.println(href);
					Movie movie=new Movie();
					movie.setName(name);
					movie.setTitle(es.get(i).text());
					//根据网页地址，获取电影的下载地址
					String douwnloadUrl=this.getDownLoadUrlFromWeburl("http://s.ygdy8.com"+href);
					movie.setUrl(douwnloadUrl);
					movieList.add(movie);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieList;
	}
}

package com.mashen.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.mashen.thread.ClientThreadSocketInputStream;

public class Client {
	public static void main(String[] args) {
		System.out.println("客户端已启动");
		try {
			Socket socket=new Socket("localhost",6688);
			//因为服务端会发送一句话，我们需要先把他打印出来
//			Scanner scannerService =new Scanner(socket.getInputStream());
//			String welcome=scannerService.nextLine();
//			System.out.println(welcome);
			ClientThreadSocketInputStream thread=new ClientThreadSocketInputStream(socket);
			thread.start();
			PrintWriter pw=new PrintWriter(socket.getOutputStream());
			//监听键盘的输入
			Scanner s=new Scanner(System.in);
			while(s.hasNextLine()){
				//键盘发送一句话 我们就发送给服务器
				String msg=s.nextLine();
				pw.println(msg);
				pw.flush();
				//等待服务器响应   如果不响应  就会在这里一直等待
//				String line=scannerService.nextLine();
//				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package com.mashen.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IpServiceImplTest {

	@Test
	public void testMainMethod() {
	}

	@Test
	public void testCheckIPIsValied() {
		IpServiceImpl service=new IpServiceImpl();
		assertEquals(true,service.checkIPIsValied("192.168.20.111"));
	}

	@Test
	public void testGetSpNameFromtContent() {
		IpServiceImpl service=new IpServiceImpl();
		String content=service.getContentFromURL("8.8.8.8");
		String spName=service.getSpNameFromContent(content);
		System.out.println(spName);
	}

	@Test
	public void testGetContentFromURL() {
		IpServiceImpl service=new IpServiceImpl();
		System.out.println(service.getContentFromURL("192.168.20.111"));
	}

}

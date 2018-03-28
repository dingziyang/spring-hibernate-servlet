package com.sdcm.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {
	
	@Test
	public void test1(){
		ApplicationContext act=new ClassPathXmlApplicationContext("applicationContext.xml");  
		Object obj = act.getBean("sessionFactory");
		System.out.println(obj);
	}
}

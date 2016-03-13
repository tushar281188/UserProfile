package com.test.fbcommunication.mysql.dao.manager;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanManager {
	private static final ApplicationContext context;
	static {
		context = new ClassPathXmlApplicationContext("/dispatcher-servlet.xml");
	}

	public static SessionFactory getSessionFactory() {
		return (SessionFactory) context.getBean("mySessionFactory");
	}
}
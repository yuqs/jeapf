package org.jeapf.framework.test;

import org.jeapf.framework.security.entity.User;
import org.jeapf.framework.security.service.UserManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	private static ApplicationContext ctx = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		init();
		UserManager um = ctx.getBean(UserManager.class);
		User entity = new User();
		entity.setUsername("test");
		entity.setPassword("test");
		um.save(entity);
	}

	public static void init() {
		String[] configLocations = new String[]{"classpath:applicationContext.xml"};
		ctx = new ClassPathXmlApplicationContext(configLocations);
	}
}

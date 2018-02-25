package com.pax.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringBootApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Config.class, args);
		System.out.println(context.getEnvironment().getProperty("local.ip"));
	}

}

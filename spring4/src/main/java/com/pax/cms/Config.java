package com.pax.cms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class Config {
	
	@Value("${local.ip:9090}")
	private String ip;

	@Bean
	public Person createPerson() {
		return new Person();
	}
	
	
}

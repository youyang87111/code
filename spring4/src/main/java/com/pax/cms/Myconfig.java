package com.pax.cms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Myconfig {

	@Bean(name="mybean")
	@Scope("prototype")
	public Mybean createMybean() {
		return new Mybean();
	}
	
	@Bean(name="mybean2")
	public MyFactoryBean createMyFactoryBean() {
		return new MyFactoryBean();
	}
	
	@Bean
	public MyBeanFactory mybeanfactory() {
		return new MyBeanFactory();
	}
	
	@Bean(name="mybean3")
	public Mybean createMybean3(MyBeanFactory factory) {
		return factory.create();
	}
	
	@Bean(name="mydoy",initMethod="init",destroyMethod="destroy")
	public Mydoy createMydoy() {
		return new Mydoy();
	}
	
}

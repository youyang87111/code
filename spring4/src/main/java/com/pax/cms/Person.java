package com.pax.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Person{

	@Autowired
	private ApplicationContext context;

	public ApplicationContext getContext() {
		return context;
	}
}

package com.pax.cms;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Bank {

	private ApplicationContext context;
	private Apple apple;

	public Bank(ApplicationContext context,Apple apple) {
		super();
		this.context = context;
		this.apple = apple;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public Apple getApple() {
		return apple;
	}
}

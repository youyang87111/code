package com.pax.cms;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Mybean> {

	@Override
	public Mybean getObject() throws Exception {
		return new Mybean();
	}

	@Override
	public Class<?> getObjectType() {
		return Mybean.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}

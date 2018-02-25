package com.pax.cms;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Mybean implements InitializingBean,DisposableBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("设置属性之后回调");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("销毁时回调");
	}

}

package com.pax.cms;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Mycat{

	@PostConstruct
	public void init() {
		System.out.println("设置属性之后回调");
	}

	@PreDestroy
	public void destroy()  {
		System.out.println("销毁时回调");
	}

}

package com.pax.core.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 系统初始化监听器,在系统启动时运行,进行一些初始化工作
 * @author 
 *
 */
public class InitListener implements javax.servlet.ServletContextListener {
	
	public static ApplicationContext ctx = null;
	
	public static ApplicationContext getCtx() {
		return ctx;
	}
	
	public void contextInitialized(ServletContextEvent evt) {
		ctx = WebApplicationContextUtils.getWebApplicationContext(evt.getServletContext());
	}
	
	public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
		
	}
	
}

package com.pax.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 字符集拦截器
 * 
 * @author
 * 
 */
public class EncodingInterceptor implements HandlerInterceptor {
	
	/**
	 * 在controller后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
								Object object, Exception exception) throws Exception {
		response.setContentType("application/json;charset=utf-8");
		
	}
	
	public void postHandle(	HttpServletRequest request, HttpServletResponse response, Object object,
							ModelAndView modelAndView) throws Exception {
		
	}
	
	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(	HttpServletRequest request, HttpServletResponse response,
								Object object) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		return true;
	}
	
}

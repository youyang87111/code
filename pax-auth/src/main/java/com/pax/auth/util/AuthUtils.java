package com.pax.auth.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pax.auth.entity.User;

public class AuthUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthUtils.class);

	public static String getUserName() {
		
		User user = getUser();
		
		if (user == null) {
			return "";
		} else {
			return user.getName();
		}
		
	}
	
	public static String getUserLoginname() {
		
		User user = getUser();
		
		if (user == null) {
			return "";
		} else {
			return user.getLoginname();
		}
	}
	
	public static User getUser() {
		try {
			
			Subject subject = SecurityUtils.getSubject();
			
			Object principal = subject.getPrincipal();
			
			if (principal == null) {
				return null;
			} else {
				User user = (User) principal;
				return user;
			}
		} catch (Exception e) {
			logger.info("不存在session会话:" + e.getMessage());
		}
		return null;
		
	}
}

package com.pax.core.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pax.auth.entity.User;

import net.sf.json.JSONObject;

/**
 * @ClassName: ContextHolderUtils
 * @Description: TODO(上下文工具类)
 */
public class WebUtils {
	private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);
	
	/**
	 * SpringMvc下获取request
	 *
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
			.getRequestAttributes()).getRequest();
		return request;
		
	}
	
	/**
	 * SpringMvc下获取response
	 *
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
			.getRequestAttributes()).getResponse();
		return response;
		
	}
	
	/**
	 * SpringMvc下获取session
	 *
	 * @return
	 */
	public static HttpSession getSession() {
		try {
			HttpSession session = getRequest().getSession();
			return session;
		} catch (Exception e) {
			logger.info("不存在sessioin会话");
		}
		return null;
	}
	
	public static String getParameter(String param) {
		String value = getRequest().getParameter(param);
		if ("null".equals(value)) {
			return null;
		} else {
			return value;
		}
	}
	
	public static String makeFailString(String msg) {
		Locale locale = WebUtils.getSession() == null ? null
				: (Locale) WebUtils.getSession().getAttribute("locale");
		JSONObject json = new JSONObject();
		json.put("success", "false");
		json.put("msg", TranslationUtils.getInstance(locale).__(msg));
		return json.toString();
	}
	
	public static String makeFailString(String msg, String status, String url) {
		Locale locale = WebUtils.getSession() == null ? null
				: (Locale) WebUtils.getSession().getAttribute("locale");
		JSONObject json = new JSONObject();
		json.put("success", "false");
		json.put("msg", TranslationUtils.getInstance(locale).__(msg));
		json.put("status", status);
		json.put("location", url);
		return json.toString();
	}
	
	public static JSONObject makeFailObj(String msg) {
		Locale locale = WebUtils.getSession() == null ? null
			: (Locale) WebUtils.getSession().getAttribute("locale");
		
		JSONObject json = new JSONObject();
		json.put("success", "false");
		json.put("msg", TranslationUtils.getInstance(locale).__(msg));
		return json;
	}
	
	public static String makeSuccessString(String msg) {
		Locale locale = WebUtils.getSession() == null ? null
				: (Locale) WebUtils.getSession().getAttribute("locale");
		JSONObject json = new JSONObject();
		json.put("success", "true");
		json.put("msg", msg == null ? null : TranslationUtils.getInstance(locale).__(msg));
		return json.toString();
	}
	
	public static JSONObject makeSuccessObj(String msg) {
		Locale locale = WebUtils.getSession() == null ? null
			: (Locale) WebUtils.getSession().getAttribute("locale");
		
		JSONObject json = new JSONObject();
		json.put("success", "true");
		json.put("msg", msg == null ? null : TranslationUtils.getInstance(locale).__(msg));
		return json;
	}
	
	public static String makeSuccessString(String msg, Object data) {
		Locale locale = WebUtils.getSession() == null ? null
				: (Locale) WebUtils.getSession().getAttribute("locale");
		JSONObject json = new JSONObject();
		json.put("success", "true");
		json.put("msg", msg == null ? null : TranslationUtils.getInstance(locale).__(msg));
		json.put("data", data);
		return json.toString();
	}
	
	public static JSONObject makeSuccessObj(String msg, Object data) {
		Locale locale = WebUtils.getSession() == null ? null
				: (Locale) WebUtils.getSession().getAttribute("locale");
		JSONObject json = new JSONObject();
		json.put("success", "true");
		json.put("msg", msg == null ? null : TranslationUtils.getInstance(locale).__(msg));
		json.put("data", data);
		return json;
	}
	
	public static com.alibaba.fastjson.JSONObject makeSuccessFastObj(String msg, Object data) {
		com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
		Locale locale = WebUtils.getSession() == null ? null
			: (Locale) WebUtils.getSession().getAttribute("locale");
		json.put("success", "true");
		json.put("msg", msg == null ? null : TranslationUtils.getInstance(locale).__(msg));
		json.put("data", data);
		return json;
	}
	
	public static com.alibaba.fastjson.JSONObject makeSuccessFastObj(String msg) {
		com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
		Locale locale = WebUtils.getSession() == null ? null
			: (Locale) WebUtils.getSession().getAttribute("locale");
		
		json.put("success", "true");
		json.put("msg", msg == null ? null : TranslationUtils.getInstance(locale).__(msg));
		return json;
	}
	
	public static com.alibaba.fastjson.JSONObject makeFailedFastObj(String msg) {
		com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
		Locale locale = WebUtils.getSession() == null ? null
			: (Locale) WebUtils.getSession().getAttribute("locale");
		
		json.put("success", "false");
		json.put("msg", msg == null ? null : TranslationUtils.getInstance(locale).__(msg));
		return json;
	}
	
	public static String makeDataTableArrayString(String sEcho, int count, Object jsonArray) {
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("success", "true");
		//
		jsonResponse.put("sEcho", sEcho);
		//总记录数
		jsonResponse.put("iTotalRecords", count);
		//总记录数
		jsonResponse.put("iTotalDisplayRecords", count);
		//记录数据
		jsonResponse.put("aaData", jsonArray);
		
		/*JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);*/
		return jsonResponse.toString();
	}
	
	public static void response(String msg) {
		
		HttpServletResponse response = getResponse();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-store");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
			}
		}
		
	}
	
	public static void responseSuccessString(String msg) {
		response(makeSuccessString(msg));
	}
	
	public static void responseSuccessString(String msg, Object data) {
		response(makeSuccessString(msg, data));
	}
	
	public static void responseFailString(String msg) {
		response(makeFailString(msg));
	}
	
	public static void responseFailString(String msg, String status, String url) {
		response(makeFailString(msg, status, url));
	}
	
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

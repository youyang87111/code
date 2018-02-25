package com.pax.core.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import com.pax.auth.entity.User;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.TranslationUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
public class BaseAjaxController {
	
	/* datatable专用 */
	protected String				sEcho			= "1";					//默认值
	
	//分页用的查询参数
	protected PageQueryParam		pageQueryParam	= new PageQueryParam();
	
	//普通用的查询参数
	protected Map<String, Object>	filterMap;
	
	public String getJsonString(Map<Object, String> args) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		Iterator<Map.Entry<Object, String>> entrys = args.entrySet().iterator();
		while (entrys.hasNext()) {
			Map.Entry<Object, String> entry = entrys.next();
			buffer.append("['" + entry.getKey() + "',");
			buffer.append("'" + entry.getValue() + "'],");
		}
		//		System.out.println("%%%%%%%%%%%%%%%%%    "+buffer);
		buffer.deleteCharAt(buffer.length() - 1);
		buffer.append("]");
		return buffer.toString();
	}
	
	public JSONArray getJSONArray(List<Object> list) {
		JSONArray jsonArray = new JSONArray();
		for (Object o : list) {
			JSONObject jsonObject = getJson(o);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public JSONObject getJson(Object o) {
		JsonConfig jsonConfig = new JsonConfig();
		// 过滤相关联的属性，避免转换循环包含
		// 此对象没有需要过滤属性
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(o, jsonConfig);
		} catch (JSONException jex) {
			throw new BusinessException("JSON Excepstion:" + jex.getMessage());
		}
		if (jsonObject == null) {
			throw new BusinessException("JSON Excepstion: transform from Object failed");
		}
		// 处理状态
		// 此对象没有需要特殊处理属性
		return jsonObject;
	}
	
	/**
	 * 初始化页面列表翻页、排序、过滤条件的参数
	 * @param
	 */
	protected void initializePagingSortingFiltering() {
		
		HttpServletRequest request = getRequest();
		
		/* datatable 传递的参数 */
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		
		pageQueryParam.setPageNo(iDisplayStart / (iDisplayLength == 0 ? 1 : iDisplayLength) + 1);
		pageQueryParam.setPageSize(iDisplayLength);
		
		sEcho = request.getParameter("sEcho");
		
		/* sorting */
		Map<String, String> sortMap = pageQueryParam.getSortMap();
		
		/* datatable 传递的参数 */
		String sortField = request.getParameter("mDataProp_" + request.getParameter("iSortCol_0"));
		String sortDirection = request.getParameter("sSortDir_0");
		String sortAble = request.getParameter("bSortable_" + request.getParameter("iSortCol_0"));
		
		if (StringUtils.isNotBlank(sortField) && "true".equals(sortAble)) {
			if (sortMap == null) {
				sortMap = new HashMap<String, String>();
			}
			sortMap.put(sortField, sortDirection);
		}
		
		/* filtering */
		filterMap = WebUtils.getParametersStartingWith(request, "search_");
		for (String key : filterMap.keySet()) {
			if (StringUtils.isNotBlank(filterMap.get(key).toString())) {
				/*
				filterMap.put(key,
					filterMap.get(key).toString().trim().replace("@", "").replace("!", "")
						.replace("?", "").replace("#", "").replace("'", "").replace("=", "")
						.replace("\\", ""));
						*/
				//TODO:区分=值/like
				filterMap.put(key, filterMap.get(key).toString().trim().replace("'", "''")
					.replace("[", "\\[").replace("%", "\\%").replace("_", "\\_").replace("^", "\\^")
				//.replace("@", "[@]")
				//.replace("!", "[!]")
				//.replace("?", "[?]")
				//.replace("#", "[#]")
				//.replace("=", "[=]")
				//.replace("\\", "[\\]")
				);
			}
		}
		
		pageQueryParam.setFilterMap(filterMap);
		pageQueryParam.setSortMap(sortMap);
	}
	
	protected void initializeFiltering() {
		
		Enumeration<String> paramNames = getRequest().getParameterNames();
		
		filterMap = new HashMap<String, Object>();
		
		while (paramNames != null && paramNames.hasMoreElements()) {
			
			String paramName = paramNames.nextElement();
			
			String[] values = getRequest().getParameterValues(paramName);
			if (values == null || values.length == 0) {
				// Do nothing, no values found at all.
			} else if (values.length > 1) {
				filterMap.put(paramName, values);
			} else {
				filterMap.put(paramName, values[0].replace("'", "''").replace("[", "\\[")
					.replace("%", "\\%").replace("_", "\\_").replace("^", "\\^"));
			}
		}
	}
	
	public String makeSuccessString(Object msg) {
		JSONObject json = new JSONObject();
		json.put("success", "true");
		json.put("msg", msg);
		return json.toString();
	}
	
	public JSONObject makeSuccessJson(Object msg) {
		JSONObject json = new JSONObject();
		json.put("success", "true");
		json.put("msg",
			(TranslationUtils
				.getInstance(com.pax.core.util.WebUtils.getSession() == null ? null
					: (Locale) com.pax.core.util.WebUtils.getSession().getAttribute("locale"))
				.__((String) msg)));
		return json;
	}
	
	public String makeFailString(Object msg) {
		JSONObject json = new JSONObject();
		json.put("success", "false");
		json.put("msg", msg);
		return json.toString();
	}
	
	public JSONObject makeFailJson(Object msg) {
		JSONObject json = new JSONObject();
		json.put("success", "false");
		json.put("msg", msg);
		return json;
	}
	
	public String makeSuccessString(Object msg, Object data) {
		JSONObject json = new JSONObject();
		json.put("success", "true");
		json.put("msg",
			(TranslationUtils
				.getInstance(com.pax.core.util.WebUtils.getSession() == null ? null
					: (Locale) com.pax.core.util.WebUtils.getSession().getAttribute("locale"))
				.__((String) msg)));
		json.put("data", data);
		return json.toString();
	}
	
	public JSONObject makeSuccessJson(Object msg, Object data) {
		JSONObject json = new JSONObject();
		json.put("success", "true");
		json.put("msg",
			(TranslationUtils
				.getInstance(com.pax.core.util.WebUtils.getSession() == null ? null
					: (Locale) com.pax.core.util.WebUtils.getSession().getAttribute("locale"))
				.__((String) msg)));
		json.put("data", data);
		return json;
	}
	
	public JSONObject makeSuccessJson2(Object msg, Object data, int size) {
		
		JSONObject json = new JSONObject();
		json.put("success", "true");
		json.put("msg",
			(TranslationUtils
				.getInstance(com.pax.core.util.WebUtils.getSession() == null ? null
					: (Locale) com.pax.core.util.WebUtils.getSession().getAttribute("locale"))
				.__((String) msg)));
		json.put("aaData", data);
		json.put("iTotalDisplayRecords", size);
		json.put("iTotalRecords", size);
		return json;
	}
	
	public String makeDataTableArrayString(String sEcho, long count, JSONArray jsonArray) {
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("success", "true");
		//
		jsonResponse.put("sEcho", sEcho);
		//总记录数
		jsonResponse.put("iTotalRecords", count);
		//总记录数
		jsonResponse.put("iTotalDisplayRecords", count);
		//记录数据
		jsonResponse.put("aaData", jsonArray.toString());
		
		/*JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);*/
		return jsonResponse.toString();
	}
	
	public JSONObject makeDataTableArrayJson(String sEcho, long count, JSONArray jsonArray) {
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
		return jsonResponse;
	}
	
	public com.alibaba.fastjson.JSONObject makeDataTableJSON(	String sEcho, long count,
																com.alibaba.fastjson.JSONArray jsonArray) {
		com.alibaba.fastjson.JSONObject jsonResponse = new com.alibaba.fastjson.JSONObject();
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
		return jsonResponse;
	}
	
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
	
	public static Session getSession() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		return session;
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
		
		Subject subject = SecurityUtils.getSubject();
		
		Object principal = subject.getPrincipal();
		
		if (principal == null) {
			return null;
		} else {
			User user = (User) principal;
			return user;
		}
		
	}
	
	/**
	 * 直接向客户端返回Content字符串，不用通过View页面渲染.
	 */
	protected void rendText(HttpServletResponse response, Map<String, String> content) {
		this.rendText(response, content, "UTF-8");
	}
	
	/**
	 * 直接向客户端返回Content字符串，不用通过View页面渲染.
	 */
	protected void rendText(HttpServletResponse response, Map<String, String> content,
							String characterEncoding) {
		
		String[] requireQuote = new String[] { "msg", "reason" };
		
		response.setCharacterEncoding(characterEncoding);
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		for (Map.Entry<String, String> entry : content.entrySet()) {
			buffer.append(entry.getKey());
			buffer.append(":");
			if (ArrayUtils.contains(requireQuote, entry.getKey())) {
				buffer.append("'" + entry.getValue() + "'");
			} else {
				buffer.append(entry.getValue());
			}
			buffer.append(",");
		}
		if (buffer.length() > 0) {
			buffer.deleteCharAt(buffer.length() - 1);
		}
		buffer.append("}");
		
		try {
			response.getWriter().write(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void response(String str) {
		HttpServletResponse response = getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
			}
		}
		
	}
	
}

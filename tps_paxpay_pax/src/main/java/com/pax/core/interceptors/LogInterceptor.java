package com.pax.core.interceptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.pax.auth.entity.OperateLog;
import com.pax.auth.entity.OperateLogParam;
import com.pax.auth.service.OperateLogService;
import com.pax.core.exception.BusinessException;
import com.pax.core.util.DateUtils;
import com.pax.core.util.TranslationUtils;
import com.pax.core.util.WebUtils;

/**
 * 系统使用者操作日志拦截器（生成器）
 * 
 * 
 * 
 */
public class LogInterceptor implements HandlerInterceptor {
	
	private List<String>		ignores				= new ArrayList<String>();
	
	/** 忽略请求参数名 */
	private List<String>		ignoreParameters	= new ArrayList<String>();
	
	private OperateLogService	operateLogService;
	
	/**
	 * 在controller后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
								Object handler, Exception exception) throws Exception {
		
		//String requestPath = request.getRequestURI();// 用户访问的资源地址，类似于/tps/user/main
		//得到main
		//int methodName_index = requestPath.lastIndexOf("/");
		//String methodName = requestPath.substring(methodName_index + 1);
		//methodName_index = requestPath.indexOf(methodName);
		//得到user
		//String newPath = requestPath.substring(0, methodName_index - 1);
		//int controllerName_index = newPath.lastIndexOf("/");
		//String controllerName = newPath.substring(controllerName_index + 1);
		
		if (handler instanceof HandlerMethod) {
			
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			
			String methodName = handlerMethod.getMethod().getName();
			String controllerName = handlerMethod.getBeanType().getSimpleName();
			
			if (!isIgnore(controllerName + "." + methodName, ignores)) {
				
				OperateLog log = new OperateLog();
				
				//设置操作者
				String loginuser = WebUtils.getUserLoginname();
				
				if (StringUtils.isBlank(loginuser)) {
					return;
				}
				log.setOperator(loginuser);
				
				//设置操作时间
				log.setOperatetime(DateUtils.getCurrentDateString());
				
				//设置操作名称
				log.setName(controllerName + "." + methodName);
				
				String errorMsg = (String) request.getAttribute("invokeFailureMessage");
				if (StringUtils.isNotBlank(errorMsg)) {
					if (errorMsg.length() > 255)
						errorMsg = errorMsg.substring(0, 250);
					log.setDescription(errorMsg);
					log.setFlag("1");
				} else {
					log.setDescription(TranslationUtils.getInstance(com.pax.core.util.WebUtils.getSession() == null ? null : (Locale) com.pax.core.util.WebUtils.getSession().getAttribute("locale")).__("操作成功"));
					log.setFlag("0");
				}
				
				bindLogParams(request, log);
				
				doLog(log);
			}
			
		} else if (handler instanceof DefaultServletHttpRequestHandler) {
			//静态资源不做任何处理
		} else {
			//静态资源不做任何处理
		}
		
	}
	
	private void bindLogParams(HttpServletRequest request, OperateLog log) throws IOException {
		
		List<OperateLogParam> params = log.getParams();
		
		Enumeration paramEnum = request.getParameterNames();
		
		do {
			
			if (!paramEnum.hasMoreElements()) {
				break;
			}
			
			String param = (String) paramEnum.nextElement();
			
			if (!isIgnore(param, ignoreParameters)) {
				String values[] = request.getParameterValues(param);
				if (values.length > 0) {
					String arrs[] = values;
					for (int i = 0; i < arrs.length; i++) {
						String value = arrs[i];
						OperateLogParam lp = new OperateLogParam();
						lp.setName(param);
						lp.setVal(value);
						params.add(lp);
					}
				}
			}
		} while (true);
	}
	
	private void doLog(OperateLog log) {
		try {
			operateLogService.save(log);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isIgnore(String str, List<String> ignoreList) {
		for (Iterator<String> i$ = ignoreList.iterator(); i$.hasNext();) {
			String ignoreStr = i$.next();
			if (isLike(ignoreStr, str)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isLike(String srcIncludeStar, String dest) {
		
		if (dest.contains(".")) {
			String srcController = srcIncludeStar.split("\\.")[0];
			String srcMethod = srcIncludeStar.split("\\.")[1];
			
			String destController = dest.split("\\.")[0];
			String destMethod = dest.split("\\.")[1];
			
			boolean controllerFlag = handleMatch(srcController, destController);
			boolean methodFlag = handleMatch(srcMethod, destMethod);
			
			if (controllerFlag && methodFlag) {
				return true;
			} else {
				return false;
			}
		} else {
			return handleMatch(srcIncludeStar, dest);
		}
		
	}
	
	private boolean handleMatch(String pattern, String destStr) {
		if ("*".equals(pattern)) {
			return true;
		} else {
			//查看pattern中是否包含*
			//如果包含,并且在第一位
			if (pattern.indexOf("*") == 0) {
				//destStr中是否包含pattern中除去*的其他部分
				int position1 = destStr.indexOf(pattern.substring(1, pattern.length()));
				int position2 = destStr.length() - (pattern.length() - 1);
				
				if (position1 == position2) {
					return true;
				} else {
					return false;
				}
				//如果包含,并且在最后一位
			} else if (pattern.indexOf("*") == pattern.length() - 1) {
				String pattern2 = pattern.substring(0, pattern.length() - 1);
				int position = destStr.indexOf(pattern2);
				if (position == 0) {
					return true;
				} else {
					return false;
				}
				//否则就必须完全相等
			} else {
				return pattern.equals(destStr);
			}
		}
	}
	
	public void postHandle(	HttpServletRequest request, HttpServletResponse response, Object object,
							ModelAndView modelAndView) throws Exception {
		
	}
	
	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(	HttpServletRequest request, HttpServletResponse response,
								Object object) throws Exception {
		
		return true;
		
	}
	
	public List<String> getIgnoreParameters() {
		return ignoreParameters;
	}
	
	public void setIgnoreParameters(List<String> ignoreParameters) {
		this.ignoreParameters = ignoreParameters;
	}
	
	public OperateLogService getOperateLogService() {
		return operateLogService;
	}
	
	public void setOperateLogService(OperateLogService operateLogService) {
		this.operateLogService = operateLogService;
	}
	
	public List<String> getIgnores() {
		return ignores;
	}
	
	public void setIgnores(List<String> ignores) {
		this.ignores = ignores;
	}
	
	public static void main(String[] args) {
		
	}
	
}

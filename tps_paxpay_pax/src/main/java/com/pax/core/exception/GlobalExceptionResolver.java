package com.pax.core.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.pax.core.util.TranslationUtils;
import com.pax.core.util.WebUtils;

/**
 * spring mvc 全局处理异常捕获 根据请求区分ajax和普通请求，分别进行响应.
 * 第一、异常信息输出到日志中。
 * 第二、截取异常详细信息的前50个字符，写入日志表中t_si_log。
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	
	//记录日志信息
	private static final Logger	log					= LoggerFactory
		.getLogger(GlobalExceptionResolver.class);
	//记录数据库最大字符长度
	private static final int	WIRTE_DB_MAX_LENGTH	= 1500;
	//记录数据库最大字符长度
	private static final short	LOG_LEVEL			= 6;
	//记录数据库最大字符长度
	private static final short	LOG_OPT				= 3;
	
	/**
	 * 对异常信息进行统一处理，区分异步和同步请求，分别处理
	 */
	public ModelAndView resolveException(	HttpServletRequest request, HttpServletResponse response,
											Object handler, Exception ex) {
		boolean isajax = isAjax(request, response);
		Throwable deepestException = deepestException(ex);
		return processException(request, response, handler, deepestException, isajax);
	}
	
	/**
	 * 判断当前请求是否为异步请求.
	 */
	private boolean isAjax(HttpServletRequest request, HttpServletResponse response) {
		return StringUtils.isNotEmpty(request.getHeader("X-Requested-With"));
	}
	
	/**
	 * 获取最原始的异常出处，即最初抛出异常的地方
	 */
	private Throwable deepestException(Throwable e) {
		Throwable tmp = e;
		int breakPoint = 0;
		while (tmp.getCause() != null) {
			if (tmp.equals(tmp.getCause())) {
				break;
			}
			tmp = tmp.getCause();
			breakPoint++;
			if (breakPoint > 1000) {
				break;
			}
		}
		return tmp;
	}
	
	/**
	 * 处理异常.
	 * @param request
	 * @param response
	 * @param handler
	 * @param isajax
	 * @return
	 */
	private ModelAndView processException(	HttpServletRequest request, HttpServletResponse response,
											Object handler, Throwable ex, boolean isajax) {
		//步骤一、异常信息记录到日志文件中.
		log.error("全局处理异常捕获:", ex);
		//步骤二、异常信息记录截取前50字符写入数据库中.
		logDb(ex);
		//步骤三、分普通请求和ajax请求分别处理.
		if (isajax) {
			return processAjax(request, response, handler, ex);
		} else {
			return processNotAjax(request, response, handler, ex);
		}
	}
	
	/**
	 * 异常信息记录截取前50字符写入数据库中
	 * @param ex
	 */
	private void logDb(Throwable ex) {
		
		String exceptionMessage = "错误异常: "	+ ex.getClass().getSimpleName() + ",错误描述："
									+ ex.getMessage();
		if (StringUtils.isNotEmpty(exceptionMessage)) {
			if (exceptionMessage.length() > WIRTE_DB_MAX_LENGTH) {
				exceptionMessage = exceptionMessage.substring(0, WIRTE_DB_MAX_LENGTH);
			}
		}
		
		//将错误信息保存到数据库
	}
	
	/**
	 * ajax异常处理并返回.
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return
	 */
	private ModelAndView processAjax(	HttpServletRequest request, HttpServletResponse response,
										Object handler, Throwable ex) {
		ModelAndView empty = new ModelAndView();
		Locale locale = WebUtils.getSession() == null ? null
			: (Locale) WebUtils.getSession().getAttribute("locale");
		//没得权限访问
		if (ex instanceof AuthorizationException) {
			request.setAttribute("invokeFailureMessage",
				TranslationUtils.getInstance(locale).__("你没有权限访问该功能"));
			WebUtils.responseFailString(TranslationUtils.getInstance(locale).__("你没有权限访问该功能"));
		} else if (ex instanceof BusinessException) {
			request.setAttribute("invokeFailureMessage",
				TranslationUtils.getInstance(locale).__(ex.getMessage()));
			WebUtils.responseFailString(TranslationUtils.getInstance(locale).__(ex.getMessage()));
		} else {
			request.setAttribute("invokeFailureMessage",
				TranslationUtils.getInstance(locale).__("系统异常"));
			WebUtils.responseFailString(TranslationUtils.getInstance(locale).__("系统异常"));
		}
		
		empty.clear();
		return empty;
	}
	
	/**
	 * 普通页面异常处理并返回.
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 */
	private ModelAndView processNotAjax(HttpServletRequest request, HttpServletResponse response,
										Object handler, Throwable ex) {
		//没得权限访问
		if (ex instanceof AuthorizationException) {
			request.setAttribute("invokeFailureMessage", "你没有权限访问该功能");
			return new ModelAndView("common/unauth");
		} else {
			
			String exceptionMessage = getThrowableMessage(ex);
			
			request.setAttribute("invokeFailureMessage", exceptionMessage);
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("exceptionMessage", exceptionMessage);
			model.put("ex", ex);
			return new ModelAndView("common/error", model);
		}
		
	}
	
	/**
	 * 返回错误信息字符串
	 *
	 * @param ex
	 *            Exception
	 * @return 错误信息字符串
	 */
	public String getThrowableMessage(Throwable ex) {
		Locale locale = WebUtils.getSession() == null ? null
			: (Locale) WebUtils.getSession().getAttribute("locale");
		
		if (ex instanceof BusinessException) {
			return TranslationUtils.getInstance(locale).__(ex.getMessage());
		} else {
			return TranslationUtils.getInstance(locale).__("系统异常");
		}
		/*StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString();*/
	}
}

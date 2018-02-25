package com.pax.auth.shiro.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pax.auth.entity.User;

import net.sf.json.JSONObject;
import net.spy.memcached.MemcachedClient;

public class KickoutSessionControlFilter extends AccessControlFilter {
	
	private final static Logger	log	= LoggerFactory.getLogger(KickoutSessionControlFilter.class);
	
	private String				kickoutUrl;															//踢出后到的地址  
	private boolean				kickoutAfter;														//踢出之前登录的/之后登录的用户 默认踢出之前登录的用户  
	private int					maxSession;															//同一个帐号最大会话数 默认1  
	private SessionManager		sessionManager;
	private MemcachedClient		client;
	
	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}
	
	public void setKickoutAfter(boolean kickoutAfter) {
		this.kickoutAfter = kickoutAfter;
	}
	
	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}
	
	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}
	
	public void setClient(MemcachedClient client) {
		this.client = client;
	}
	
	/** 
	 * 是否允许访问，返回true表示允许 
	 */
	@Override
	protected boolean isAccessAllowed(	ServletRequest request, ServletResponse response,
										Object mappedValue) throws Exception {
		return false;
	}
	
	/** 
	 * 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）。 
	 */
	@Override
	protected boolean onAccessDenied(	ServletRequest request,
										ServletResponse response) throws Exception {
		
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Serializable sessionId = session.getId();
		
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			//如果没有登录，直接进行之后的流程  
			return true;
		}
		
		String username = ((User) subject.getPrincipal()).getLoginname();
		
		// 初始化用户的队列放到缓存里  
		Deque<Serializable> deque = (Deque<Serializable>) client.get(username);
		if (deque == null || deque.size() == 0) {
			deque = new LinkedList<Serializable>();
			client.set(username, 0, deque);
		}
		
		//如果队列里没有此sessionId，且用户没有被踢出；放入队列  
		if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
			deque.push(sessionId);
			client.replace(username, 0, deque);
		}
		
		//如果队列里的sessionId数超出最大会话数，开始踢人  
		while (deque.size() > maxSession) {
			Serializable kickoutSessionId = null;
			if (kickoutAfter) { //如果踢出后者  
				kickoutSessionId = deque.getFirst();
				kickoutSessionId = deque.removeFirst();
				client.replace(username, 0, deque);
			} else { //否则踢出前者  
				kickoutSessionId = deque.removeLast();
				client.replace(username, 0, deque);
			}
			try {
				Session kickoutSession = sessionManager
					.getSession(new DefaultSessionKey(kickoutSessionId));
				if (kickoutSession != null) {
					//设置会话的kickout属性表示踢出了  
					kickoutSession.setAttribute("kickout", true);
				}
			} catch (Exception e) {//ignore exception  
				//获取session出错，说明session早就过期了
				deque.remove(sessionId);
				return true;
			}
		}
		
		//如果被踢出了，直接退出，重定向到踢出后的地址  
		if (session.getAttribute("kickout") != null) {
			//会话被踢出了  
			try {
				subject.logout();
			} catch (Exception e) {
				log.error("强制用户退出时出错", e);
			}
			
			boolean isajax = isAjax((HttpServletRequest) request, (HttpServletResponse) response);
			
			if (isajax) {
				ajaxHandle(response);
			} else {
				WebUtils.issueRedirect(request, response, kickoutUrl);
			}
			
			return false;
		}
		
		return true;
	}
	
	private void ajaxHandle(ServletResponse response) {
		JSONObject json = new JSONObject();
		json.put("success", "false");
		json.put("msg", "");
		json.put("status", "302");
		json.put("location", kickoutUrl);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		((HttpServletResponse) response).setHeader("Cache-Control", "no-store");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}
	
	private boolean isAjax(HttpServletRequest request, HttpServletResponse response) {
		return StringUtils.isNotEmpty(request.getHeader("X-Requested-With"));
	}
	
}

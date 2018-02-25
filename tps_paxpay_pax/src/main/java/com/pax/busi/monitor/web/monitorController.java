package com.pax.busi.monitor.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pax.auth.entity.Organization;
import com.pax.auth.service.OrganizationService;
import com.pax.busi.common.entity.Chintoeng;
import com.pax.busi.common.entity.Inntransdef;
import com.pax.busi.common.service.CommonService;
import com.pax.core.util.ApplicationContextUtil;
import com.pax.core.util.TranslationUtils;

import net.sf.json.JSONObject;
import net.spy.memcached.MemcachedClient;

@Controller
@RequestMapping("/monitor")
public class monitorController {
	
	protected final Logger				logger	= LoggerFactory.getLogger(getClass());
	
	@Resource
	private OrganizationService			organizationService;
	
	@Resource
	private CommonService				commonService;
	
	private static List<Inntransdef>	inntransdefList;
	
	private static List<Organization>	orgList;
	
	private static List<Chintoeng>		chintoengList;
	
	@RequiresPermissions("monitor:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		List<Organization> list = organizationService.list(new HashMap<String, Object>());
		StringBuffer buffer = new StringBuffer();
		for (Organization organization : list) {
			buffer.append(organization.getId() + "_");
		}
		
		session.setAttribute("orgs", buffer.substring(0, buffer.length() - 1));
		
		return "busi/monitor/transMonitor";
	}
	
	@RequestMapping(value = "/send")
	public void send(HttpServletRequest req, HttpServletResponse resp) {
		
		if (inntransdefList == null) {
			inntransdefList = commonService.listAllInntransdef();
		}
		
		if (orgList == null) {
			orgList = organizationService.listAllOfSite(99999);
		}
		
		if (chintoengList == null) {
			chintoengList = commonService.listChintoeng();
		}
		
		String queryString = req.getQueryString();
		
		logger.info("收到监控数据：" + queryString);
		
		String result = "ok";
		
		try {
			
			String p_subject = req.getParameter("p_subject");
			
			if (StringUtils.isNotBlank(p_subject)) {
				MemcachedClient memcachedClient = (MemcachedClient) ApplicationContextUtil
					.getContext().getBean("memcachedClient");
				
				JSONObject jsonObject = new JSONObject();
				
				String DEPID = req.getParameter("DEPID");
				if (StringUtils.isBlank(DEPID)) {
					DEPID = "";
				}
				String TRNAME = req.getParameter("TRNAME");//传递的是交易名称
				if (StringUtils.isBlank(TRNAME)) {
					TRNAME = "";
				}
				
				String RSPINFO = req.getParameter("RSPINFO");
				if (StringUtils.isBlank(RSPINFO)) {
					RSPINFO = "";
				}
				
				String RSPCODE = req.getParameter("RSPCODE");
				if (StringUtils.isBlank(RSPCODE)) {
					RSPCODE = "";
				}
				String MID = req.getParameter("MID");
				if (StringUtils.isBlank(MID)) {
					MID = "";
				}
				String TID = req.getParameter("TID");
				if (StringUtils.isBlank(TID)) {
					TID = "";
				}
				String CARDNO = req.getParameter("CARDNO");
				if (StringUtils.isBlank(CARDNO)) {
					CARDNO = "";
				}
				String AMT = req.getParameter("AMT");
				if (StringUtils.isBlank(AMT)) {
					AMT = "";
				}
				String TRACENO = req.getParameter("TRACENO");
				if (StringUtils.isBlank(TRACENO)) {
					TRACENO = "";
				}
				String OUT_TRADE_NO = req.getParameter("OUT_TRADE_NO");
				if (StringUtils.isBlank(OUT_TRADE_NO)) {
					OUT_TRADE_NO = "";
				}
				String BATCHNO = req.getParameter("BATCHNO");
				if (StringUtils.isBlank(BATCHNO)) {
					BATCHNO = "";
				}
				String DATETIME = req.getParameter("DATETIME");
				if (StringUtils.isBlank(DATETIME)) {
					DATETIME = "";
				}
				String CONSUME = req.getParameter("CONSUME");
				if (StringUtils.isBlank(CONSUME)) {
					CONSUME = "";
				}
				String AUTHNO = req.getParameter("AUTHNO");
				if (StringUtils.isBlank(AUTHNO)) {
					AUTHNO = "";
				}
				String REFNO = req.getParameter("SYSID");
				if (StringUtils.isBlank(REFNO)) {
					REFNO = "";
				}
				
				String CURRENCY = req.getParameter("CURRENCY");
				if (StringUtils.isBlank(CURRENCY)) {
					CURRENCY = "";
				}
				
				String SYSID = req.getParameter("SYSID");
				if (StringUtils.isBlank(SYSID)) {
					SYSID = "";
				}
				
				jsonObject.put("DEPID", DEPID);
				jsonObject.put("RSPINFO", RSPINFO);
				jsonObject.put("RSPCODE", RSPCODE);
				jsonObject.put("MID", MID);
				jsonObject.put("TID", TID);
				jsonObject.put("CARDNO", CARDNO);
				jsonObject.put("AMT", AMT);
				jsonObject.put("TRACENO", TRACENO);
				jsonObject.put("OUT_TRADE_NO", OUT_TRADE_NO);
				jsonObject.put("BATCHNO", BATCHNO);
				jsonObject.put("DATETIME", DATETIME);
				jsonObject.put("CONSUME", CONSUME);
				jsonObject.put("AUTHNO", AUTHNO);
				jsonObject.put("REFNO", REFNO);
				jsonObject.put("CURRENCY", CURRENCY);
				jsonObject.put("TRNAME", TRNAME);
				jsonObject.put("SYSID", SYSID);
				
				String TRNAME_EN = TranslationUtils.getInstance(new Locale("en", "US")).__(TRNAME);
				
				jsonObject.put("TRNAME_EN", TRNAME_EN);
				
				for (Organization organization : orgList) {
					if (String.valueOf(organization.getId()).equals(DEPID)) {
						jsonObject.put("DEPNAME", organization.getName());
					}
				}
				
				String RSPINFO_EN = RSPINFO;
				
				for (Chintoeng chintoeng : chintoengList) {
					if (chintoeng.getChinmsg().equals(RSPINFO)) {
						RSPINFO_EN = chintoeng.getEngmsg();
						break;
					}
				}
				
				jsonObject.put("RSPINFO_EN", RSPINFO_EN);
				
				// 在存的时候，不能取
				synchronized (memcachedClient) {
					
					Map<String, Deque<JSONObject>> map;
					
					Object object = memcachedClient.get(p_subject);
					
					if (object == null) {
						logger.info("缓存里面的map是null，新建一个空map");
						map = new HashMap<String, Deque<JSONObject>>();
						Deque<JSONObject> deque = new LinkedList<JSONObject>();
						deque.add(jsonObject);
						map.put(DEPID, deque);
						memcachedClient.set(p_subject, 0, map);
						logger.info("放入缓存成功");
					} else {
						map = (Map<String, Deque<JSONObject>>) object;
						if (map.containsKey(DEPID)) {
							
							Deque<JSONObject> deque = map.get(DEPID);
							
							int size = deque.size();
							
							if (size == 40) {
								deque.removeFirst();
								
								deque.add(jsonObject);
								
								map.put(DEPID, deque);
								
								memcachedClient.replace(p_subject, 0, map);
								logger.info("放入缓存成功");
							} else {
								
								deque.add(jsonObject);
								
								map.put(DEPID, deque);
								
								memcachedClient.replace(p_subject, 0, map);
								logger.info("放入缓存成功");
							}
						} else {
							Deque<JSONObject> deque = new LinkedList<JSONObject>();
							deque.add(jsonObject);
							
							map.put(DEPID, deque);
							
							memcachedClient.replace(p_subject, 0, map);
							logger.info("放入缓存成功");
							
						}
					}
					
				}
			}
			
		} catch (Exception e) {
			logger.error("监控中处理数据出错", e);
			resp.setStatus(500);
			result = "fail";
		}
		
		PrintWriter writer = null;
		try {
			writer = resp.getWriter();
		} catch (IOException e) {
			logger.error("监控中处理数据出错", e);
		}
		
		writer.write(result);
		writer.flush();
		
		logger.info("返回结果：" + result);
		
	}
	
}

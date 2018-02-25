package com.pax.busi.monitor.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pax.busi.monitor.websocket.TransMonitor;
import com.pax.core.util.ApplicationContextUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.spy.memcached.MemcachedClient;

@Service
public class MessageTimerTask {
	
	protected final Logger	logger		= LoggerFactory.getLogger(getClass());
	
	private final String[]	p_subjects	= { "cpos" };
	
	public void execute() {
		try {
			
			//先判断是否有客户端在监控交易数据,没有客户端就不往下面执行
			if(MapUtils.isEmpty(TransMonitor.connections)){
				return;
			}
			
			//logger.info("读取消息...");
			
			MemcachedClient memcachedClient = (MemcachedClient) ApplicationContextUtil.getContext()
				.getBean("memcachedClient");
			
			//memcachedClient.delete("cpos");
			
			for (String p_subject : p_subjects) {
				
				// 取的时候，不能存
				synchronized (memcachedClient) {
					
					Object object = memcachedClient.get(p_subject);
					
					//memcachedClient.delete(p_subject);
					
					if (object != null) {
						
						Map<String, Deque<JSONObject>> map = (Map<String, Deque<JSONObject>>) object;
						
						//遍历所有的连接
						for (Map.Entry<String, Session> entry : TransMonitor.connections
							.entrySet()) {
							
							//对jsonArray根据交易时间排序
							List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
							
							String sessionId = entry.getKey();
							Session session = entry.getValue();
							
							List<String> allOrgs = TransMonitor.allOrgs.get(sessionId);
							List<String> sends = TransMonitor.hasSends.get(sessionId);
							
							for (String org : allOrgs) {
								
								//遍历缓存里面的map key=org,value=交易记录
								for (Map.Entry<String, Deque<JSONObject>> entry2 : map.entrySet()) {
									String org2 = entry2.getKey();
									Deque<JSONObject> deque = entry2.getValue();
									if (org.equals(org2)) {
										
										//遍历所有的交易记录
										Iterator<JSONObject> iterator = deque.iterator();
										while (iterator.hasNext()) {
											JSONObject jsonObject = iterator.next();
											String REFNO = (String) jsonObject.get("REFNO");
											//检查哪些记录已经发送过
											if (sends.contains(REFNO)) {
												//已经发送过的就不再进行处理
											} else {
												jsonObjects.add(jsonObject);
												sends.add(REFNO);
											}
										}
										
									}
								}
							}
							
							if (jsonObjects.size() > 0) {
								
								Collections.sort(jsonObjects, new Comparator() {
									@Override
									public int compare(Object o1, Object o2) {
										if (o1 instanceof JSONObject && o2 instanceof JSONObject) {
											JSONObject e1 = (JSONObject) o1;
											JSONObject e2 = (JSONObject) o2;
											
											return e2.get("SYSID").toString()
												.compareTo(e1.get("SYSID").toString());
											
										}
										throw new ClassCastException("不能转换为JSONObject类型");
									}
								});
								
								JSONArray jsonArray = new JSONArray();
								int size = 40;
								//只取前面40个,顺序：后收到的，在前面
								if (jsonObjects.size() < size) {
									size = jsonObjects.size();
								}
								for (int i = 0; i < size; i++) {
									jsonArray.add(jsonObjects.get(i));
								}
								session.getBasicRemote().sendText(jsonArray.toString());//
							}
							
						}
						
					} else {
						//logger.info("没有读取到消息...");
					}
					
				}
			}
			
		} catch (
		
		Exception e) {
			logger.error("消息通知的定时任务出错", e);
		} catch (Throwable e) {
			logger.error("消息通知的定时任务出错", e);
		}
	}
	
	public static void main(String[] args) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		JSONObject o1 = new JSONObject();
		o1.put("DATETIME", "12:02:34");
		JSONObject o2 = new JSONObject();
		o2.put("DATETIME", "11:03:24");
		JSONObject o3 = new JSONObject();
		o3.put("DATETIME", "15:05:44");
		jsonObjects.add(o1);
		jsonObjects.add(o2);
		jsonObjects.add(o3);
		
		Collections.sort(jsonObjects, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if (o1 instanceof JSONObject && o2 instanceof JSONObject) {
					JSONObject e1 = (JSONObject) o1;
					JSONObject e2 = (JSONObject) o2;
					
					return e2.get("DATETIME").toString().compareTo(e1.get("DATETIME").toString());
					
				}
				throw new ClassCastException("不能转换为JSONObject类型");
			}
		});
		
		System.out.println(jsonObjects);
	}
}

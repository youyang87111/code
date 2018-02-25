package com.pax.busi.monitor.websocket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint("/transMonitor/{p_subject}/{orgs}")
public class TransMonitor {
	
	protected final Logger							logger		= LoggerFactory
		.getLogger(getClass());
	
	private Session									session;
	private String									sessionId;
	
	// 保存连接的MAP容器
	public static final Map<String, Session>		connections	= new HashMap<String, Session>();
	public static final Map<String, List<String>>	allOrgs		= new HashMap<String, List<String>>();
	public static final Map<String, List<String>>	hasSends	= new HashMap<String, List<String>>();
	
	/**
	 * 建立连接时调用,可传入的参数Session(WebSocket的Session,在SpringWebSocket里面则是WebSocketSession),
	 * EndpointConfig,和带@PathParam注的参数
	 * @param session
	 * @param config
	 */
	@OnOpen
	public void onOpen(	@PathParam("p_subject") String p_subject, @PathParam("orgs") String orgs,
						Session session, EndpointConfig config) {
		
		this.session = session;
		this.session.setMaxIdleTimeout(8 * 60 * 60 * 1000l);
		sessionId = session.getId() + "_" + p_subject;
		
		if (StringUtils.isNotBlank(orgs)) {
			String[] ss = orgs.split("_");
			List<String> list = Arrays.asList(ss);
			allOrgs.put(sessionId, list);
		} else {
			allOrgs.put(sessionId, new ArrayList<String>());
		}
		
		connections.put(sessionId, session);
		
		List<String> sends = new ArrayList<String>();
		
		hasSends.put(sessionId, sends);
	}
	
	/**
	 * 消息到来时调用,可传入的参数Session(WebSocket的Session,在SpringWebSocket里面则是WebSocketSession),
	 * 根据消息的形式，如果是文本消息，传入String类型参数或者Reader，如果是二进制消息，传入byte[]类型参数或者InputStream，
	 * 如果Pong消息传入实现了PongMessage接口的类型参数，方法还可以带返回类型，如果有返回类型，比如String（还可以是byte[]或ByteBuffer）,
	 * 则是立即向客户端发送String消息，如果是分片消息（消息有whole，partial两种，全部消息和分片消息），
	 * 还可传入boolean类型的参数，判断是否到了分片的结尾。
	 * @param session
	 * @param message
	 * @param isLast
	 */
	@OnMessage //处理文本消息
	public void onMessage(Session session, String message, boolean isLast) {
		
	}
	
	/**
	 * 处理二进制消息,参数随便搭配
	 * @param session
	 * @param message
	 * @param isLast
	 */
	@OnMessage
	public void onMessage(Session session, byte[] message, boolean isLast) {
		
	}
	
	/**
	 * 传输发生错误时调用,可传入参数Session,Throwable
	 * @param session
	 * @param t
	 */
	@OnError
	public void onError(Session session, Throwable t) {
		
	}
	
	/**
	 * 连接关闭时调用,可能一方主动关闭，或者连接超时的关闭
	 * @param cr
	 */
	@OnClose
	public void onClose(CloseReason cr) {
		
		logger.info(sessionId + "断开连接");
		
		connections.remove(this.sessionId);
		allOrgs.remove(this.sessionId);
		hasSends.remove(sessionId);
	}
}

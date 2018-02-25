package com.pax.auth.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.spy.memcached.MemcachedClient;

public class SessionDAO extends AbstractSessionDAO {
	
	private final static Logger	log	= LoggerFactory.getLogger(SessionDAO.class);
	
	private MemcachedClient		client;
	
	public SessionDAO(MemcachedClient client) {
		if (client == null) {
			throw new RuntimeException("必须存在memcached客户端实例");
		}
		this.client = client;
	}
	
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		try {
			client.set(sessionId.toString(), (int) session.getTimeout() / 1000, session);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return sessionId;
	}
	
	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = null;
		try {
			session = (Session) client.get(sessionId.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return session;
	}
	
	@Override
	public void delete(Session session) {
		try {
			client.delete(session.getId().toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Override
	public Collection<Session> getActiveSessions() {
		return Collections.emptySet();
	}
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		try {
			client.replace(session.getId().toString(), (int) session.getTimeout() / 1000, session);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}

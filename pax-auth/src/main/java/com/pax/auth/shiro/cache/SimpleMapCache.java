package com.pax.auth.shiro.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;

import net.spy.memcached.MemcachedClient;

@SuppressWarnings("all")
public class SimpleMapCache implements Cache<Object, Object>, Serializable {
	
	private MemcachedClient memcachedClient;
	
	public SimpleMapCache(MemcachedClient memcachedClient) {
		if (memcachedClient == null) {
			throw new RuntimeException("必须存在memcached客户端实例");
		}
		this.memcachedClient = memcachedClient;
		
		Object object = memcachedClient.get("web-simpleMapCache");
		
		if (object == null) {
			Map<Object, Object> attributes = new HashMap<Object, Object>();
			this.memcachedClient.add("web-simpleMapCache", 0, attributes);
		}
		
	}
	
	public Object get(Object key) throws CacheException {
		
		Map<Object, Object> attributes = (Map<Object, Object>) memcachedClient
			.get("web-simpleMapCache");
		
		return attributes.get(key);
	}
	
	public Object put(Object key, Object value) throws CacheException {
		Map<Object, Object> attributes = (Map<Object, Object>) memcachedClient
			.get("web-simpleMapCache");
		Object object = attributes.put(key, value);
		memcachedClient.replace("web-simpleMapCache", 0, attributes);
		
		return object;
	}
	
	public Object remove(Object key) throws CacheException {
		Map<Object, Object> attributes = (Map<Object, Object>) memcachedClient
			.get("web-simpleMapCache");
		Object object = attributes.remove(key);
		memcachedClient.replace("web-simpleMapCache", 0, attributes);
		
		return object;
	}
	
	public void clear() throws CacheException {
		Map<Object, Object> attributes = (Map<Object, Object>) memcachedClient
			.get("web-simpleMapCache");
		attributes.clear();
		memcachedClient.replace("web-simpleMapCache", 0, attributes);
	}
	
	public int size() {
		Map<Object, Object> attributes = (Map<Object, Object>) memcachedClient
			.get("web-simpleMapCache");
		return attributes.size();
	}
	
	public Set<Object> keys() {
		Map<Object, Object> attributes = (Map<Object, Object>) memcachedClient
			.get("web-simpleMapCache");
		Set<Object> keys = attributes.keySet();
		if (!keys.isEmpty())
			return Collections.unmodifiableSet(keys);
		else
			return Collections.emptySet();
	}
	
	public Collection<Object> values() {
		Map<Object, Object> attributes = (Map<Object, Object>) memcachedClient
			.get("web-simpleMapCache");
		Collection<Object> values = attributes.values();
		if (!CollectionUtils.isEmpty(values))
			return Collections.unmodifiableCollection(values);
		else
			return Collections.emptySet();
	}
	
	public String toString() {
		Map<Object, Object> attributes = (Map<Object, Object>) memcachedClient
			.get("web-simpleMapCache");
		return (new StringBuilder("web-simpleMapCache (")).append(attributes.size())
			.append(" entries)").toString();
	}
}

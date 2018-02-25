package com.pax.auth.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

public class ShiroCacheManager implements CacheManager, Destroyable {
	
	private SimpleCacheManager simpleCacheManager;
	
	@Override
	public Cache<Object, Object> getCache(String name) throws CacheException {
		return simpleCacheManager.getCache(name);
	}
	
	@Override
	public void destroy() throws Exception {
		simpleCacheManager.destroy();
	}
	
	public SimpleCacheManager getSimpleCacheManager() {
		return simpleCacheManager;
	}
	
	public void setSimpleCacheManager(SimpleCacheManager simpleCacheManager) {
		this.simpleCacheManager = simpleCacheManager;
	}
}

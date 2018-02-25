package com.pax.auth.dao;

import java.util.List;
import java.util.Map;

import com.pax.auth.entity.Site;
import com.pax.common.model.PageQueryParam;

public interface SiteDao {
	
	void save(Map<String, Object> filterMap);
	
	List<Site> list(PageQueryParam pageQueryParam);
	
	List<Site> listAll();
	
	Site get(String id);
	
	int getNextId();
	
	void update(Map<String, Object> filterMap);
	
	void frozen(String id);
	
	void unfrozen(String id);
	
	void delete(String id);
}

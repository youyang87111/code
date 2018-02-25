package com.pax.auth.dao;

import java.util.List;
import java.util.Map;

import com.pax.auth.entity.Function;
import com.pax.core.model.PageQueryParam;

public interface FunctionDao {
	
	void save(Map<String, Object> filterMap);
	
	List<Function> list(PageQueryParam pageQueryParam);
	
	Function get(String id);
	
	int getNextId();
	
	void update(Map<String, Object> filterMap);
	
	void delete(String id);
	
	List<Function> getFuncsByAuth(String id);
	
	int getAuthsCount(String id);
	
}

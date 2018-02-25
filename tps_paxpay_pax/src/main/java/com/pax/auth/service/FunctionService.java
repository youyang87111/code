package com.pax.auth.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.Function;
import com.pax.core.model.PageQueryParam;

public interface FunctionService {
	
	public void save(Map<String, Object> filterMap);
	
	public PageInfo<Function> list(PageQueryParam pageQueryParam);
	
	public Function get(String id);
	
	public void update(Map<String, Object> filterMap);
	
	public void delete(String[] ids);
	
	public List<Function> getFuncsByAuth(String id);
	
}

package com.pax.auth.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.Site;
import com.pax.core.model.PageQueryParam;

public interface SiteService {
	
	public void save(Map<String, Object> filterMap);
	
	public PageInfo<Site> list(PageQueryParam pageQueryParam);
	
	public Site get(String id);
	
	public void update(Map<String, Object> filterMap);
	
	public void frozen(String[] ids);
	
	public void unfrozen(String[] ids);
	
	public void delete(String[] ids);
	
	public List<Site> listAll();
	
}

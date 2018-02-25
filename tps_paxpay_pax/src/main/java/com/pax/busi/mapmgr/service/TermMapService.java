package com.pax.busi.mapmgr.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pax.busi.mapmgr.entity.TermMapView;
import com.pax.busi.mapmgr.input.TermMapAddInput;
import com.pax.core.model.PageQueryParam;

public interface TermMapService {
	
	void save(TermMapAddInput input);
	
	PageInfo<TermMapView> list(PageQueryParam pageQueryParam);
	
	TermMapView get(String id);
	
	void frozen(String[] ids);
	
	void unfrozen(String[] ids);
	
	void delete(String[] ids);
	
	void audit(Map<String, Object> filterMap);
	
}

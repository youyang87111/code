package com.pax.busi.mapmgr.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pax.busi.mapmgr.entity.MerMap;
import com.pax.busi.mapmgr.input.MerMapAddInput;
import com.pax.busi.mapmgr.input.MerMapUpdateInput;
import com.pax.core.model.PageQueryParam;

public interface MerMapService {
	
	void save(MerMapAddInput input);
	
	void update(MerMapUpdateInput input);
	
	PageInfo<MerMap> list(PageQueryParam pageQueryParam);
	
	MerMap get(String id);
	
	void frozen(String[] ids);
	
	void unfrozen(String[] ids);
	
	void delete(String[] ids);
	
	void audit(Map<String, Object> filterMap);
	
}

package com.pax.busi.mapmgr.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pax.busi.mapmgr.entity.BranchMapView;
import com.pax.busi.mapmgr.input.BranchMapAddInput;
import com.pax.busi.mapmgr.input.BranchMapUpdateInput;
import com.pax.core.model.PageQueryParam;

public interface BranchMapService {
	
	void save(BranchMapAddInput addInput);
	
	PageInfo<BranchMapView> list(PageQueryParam pageQueryParam);
	
	BranchMapView get(String id);
	
	void frozen(String[] ids);
	
	void unfrozen(String[] ids);
	
	void delete(String[] ids);
	
	void audit(Map<String, Object> filterMap);
	
	void update(BranchMapUpdateInput input);
	
}

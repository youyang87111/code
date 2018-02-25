package com.pax.busi.mapmgr.dao;

import java.util.List;
import java.util.Map;

import com.pax.busi.mapmgr.entity.MerMap;
import com.pax.core.model.PageQueryParam;

public interface MerMapDao {
	
	MerMap getMerMapMode(String lbid, String lmid, String cls, String rbid);
	
	void saveMerMapMode(MerMap merMap);
	
	void save(MerMap merMap);
	
	List<MerMap> list(PageQueryParam pageQueryParam);
	
	MerMap get(String lbid, String lmid, String cls, String rbid, String mmf);
	
	void update(MerMap merMap);
	
	void frozenMerMapMode(String lbid, String lmid, String cls, String rbid);
	
	void frozen(String lbid, String lmid, String cls, String rbid, String mmf);
	
	void unfrozenMerMapMode(String lbid, String lmid, String cls, String rbid);
	
	void unfrozen(String lbid, String lmid, String cls, String rbid, String mmf);
	
	void deleteMerMapMode(String lbid, String lmid, String cls, String rbid);
	
	void delete(String lbid, String lmid, String cls, String rbid, String mmf);
	
	void audit(Map<String, Object> filterMap);
	
	void auditMerMapMode(Map<String, Object> filterMap);
	
	//在新增终端映射的时候，要查看商户映射是否存在
	List<MerMap> getMerMapModes(String lbid, String lmid, String rbid);
	
}

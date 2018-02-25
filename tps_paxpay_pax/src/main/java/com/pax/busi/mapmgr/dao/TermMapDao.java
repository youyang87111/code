package com.pax.busi.mapmgr.dao;

import java.util.List;
import java.util.Map;

import com.pax.busi.mapmgr.entity.TermMapView;
import com.pax.core.model.PageQueryParam;

public interface TermMapDao {
	
	TermMapView getTermMapMode(String lbid, String lmid, String rbid, String rmid);
	
	void saveTermMapMode(TermMapView termMapMode);
	
	TermMapView getTermMap(	String lbid, String lmcr, String lmid, String rbid, String rmcr,
							String rmid, String ltid, String rtid);
	
	void save(TermMapView termMap);
	
	List<TermMapView> list(PageQueryParam pageQueryParam);
	
	TermMapView get(String lbid, String lmid, String ltid, String rbid, String rmid, String rtid,
					String tmf);
	
	void frozen(TermMapView termMapView);
	
	void unfrozen(TermMapView termMapView);
	
	void deleteTermMapMode(TermMapView termMapView);
	
	void delete(TermMapView termMapView);
	
	void audit(Map<String, Object> filterMap);
	
	//查看一一映射里面还有记录没得，没得就可以删除mode了
	List<TermMapView> getTermMaps(TermMapView termMapView);
	
}

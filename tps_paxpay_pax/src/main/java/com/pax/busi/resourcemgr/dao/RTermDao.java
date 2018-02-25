package com.pax.busi.resourcemgr.dao;

import java.util.List;

import com.pax.busi.resourcemgr.entity.RTerm;
import com.pax.core.model.PageQueryParam;

public interface RTermDao {
	
	public RTerm get(String mcr, String mid, String tid);
	
	public List<RTerm> list(PageQueryParam pageQueryParam);
	
	public void update(RTerm rTerm);
	
	public void audit(RTerm rTerm);
	
	public void unfrozen(RTerm rTerm);
	
	public void frozen(RTerm rTerm);
	
	public void delete(RTerm rTerm);
	
	public void save(RTerm rTerm);
	
	public void updateTmk(RTerm rTerm);
	
	public void saveBySyn(RTerm rTerm);
	
	public void updateBySyn(RTerm tempRTerm);
	
}

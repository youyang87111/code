package com.pax.busi.resourcemgr.dao;

import java.util.List;

import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.core.model.PageQueryParam;

public interface CposMerDao {
	
	public void save(CposMer cposMer);
	
	public List<CposMer> list(PageQueryParam pageQueryParam);
	
	public CposMer get(String mcr, String mid);
	
	public void update(CposMer cposMer);
	
	public void audit(CposMer cposMer);
	
	public void frozen(CposMer cposMer);
	
	public void unfrozen(CposMer cposMer);
	
	public void delete(CposMer cposMer);
	
	public void saveBySyn(CposMer cposMer);
	
	public void updateBySyn(CposMer temp);
	
}

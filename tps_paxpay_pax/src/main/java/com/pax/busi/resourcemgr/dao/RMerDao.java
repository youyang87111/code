package com.pax.busi.resourcemgr.dao;

import java.util.List;

import com.pax.busi.resourcemgr.entity.RMer;
import com.pax.core.model.PageQueryParam;

public interface RMerDao {
	
	public RMer get(String mcr, String mid);
	
	public void save(RMer rMer);
	
	public List<RMer> list(PageQueryParam pageQueryParam);
	
	public void update(RMer rMer);
	
	public void audit(RMer rMer);
	
	public void frozen(RMer rMer);
	
	public void unfrozen(RMer rMer);
	
	public void delete(RMer rMer);
	
	public void saveBySyn(RMer temp);
	
	public void updateBySyn(RMer temp);
	
}

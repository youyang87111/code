package com.pax.busi.resourcemgr.dao;

import java.util.List;

import com.pax.busi.resourcemgr.entity.CposTerm;
import com.pax.core.model.PageQueryParam;

public interface CposTermDao {
	
	public CposTerm get(String mcr, String mid, String tid);
	
	public void save(CposTerm cposTerm);
	
	public void resetKey(CposTerm cposTerm);
	
	public List<CposTerm> list(PageQueryParam pageQueryParam);
	
	public void delete(CposTerm cposTerm);
	
	public void audit(CposTerm cposTerm);
	
	public void frozen(CposTerm cposTerm);
	
	public void unfrozen(CposTerm cposTerm);
	
	public List<CposTerm> listAll();
	
	public List<CposTerm> listBySyn(String mcr, String mid, String notEqualStaus);
	
	public void saveBySyn(CposTerm cposTerm);
	
	public void updateBySyn(CposTerm temp);
	
}

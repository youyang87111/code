package com.pax.busi.resourcemgr.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pax.busi.resourcemgr.entity.CposTerm;
import com.pax.busi.resourcemgr.input.CposTermAddInput;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
import com.pax.core.model.PageQueryParam;



public interface CposTermService {

	public void save(CposTermAddInput input);

	public CposTerm detail(String id);

	public PageInfo<CposTerm> list(PageQueryParam pageQueryParam);

	public void delete(String[] ids);

	public void audit(String id, String audit);

	public void frozen(String[] ids);

	public void unfrozen(String[] ids);

	public void importInput(MerTermImportInput importInput);

	public List<CposTerm> listAll();

	public void createKey(String[] ids);

	public void exportKey(String[] ids);
	
	public void resetKey(String id);

	
}

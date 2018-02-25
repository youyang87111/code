package com.pax.busi.resourcemgr.service;


import java.util.List;





import com.github.pagehelper.PageInfo;
import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.busi.resourcemgr.input.CposMerAddInput;
import com.pax.busi.resourcemgr.input.CposMerUpdateInput;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
import com.pax.core.model.PageQueryParam;



public interface CposMerService {

	void save(CposMerAddInput input);

	PageInfo<CposMer> list(PageQueryParam pageQueryParam);
	
	void update(CposMerUpdateInput cposMerUpdateInput);
	
	void audit(String id, String audit);
	
	void frozen(String[] ids);
	
	void unfrozen(String[] ids);
	
	void importInput(MerTermImportInput importInput);

	CposMer detail(String id);

	void delete(String[] ids);

	List<CposMer> listAll();

}

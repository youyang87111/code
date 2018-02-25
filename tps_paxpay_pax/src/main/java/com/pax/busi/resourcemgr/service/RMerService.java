package com.pax.busi.resourcemgr.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pax.busi.resourcemgr.entity.RMer;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
import com.pax.busi.resourcemgr.input.RMerAddInput;
import com.pax.busi.resourcemgr.input.RMerUpdateInput;
import com.pax.core.model.PageQueryParam;




public interface RMerService {

	public void save(RMerAddInput input);

	public PageInfo<RMer> list(PageQueryParam pageQueryParam);

	public void update(RMerUpdateInput input);

	public RMer detail(String id);

	public List<RMer> listAll();

	public void audit(String id, String audit);

	public void unfrozen(String[] ids);

	public void frozen(String[] ids);

	public void importInput(MerTermImportInput importInput);

	public void delete(String[] ids);
	
}

package com.pax.busi.resourcemgr.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.User;
import com.pax.busi.resourcemgr.entity.RTerm;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
import com.pax.busi.resourcemgr.input.RTermAddInput;
import com.pax.busi.resourcemgr.input.RTermUpdateInput;
import com.pax.core.model.PageQueryParam;


public interface RTermService {

	public PageInfo<RTerm> list(PageQueryParam pageQueryParam);

	public List<RTerm> listAll();

	public RTerm detail(String id);

	public void update(RTermUpdateInput input);

	public void audit(String id, String audit);

	public void frozen(String[] ids);

	public void unfrozen(String[] ids);

	public void delete(String[] ids);

	public void importInput(MerTermImportInput importInput);

	public void save(RTermAddInput input);
	
//	public void importKey(TermSecretInput input,String mcr) throws BusinessException, Exception;
//
//	public void importKeyL(TermSecretInput input, String mcr) throws BusinessException, Exception;

	public void exchangeKey();

	public String importKeys(List<String> list, String mcr, User user);

	public List<String> parseFile(String file) throws Exception;

}

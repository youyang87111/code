package com.pax.auth.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.OperateLog;
import com.pax.auth.entity.OperateLogParam;
import com.pax.common.model.PageQueryParam;

public interface OperateLogService {
	
	void save(OperateLog log);
	
	PageInfo<OperateLog> list(PageQueryParam pageQueryParam);
	
	void delete(String[] ids);
	
	List<OperateLogParam> getParamsById(String id);
}

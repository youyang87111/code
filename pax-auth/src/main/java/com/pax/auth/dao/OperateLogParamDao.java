package com.pax.auth.dao;

import java.util.List;

import com.pax.auth.entity.OperateLogParam;

public interface OperateLogParamDao {
	
	void save(OperateLogParam param);
	
	void delete(Integer valueOf);
	
	List<OperateLogParam> list(String id);
	
}

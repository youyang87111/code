package com.pax.auth.dao;

import java.util.List;

import com.pax.auth.entity.OperateLog;
import com.pax.common.model.PageQueryParam;

public interface OperateLogDao {
	
	public int getNextId();

	public void save(OperateLog log);

	public List<OperateLog> list(PageQueryParam pageQueryParam);

	public OperateLog get(int id);

	public void delete(int id);
}

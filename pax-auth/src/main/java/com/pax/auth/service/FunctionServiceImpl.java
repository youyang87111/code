package com.pax.auth.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.auth.dao.FunctionDao;
import com.pax.auth.entity.Function;
import com.pax.auth.util.AuthUtils;
import com.pax.common.exception.BusinessException;
import com.pax.common.model.PageQueryParam;
import com.pax.common.util.DateUtils;

@Service
public class FunctionServiceImpl implements FunctionService {
	
	@javax.annotation.Resource
	private FunctionDao functionDao;
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(Map<String, Object> filterMap) {
		
		int id = functionDao.getNextId();
		filterMap.put("id", id);
		filterMap.put("buildoper", AuthUtils.getUser().getLoginname());
		filterMap.put("builddatetime", DateUtils.getCurrentDateString());
		
		functionDao.save(filterMap);
	}
	
	@Override
	public PageInfo<Function> list(PageQueryParam pageQueryParam) {
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<Function> list = functionDao.list(pageQueryParam);
		
		PageInfo<Function> pageInfo = new PageInfo<Function>(list);
		
		return pageInfo;
		
	}
	
	@Override
	public Function get(String id) {
		return functionDao.get(id);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void update(Map<String, Object> filterMap) {
		
		filterMap.put("modifyoper", AuthUtils.getUser().getLoginname());
		filterMap.put("modifydatetime", DateUtils.getCurrentDateString());
		
		functionDao.update(filterMap);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void delete(String[] ids) {
		
		for (String id : ids) {
			
			//判断是否有权限引用到该功能
			int authsCount = functionDao.getAuthsCount(id);
			if (authsCount != 0) {
				throw new BusinessException("有权限引用该功能，不能删除");
			}
			functionDao.delete(id);
		}
	}
	
	@Override
	public List<Function> getFuncsByAuth(String id) {
		
		List<Function> list = functionDao.getFuncsByAuth(id);
		
		return list;
	}
	
}

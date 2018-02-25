package com.pax.auth.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.auth.dao.SiteDao;
import com.pax.auth.entity.Site;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.DateUtils;
import com.pax.core.util.WebUtils;

@Service
public class SiteServiceImpl implements SiteService {
	
	@Resource
	private SiteDao siteDao;
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(Map<String, Object> filterMap) {
		
		int id = siteDao.getNextId();
		filterMap.put("id", id);
		filterMap.put("status", "2");
		filterMap.put("buildoper", WebUtils.getUserName());
		filterMap.put("builddatetime", DateUtils.getCurrentDateString());
		
		siteDao.save(filterMap);
	}
	
	@Override
	public PageInfo<Site> list(PageQueryParam pageQueryParam) {
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<Site> list = siteDao.list(pageQueryParam);
		
		PageInfo<Site> pageInfo = new PageInfo<Site>(list);
		
		return pageInfo;
		
	}
	
	@Override
	public Site get(String id) {
		return siteDao.get(id);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void update(Map<String, Object> filterMap) {
		
		filterMap.put("modifyoper", WebUtils.getUserName());
		filterMap.put("modifydatetime", DateUtils.getCurrentDateString());
		
		siteDao.update(filterMap);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void frozen(String[] ids) {
		for (String id : ids) {
			siteDao.frozen(id);
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void unfrozen(String[] ids) {
		for (String id : ids) {
			siteDao.unfrozen(id);
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void delete(String[] ids) {
		for (String id : ids) {
			siteDao.delete(id);
		}
	}
	
	@Override
	public List<Site> listAll() {
		
		List<Site> list = siteDao.listAll();
		
		return list;
	}
	
}

package com.pax.auth.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.auth.dao.AuthorityDao;
import com.pax.auth.entity.Authority;
import com.pax.auth.entity.Function;
import com.pax.auth.entity.Menu;
import com.pax.auth.util.AuthUtils;
import com.pax.common.exception.BusinessException;
import com.pax.common.model.PageQueryParam;
import com.pax.common.util.DateUtils;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	
	@javax.annotation.Resource
	private AuthorityDao	authorityDao;
	
	@javax.annotation.Resource
	private FunctionService	functionService;
	
	@javax.annotation.Resource
	private MenuService		menuService;
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(Map<String, Object> filterMap) {
		
		int id = authorityDao.getNextId();
		filterMap.put("id", id);
		filterMap.put("buildoper", AuthUtils.getUser().getLoginname());
		filterMap.put("builddatetime", DateUtils.getCurrentDateString());
		
		authorityDao.save(filterMap);
	}
	
	@Override
	public PageInfo<Authority> list(PageQueryParam pageQueryParam) {
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<Authority> list = authorityDao.list(pageQueryParam);
		
		PageInfo<Authority> pageInfo = new PageInfo<Authority>(list);
		
		return pageInfo;
		
	}
	
	@Override
	public Authority get(String id) {
		return authorityDao.get(id);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void update(Map<String, Object> filterMap) {
		
		filterMap.put("modifyoper", AuthUtils.getUser().getLoginname());
		filterMap.put("modifydatetime", DateUtils.getCurrentDateString());
		
		authorityDao.update(filterMap);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void delete(String[] ids) {
		
		for (String id : ids) {
			//判断是否有角色引用到该权限
			int rolesCount = authorityDao.getRolesCount(id);
			if (rolesCount != 0) {
				throw new BusinessException("有角色引用该权限，不能删除");
			}
			//先删除权限和功能的关联关系
			authorityDao.deleteAuthFuncs(id);
			authorityDao.delete(id);
		}
	}
	
	/**
	 * 得到某个权限可以选择的功能，1、通过权限所属站点来过滤；
	 * @return 可选功能是在包含在menu的funcs中
	 * 
	 * 请求参数中包括 id:权限id
	 * 
	 */
	@Override
	public List<Menu> getFuncsToUseByAuth(Map<String, Object> filterMap) {
		
		//得到要分配功能的这个权限的站点id
		int site_id = authorityDao.getSiteId(filterMap.get("id").toString());
		filterMap.put("site_id", site_id);
		
		//========================查询出所有的菜单====================
		List<Menu> list = menuService.getFuncsToUseByAuth(filterMap);
		
		return list;
	}
	
	@Override
	public List<Function> getFuncsByAuth(String id) {
		
		List<Function> list = functionService.getFuncsByAuth(id);
		return list;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void grantFuncs(Map<String, Object> filterMap) {
		
		authorityDao.deleteAuthFuncs(filterMap.get("id").toString());
		
		if (null != filterMap.get("funcs")) {
			String funcs = filterMap.get("funcs").toString();
			String[] fs = funcs.split(",");
			
			if (fs.length > 0) {
				filterMap.put("funcs", fs);
				authorityDao.grantFuncs(filterMap);
			}
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void grantResources(Map<String, Object> filterMap) {
		String resources = filterMap.get("resources").toString();
		String[] res = resources.split(",");
		
		filterMap.put("resources", res);
		authorityDao.deleteAllResources(filterMap.get("authId").toString());
		authorityDao.grantResources(filterMap);
	}
	
	@Override
	public List<Authority> getAuthsToUseByUser(int userId) {
		
		List<Authority> list = authorityDao.getAuthsToUseByUser(userId);
		
		return list;
	}
	
	@Override
	public List<Authority> getAuthsByRole(String id) {
		
		List<Authority> list = authorityDao.getAuthsByRole(id);
		
		return list;
	}
	
	@Override
	public List<Authority> getAuthsByRoleModel(String id) {
		
		List<Authority> list = authorityDao.getAuthsByRoleModel(id);
		
		return list;
	}
	
	/**
	 * 得到某个用户的所有权限
	 * @param id
	 * @return
	 * @see com.pax.auth.service.AuthorityService#getAuthsByUser(int)
	 */
	@Override
	public List<Authority> getAuthsByUser(int id) {
		
		List<Authority> list = authorityDao.getAuthsByUser(id);
		
		if (CollectionUtils.isEmpty(list)) {
			//list为空，说明可能使用的是rolemodel
			list = authorityDao.getAuthsByUserByRoelModel(id);
		}
		
		return list;
	}
	
	@Override
	public List<Authority> getAuthsOutofSite(int siteId) {
		return authorityDao.getAuthsOutofSite(siteId);
	}
	
	@Override
	public List<Authority> getAuthsOfSite(int siteId) {
		return authorityDao.getAuthsOfSite(siteId);
	}
	
}

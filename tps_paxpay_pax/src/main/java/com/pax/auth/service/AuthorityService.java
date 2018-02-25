package com.pax.auth.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.Authority;
import com.pax.auth.entity.Function;
import com.pax.auth.entity.Menu;
import com.pax.core.model.PageQueryParam;

public interface AuthorityService {
	
	public void save(Map<String, Object> filterMap);
	
	public PageInfo<Authority> list(PageQueryParam pageQueryParam);
	
	public Authority get(String id);
	
	public void update(Map<String, Object> filterMap);
	
	public void delete(String[] ids);
	
	public void grantResources(Map<String, Object> filterMap);
	
	/**
	 * 得到某个权限可以选择的功能，1、通过权限所属站点来过滤；
	 * @return 可选功能是在包含在menu的funcs中
	 * 
	 * 请求参数中包括 id:权限id
	 * 
	 */
	public List<Menu> getFuncsToUseByAuth(Map<String, Object> filterMap);
	
	/**
	 * 得到某个权限已经选择的功能
	 * @param id 权限id
	 * @return
	 */
	public List<Function> getFuncsByAuth(String id);
	
	public void grantFuncs(Map<String, Object> filterMap);
	
	/**
	 * 得到某个角色可以选择的权限，1、通过角色所属站点来过滤；2、只能从创建者所拥有的最大权限范围取；3、如果是amdin，则显示admin所属站点的全部权限
	 * @return 可选权限是在包含在menu的auths中
	 * 
	 * 请求参数中包括 id
	 * 
	 */
	public List<Authority> getAuthsToUseByUser(int userId);
	
	/**
	 * 得到某个角色的所有权限，即角色已经选择了的权限
	 * @param id
	 * @return
	 */
	public List<Authority> getAuthsByRole(String id);
	
	/**
	 * 得到某个角色的所有权限，即角色已经选择了的权限
	 * @param id
	 * @return
	 */
	public List<Authority> getAuthsByRoleModel(String id);
	
	/**
	 * 得到某个用户的所有权限
	 * @param id
	 * @return
	 */
	public List<Authority> getAuthsByUser(int id);
	
	/**
	 * 得到除某个站点以为的其他站点的所有权限
	 * @param siteId
	 * @return
	 */
	public List<Authority> getAuthsOutofSite(int siteId);
	
	/**
	 * 得到某个站点的所有权限
	 * @param siteId
	 * @return
	 */
	public List<Authority> getAuthsOfSite(int siteId);
	
}

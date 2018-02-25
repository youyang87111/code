package com.pax.auth.dao;

import java.util.List;
import java.util.Map;

import com.pax.auth.entity.Menu;

public interface MenuDao {
	
	void save(Map<String, Object> filterMap);
	
	List<Menu> list(Map<String, Object> filterMap);
	
	Menu get(String id);
	
	int getNextId();
	
	void update(Map<String, Object> filterMap);
	
	void delete(String id);
	
	int getSiteId(String id);
	
	/**
	 * 得到某个权限可以选择的功能，1、通过权限所属站点来过滤；
	 * @return 可选功能是在包含在menu的funcs中
	 * 
	 * 请求参数中包括 site_id:权限所属站点id
	 * 
	 */
	List<Menu> getFuncsToUseByAuth(Map<String, Object> filterMap);
	
	/**
	 * 得到登陆者所拥有的子菜单
	 * @param userId
	 * @return
	 */
	List<Menu> getMenusByUser(int userId);
	
	int getSubMenusCount(String id);
	
	int getFuncsCount(String id);
	
	List<Menu> getMenusByUserByRolemode(int id);
}

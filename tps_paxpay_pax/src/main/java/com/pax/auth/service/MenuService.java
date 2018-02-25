package com.pax.auth.service;

import java.util.List;
import java.util.Map;

import com.pax.auth.entity.Menu;
import com.pax.auth.entity.User;

public interface MenuService {
	
	public void save(Map<String, Object> filterMap);
	
	public Menu get(String id);
	
	public void update(Map<String, Object> filterMap);
	
	public void delete(String ids);
	
	public Menu load(Map<String, Object> filterMap);
	
	/**
	 * 根据站点得到所有的菜单
	 * @param filterMap
	 * @return
	 */
	public List<Menu> list(Map<String, Object> filterMap);
	
	public void setAllParentMenu(Menu menu, List<Menu> allMenu, List<Menu> allParentMenu);
	
	/**
	 * 得到某个权限可以选择的功能，1、通过权限所属站点来过滤；
	 * @return 可选功能是在包含在menu的funcs中
	 * 
	 * 请求参数中包括 site_id:权限所属站点id
	 * 
	 */
	public List<Menu> getFuncsToUseByAuth(Map<String, Object> filterMap);
	
	/**
	 * 得到登陆者所拥有的子菜单
	 * @param id
	 * @return
	 */
	public List<Menu> getMenusByUser(User user);
	
}

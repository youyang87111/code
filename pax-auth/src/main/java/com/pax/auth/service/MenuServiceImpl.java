package com.pax.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pax.auth.dao.MenuDao;
import com.pax.auth.entity.Menu;
import com.pax.auth.entity.User;
import com.pax.auth.util.AuthUtils;
import com.pax.common.exception.BusinessException;
import com.pax.common.util.DateUtils;

@Service
public class MenuServiceImpl implements MenuService {
	
	@javax.annotation.Resource
	private MenuDao			menuDao;
	
	@javax.annotation.Resource
	private FunctionService	functionService;
	
	@javax.annotation.Resource
	private AuthJsonService	authJsonService;
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(Map<String, Object> filterMap) {
		
		int id = menuDao.getNextId();
		filterMap.put("id", id);
		
		//得到父节点的site_id
		int site_id = menuDao.getSiteId(filterMap.get("pid").toString());
		filterMap.put("site_id", site_id);
		
		filterMap.put("buildoper", AuthUtils.getUser().getLoginname());
		filterMap.put("builddatetime", DateUtils.getCurrentDateString());
		
		menuDao.save(filterMap);
	}
	
	@Override
	public Menu load(Map<String, Object> filterMap) {
		
		List<Menu> list = menuDao.list(filterMap);
		
		//得到根菜单
		Menu parent = null;
		for (Menu menu : list) {
			if (menu.getPmenu() == null) {
				parent = menu;
				break;
			}
		}
		
		setChilds(parent, list);
		
		return parent;
		
	}
	
	private void setChilds(Menu parent, List<Menu> list) {
		List<Menu> childs = new ArrayList<Menu>();
		parent.setList(childs);
		for (Menu menu : list) {
			//排除根菜单，根菜单没得parent
			if (menu.getPmenu() != null) {
				if (menu.getPmenu().getId() == parent.getId()) {
					childs.add(menu);
					setChilds(menu, list);
				}
			}
			
		}
	}
	
	@Override
	public Menu get(String id) {
		return menuDao.get(id);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void update(Map<String, Object> filterMap) {
		
		filterMap.put("modifyoper", AuthUtils.getUser().getLoginname());
		filterMap.put("modifydatetime", DateUtils.getCurrentDateString());
		
		menuDao.update(filterMap);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void delete(String id) {
		
		//判断该菜单下面还有没得子菜单
		int subMenusCount = menuDao.getSubMenusCount(id);
		
		if (subMenusCount != 0) {
			throw new BusinessException("该菜单下面有子菜单，不能删除");
		}
		
		//判断该菜单下面有没得功能
		int funcsCount = menuDao.getFuncsCount(id);
		
		if (funcsCount != 0) {
			throw new BusinessException("有功能引用该菜单，不能删除");
		}
		
		menuDao.delete(id);
	}
	
	@Override
	public List<Menu> list(Map<String, Object> filterMap) {
		
		List<Menu> list = menuDao.list(filterMap);
		
		return list;
	}
	
	/**
	 * 从allMenu中找到menu的所有父节点，然后存入到allParentMenu中
	 * @param menu
	 * @param allMenu
	 * @param allParentMenu
	 */
	public void setAllParentMenu(Menu menu, List<Menu> allMenu, List<Menu> allParentMenu) {
		Menu parent = menu.getPmenu();
		if (parent == null) {
			//已经找到最顶层了
			return;
		} else {
			for (Menu m : allMenu) {
				if (m.getId() == parent.getId()) {
					//找到了父节点，将父节点放入allParentMenu
					if (!allParentMenu.contains(m)) {
						allParentMenu.add(m);
						//接着再找m的父节点
						setAllParentMenu(m, allMenu, allParentMenu);
					}
					
				}
			}
		}
		
	}
	
	/**
	 * 得到某个权限可以选择的功能，1、通过权限所属站点来过滤；
	 * @return 可选功能是在包含在menu的funcs中
	 * 
	 * 请求参数中包括 site_id:权限所属站点id
	 * 
	 */
	@Override
	public List<Menu> getFuncsToUseByAuth(Map<String, Object> filterMap) {
		return menuDao.getFuncsToUseByAuth(filterMap);
	}
	
	/**
	 * 得到某个用户可以访问的菜单
	 * @param 
	 * @return
	 */
	@Override
	public List<Menu> getMenusByUser(User user)
	
	{
		
		//========================查询出所有的菜单==========================
		Map<String, Object> menuFilterMap = new HashMap<String, Object>();
		
		//得到用户的站点id
		int site_id = user.getSite().getId();
		
		menuFilterMap.put("site_id", site_id);
		List<Menu> allMenu = menuDao.list(menuFilterMap);
		//========================查询出所有的菜单==========================
		
		//得到登陆者所拥有的子菜单
		List<Menu> subMenu = menuDao.getMenusByUser(user.getId());
		
		if (CollectionUtils.isEmpty(subMenu)) {
			//当submenu为空时，说明有可能是使用的rolemodel
			subMenu = menuDao.getMenusByUserByRolemode(user.getId());
		}
		
		List<Menu> allParentMenu = new ArrayList<Menu>();
		
		//找到这些子菜单的所有父菜单
		for (Menu menu : subMenu) {
			setAllParentMenu(menu, allMenu, allParentMenu);
		}
		
		allParentMenu.addAll(subMenu);
		
		return allParentMenu;
	}
	
}

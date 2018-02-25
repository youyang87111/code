package com.pax.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.auth.dao.RoleModelDao;
import com.pax.auth.entity.Authority;
import com.pax.auth.entity.Menu;
import com.pax.auth.entity.RoleModel;
import com.pax.auth.entity.Site;
import com.pax.auth.entity.User;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.DateUtils;
import com.pax.core.util.WebUtils;

@Service
public class RoleModelServiceImpl implements RoleModelService {
	
	@Resource
	private RoleModelDao		roleModelDao;
	
	@Resource
	private OrganizationService	organizationService;
	
	@Resource
	private UserService			userService;
	
	@Resource
	private MenuService			menuService;
	
	@Resource
	private FunctionService		functionService;
	
	@Resource
	private AuthorityService	authorityService;
	
	@Resource
	private SiteService			siteService;
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(Map<String, Object> filterMap) {
		
		Site site_input = siteService.get(filterMap.get("site_id").toString());
		
		if (site_input == null) {
			throw new BusinessException("站点不存在");
		}
		
		User user = WebUtils.getUser();
		Site site = user.getSite();
		//只有平台管理员可以创建角色模板
		if (!"P".equals(site.getTag())) {
			throw new BusinessException("角色模板只能由平台管理员创建");
		}
		
		int id = roleModelDao.getNextId();
		filterMap.put("id", id);
		filterMap.put("buildoper", WebUtils.getUser().getLoginname());
		filterMap.put("builddatetime", DateUtils.getCurrentDateString());
		
		roleModelDao.save(filterMap);
	}
	
	@Override
	public PageInfo<RoleModel> list(PageQueryParam pageQueryParam) {
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<RoleModel> list = roleModelDao.list(pageQueryParam);
		
		PageInfo<RoleModel> pageInfo = new PageInfo<RoleModel>(list);
		
		return pageInfo;
		
	}
	
	@Override
	public RoleModel get(String id) {
		
		RoleModel role = roleModelDao.get(id);
		
		return role;
		
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void update(Map<String, Object> filterMap) {
		
		filterMap.put("modifyoper", WebUtils.getUser().getLoginname());
		filterMap.put("modifydatetime", DateUtils.getCurrentDateString());
		
		roleModelDao.update(filterMap);
	}
	
	@Override
	public List<RoleModel> getRolesByUser(String userId) {
		
		List<RoleModel> list = roleModelDao.getRolesByUser(userId);
		
		return list;
	}
	
	/**
	 * 得到某个用户所有可以选择的角色，1、通过被分配用户所属的机构来过滤；
	 * 2、只能从创建者所拥有的本机机构的最大角色范围取；3、如果是平台的操作员，包含被分配用户的机构下的的所有权限
	 * @param 参数：id（用户id），search_name(按角色名称来过滤)
	 * @return
	 */
	@Override
	public List<RoleModel> getRolesToUseByUser(Map<String, Object> filterMap) {
		
		User loginUser = WebUtils.getUser();
		
		//被分配角色的用户
		User user = userService.get(filterMap.get("id").toString());
		int orgId = user.getOrg().getId();
		
		filterMap.put("orgId", orgId);
		
		if (!"admin".equals(loginUser.getLoginname())) {
			//如果被操作用户和登陆用户是同一级的，那么只能从创建者所拥有的本机机构的最大角色范围取
			if (loginUser.getOrg().getId() == user.getOrg().getId()) {
				List<RoleModel> rolesOfLoginUser = getRolesByUser(
					String.valueOf(loginUser.getId()));
				filterMap.put("rolesOfLoginUser", rolesOfLoginUser);
			}
		}
		
		List<RoleModel> result = roleModelDao.getRolesToUseByUser(filterMap);
		
		return result;
		
	}
	
	@Override
	public List<Menu> getAuthsToUseByRole(Map<String, Object> filterMap) {
		
		//得到要分配权限的这个角色的站点id
		int site_id = roleModelDao.getSiteId(filterMap.get("id").toString());
		
		//得到这个站点的所有权限
		//List<Authority> list = authorityService.getAuthsOfSite(site_id);
		
		User user = WebUtils.getUser();
		Site site = user.getSite();
		
		//得到登陆者所拥有的所有权限
		List<Authority> list = authorityService.getAuthsToUseByUser(user.getId());
		
		if ("P".equals(site.getTag())) {
			
			if (!"admin".equals(user.getLoginname())) {
				//得到其他站点的所有权限
				List<Authority> list2 = authorityService.getAuthsOutofSite(site.getId());
				list.addAll(list2);
			}
			
		}
		
		//========================查询出角色站点的所有的菜单==========================
		Map<String, Object> menuFilterMap = new HashMap<String, Object>();
		
		menuFilterMap.put("site_id", site_id);
		List<Menu> allMenu = menuService.list(menuFilterMap);
		//========================查询出角色站点的所有的菜单==========================
		
		//把这些权限设置到菜单里面去
		for (Authority authority : list) {
			
			for (Menu menu : allMenu) {
				if (menu.getId() == authority.getMenu().getId()) {
					menu.getAuths().add(authority);
					break;
				}
			}
			
		}
		
		return allMenu;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void grantAuths(Map<String, Object> filterMap) {
		
		String id = filterMap.get("id").toString();
		
		RoleModel role = get(id);
		Site site = role.getSite();
		
		String auths = filterMap.get("auths").toString();
		String[] as = auths.split(",");
		
		User user = WebUtils.getUser();
		
		//得到登陆者所拥有的所有权限(admin已经包含了其他站点的所有权限)
		List<Authority> list = authorityService.getAuthsToUseByUser(user.getId());
		
		if ("P".equals(user.getSite().getTag())) {
			
			//除了admin的其他平台操作员，需要得到其他站点的所有权限
			if (!"admin".equals(user.getLoginname())) {
				//得到其他站点的所有权限
				List<Authority> list2 = authorityService.getAuthsOutofSite(user.getSite().getId());
				list.addAll(list2);
			}
			
		}
		//遍历每一个权限，看权限站点和角色站点是否一直
		for (String authId : as) {
			Authority authority = authorityService.get(authId);
			if (authority.getSite().getId() != role.getSite().getId()) {
				throw new BusinessException("权限站点和角色站点不匹配");
			}
			if (!list.contains(authority)) {
				throw new BusinessException("所指定的权限中包含你无权使用的权限");
			}
		}
		
		filterMap.put("auths", as);
		roleModelDao.deleteAllAuths(filterMap.get("id").toString());
		roleModelDao.grantAuths(filterMap);
	}
	
	@Override
	public List<RoleModel> getRolesOutofSite(int siteId) {
		return roleModelDao.getRolesOutofSite(siteId);
	}
	
}

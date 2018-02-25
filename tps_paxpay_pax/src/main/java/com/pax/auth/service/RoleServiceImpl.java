package com.pax.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.auth.dao.RoleDao;
import com.pax.auth.entity.Authority;
import com.pax.auth.entity.Menu;
import com.pax.auth.entity.Organization;
import com.pax.auth.entity.Role;
import com.pax.auth.entity.Site;
import com.pax.auth.entity.User;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.DateUtils;
import com.pax.core.util.WebUtils;

@Service
public class RoleServiceImpl implements RoleService {
	
	@javax.annotation.Resource
	private RoleDao				roleDao;
	
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
		//如果是平台管理员，是可以新增任何平台的角色
		//如果是其他平台的管理员，只能新增自己站点的角色
		if (!"P".equals(site.getTag())) {
			if (site_input.getId() != site.getId()) {
				throw new BusinessException("只能创建自己所属站点的角色");
			}
		}
		
		//===================判断机构和站点是否匹配=====================
		Organization org_input = organizationService.get(filterMap.get("org_id").toString());
		
		if (org_input == null) {
			throw new BusinessException("机构不存在");
		}
		
		if (org_input.getSite().getId() != site_input.getId()) {
			throw new BusinessException("机构所属站点和当前选择的站点不匹配");
		}
		//===================判断机构和站点是否匹配=====================
		
		//===================判断机构是否在可选范围内=====================
		//如果是admin，可以新增所有机构的角色，否则只能新增本机和下级机构的角色
		if (!"admin".equals(user.getLoginname())) {
			if ("P".equals(site.getTag())) {
				//当前操作员可看到的机构列表
				List<Organization> orgs = organizationService.list(filterMap);
				//其他所有站点的所有机构
				List<Organization> orgs2 = organizationService.listAllOfSite(site.getId());
				orgs.addAll(orgs2);
				if (!orgs.contains(org_input)) {
					throw new BusinessException("你不能新增所选机构的角色");
				}
			} else {
				//当前操作员可看到的机构列表
				List<Organization> orgs = organizationService.list(filterMap);
				//判断当前选择的机构，是否在orgs里面
				if (!orgs.contains(org_input)) {
					throw new BusinessException("你不能新增所选机构的角色");
				}
			}
			
		}
		//===================判断机构是否在可选范围内=====================
		
		int id = roleDao.getNextId();
		filterMap.put("id", id);
		filterMap.put("buildoper", WebUtils.getUser().getLoginname());
		filterMap.put("builddatetime", DateUtils.getCurrentDateString());
		
		roleDao.save(filterMap);
	}
	
	@Override
	public PageInfo<Role> list(PageQueryParam pageQueryParam) {
		
		initializeFiltering(pageQueryParam);
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<Role> list = roleDao.list(pageQueryParam);
		
		PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		
		return pageInfo;
		
	}
	
	private void initializeFiltering(PageQueryParam pageQueryParam) {
		
		pageQueryParam.getFilterMap().remove("admin");
		
		User user = WebUtils.getUser();
		Site site = user.getSite();
		
		//如果是其他平台的管理员，只能看到自己站点的角色,并且只能看到本级和下级机构的角色
		if (!"P".equals(site.getTag())) {
			//查看选择了站点没得，没有选择，为站点参数赋值
			if (pageQueryParam.getFilterMap().containsKey("site_id")) {
				String value = pageQueryParam.getFilterMap().get("site_id").toString();
				if (StringUtils.isBlank(value)) {
					pageQueryParam.getFilterMap().put("site_id", site.getId());
				} else {
					//看站点参数是不是和site一致,不一致以site为准
					if (!value.equals(String.valueOf(site.getId()))) {
						pageQueryParam.getFilterMap().put("site_id", site.getId());
					}
				}
				
			} else {
				pageQueryParam.getFilterMap().put("site_id", site.getId());
			}
			
			Map<String, Object> filterMap = new HashMap<String, Object>();
			filterMap.put("id", user.getOrg().getId());
			filterMap.put("site_id", site.getId());
			//得到当前登陆用户的机构和下级机构
			List<Organization> orgs = organizationService.list(filterMap);
			
			if (pageQueryParam.getFilterMap().containsKey("org_id")) {
				String value = pageQueryParam.getFilterMap().get("org_id").toString();
				if (StringUtils.isBlank(value)) {
					pageQueryParam.getFilterMap().put("orgs", orgs);
				} else {
					//看机构参数是不是包含在orgs里面
					
					Organization org = organizationService.get(value);
					if (!orgs.contains(org)) {
						pageQueryParam.getFilterMap().put("org_id", "");
						pageQueryParam.getFilterMap().put("orgs", orgs);
					} else {
						//包含就安装机构去查询
					}
					
				}
			} else {
				pageQueryParam.getFilterMap().put("orgs", orgs);
			}
			//平台用户可以看到所有站点的角色
		} else {
			//admin可以查看所有
			if ("admin".equals(user.getLoginname())) {
				pageQueryParam.getFilterMap().put("admin", true);
			} else {
				//如果是平台其他管理员
				//得到当前登陆用户的机构和下级机构
				Map<String, Object> filterMap = new HashMap<String, Object>();
				filterMap.put("id", user.getOrg().getId());
				filterMap.put("site_id", site.getId());
				List<Organization> orgs = organizationService.list(filterMap);
				//得到其他平台的所有机构
				List<Organization> orgs2 = organizationService.listAllOfSite(site.getId());
				//把全部机构加起来
				orgs.addAll(orgs2);
				
				if (pageQueryParam.getFilterMap().containsKey("org_id")) {
					String value = pageQueryParam.getFilterMap().get("org_id").toString();
					if (StringUtils.isBlank(value)) {
						pageQueryParam.getFilterMap().put("orgs", orgs);
					} else {
						//看机构参数是不是包含在orgs里面
						
						Organization org = organizationService.get(value);
						if (!orgs.contains(org)) {
							pageQueryParam.getFilterMap().put("org_id", "");
							pageQueryParam.getFilterMap().put("orgs", orgs);
						} else {
							//包含就安装机构去查询
						}
						
					}
				} else {
					pageQueryParam.getFilterMap().put("orgs", orgs);
				}
			}
		}
	}
	
	@Override
	public Role get(String id) {
		
		List<Role> list = listAllRoles();
		
		Role role = roleDao.get(id);
		
		if (list.contains(role)) {
			return role;
		} else {
			throw new BusinessException("你没有权限操作该记录");
		}
		
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void update(Map<String, Object> filterMap) {
		
		get(filterMap.get("id").toString());
		
		filterMap.put("modifyoper", WebUtils.getUser().getLoginname());
		filterMap.put("modifydatetime", DateUtils.getCurrentDateString());
		
		roleDao.update(filterMap);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void delete(String[] ids) {
		
		for (String id : ids) {
			
			get(id);
			
			//判断是否有用户引用到该角色
			int usersCount = roleDao.getUsersCount(id);
			
			if (usersCount != 0) {
				throw new BusinessException("有用户引用该角色，不能删除");
			}
			roleDao.deleteRoleAuth(id);
			roleDao.delete(id);
		}
	}
	
	@Override
	public List<Role> getRolesByUser(String userId) {
		
		List<Role> list = roleDao.getRolesByUser(userId);
		
		return list;
	}
	
	/**
	 * 得到某个用户所有可以选择的角色，1、通过被分配用户所属的机构来过滤；
	 * 2、只能从创建者所拥有的本机机构的最大角色范围取,再加上自己创建的角色；3、如果是平台的操作员，包含被分配用户的机构下的的所有权限
	 * @param 参数：id（用户id），search_name(按角色名称来过滤)
	 * @return
	 */
	@Override
	public List<Role> getRolesToUseByUser(Map<String, Object> filterMap) {
		
		User loginUser = WebUtils.getUser();
		
		//被分配角色的用户
		User user = userService.get(filterMap.get("id").toString());
		int orgId = user.getOrg().getId();
		
		filterMap.put("orgId", orgId);
		
		filterMap.put("loginname", loginUser.getLoginname());
		
		if (!"admin".equals(loginUser.getLoginname())) {
			//如果被操作用户和登陆用户是同一级的，那么只能从创建者所拥有的本机机构的最大角色范围取,再加上自己创建的角色
			//如果被操作用户和登陆用户不是同一级的，那么只能选择自己创建的角色
			if (loginUser.getOrg().getId() == user.getOrg().getId()) {
				List<Role> rolesOfLoginUser = getRolesByUser(String.valueOf(loginUser.getId()));
				filterMap.put("rolesOfLoginUser", rolesOfLoginUser);
			}
		}
		
		List<Role> result = roleDao.getRolesToUseByUser(filterMap);
		
		return result;
		
	}
	
	public List<Role> listAllRoles() {
		
		PageQueryParam pageQueryParam = new PageQueryParam();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		pageQueryParam.setFilterMap(filterMap);
		
		initializeFiltering(pageQueryParam);
		
		List<Role> list = roleDao.list(pageQueryParam);
		
		return list;
		
	}
	
	@Override
	public List<Menu> getAuthsToUseByRole(Map<String, Object> filterMap) {
		
		//========================查询出所有的菜单==========================
		Map<String, Object> menuFilterMap = new HashMap<String, Object>();
		
		//得到要分配权限的这个角色的站点id
		int site_id = roleDao.getSiteId(filterMap.get("id").toString());
		
		menuFilterMap.put("site_id", site_id);
		List<Menu> allMenu = menuService.list(menuFilterMap);
		//========================查询出所有的菜单==========================
		
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
		
		Role role = get(id);
		Site site = role.getSite();
		
		String auths = filterMap.get("auths").toString();
		if ("".equals(auths.trim())) {
			//String[] as = new Array<String>;
		}
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
			if ("".equals(authId.trim()))
				continue;
			Authority authority = authorityService.get(authId);
			if (authority.getSite().getId() != role.getSite().getId()) {
				throw new BusinessException("权限站点和角色站点不匹配");
			}
			
			if (!list.contains(authority)) {
				throw new BusinessException("所指定的权限中包含你无权使用的权限");
			}
		}
		
		filterMap.put("auths", as);
		roleDao.deleteAllAuths(filterMap.get("id").toString());
		roleDao.grantAuths(filterMap);
	}
	
	@Override
	public List<Role> getRolesOutofSite(int siteId) {
		return roleDao.getRolesOutofSite(siteId);
	}
	
}

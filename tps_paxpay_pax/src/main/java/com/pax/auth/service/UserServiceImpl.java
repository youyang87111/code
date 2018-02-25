package com.pax.auth.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.auth.dao.UserDao;
import com.pax.auth.entity.*;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.DateUtils;
import com.pax.core.util.MD5Util;
import com.pax.core.util.RandCodeUtils;
import com.pax.core.util.WebUtils;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao				userDao;
	
	@Resource
	private OrganizationService	organizationService;
	
	@Resource
	private MenuService			menuService;
	
	@Resource
	private AuthorityService	authorityService;
	
	@Resource
	private RoleService			roleService;
	
	@Resource
	private MemcachedClient		client;
	
	@Resource
	private SessionManager		sessionManager;
	
	@Override
	public User login(String loginname) {
		User user = userDao.login(loginname);
		return user;
	}
	
	@Override
	public List<String> getAllAuthTags(User user) {
		List<Authority> auths = authorityService.getAuthsByUser(user.getId());
		
		List<String> authTags = new ArrayList<String>();
		for (Authority authority : auths) {
			String tag = authority.getTag();
			authTags.add(tag);
		}
		return authTags;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(Map<String, Object> filterMap) {
		
		int counts = userDao.getCountsByLoginName(filterMap.get("loginname").toString());
		
		if (counts > 0) {
			throw new BusinessException("登录名已经存在");
		}
		
		int id = userDao.getNextId();
		filterMap.put("id", id);
		
		//生成6位随机盐値
		String salt = RandCodeUtils.getRandCode(6);
		filterMap.put("salt", salt);
		
		//对密码进行MD5加密
		String password = MD5Util.getMd5String(filterMap.get("password").toString(), salt);
		filterMap.put("password", password);
		
		filterMap.put("status", "2");
		filterMap.put("buildoper", WebUtils.getUser().getLoginname());
		filterMap.put("builddatetime", DateUtils.getCurrentDateString());
		
		userDao.save(filterMap);
	}
	
	@Override
	public PageInfo<User> list(PageQueryParam pageQueryParam) {
		
		initializeFiltering(pageQueryParam);
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<User> list = userDao.list(pageQueryParam);
		
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		
		return pageInfo;
		
	}
	
	private void initializeFiltering(PageQueryParam pageQueryParam) {
		
		User user = WebUtils.getUser();
		Site site = user.getSite();
		
		//如果是其他平台的管理员，只能看到自己站点的用户,并且只能看到本级和下级机构的用户
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
			//平台用户可以看到所有站点的用户	
		} else {
			//admin可以查看所有
			if ("admin".equals(user.getLoginname())) {
				//不做任何限制
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
	public User get(String id) {
		
		List<User> list = listAllUser();
		
		User user = userDao.get(id);
		
		if (list.contains(user)) {
			return user;
		} else {
			throw new BusinessException("你没有权限操作该记录");
		}
		
	}
	
	private void forceLogout(User user) {
		String loginname = user.getLoginname();
		
		Deque<Serializable> deque = (Deque<Serializable>) client.get(loginname);
		
		if (deque != null && deque.size() > 0) {
			Object[] sessionIds = deque.toArray();
			
			for (Object object : sessionIds) {
				String sessionId = (String) object;
				
				try {
					Session kickoutSession = sessionManager
						.getSession(new DefaultSessionKey(sessionId));
					if (kickoutSession != null) {
						//设置会话的kickout属性表示踢出了  
						kickoutSession.setAttribute("kickout", true);
					}
				} catch (Exception e) {
					//获取session出错，说明session早就过期了
					deque.remove(sessionId);
				}
			}
		}
	}
	
	private List<User> listAllUser() {
		
		PageQueryParam pageQueryParam = new PageQueryParam();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		pageQueryParam.setFilterMap(filterMap);
		
		initializeFiltering(pageQueryParam);
		
		List<User> list = userDao.list(pageQueryParam);
		
		return list;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void update(Map<String, Object> filterMap) {
		
		User user = get(filterMap.get("id").toString());
		if ("3".equals(user.getStatus())) {
			throw new BusinessException("该记录不能修改");
		}
		filterMap.put("modifyoper", WebUtils.getUser().getLoginname());
		filterMap.put("modifydatetime", DateUtils.getCurrentDateString());
		
		userDao.update(filterMap);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void frozen(String[] ids) {
		
		for (String id : ids) {
			User user = get(id);
			if (!"2".equals(user.getStatus())) {
				throw new BusinessException("该记录不能冻结");
			}
			forceLogout(user);
			String frozentime = DateUtils.getCurrentDateString();
			userDao.frozen(id,frozentime);
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void unfrozen(String[] ids) {
		
		for (String id : ids) {
			User user = get(id);
			if (!"1".equals(user.getStatus())) {
				throw new BusinessException("该记录不能解冻");
			}
			userDao.unfrozen(id);
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void delete(String[] ids) {
		
		for (String id : ids) {
			User user = get(id);
			
			User loginUser = WebUtils.getUser();
			
			if (user.getId() == loginUser.getId()) {
				throw new BusinessException("不能删除自己");
			}
			
			forceLogout(user);
			
			userDao.deleteUserRoles(id);
			userDao.deleteUserRoleModels(id);
			userDao.delete(id);
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public String resetPwd(String id) {
		
		User user = get(id);
		
		if ("3".equals(user.getStatus())) {
			throw new BusinessException("该用户不能重置密码");
		}
		forceLogout(user);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		
		filterMap.put("id", id);
		
		//生成6位随机盐値
		String salt = RandCodeUtils.getRandCode(6);
		filterMap.put("salt", salt);
		
		//生成6位数的随机密码
		String randPwd = RandCodeUtils.getRandCode(6);
		
		//对密码进行MD5加密
		String password = MD5Util.getMd5String(MD5Util.getMd5String(randPwd), salt);
		filterMap.put("password", password);
		filterMap.put("lastupdatepwdtime", DateUtils.getCurrentDateString());
		
		userDao.resetPwd(filterMap);
		
		return randPwd;
		
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void grantRoles(Map<String, Object> filterMap) {
		
		String userId = filterMap.get("userId").toString();
		
		User user = get(userId);
		
		if ("3".equals(user.getStatus())) {
			throw new BusinessException("不能为该用户分配指定的角色");
		}
		
		String roles = filterMap.get("roles").toString();
		String[] rs = roles.split(",");
		
		Map<String, Object> roleFilterMap = new HashMap<String, Object>();
		
		roleFilterMap.put("id", userId);
		
		//得到所有可以分配给userId用户使用的角色
		List<Role> list = roleService.getRolesToUseByUser(roleFilterMap);
		
		for (String roleId : rs) {
			Role role = roleService.get(roleId);
			if (!list.contains(role)) {
				throw new BusinessException("不能为该用户分配指定的角色");
			}
		}
		
		filterMap.put("roles", rs);
		
		userDao.deleteUserRoles(userId);
		
		userDao.grantRoles(filterMap);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void updatePwd(Map<String, Object> filterMap) {
		
		User user = WebUtils.getUser();
		
		filterMap.put("id", user.getId());
		
		String oldPwd = MD5Util.getMd5String(filterMap.get("oldPwd").toString(), user.getSalt());
		if (!StringUtils.equals(filterMap.get("newPwd1").toString(),
			filterMap.get("newPwd2").toString())) {
			throw new BusinessException("新密码与确认密码不一致");
		}
		if (oldPwd.equals(user.getPassword())) {
			String newPwd = MD5Util.getMd5String(filterMap.get("newPwd1").toString(),
				user.getSalt());
			if (newPwd.equals(oldPwd)) {
				throw new BusinessException("新旧密码不能相同");
			}
			filterMap.put("newPwd1", newPwd);
			filterMap.put("lastupdatepwdtime", DateUtils.getCurrentDateString());
			userDao.updatePwd(filterMap);
		} else {
			throw new BusinessException("旧密码不正确");
		}
		
	}
	
	@Override
	public int getSiteId(String id) {
		return userDao.getSiteId(id);
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Util.getMd5String("111111"));
	}

	@Override
	public void updateLastlogintime(User user) {
		userDao.updateLastlogintime(user);
	}

	@Override
	public void firstUpdatePwd(Map<String, Object> filterMap) {

		User user = login(filterMap.get("loginname").toString());
		
		filterMap.put("id", user.getId());
		
		String oldPwd = MD5Util.getMd5String(filterMap.get("oldPwd").toString(), user.getSalt());
		if (!StringUtils.equals(filterMap.get("newPwd1").toString(),
			filterMap.get("newPwd2").toString())) {
			throw new BusinessException("新密码与确认密码不一致");
		}
		if (oldPwd.equals(user.getPassword())) {
			String newPwd = MD5Util.getMd5String(filterMap.get("newPwd1").toString(),
				user.getSalt());
			if (newPwd.equals(oldPwd)) {
				throw new BusinessException("新旧密码不能相同");
			}
			filterMap.put("newPwd1", newPwd);
			filterMap.put("lastupdatepwdtime", DateUtils.getCurrentDateString());
			userDao.updatePwd(filterMap);
		} else {
			throw new BusinessException("旧密码不正确");
		}
		
	}

	@Override
	public void frozen(User user) {
		userDao.frozenUser1(user);
	}

	@Override
	public void updateErrortimes(User user) {
		userDao.updateErrortimes(user);
	}
	
}

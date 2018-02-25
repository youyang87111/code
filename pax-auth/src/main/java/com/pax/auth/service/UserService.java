package com.pax.auth.service;

import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.User;
import com.pax.common.model.PageQueryParam;

import java.util.List;
import java.util.Map;

public interface UserService {
	
	public List<String> getAllAuthTags(User user);
	
	public void save(Map<String, Object> filterMap);
	
	public PageInfo<User> list(PageQueryParam pageQueryParam);
	
	public User get(String id);
	
	public void update(Map<String, Object> filterMap);
	
	public void frozen(String[] ids);
	
	public void unfrozen(String[] ids);
	
	public void delete(String[] ids);
	
	public String resetPwd(String id);
	
	public void grantRoles(Map<String, Object> filterMap);
	
	public User login(String loginname);
	
	public void updatePwd(Map<String, Object> filterMap);
	
	public int getSiteId(String id);

	public void updateLastlogintime(User user);

	public void firstUpdatePwd(Map<String, Object> filterMap);

	public void frozen(User user);

	public void updateErrortimes(User user);

	
}

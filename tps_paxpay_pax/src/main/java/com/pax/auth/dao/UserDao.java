package com.pax.auth.dao;

import java.util.List;
import java.util.Map;

import com.pax.auth.entity.User;
import com.pax.core.model.PageQueryParam;

public interface UserDao {
	
	void save(Map<String, Object> filterMap);
	
	List<User> list(PageQueryParam pageQueryParam);
	
	User get(String id);
	
	int getNextId();
	
	void update(Map<String, Object> filterMap);
	
	void frozen(String id,String frozentime);
	
	void unfrozen(String id);
	
	void delete(String id);
	
	void resetPwd(Map<String, Object> filterMap);
	
	void grantRoles(Map<String, Object> filterMap);
	
	void deleteUserRoles(String id);
	
	User login(String loginname);
	
	void updatePwd(Map<String, Object> filterMap);
	
	int getSiteId(String id);
	
	User findByLoginname(String loginname);
	
	void deleteUserRoleModels(String id);
	
	int getCountsByLoginName(String loginname);

	void updateLastlogintime(User user);

	List<User> listAll();

	void frozenUser(String id,String frozenreason,String frozentime);

	void cancel(String string);

	void frozenUser1(User user);

	void updateErrortimes(User user);
}

package com.pax.auth.dao;

import java.util.List;
import java.util.Map;

import com.pax.auth.entity.Role;
import com.pax.core.model.PageQueryParam;

public interface RoleDao {
	
	void save(Map<String, Object> filterMap);
	
	List<Role> list(PageQueryParam pageQueryParam);
	
	Role get(String id);
	
	int getNextId();
	
	void update(Map<String, Object> filterMap);
	
	void delete(String id);
	
	List<Role> getRolesByUser(String userId);
	
	List<Role> getRolesToUseByUser(Map<String, Object> filterMap);
	
	int getSiteId(String id);
	
	void deleteAllAuths(String string);
	
	void grantAuths(Map<String, Object> filterMap);
	
	int getUsersCount(String id);
	
	void deleteRoleAuth(String id);
	
	List<Role> getRolesOutofSite(int siteId);
}

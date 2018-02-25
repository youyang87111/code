package com.pax.auth.dao;

import java.util.List;
import java.util.Map;

import com.pax.auth.entity.RoleModel;
import com.pax.common.model.PageQueryParam;

public interface RoleModelDao {
	
	void save(Map<String, Object> filterMap);
	
	List<RoleModel> list(PageQueryParam pageQueryParam);
	
	RoleModel get(String id);
	
	int getNextId();
	
	void update(Map<String, Object> filterMap);
	
	List<RoleModel> getRolesByUser(String userId);
	
	List<RoleModel> getRolesToUseByUser(Map<String, Object> filterMap);
	
	int getSiteId(String id);
	
	void deleteAllAuths(String string);
	
	void grantAuths(Map<String, Object> filterMap);
	
	int getUsersCount(String id);
	
	void deleteRoleAuth(String id);
	
	List<RoleModel> getRolesOutofSite(int siteId);

	RoleModel getByTag(String m);
}

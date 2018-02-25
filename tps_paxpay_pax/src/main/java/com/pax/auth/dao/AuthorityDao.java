package com.pax.auth.dao;

import java.util.List;
import java.util.Map;

import com.pax.auth.entity.Authority;
import com.pax.core.model.PageQueryParam;

public interface AuthorityDao {
	
	void save(Map<String, Object> filterMap);
	
	List<Authority> list(PageQueryParam pageQueryParam);
	
	Authority get(String id);
	
	int getNextId();
	
	void update(Map<String, Object> filterMap);
	
	void delete(String id);
	
	int getSiteId(String id);
	
	void deleteAllResources(String id);
	
	void grantResources(Map<String, Object> filterMap);
	
	void deleteAuthFuncs(String id);
	
	void grantFuncs(Map<String, Object> filterMap);
	
	List<Authority> getAuthsToUseByUser(int userId);
	
	List<Authority> getAuthsByRole(String userId);
	
	List<Authority> getAuthsByUser(int userId);
	
	int getRolesCount(String id);
	
	List<Authority> getAuthsOutofSite(int siteId);
	
	List<Authority> getAuthsOfSite(int siteId);
	
	List<Authority> getAuthsByRoleModel(String id);
	
	List<Authority> getAuthsByUserByRoelModel(int id);
}

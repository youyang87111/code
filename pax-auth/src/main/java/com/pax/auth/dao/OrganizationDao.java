package com.pax.auth.dao;

import java.util.List;
import java.util.Map;

import com.pax.auth.entity.Organization;

public interface OrganizationDao {
	
	/**
	 * 得到某个站点下的所有机构
	 * @param ：site_id=站点id
	 * @return
	 */
	List<Organization> list(Map<String, Object> filterMap);
	
	Organization get(String id);
	
	/**
	 * 得到除某个站点外的所有机构
	 * @return 参数：siteId=站点id
	 */
	List<Organization> listAllOfSite(int siteId);

    int getNextId();

    int insert(Organization org);

	Organization findByMaps(Map params);

    int update(Organization org);

    int updateOrgName(Map orgParams);

    Organization findByOrgId(String orgId);

	List<Organization> findByUser(Map<String, Object> filterMap);
}

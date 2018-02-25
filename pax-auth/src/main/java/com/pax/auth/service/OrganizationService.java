package com.pax.auth.service;

import java.util.List;
import java.util.Map;

import com.pax.auth.entity.Organization;

public interface OrganizationService {
	
	/**
	 * 得到当前登陆用户的机构以及下级机构（如果是admin就展示完整的树）
	 *
	 * @return 参数：site_id=站点id
	 */
	List<Organization> list(Map<String, Object> filterMap);
	
	/**
	 * 得到某个机构
	 * @param id
	 * @return
	 */
	Organization get(String id);
	
	/**
	 * 得到除某个站点外的所有机构（可用于查询所有的机构，只要站点随意传一个値就行了）
	 *
	 * @return 参数：siteId=站点id
	 */
	List<Organization> listAllOfSite(int siteId);
	
	/**
	 * 列出指定平台指定机构的机构树形结构
	 *
	 * @param siteId
	 * @param orgId
	 * @return
	 */
	List<Organization> orgTree(String siteId, String orgId);
	
	Boolean showTop();
	
	List<Organization> listByAgentId(Map<String, Object> filterMap);
	
}

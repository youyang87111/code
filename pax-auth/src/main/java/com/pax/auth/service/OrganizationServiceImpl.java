package com.pax.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pax.auth.dao.OrganizationDao;
import com.pax.auth.entity.Organization;
import com.pax.auth.entity.Site;
import com.pax.auth.entity.User;
import com.pax.auth.util.AuthUtils;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Resource
	private OrganizationDao organizationDao;
	
	/**
	 * 得到当前登陆用户的机构以及下级机构（如果是admin就展示完整的树）
	 * @return 参数：site_id=站点id
	 */
	@Override
	public List<Organization> list(Map<String, Object> filterMap) {
		
		User user = AuthUtils.getUser();
		
		String siteId = "";
		if (!filterMap.containsKey("site_id")) {
			
			siteId = String.valueOf(user.getSite().getId());
			filterMap.put("site_id", siteId);
		} else {
			siteId = filterMap.get("site_id").toString();
		}
		
		List<Organization> allOrgs = organizationDao.list(filterMap);
		
		Organization parent = null;
		
		//如果是admin，寻找parent，就通过pid等于null来找到根节点
		if ("admin".equals(user.getLoginname())) {
			for (Organization organization : allOrgs) {
				if (organization.getParent() == null) {
					parent = organization;
					break;
				}
			}
		} else {
			
			Site site = user.getSite();
			//如果是平台操作员
			if ("P".equals(site.getTag())) {
				//如果是查询平台操作员所属站点的机构，则显示本机和下级机构
				if (String.valueOf(site.getId()).equals(siteId)) {
					//得到当前登陆用户的机构id
					int orgId = user.getOrg().getId();
					
					for (Organization organization : allOrgs) {
						if (organization.getId() == orgId) {
							parent = organization;
							break;
						}
					}
					//如果是查询其他站点的机构，则展示所有的
				} else {
					for (Organization organization : allOrgs) {
						if (organization.getParent() == null) {
							parent = organization;
							break;
						}
					}
				}
				
			} else {
				
				//得到当前登陆用户的机构id
				int orgPid = user.getOrg().getId();
				for (Organization organization : allOrgs) {
					if (organization.getId() == orgPid) {
						parent = organization;
						break;
					}
				}
				
			}
			
		}
		
		List<Organization> subOrgs = new ArrayList<Organization>();
		subOrgs.add(parent);
		
		//为这个机构设置下级机构
		setChilds(parent, allOrgs, subOrgs);
		
		return subOrgs;
	}
	
	/**
	 * 得到除某个站点外的所有机构
	 * @return 参数：siteId=站点id
	 */
	@Override
	public List<Organization> listAllOfSite(int siteId) {
		
		return organizationDao.listAllOfSite(siteId);
	}
	
	@Override
	public List<Organization> orgTree(String siteId, String orgId) {
		Map filterMap = new HashMap();
		filterMap.put("site_id", siteId);
		List<Organization> allOrgs = organizationDao.list(filterMap);
		Organization parent = organizationDao.findByOrgId(orgId);//parent为orgId自身
		List<Organization> subOrgs = new ArrayList<Organization>();
		subOrgs.add(parent);
		
		//为这个机构设置下级机构
		setChilds(parent, allOrgs, subOrgs);
		//得到当前登陆用户的机构id
		
		return subOrgs;
	}
	
	@Override
	public Boolean showTop() {
		boolean topindex = true;
		User user = AuthUtils.getUser();
		if ("admin".equals(user.getLoginname())) {
			topindex = false;
		} else {
			topindex = true;
		}
		return topindex;
	}
	
	@Override
	public List<Organization> listByAgentId(Map<String, Object> filterMap) {
		User user = AuthUtils.getUser();
		
		String siteId = "";
		if (!filterMap.containsKey("site_id")) {
			
			siteId = String.valueOf(user.getSite().getId());
			filterMap.put("site_id", siteId);
		} else {
			siteId = filterMap.get("site_id").toString();
			filterMap.put("site_id", siteId);
		}
		
		List<Organization> allOrgs = organizationDao.list(filterMap);
		
		Organization parent = null;
		
		//得到当前点击的机构Id
		int orgPid = Integer.valueOf(filterMap.get("agentId").toString());
		for (Organization organization : allOrgs) {
			if (organization.getId() == orgPid) {
				parent = organization;
				break;
			}
		}
		
		List<Organization> subOrgs = new ArrayList<Organization>();
		subOrgs.add(parent);
		
		//为这个机构设置下级机构
		setChilds(parent, allOrgs, subOrgs);
		
		return subOrgs;
	}
	
	private void setChilds(	Organization parent, List<Organization> allOrgs,
							List<Organization> subOrgs) {
		
		for (Organization organization : allOrgs) {
			//排除根机构，根机构没得parent
			if (organization.getParent() != null) {
				if (organization.getParent().getId() == parent.getId()) {
					subOrgs.add(organization);
					setChilds(organization, allOrgs, subOrgs);
				}
			}
			
		}
	}
	
	@Override
	public Organization get(String id) {
		return organizationDao.get(id);
	}
}

package com.pax.auth.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.Menu;
import com.pax.auth.entity.RoleModel;
import com.pax.common.model.PageQueryParam;

public interface RoleModelService {
	
	public void save(Map<String, Object> filterMap);
	
	public PageInfo<RoleModel> list(PageQueryParam pageQueryParam);
	
	public RoleModel get(String id);
	
	public void update(Map<String, Object> filterMap);
	
	/**
	 * 得到某个用户的所拥有的所有角色
	 * @param userId
	 * @return
	 */
	public List<RoleModel> getRolesByUser(String userId);
	
	/**
	 * 得到某个用户所有可以选择的角色，1、通过被分配用户所属的机构来过滤；
	 * 2、只能从创建者所拥有的本机机构的最大角色范围取；3、如果是平台的操作员，包含被分配用户的机构下的的所有权限
	 * @param 参数：id（用户id），search_name(按角色名称来过滤)
	 * @return
	 */
	public List<RoleModel> getRolesToUseByUser(Map<String, Object> filterMap);
	
	/**
	 * 得到某个角色可以选择的权限，1、通过角色所属站点来过滤；2、只能从创建者所拥有的最大权限范围取；3、如果是平台操作员，还可以包含其他站点的所有权限
	 * @return 可选权限是在包含在menu的auths中
	 * 
	 * 请求参数中包括 id
	 * 
	 */
	public List<Menu> getAuthsToUseByRole(Map<String, Object> filterMap);
	
	public void grantAuths(Map<String, Object> filterMap);
	
	/**
	 * 得到除某个站点以为的其他站点的角色
	 * @param siteId
	 * @return
	 */
	public List<RoleModel> getRolesOutofSite(int siteId);
	
}

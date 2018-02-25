package com.pax.auth.service;

import java.util.List;

import com.pax.auth.entity.Authority;
import com.pax.auth.entity.Function;
import com.pax.auth.entity.Menu;
import com.pax.auth.entity.OperateLog;
import com.pax.auth.entity.OperateLogParam;
import com.pax.auth.entity.Organization;
import com.pax.auth.entity.Role;
import com.pax.auth.entity.RoleModel;
import com.pax.auth.entity.Site;
import com.pax.auth.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 权限管理相关的对象的json格式化，放到这里统一管理
 *                       
 * @Filename AuthJsonService.java
 *
 * @Description 
 *
 * @Version 1.0
 *
 * @Author pax
 *
 * @Email 
 *       
 * @History
 *<li>Author: pax</li>
 *<li>Date: 2017年5月12日</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public interface AuthJsonService {
	
	public JSONArray getJSONArrayForAuth(List<Authority> result);
	
	public JSONObject getJson(Authority authority);
	
	public JSONArray getJSONArrayForFunc(List<Function> result);
	
	public JSONObject getJson(Function function);
	
	public JSONArray getJSONArrayForMenu(List<Menu> result);
	
	public JSONObject getJson(Menu menu);
	
	public JSONArray getJSONArrayForSite(List<Site> result);
	
	public JSONObject getJson(Site site);
	
	public JSONArray getJSONArrayForOrg(List<Organization> result);
	
	public JSONObject getJson(Organization organization);
	
	public JSONArray getJSONArrayForRole(List<Role> result);
	
	public JSONObject getJson(Role role);
	
	public JSONArray getJSONArrayForUser(List<User> result);
	
	public JSONObject getJson(User user);
	
	public JSONArray getJSONArrayForRoleModel(List<RoleModel> list);
	
	public JSONObject getJson(RoleModel roleModel);

	public JSONArray getJSONArrayForOperateLog(List<OperateLog> list);
	
	public JSONObject getJson(OperateLog operateLog);

	public JSONArray getJSONArrayForOperateLogParam(List<OperateLogParam> list);
	
	public JSONObject getJson(OperateLogParam operateLogParam);
}

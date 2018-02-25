package com.pax.auth.service;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

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
import com.pax.common.util.JSONUtils;
import com.pax.common.util.TranslationUtils;
import com.pax.common.util.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class AuthJsonServiceImpl implements AuthJsonService {
	
	@Override
	public JSONArray getJSONArrayForAuth(List<Authority> result) {
		JSONArray jsonArray = new JSONArray();
		for (Authority authority : result) {
			JSONObject jsonObject = getJson(authority);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(Authority authority) {
		
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		JSONObject jsonObject = JSONUtils.JSONFilter(authority, new String[] { "menu" });
		
		if (authority.getMenu() != null) {
			jsonObject.put("menu", JSONUtils.JSONFilter(authority.getMenu(),
				new String[] { "pmenu", "site", "funcs", "auths" }));
		}
		if (authority.getSite() != null) {
			jsonObject.put("site", getJson(authority.getSite()));
		}
		
		jsonObject.put("name", translation.__(authority.getName()));
		
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForFunc(List<Function> result) {
		JSONArray jsonArray = new JSONArray();
		for (Function function : result) {
			JSONObject jsonObject = getJson(function);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(Function function) {
		
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		JSONObject jsonObject = JSONUtils.JSONFilter(function, new String[] { "menu", "site" });
		
		if (function.getSite() != null) {
			jsonObject.put("site", getJson(function.getSite()));
		}
		
		if (function.getMenu() != null) {
			JSONObject menuJosn = JSONUtils.JSONFilter(function.getMenu(),
					new String[] { "pmenu", "site", "funcs", "auths" });
			
			menuJosn.put("name", translation.__(function.getMenu().getName()));
			jsonObject.put("menu",menuJosn);
		}
		
		jsonObject.put("name", translation.__(function.getName()));
		
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForMenu(List<Menu> result) {
		JSONArray jsonArray = new JSONArray();
		for (Menu menu : result) {
			JSONObject jsonObject = getJson(menu);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(Menu menu) {
		
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		JSONObject jsonObject = JSONUtils.JSONFilter(menu,
			new String[] { "site", "pmenu", "funcs", "auths" });
		
		if (menu.getPmenu() != null) {
			jsonObject.put("pId", menu.getPmenu().getId());
		} else {
			jsonObject.put("pId", "");
		}
		
		if (CollectionUtils.isNotEmpty(menu.getFuncs())) {
			jsonObject.put("funcs", getJSONArrayForFunc(menu.getFuncs()));
		} else {
			jsonObject.put("funcs", new JSONArray());
		}
		
		if (CollectionUtils.isNotEmpty(menu.getAuths())) {
			jsonObject.put("auths", getJSONArrayForAuth(menu.getAuths()));
		} else {
			jsonObject.put("auths", new JSONArray());
		}
		
		jsonObject.put("open", true);
		
		jsonObject.put("name", translation.__(menu.getName()));
		
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForSite(List<Site> result) {
		JSONArray jsonArray = new JSONArray();
		for (Site site : result) {
			JSONObject jsonObject = getJson(site);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(Site site) {
		
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		JSONObject jsonObject = JSONUtils.JSONFilter(site, new String[] {});
		
		jsonObject.put("name", translation.__(site.getName()));
		
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForOrg(List<Organization> result) {
		JSONArray jsonArray = new JSONArray();
		for (Organization org : result) {
			JSONObject jsonObject = getJson(org);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(Organization organization) {
		
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		JSONObject jsonObject = JSONUtils.JSONFilter(organization,
			new String[] { "parent", "site" });
		
		if (organization.getParent() != null) {
			jsonObject.put("pId", organization.getParent().getId());
		} else {
			jsonObject.put("pId", "");
		}
		
		jsonObject.put("open", true);
		
		jsonObject.put("name", translation.__(organization.getName()));
		
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForRole(List<Role> result) {
		
		JSONArray jsonArray = new JSONArray();
		for (Role role : result) {
			JSONObject jsonObject = getJson(role);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public JSONObject getJson(Role role) {
		
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		JSONObject jsonObject = JSONUtils.JSONFilter(role, new String[] { "org" });
		
		if (role.getSite() != null) {
			jsonObject.put("site", getJson(role.getSite()));
		}
		jsonObject.put("name", translation.__(role.getName()));
		
		JSONObject orgJson = JSONUtils.JSONFilter(role.getOrg(), new String[] { "parent", "site" });
		orgJson.put("name", translation.__(role.getOrg().getName()));
		
		jsonObject.put("org",orgJson);
		
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForUser(List<User> result) {
		
		JSONArray jsonArray = new JSONArray();
		for (User user : result) {
			JSONObject jsonObject = getJson(user);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public JSONObject getJson(User user) {
		
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		JSONObject jsonObject = JSONUtils.JSONFilter(user, new String[] { "org" });
		
		JSONObject orgJson = JSONUtils.JSONFilter(user.getOrg(), new String[] { "parent", "site" });
		orgJson.put("name", translation.__(user.getOrg().getName()));
		
		jsonObject.put("org",orgJson);
		
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForRoleModel(List<RoleModel> list) {
		JSONArray jsonArray = new JSONArray();
		for (RoleModel roleModel : list) {
			JSONObject jsonObject = getJson(roleModel);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(RoleModel roleModel) {
		
		JSONObject jsonObject = JSONUtils.JSONFilter(roleModel, new String[] {});
		
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForOperateLog(List<OperateLog> list) {
		JSONArray jsonArray = new JSONArray();
		for (OperateLog operateLog : list) {
			JSONObject jsonObject = getJson(operateLog);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(OperateLog operateLog) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(operateLog, new String[] {});
		if (StringUtils.isNotBlank(operateLog.getFlag())) {
			String flag = "";
			if ("0".equals(operateLog.getFlag())) {
				flag = translation.__("操作成功");
			} else {
				flag = translation.__("操作失败");
			}
			
			jsonObject.put("flag", flag);
			jsonObject.put("description", translation.__(operateLog.getDescription()));
		}
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForOperateLogParam(List<OperateLogParam> list) {
		JSONArray jsonArray = new JSONArray();
		for (OperateLogParam operateLogParam : list) {
			JSONObject jsonObject = getJson(operateLogParam);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(OperateLogParam operateLogParam) {
		JSONObject jsonObject = JSONUtils.JSONFilter(operateLogParam, new String[] {});
		return jsonObject;
	}
	
}

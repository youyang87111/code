package com.pax.auth.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.Authority;
import com.pax.auth.entity.Menu;
import com.pax.auth.entity.Role;
import com.pax.auth.inputparam.RoleAddInput;
import com.pax.auth.inputparam.RoleUpdateInput;
import com.pax.auth.service.AuthJsonService;
import com.pax.auth.service.AuthorityService;
import com.pax.auth.service.RoleService;
import com.pax.core.util.BindingResultHandler;
import com.pax.core.util.MapUtils;
import com.pax.core.web.BaseAjaxController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseAjaxController {
	
	@Resource
	private RoleService			roleService;
	
	@Resource
	private AuthorityService	authorityService;
	
	@Resource
	private AuthJsonService		authJsonService;
	
	@RequiresPermissions("role:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "auth/role";
	}
	
	@RequiresPermissions("role:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity save(@Validated RoleAddInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		roleService.save(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	
	@RequiresPermissions("role:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseEntity list() {
		
		initializePagingSortingFiltering();
		
		PageInfo<Role> page = roleService.list(pageQueryParam);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForRole(page.getList());
		
		JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresPermissions("role:detail")
	@RequestMapping(value = "/detail")
	@ResponseBody
	public ResponseEntity detail(String id) {
		
		Role role = roleService.get(id);
		
		JSONObject result = authJsonService.getJson(role);
		
		return new ResponseEntity(makeSuccessJson("操作成功", result), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("role:update")
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity update(@Validated RoleUpdateInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		roleService.update(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("role:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		roleService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	/**
	 * 得到某个角色可以选择的权限，1、通过角色所属站点来过滤；2、只能从创建者所拥有的最大权限范围取；3、如果是平台的操作员，还包含其他所有站点的所有权限
	 * @return 可选权限是在包含在menu的auths中
	 * 
	 * 请求参数中包括 id
	 * 
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/getAuthsToUseByRole")
	@ResponseBody
	public ResponseEntity getAuthsToUseByRole() {
		
		initializeFiltering();
		
		List<Menu> list = roleService.getAuthsToUseByRole(filterMap);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForMenu(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	/**
	 * 得到某个角色的所有权限，即角色已经选择了的权限
	 * @param id
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/getAuthsByRole")
	@ResponseBody
	public ResponseEntity getAuthsByRole(String id) {
		
		List<Authority> list = authorityService.getAuthsByRole(id);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForAuth(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	/**
	 * 为角色赋权限 
	 * @return  id:角色id，auths：权限数组
	 */
	@RequiresPermissions("role:grantAuths")
	@RequestMapping(value = "/grantAuths")
	@ResponseBody
	public ResponseEntity grantAuths() {
		
		initializeFiltering();
		
		roleService.grantAuths(filterMap);
		
		JSONObject result = makeSuccessJson("操作成功");
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
}

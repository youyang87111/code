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
import com.pax.auth.entity.Function;
import com.pax.auth.entity.Menu;
import com.pax.auth.inputparam.AuthorityAddInput;
import com.pax.auth.inputparam.AuthorityUpdateInput;
import com.pax.auth.service.AuthJsonService;
import com.pax.auth.service.AuthorityService;
import com.pax.auth.service.FunctionService;
import com.pax.auth.service.MenuService;
import com.pax.common.util.BindingResultHandler;
import com.pax.common.util.MapUtils;
import com.pax.common.web.BaseAjaxController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/authority")
public class AuthorityController extends BaseAjaxController {
	
	@Resource
	private AuthorityService	authorityService;
	
	@Resource
	private MenuService			menuService;
	
	@Resource
	private FunctionService		functionService;
	
	@Resource
	private AuthJsonService		authJsonService;
	
	@RequiresPermissions("auth:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "auth/authority";
	}
	
	@RequiresPermissions("auth:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity save(@Validated AuthorityAddInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		authorityService.save(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	
	@RequiresPermissions("auth:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseEntity list() {
		
		initializePagingSortingFiltering();
		
		PageInfo<Authority> page = authorityService.list(pageQueryParam);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForAuth(page.getList());
		
		JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresPermissions("auth:detail")
	@RequestMapping(value = "/detail")
	@ResponseBody
	public ResponseEntity detail(String id) {
		
		Authority authority = authorityService.get(id);
		
		JSONObject result = authJsonService.getJson(authority);
		
		return new ResponseEntity(makeSuccessJson("操作成功", result), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("auth:update")
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity update(@Validated AuthorityUpdateInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		authorityService.update(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("auth:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		authorityService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	/**
	 * 得到某个权限可以选择的功能，1、通过权限所属站点来过滤；
	 * @return 可选功能是在包含在menu的funcs中
	 * 
	 * 请求参数中包括 id:权限id
	 * 
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/getFuncsToUseByAuth")
	@ResponseBody
	public ResponseEntity getFuncsToUseByAuth() {
		
		initializeFiltering();
		
		List<Menu> list = authorityService.getFuncsToUseByAuth(filterMap);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForMenu(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	/**
	 * 得到某个权限已经选择的功能
	 * @param id 权限id
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/getFuncsByAuth")
	@ResponseBody
	public ResponseEntity getFuncsByAuth(String id) {
		
		List<Function> list = authorityService.getFuncsByAuth(id);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForFunc(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	/**
	 * 为权限分配功能  参数 id：权限id，funcs：功能
	 * @return 
	 */
	@RequiresAuthentication
	@RequiresPermissions("auth:grantFuncs")
	@RequestMapping(value = "/grantFuncs")
	@ResponseBody
	public ResponseEntity grantFuncs() {
		
		initializeFiltering();
		
		authorityService.grantFuncs(filterMap);
		
		JSONObject result = makeSuccessJson("操作成功");
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
}

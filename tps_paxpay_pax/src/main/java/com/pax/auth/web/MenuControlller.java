package com.pax.auth.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pax.auth.entity.Menu;
import com.pax.auth.entity.User;
import com.pax.auth.inputparam.MenuAddInput;
import com.pax.auth.inputparam.MenuUpdateInput;
import com.pax.auth.service.AuthJsonService;
import com.pax.auth.service.MenuService;
import com.pax.core.exception.BusinessException;
import com.pax.core.util.BindingResultHandler;
import com.pax.core.util.MapUtils;
import com.pax.core.web.BaseAjaxController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/menu")
public class MenuControlller extends BaseAjaxController {
	
	@Resource
	private MenuService		menuService;
	
	@Resource
	private AuthJsonService	authJsonService;
	
	@RequiresPermissions("menu:enter")
	@RequestMapping(value = "/enter")
	public String enter() {
		return "auth/menu";
	}
	
	@RequiresPermissions("menu:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity save(@Validated MenuAddInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		menuService.save(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	
	@RequiresPermissions("menu:detail")
	@RequestMapping(value = "/detail")
	@ResponseBody
	public ResponseEntity detail(String id) {
		
		if (StringUtils.isBlank(id)) {
			throw new BusinessException("菜单id不能为空");
		}
		
		Menu menu = menuService.get(id);
		
		JSONObject result = authJsonService.getJson(menu);
		
		return new ResponseEntity(makeSuccessJson("操作成功", result), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("menu:update")
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity update(@Validated MenuUpdateInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		menuService.update(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("menu:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String id) {
		
		if (StringUtils.isBlank(id)) {
			throw new BusinessException("菜单id不能为空");
		}
		
		menuService.delete(id);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	/**
	 * 得到当前登陆用户的菜单树(首页展示菜单使用)
	 */
	@RequiresAuthentication
	@RequestMapping("/getMenusByUser")
	@ResponseBody
	public ResponseEntity getMenusByUser() {
		
		User user = getUser();
		
		//得到要显示的菜单
		List<Menu> list = menuService.getMenusByUser(user);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForMenu(list);
		
		return new ResponseEntity(makeSuccessJson("操作成功", jsonArray), HttpStatus.OK);
		
	}
	
	@RequiresAuthentication
	@RequestMapping("/listAll")
	@ResponseBody
	public ResponseEntity listAll() {
		
		initializeFiltering();
		
		if (!filterMap.containsKey("site_id")) {
			throw new BusinessException("站点id不能为空");
		} else {
			String site_id = filterMap.get("site_id").toString();
			if (StringUtils.isBlank(site_id)) {
				throw new BusinessException("站点id不能为空");
			}
		}
		
		//目前得到的是所有菜单
		List<Menu> list = menuService.list(filterMap);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForMenu(list);
		
		return new ResponseEntity(makeSuccessJson("操作成功", jsonArray), HttpStatus.OK);
		
	}
	
}

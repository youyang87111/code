package com.pax.auth.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.Site;
import com.pax.auth.entity.User;
import com.pax.auth.inputparam.SiteAddInput;
import com.pax.auth.inputparam.SiteUpdateInput;
import com.pax.auth.service.AuthJsonService;
import com.pax.auth.service.SiteService;
import com.pax.core.util.BindingResultHandler;
import com.pax.core.util.MapUtils;
import com.pax.core.web.BaseAjaxController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/site")
@SuppressWarnings("all")
public class SiteController extends BaseAjaxController {
	
	@Resource
	private SiteService		siteService;
	
	@Resource
	private AuthJsonService	authJsonService;
	
	@RequiresPermissions("site:enter")
	@RequestMapping(value = "/enter")
	public String enter() {
		return "auth/site";
	}
	
	@RequiresPermissions("site:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity save(@Validated SiteAddInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		siteService.save(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	
	/**
	 * 分页查询使用
	 * @return
	 */
	@RequiresPermissions("site:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseEntity list() {
		
		initializePagingSortingFiltering();
		
		PageInfo<Site> page = siteService.list(pageQueryParam);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForSite(page.getList());
		
		JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	/**
	 * 下拉列表使用(根据用户所属站点来过滤,规则：商户管理平台站点的用户，可以查询所有站点，其余用户，只能查看自己归属的站点)
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public ResponseEntity listAll() {
		
		List<Site> list = null;
		
		User user = getUser();
		Site site = user.getSite();
		if ("P".equals(site.getTag())) {
			list = siteService.listAll();
		} else {
			list = new ArrayList<Site>();
			list.add(site);
		}
		
		JSONArray jsonArray = authJsonService.getJSONArrayForSite(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresPermissions("site:detail")
	@RequestMapping(value = "/detail")
	@ResponseBody
	public ResponseEntity detail(String id) {
		
		Site site = siteService.get(id);
		
		JSONObject result = authJsonService.getJson(site);
		
		return new ResponseEntity(makeSuccessJson("操作成功", result), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("site:update")
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity update(@Validated SiteUpdateInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		siteService.update(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("site:frozen")
	@RequestMapping(value = "/frozen")
	@ResponseBody
	public ResponseEntity frozen(String[] ids) {
		
		siteService.frozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("site:unfrozen")
	@RequestMapping(value = "/unfrozen")
	@ResponseBody
	public ResponseEntity unfrozen(String[] ids) {
		
		siteService.unfrozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	/**
	 * 暂时不提供站点删除功能
	 * @param ids
	 * @return
	 */
	//@RequiresPermissions("site:delete")
	//@RequestMapping(value = "/delete")
	//@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		siteService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
}

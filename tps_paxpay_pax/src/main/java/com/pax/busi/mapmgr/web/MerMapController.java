package com.pax.busi.mapmgr.web;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.pax.busi.common.service.BusiJsonService;
import com.pax.busi.mapmgr.entity.MerMap;
import com.pax.busi.mapmgr.input.MerMapAddInput;
import com.pax.busi.mapmgr.input.MerMapUpdateInput;
import com.pax.busi.mapmgr.service.MerMapService;
import com.pax.core.util.BindingResultHandler;
import com.pax.core.util.DateUtils;
import com.pax.core.web.BaseAjaxController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/merMap")
public class MerMapController extends BaseAjaxController {
	private static final Logger	logger	= LoggerFactory.getLogger(MerMapController.class);
	
	@Resource
	private BusiJsonService		busiJsonService;
	
	@Resource
	private MerMapService		merMapService;
	
	@RequiresPermissions("merMap:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "busi/mapMgr/merMap";
	}
	
	@RequiresPermissions("merMap:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity save(@Validated MerMapAddInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		input.setBuildoper(getUserLoginname());
		input.setBuilddatetime(DateUtils.getCurrentDateString());
		
		merMapService.save(input);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	
	@RequiresPermissions("merMap:update")
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity update(@Validated MerMapUpdateInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		input.setModifyoper(getUserLoginname());
		input.setModifydatetime(DateUtils.getCurrentDateString());
		merMapService.update(input);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("merMap:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseEntity list() {
		
		initializePagingSortingFiltering();
		
		PageInfo<MerMap> page = merMapService.list(pageQueryParam);
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForMerMap(page.getList());
		
		JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresPermissions("merMap:detail")
	@RequestMapping(value = "/detail")
	@ResponseBody
	public ResponseEntity detail(String id) {
		
		MerMap merMap = merMapService.get(id);
		
		JSONObject result = busiJsonService.getJson(merMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功", result), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("merMap:frozen")
	@RequestMapping(value = "/frozen")
	@ResponseBody
	public ResponseEntity frozen(String[] ids) {
		
		merMapService.frozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("merMap:unfrozen")
	@RequestMapping(value = "/unfrozen")
	@ResponseBody
	public ResponseEntity unfrozen(String[] ids) {
		
		merMapService.unfrozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("merMap:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		merMapService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("merMap:audit")
	@RequestMapping(value = "/audit")
	@ResponseBody
	public ResponseEntity audit() {
		
		initializeFiltering();
		
		merMapService.audit(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
}

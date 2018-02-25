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
import com.pax.busi.mapmgr.entity.BranchMapView;
import com.pax.busi.mapmgr.input.BranchMapAddInput;
import com.pax.busi.mapmgr.input.BranchMapUpdateInput;
import com.pax.busi.mapmgr.service.BranchMapService;
import com.pax.core.util.BindingResultHandler;
import com.pax.core.util.DateUtils;
import com.pax.core.web.BaseAjaxController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/branchMap")
public class BranchMapController extends BaseAjaxController {
	
	private static final Logger	logger	= LoggerFactory.getLogger(BranchMapController.class);
	
	@Resource
	private BranchMapService	branchMapService;
	
	@Resource
	private BusiJsonService		busiJsonService;
	
	@RequiresPermissions("branchMap:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "busi/mapMgr/branchMap";
	}
	
	@RequiresPermissions("branchMap:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity save(@Validated BranchMapAddInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		input.setBuildoper(getUserLoginname());
		input.setBuilddatetime(DateUtils.getCurrentDateString());
		branchMapService.save(input);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	
	@RequiresPermissions("branchMap:update")
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity update(@Validated BranchMapUpdateInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		input.setModifyoper(getUserLoginname());
		input.setModifydatetime(DateUtils.getCurrentDateString());
		branchMapService.update(input);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("branchMap:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseEntity list() {
		
		initializePagingSortingFiltering();
		
		PageInfo<BranchMapView> page = branchMapService.list(pageQueryParam);
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForBranchMap(page.getList());
		
		JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresPermissions("branchMap:detail")
	@RequestMapping(value = "/detail")
	@ResponseBody
	public ResponseEntity detail(String id) {
		
		BranchMapView branchMap = branchMapService.get(id);
		
		JSONObject result = busiJsonService.getJson(branchMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功", result), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("branchMap:frozen")
	@RequestMapping(value = "/frozen")
	@ResponseBody
	public ResponseEntity frozen(String[] ids) {
		
		branchMapService.frozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("branchMap:unfrozen")
	@RequestMapping(value = "/unfrozen")
	@ResponseBody
	public ResponseEntity unfrozen(String[] ids) {
		
		branchMapService.unfrozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("branchMap:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		branchMapService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("branchMap:audit")
	@RequestMapping(value = "/audit")
	@ResponseBody
	public ResponseEntity audit() {
		
		initializeFiltering();
		
		branchMapService.audit(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	
}

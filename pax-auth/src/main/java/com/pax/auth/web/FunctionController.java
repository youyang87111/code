package com.pax.auth.web;

import java.util.Map;

import javax.annotation.Resource;

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
import com.pax.auth.entity.Function;
import com.pax.auth.inputparam.FunctionAddInput;
import com.pax.auth.inputparam.FunctionUpdateInput;
import com.pax.auth.service.AuthJsonService;
import com.pax.auth.service.FunctionService;
import com.pax.common.util.BindingResultHandler;
import com.pax.common.util.MapUtils;
import com.pax.common.web.BaseAjaxController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/function")
public class FunctionController extends BaseAjaxController {
	
	@Resource
	private FunctionService	functionService;
	
	@Resource
	private AuthJsonService	authJsonService;
	
	@RequiresPermissions("func:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "auth/function";
	}
	
	@RequiresPermissions("func:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity save(@Validated FunctionAddInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		functionService.save(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	
	@RequiresPermissions("func:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseEntity list() {
		
		initializePagingSortingFiltering();
		
		PageInfo<Function> page = functionService.list(pageQueryParam);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForFunc(page.getList());
		
		JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresPermissions("func:detail")
	@RequestMapping(value = "/detail")
	@ResponseBody
	public ResponseEntity detail(String id) {
		
		Function function = functionService.get(id);
		
		JSONObject result = authJsonService.getJson(function);
		
		return new ResponseEntity(makeSuccessJson("操作成功", result), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("func:update")
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity update(@Validated FunctionUpdateInput input, BindingResult br) {
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		Map<String, Object> filterMap = MapUtils.beanToMap(input);
		
		functionService.update(filterMap);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("func:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		functionService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
}

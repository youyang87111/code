package com.pax.auth.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.OperateLog;
import com.pax.auth.entity.OperateLogParam;
import com.pax.auth.service.AuthJsonService;
import com.pax.auth.service.OperateLogService;
import com.pax.core.web.BaseAjaxController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/log")
public class OperateLogController extends BaseAjaxController {
	
	@Resource
	private OperateLogService	logService;
	
	@Resource
	private AuthJsonService		authJsonService;
	
	@RequiresPermissions("log:enter")
	@RequestMapping(value = "/enter")
	public String enter() {
		return "auth/log";
	}
	
	/**
	 * 分页查询日志
	 * @return
	 */
	@RequiresPermissions("log:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseEntity list() {
		
		initializePagingSortingFiltering();
		
		Map<String, Object> filterMap = pageQueryParam.getFilterMap();
		String operatetime = filterMap.get("operatetime").toString().replace("-", "");
		if (operatetime != null && !"".equals(operatetime)) {
			filterMap.put("operatedate", operatetime);
		}
		pageQueryParam.setFilterMap(filterMap);
		PageInfo<OperateLog> page = logService.list(pageQueryParam);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForOperateLog(page.getList());
		
		JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresPermissions("log:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		logService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
	}
	
	@RequiresPermissions("log:getParamsById")
	@RequestMapping(value = "/getParamsById")
	@ResponseBody
	public ResponseEntity getParamsById(String search_id) {
		
		List<OperateLogParam> list = logService.getParamsById(search_id);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForOperateLogParam(list);
		
		JSONObject result = makeDataTableArrayJson(sEcho, list.size(), jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
}

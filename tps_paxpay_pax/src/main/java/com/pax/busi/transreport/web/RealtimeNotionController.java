package com.pax.busi.transreport.web;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.pax.busi.common.service.BusiJsonService;
import com.pax.busi.transreport.entity.RealtimeNotion;
import com.pax.busi.transreport.service.RealtimeNotionService;
import com.pax.core.web.BaseAjaxController;

@Controller
@RequestMapping("/realtimenotion")
@SuppressWarnings("all")
public class RealtimeNotionController extends BaseAjaxController{
	
	@Resource
	private RealtimeNotionService	realtimeNotionService;
	
	@Resource 
	private BusiJsonService busiJsonService;
	
	//@RequiresPermissions("realtimenotion:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "busi/transReport/realtimenotion";
	}
	
	//@RequiresPermissions("realtimenotion:list")
	@RequestMapping(value = "/list")
	public ResponseEntity list() {
		
		initializePagingSortingFiltering();
		
		PageInfo<RealtimeNotion> page = realtimeNotionService.list(pageQueryParam);
            
		JSONArray array = busiJsonService.getJSONArrayForRealtimeNotion(page.getList());
    		
		JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), array);
		
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	//@RequiresPermissions("realtimenotion:resend")
	@RequestMapping(value = "/resend")
	@ResponseBody
	public ResponseEntity resend(String transactionNo) {
		
		realtimeNotionService.resend(transactionNo);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
}

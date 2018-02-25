package com.pax.busi.resourcemgr.web;


import java.util.List;

import javax.annotation.Resource;







import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.pax.busi.common.service.BusiJsonService;
import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.busi.resourcemgr.input.CposMerAddInput;
import com.pax.busi.resourcemgr.input.CposMerUpdateInput;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
import com.pax.busi.resourcemgr.service.CposMerService;
import com.pax.core.exception.BusinessException;
import com.pax.core.util.BindingResultHandler;
import com.pax.core.util.DateUtils;
import com.pax.core.util.JSONHelper;
import com.pax.core.util.JSONUtils;
import com.pax.core.util.WebUtils;
import com.pax.core.web.BaseAjaxController;


@Controller
@RequestMapping("/cposMer")
@SuppressWarnings("all")
public class CposMerController extends BaseAjaxController{
	
	@Resource
	private CposMerService		cposMerService;
	
	@Resource
	private BusiJsonService busiJsonService;
	
	
	@RequiresPermissions("cposMer:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "busi/resourceMgr/cposMer";
	}
	
	@RequiresPermissions("cposMer:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity save(@Validated CposMerAddInput input,BindingResult br){
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
        cposMerService.save(input);
        
        return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
	}
	
	
	@RequiresPermissions("cposMer:update")
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity update(@Validated CposMerUpdateInput input,BindingResult br){
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
        cposMerService.update(input);
        
        return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
	}
	
	@RequiresPermissions("cposMer:detail")
    @RequestMapping(value = "/detail")
	@ResponseBody
    public void detail(@RequestParam("id") String id) {
		
        CposMer cposMer = cposMerService.detail(id);
        
        JSONObject jObject = busiJsonService.getJson(cposMer);
        
        jObject = makeSuccessJson("操作成功",jObject);
        
        response(jObject.toString());
    }
	
	/**
	 * 分页查询使用
	 * @return
	 */
	@RequiresPermissions("cposMer:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public void list(){
		
        initializePagingSortingFiltering();
        
        PageInfo<CposMer> page = cposMerService.list(pageQueryParam);
        
        JSONArray array = busiJsonService.getJSONArrayForCposMer(page.getList());
        
        JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), array);
        
        response(result.toString());
        
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public ResponseEntity listAll() {
		
		List<CposMer> list = cposMerService.listAll();
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForCposMer(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	
	
	@RequiresPermissions("cposMer:audit")
    @RequestMapping(value = "/audit")
    @ResponseBody
    public ResponseEntity audit(@RequestParam("id")String id,
                                @RequestParam("passStatu") String audit) {
        
		cposMerService.audit(id, audit);
        
        return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
    }
	
	@RequiresPermissions("cposMer:frozen")
	@RequestMapping(value = "/frozen")
	@ResponseBody
	public ResponseEntity frozen(String[] ids) {
		
		cposMerService.frozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("cposMer:unfrozen")
	@RequestMapping(value = "/unfrozen")
	@ResponseBody
	public ResponseEntity unfrozen(String[] ids) {
		
		cposMerService.unfrozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("cposMer:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		cposMerService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	@RequiresPermissions("cposMer:import")
	@RequestMapping(value = "/import")
	@ResponseBody
	public ResponseEntity importCposMer(@Validated MerTermImportInput	importInput) {
		
		importInput.setBuildoper(WebUtils.getUserName());
		
		importInput.setBuilddatetime(DateUtils.getCurrentDateString());
		
		cposMerService.importInput(importInput);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
}

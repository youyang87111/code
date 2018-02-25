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
import com.pax.busi.resourcemgr.entity.CposTerm;
import com.pax.busi.resourcemgr.input.CposTermAddInput;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
import com.pax.busi.resourcemgr.service.CposTermService;
import com.pax.core.exception.BusinessException;
import com.pax.core.util.BindingResultHandler;
import com.pax.core.util.DateUtils;
import com.pax.core.util.JSONHelper;
import com.pax.core.util.WebUtils;
import com.pax.core.web.BaseAjaxController;

@Controller
@RequestMapping("/cposTerm")
@SuppressWarnings("all")
public class CposTermController extends BaseAjaxController{
	
	@Resource
	private CposTermService cposTermService;
	@Resource
	private BusiJsonService busiJsonService;
	
	@RequiresPermissions("cposTerm:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "busi/resourceMgr/cposTerm";
	}
	
	@RequiresPermissions("cposTerm:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity save(@Validated CposTermAddInput input,BindingResult br){
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		} 
		
		cposTermService.save(input);
		
        return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
	}
	
	
	@RequiresPermissions("cposTerm:detail")
    @RequestMapping(value = "/detail")
	@ResponseBody
    public void detail(@RequestParam("id") String id) {
    	
		CposTerm cposTerm = cposTermService.detail(id);
    	
		JSONObject jObject = busiJsonService.getJson(cposTerm);
		
		jObject = makeSuccessJson("操作成功",jObject);
		
		response(jObject.toString());
    }
	
	/**
	 * 分页查询使用
	 * @return
	 */
	@RequiresPermissions("cposTerm:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public void list(){
		
        initializePagingSortingFiltering();
        
        PageInfo<CposTerm> page = cposTermService.list(pageQueryParam);
        
        JSONArray array = busiJsonService.getJSONArrayForCposTerm(page.getList());
        
        JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), array);
        
        response(result.toString());
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public ResponseEntity listAll() {
		
		List<CposTerm> list = cposTermService.listAll();
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForCposTerm(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	@RequiresPermissions("cposTerm:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		cposTermService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("cposTerm:audit")
    @RequestMapping(value = "/audit")
    @ResponseBody
    public ResponseEntity audit(@RequestParam("id")String id,
    							@RequestParam("passStatu") String audit) {
    	
		cposTermService.audit(id, audit);
        
		return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
    }
	
	@RequiresPermissions("cposTerm:frozen")
	@RequestMapping(value = "/frozen")
	@ResponseBody
	public ResponseEntity frozen(String[] ids) {
		
		cposTermService.frozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("cposTerm:unfrozen")
	@RequestMapping(value = "/unfrozen")
	@ResponseBody
	public ResponseEntity unfrozen(String[] ids) {
		
		cposTermService.unfrozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	@RequiresPermissions("cposTerm:import")
	@RequestMapping(value = "/import")
	@ResponseBody
	public ResponseEntity importCposTerm(@Validated MerTermImportInput	importInput) {
		
		importInput.setBuildoper(WebUtils.getUserName());
		
		importInput.setBuilddatetime(DateUtils.getCurrentDateString());
		
		cposTermService.importInput(importInput);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	@RequiresPermissions("cposTerm:createKey")
	@RequestMapping(value = "/createKey")
	@ResponseBody
	public ResponseEntity createKey(String[] ids) {
		
		cposTermService.createKey(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("cposTerm:exportKey")
	@RequestMapping(value = "/exportKey")
	@ResponseBody
	public ResponseEntity exportKey(String[] ids) {
		
		cposTermService.exportKey(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("cposTerm:resetKey")
	@RequestMapping(value = "/resetKey")
	@ResponseBody
	public ResponseEntity resetKey(@RequestParam("id") String id){
		
		cposTermService.resetKey(id);
		
        return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
	}
	
}

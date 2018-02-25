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
import com.pax.busi.resourcemgr.entity.RMer;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
import com.pax.busi.resourcemgr.input.RMerAddInput;
import com.pax.busi.resourcemgr.input.RMerUpdateInput;
import com.pax.busi.resourcemgr.service.RMerService;
import com.pax.core.exception.BusinessException;
import com.pax.core.util.BindingResultHandler;
import com.pax.core.util.DateUtils;
import com.pax.core.util.JSONHelper;
import com.pax.core.util.WebUtils;
import com.pax.core.web.BaseAjaxController;


@Controller
@RequestMapping("/rMer")
@SuppressWarnings("all")
public class RMerController extends BaseAjaxController{
	
	@Resource 
	private RMerService rMerService;
	@Resource
	private BusiJsonService busiJsonService;
	
	@RequiresPermissions("rMer:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "busi/resourceMgr/rMer";
	}
	
	@RequiresPermissions("rMer:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity save(@Validated RMerAddInput input,BindingResult br){
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		rMerService.save(input);
		
        return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
       
	}
	
	/**
	 * 分页查询使用
	 * @return
	 */
	@RequiresPermissions("rMer:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public void list(){
		
        initializePagingSortingFiltering();
        
        PageInfo<RMer> page = rMerService.list(pageQueryParam);
        
        JSONArray array = busiJsonService.getJSONArrayForRMer(page.getList());
        
        JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), array);
        
        response(result.toString());
	}

	@RequiresAuthentication
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public ResponseEntity listAll() {
		
		List<RMer> list = rMerService.listAll();
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForRMer(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@RequiresPermissions("rMer:update")
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity update(@Validated RMerUpdateInput input,BindingResult br){
       
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		rMerService.update(input);
        
		return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
	}
	
	@RequiresPermissions("rMer:detail")
    @RequestMapping(value = "/detail")
	@ResponseBody
    public void detail(@RequestParam("id") String id) {
        
		RMer rMer = rMerService.detail(id);
        
		JSONObject jObject = busiJsonService.getJson(rMer);
		
		jObject = makeSuccessJson("操作成功",jObject);
        
		response(jObject.toString());
    }
	
	@RequiresPermissions("rMer:audit")
    @RequestMapping(value = "/audit")
    @ResponseBody
    public ResponseEntity audit(@RequestParam("id")String id,
    							@RequestParam("passStatu") String audit) {
        
		rMerService.audit(id, audit);
        
        return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
    }
	
	@RequiresPermissions("rMer:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		rMerService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("rMer:frozen")
	@RequestMapping(value = "/frozen")
	@ResponseBody
	public ResponseEntity frozen(@RequestParam("ids")String[] ids) {
		
		rMerService.frozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("rMer:unfrozen")
	@RequestMapping(value = "/unfrozen")
	@ResponseBody
	public ResponseEntity unfrozen(String[] ids) {
		
		rMerService.unfrozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("rMer:import")
	@RequestMapping(value = "/import")
	@ResponseBody
	public ResponseEntity importRMer(@Validated MerTermImportInput	importInput) {
		
		importInput.setBuildoper(WebUtils.getUserName());
		
		importInput.setBuilddatetime(DateUtils.getCurrentDateString());
		
		rMerService.importInput(importInput);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
}

package com.pax.busi.resourcemgr.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.Logger;

import com.alipay.api.internal.util.StringUtils;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.User;
import com.pax.busi.common.service.BusiJsonService;
import com.pax.busi.resourcemgr.entity.RMer;
import com.pax.busi.resourcemgr.entity.RTerm;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
import com.pax.busi.resourcemgr.input.RMerAddInput;
import com.pax.busi.resourcemgr.input.RTermAddInput;
import com.pax.busi.resourcemgr.input.RTermUpdateInput;
import com.pax.busi.resourcemgr.input.SecretInput;
import com.pax.busi.resourcemgr.service.RTermService;
import com.pax.core.exception.BusinessException;
import com.pax.core.util.BindingResultHandler;
import com.pax.core.util.DateUtils;
import com.pax.core.util.FileUtils;
import com.pax.core.util.JSONHelper;
import com.pax.core.util.WebUtils;
import com.pax.core.web.BaseAjaxController;


@Controller
@RequestMapping("/rTerm")
@SuppressWarnings("all")
public class RTermController extends BaseAjaxController{
	private Log log = LogFactory.getLog(RTermController.class);
	
	@Resource
	private RTermService rTermService;
	
	@Resource 
	private BusiJsonService busiJsonService;
	
	
	@RequiresPermissions("rTerm:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "busi/resourceMgr/rTerm";
	}
	
	/**
	 * 分页查询使用
	 * @return
	 */
	@RequiresPermissions("rTerm:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public void list(){
		
        initializePagingSortingFiltering();
       
        PageInfo<RTerm> page = rTermService.list(pageQueryParam);
        
        JSONArray array = busiJsonService.getJSONArrayForRTerm(page.getList());
        
        JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), array);
        
        response(result.toString());
	}

	@RequiresAuthentication
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public ResponseEntity listAll() {
		
		List<RTerm> list = rTermService.listAll();
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForRTerm(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	@RequiresPermissions("rTerm:detail")
    @RequestMapping(value = "/detail")
	@ResponseBody
    public void detail(@RequestParam("id") String id) {
        
		RTerm rTerm = rTermService.detail(id);
        
		JSONObject jObject = busiJsonService.getJson(rTerm);
        
		jObject = makeSuccessJson("操作成功",jObject);
		
		response(jObject.toString());
    }
	
	@RequiresPermissions("rTerm:save")
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseEntity addNewRTerm(@Validated RTermAddInput input,BindingResult br){
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		rTermService.save(input);
		
        return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
	}
	
	
	@RequiresPermissions("rTerm:update")
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity update(@Validated RTermUpdateInput input,BindingResult br){
		
		if (br.hasErrors()) {
			JSONObject errorJson = BindingResultHandler.handleBindingResult(br);
			return new ResponseEntity(makeFailJson(errorJson), HttpStatus.OK);
		}
		
		rTermService.update(input);
        
		return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
	}
	
	@RequiresPermissions("rTerm:audit")
    @RequestMapping(value = "/audit")
    @ResponseBody
    public ResponseEntity audit(@RequestParam("id") String id,
    							@RequestParam("passStatu") String audit) {
        
		rTermService.audit(id, audit);
       
		return new ResponseEntity(WebUtils.makeSuccessFastObj("操作成功"), HttpStatus.OK);
    }
	
	@RequiresPermissions("rTerm:frozen")
	@RequestMapping(value = "/frozen")
	@ResponseBody
	public ResponseEntity frozen(String[] ids) {
		
		rTermService.frozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("rTerm:unfrozen")
	@RequestMapping(value = "/unfrozen")
	@ResponseBody
	public ResponseEntity unfrozen(String[] ids) {
		
		rTermService.unfrozen(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("rTerm:delete")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity delete(String[] ids) {
		
		rTermService.delete(ids);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("rTerm:import")
	@RequestMapping(value = "/import")
	@ResponseBody
	public ResponseEntity importRMer(@Validated MerTermImportInput	importInput) {
		
		importInput.setBuildoper(WebUtils.getUserName());
		
		importInput.setBuilddatetime(DateUtils.getCurrentDateString());
		
		rTermService.importInput(importInput);
		
		return new ResponseEntity(makeSuccessJson("操作成功"), HttpStatus.OK);
		
	}
	
	@RequiresPermissions("rTerm:importKey")
	@RequestMapping(value = "/importKeys")
	@ResponseBody
	public ResponseEntity importKeys(@Validated SecretInput input) throws Exception {
		
		InputStream ins = input.getFile().getInputStream();
		
		InputStreamReader isr = new InputStreamReader(ins);
		
		BufferedReader reader = new BufferedReader(isr);
        
		List<String> listStr = new LinkedList<String>();
        
        try {
			String lineTxt = null;
            while((lineTxt = reader.readLine()) != null){
            	listStr.add(lineTxt);
            }
		} finally {
				reader.close();
		}
		User user = new User();
		user.setName(WebUtils.getUser().getName());
		user.setBuilddatetime(DateUtils.getCurrentDateString());
		String msg = null;
		try {
			listStr.remove(0);
			msg = rTermService.importKeys(listStr, input.getMcr(), user);
			log.info(WebUtils.getUser().getName() + " import key success." + input.getMcr());
			return new ResponseEntity(WebUtils.makeSuccessFastObj(msg), HttpStatus.OK);
		} catch (Exception e) {
			log.info(WebUtils.getUser().getName() + " import key fail.", e);
			throw new BusinessException("导入失败");
		}
		
	}
	
	
	@RequiresPermissions("rTerm:exchangeKey")
	@RequestMapping(value = "/exchangeKey")
	@ResponseBody
	public ResponseEntity exchangeKey(){
		
		rTermService.exchangeKey();
		
		return new ResponseEntity(WebUtils.makeSuccessFastObj("转换第三方密钥成功"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/download")
	@ResponseBody
	public ResponseEntity download() throws Exception{
		
		String path = WebUtils.getRequest().getRealPath("/batchTemplate/");
		
		File file = new File(path+File.separator+"SecretTemplate.txt");
	    
		byte[] body = null;
	    
		InputStream is = new FileInputStream(file);
	   
		body = new byte[is.available()];
	    
		is.read(body);
	    
		HttpHeaders headers = new HttpHeaders();
	   
		headers.add("Content-Disposition", "attchement;filename=" + file.getName());
	    
	    HttpStatus statusCode = HttpStatus.OK;
	    
	    ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
	   
	    return entity;
	}
	
}

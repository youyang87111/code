package com.pax.busi.common.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pax.auth.service.OrganizationService;
import com.pax.busi.common.entity.AppClass;
import com.pax.busi.common.entity.BankInfo;
import com.pax.busi.common.entity.Bmf;
import com.pax.busi.common.entity.Branch;
import com.pax.busi.common.entity.CardType;
import com.pax.busi.common.entity.Inntransdef;
import com.pax.busi.common.entity.Language;
import com.pax.busi.common.entity.Mcr;
import com.pax.busi.common.entity.Mmf;
import com.pax.busi.common.entity.Tmf;
import com.pax.busi.common.service.BusiJsonService;
import com.pax.busi.common.service.CommonService;
import com.pax.core.util.WebUtils;
import com.pax.core.web.BaseAjaxController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseAjaxController {
	
	
	@Resource
	private CommonService	commonService;
	
	@Resource
	private BusiJsonService	busiJsonService;
	
	@Resource
	private OrganizationService organizationService;
	
	@RequiresAuthentication
	@RequestMapping(value = "/listAppClass")
	@ResponseBody
	public ResponseEntity listAppClass(String classgroup) {
		
		List<AppClass> list = commonService.listAppClass(classgroup);
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForAppClass(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/listBankInfo")
	@ResponseBody
	public ResponseEntity listBankInfo() {
		
		List<BankInfo> list = commonService.listBankInfo();
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForBankInfo(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/listBmf")
	@ResponseBody
	public ResponseEntity listBmf() {
		
		List<Bmf> list = commonService.listBmf();
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForBmf(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/listBranch")
	@ResponseBody
	public ResponseEntity listBranch(String type) {
		
		List<Branch> list = commonService.listBranch(type);
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForBranch(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/listCardType")
	@ResponseBody
	public ResponseEntity listCardType() {
		
		List<CardType> list = commonService.listCardType();
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForCardType(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/listInntransdef")
	@ResponseBody
	public ResponseEntity listInntransdef(String classgroup) {
		
		List<Inntransdef> list = commonService.listInntransdef(classgroup);
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForInntransdef(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/listMcr")
	@ResponseBody
	public ResponseEntity listMcr(String type) {
		
		List<Mcr> list = commonService.listMcr(type);
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForMcr(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/listMmf")
	@ResponseBody
	public ResponseEntity listMmf() {
		
		List<Mmf> list = commonService.listMmf();
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForMmf(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/listTmf")
	@ResponseBody
	public ResponseEntity listTmf() {
		
		List<Tmf> list = commonService.listTmf();
		
		JSONArray jsonArray = busiJsonService.getJSONArrayForTmf(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/locale/dropdown", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findLocales() {
		
        List<Language> list= commonService.findLocales();
        
        JSONArray jsonArray = busiJsonService.getJSONArrayForLanguage(list);
		
		JSONObject result = makeSuccessJson("操作成功", jsonArray);
		
        return new ResponseEntity(WebUtils.makeSuccessFastObj(null, result), HttpStatus.OK);
    }
	
    @RequestMapping(value = "/locale/switch", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity switchLocales(@RequestParam("locale") String locale) {
        
    	String res = commonService.changeLocale(locale);
        
    	JSONObject obj = new JSONObject();
        
    	obj.put("locale", res);
        
    	return new ResponseEntity(WebUtils.makeSuccessFastObj("切换成功", obj), HttpStatus.OK);
    }
	
}

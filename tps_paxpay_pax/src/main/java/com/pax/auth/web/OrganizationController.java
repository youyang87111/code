package com.pax.auth.web;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pax.auth.entity.Organization;
import com.pax.auth.service.AuthJsonService;
import com.pax.auth.service.OrganizationService;
import com.pax.core.web.BaseAjaxController;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseAjaxController {
	
	@Resource
	private OrganizationService	organizationService;
	
	@javax.annotation.Resource
	private AuthJsonService		authJsonService;
	
	@RequiresAuthentication
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "auth/organization";
	}
	
	/**
	 * 得到当前登陆用户的机构以及下级机构（如果是admin就展示完整的树）
	 * @return 参数：site_id=站点id
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseEntity list() {
		
		initializeFiltering();
		
		List<Organization> list = organizationService.list(filterMap);
		
		JSONArray jsonArray = authJsonService.getJSONArrayForOrg(list);
		
		return new ResponseEntity(makeSuccessJson("操作成功", jsonArray), HttpStatus.OK);
		
	}
	@RequiresAuthentication
	@RequestMapping(value = "/showtop",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity showTop(){
		
		Boolean topIndex = organizationService.showTop();
		
		JSONObject result = new JSONObject();
		
		result.put("topIndex",topIndex);
		
		return new ResponseEntity(makeSuccessJson("操作成功",result),HttpStatus.OK);
	}
}

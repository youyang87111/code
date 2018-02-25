package com.pax.busi.transreport.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.pax.busi.common.service.BusiJsonService;
import com.pax.busi.transreport.entity.Cposhistrans;
import com.pax.busi.transreport.service.CposhistransService;
import com.pax.core.util.WebUtils;
import com.pax.core.web.BaseAjaxController;



@Controller
@RequestMapping("/cposhistrans")
@SuppressWarnings("all")
public class CposhistransController extends BaseAjaxController{
	
	@Resource
	private CposhistransService	cposhistransService;
	
	@Resource 
	private BusiJsonService busiJsonService;
	
	//@RequiresPermissions("cposhistrans:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "busi/transReport/cposhistrans";
	}
	
	//@RequiresPermissions("cposhistrans:export")
    @RequestMapping(value = "/export")
    public ResponseEntity export(){
		
		HttpServletRequest request = getRequest();
		/* filtering */
		filterMap = org.springframework.web.util.WebUtils.getParametersStartingWith(request, "search_");
		for (String key : filterMap.keySet()) {
			if (StringUtils.isNotBlank(filterMap.get(key).toString())) {
				filterMap.put(key, filterMap.get(key).toString().trim().replace("'", "''")
					.replace("[", "\\[").replace("%", "\\%").replace("_", "\\_").replace("^", "\\^")
				);
			}
		}
		
		pageQueryParam.setFilterMap(filterMap);
		
		cposhistransService.exportForExcel(pageQueryParam);
		
		return new ResponseEntity(WebUtils.makeSuccessFastObj("导出成功"), HttpStatus.OK);
    }
	
	//@RequiresPermissions("cposhistrans:export")
    @RequestMapping(value = "/exportCount")
    public ResponseEntity exportCount() {
		
		HttpServletRequest request = getRequest();
		/* filtering */
		filterMap = org.springframework.web.util.WebUtils.getParametersStartingWith(request, "search_");
		for (String key : filterMap.keySet()) {
			if (StringUtils.isNotBlank(filterMap.get(key).toString())) {
				filterMap.put(key, filterMap.get(key).toString().trim().replace("'", "''")
					.replace("[", "\\[").replace("%", "\\%").replace("_", "\\_").replace("^", "\\^")
				);
			}
		}
		
		pageQueryParam.setFilterMap(filterMap);
		
		long count = cposhistransService.exportCount(pageQueryParam);
		
		return new ResponseEntity(WebUtils.makeSuccessObj(Long.toString(count)), HttpStatus.OK);
    }
	
	//@RequiresPermissions("cposhistrans:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public void list(){
		
		initializePagingSortingFiltering();
		
		PageInfo<Cposhistrans> page = cposhistransService.list(pageQueryParam);
		
		JSONArray array = busiJsonService.getJSONArrayForCposhistrans(page.getList());
		
		JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), array);
    		
		response(result.toString());
	}
	
	

}

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
import com.pax.busi.transreport.entity.Cposhislog;
import com.pax.busi.transreport.service.CposhislogService;
import com.pax.core.util.WebUtils;
import com.pax.core.web.BaseAjaxController;

@Controller
@RequestMapping("/cposhislog")
@SuppressWarnings("all")
public class CposhislogController extends BaseAjaxController{
	
	@Resource
	private CposhislogService cposhislogService;
	
	@Resource 
	private BusiJsonService busiJsonService;
	
	//@RequiresPermissions("cposhislog:enter")
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter() {
		return "busi/transReport/cposhislog";
	}
	
	//@RequiresPermissions("cposhislog:export")
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
		
		cposhislogService.exportForExcel(pageQueryParam);
		
		return new ResponseEntity(WebUtils.makeSuccessFastObj("导出成功"), HttpStatus.OK);
    }
	
	//@RequiresPermissions("cposhislog:export")
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
		
		long count = cposhislogService.exportCount(pageQueryParam);
		
		return new ResponseEntity(WebUtils.makeSuccessObj(Long.toString(count)), HttpStatus.OK);
    }
	
	//@RequiresPermissions("cposhistrans:list")
	@RequestMapping(value = "/list")
	@ResponseBody
	public void list(){
		
		initializePagingSortingFiltering();
		
		PageInfo<Cposhislog> page = cposhislogService.list(pageQueryParam);
            
		JSONArray array = busiJsonService.getJSONArrayForCposhislog(page.getList());
    		
		JSONObject result = makeDataTableArrayJson(sEcho, page.getTotal(), array);
        
        response(result.toString());
		
	}

}

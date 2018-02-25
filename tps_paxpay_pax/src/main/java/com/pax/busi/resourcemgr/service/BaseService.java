package com.pax.busi.resourcemgr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.pax.auth.entity.Organization;
import com.pax.auth.entity.Site;
import com.pax.auth.entity.User;
import com.pax.auth.service.OrganizationService;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.DateUtils;
import com.pax.core.util.WebUtils;

@Service
public class BaseService {
	
	@Resource
	private OrganizationService	organizationService;
	
	protected int start = 0;
	
	protected int end = 0;
	
	protected void initializeFiltering(PageQueryParam pageQueryParam) {
		
		User user = WebUtils.getUser();
		Site site = user.getSite();
		
		//如果是其他平台的管理员，只能看到自己站点的用户,并且只能看到本级和下级机构的用户
		if (!"P".equals(site.getTag())) {
			//查看选择了站点没得，没有选择，为站点参数赋值
			if (pageQueryParam.getFilterMap().containsKey("site_id")) {
				String value = pageQueryParam.getFilterMap().get("site_id").toString();
				if (StringUtils.isBlank(value)) {
					pageQueryParam.getFilterMap().put("site_id", site.getId());
				} else {
					//看站点参数是不是和site一致,不一致以site为准
					if (!value.equals(String.valueOf(site.getId()))) {
						pageQueryParam.getFilterMap().put("site_id", site.getId());
					}
				}
				
			} else {
				pageQueryParam.getFilterMap().put("site_id", site.getId());
			}
			
			Map<String, Object> filterMap = new HashMap<String, Object>();
			filterMap.put("id", user.getOrg().getId());
			filterMap.put("site_id", site.getId());
			//得到当前登陆用户的机构和下级机构
			List<Organization> orgs = organizationService.list(filterMap);
			
			if (pageQueryParam.getFilterMap().containsKey("orgId")) {
				String value = pageQueryParam.getFilterMap().get("orgId").toString();
				if (StringUtils.isBlank(value)) {
					pageQueryParam.getFilterMap().put("orgs", orgs);
				} else {
					//看机构参数是不是包含在orgs里面
					
					Organization org = organizationService.get(value);
					if (!orgs.contains(org)) {
						pageQueryParam.getFilterMap().put("orgId", "");
						pageQueryParam.getFilterMap().put("orgs", orgs);
					} else {
						//包含就安装机构去查询
					}
					
				}
			} else {
				pageQueryParam.getFilterMap().put("orgs", orgs);
			}
			//平台用户可以看到所有站点的用户	
		} 
		/*else {
			//admin可以查看所有
			if ("admin".equals(user.getLoginname())) {
				//不做任何限制
			} else {
				//如果是平台其他管理员
				//得到当前登陆用户的机构和下级机构
				Map<String, Object> filterMap = new HashMap<String, Object>();
				filterMap.put("id", user.getOrg().getId());
				filterMap.put("site_id", site.getId());
				List<Organization> orgs = organizationService.list(filterMap);
				//得到其他平台的所有机构
				List<Organization> orgs2 = organizationService.listAllOfSite(site.getId());
				//把全部机构加起来
				orgs.addAll(orgs2);
				
				if (pageQueryParam.getFilterMap().containsKey("org_id")) {
					String value = pageQueryParam.getFilterMap().get("org_id").toString();
					if (StringUtils.isBlank(value)) {
						pageQueryParam.getFilterMap().put("orgs", orgs);
					} else {
						//看机构参数是不是包含在orgs里面
						
						Organization org = organizationService.get(value);
						if (!orgs.contains(org)) {
							pageQueryParam.getFilterMap().put("org_id", "");
							pageQueryParam.getFilterMap().put("orgs", orgs);
						} else {
							//包含就安装机构去查询
						}
						
					}
				} else {
					pageQueryParam.getFilterMap().put("orgs", orgs);
				}
			}
		}*/
		
	}
	
	protected void initDate(PageQueryParam pageQueryParam){

		Map<String,Object> map = pageQueryParam.getFilterMap();
		
		String transdateb = "";
		
		String transdatee = "";
		
		if(map.get("transdateb")!=null){
			transdateb = map.get("transdateb").toString();
		}
		
		if(map.get("transdatee")!=null){
			transdatee = map.get("transdatee").toString();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		if(StringUtils.isNotBlank(transdateb)){
			map.put("transdateb", transdateb.replace("-", StringUtils.EMPTY));
		}else{
			throw new BusinessException("请选择开始日期");
		}
		
		if(StringUtils.isNotBlank(transdatee)){
			map.put("transdatee", transdatee.replace("-", StringUtils.EMPTY));
		}else{
			throw new BusinessException("请选择结束日期");
		}
		try{
			
			Date endDate = sdf.parse(transdatee+" 00:00:00");
			
			Date startDate = sdf.parse(transdateb+" 00:00:00");
			
			if(endDate.getTime()<startDate.getTime()){
				throw new BusinessException("开始日期不能晚于结束日期");
			}
			
			Date currentDate = new Date();
			
			start = DateUtils.daysBetween(startDate,currentDate);
			
	        end = DateUtils.daysBetween(endDate ,currentDate);
	        }catch(ParseException e){
				throw new BusinessException("日期转换失败");
	        }
	}
	
}

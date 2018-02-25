package com.pax.busi.transreport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.pax.busi.resourcemgr.service.BaseService;
import com.pax.busi.transreport.dao.RealtimeNotionDao;
import com.pax.busi.transreport.entity.RealtimeNotion;
import com.pax.core.model.PageQueryParam;

@Service
public class RealtimeNotionServiceImpl extends BaseService implements RealtimeNotionService{

	@Resource
	private RealtimeNotionDao realtimeNotionDao;
	
	@Override
	public PageInfo<RealtimeNotion> list(PageQueryParam pageQueryParam) {
		
		initializeFiltering(pageQueryParam);
		
		Map<String,Object> map = pageQueryParam.getFilterMap();
		
		String type = map.get("status").toString();
		
		int pagesize = pageQueryParam.getPageSize();
        
		int startrow = (pageQueryParam.getPageNo()-1)*(pageQueryParam.getPageSize());
        
	    map.put("startrow", startrow);
        
		map.put("pagesize", pagesize);
		
		List<RealtimeNotion> realtimeNotion = new ArrayList<RealtimeNotion>();
		
		long totalcount = 0;
		
		if("1".equals(type)){
			//查询同步成功 表t_b_realtime_notion_suc
			map = pageQueryParam.getFilterMap();
			map.put("status", "");
			realtimeNotion = realtimeNotionDao.list1(pageQueryParam);
			totalcount = realtimeNotionDao.count1(pageQueryParam);
		}else if("-1".equals(type)){
			//查询同步失败 表t_b_realtime_notion
			map = pageQueryParam.getFilterMap();
			map.put("status", "1");
			realtimeNotion = realtimeNotionDao.list2(pageQueryParam);
			
			totalcount = realtimeNotionDao.count2(pageQueryParam);
		}else{
			//查询待同步表t_b_realtime_notion
			
			map = pageQueryParam.getFilterMap();
			map.put("status", "0");
			realtimeNotion = realtimeNotionDao.list3(pageQueryParam);
			totalcount = realtimeNotionDao.count3(pageQueryParam);
		}
		
		PageInfo<RealtimeNotion> page = new PageInfo<RealtimeNotion>(realtimeNotion);
		
		page.setTotal(totalcount);
		
		return page;
	}

	@Override
	public void resend(String transactionNo) {
		realtimeNotionDao.resend(transactionNo);
	}

}

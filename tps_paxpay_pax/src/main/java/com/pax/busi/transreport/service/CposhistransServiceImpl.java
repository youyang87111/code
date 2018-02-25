package com.pax.busi.transreport.service;


import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.pax.busi.common.service.BusiJsonService;
import com.pax.busi.resourcemgr.service.BaseService;
import com.pax.busi.transreport.dao.CposhistransDao;
import com.pax.busi.transreport.entity.Cposhistrans;
import com.pax.busi.transreport.entity.CpostransForExcel;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.ExcelUtils;
import com.pax.core.util.TranslationUtils;
import com.pax.core.util.WebUtils;

@Service
public class CposhistransServiceImpl extends BaseService implements CposhistransService{
	@Resource
	private CposhistransDao cposhistransDao;
	@Resource
	private BusiJsonService busiJsonService;
	
	String[] names = {	"交易系统ID|sysid","接入商户号|mid","接入终端号|tid",
 			"交易名称|innid",  "币种|currency","金额|amount",
 			"批次号|batchno", "流水号|traceno","商户订单号|notes1", 
 			"支付订单号|notes2","交易日期|transdate",	"交易时间|transtime",
 			"机构|org","转出渠道|rbid","转出商户号|rmid", "转出终端号|rtid","撤销标志|voidflag" };
	
	@Override
	public void exportForExcel(PageQueryParam pageQueryParam){
		
		//pageQueryParam.setPageSize(Integer.MAX_VALUE);
		TranslationUtils translation = TranslationUtils
					.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		String fileName = translation.__("接入流水报表")+"_"
				+ new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".xls";
		 
		initializeFiltering(pageQueryParam);
		
		initDate(pageQueryParam);
		
		List<CpostransForExcel> list = new ArrayList<CpostransForExcel>();
		
		if(start == 0&&end == 0){
			//导出当日流水     t_b_cpostrans表
			list = cposhistransDao.export1(pageQueryParam);
		}
		
		if(start > 0&&start <= 30&&end > 0&&end <= 30){
			//查询一个月内流水 t_b_cposhistrans表
			list = cposhistransDao.export2(pageQueryParam);
		}
		
		if(start > 30&&end > 30){
			//查询一个月前的流水 t_b_cposhistrans2表
			list = cposhistransDao.export3(pageQueryParam);
		}
		
		if(start > 0&&start <= 30&&end == 0){
			//查询一个月内(包括当日)的流水  t_b_cpostrans表和t_b_cposhistrans表
			list = cposhistransDao.export4(pageQueryParam);
		}
		
		if(start > 30&&end > 0&&end <= 30){
			//查询一个月和之前的流水 t_b_cposhistrans2表和t_b_cposhistrans表
			list = cposhistransDao.export5(pageQueryParam);
		}
		
		if(start > 30&&end == 0){
			//查询所有的流水 t_b_cpostrans表和t_b_cposhistrans表和t_b_cposhistrans2
			list = cposhistransDao.export6(pageQueryParam);
		}
		try{
				
			JSONArray jsonArray = busiJsonService.getJSONArrayForCpostransForExcel(list);
			
			// 生成文件用到的对象
			@SuppressWarnings("unchecked")
			List<Object> result = (List<Object>) JSONArray.toCollection(jsonArray,
			CpostransForExcel.class);
		 

            SXSSFWorkbook wb = ExcelUtils.toExcel(result, names);

            HttpServletResponse response = WebUtils.getResponse();
            HttpServletRequest request = WebUtils.getRequest();

            response.setContentType("application/x-msdownload;charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            String userAgent = request.getHeader("User-Agent");
            //针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                //非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            //解决中文乱码
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",
                    fileName));

            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            throw new BusinessException("文件下载失败：" + e.getMessage());
        }
	}

	@Override
	public long exportCount(PageQueryParam pageQueryParam) {
		
		long totalcount = 0;
		
		initDate(pageQueryParam);
	        
        if(start == 0&&end == 0){
        	//导出当日流水     t_b_cpostrans表
        	totalcount = cposhistransDao.count0(pageQueryParam);
        }
        
        if(start > 0&&start <= 30&&end > 0&&end <= 30){
        	//查询一个月内流水 t_b_cposhistrans表
        	totalcount = cposhistransDao.count1(pageQueryParam);
        }
        
        if(start > 30&&end > 30){
        	//查询一个月前的流水 t_b_cposhistrans2表
        	totalcount = cposhistransDao.count2(pageQueryParam);
        }
        
        if(start > 0&&start <= 30&&end == 0){
        	//查询一个月内(包括当日)的流水  t_b_cpostrans表和t_b_cposhistrans表
        	totalcount = total4(pageQueryParam);
        }
        
        if(start > 30&&end > 0&&end <= 30){
        	//查询一个月和之前的流水 t_b_cposhistrans2表和t_b_cposhistrans表
        	totalcount = total5(pageQueryParam);
        }
        
        if(start > 30&&end == 0){
        	//查询所有的流水 t_b_cpostrans表和t_b_cposhistrans表和t_b_cposhistrans2
        	totalcount = total6(pageQueryParam);
        }
		
		return totalcount;
	}
	
	@Override
	public PageInfo<Cposhistrans> list(PageQueryParam pageQueryParam){
		
		initializeFiltering(pageQueryParam);
		
		Map<String,Object> map = pageQueryParam.getFilterMap();
        
		int startrow = (pageQueryParam.getPageNo()-1)*(pageQueryParam.getPageSize());
		
		int pagesize = pageQueryParam.getPageSize();
	        
	    int endrow = startrow + pageQueryParam.getPageSize();
        
	    map.put("startrow", startrow);
        
		map.put("pagesize", pagesize);
		
		initDate(pageQueryParam);
		
		PageInfo<Cposhistrans> page = new PageInfo<Cposhistrans>();
	        
        if(start == 0&&end == 0){
        	//查询当日流水     t_b_cpostrans表
        	page = list1(pageQueryParam);
        }
        
        if(start > 0&&start <= 30&&end > 0&&end <= 30){
        	//查询一个月内流水 t_b_cposhistrans表
        	page = list2(pageQueryParam);
        }
        
        if(start > 30&&end > 30){
        	//查询一个月前的流水 t_b_cposhistrans2表
    		page = list3(pageQueryParam);
        }
        
        if(start > 0&&start <= 30&&end == 0){
        	//查询一个月内(包括当日)的流水  t_b_cpostrans表和t_b_cposhistrans表
        	page = list4(pageQueryParam);
        }
        
        if(start > 30&&end > 0&&end <= 30){
        	//查询一个月和之前的流水 t_b_cposhistrans2表和t_b_cposhistrans表
        	page = list5(pageQueryParam);
        }
        
        if(start > 30&&end == 0){
        	//查询所有的流水 t_b_cpostrans表和t_b_cposhistrans表和t_b_cposhistrans2
        	page = list6(pageQueryParam,startrow,endrow);
        }
		return page;
	}
	
	/**
	 * 查询当日流水
	 */
	public PageInfo<Cposhistrans> list1(PageQueryParam pageQueryParam) {
		
		List<Cposhistrans> Cposhistrans = cposhistransDao.list1(pageQueryParam);
		
		PageInfo<Cposhistrans> pageInfo = new PageInfo<Cposhistrans>(Cposhistrans);
		
		pageInfo.setTotal(cposhistransDao.count0(pageQueryParam));
		
		return pageInfo;
	}
	/**
	 * 查询一个月内流水
	 */
	public PageInfo<Cposhistrans> list2(PageQueryParam pageQueryParam) {
		
		List<Cposhistrans> cposhistrans = cposhistransDao.list2(pageQueryParam);
		
		PageInfo<Cposhistrans> pageInfo = new PageInfo<Cposhistrans>(cposhistrans);
		
		pageInfo.setTotal(cposhistransDao.count1(pageQueryParam));
		
		return pageInfo;
	}
	
	/**
	 * 查询一个月前流水
	 */
	public PageInfo<Cposhistrans> list3(PageQueryParam pageQueryParam) {
		
		List<Cposhistrans> cposhistrans = cposhistransDao.list3(pageQueryParam);
		
		PageInfo<Cposhistrans> pageInfo = new PageInfo<Cposhistrans>(cposhistrans);
		
		pageInfo.setTotal(total3(pageQueryParam));
		
		return pageInfo;
	}

	

	/**
	 * 查询一个月内(包括当日)的流水
	 */
	public PageInfo<Cposhistrans> list4(PageQueryParam pageQueryParam) {
		
		List<Cposhistrans> cposhistrans = cposhistransDao.list4(pageQueryParam);
		
		PageInfo<Cposhistrans> pageInfo = new PageInfo<Cposhistrans>(cposhistrans);
		
		pageInfo.setTotal(total4(pageQueryParam));
		
		return pageInfo;
	}
	
	

	/**
	 * 查询一个月和之前的流水
	 */
	public PageInfo<Cposhistrans> list5(PageQueryParam pageQueryParam) {
		
		List<Cposhistrans> cposhistrans = cposhistransDao.list5(pageQueryParam);
		
		PageInfo<Cposhistrans> pageInfo = new PageInfo<Cposhistrans>(cposhistrans);
		
		pageInfo.setTotal(total5(pageQueryParam));
		return pageInfo;
	}
	
	/**
	 * 查询所有流水
	 */
	public PageInfo<Cposhistrans> list6(PageQueryParam pageQueryParam,int startrow,int endrow) {
		
		Map<String,Object> map = pageQueryParam.getFilterMap();
		
		List<Cposhistrans> cposhistrans = new ArrayList<Cposhistrans>();
		
		long count1 = total3(pageQueryParam);
		
		long count2 = total5(pageQueryParam);
		
		if((long)startrow < count1&&(long)endrow < count1){
			cposhistrans = cposhistransDao.list3(pageQueryParam);
		}
		
		
		if((long)startrow <= count1&&(long)endrow > count1){
			cposhistrans = cposhistransDao.list5(pageQueryParam);
		}
		
		
		if((long)startrow > count1&&(long)endrow <= count2){
			
			map.put("startrow", startrow - count1);
			
			cposhistrans = cposhistransDao.list2(pageQueryParam);
		}
		
		
		if((long)startrow <= count2&&(long)endrow > count2){
			
			map.put("startrow", startrow - count1);
			
			cposhistrans = cposhistransDao.list4(pageQueryParam);
		}
		
		
		if((long)startrow > count2&&(long)endrow > count2){
			
			map.put("startrow", startrow - count2);
			
			cposhistrans = cposhistransDao.list1(pageQueryParam);
		}
		
		PageInfo<Cposhistrans> pageInfo = new PageInfo<Cposhistrans>(cposhistrans);
		
		pageInfo.setTotal(total6(pageQueryParam));
		
		return pageInfo;	
	}
	
	/**
	 * 查询表t_b_cposhistrans2的记录数
	 * @param pageQueryParam
	 * @return
	 */
	private long total3(PageQueryParam pageQueryParam) {
		return cposhistransDao.count2(pageQueryParam);
	}
	
	/**
	 * 查询表t_b_cposhistrans和t_b_cpostrans总的记录数
	 * @param pageQueryParam
	 * @return
	 */
	private long total4(PageQueryParam pageQueryParam) {
		return cposhistransDao.count0(pageQueryParam)+cposhistransDao.count1(pageQueryParam);
	}
	/**
	 * 查询表t_b_cposhistrans和t_b_cposhistrans2的总记录数
	 * @param pageQueryParam
	 * @return
	 */
	private long total5(PageQueryParam pageQueryParam) {
		return cposhistransDao.count1(pageQueryParam)+cposhistransDao.count2(pageQueryParam);
	}
	/**
	 * 查询表t_b_cposhistrans和t_b_cposhistrans2和t_b_cpostrans的总记录数
	 * @param pageQueryParam
	 * @return
	 */
	private long total6(PageQueryParam pageQueryParam) {
		return cposhistransDao.count0(pageQueryParam)+cposhistransDao.count1(pageQueryParam)+cposhistransDao.count2(pageQueryParam);
	}
	
}

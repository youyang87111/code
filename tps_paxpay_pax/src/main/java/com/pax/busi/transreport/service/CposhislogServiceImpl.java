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
import com.pax.busi.transreport.dao.CposhislogDao;
import com.pax.busi.transreport.entity.Cposhislog;
import com.pax.busi.transreport.entity.CposhislogForExcel;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.ExcelUtils;
import com.pax.core.util.TranslationUtils;
import com.pax.core.util.WebUtils;

@Service
public class CposhislogServiceImpl extends BaseService implements CposhislogService{
	
	@Resource
	private CposhislogDao cposhislogDao;
	
	@Resource
	private BusiJsonService busiJsonService;
	
	String[] names = {	"交易系统ID|sysid","接入商户号|mid","接入终端号|tid",
 			"交易名称|innid",  "币种|currency","金额|amount",
 			"批次号|batchno", "流水号|traceno","商户订单号|notes1", 
 			"支付订单号|notes2","交易日期|transdate",	"交易时间|transtime",
 			"机构|org","转出渠道|rbid","转出商户号|rmid", "转出终端号|rtid","交易结果|notes8" };
	
	@Override
	public PageInfo<Cposhislog> list(PageQueryParam pageQueryParam){
		
		Map<String,Object> map = pageQueryParam.getFilterMap();
		
		map.put("startrow", (pageQueryParam.getPageNo()-1)*(pageQueryParam.getPageSize()));
		
		map.put("pagesize", pageQueryParam.getPageSize());
		
		initDate(pageQueryParam);
        
        PageInfo<Cposhislog> page = new PageInfo<Cposhislog>();
        
        if(start == 0&&end == 0){
        	//查询当日     t_b_cposlog表
        	page = list1(pageQueryParam);
        }
        if(start > 0&&end > 0){
        	//查询历史   t_b_cposhislog
        	page = list2(pageQueryParam);
        }
        if(start > 0&&end == 0){
        	//查询历史包含当天     t_b_cposlog表和t_b_cposhislog
        	page = list3(pageQueryParam);
        }
		return page;
	}
	
	public PageInfo<Cposhislog> list1(PageQueryParam pageQueryParam) {
		
		List<Cposhislog> Cposhislog = cposhislogDao.list1(pageQueryParam);
		
		PageInfo<Cposhislog> pageInfo = new PageInfo<Cposhislog>(Cposhislog);
		
		pageInfo.setTotal(total1(pageQueryParam));
		
		return pageInfo;
	}
	
	

	public PageInfo<Cposhislog> list2(PageQueryParam pageQueryParam) {
		
		List<Cposhislog> Cposhislog = cposhislogDao.list2(pageQueryParam);
		
		PageInfo<Cposhislog> pageInfo = new PageInfo<Cposhislog>(Cposhislog);
		
		pageInfo.setTotal(total2(pageQueryParam));
		
		return pageInfo;
	}

	public PageInfo<Cposhislog> list3(PageQueryParam pageQueryParam) {

		List<Cposhislog> Cposhislog = cposhislogDao.list3(pageQueryParam);
		
		PageInfo<Cposhislog> pageInfo = new PageInfo<Cposhislog>(Cposhislog);
		
		pageInfo.setTotal(total3(pageQueryParam));
		
		return pageInfo;
	}
	
	/**
	 * 查询表t_b_cposlog的记录数
	 * @param pageQueryParam
	 * @return
	 */
	private long total1(PageQueryParam pageQueryParam) {
		return cposhislogDao.count1(pageQueryParam);
	}
	
	/**
	 * 查询表t_b_cposhislog的记录数
	 * @param pageQueryParam
	 * @return
	 */
	private long total2(PageQueryParam pageQueryParam) {
		return cposhislogDao.count2(pageQueryParam);
	}
	
	/**
	 * 查询表t_b_cposlog和t_b_cposhislog的记录数
	 * @param pageQueryParam
	 * @return
	 */
	private long total3(PageQueryParam pageQueryParam) {
		return cposhislogDao.count1(pageQueryParam) + cposhislogDao.count2(pageQueryParam);
	}

	@Override
	public void exportForExcel(PageQueryParam pageQueryParam) {
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
	String fileName = translation.__("交易日志报表")+"_"
			+ new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".xls";
	 
	initializeFiltering(pageQueryParam);
	
	initDate(pageQueryParam);
	
	List<CposhislogForExcel> list = new ArrayList<CposhislogForExcel>();
	
	if(start == 0&&end == 0){
		//导出当日流水     t_b_cpostrans表
		list = cposhislogDao.export1(pageQueryParam);
	}
	 if(start > 0&&end > 0){
     	//查询历史   t_b_cposhislog
		 list = cposhislogDao.export2(pageQueryParam);
     }
     if(start > 0&&end == 0){
     	//查询历史包含当天     t_b_cposlog表和t_b_cposhislog
    	 list = cposhislogDao.export3(pageQueryParam);
     }
	
	try{
			
		JSONArray jsonArray = busiJsonService.getJSONArrayForCposhislogForExcel(list);
		
		// 生成文件用到的对象
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>) JSONArray.toCollection(jsonArray,
				CposhislogForExcel.class);
	 

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
        	//导出当日流水     t_b_cposhislog表
        	totalcount = total1(pageQueryParam);
        }
        
        if(start > 0&&end > 0){
        	//查询历史   t_b_cposhislog
        	totalcount = total2(pageQueryParam);
        }
        if(start > 0&&end == 0){
        	//查询历史包含当天     t_b_cposlog表和t_b_cposhislog
        	totalcount = total3(pageQueryParam);
        }
		
		return totalcount;
	}


}

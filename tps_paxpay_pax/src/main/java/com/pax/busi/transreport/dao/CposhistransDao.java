package com.pax.busi.transreport.dao;

import java.util.List;

import com.pax.busi.transreport.entity.Cposhistrans;
import com.pax.busi.transreport.entity.CpostransForExcel;
import com.pax.core.model.PageQueryParam;

public interface CposhistransDao {

	public List<CpostransForExcel> listAll(PageQueryParam pageQueryParam);
	
	/**
	 * 统计表t_b_cpostrans的记录数
	 * @param pageQueryParam
	 * @return
	 */
	public long count0(PageQueryParam pageQueryParam);
	
	/**
	 * 统计表t_b_cposhistrans的记录数
	 * @param pageQueryParam
	 * @return
	 */
	public long count1(PageQueryParam pageQueryParam);
	
	/**
	 * 统计表t_b_cposhistrans2的记录数
	 * @param pageQueryParam
	 * @return
	 */
	public long count2(PageQueryParam pageQueryParam);

	public List<Cposhistrans> list1(PageQueryParam pageQueryParam);

	public List<Cposhistrans> list2(PageQueryParam pageQueryParam);
	
	public List<Cposhistrans> list3(PageQueryParam pageQueryParam);

	public List<Cposhistrans> list4(PageQueryParam pageQueryParam);
	
	public List<Cposhistrans> list5(PageQueryParam pageQueryParam);

	public List<CpostransForExcel> export1(PageQueryParam pageQueryParam);
	
	public List<CpostransForExcel> export2(PageQueryParam pageQueryParam);
	
	public List<CpostransForExcel> export3(PageQueryParam pageQueryParam);
	
	public List<CpostransForExcel> export4(PageQueryParam pageQueryParam);
	
	public List<CpostransForExcel> export5(PageQueryParam pageQueryParam);
	
	public List<CpostransForExcel> export6(PageQueryParam pageQueryParam);

}

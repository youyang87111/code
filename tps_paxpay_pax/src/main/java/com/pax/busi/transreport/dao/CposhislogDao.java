package com.pax.busi.transreport.dao;

import java.util.List;

import com.pax.busi.transreport.entity.Cposhislog;
import com.pax.busi.transreport.entity.CposhislogForExcel;
import com.pax.core.model.PageQueryParam;

public interface CposhislogDao {

	List<Cposhislog> list1(PageQueryParam pageQueryParam);

	List<Cposhislog> list2(PageQueryParam pageQueryParam);

	List<Cposhislog> list3(PageQueryParam pageQueryParam);

	long count1(PageQueryParam pageQueryParam);

	long count2(PageQueryParam pageQueryParam);

	List<CposhislogForExcel> export1(PageQueryParam pageQueryParam);
	
	List<CposhislogForExcel> export2(PageQueryParam pageQueryParam);
	
	List<CposhislogForExcel> export3(PageQueryParam pageQueryParam);


}

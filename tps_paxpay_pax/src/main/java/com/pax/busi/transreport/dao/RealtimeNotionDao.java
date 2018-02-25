package com.pax.busi.transreport.dao;

import java.util.List;

import com.pax.busi.transreport.entity.RealtimeNotion;
import com.pax.core.model.PageQueryParam;

public interface RealtimeNotionDao {

	List<RealtimeNotion> list1(PageQueryParam pageQueryParam);

	List<RealtimeNotion> list2(PageQueryParam pageQueryParam);

	List<RealtimeNotion> list3(PageQueryParam pageQueryParam);
	
	void resend(String transactionNo);

	long count1(PageQueryParam pageQueryParam);
	
	long count2(PageQueryParam pageQueryParam);

	long count3(PageQueryParam pageQueryParam);

}

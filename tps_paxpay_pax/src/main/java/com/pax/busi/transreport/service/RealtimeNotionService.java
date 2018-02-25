package com.pax.busi.transreport.service;

import com.github.pagehelper.PageInfo;
import com.pax.busi.transreport.entity.RealtimeNotion;
import com.pax.core.model.PageQueryParam;

public interface RealtimeNotionService {

	PageInfo<RealtimeNotion> list(PageQueryParam pageQueryParam);

	void resend(String transactionNo);

}

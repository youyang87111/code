package com.pax.busi.transreport.service;

import com.github.pagehelper.PageInfo;
import com.pax.busi.transreport.entity.Cposhistrans;
import com.pax.core.model.PageQueryParam;

public interface CposhistransService {

	public void exportForExcel(PageQueryParam pageQueryParam);

	public long exportCount(PageQueryParam pageQueryParam);

	public PageInfo<Cposhistrans> list(PageQueryParam pageQueryParam);
}

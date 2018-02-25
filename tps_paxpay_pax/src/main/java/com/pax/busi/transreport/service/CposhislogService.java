package com.pax.busi.transreport.service;

import com.github.pagehelper.PageInfo;
import com.pax.busi.transreport.entity.Cposhislog;
import com.pax.core.model.PageQueryParam;

public interface CposhislogService {

	PageInfo<Cposhislog> list(PageQueryParam pageQueryParam);

	void exportForExcel(PageQueryParam pageQueryParam);

	long exportCount(PageQueryParam pageQueryParam);

}

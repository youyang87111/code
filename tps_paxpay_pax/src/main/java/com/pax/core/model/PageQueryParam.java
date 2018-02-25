package com.pax.core.model;

import java.util.Map;

public class PageQueryParam {
	
	private int					pageNo		= 1;
	
	private int					pageSize	= 5;
	
	private Map<String, String>	sortMap;
	
	private Map<String, Object>	filterMap;
	
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageSize() {
		return pageSize==-1?999999:pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public Map<String, String> getSortMap() {
		return sortMap;
	}
	
	public void setSortMap(Map<String, String> sortMap) {
		this.sortMap = sortMap;
	}
	
	public Map<String, Object> getFilterMap() {
		return filterMap;
	}
	
	public void setFilterMap(Map<String, Object> filterMap) {
		this.filterMap = filterMap;
	}
	
}

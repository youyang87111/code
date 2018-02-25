package com.pax.busi.facade;

import java.io.Serializable;

public class BaseReq implements Serializable {
	
	/** Comment for <code>serialVersionUID</code> */
	private static final long	serialVersionUID	= 1L;
	
	//请求标识号
	private String requestId;
		
	//交易日期时间,格式：YYYYMMDDhhmmss
	private String requestTime;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	
}

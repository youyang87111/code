package com.pax.busi.facade;

import java.io.Serializable;

public class BaseRsp implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	
	//请求标识号，原值返回
	private String requestId;
		
	//操作结果 00表示成功，其他表示失败
	private String				responseCode;
	
	//结果描述
	private String				responseText;
	
	//响应日期时间,格式：YYYYMMDDhhmmss
	private String responseTime;
	
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public String getResponseText() {
		return responseText;
	}
	
	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public void setResponse(ResponseCode response) {
		this.responseCode = response.getResponseCode();
		this.responseText = response.getResponseText();
	}
}

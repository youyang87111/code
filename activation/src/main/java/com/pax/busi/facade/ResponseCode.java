package com.pax.busi.facade;

public enum ResponseCode {

	SUCCESS("00","成功"),SYSERROR("01","系统异常");
	
    private String responseCode;  
    private String responseText;  
    
    // 构造方法  
    private ResponseCode(String responseCode, String responseText) {  
        this.responseCode = responseCode;  
        this.responseText = responseText;  
    }

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}  
    
    
	
}

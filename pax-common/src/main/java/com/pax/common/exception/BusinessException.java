package com.pax.common.exception;

import java.util.Locale;

import com.pax.common.util.TranslationUtils;
import com.pax.common.util.WebUtils;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BusinessException(String message){
		super(TranslationUtils.getInstance(WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale")).__(message));
	}
	
	public BusinessException(Throwable cause)
	{
		super(cause);
	}
	
	public BusinessException(String message,Throwable cause)
	{
		super(TranslationUtils.getInstance(WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale")).__(message),cause);
	}
}

package com.pax.core.exception;

import java.util.Locale;

import com.pax.core.util.TranslationUtils;
import com.pax.core.util.WebUtils;

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

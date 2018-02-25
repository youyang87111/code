package com.pax.common.util;

import java.util.List;
import java.util.Locale;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import net.sf.json.JSONObject;

public class BindingResultHandler {
	
	public static JSONObject handleBindingResult(BindingResult br) {
		
		Locale locale = WebUtils.getSession() == null ? null
			: (Locale) WebUtils.getSession().getAttribute("locale");
		
		List<FieldError> list = br.getFieldErrors();
		
		JSONObject jsonObject = new JSONObject();
		
		for (int i = 0; i < list.size(); i++) {
			FieldError fieldError = list.get(i);
			jsonObject.put(fieldError.getField(),
				TranslationUtils.getInstance(locale).__(fieldError.getDefaultMessage()));
		}
		
		return jsonObject;
		
	}
}

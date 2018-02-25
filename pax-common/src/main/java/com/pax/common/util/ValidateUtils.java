package com.pax.common.util;

import org.apache.commons.lang.StringUtils;

import com.pax.common.exception.BusinessException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class ValidateUtils {
	
	private static Validator	validator	= Validation.buildDefaultValidatorFactory()
												.getValidator();
	
	/**
	 * hibernate validation的共通API (需要验证时在业务层第一句调用)
	 * @param t 业务层的inputParam的bean作为验证参数
	 * */
	public static <T> Set<String> validate(T t) {
		Set<String> msgs = new HashSet<String>();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			msgs.add(constraintViolation.getMessage());
		}
		return msgs;
	}
	public static <T> Map<String,String> validateToMap(T t) {
		Map<String,String> msgs = new HashMap<String, String>();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
		 	String errorFullName = constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().getName();
		 	String errorName = errorFullName.substring(errorFullName.lastIndexOf(".")+1);
			msgs.put(errorName,constraintViolation.getPropertyPath()+","+constraintViolation.getMessage());
			break;
		}
		return msgs;
	}

	public static <T> void validates(T t) {

		//收集全部错误信息
		StringBuilder sb = new StringBuilder();

		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			sb.append(constraintViolation.getMessage() + "<br>");
		}

		String valMsg = sb.toString();

		if (StringUtils.isNotEmpty(valMsg)) {
			throw new BusinessException(valMsg);
		}
	}

}

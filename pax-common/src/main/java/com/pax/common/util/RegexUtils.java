package com.pax.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	
	public static boolean validate(String regex, String value) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		boolean b = matcher.matches();
		return b;
	}
	
	public static void main(String[] args) {
		System.out.println(RegexUtils.validate("^[0-9]{1,7}$", "1111"));
	}
}

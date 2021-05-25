package com.springboot.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberValidator {

	private static String REGEX_FOR_ALLCOUNTRY="^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$";
	private static Pattern pattern;
	private static Matcher numberMatcher;
	private NumberValidator()
	{
		
	}
	public static boolean isValid(String phoneNumber)
	{
		pattern = Pattern.compile(REGEX_FOR_ALLCOUNTRY);
		numberMatcher = pattern.matcher(phoneNumber);
		return (numberMatcher.find() && numberMatcher.group().equals(phoneNumber)); 
	}
	
}

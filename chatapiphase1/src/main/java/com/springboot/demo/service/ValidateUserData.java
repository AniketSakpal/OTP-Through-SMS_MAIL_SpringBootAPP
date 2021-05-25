package com.springboot.demo.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUserData 
{
	public static boolean validateUserMail(String userData)
	{
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"; // Regex to validate mail
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userData);
		 
		return matcher.matches();
	}
	public static boolean validateUserPhone(String userData)
	{
		String phoneNumberRegex = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$";
		Pattern phonePattern = Pattern.compile(phoneNumberRegex);
		Matcher phoneMatcher = phonePattern.matcher(userData);
		
		return phoneMatcher.matches();
	}
}

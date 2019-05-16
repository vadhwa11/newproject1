package com.test.validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PatternValidator {

	Matcher matcher;
	public static final Pattern EMAIL_PATTERN = 
		    Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"  , Pattern.CASE_INSENSITIVE);
	
	public static final Pattern VALID_PHONE_NUMBER_REGEX=Pattern.compile("^[7-9][0-9]{9}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PAN_NUMBER_REGEX=Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_GSTIN_NUMBER_REGEX=Pattern.compile("[0-9]{2}[0-9A-Za-z]{13}$", Pattern.CASE_INSENSITIVE);
	private static Pattern  VALID_PASSWORD_REGEX=Pattern.compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[-_@#$%^&+=]).*$");
	

}

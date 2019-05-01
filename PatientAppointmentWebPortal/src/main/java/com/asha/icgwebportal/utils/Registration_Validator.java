package com.asha.icgwebportal.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Registration_Validator  {
	static final String regex = "^(?!(?:(?:\\x22?\\x5C[\\x00-\\x7E]\\x22?)|(?:\\x22?[^\\x5C\\x22]\\x22?)){255,})(?!(?:(?:\\x22?\\x5C[\\x00-\\x7E]\\x22?)|(?:\\x22?[^\\x5C\\x22]\\x22?)){65,}@)(?:(?:[\\x21\\x23-\\x27\\x2A\\x2B\\x2D\\x2F-\\x39\\x3D\\x3F\\x5E-\\x7E]+)|(?:\\x22(?:[\\x01-\\x08\\x0B\\x0C\\x0E-\\x1F\\x21\\x23-\\x5B\\x5D-\\x7F]|(?:\\x5C[\\x00-\\x7F]))*\\x22))(?:\\.(?:(?:[\\x21\\x23-\\x27\\x2A\\x2B\\x2D\\x2F-\\x39\\x3D\\x3F\\x5E-\\x7E]+)|(?:\\x22(?:[\\x01-\\x08\\x0B\\x0C\\x0E-\\x1F\\x21\\x23-\\x5B\\x5D-\\x7F]|(?:\\x5C[\\x00-\\x7F]))*\\x22)))*@(?:(?:(?!.*[^.]{64,})(?:(?:(?:xn--)?[a-z0-9]+(?:-[a-z0-9]+)*\\.){1,126}){1,}(?:(?:[a-z][a-z0-9]*)|(?:(?:xn--)[a-z0-9]+))(?:-[a-z0-9]+)*)|(?:\\[(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){7})|(?:(?!(?:.*[a-f0-9][:\\]]){7,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?)))|(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){5}:)|(?:(?!(?:.*[a-f0-9]:){5,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3}:)?)))?(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))(?:\\.(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))){3}))\\]))$";


	public static final Pattern EMAIL_PATTERN = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PHONE_NUMBER_REGEX=Pattern.compile("^[7-9][0-9]{9}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PAN_NUMBER_REGEX=Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PINCODE_REGEX=Pattern.compile("[0-9]{6}", Pattern.CASE_INSENSITIVE);
	
	 public boolean emailValidate(String email)
	    {   
	    	//System.out.println(email);
	    	Matcher matcher = EMAIL_PATTERN .matcher(email);
	    	  if (!matcher.matches()) 
	    		  return false;
	    	  else
	    		  return true;
	    }
	    public boolean phoneValidate(String num)
	    {
	    	Matcher matcher = VALID_PHONE_NUMBER_REGEX .matcher(num);
	    	if (!matcher.matches()) 
	  		  return false;
	  	  else
	  		  return true;
	    }
	    
	    public boolean panValidate(String pan)
	    {
	    	Matcher matcher = VALID_PAN_NUMBER_REGEX .matcher(pan);
	    	if (!matcher.matches()) 
	  		  return false;
	  	  else
	  		  return true;
	    	
	    }
	    public boolean pincodeValidate(String pincode)
	    {
	    	Matcher matcher = VALID_PINCODE_REGEX .matcher(pincode);
	    	if (!matcher.matches()) 
	  		  return false;
	  	  else
	  		  return true;
	    	
	    }
}
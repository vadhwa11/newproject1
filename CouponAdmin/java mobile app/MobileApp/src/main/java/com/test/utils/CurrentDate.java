package com.test.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {
 int x=4;

	public static  String Date() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
		Date date = new java.util.Date();
		String CurrentDtate=dateFormat.format(date);
		return CurrentDtate;

	}
	
	public  static  String CoupanGenrationDate() {

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssmm");
		Date date = new java.util.Date();
		String CurrentDtate=dateFormat.format(date);
		System.out.println(CurrentDtate);

		return CurrentDtate;

	}

	
}
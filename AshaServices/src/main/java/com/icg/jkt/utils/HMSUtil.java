package com.icg.jkt.utils;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.text.ParseException;
import org.springframework.web.bind.ServletRequestUtils;
import com.icg.jkt.entity.MasEmployee;


public class HMSUtil extends ServletRequestUtils {
	
 public static int calculateAgeNoOfYear(Date dob ) {
		 
		 Calendar lCal = Calendar.getInstance();
		    lCal.setTime(dob);
		    int yr=lCal.get(Calendar.YEAR);
		    int mn=lCal.get(Calendar.MONTH) + 1;
		    int dt=lCal.get(Calendar.DATE);
		    LocalDate today = LocalDate.now(); //Today's date
		LocalDate birthday = LocalDate.of(yr,mn,dt) ; //Birth date
		Period p = Period.between(birthday, today); // Period
		return p.getYears();
	 }
 
 public static String getFullName(MasEmployee emp) {
	 String patientName = "";
		if (emp.getFirstName() != null) {
			patientName = emp.getFirstName();
		}

		if (emp.getMiddleName() != null) {
			patientName = patientName + " "
					+ emp.getMiddleName();
		}
		if (emp.getLastName() != null) {
			patientName = patientName + " "
					+ emp.getLastName();
	 
			}
		return patientName;
 	}
 
 public static String getProperties(String fileName, String propName){
		String propertyValue = null;
		try{
			URL resourcePath = Thread.currentThread().getContextClassLoader()
					.getResource(fileName);
			Properties properties= new Properties();
			properties.load(resourcePath.openStream());
			propertyValue = properties.getProperty(propName);
		}catch(Exception e){e.printStackTrace();}
		return propertyValue;
	}
 
 public static String convertDateToStringFormat(Date date, String format){
     String dateFormat="";
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
     if(date != null) {
    	 dateFormat=simpleDateFormat.format(date);    
     }    
     return dateFormat;	

}
 
 public static Date dateFormatteryyyymmdd(String stringDate) throws Exception {
		SimpleDateFormat dateFormatterYYYYMMDD = new SimpleDateFormat(
				"yyyy-MM-dd");
		try {
			return (dateFormatterYYYYMMDD.parse(stringDate));
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;			
		}
		
	}
 
 
	public static Date convertStringDateToUtilDate(String date, String format) {
		Date utilDate = null;

		SimpleDateFormat df = new SimpleDateFormat(format);
		if (date != null) {
			try {
				utilDate = df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return utilDate;
	}
}

package com.icg.jkt.utils;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

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
 
 public static String convertDateToStringFormat1(LocalDate visitDate, String format){
     String dateFormat="";
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
     if(visitDate != null) {
    	 dateFormat=simpleDateFormat.format(visitDate);    
     }    
     return dateFormat;	

}
 
 
 
 
 public static Date getNextDate(Date today) {
	 
	String strDate= convertDateToStringFormat(today, "yyyy-MM-dd");
	Date returnDate=null;
	try {
		returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 final Calendar calendar = Calendar.getInstance();
	 calendar.setTime(returnDate);
	 calendar.add(Calendar.DAY_OF_YEAR, 1);
	 return calendar.getTime();
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
	
	
	public static Date convertStringDateToUtilDateForDatabase(String date) {
		Date utilDate = null;
		String format="";
		if(date.trim().length()>10) {
			 format="dd/MM/yyyy HH:mm:ss";
		}else {
			 format="dd/MM/yyyy";
		}
		
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
	
	
	
	public static String convertNullToEmptyString(Object obj) {
		return (obj == null) ? "" : obj.toString();
	}
	
	
	
	public static long calculateTotalMinutes(String startTime, String endTime) {

		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date d1;
		Date d2;
		long totalMinutes=0;
		try {
			d1 = format.parse(startTime);
			d2 = format.parse(endTime);
			totalMinutes = (d2.getTime() - d1.getTime())/(60 * 1000); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return totalMinutes;
	}
	
	
	public static String addingMinutes(String startTime, int timeInterval) {

		 SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		 Date d;
		 String newTime="";
		try {
			d = df.parse(startTime);
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(d);
			 cal.add(Calendar.MINUTE, timeInterval);
			 newTime = df.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		 return newTime;
	}
	
	
	 public static String getDateWithoutTime(Date date) {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    return sdf.format(date);
		  }
	
	 
	 public static String changeDateToddMMyyyy(Date dbDate) {
		 String strDate = dbDate.toString();
		 String strNewDate = "", year = "", dt = "", month = "";
		 year = strDate.substring(0, 4);
		 month = strDate.substring(5, 7);
		 dt = strDate.substring(8, 10);
		 strNewDate = (dt + "/" + month + "/" + year);
		 return strNewDate;
		}
	 
	 
	 public static Date convertStringTypeDateToDateType(String date) {
			Date orderDateTime = null;

			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if (date != null) {
				try {
					orderDateTime = df.parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			return orderDateTime;
		}
	 
	 
}



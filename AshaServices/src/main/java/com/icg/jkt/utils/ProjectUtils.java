package com.icg.jkt.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;

public class ProjectUtils {

	
	private static SimpleDateFormat inSDF = new SimpleDateFormat("dd/mm/yyyy");
	private static SimpleDateFormat sqldateformatIn = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sqldateformatInNew = new SimpleDateFormat("yyyy/mm/dd");
	private static SimpleDateFormat outSDF = new SimpleDateFormat("yyyy/mm/dd");
	private static SimpleDateFormat sqldateformatout = new SimpleDateFormat("yyyy-MM-dd");
	
	public synchronized static String generateToken() {
		DateFormat sdf = new SimpleDateFormat("yyMMddHHmmssS");
		Date date = new Date();
		String tokenID = sdf.format(date);
		tokenID = tokenID.replace("-", "");
		tokenID = tokenID.replace("+", "");
		return RandomStringUtils.randomAlphabetic(5) + "" + tokenID;
	}

	public static String refreshTocken(HttpServletRequest request) {
		LinkedHashMap<String, String> userMAp = (LinkedHashMap<String, String>) request.getSession()
				.getAttribute("shopdetail");
		String token = ProjectUtils.generateToken();
		LinkedHashMap<String, String> usermap = new LinkedHashMap<String, String>();
		usermap.put(token, userMAp.get("adminid") + "");
		usermap.put("adminid", userMAp.get("adminid"));
		// usermap.put("shopid", userMAp.get("shopid"));
		request.getSession().setAttribute("shopdetail", usermap);
		request.getSession().setMaxInactiveInterval(15 * 60);
		return token;
	}

	public static boolean validateSession(String token, HttpServletRequest session) {
		HttpSession s = session.getSession(false);
		if (s == null) {
			return false;
		}
		try {
			LinkedHashMap<String, String> userMAp = (LinkedHashMap<String, String>) s.getAttribute("shopdetail");
			if (userMAp == null)
				return false;

			if (userMAp.containsKey((String) token)) {
				return true;
			}

		} catch (Exception ex) {
			// System.out.println("USER SESSION IS EXPIRED ....!!! TIMES UP");
		}
		return false;
	}

	public static String saltify_level2(String unecryptedPassword) throws Exception {
		String salt = "VuCETZcz6D";
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
			messageDigest.update((unecryptedPassword + salt).getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String encryptedPassword = (new BigInteger(messageDigest.digest())).toString(16);
		return encryptedPassword;
	}

	public static Date getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");
		Date d = new Date();
		return d;
	}
	
	public static String getCurrentDateforweekgraph() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		return dateFormat.format(d).toString();
	}
	
	public static String getCurrentDateandTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
		Date d = new Date();
		return dateFormat.format(d).toString();
	}
	
	
	

	public synchronized static String generateCode() {
		return RandomStringUtils.randomNumeric(6);
	}

	public static String getDateForgraph() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		//System.out.println("date     " + dateFormat.format(date));
	//	System.out.println("day   " + dayOfWeek);
		dayOfWeek = dayOfWeek + 7;
		//System.out.println("day again  " + dayOfWeek);

		String dateagain = customizeDateForGraph(dateFormat.format(date), dayOfWeek);
		//System.out.println("day again and again   " + dateagain);
		return dateagain.toString();
	}
	

	public static String customizeDateForGraph(String date, int day) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date startDate, newDate;
		Calendar cal = Calendar.getInstance();
		try {
			startDate = df.parse(date);
			String newDateString = df.format(startDate);
			cal.setTime(startDate);
			cal.add(Calendar.DATE, -day);
			Date currentDatePlusOne = cal.getTime();
			return df.format(currentDatePlusOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cal.getTime().toString();
	}
	
	
	
	//
	
	public static String getOneMonthBack(String date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate, newDate;
		Calendar cal = Calendar.getInstance();
		try {
			startDate = df.parse(date);
			String newDateString = df.format(startDate);
			cal.setTime(startDate);
			cal.add(Calendar.MONTH, -1);
			Date currentDatePlusOne = cal.getTime();
			return df.format(currentDatePlusOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cal.getTime().toString();
	}
	
	
	public static Date getOneMonthBackNew(String date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate, newDate;
		Calendar cal = Calendar.getInstance();
		try {
			startDate = df.parse(date);
			String newDateString = df.format(startDate);
			cal.setTime(startDate);
			cal.add(Calendar.MONTH, -1);
			Date currentDatePlusOne = cal.getTime();
			
			return currentDatePlusOne;
			//return df.format(currentDatePlusOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return cal.getTime().toString();
		return cal.getTime();
	}
	
	
	
	
	public static String getDayNameOfDate(String date) {
		
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate, newDate;
        Calendar c = Calendar.getInstance();

        try {
            startDate = df.parse(date);
            c.setTime(startDate);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                    //System.out.println("Bhai Day mila      "+dayOfWeek);

            switch (dayOfWeek) {
                case 1:
                   // System.out.print("Sunday");
                    return "Sunday";
                  //  break;
                case 2:
                    //System.out.print("Monday");
                    return "Monday";
                    //break;
                case 3:
                    //System.out.print("Tuesday");
                    return "Tuesday";
                  //  break;
                case 4:
                    //System.out.print("Wednesday");
                    return "Wednesday";
                   // break;
                case 5:
                    //System.out.print("Thursday");
                    return "Thursday";
                   // break;
                case 6:
                    //System.out.print("Friday");
                    return "Friday";
                   // break;
                case 7:
                   // System.out.print("Saturday");
                    return "Saturday";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
	
//	public static String getDateFormate(String date){
//		
//		 SimpleDateFormat inSDF = new SimpleDateFormat("dd/mm/yyyy");
//	     SimpleDateFormat outSDF = new SimpleDateFormat("yyyy/mm/dd");
//		String outDate = "";
//	    if (date != null) {
//	        try {
//	            Date datenew = inSDF.parse(date);
//	            outDate = outSDF.format(date);
//	        } catch (ParseException ex){ 
//	        }
//	    }
//	    return outDate;
//	}
	
	
	
	private static JSONObject function(HashMap<String, String> jsonData, String[] strings) {

		  for (int i = 0; i < strings.length; i++) {
		   boolean b = jsonData.containsKey(strings[i]) && jsonData.get(strings[i]) != null
		     && !(jsonData.get(strings[i]).toString().equals(""));
		   if (b == false) {
		    return ErrorDiscriptions.getReturnMsg(
		      "jsondata is not contain" + " " + strings[i] + " " + "as a  key or value or it is null", "0");
		   }
		  }
		  return ErrorDiscriptions.getReturnMsg("validated data found.", "1");
		 }

	public static java.sql.Date getCurrentDates() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String temp = dateFormat.format(d);
		//System.out.println(java.sql.Date.valueOf(temp));
		return java.sql.Date.valueOf(temp);
	}
	
	
	public static LocalDateTime getDateTimePluseThirtyMin(String date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime todayOld = LocalDateTime.parse(date, formatter);
		System.out.println("normal date "+todayOld);
		Duration addTwentyMin = Duration.ofMinutes(30);
		String oldFinal=todayOld.plus(addTwentyMin).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		LocalDateTime oldFinaldateTime = LocalDateTime.parse(oldFinal, formatter);
		System.out.println("After add 30 min      "+oldFinaldateTime);
		return oldFinaldateTime;
	}
	
	public static  Period getDOB(Date d)
	{
		
	    Calendar lCal = Calendar.getInstance();
	    lCal.setTime(d);
        int yr=lCal.get(Calendar.YEAR);
        int mn=lCal.get(Calendar.MONTH) + 1;
        int dt=lCal.get(Calendar.DATE);
        LocalDate today = LocalDate.now();
		System.out.println("today"+today);//Today's date
		LocalDate birthday = LocalDate.of(yr,mn,dt) ; //Birth date
		System.out.println("birthday"+birthday);
		Period p = Period.between(birthday, today);
		
		return p;
	}

	public static String getSqlDateFormat(String inDate) {
		String outDate = "";
		java.util.Date date = null;
		// java.util.Date date2 = null;
		if (inDate != null) {
			try {
				java.util.Date date2 = sqldateformatIn.parse(inDate);
				outDate = sqldateformatout.format(date2);
				// date = sqldateformatout.parse(outDate);
				// date=Date.valueOf(outDate);

			} catch (ParseException ex) {
			}
		}
		return outDate;
	}
	public static String getSqlDateFormatNew(String inDate) {
		String outDate = "";
		java.util.Date date = null;
		if (inDate != null) {
			try {
				java.util.Date date2 = sqldateformatInNew.parse(inDate);
				outDate = sqldateformatout.format(date2);
			} catch (ParseException ex) {
			}
		}
		return outDate;
	}
	
	

}

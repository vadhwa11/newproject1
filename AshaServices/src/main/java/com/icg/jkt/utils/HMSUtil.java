package com.icg.jkt.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;

import com.icg.jkt.entity.MasEmployee;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;


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
			totalMinutes = (d2.getTime() - d1.getTime())/(60 * 1000) % 60;; 
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
	
	@SuppressWarnings("rawtypes")
	public synchronized static void generateReport1(String jasper_filename, Map parameters,
			Connection conn, HttpServletResponse response,
			ServletContext context) {
	
		response.setContentType( "application/pdf" );
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ jasper_filename + ".pdf");
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(getCompiledReport(context,
					  jasper_filename), parameters, conn);
			try {
				JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JRException e) {
			e.printStackTrace();
		}

	}
	
	public synchronized static void generateReport(String jasper_filename, Map parameters,
			Connection conn, HttpServletResponse response,
			ServletContext context) {
		byte bytes[] = null;
		try {
			bytes = JasperRunManager.runReportToPdf(getCompiledReport(context,
					jasper_filename), parameters, conn);
			
			    
			if(!conn.isClosed())
			conn.close();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setHeader("Content-Disposition", "attachment; filename="
				+ jasper_filename + ".pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream;
		try {
			ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			//ouputStream.flush();
			//ouputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
		
	
	
	
public static JasperReport getCompiledReport(ServletContext context,
			String fileName) throws JRException {
		File reportFile = new File(context.getRealPath("/reports/" + fileName
				+ ".jasper"));
		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(reportFile.getPath());
		return jasperReport;
	}
	
}



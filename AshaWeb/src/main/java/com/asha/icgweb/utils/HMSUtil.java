package com.asha.icgweb.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.ServletRequestUtils;

import net.sf.jasperreports.engine.JRException;
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
		    LocalDate today = LocalDate.now();
		    
		//System.out.println("today"+today);//Today's date
		LocalDate birthday = LocalDate.of(yr,mn,dt) ; //Birth date
		//System.out.println("birthday"+birthday);
		Period p = Period.between(birthday, today);
		//System.out.println("Period : "+p);
		return p.getYears();
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
 

	public static Box getBox(HttpServletRequest request) {
		Box box = new Box("requestbox");
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			box.put(key, request.getParameterValues(key));
		}
		return box;
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
			ouputStream.flush();
			ouputStream.close();
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
package com.asha.icgweb.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.asha.icgweb.dao.ReportDao;
import com.asha.icgweb.utils.HMSUtil;

@WebServlet(name="/reportservlet", urlPatterns = "/reportservlet")
public class ReportServlet extends HttpServlet{

	@Autowired
	ReportDao reportDao;
	
	public ReportServlet() {
		super();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		System.out.println("servlet calling...");
		//generateReport(request,response);
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		String filename = "Conveyance Reimbursement_Feb-Mar-19.xlsx";   
		String filepath = "D:\\ICG\\";   
		response.setContentType("APPLICATION/OCTET-STREAM");   
		response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
		  
		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
		            
		int i;   
		while ((i=fileInputStream.read()) != -1) {  
		out.write(i);   
		}   
		fileInputStream.close();   
		out.close();   
		}

	private void generateReport(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		System.out.println("connectionMap :: "+connectionMap);
		HMSUtil.generateReport("Physiotherapy_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		
	} 
	
}

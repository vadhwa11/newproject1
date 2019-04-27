package com.asha.icgweb.controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.asha.icgweb.dao.ReportDao;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asha.icgweb.service.ReportService;
import com.asha.icgweb.utils.HMSUtil;
import com.asha.icgweb.utils.Box;

@RequestMapping("/report")
@RestController
@CrossOrigin
public class ReportWebController {

	@Autowired
	ReportService reportService;
	@Autowired
	ReportDao reportDao;
	
	
	
	
	@RequestMapping(value = "/printVisitTokenReport", method = RequestMethod.POST)
	public ModelAndView printVisitTokenReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		Box box= HMSUtil.getBox(request);
		JSONObject json = new JSONObject(box);
		System.out.println("json="+json);
		JSONArray sVISIT_ID= new JSONArray();
		if(json.get("visit_id") !=null)
		{
			sVISIT_ID = json.getJSONArray(("visit_id"));
		}
		int VISIT_ID= Integer.parseInt(sVISIT_ID.getString(0));
		System.out.println("VISIT_ID="+VISIT_ID);
		
		parameters.put("VISIT_ID", VISIT_ID);
		parameters.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/reports/"));
		
		System.out.println(request.getServletContext().getRealPath("/reports/"));

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Token_Slip_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	@RequestMapping(value = "/printCaseSheet", method = RequestMethod.POST)
	public ModelAndView printCaseSheet(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		Box box= HMSUtil.getBox(request);
		JSONObject json = new JSONObject(box);
		System.out.println("json="+json);
		JSONArray sVISIT_ID= new JSONArray();
		if(json.get("visit_id") !=null)
		{
			sVISIT_ID = json.getJSONArray(("visit_id"));
		}
		int VISIT_ID= Integer.parseInt(sVISIT_ID.getString(0));
		System.out.println("VISIT_ID="+VISIT_ID);
		
		parameters.put("VISIT_ID", VISIT_ID);
		parameters.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/reports/"));
		
		System.out.println(request.getServletContext().getRealPath("/reports/"));

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("opd_casesheet_new_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	@RequestMapping(value = "/printPrescriptionSlip", method = RequestMethod.POST)
	public ModelAndView printPrescriptionSlip(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		Box box= HMSUtil.getBox(request);
		JSONObject json = new JSONObject(box);
		System.out.println("json="+json);
		JSONArray sVISIT_ID= new JSONArray();
		if(json.get("visit_id") !=null)
		{
			sVISIT_ID = json.getJSONArray(("visit_id"));
		}
		int VISIT_ID= Integer.parseInt(sVISIT_ID.getString(0));
		System.out.println("VISIT_ID="+VISIT_ID);
		
		parameters.put("VISIT_ID", VISIT_ID);
		parameters.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/reports/"));
		
		System.out.println(request.getServletContext().getRealPath("/reports/"));

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("OPD_Prescription_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	
	@RequestMapping(value = "/printInvestigationSlip", method = RequestMethod.POST)
	public ModelAndView printInvestigationSlip(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		Box box= HMSUtil.getBox(request);
		JSONObject json = new JSONObject(box);
		System.out.println("json="+json);
		JSONArray sVISIT_ID= new JSONArray();
		if(json.get("visit_id") !=null)
		{
			sVISIT_ID = json.getJSONArray(("visit_id"));
		}
		int VISIT_ID= Integer.parseInt(sVISIT_ID.getString(0));
		System.out.println("VISIT_ID="+VISIT_ID);
		
		parameters.put("VISIT_ID", VISIT_ID);
		parameters.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/reports/"));
		
		System.out.println(request.getServletContext().getRealPath("/reports/"));

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Investigation_Slip_report_3", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	
	  
}




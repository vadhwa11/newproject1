package com.icg.jkt.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icg.jkt.service.MasterService;
import com.icg.jkt.service.OpdMasterService;

/**
 * Copyright 2019 JK Technosoft Ltd. All rights reserved. Use is subject to
 * license terms. Purpose of the HMS - This is for login.
 * 
 * @author Krishna Thakur Create Date: 16/01/2019
 * @version 0.1
 */

@RequestMapping("/v0.1/opdmaster")
@RestController
@CrossOrigin
public class OpdMasterController {

	@Autowired
	OpdMasterService ms;

	//////// Get Master Department List ///////////////////////////////

	@RequestMapping(value = "/getDepartmentList", method = RequestMethod.GET)
	public String departmentList(@RequestBody HashMap<String, String> jsondata, HttpServletRequest request) {
		return ms.departmentList(jsondata, request);
	}

	////////////////// Get Master ICD List /////////////////////////////

	@RequestMapping(value = "/getICDList", method = RequestMethod.POST)
	public String getICD(@RequestBody HashMap<String, String> jsondata, HttpServletRequest request) {
		return ms.getICD(jsondata, request);
	}

	////////////////// Get Master ICD List /////////////////////////////

	@RequestMapping(value = "/getInvestigationList", method = RequestMethod.POST)
	public String getInvestigation(@RequestBody HashMap<String, String> jsondata, HttpServletRequest request) {
		return ms.getInvestigation(jsondata, request);
	}

	////////////////// Get Master MasStoreItem List
	////////////////// /////////////////////////////

	@RequestMapping(value = "/getMasStoreItem", method = RequestMethod.POST)
	public String getMasStoreItem(@RequestBody HashMap<String, String> jsondata, HttpServletRequest request) {
		return ms.getMasStoreItem(jsondata, request);
	}

	////////////////// Get Master MasStoreItem List //////////////////
	////////////////// /////////////////////////////

	@RequestMapping(value = "/getMasFrequency", method = RequestMethod.POST)
	public String getMasFrequency(@RequestBody HashMap<String, String> jsondata, HttpServletRequest request) {
		return ms.getMasFrequency(jsondata, request);
	}

	@RequestMapping(value = "/getTemplateName", method = RequestMethod.POST)
	public String getTemplateName(@RequestBody HashMap<String, String> jsondata, HttpServletRequest request) {
		return ms.getTemplateName(jsondata, request);
	}

	@RequestMapping(value = "/getTemplateInvestData", method = RequestMethod.POST)
	public String getTemplateInvestData(@RequestBody HashMap<String, Object> jsondata, HttpServletRequest request) {
		return ms.getTemplateInvestData(jsondata, request);
	}
	
	@RequestMapping(value = "/getTemplateTreatmentData", method = RequestMethod.POST)
	public String getTemplateTreatmentData(@RequestBody HashMap<String, Object> jsondata, HttpServletRequest request) {
		return ms.getTemplateTreatmentData(jsondata, request);
	}

	////////////////// Get Empanelled Hospital List //////////////////

	@RequestMapping(value = "/getEmpanelledHospital", method = RequestMethod.POST)
	public String getEmpanelledHospital(@RequestBody HashMap<String, String> jsondata, HttpServletRequest request) {
		return ms.getEmpanelledHospital(jsondata, request);
	}

	////////////////// Get Disposal List //////////////////

	@RequestMapping(value = "/getDisposalList", method = RequestMethod.POST)
	public String getDisposalList(@RequestBody HashMap<String, String> jsondata, HttpServletRequest request) {
		return ms.getDisposalList(jsondata, request);
	}

	////////////////// Get MasNursingCare List //////////////////

	@RequestMapping(value = "/getMasNursingCare", method = RequestMethod.POST)
	public String getMasNursingCare(@RequestBody HashMap<String, Object> jsondata, HttpServletRequest request) {
		return ms.getMasNursingCare(jsondata, request);
	}

	/*
	 * Method to Execute Stored Procedure: Kaushal Mishra
	 * 
	 * @RequestMapping(value = "/executeDbProcedure", method = RequestMethod.POST)
	 * public String executeDbProcedure(@RequestBody String jsondata,
	 * HttpServletRequest request) { return ms.executeDbProcedure(jsondata); }
	 */
	/* Method to Execute Stored Procedure: Kaushal Mishra */

	@RequestMapping(value = "/executeDbProcedure", method = RequestMethod.POST)
	public String executeDbProcedure(@RequestBody String jsondata, HttpServletRequest request){
		return ms.executeDbProcedure(jsondata);
	}
	
	
	@RequestMapping(value = "/executeDbProcedureforStatistics", method = RequestMethod.POST)
	public String executeDbProcedureforStatistics(@RequestBody String jsondata, HttpServletRequest request){
		System.out.println("sss");
		return ms.executeDbProcedureforStatistics(jsondata);
	}
	
	//rajdeo dashboard
	//rajdeo
		@RequestMapping(value="/executeProcedureForDashBoard", method = RequestMethod.POST, produces="application/json", consumes="application/json")
		public String executeProcedureForDashBoard(@RequestBody HashMap<String, Object> dashboardPayload, HttpServletRequest request, HttpServletResponse response) {
			return ms.executeProcedureForDashBoard(dashboardPayload,request,response);
		}
}

package com.icg.jkt.service;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;

@Repository
public interface OpdMasterService {

	String departmentList(HashMap<String, String> jsondata, HttpServletRequest request);

	String getICD(HashMap<String, String> jsondata, HttpServletRequest request);

	String getInvestigation(HashMap<String, String> jsondata, HttpServletRequest request);

	String getMasStoreItem(HashMap<String, String> jsondata, HttpServletRequest request);

	String getMasFrequency(HashMap<String, String> jsondata, HttpServletRequest request);

	String getTemplateName(HashMap<String, String> jsondata, HttpServletRequest request);

	String getEmpanelledHospital(HashMap<String, String> jsondata, HttpServletRequest request);

	String getDisposalList(HashMap<String, String> jsondata, HttpServletRequest request);

	String getMasNursingCare(HashMap<String, Object> jsondata, HttpServletRequest request);
	
	String getMasItemClass(HashMap<String, Object> jsondata, HttpServletRequest request);

	String getTemplateInvestData(HashMap<String, Object> jsondata, HttpServletRequest request);

	String getTemplateTreatmentData(HashMap<String, Object> jsondata, HttpServletRequest request);

	String executeDbProcedure(String jsondata);

	String executeDbProcedureforStatistics(String jsondata);

	String getDispUnit(HashMap<String, Object> jsondata, HttpServletRequest request);

	

	//String executeDbProcedure(String jsondata) ;
	String executeProcedureForDashBoard(Map<String, Object> dashboardPayload, HttpServletRequest request, HttpServletResponse response);

	

}

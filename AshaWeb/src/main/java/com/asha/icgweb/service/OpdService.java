package com.asha.icgweb.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;

import com.asha.icgweb.model.OpdModel;

@Repository
public interface OpdService {

	String preConsPatientWatingWeb(String payload, HttpServletRequest request, HttpServletResponse response);

	String saveVitailsPatientdetails(String payload, HttpServletRequest request, HttpServletResponse response);

	String getIdealWeight(String payload, HttpServletRequest request, HttpServletResponse response);

	String getOpdWaitingList(String payload, HttpServletRequest request, HttpServletResponse response);

	String getIcdList(String payload, HttpServletRequest request, HttpServletResponse response);

	//String getOpdPatinetDetails(String payload, HttpServletRequest request, HttpServletResponse response);

	String getFamilyPatientHistory(String payload, HttpServletRequest request, HttpServletResponse response);

	String getOpdPatientDetailModel(String payload,HttpServletRequest request,
			HttpServletResponse response);

	String saveOpdPatientdetails(String payload, HttpServletRequest request, HttpServletResponse response);

	String saveOpdInvestigationTemplates(String payload, HttpServletRequest request, HttpServletResponse response);
	
	String saveTreatmentOpdTemplates(String payload, HttpServletRequest request, HttpServletResponse response);

	String getIinvestigationList(String payload, HttpServletRequest request, HttpServletResponse response);

	String getMasStoreItemList(String payload, HttpServletRequest request, HttpServletResponse response);
	
	String obesityWaitingList(String payload);
	
	String getObesityDetail(String payload, HttpServletRequest request, HttpServletResponse response);

	String getMasDisposalList(String payload, HttpServletRequest request, HttpServletResponse response);

	String getMasFrequency(String payload, HttpServletRequest request, HttpServletResponse response);

	String getTemplateName(String payload, HttpServletRequest request, HttpServletResponse response);

	String getEmpanelledHospital(String payload, HttpServletRequest request, HttpServletResponse response);

	String getMasNursingCare(String payload, HttpServletRequest request, HttpServletResponse response);

	String getTemplateInvestigation(String payload, HttpServletRequest request, HttpServletResponse response);
    
	String getTemplateTreatment(String payload, HttpServletRequest request, HttpServletResponse response);
	
	String getCaseSheet(String payload, HttpServletRequest request, HttpServletResponse response);
	String getExaminationDetail(String payload, HttpServletRequest request, HttpServletResponse response);

	String updatAndInsertPatientRecall(String payload, HttpServletRequest request, HttpServletResponse response);

	String deleteGridRow(String payload, HttpServletRequest request, HttpServletResponse response);

	String getPatientReferalDetail(String payload, HttpServletRequest request, HttpServletResponse response);

	String getPatientHistoryDetail(String payload, HttpServletRequest request, HttpServletResponse response);

	String getTreatmentPatientDetail(String payload, HttpServletRequest request, HttpServletResponse response);

	String getInvestigationDetail(String payload, HttpServletRequest request, HttpServletResponse response);

	String getOpdPreviousVisitRecord(String payload, HttpServletRequest request, HttpServletResponse response);

	String getOpdReportsDetailsbyServiceNo(String payload, HttpServletRequest request, HttpServletResponse response);

	String getOpdReportsDetailsbyPatinetId(String payload, HttpServletRequest request, HttpServletResponse response);

	String getOpdPreviousVital(String payload, HttpServletRequest request, HttpServletResponse response);

	

	

	
	
}


package com.icg.jkt.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public interface PatientRegistrationService {


	Map<String,Object> findPatientAndDependentFromEmployee(Map<String, String> requestData);
	Map<String, Object> getRecordsFordepartmentBloodGroupAndMedicalCategory();
	Map<String, Object> getTokenNoForDepartmentMultiVisit(Map<String, Object> requestData);
	String submitPatientDetails(String requestData);
	Map<String, Object> showAppointmentForOthers();
	Map<String, Object> getTokenNoOfDepartmentForOthers(Map<String, Object> requestData);
	String submitPatientDetailsForOthers(String requestData);
	Map<String, Object> searchOthersRegisteredPatient(Map<String, String> requestData);
	Map<String, Object> patientListForUploadDocument(Map<String, String> requestData);
	Map<String, Object> patientAppointmentHistory(Map<String, String> requestData);
	Map<String, Object> patientVisitCancellation(Map<String, String> requestData);
	void printVisitTokenReport(String requestData, HttpServletRequest request, HttpServletResponse response);
	

}

package com.icg.jkt.service;


import java.util.Map;

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
	

}

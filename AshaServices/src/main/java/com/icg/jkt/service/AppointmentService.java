package com.icg.jkt.service;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {

	Map<String, Object> getDataForDoctorAppointment();
	Map<String, Object> getappointmentSetupDetails(Map<String, String> requestData);
	Map<String, Object> getlocationWiseAppointmentType(Map<String, String> requestData);
	String submitAppointmentSetup(String requestData);
	Map<String, Object> getAppointmentSessionDetails();
	String submitAppointmentSession(String requestData);
	Map<Long, String> getAllHospitalList1();
	/*
	 * String submitAppointmentByWebportal(String requestData) throws JSONException,
	 * ParseException;
	 */
 
	String getAllAppointmentSession(JSONObject jsonObject);
	Map<String, Object> getHospitalList( String requestData);
	Map<String, Object> getAppointmentHistory(String requestData);
 
	String updateAppointmentSession(String requestData);
	Map<String, Object> reschedulePatientAppointment(String requestData);
	Map<String, Object> getRecordsForDoctorAppointment();
	Map<String, Object> getOpdHistory(String requestData);
	Map<String, Object> checkVisitExistOrNot(String requestData);

}

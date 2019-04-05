package com.icg.jkt.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {

	Map<String, Object> getRecordsForDoctorAppointment();
	Map<String, Object> getappointmentSetupDetails(Map<String, String> requestData);
	Map<String, Object> getlocationWiseAppointmentType(Map<String, String> requestData);
	String submitAppointmentSetup(String requestData);
	Map<String, Object> getAppointmentSessionDetails();
	String submitAppointmentSession(String requestData);
	String getAllAppointmentSession(JSONObject jsonObject);

}
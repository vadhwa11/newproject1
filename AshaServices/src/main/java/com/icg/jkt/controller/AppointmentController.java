package com.icg.jkt.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icg.jkt.service.AppointmentService;

@RestController
@CrossOrigin
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	// This path will change to dataForAppointmentSetUo
	@RequestMapping(value = "/recordsforAppointmentSetUp", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getRecordsForDoctorAppointment() {
		Map<String, Object> dataForDoctorAppointment = new HashMap<String,Object>();
		dataForDoctorAppointment = appointmentService.getDataForDoctorAppointment();
		if (dataForDoctorAppointment.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<String, Object>>(dataForDoctorAppointment, HttpStatus.OK);
	}
	
	
	
	
	  @RequestMapping(value = "/locationWiseAppointmentType", method =RequestMethod.POST) 
	  public ResponseEntity<Map<String, Object>>getlocationWiseAppointmentType(@RequestBody Map<String, String> requestData){
		  Map<String, Object> locationWiseAppoitmentType = new HashMap<String,Object>(); 
		  locationWiseAppoitmentType = appointmentService.getlocationWiseAppointmentType(requestData);
		  if(locationWiseAppoitmentType.isEmpty()) { 
			 return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT); 
		} 
		     return new ResponseEntity<Map<String, Object>>(locationWiseAppoitmentType,HttpStatus.OK);
   }
	 
	
	
	@RequestMapping(value = "/appointmentSetupDetails", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getappointmentSetupDetails(@RequestBody Map<String, String> requestData) {
		Map<String, Object> recordsForAppointmentSetupDetails = new HashMap<String,Object>();
		recordsForAppointmentSetupDetails = appointmentService.getappointmentSetupDetails(requestData);
		if (recordsForAppointmentSetupDetails.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(recordsForAppointmentSetupDetails, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/submitAppointmentSetup", method = RequestMethod.POST)
	public ResponseEntity<String> submitAppointmentSetup(@RequestBody String requestData) {
		System.out.println("submitAppointmentSetup: " +requestData);
		String recordsForAppointmentSetupDetails = appointmentService.submitAppointmentSetup(requestData);
		if (recordsForAppointmentSetupDetails.isEmpty()) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(recordsForAppointmentSetupDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/showappointmentsession", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> showAppointmentSession() {
		Map<String, Object> appointmentSessionDetails = new HashMap<String,Object>();
		appointmentSessionDetails = appointmentService.getAppointmentSessionDetails();
		if (appointmentSessionDetails.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<String, Object>>(appointmentSessionDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/submitappointmentsession", method = RequestMethod.POST)
	public ResponseEntity<String> submitAppointmentSession(@RequestBody String requestData) {
		String appointmentSessionDetails = appointmentService.submitAppointmentSession(requestData);
		if (appointmentSessionDetails.isEmpty()) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(appointmentSessionDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllAppointmentSession", method=RequestMethod.POST,produces = "application/json", consumes = "application/json")
	public String getAllAppointmentSession(@RequestBody Map<String, Object> requestData) {
		JSONObject jsonObject = new JSONObject(requestData);
		return appointmentService.getAllAppointmentSession(jsonObject);
	}
	
	@RequestMapping(value = "/hospitals", method =RequestMethod.POST) 
	  public ResponseEntity<Map<String, Object>>getHospitalList(){
		  Map<String, Object> hospitalList = new HashMap<String,Object>(); 
		  String data ="{}";
		  hospitalList = appointmentService.getHospitalList(data);
		  if(hospitalList.isEmpty()) { 
			 return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT); 
		} 
		     return new ResponseEntity<Map<String, Object>>(hospitalList,HttpStatus.OK);
}
	
	
	@RequestMapping(value = "/updateAppointmentSession", method = RequestMethod.POST)
	public ResponseEntity<String> updateAppointmentSession(@RequestBody String requestData) {
		String updatedStatusAppSession = appointmentService.updateAppointmentSession(requestData);
		if (updatedStatusAppSession.isEmpty()) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(updatedStatusAppSession, HttpStatus.OK);
	}
	
	
}

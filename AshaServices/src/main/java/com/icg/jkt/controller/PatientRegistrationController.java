package com.icg.jkt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icg.jkt.service.PatientRegistrationService;

@RestController
@CrossOrigin
@RequestMapping("/registration")
public class PatientRegistrationController {

	@Autowired
	private PatientRegistrationService patientRegistrationService;

	@RequestMapping(value = "/patients", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findPatientAndDependentFromEmployee(@RequestBody Map<String, String> requestData) {
		Map<String, Object> patientAndDependentFromEmployee = new HashMap<String,Object>();
		 patientAndDependentFromEmployee = patientRegistrationService.findPatientAndDependentFromEmployee(requestData);
		if (patientAndDependentFromEmployee.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<String, Object>>(patientAndDependentFromEmployee, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/departmentBloodGroupAndMedicalCategory", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getRecordsFordepartmentBloodGroupAndMedicalCategory() {
		System.out.println("inside departmentBloodGroupAndMedicalCategory");
		Map<String, Object> recordsFordepartmentBloodGroupAndMedicalCategory = new HashMap<String,Object>();
		recordsFordepartmentBloodGroupAndMedicalCategory = patientRegistrationService.getRecordsFordepartmentBloodGroupAndMedicalCategory();
		if (recordsFordepartmentBloodGroupAndMedicalCategory.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<String, Object>>(recordsFordepartmentBloodGroupAndMedicalCategory, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/tokenNoForDepartmentMultiVisit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getTokenNoForDepartmentMultiVisit(@RequestBody Map<String, Object> requestData) {
		Map<String, Object> tokenNoForDepartmentMultiVisit = new HashMap<String,Object>();
		System.out.println("requestDatatokenNoForDepartmentMultiVisit :" + requestData);
		tokenNoForDepartmentMultiVisit = patientRegistrationService.getTokenNoForDepartmentMultiVisit(requestData);
		if (tokenNoForDepartmentMultiVisit.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
		System.out.println(tokenNoForDepartmentMultiVisit);
		return new ResponseEntity<Map<String, Object>>(tokenNoForDepartmentMultiVisit, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/submitPatientDetails", method = RequestMethod.POST)
	public ResponseEntity<String> submitPatientDetails(@RequestBody String requestData) {
		String result="";
		System.out.println("requestDatasubmitPatientDetails:" + requestData);
		result = patientRegistrationService.submitPatientDetails(requestData);
		if (result.isEmpty()) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/showAppointmentForOthers", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> showAppointmentForOthers() {
		Map<String, Object> showAppointmentForOthers = new HashMap<String,Object>();
		showAppointmentForOthers = patientRegistrationService.showAppointmentForOthers();
		if (showAppointmentForOthers.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<String, Object>>(showAppointmentForOthers, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/tokenNoOfDepartmentForOthers", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getTokenNoOfDepartmentForOthers(@RequestBody Map<String, Object> requestData) {
		Map<String, Object> tokenNoForOthers = new HashMap<String,Object>();
		System.out.println("tokenNoForOthers :" + requestData);
		tokenNoForOthers = patientRegistrationService.getTokenNoOfDepartmentForOthers(requestData);
		if (tokenNoForOthers.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<String, Object>>(tokenNoForOthers, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/submitPatientDetailsForOthers", method = RequestMethod.POST)
	public ResponseEntity<String> submitPatientDetailsForOthers(@RequestBody String requestData) {
		
		String result = patientRegistrationService.submitPatientDetailsForOthers(requestData);
		if (result.isEmpty()) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/searchOthersRegisteredPatient", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> searchOthersRegisteredPatient(@RequestBody Map<String, String> requestData) {
		Map<String, Object> patientDetails = new HashMap<String,Object>();
		patientDetails = patientRegistrationService.searchOthersRegisteredPatient(requestData);
		if (patientDetails.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<String, Object>>(patientDetails, HttpStatus.OK);
	}
	
}

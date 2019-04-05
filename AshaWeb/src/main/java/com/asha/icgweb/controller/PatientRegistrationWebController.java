package com.asha.icgweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asha.icgweb.service.RegistrationService;

@RequestMapping("/registration")
@RestController
@CrossOrigin
public class PatientRegistrationWebController {

	@Autowired
	RegistrationService registrationService;
	

	@RequestMapping(value="/showemployeeanddependent", method = RequestMethod.GET)
	public ModelAndView showEmployeeAndDependentJsp(HttpServletRequest request, HttpServletResponse response) {
		String  data ="{}";
		String jsp="";
		data=registrationService.getDepartmentBloodGroupAndMedicalCategory(data,request, response);
		jsp = "showEmployeeAndDependent";
		return new ModelAndView(jsp, "data", data);
	}
	
	@RequestMapping(value="/getEmployeeAndDependentlist", method = RequestMethod.POST)
	public String getEmployeeAndDependentData(@RequestBody String data, HttpServletRequest request,
			HttpServletResponse response) {	
		return registrationService.getEmployeeAndDependentData(data, request, response);
	}
	
	
	
	@RequestMapping(value="/tokenNoForDepartmentMultiVisit", method = RequestMethod.POST)
	public String getTokenNoForDepartmentMultiVisit(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {	
		return registrationService.getTokenNoForDepartmentMultiVisit(data, request, response);
	}
	

	@RequestMapping(value = "/submitPatientDetails", method = RequestMethod.POST)
	  public String submitPatientDetails(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
		return registrationService.submitPatientDetails(data, request, response);
		
	  }	
	
	
	  @RequestMapping(value = "/showVisitTokenList", method = RequestMethod.GET)
	  public ModelAndView showVisitTokenList(HttpServletRequest request, HttpServletResponse response) {
	  String result= request.getParameter("visitList");
	  String jsp = "responseMessage"; 
	  return new ModelAndView(jsp, "result", result); 
	  }
	 
	
	
	@RequestMapping(value="/registrationandappointmentothers", method = RequestMethod.GET)
	public ModelAndView showRegistrationAndAppointmentOthers(HttpServletRequest request, HttpServletResponse response) {
		String data ="{}";
		String jsp="";
		data=registrationService.showRegistrationAndAppointmentOthers(data,request, response);
		jsp = "othersappointmentandregistration";
		return new ModelAndView(jsp, "data", data);
	}
	
	
	
	@RequestMapping(value="/tokenNoOfDepartmentForOthers", method = RequestMethod.POST)
	public String getTokenNoOfDepartmentForOthers(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {	
		return registrationService.getTokenNoOfDepartmentForOthers(data, request, response);
	}
	
	
	@RequestMapping(value = "/submitPatientDetailsForOthers", method = RequestMethod.POST)
	  public String submitPatientDetailsForOthers(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
		String	result = registrationService.submitPatientDetailsForOthers(data, request, response);
		return result;
	  }	
	
	  @RequestMapping(value = "/showVisitTokenForOthers", method = RequestMethod.GET)
	  public ModelAndView showVisitTokenForOthers(HttpServletRequest request, HttpServletResponse response) {
	  String result= request.getParameter("visitId");
	  String jsp = "responseMessage"; 
	  return new ModelAndView(jsp, "result", result);
	  }
	
	  @RequestMapping(value="/searchOthersRegisteredPatient", method = RequestMethod.POST)
		public String searchOthersRegisteredPatient(@RequestBody String data, HttpServletRequest request,
				HttpServletResponse response) {	
			return registrationService.searchOthersRegisteredPatient(data, request, response);
		}
	  
}



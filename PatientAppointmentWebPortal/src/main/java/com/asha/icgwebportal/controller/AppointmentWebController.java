package com.asha.icgwebportal.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asha.icgwebportal.utils.HMSUtil;
import com.asha.icgwebportal.utils.RestUtils;
import com.asha.icgwebportal.vo.PatientDetails;

@RequestMapping("/appointment")
@RestController
@CrossOrigin
public class AppointmentWebController {

	String IpAndPortNo=HMSUtil.getProperties("urlextension.properties","OSB_IP_AND_PORT");
	
	@RequestMapping(value="/showappointmentsetup", method = RequestMethod.GET)
	public ModelAndView showRecordsForDoctorAppointment(HttpServletRequest request, HttpServletResponse response) {
		String data="";
		String jsp = "patientPersonalDetails";
		return new ModelAndView(jsp, "data", data);
	}
	
	//Get Patient Details by serviceId 
	@RequestMapping(value="/getServiceDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getServiceDetails(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,Model model) {
		String serviceNumber="";
		  serviceNumber=request.getParameter("serviceNumber");
		  session.setAttribute("serviceNumber", serviceNumber);
		JSONObject json = new JSONObject();
		json.put("serviceNo", serviceNumber);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String,String>();
		model.addAttribute("serviceNo", serviceNumber);
		
		 String Url = HMSUtil.getProperties("urlextension.properties","PATIEN_DETAILS"); String
		 OSBURL = IpAndPortNo + Url;
		 String patientData =RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders,json.toString());
		

	  // String patientData =RestUtils.postWithHeaders("http://localhost:8082/AshaServices/registration/patients", requestHeaders,json.toString()); 
	    model.addAttribute("patientDetail",new PatientDetails());
	    return new ModelAndView("patientPersonalDetails", "patientData", patientData);
	}
	
	//Book Appointment
	
	@RequestMapping(value="/bookAppointment", method = RequestMethod.POST)
	public ModelAndView bookAppointment(@ModelAttribute("patientDetail") PatientDetails patientDetail,@RequestBody String data,HttpServletRequest request,Model model,
			HttpServletResponse response) {
		String patientDetails=request.getParameter("patient");
		String serviceNo=request.getParameter("serviceNo");
		String[] patientDetailsArray = patientDetails.split(",");
		PatientDetails patientDetail1=new PatientDetails();
		patientDetail.setServiceNo(serviceNo);
		patientDetail.setFullName(patientDetailsArray[0]);
		patientDetail.setRelation(patientDetailsArray[1]);
		patientDetail.setDateOfBirth(patientDetailsArray[2]);
		patientDetail.setAge(patientDetailsArray[3]);
		patientDetail.setGenderId(patientDetailsArray[4]);
		if(patientDetailsArray[4].equalsIgnoreCase("1"))
			patientDetail.setGender("MALE");
		else
			patientDetail.setGender("FEMALE");
		patientDetail.setUhidNo(patientDetailsArray[5]);
		patientDetail.setEmpId(patientDetailsArray[6]);
		patientDetail.setRelationId(patientDetailsArray[7]);
		
		System.out.println("patientDetails: "+patientDetails);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String,String>();
		
		
		 String Url = HMSUtil.getProperties("urlextension.properties","RECORD_FOR_APPOINTMENT_SETUP"); 
		 String OSBURL = IpAndPortNo + Url;
		 data="{}"; 
		 String departmentList =RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders,data);
		
	    //String departmentList = RestUtils.postWithHeaders("http://localhost:8082/AshaServices/appointment/recordsforAppointmentSetUp", requestHeaders, data); 
		model.addAttribute("departmentList", departmentList);
		
		
		  MultiValueMap<String,String> requestHeaders1 = new LinkedMultiValueMap<String,String>();
		
	     String Url1 = HMSUtil.getProperties("urlextension.properties","HOSPITAL_LIST");
	     String OSBURL1 = IpAndPortNo + Url1;
	     String hospitalList=RestUtils.postWithHeaders(OSBURL1.trim(), requestHeaders1,data);
		
		 
		
		//String hospitalList =RestUtils.postWithHeaders("http://localhost:8082/AshaServices/appointment/getHospitalList", requestHeaders, data); 
		model.addAttribute("hospitalList", hospitalList);
		
		return new ModelAndView("bookAppointment", "patientDetail", patientDetail);
	}
	
	//Get Appointment Type
	
	@RequestMapping(value="/getAppointmentType", method = RequestMethod.POST)
	public String getAppointmentType(@RequestBody String data,HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String,String>();
		String Url = HMSUtil.getProperties("urlextension.properties","LOCATION_WISE_APPOINTMENT");
		String OSBURL = IpAndPortNo + Url;
	    return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders,data);
	
		 
		
			//return RestUtils.postWithHeaders("http://localhost:8082/AshaServices/appointment/locationWiseAppointmentType", requestHeaders, data); 
	}
	
	//Get Appointment Tokens
	
		@RequestMapping(value="/getAppointmentTokens", method = RequestMethod.POST)
		public String getAppointmentTokens(@RequestBody String data,HttpServletRequest request,HttpServletResponse response) {
	
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String,String>();
		System.out.println("data: "+data);
	    String Url =HMSUtil.getProperties("urlextension.properties","TOKEN_FOR_MULTI_VISIT");
		String OSBURL = IpAndPortNo + Url;
		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders,data);
		
			
			//return RestUtils.postWithHeaders("http://localhost:8082/AshaServices/registration/tokenNoForDepartmentMultiVisit", requestHeaders, data);
		   
		}
		
		//Submit Appointment
		
		@RequestMapping(value="/submitBookAppointment", method = RequestMethod.POST)
		public ModelAndView submitBookAppointment(@ModelAttribute("patientDetail") PatientDetails patientDetails,HttpServletRequest request,Model model,
				HttpServletResponse response) {
			String patientDetail=request.getParameter("patient");
			String serviceNo=request.getParameter("serviceNo");
			
		    String a = patientDetails.getAppointmentTypeId()[0];
			JSONObject patientinfo = new JSONObject(patientDetails);
			
			
			 Date visitStratDate = HMSUtil.convertStringDateToUtilDate(patientDetails.getAppointmentDate(), "yyyy-MM-dd"); 
			 String visitDate = HMSUtil.convertDateToStringFormat(visitStratDate, "dd-MM-yyyy");
			patientinfo.put("visitDate", visitDate);
			patientinfo.put("hospitalId", patientDetails.getHospital());
			JSONObject json = new JSONObject();
			JSONArray appJSONArray = new JSONArray(Arrays.asList(patientDetails.getAppointmentTypeId()));
			JSONArray tokenJSONArray = new JSONArray(Arrays.asList(patientDetails.getTokenIds()));
			json.put("departmentId", patientDetails.getDepartmentId());
			json.put("priorityId", "3");
			json.put("appointmentTypeId",appJSONArray);
			json.put("tokenIds", tokenJSONArray);
			json.put("patientDetailsForm", patientinfo);
			
			
			
			MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String,String>();
		    String Url = HMSUtil.getProperties("urlextension.properties","SUBMIT_PATIENT_DETAILS");
		    String OSBURL = IpAndPortNo + Url; String responseData =
		    RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders,json.toString());
		
			//String responseData = RestUtils.postWithHeaders("http://localhost:8082/AshaServices/registration/submitPatientDetails", requestHeaders, json.toString()); 
			
			JSONObject responsedata = new JSONObject(responseData);
			model.addAttribute("message", "Appointment has been booked.");
			JSONArray respVisit = responsedata.getJSONArray("visitList");
			if(respVisit!=null || respVisit.length()>0)
			model.addAttribute("visitId",respVisit.get(0));
			model.addAttribute("serviceNumber",serviceNo);
			//...
             System.out.println(" submitresponseData"+responseData+"visitId"+respVisit.get(0));
			 return new ModelAndView("taketoken", "responseData", responseData);
}
		
		//get Appointment History
		@RequestMapping(value="/getAppointmentHistory", method = {RequestMethod.POST,RequestMethod.GET})
		public ModelAndView getAppointmentHistory(@ModelAttribute("patientDetail") PatientDetails patientDetails1,HttpServletRequest request,Model model,
				HttpServletResponse response) {
			String patientDetails=request.getParameter("patient");
			String serviceNo=request.getParameter("serviceNo");
			
			 patientDetails1.setServiceNo(serviceNo);
			
		/*
		 * String[] patientDetailsArray = patientDetails.split(","); PatientDetails
		 * patientDetail=new PatientDetails(); patientDetail.setServiceNo(serviceNo);
		 * patientDetail.setFullName(patientDetailsArray[0]);
		 * patientDetail.setRelation(patientDetailsArray[1]);
		 * patientDetail.setDateOfBirth(patientDetailsArray[2]);
		 * patientDetail.setAge(patientDetailsArray[3]);
		 * patientDetail.setGender(patientDetailsArray[4]);
		 * System.out.println("(appointment History): "+patientDetails1);
		 */
			JSONObject json = new JSONObject(patientDetails1);
		//	JSONObject json = new JSONObject(serviceNo.toString());
			System.out.println("get appointment history json create data:----"+json);
			MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String,String>();
		  String Url = HMSUtil.getProperties("urlextension.properties","APPOINTMENT_HISTORY");
		  String OSBURL = IpAndPortNo + Url; 
		  String appointmentList = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders,json.toString());
		
		
			//String appointmentList =RestUtils.postWithHeaders("http://localhost:8082/AshaServices/appointment/getAppointmentHistory", requestHeaders, json.toString()); 
			//model.addAttribute("appointmentList", appointmentList);
			model.addAttribute("serviceNo", serviceNo);
			return new ModelAndView("appointmentHistory", "appointmentList", appointmentList);
		}
		
		@RequestMapping(value = "/getAllAppointmentList", method = RequestMethod.POST)
		public String getAllAppointmentList(@RequestBody Map<String, Object> payload,
				HttpServletRequest request, HttpServletResponse response) {
		String appointmentList="";
		JSONObject json = new JSONObject(payload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
	    String Url =HMSUtil.getProperties("urlextension.properties","APPOINTMENT_HISTORY");
		String OSBURL = IpAndPortNo + Url;
		 appointmentList = RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders,json.toString());
		// appointmentList =RestUtils.postWithHeaders("http://localhost:8082/AshaServices/appointment/getAppointmentHistory", requestHeaders, json.toString()); 
			return  appointmentList;
		}
		
		@RequestMapping(value="/searchAppointment", method=RequestMethod.POST)
		public String addPhsiotherapy(@RequestBody String requestPayload, HttpServletRequest request, HttpServletResponse response) {
			String searchAppointmentResponse="";	
			JSONObject jsonObject = new JSONObject(requestPayload);
			MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
			
			
		 String Url =HMSUtil.getProperties("urlextension.properties","APPOINTMENT_HISTORY");
		 String OSBURL = IpAndPortNo + Url;
		
		 searchAppointmentResponse = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders,jsonObject.toString());
		
			//searchAppointmentResponse = RestUtils.postWithHeaders("http://localhost:8082/AshaServices/appointment/getAppointmentHistory", requestHeaders, jsonObject.toString());
			System.out.println("appointment history search data:----"+searchAppointmentResponse);
			
			return searchAppointmentResponse;
		}
		
		@RequestMapping(value = "/rescheduleAppointmentDataGet", method = RequestMethod.POST)
		public ResponseEntity<String> rescheduleAppointmentDataGet(@RequestBody String requestData) {
			System.out.println("requestDatasubmitPatientDetails:" + requestData);
			//String result = patientRegistrationService.submitPatientDetails(requestData);
			if (requestData.isEmpty()) {
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<String>(requestData, HttpStatus.OK);
		}
		
		 @RequestMapping(value = "/rescheduleAppointment", method = RequestMethod.GET)
		  public ModelAndView rescheduleAppointment(@ModelAttribute("patientDetail") PatientDetails patientDetails,HttpServletRequest request, HttpServletResponse response) {
		  System.out.println("inside rescheduleAppointment"); 
		  String result= request.getParameter("appointmentData");
		  String[] data = result.split(",");
		  
		  String start_dt = data[3];
		  Date date=null;
		  DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD"); 
		  try {
			 date = (Date)formatter.parse(start_dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
		  String dob = newFormat.format(date);
		  patientDetails.setVisitId(data[0]);
		  patientDetails.setServiceNo(data[1]);
		  patientDetails.setFullName(data[2]);
		  patientDetails.setDateOfBirth(dob);
		  patientDetails.setGender(data[4]);
		  patientDetails.setHospital(data[5]);
		  patientDetails.setDepartment(data[6]);
		  patientDetails.setHospitalId(data[7]);
		  patientDetails.setDepartmentId(data[8]);
		  patientDetails.setAppointmentType(data[9]);
		  patientDetails.setAppointmentId(data[10]);
		  patientDetails.setRelation(data[11]);
		  System.out.println("inside rescheduleAppointment"+result); 
		  String jsp = "rescheduleAppointment"; 
		  return new ModelAndView(jsp, "patientDetails", patientDetails); 
		  }
		 
		//Reschedule Appointment
			
			@RequestMapping(value="/submitRescheduleAppointment", method = RequestMethod.POST)
			public ModelAndView submitRescheduleAppointment(@ModelAttribute("patientDetail") PatientDetails patientDetails,@RequestBody String data,HttpServletRequest request,Model model,
					HttpServletResponse response) {
				String visitId=request.getParameter("visitId");
				String tokenNumber=request.getParameter("tokenNumber");
				String appointmentDate=request.getParameter("appointmentDate");
				String serviceNo=request.getParameter("serviceNo");
				
				patientDetails.setVisitId(visitId);
				patientDetails.setTokenNumber(tokenNumber);
				patientDetails.setAppointmentDate(appointmentDate);
				//JSONObject json = new JSONObject(patientDetails);
				JSONObject json = new JSONObject();
				json.put("visitId", visitId);
				json.put("tokenNumber",tokenNumber);
				json.put("appointmentDate",appointmentDate);
				json.put("serviceNo", serviceNo);
				
				
				MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String,String>();
		
				String Url =HMSUtil.getProperties("urlextension.properties","RESCHEDULE_APPOINTMENT");
				String OSBURL = IpAndPortNo + Url;
				String responseData =RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders,json.toString());
		
			 //	String responseData = RestUtils.postWithHeaders("http://localhost:8082/AshaServices/appointment/reschedulePatientAppointment", requestHeaders, json.toString()); 
				JSONObject responsedata = new JSONObject(responseData);
				model.addAttribute("message",responsedata.get("message"));
				
				return new ModelAndView("appointmentHistory", "serviceNo", serviceNo);
			}
		
			
			//GET OPD HISTORY
			@RequestMapping(value="/getOpdHistory", method = {RequestMethod.POST,RequestMethod.GET})
			public ModelAndView getOpdHistory(HttpServletRequest request,Model model,HttpServletResponse response) {
				String serviceNo=request.getParameter("serviceNo");
				JSONObject json = new JSONObject();
				json.put("serviceNo", serviceNo);
				MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String,String>();
				 String Url =HMSUtil.getProperties("urlextension.properties","APPOINTMENT_HISTORY");
				 String OSBURL = IpAndPortNo + Url;
				
				 String opdHistoryList = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders,json.toString());
				
				//String opdHistoryList =RestUtils.postWithHeaders("http://localhost:8082/AshaServices/appointment/getAppointmentHistory", requestHeaders, json.toString()); 
				
				//String opdHistoryList =RestUtils.postWithHeaders("http://localhost:8082/AshaServices/appointment/getOpdHistory", requestHeaders, json.toString()); 
				model.addAttribute("serviceNo", serviceNo);
				return new ModelAndView("opdHistory", "opdHistoryList", opdHistoryList);
			}
			
			
			@RequestMapping(value = "/getAllOpdList", method = RequestMethod.POST)
			public String getAllOpdList(@RequestBody Map<String, Object> payload,
					HttpServletRequest request, HttpServletResponse response) {
				String responseObj = "";
				JSONObject json = new JSONObject(payload);
				MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
				
				 String Url =HMSUtil.getProperties("urlextension.properties","APPOINTMENT_HISTORY");
				 String OSBURL = IpAndPortNo + Url;
				
				 String opdHistoryList = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders,json.toString());
				//String opdHistoryList =RestUtils.postWithHeaders("http://localhost:8082/AshaServices/appointment/getAppointmentHistory", requestHeaders, json.toString()); 
				return opdHistoryList;
			}
			
			
			@RequestMapping(value = "/checkVisitOnSameDate", method = RequestMethod.POST)
			public String checkVisitOnSameDate(@RequestBody Map<String, Object> payload,
				HttpServletRequest request, HttpServletResponse response) {
				String responseObj = "";
				JSONObject json = new JSONObject(payload);
				MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
				
				 String Url =HMSUtil.getProperties("urlextension.properties","CHECK_VISIT_LIST");
				 String OSBURL = IpAndPortNo + Url;
				 
				 String status = RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders,json.toString());
		
				//String status =RestUtils.postWithHeaders("http://localhost:8082/AshaServices/appointment/checkVisitExist", requestHeaders, json.toString()); 
				return status;
			}
			
			
}



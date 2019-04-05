package com.asha.icgweb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.pool2.impl.AbandonedConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asha.icgweb.model.LoginModel;
import com.asha.icgweb.model.OpdModel;
import com.asha.icgweb.service.OpdService;
import com.asha.icgweb.utils.HMSUtil;
import com.asha.icgweb.utils.RestUtils;

@RequestMapping("/v0.1/opd")
@RestController
@CrossOrigin
public class OpdController  {
	
	@Autowired
	OpdService os;
	
	String ipAndPort = HMSUtil.getProperties("urlextension.properties", "OSB_IP_AND_PORT");
	
	@RequestMapping(value="/getPreConsPatientWatingWeb", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String preConsPatientWatingWeb(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.preConsPatientWatingWeb(payload, request, response);
	}
	
	@RequestMapping(value = "/preOpdWaitingList", method = RequestMethod.GET)
	public ModelAndView preOpdWaitingList(HttpServletRequest request,	HttpServletResponse response) {
		System.out.println("pre waiting called");
		String jsp = "opdPreConsulation";
		//String jsonResponse = os.saveOpdPatientdetails(payload, request, response);
		////System.out.println("jsonResponse "+jsonResponse);
		ModelAndView mv =new ModelAndView();
		//mv.addObject("data", jsonResponse);
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value = "/addVitalRecords", method = RequestMethod.GET)
	public ModelAndView addVitalRecord(HttpServletRequest request,	HttpServletResponse response) {
		System.out.println("add vitals called");
		String jsp = "addVitalPreConsultant";
		ModelAndView mv =new ModelAndView();
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value="/saveVitailsPatientdetails", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String saveVitailsPatientdetails(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.saveVitailsPatientdetails(payload, request, response);
	}
	
	@RequestMapping(value="/getIdealWeight", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String getIdealWeight(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.getIdealWeight(payload, request, response);
	}
	
	@RequestMapping(value="/getOpdWaitingList", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String getOpdWaitingList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.getOpdWaitingList(payload, request, response);
	}
	
/*	@RequestMapping(value="/getOpdPatientDetails", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String getOpdPatinetDetails(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.getOpdPatinetDetails(payload, request, response);
	}*/
	
	@RequestMapping(value = "/getOpdPatientModel", method = RequestMethod.GET)
	public ModelAndView getOpdPatientModel(HttpServletRequest request,	HttpServletResponse response) {
		System.out.println("called");
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "opdMain";
		int visitId= Integer.parseInt(request.getParameter("visitId"));
		String payload = "{\"visitId\":"+visitId+"}";
		String jsonResponse = os.getOpdPatientDetailModel(payload, request, response);
		System.out.println("jsonResponse "+jsonResponse);
		ModelAndView mv =new ModelAndView();
		mv.addObject("data", jsonResponse);
		mv.setViewName(jsp);
		return mv;
	}
	
	
	@RequestMapping(value="/getIcdList", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String getIcdList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.getIcdList(payload, request, response);
	}
	
	@RequestMapping(value = "/getFamilyPatinetHistory", method = RequestMethod.GET)
	public ModelAndView getFamilyPatientHistory(HttpServletRequest request,	HttpServletResponse response) {
		System.out.println("called");
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "temp_presentCompalint";
		
		int empid= Integer.parseInt(request.getParameter("employeeId"));
		String payload = "{\"employeeId\":"+empid+"}";
		String jsonResponse = os.getFamilyPatientHistory(payload, request, response);
		System.out.println("jsonResponse "+jsonResponse);
		ModelAndView mv =new ModelAndView();
		mv.addObject("data", jsonResponse);
		mv.setViewName(jsp);
		return mv;
	}
	
@RequestMapping(value="/saveOpdPatientdetails", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String saveOpdPatientdetails(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		System.out.println("data : " +payload );
		//Box box= HMSUtil.getBox(request);
		
		//JSONObject obj = new JSONObject(box);
		//System.out.println("jsonreq"+obj);
		
		
		return os.saveOpdPatientdetails(payload, request, response);
	}

	@RequestMapping(value = "/opdSubmit", method = RequestMethod.GET)
	public ModelAndView submitOpdPatientdetails(HttpServletRequest request,	HttpServletResponse response) {
		System.out.println("save called");
		String jsp = "opdSubmit";
		//String jsonResponse = os.saveOpdPatientdetails(payload, request, response);
		////System.out.println("jsonResponse "+jsonResponse);
		ModelAndView mv =new ModelAndView();
		//mv.addObject("data", jsonResponse);
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value = "/opdWaitingList", method = RequestMethod.GET)
	public ModelAndView opdWaitingList(HttpServletRequest request,	HttpServletResponse response) {
		System.out.println("waiting called");
		String jsp = "opdWaitingList";
		//String jsonResponse = os.saveOpdPatientdetails(payload, request, response);
		////System.out.println("jsonResponse "+jsonResponse);
		ModelAndView mv =new ModelAndView();
		//mv.addObject("data", jsonResponse);
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value = "/showCreateInvestigationTemplate", method = RequestMethod.GET)
	public ModelAndView showCreateInvestigationTemplate(HttpServletRequest request,	HttpServletResponse response) {
		System.out.println("called");
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "opdInvestigationTemplate";
		int empid= Integer.parseInt(request.getParameter("employeeId"));
		String payload = "{\"employeeId\":"+empid+"}";
		String jsonResponse = os.getFamilyPatientHistory(payload, request, response);
		System.out.println("jsonResponse "+jsonResponse);
		ModelAndView mv =new ModelAndView();
		mv.addObject("data", jsonResponse);
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value = "/showUpdateInvestigationTemplate", method = RequestMethod.GET)
	public ModelAndView updateCreateInvestigationTemplate(HttpServletRequest request,	HttpServletResponse response) {
		System.out.println("called");
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "updateInvestigationTemplate";
		int empid= Integer.parseInt(request.getParameter("employeeId"));
		String payload = "{\"employeeId\":"+empid+"}";
		String jsonResponse = os.getFamilyPatientHistory(payload, request, response);
		System.out.println("jsonResponse "+jsonResponse);
		ModelAndView mv =new ModelAndView();
		mv.addObject("data", jsonResponse);
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value="/saveOpdTemplates", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String saveOpdTemplates(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		System.out.println("data : " +payload );
		return os.saveOpdTemplates(payload, request, response);
	}
	/*@RequestMapping(value="/getFamilyPatinetHistory", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String getFamilyPatinetHistory(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.getFamilyPatientHistory(payload, request, response);
	}*/
	
	@RequestMapping(value="/getIinvestigationList", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String getIinvestigationList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.getIinvestigationList(payload, request, response);
	}
	
	@RequestMapping(value="/getMasDisposalList", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String getMasDisposalList(@RequestBody String payload,HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.getMasDisposalList(payload,request, response);
	}
	
	@RequestMapping(value = "/createTreatmentTemplate", method = RequestMethod.GET)
	public ModelAndView createTreatmentTemplate(HttpServletRequest request,	HttpServletResponse response) {
		System.out.println("called");
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "prescriptionTemplate";
		int empid= Integer.parseInt(request.getParameter("employeeId"));
		String payload = "{\"employeeId\":"+empid+"}";
		String jsonResponse = os.getFamilyPatientHistory(payload, request, response);
		System.out.println("jsonResponse "+jsonResponse);
		ModelAndView mv =new ModelAndView();
		mv.addObject("data", jsonResponse);
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value="/getMasStoreItemList", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String getMasStoreItemList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.getMasStoreItemList(payload, request, response);
	}
	
	@RequestMapping(value="/getMasFrequency", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String getMasFrequency(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.getMasFrequency(payload, request, response);
	}
	
	@RequestMapping(value="/getTemplateName", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String getTemplateName(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.getTemplateName(payload, request, response);
	}
	
	@RequestMapping(value="/getEmpanelledHospital", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String getEmpanelledHospital(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return os.getEmpanelledHospital(payload, request, response);
	}
	
	
	
	@RequestMapping(value="/show",method=RequestMethod.POST)
	public ResponseEntity<Object> showRegistrationJsp(@RequestBody String payload,HttpServletRequest request,HttpServletResponse httpServletResponse) {
   
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("departmentName", "GENERAL MEDICINE");
		map.put( "employeeName", "krishna" );
		map.put( "patientName", "Rahul" );
		map.put( "gender", "male" );
		map.put( "visitId", 2 );
		map.put( "patientId", 1234 );
		map.put( "tokenNo", 22 );
		map.put( "priority", 3);
		map.put( "doctorname", "Thakur" );
		map.put( "age", 30 );
		map.put( "status", "w" );
		//list.add(map)
		return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/obesityWaitingJsp", method = RequestMethod.GET)
	public ModelAndView obesityWaitingLJsp() {
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "obesityWaitingList";
		ModelAndView mv =new ModelAndView();
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value="/obesityWaitingList", method = RequestMethod.POST)
	public String obesityWaitingList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		System.out.println("aasha web called");
		String result = os.obesityWaitingList(payload);
		return result;
	}
	
	@RequestMapping(value="/patientObesityDetailjsp", method = RequestMethod.GET)
	public ModelAndView patientObesityDetailjsp( HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "patientObesityDetail";		
		int id= Integer.parseInt(request.getParameter("Id"));
		String payload = "{\"id\":"+id+"}";
		String jsonResponse = os.getObesityDetail(payload, request, response);		
		System.out.println("jsonResponse "+jsonResponse);
		ModelAndView mv =new ModelAndView();
		mv.addObject("data", jsonResponse);
		mv.setViewName(jsp);
		return mv;
	}
	
	/*
	 * @RequestMapping(value="/getIdealWeight", method =
	 * RequestMethod.POST,produces="application/json",consumes="application/json")
	 * public String getIdealWeight(@RequestBody String payload, HttpServletRequest
	 * request, HttpServletResponse response) { //String idealWeight =
	 * os.idealWeight(map, request, response); MultiValueMap<String,String>
	 * requestHeaders = new LinkedMultiValueMap<String, String>(); //String data =
	 * RestUtils.postWithHeaders(
	 * "http://localhost:8081/AshaServices/v0.1/opd/getIdealWeight", requestHeaders,
	 * payload); //osb link String data = RestUtils.postWithHeaders(
	 * "http://192.168.10.59:7003/GetIdealWeight/ProxyService/GetIdealWeight",
	 * requestHeaders, payload); System.out.println("data "+data); return data; }
	 */
	
	@RequestMapping(value="/saveObesityDetails", method = RequestMethod.POST,produces="application/json",consumes="application/json")
	public String saveObesityDetails(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		//String data =  RestUtils.postWithHeaders("http://localhost:8081/AshaServices/v0.1/opd/saveObesityDetails", requestHeaders, payload);
		String OsbURL = HMSUtil.getProperties("urlextension.properties", "saveObesityDetail");	
		String data =  RestUtils.postWithHeaders(ipAndPort.trim()+OsbURL.trim(), requestHeaders, payload);
		System.out.println("data "+data);
		return data;
	}
	
	@RequestMapping(value="/referralWaitingList", method = RequestMethod.GET)
	public ModelAndView referralWaitingJsp() {
		System.out.println("inside referralWaitingJsp");
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "referralWaitingList";
		ModelAndView mv =new ModelAndView();
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value="/referredPatientList", method = RequestMethod.POST)
	public String referredPatientList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {	
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String OsbURL = HMSUtil.getProperties("urlextension.properties", "referralWaitingList");	
		String data =  RestUtils.postWithHeaders(ipAndPort.trim()+OsbURL.trim(), requestHeaders, payload);		
		return data;
	}
	
	@RequestMapping(value="/referredPatientDetail", method = RequestMethod.GET)
	public ModelAndView referredPatientDetail( HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "referredPatientDetail";		
		int id= Integer.parseInt(request.getParameter("Id"));
		String payload = "{\"id\":"+id+"}";
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();		
		String OsbURL = HMSUtil.getProperties("urlextension.properties", "referralDetailList");	
		String jsonResponse =  RestUtils.postWithHeaders(ipAndPort.trim()+OsbURL.trim(), requestHeaders, payload);		
		ModelAndView mv =new ModelAndView();
		mv.addObject("data", jsonResponse);
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value="/updateReferralDetail", method = RequestMethod.POST)
	public String updateReferralDetail(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String OsbURL = HMSUtil.getProperties("urlextension.properties", "updateReferralDetail");	
		String data =  RestUtils.postWithHeaders(ipAndPort.trim()+OsbURL.trim(), requestHeaders, payload);		
		return data;
	}
	
	@RequestMapping(value="/admissionDischargePending", method = RequestMethod.GET)
	public ModelAndView getAdmissionDischargeList() {
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "admissionDischargePending";
		ModelAndView mv =new ModelAndView();
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value="/getAdmissionDischargeList", method = RequestMethod.POST)
	public String getAdmissionDischargeList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String OsbURL = HMSUtil.getProperties("urlextension.properties", "getAdmissionDischargeList");	
		String data =  RestUtils.postWithHeaders(ipAndPort.trim()+OsbURL.trim(), requestHeaders, payload);		
		return data;
	}
	
	@RequestMapping(value="/admissionAndDischarge", method = RequestMethod.GET)
	public ModelAndView admissionAndDischarge(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "admissionAndDischarge";
		String id = request.getParameter("id");
		String payload = "{\"id\":"+id+"}";
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String OsbURL = HMSUtil.getProperties("urlextension.properties", "admissionAndDischarge");	
		String data =  RestUtils.postWithHeaders(ipAndPort.trim()+OsbURL.trim(), requestHeaders, payload);
		System.out.println("data is "+data);
		ModelAndView mv =new ModelAndView();
		mv.addObject("data", data);
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value="/savePatientAdmission", method = RequestMethod.POST,produces="application/json",consumes="application/json")
	public String savePatientAdmission(@RequestBody String payload, HttpServletRequest request,HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String OsbURL = HMSUtil.getProperties("urlextension.properties", "savePatientAdmission");	
		String data =  RestUtils.postWithHeaders(ipAndPort.trim()+OsbURL.trim(), requestHeaders, payload);
		return data;		
	}
	
	@RequestMapping(value="/newAdmission", method = RequestMethod.GET)
	public ModelAndView newAdmission(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =new ModelAndView();
		String jsp = "newAdmission";
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value="/getServiceWisePatientList", method = RequestMethod.POST,produces="application/json",consumes="application/json")
	public String getServiceWisePatientList(@RequestBody String payload, HttpServletRequest request,HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String OsbURL = HMSUtil.getProperties("urlextension.properties", "getServiceWisePatientList");	
		String data =  RestUtils.postWithHeaders(ipAndPort.trim()+OsbURL.trim(), requestHeaders, payload);
		return data;
	}
	
	@RequestMapping(value="/saveNewAdmission", method = RequestMethod.POST,produces="application/json",consumes="application/json")
	public String saveNewAdmission(@RequestBody String payload, HttpServletRequest request,HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String OsbURL = HMSUtil.getProperties("urlextension.properties", "saveNewAdmission");	
		String data =  RestUtils.postWithHeaders(ipAndPort.trim()+OsbURL.trim(), requestHeaders, payload);
		return data;
	}
	
	@RequestMapping(value="/nursingCareWaitingJSP", method = RequestMethod.GET)
	public ModelAndView nursingCareWaitingJSP(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =new ModelAndView();
		String jsp = "nursingCareWaitingJSP";
		mv.setViewName(jsp);
		return mv;
	}
	
	@RequestMapping(value="/nursingCareWaitingList", method = RequestMethod.POST,produces="application/json",consumes="application/json")
	public String nursingCareWaitingList(@RequestBody String payload, HttpServletRequest request,HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String data =  RestUtils.postWithHeaders("http://192.168.203.172:8081/AshaServices/v0.1/opd/nursingCareWaitingList", requestHeaders, payload);
		return data;
	}
	
	@RequestMapping(value="/nursingCareDetail", method = RequestMethod.GET)
	public ModelAndView nursingCareDetail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String jsp = "nursingCareDetail";
		String id = request.getParameter("header_id");
		String payload = "{\"header_id\":"+id+"}";
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String data =  RestUtils.postWithHeaders("http://192.168.203.172:8081/AshaServices/v0.1/opd/getNursingCareDetail", requestHeaders, payload);
		mv.addObject("data", data);
		mv.setViewName(jsp);
		return mv;
		
	}
}
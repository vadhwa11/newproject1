package com.asha.icgweb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asha.icgweb.service.MasterService;
import com.asha.icgweb.utils.HMSUtil;
import com.asha.icgweb.utils.RestUtils;

/**
 * @version 1.0 Master Module
 * @author rajdeo.kumar
 *
 */
@RequestMapping("/v0.1/master")
@RestController
@CrossOrigin
public class MasterWebController {
	@Autowired
	MasterService masterService;
	
	String IpAndPortNo = HMSUtil.getProperties("urlextension.properties", "OSB_IP_AND_PORT");
	
	
	/**************************************Command Master**************************************************/
	/**
	 * @param CommandMaster
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="/masterModule", method=RequestMethod.GET)	
	public ModelAndView masterModules(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("masterModules calling...");
		ModelAndView mav = new ModelAndView("masterModule");			
		return mav;
			
	}
	
	@RequestMapping(value="/commandMaster", method=RequestMethod.GET)	
	public ModelAndView commandMaster(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("commandMaster calling...");
		ModelAndView mav = new ModelAndView("commandMaster");			
		return mav;
			
	}
	
	@RequestMapping(value="/getAllCommandDetails", method=RequestMethod.POST)	
	public String  getAllCommandDetails(@RequestBody String cmdPayload, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_ALL_COMMAND");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, cmdPayload);
	}
	
	@RequestMapping(value="/getCommandTypeList", method=RequestMethod.POST)
	public String getCommandTypeList(@RequestBody String payload, 
			HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_COMMAND_TYPE_LIST");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	
	@RequestMapping(value="/addCommand", method=RequestMethod.POST)
	public String addCommand(@RequestBody Map<String, Object> requestPayload, HttpServletRequest request,
			HttpServletResponse response) {			
		JSONObject jsonObject = new JSONObject(requestPayload);						
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_ADD_COMMAND");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	@RequestMapping(value="/updateCommandDetails", method=RequestMethod.POST)
	public String updateCommandDetails(@RequestBody String updateCmdPayload, HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(updateCmdPayload);	
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();		
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_UPDATE_COMMAND");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/updateCommandStatus", method=RequestMethod.POST)
	public String updateCommandStatus(@RequestBody String activeStatusCmdPayload, HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(activeStatusCmdPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();		
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_STATUS_COMMAND");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	/**************************************MAS UNIT**************************************************/
	/**
	 * @param Unit Master
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/unitMaster", method=RequestMethod.GET)
	public ModelAndView unitMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("unitMaster");		
		return mav;
	}
	
	@RequestMapping(value="/addUnit", method=RequestMethod.POST)
	public String addUnitMaster(@RequestBody Map<String, Object> payload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_ADD_UNIT");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/getCmdList", method=RequestMethod.POST)
	public String getCommandList(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_COMMAND_LIST");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/getUnitTypeList", method=RequestMethod.POST)
	public String getUnitTypeList(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_UNIT_TYPE_LIST");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	
	
	@RequestMapping(value="/getAllUnit", method=RequestMethod.POST)
	public String getAllUnit(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_ALL_UNIT");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/updateUnit", method=RequestMethod.POST)
	public String updateUnit(@RequestBody HashMap<String, Object> payload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_UPDATE_UNIT");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
		
	
	@RequestMapping(value="/updateUnitStatus", method=RequestMethod.POST)
	public String updateUnitStatus(@RequestBody String payload, 
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_UPDATE_UNIT_STATUS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	/***********************************IDEAL WEIGHT**************************************************/
	@RequestMapping(value="/idealWeightMaster", method=RequestMethod.GET)
	public ModelAndView idealWeightMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("idealWeightMaster");		
		return mav;
	}
	
	@RequestMapping(value="/getAgeList", method=RequestMethod.POST)
	public String getAgeList(@RequestBody Map<String, Object> idealWeight, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(idealWeight);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "getAge");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/addIdealWeightMaster", method=RequestMethod.POST)
	public String addIdealWeightMaster(@RequestBody Map<String, Object> idealWeight, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(idealWeight);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "addIdealWeightMaster");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/getAllIdealWeight", method=RequestMethod.POST)
	public String getAllIdealWeight(@RequestBody String empnelledHospital, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(empnelledHospital);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "getAllIdealWeight");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());		
	}
	
	@RequestMapping(value="/updateIdealWeight", method=RequestMethod.POST)
	public String updateIdealWeight(@RequestBody String empnelledHospital, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(empnelledHospital);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "updateIdealWeight");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());		
	}
	
	/******************************************MAS HOSPITAL*****************************************************************/
	/**
	 * @param Mas Hospital
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/hospitalMaster", method=RequestMethod.GET)
	public ModelAndView hospitalMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("hospitalMaster");		
		return mav;
	}
	
	@RequestMapping(value="/addHospital", method=RequestMethod.POST)
	public String addHospital(@RequestBody Map<String, Object> requestPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "ADD_MASHOSPITAL");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/getUnitNameList", method=RequestMethod.POST)
	public String getUnitNameList(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_UNIT_NAME_LIST");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	
	@RequestMapping(value="/getAllHospital", method=RequestMethod.POST)
	public String getAllHospital(@RequestBody String hospitalPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(hospitalPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_ALL_HOSPITAL");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	
	@RequestMapping(value="/updateMasHospitalDetails", method=RequestMethod.POST)
	public String updateMasHospitalDetails(@RequestBody String hospitalPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(hospitalPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "UPDATE_HOS_DETAILS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	@RequestMapping(value="/updateMasHospitalStatus", method=RequestMethod.POST)
	public String updateMasHospitalStatus(@RequestBody String hospitalPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(hospitalPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "UPDATE_HOS_STATUS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	
	/***************************************MAS RELATION*****************************************************/
	@RequestMapping(value="/relationMaster", method=RequestMethod.GET)
	public ModelAndView relationMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("relationMaster");		
		return mav;
	}
	
	@RequestMapping(value="/getAllRelation", method=RequestMethod.POST)
	public String getAllRelation(@RequestBody String relationPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(relationPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "getAllRelation");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	
	@RequestMapping(value="/updateRelationDetails", method=RequestMethod.POST)
	public String updateRelationDetails(@RequestBody String relationPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(relationPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "updateRelationDetails");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	@RequestMapping(value="/updateRelationStatus", method=RequestMethod.POST)
	public String updateRelationStatus(@RequestBody String relationPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(relationPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "updateRelationStatus");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	@RequestMapping(value="/addRelation", method=RequestMethod.POST)
	public String addRelation(@RequestBody Map<String, Object> requestPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "addRelation");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	/*********************************MAS DISPOSAL*************************************************/
	@RequestMapping(value="/disposalMaster", method=RequestMethod.GET)
	public ModelAndView disposalMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("disposalMaster");		
		return mav;
	}
	
	@RequestMapping(value="/addDisposal", method=RequestMethod.POST)
	public String addDisposal(@RequestBody Map<String, Object> disposalPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(disposalPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_ADD_DISPOSAL");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/getAllDisposal", method=RequestMethod.POST)
	public String getAllDisposal(@RequestBody String disposalPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(disposalPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_ALL_DISPOSAL");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	@RequestMapping(value="/updateDisposalDetails", method=RequestMethod.POST)
	public String updateDisposalDetails(@RequestBody String disposalPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(disposalPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_UPDATE_DISPOSAL_DETAILS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	@RequestMapping(value="/updateDisposalStatus", method=RequestMethod.POST)
	public String updateDisposalStatus(@RequestBody String disposalPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(disposalPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_UPDATE_DISPOSAL_STATUS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());		
	}
	/***************************MAS APPOINTMENT TYPE****************************************************/
	@RequestMapping(value="/appointmentTypeMaster", method=RequestMethod.GET)
	public ModelAndView appointmentTypeMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("appointmentTypeMaster");		
		return mav;
	}
	
	@RequestMapping(value="/addAppointmentType", method=RequestMethod.POST)
	public String addAppointmentType(@RequestBody Map<String, Object> appointmentPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(appointmentPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_ADD_APPOINTMENT_TYPE");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/getAllAppointmentType", method=RequestMethod.POST)
	public String getAllAppointmentType(@RequestBody String appointmentPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(appointmentPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_ALL_APPOINTMENT_TYPE");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	@RequestMapping(value="/updateAppointmentTypeDetails", method=RequestMethod.POST)
	public String updateAppointmentTypeDetails(@RequestBody String appointmentPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(appointmentPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_UPDATE_APPOINTMENT_TYPE_DETAILS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());		
	}
	
	@RequestMapping(value="/updateAppointmentTypeStatus", method=RequestMethod.POST)
	public String updateAppointmentTypeStatus(@RequestBody String appointmentPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(appointmentPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_UPDATE_APPOINTMENT_STATUS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	/*********************************MAS FREQUENCY******************************************************/
	@RequestMapping(value="/frequencyMaster", method=RequestMethod.GET)
	public ModelAndView frequencyMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("frequencyMaster");		
		return mav;
	}
	
	@RequestMapping(value="/addOpdFrequency", method=RequestMethod.POST)
	public String addOpdFrequency(@RequestBody Map<String, Object> appointmentPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(appointmentPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_ADD_OPD_FREQUENCY");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/getAllOpdFrequency", method=RequestMethod.POST)
	public String getAllOpdFrequency(@RequestBody String departTypePayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(departTypePayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_ALL_OPD_FREQUENCY");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	@RequestMapping(value="/updateOpdFrequencyDetails", method=RequestMethod.POST)
	public String updateOpdFrequencyDetails(@RequestBody String appointmentPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(appointmentPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_UPDATE_OPD_FREQUENCY_DETAILS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());		
	}
	
	@RequestMapping(value="/updateOpdFrequencyStatus", method=RequestMethod.POST)
	public String updateOpdFrequencyStatus(@RequestBody String appointmentPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(appointmentPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_UPDATE_OPD_FREQUENCY_STATUS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());		
	}
	
	/******************************** MAS Empanelled Hospital Master***************************************/
	@RequestMapping(value="/empanelledHospitalMaster", method=RequestMethod.GET)
	public ModelAndView empanelledHospitalMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("empanelledHospitalMaster");		
		return mav;
	}
	
	@RequestMapping(value="/addEmpanelledHospital", method=RequestMethod.POST)
	public String addEmpanelledHospital(@RequestBody Map<String, Object> empnelledHospital, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(empnelledHospital);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "addEmpanelledHospital");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/getAllEmpanelledHospital", method=RequestMethod.POST)
	public String getAllEmpanelledHospital(@RequestBody String empnelledHospital, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(empnelledHospital);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "getAllEmpanelledHospital");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());		
	}
	
	@RequestMapping(value="/updateEmpanelledHospital", method=RequestMethod.POST)
	public String updateEmpanelledHospital(@RequestBody String empnelledHospital, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(empnelledHospital);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "updateEmpanelledHospital");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	
	
	/************************************ MAS DEPARTMENT *******************************************************/
	@RequestMapping(value="/departmentMaster", method=RequestMethod.GET)
	public ModelAndView departmentMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("departmentMaster");		
		return mav;
	}
	
	@RequestMapping(value="/getDepartmentTypeList", method=RequestMethod.POST)
	public String getDepartmentTypeList(@RequestBody String departTypePayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(departTypePayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_DEPARTMENT_TYPE_LIST");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());		
	}
	
	@RequestMapping(value="/addDepartment", method=RequestMethod.POST)
	public String addDepartment(@RequestBody Map<String, Object> appointmentPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(appointmentPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_ADD_DEPARTMENT");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/getAllDepartment", method=RequestMethod.POST)
	public String getAllDepartment(@RequestBody String departTypePayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(departTypePayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_GET_ALL_DEPARTMENT");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());		
	}
	
	@RequestMapping(value="/updateDepartmentDetails", method=RequestMethod.POST)
	public String updateDepartmentDetails(@RequestBody String appointmentPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(appointmentPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_UPDATE_DEPARTMENT_DETAILS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	@RequestMapping(value="/updateDepartmentStatus", method=RequestMethod.POST)
	public String updateDepartmentStatus(@RequestBody String appointmentPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(appointmentPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "OSB_UPDATE_DEPARTMENT_STATUS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());		
	}
	
	/******************************************phsiotherapy TYPE MASTER*****************************************************************/
	/**
	 * @Author Anita
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/physiotherapyMaster", method=RequestMethod.GET)
	public ModelAndView phsiotherapyMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("phsiotherapyMaster");		
		return mav;
	}
	
	
	@RequestMapping(value="/getAllPhysiotherapy", method=RequestMethod.POST)
	public String getAllPhysiotherapy(@RequestBody String physiotherapyPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(physiotherapyPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "getAllPhysiotherapy");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	
	@RequestMapping(value="/addPhsiotherapy", method=RequestMethod.POST)
	public String addPhsiotherapy(@RequestBody Map<String, Object> requestPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "addPhsiotherapyCare");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/updatePhsiotherapyDetails", method=RequestMethod.POST)
	public String updatePhsiotherapyDetails(@RequestBody String masNursingCare, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(masNursingCare);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "updatePhysiotherapyDetails");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	/***************************************** ICD *********************************************************/
	
	@RequestMapping(value="/ICDMaster", method=RequestMethod.GET)
	public ModelAndView ICDMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("ICDMaster");		
		return mav;
	}
}

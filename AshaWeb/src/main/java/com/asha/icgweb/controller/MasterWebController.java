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
	String IpAndPortNoLocal = HMSUtil.getProperties("urlextension.properties", "LOCAL_IP_AND_PORT");
	
	
	/**************************************Command Master**************************************************/
	/**
	 * @param CommandMaster
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="/masterModule", method=RequestMethod.GET)	
	public ModelAndView masterModules(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("masterModule");			
		return mav;
			
	}
	
	@RequestMapping(value="/regionMaster", method=RequestMethod.GET)	
	public ModelAndView commandMaster(HttpServletRequest request, HttpServletResponse response) {
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
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_AGE");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/addIdealWeight", method=RequestMethod.POST)
	public String addIdealWeightMaster(@RequestBody Map<String, Object> idealWeight, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(idealWeight);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "addIdealWeight");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/getAllIdealWeight", method=RequestMethod.POST)
	public String getAllIdealWeight(@RequestBody String empnelledHospital, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(empnelledHospital);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_ALL_IDEAL_WEIGHT");
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
		String Url = HMSUtil.getProperties("urlextension.properties", "ADD_EMPPANELLED_HOSPITAL");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/getAllEmpanelledHospital", method=RequestMethod.POST)
	public String getAllEmpanelledHospital(@RequestBody String empnelledHospital, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(empnelledHospital);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_ALL_EMPANELLED_HOSPITAL");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());		
	}
	
	@RequestMapping(value="/updateEmpanelledHospital", method=RequestMethod.POST)
	public String updateEmpanelledHospital(@RequestBody String empnelledHospital, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(empnelledHospital);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "UPDATE_EMPANELLED_HOSPITAL");
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
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_ALL_PHYSIOTHERAPY");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	
	@RequestMapping(value="/addPhsiotherapy", method=RequestMethod.POST)
	public String addPhsiotherapy(@RequestBody Map<String, Object> requestPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "ADD_PHSIOTHERAPY_CARE");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/updatePhsiotherapyDetails", method=RequestMethod.POST)
	public String updatePhsiotherapyDetails(@RequestBody String masNursingCare, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(masNursingCare);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "UPDATE_PHSITHERAPY_DETAILS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	/***************************************** ICD *********************************************************/
	
	@RequestMapping(value="/ICDMaster", method=RequestMethod.GET)
	public ModelAndView ICDMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("ICDMaster");		
		return mav;
	}
	
/***************************************** SERVICE TYPE *********************************************************/
	
	@RequestMapping(value="/serviceTypeMaster", method=RequestMethod.GET)	
	public ModelAndView serviceTypeMaster(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("serviceTypeMaster calling...");
		ModelAndView mav = new ModelAndView("serviceTypeMaster");			
		return mav;
			
	}
	@RequestMapping(value="/getAllServiceTypeDetails", method=RequestMethod.POST)	
	public String  getAllServiceTypeDetails(@RequestBody String stPayload, HttpServletRequest request, HttpServletResponse response) {
		String serviceTypeDetails="";		
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "osb_getAllServiceType");	
		String OSBURL = IpAndPortNo + Url;
		serviceTypeDetails = RestUtils.postWithHeaders(OSBURL, requestHeaders, stPayload);		
		
		return serviceTypeDetails;
	}
	
	@RequestMapping(value="/updateServiceTypeDetails", method=RequestMethod.POST)
	public String updateServiceTypeDetails(@RequestBody String updateStPayload, HttpServletRequest request, HttpServletResponse response) {
		String responseObj="";
		String Url="";
		System.out.println("updateServiceTypeDetails");
		JSONObject jsonObject = new JSONObject(updateStPayload);	
		System.out.println("updateServiceTypeDetails :: "+jsonObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();		
		Url = HMSUtil.getProperties("urlextension.properties", "osb_updateServiceType");
		String OSBURL = IpAndPortNo + Url;
		responseObj = RestUtils.postWithHeaders(OSBURL, requestHeaders, jsonObject.toString());
		System.out.println("updateServiceType ::"+responseObj);
		return responseObj;
	}
	@RequestMapping(value="/addServiceType", method=RequestMethod.POST)
	public String addServiceType(@RequestBody Map<String, Object> requestPayload, HttpServletRequest request,
			HttpServletResponse response) {			
		JSONObject jsonObject = new JSONObject(requestPayload);						
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "osb_addServiceType");
		String OSBURL = IpAndPortNo + Url;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/updateServiceTypeStatus", method=RequestMethod.POST)
	public String updateServiceTypeStatus(@RequestBody String activeStatusCmdPayload, HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(activeStatusCmdPayload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();		
		String Url = HMSUtil.getProperties("urlextension.properties", "osb_statusServiceType");
		String OSBURL = IpAndPortNo + Url;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}	
	
	
	/**************************************
	 * Rank Master
	 **************************************************/
	/**
	 * @param RankMaster
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = "/rankMaster", method = RequestMethod.GET)
	public ModelAndView rankMaster(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("rankMaster calling...");
		ModelAndView mav = new ModelAndView("rankMaster");
		return mav;

	}
	
	@RequestMapping(value = "/getAllRankDetails", method = RequestMethod.POST)
	public String getAllRankDetails(@RequestBody String rankPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "osb_getRank");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, rankPayload);
	}
	
	@RequestMapping(value = "/addRank", method = RequestMethod.POST)
	public String addRank(@RequestBody Map<String, Object> requestPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(requestPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","osb_addRank");
		String OSBURL = IpAndPortNo + Url;		
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/updateRankDetails", method = RequestMethod.POST)
	public String updateRankDetails(@RequestBody HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "osb_updateRank");
		String OSBURL = IpAndPortNo + Url;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/updateRankStatus", method = RequestMethod.POST)
	public String updateRankStatus(@RequestBody String activeStatusRankPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "osb_statusRank");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, activeStatusRankPayload);
	}

	@RequestMapping(value = "/getEmployeeCategoryList", method = RequestMethod.POST)
	public String getEmployeeCategoryList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "osb_getEmployeeCategoryList");
		String OSBURL = IpAndPortNo + URL; 
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
	}
	/**************************************
	 * Trade Master
	 **************************************************/
	/**
	 * @param TradeMaster
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tradeMaster", method = RequestMethod.GET)
	public ModelAndView tradeMaster(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("tradeMaster calling...");
		ModelAndView mav = new ModelAndView("tradeMaster");
		return mav;

	}

	@RequestMapping(value = "/addTrade", method = RequestMethod.POST)
	public String addTrade(@RequestBody Map<String, Object> requestPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(requestPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addTrade");
		String OSBURL = IpAndPortNo + URL;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllTradeDetails", method = RequestMethod.POST)
	public String getAllTradeDetails(@RequestBody String tradePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllTrade");
		String OSBURL = IpAndPortNo + URL;
		return  RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, tradePayload);

	}

	@RequestMapping(value = "/updateTradeDetails", method = RequestMethod.POST)
	public String updateTradeDetails(@RequestBody HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateTrade");
		String OSBURL = IpAndPortNo + URL;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/updateTradeStatus", method = RequestMethod.POST)
	public String updateTradeStatus(@RequestBody String activeStatusTradePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "statusTrade");
		String OSBURL = IpAndPortNo + URL; 
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, activeStatusTradePayload);
	}

	@RequestMapping(value = "/getServiceTypeList", method = RequestMethod.POST)
	public String getServiceTypeList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getServiceTypeList");
		String OSBURL = IpAndPortNo + URL; 		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
	}

	/**************************************
	 * RELION Master
	 **************************************************/
	/**
	 * @param ReligionMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/religionMaster", method = RequestMethod.GET)
	public ModelAndView religionMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("religionMaster");
		return mav;
	}

	@RequestMapping(value = "/addReligion", method = RequestMethod.POST)
	public String addReligion(@RequestBody Map<String, Object> religionPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(religionPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addReligion");
		String OSBURL = IpAndPortNo + URL; 
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllReligion", method = RequestMethod.POST)
	public String getAllReligion(@RequestBody String religionPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllReligion");
		String OSBURL = IpAndPortNo + URL; 
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, religionPayload);
	}

	@RequestMapping(value = "/updateReligionDetails", method = RequestMethod.POST)
	public String updateReligionDetails(@RequestBody String religionPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(religionPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateReligionDetails");
		String OSBURL = IpAndPortNo + URL;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/updateReligionStatus", method = RequestMethod.POST)
	public String updateReligionStatus(@RequestBody String religionPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateReligionStatus");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, religionPayload);
	} 

	/**************************************
	 * MARITAL STATUS Master
	 **************************************************/
	/**
	 * @param MaritalStatusMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/maritalStatusMaster", method = RequestMethod.GET)
	public ModelAndView maritalStatusMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("maritalStatusMaster");
		return mav;
	}

	@RequestMapping(value = "/addMaritalStatus", method = RequestMethod.POST)
	public String addMaritalStatus(@RequestBody Map<String, Object> maritalStatusPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(maritalStatusPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addMaritalStatus");
		String OSBURL = IpAndPortNo + URL;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllMaritalStatus", method = RequestMethod.POST)
	public String getAllMaritalStatus(@RequestBody String maritalStatusPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllMaritalStatus");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, maritalStatusPayload);
	}

	@RequestMapping(value = "/updateMaritalStatusDetails", method = RequestMethod.POST)
	public String updateMaritalStatusDetails(@RequestBody String maritalStatusPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateMaritalStatusDetails");
		String OSBURL = IpAndPortNo + URL;
		return  RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, maritalStatusPayload);
		
	}

	@RequestMapping(value = "/updateMaritalStatusStatus", method = RequestMethod.POST)
	public String updateMaritalStatusStatus(@RequestBody String maritalStatusPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateMaritalStatusStatus");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, maritalStatusPayload);
		}

	/**************************************
	 * Employee Category Master
	 **************************************************/
	/**
	 * @param EmployeeCategoryMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/employeeCategoryMaster", method = RequestMethod.GET)
	public ModelAndView employeeCategoryMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("employeeCategoryMaster");
		return mav;
	}

	@RequestMapping(value = "/addEmployeeCategory", method = RequestMethod.POST)
	public String addEmployeeCategory(@RequestBody Map<String, Object> employeeCategoryPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(employeeCategoryPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addEmployeeCategory");
		String OSBURL = IpAndPortNo + URL;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllEmployeeCategory", method = RequestMethod.POST)
	public String getAllEmployeeCategory(@RequestBody String employeeCategoryPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllEmployeeCategory");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, employeeCategoryPayload);
	}

	@RequestMapping(value = "/updateEmployeeCategoryDetails", method = RequestMethod.POST)
	public String updateEmployeeCategoryDetails(@RequestBody String employeeCategoryPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateEmployeeCategoryDetails");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, employeeCategoryPayload);
	}

	@RequestMapping(value = "/updateEmployeeCategoryStatus", method = RequestMethod.POST)
	public String updateEmployeeCategoryStatus(@RequestBody String employeeCategoryPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateEmployeeCategoryStatus");
		String OSBURL = IpAndPortNo + URL;
		return  RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, employeeCategoryPayload);
	}

	/**************************************
	 * Administrative Sex Master
	 **************************************************/
	/**
	 * @param AdministrativeSexMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/genderMaster", method = RequestMethod.GET)
	public ModelAndView genderMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("genderMaster");
		return mav;
	}

	@RequestMapping(value = "/addAdministrativeSex", method = RequestMethod.POST)
	public String addAdministrativeSex(@RequestBody Map<String, Object> administrativeSexPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(administrativeSexPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addAdministrativeSex");
		String OSBURL = IpAndPortNo + URL;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllAdministrativeSex", method = RequestMethod.POST)
	public String getAllAdministrativeSex(@RequestBody String administrativeSexPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllAdministrativeSex");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, administrativeSexPayload);
	}

	@RequestMapping(value = "/updateAdministrativeSexDetails", method = RequestMethod.POST)
	public String updateAdministrativeSexDetails(@RequestBody String administrativeSexPayload,
			HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateAdministrativeSexDetails");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, administrativeSexPayload);
		
	}

	@RequestMapping(value = "/updateAdministrativeSexStatus", method = RequestMethod.POST)
	public String updateAdministrativeSexStatus(@RequestBody String administrativeSexPayload,
			HttpServletRequest request, HttpServletResponse response) {
		
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateAdministrativeSexStatus");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, administrativeSexPayload);
	}

	/**************************************
	 * Medical Category Master
	 **************************************************/
	/**
	 * @param MedicalCategoryMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/medicalCategoryMaster", method = RequestMethod.GET)
	public ModelAndView medicalCategoryMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("medicalCategoryMaster");
		return mav;
	}

	@RequestMapping(value = "/addMedicalCategory", method = RequestMethod.POST)
	public String addMedicalCategory(@RequestBody Map<String, Object> medicalCategoryPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(medicalCategoryPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addMedicalCategory");
		String OSBURL = IpAndPortNo + URL;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllMedicalCategory", method = RequestMethod.POST)
	public String getAllMedicalCategory(@RequestBody String medicalCategoryPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllMedicalCategory");
		String OSBURL = IpAndPortNo + URL;
		return  RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, medicalCategoryPayload);
	}

	@RequestMapping(value = "/updateMedicalCategoryDetails", method = RequestMethod.POST)
	public String updateMedicalCategoryDetails(@RequestBody String medicalCategoryPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateMedicalCategoryDetails");
		String OSBURL = IpAndPortNo + URL;	
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, medicalCategoryPayload);
	}

	@RequestMapping(value = "/updateMedicalCategoryStatus", method = RequestMethod.POST)
	public String updateMedicalCategoryStatus(@RequestBody String medicalCategoryPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateMedicalCategoryStatus");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, medicalCategoryPayload);
	}

	/**************************************
	 * Blood Group Master
	 **************************************************/
	/**
	 * @param BloodGroupMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/bloodGroupMaster", method = RequestMethod.GET)
	public ModelAndView bloodGroupMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("bloodGroupMaster");
		return mav;
	}

	@RequestMapping(value = "/addBloodGroup", method = RequestMethod.POST)
	public String addBloodGroup(@RequestBody Map<String, Object> bloodGroupPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(bloodGroupPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addBloodGroup");
		String OSBURL = IpAndPortNo + URL;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllBloodGroup", method = RequestMethod.POST)
	public String getAllBloodGroup(@RequestBody String bloodGroupPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllBloodGroup");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, bloodGroupPayload);
	}

	@RequestMapping(value = "/updateBloodGroupDetails", method = RequestMethod.POST)
	public String updateBloodGroupDetails(@RequestBody String bloodGroupPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateBloodGroupDetails");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, bloodGroupPayload);
	}

	@RequestMapping(value = "/updateBloodGroupStatus", method = RequestMethod.POST)
	public String updateBloodGroupStatus(@RequestBody String bloodGroupPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateBloodGroupStatus");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, bloodGroupPayload);
	}

	/**************************************
	 * Sample Container Master
	 **************************************************/
	/**
	 * @param SampleContainerMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/sampleContainerMaster", method = RequestMethod.GET)
	public ModelAndView sampleContainerMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("sampleContainerMaster");
		return mav;
	}

	@RequestMapping(value = "/addSample", method = RequestMethod.POST)
	public String addSample(@RequestBody Map<String, Object> samplePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(samplePayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addSample");
		String OSBURL = IpAndPortNo + URL;
		//String LOCALURL = IpAndPortNoLocal + URL;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllSample", method = RequestMethod.POST)
	public String getAllSample(@RequestBody String samplePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllSample");
		String OSBURL = IpAndPortNo + URL;
		//String LOCALURL = IpAndPortNoLocal + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, samplePayload);
	}

	@RequestMapping(value = "/updateSampleDetails", method = RequestMethod.POST)
	public String updateSampleDetails(@RequestBody String samplePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateSampleDetails");
		String OSBURL = IpAndPortNo + URL;
		//String LOCALURL = IpAndPortNoLocal + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, samplePayload);
	}

	@RequestMapping(value = "/updateSampleStatus", method = RequestMethod.POST)
	public String updateSampleStatus(@RequestBody String samplePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateSampleStatus");
		String OSBURL = IpAndPortNo + URL;
		//String LOCALURL = IpAndPortNoLocal + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, samplePayload);
	}

	/**************************************
	 * Unit of Measurement Master
	 **************************************************/
	/**
	 * @param UOMMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/uomMaster", method = RequestMethod.GET)
	public ModelAndView uomMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("uomMaster");
		return mav;
	}
	
	@RequestMapping(value = "/addUOM", method = RequestMethod.POST)
	public String addUOM(@RequestBody Map<String, Object> UOMPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(UOMPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addUOM");
		String OSBURL = IpAndPortNo + URL;
		//String LOCALURL = IpAndPortNoLocal + URL;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllUOM", method = RequestMethod.POST)
	public String getAllUOM(@RequestBody String UOMPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllUOM");
		String OSBURL = IpAndPortNo + URL;
		//String LOCALURL = IpAndPortNoLocal + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, UOMPayload);
	}

	@RequestMapping(value = "/updateUOMDetails", method = RequestMethod.POST)
	public String updateUOMDetails(@RequestBody String UOMPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateUOMDetails");
		String OSBURL = IpAndPortNo + URL;
		//String LOCALURL = IpAndPortNoLocal + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, UOMPayload);
	}

	@RequestMapping(value = "/updateUOMStatus", method = RequestMethod.POST)
	public String updateUOMStatus(@RequestBody String UOMPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateUOMStatus");
		String OSBURL = IpAndPortNo + URL;
		//String LOCALURL = IpAndPortNoLocal + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, UOMPayload);		
	}
	
	/**************************************
	 * Item Unit Master
	 **************************************************/
	/**
	 * @param itemUnitMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/itemUnitMaster", method = RequestMethod.GET)
	public ModelAndView itemUnitMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("itemUnitMaster");
		return mav;
	}
	
	@RequestMapping(value = "/addItemUnit", method = RequestMethod.POST)
	public String addItemUnit(@RequestBody Map<String, Object> itemUnitPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(itemUnitPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addItemUnit");
		String OSBURL = IpAndPortNo + URL;	
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllItemUnit", method = RequestMethod.POST)
	public String getAllItemUnit(@RequestBody String itemUnitPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllItemUnit");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, itemUnitPayload);
	}

	@RequestMapping(value = "/updateItemUnitDetails", method = RequestMethod.POST)
	public String updateItemUnitDetails(@RequestBody String itemUnitPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateItemUnitDetails");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, itemUnitPayload);
	}

	@RequestMapping(value = "/updateItemUnitStatus", method = RequestMethod.POST)
	public String updateItemUnitStatus(@RequestBody String itemUnitPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateItemUnitStatus");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, itemUnitPayload);		
	}
	
	/**************************************
	 * Users Master
	 **************************************************/
	/**
	 * @param usersMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/usersMaster", method = RequestMethod.GET)
	public ModelAndView usersMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("usersMaster");
		return mav;
	}
	
	@RequestMapping(value = "/addUsers", method = RequestMethod.POST)
	public String addUsers(@RequestBody Map<String, Object> usersPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(usersPayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addUsers");
		String OSBURL = IpAndPortNo + URL;	
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.POST)
	public String getAllUsers(@RequestBody String usersPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllUsers");
		String OSBURL = IpAndPortNo + URL;	
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, usersPayload);
	}

	@RequestMapping(value = "/updateUsersDetails", method = RequestMethod.POST)
	public String updateUsersDetails(@RequestBody String usersPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateUsersDetails");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, usersPayload);
	}

	@RequestMapping(value = "/updateUsersStatus", method = RequestMethod.POST)
	public String updateUsersStatus(@RequestBody String usersPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateUsersStatus");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, usersPayload);		
	}
	
	@RequestMapping(value = "/getHospitalList", method = RequestMethod.POST)
	public String getHospitalList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getHospitalList");
		String OSBURL = IpAndPortNo + URL; 
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
	}
	
	/**************************************
	 * MainChargecode Master
	 **************************************************/
	/**
	 * @param mainChargecodeMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/mainChargeCodeMaster", method = RequestMethod.GET)
	public ModelAndView mainChargeCodeMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("mainChargeCodeMaster");
		return mav;
	}
	
	@RequestMapping(value = "/addMainChargecode", method = RequestMethod.POST)
	public String addMainChargecode(@RequestBody Map<String, Object> mainChargecodePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(mainChargecodePayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addMainChargecode");
		String OSBURL = IpAndPortNo + URL;	
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllMainChargecode", method = RequestMethod.POST)
	public String getAllMainChargecode(@RequestBody String mainChargecodePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllMainChargecode");
		String OSBURL = IpAndPortNo + URL;	
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, mainChargecodePayload);
	}

	@RequestMapping(value = "/updateMainChargecodeDetails", method = RequestMethod.POST)
	public String updateMainChargecodeDetails(@RequestBody String mainChargecodePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateMainChargecodeDetails");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, mainChargecodePayload);
	}

	@RequestMapping(value = "/updateMainChargecodeStatus", method = RequestMethod.POST)
	public String updateMainChargecodeStatus(@RequestBody String mainChargecodePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateMainChargecodeStatus");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, mainChargecodePayload);		
	}
	
	@RequestMapping(value = "/getDepartmentList", method = RequestMethod.POST)
	public String getDepartmentList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getDepartmentList");
		String OSBURL = IpAndPortNo + URL; 
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
	}
	
	/**************************************
	 * Role Master
	 **************************************************/
	/**
	 * @param RoleMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/roleMaster", method = RequestMethod.GET)
	public ModelAndView roleMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("roleMaster");
		return mav;
	}

	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public String addRole(@RequestBody Map<String, Object> rolePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(rolePayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addRole");
		String OSBURL = IpAndPortNo + URL;
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllRole", method = RequestMethod.POST)
	public String getAllRole(@RequestBody String rolePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllRole");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, rolePayload);
	}

	@RequestMapping(value = "/updateRoleDetails", method = RequestMethod.POST)
	public String updateRoleDetails(@RequestBody String rolePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateRoleDetails");
		String OSBURL = IpAndPortNo + URL;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, rolePayload);
	}

	@RequestMapping(value = "/updateRoleStatus", method = RequestMethod.POST)
	public String updateRoleStatus(@RequestBody String rolePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateRoleStatus");
		String OSBURL = IpAndPortNo + URL;
		return  RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, rolePayload);
	}
	/**************************************
	 * Range Master
	 **************************************************/
	/**
	 * @param rangeMaster
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/rangeMaster", method = RequestMethod.GET)
	public ModelAndView rangeMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("rangeMaster");
		return mav;
	}
	
	@RequestMapping(value = "/addRange", method = RequestMethod.POST)
	public String addRange(@RequestBody Map<String, Object> rangePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String responseObj = "";
		JSONObject jsonObject = new JSONObject(rangePayload);
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "addRange");
		String OSBURL = IpAndPortNo + URL;	
		responseObj = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObj;
	}

	@RequestMapping(value = "/getAllRange", method = RequestMethod.POST)
	public String getAllRange(@RequestBody String rangePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getAllRange");
		String OSBURL = IpAndPortNo + URL;	
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, rangePayload);
	}

	@RequestMapping(value = "/updateRangeDetails", method = RequestMethod.POST)
	public String updateRangeDetails(@RequestBody String rangePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateRangeDetails");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, rangePayload);
	}

	@RequestMapping(value = "/updateRangeStatus", method = RequestMethod.POST)
	public String updateRangeStatus(@RequestBody String rangePayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "updateRangeStatus");
		String OSBURL = IpAndPortNo + URL;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, rangePayload);		
	}
	
	@RequestMapping(value = "/getGenderList", method = RequestMethod.POST)
	public String getGenderList(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getGenderList");
		String OSBURL = IpAndPortNo + URL; 
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
	}
	
	@RequestMapping(value="/getMasRange", method=RequestMethod.POST)
	public String getMasRange(@RequestBody String rangePayload, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String URL = HMSUtil.getProperties("urlextension.properties", "getMasRange");
		String OSBURL = IpAndPortNo + URL;
		return  RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, rangePayload);
	}
}

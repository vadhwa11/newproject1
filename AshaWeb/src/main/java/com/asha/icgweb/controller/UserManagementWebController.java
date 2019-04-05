package com.asha.icgweb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asha.icgweb.utils.HMSUtil;
import com.asha.icgweb.utils.RestUtils;
/**
 * 
 * @author rajdeo.kumar
 *
 */
@RequestMapping("/")
@RestController
@CrossOrigin
public class UserManagementWebController {

	/**
	 * @Page manageUserApplication.jsp
	 * @param request
	 * @param response
	 * @return
	 * @param manageUserApplication method will load the User Application Page.
	 */
	
	String IpAndPortNo = HMSUtil.getProperties("urlextension.properties", "OSB_IP_AND_PORT");
	String LOCAL_IPAndPortNo = HMSUtil.getProperties("urlextension.properties", "LOCAL_IP");
	
	@RequestMapping(value="/manageUserApplication", method=RequestMethod.GET)	
	public ModelAndView manageUserApplication(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("manageUserApplication");			
		return mav;			
	}
	@RequestMapping(value="/getAllUserApplication", method=RequestMethod.POST)
	public String getAllUserApplication(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_ALL_USER_APPLICATION");
		String OSBURL = LOCAL_IPAndPortNo+Url;
		String responseObject = RestUtils.postWithHeaders(OSBURL, requestHeaders, jsonObject.toString());
		return responseObject;
	}
	
	
	@RequestMapping(value="/updateUserApplication", method=RequestMethod.POST)
	public String updateUserApplication(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "UPDATE_USER_APPLICATION");
		String OSBURL = LOCAL_IPAndPortNo+Url;
		String responseObject = RestUtils.postWithHeaders(OSBURL, requestHeaders, jsonObject.toString());
		return responseObject;
	}
	
	/**
	 * @Page manageTemplate.jsp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageTemplate", method=RequestMethod.GET)	
	public ModelAndView manageTemplate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("manageTemplate");			
		return mav;			
	}
	
	@RequestMapping(value="/getAllTemplate", method=RequestMethod.POST)
	public String getAllTemplate(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_ALL_TEMPLATE");
		String OSBURL = LOCAL_IPAndPortNo+Url;
		String responseObject = RestUtils.postWithHeaders(OSBURL, requestHeaders, jsonObject.toString());
		return responseObject;
	}
	
	/**
	 * @Page assignApplicationToTemplate.jsp
	 * @param request
	 * @param response
	 * @return
	 * @method assignApplicationToTemplate 'load the AssignApplicationToTemplate' Page
	 */
	@RequestMapping(value="/assignApplicationToTemplate", method=RequestMethod.GET)	
	public ModelAndView assignApplicationToTemplate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("assignApplicationToTemplate");			
		return mav;			
	}
	
	@RequestMapping(value="/manageRole", method=RequestMethod.GET)	
	public ModelAndView manageRole(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("manageRole");			
		return mav;			
	}
	
	
	@RequestMapping(value="/addFormsAndReports", method=RequestMethod.GET)	
	public ModelAndView addFormsAndReports(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("addFormsAndReports");			
		return mav;			
	}
	
	@RequestMapping(value="/getApplicationAutoComplete", method=RequestMethod.POST)	
	public String getApplicationAutoComplete(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_APPLICATION_AUTO_COMPLETE");
		String OSBURL = LOCAL_IPAndPortNo+Url;
		return RestUtils.postWithHeaders(OSBURL, requestHeaders, jsonObject.toString());			
	}
}
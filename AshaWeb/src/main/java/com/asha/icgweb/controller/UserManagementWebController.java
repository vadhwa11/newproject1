package com.asha.icgweb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
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
@RequestMapping("/user")
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
		String OSBURL = IpAndPortNo+Url;
		String responseObject = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObject;
	}
	
	
	@RequestMapping(value="/updateUserApplication", method=RequestMethod.POST)
	public String updateUserApplication(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "UPDATE_USER_APPLICATION");
		String OSBURL = IpAndPortNo+Url;
		String responseObject = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObject;
	}
	
	@RequestMapping(value="/addUserApplication", method=RequestMethod.POST)
	public String addUserApplication(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "ADD_USER_APPLICATION");
		String OSBURL = IpAndPortNo+Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
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
	@RequestMapping(value="/addTemplate", method=RequestMethod.POST)
	public String addTemplate(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "ADD_TEMPLATE");
		String OSBURL = IpAndPortNo+Url;
		String responseObject = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObject;
	}
	
	@RequestMapping(value="/getAllTemplate", method=RequestMethod.POST)
	public String getAllTemplate(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_ALL_TEMPLATE");
		String OSBURL = IpAndPortNo+Url;
		String responseObject = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObject;
	}
	
	@RequestMapping(value="/getTemplateList", method=RequestMethod.POST)
	public String getTemplateList(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_TEMPLATE_LIST");
		String OSBURL = IpAndPortNo+Url;
		String responseObject = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObject;
	}
	
	@RequestMapping(value="/getModuleNameTemplateWise", method=RequestMethod.POST)
	public String getModuleNameTemplateWise(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_MODULE_NAME_TEMPLATE_WISE");
		String OSBURL = IpAndPortNo+Url;
		String responseObject = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		return responseObject;
	}
	
	@RequestMapping(value="/updateTemplate", method=RequestMethod.POST)
	public String updateTemplate(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "UPDATE_TEMPLATE");
		String OSBURL = IpAndPortNo+Url;
		String responseObject = RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
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
		String OSBURL = IpAndPortNo+Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());			
	}
	
	@RequestMapping(value="/addFormAndReports", method=RequestMethod.POST)	
	public String addFormAndReports(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "ADD_FORM_AND_REPORTS");
		String OSBURL = IpAndPortNo+Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());			
	}
	
	@RequestMapping(value="/getAllApplicationAndTemplates", method=RequestMethod.POST)
	public String getAllApplicationAndTemplates(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_ALL_APPLICATION_AND_TEMPLATES");
		String OSBURL = IpAndPortNo+Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	
	@RequestMapping(value="/addTemplateApplication", method=RequestMethod.POST)
	public String addTemplateApplication(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "ADD_TEMPLATE_APPLICATION");
		String OSBURL = IpAndPortNo+Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	@RequestMapping(value="/roleRights", method=RequestMethod.GET)	
	public ModelAndView croleRights(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("roleRights");			
		return mav;
			
	}
	
	@RequestMapping(value="/getRoleRightsList", method=RequestMethod.POST)
	public String getRoleRightsList(@RequestBody String payload, 
			HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_ROLE_RIGHTS_LIST");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	
	@RequestMapping(value="/getTemplateNameList", method=RequestMethod.POST)
	public String getTemplateNameList(@RequestBody String payload, 
			HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_TEMPLATE_NAME_LIST");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}	
	
	@RequestMapping(value="/getAssingedTemplateNameList", method=RequestMethod.POST)
	public String getAssingedTemplateNameList(@RequestBody String payload, 
			HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_ASSINGEED_TEMPLATE_NAME_LIST");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}
	
	@RequestMapping(value="/saveRolesRight", method=RequestMethod.POST)
	public String saveRolesRight(@RequestBody Map<String, Object> requestPayload, HttpServletRequest request,
			HttpServletResponse response) {			
		JSONObject jsonObject = new JSONObject(requestPayload);						
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "SAVE_ROLES_RIGHT");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
	}
	
	@RequestMapping(value="/editFormsAndReport",method=RequestMethod.POST)
	public ModelAndView editFormsAndReport(HttpServletRequest request, HttpServletResponse response) {	
		ModelAndView mav = new ModelAndView("editFormsAndReport");			
		return mav;
	}
	@RequestMapping(value="/getApplicationNameFormsAndReport", method=RequestMethod.POST)
	public String getApplicationNameFormsAndReport(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_APPLICATIONNAME_FORMS_AND_REPORT");
		String OSBURL = IpAndPortNo+Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	@RequestMapping(value="/updateAddFormsAndReport", method=RequestMethod.POST)
	public String updateAddFormsAndReport(@RequestBody Map<String, Object> requestObject) {
		JSONObject jsonObject = new JSONObject(requestObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "UPDATE_ADD_FORM_AND_REPORT");
		String OSBURL = IpAndPortNo+Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());	
	}
	
	
	
	@RequestMapping(value="/showApplicationsOnRoleBase", method=RequestMethod.POST)	
	public String showApplicationsOnRoleBase(@RequestBody String requestObject,HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		HttpSession session = request.getSession();
		String Url = HMSUtil.getProperties("urlextension.properties", "GET_APPLICATION_BASED_ON_ROLE");
		String OSBURL = IpAndPortNo+Url;
		String resp= RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, requestObject);	
		if(resp!=null && !resp.isEmpty()) {
			JSONObject json = new JSONObject(resp);
			/* HttpSession session = request.getSession(); */
			JSONArray arr = json.getJSONArray(("respUserList"));
			String status = json.get("status").toString();
			if(!status.equalsIgnoreCase("0") && arr.length()>0){
				JSONObject obj = (JSONObject) arr.get(0);
				 session.setAttribute("user_id",obj.get("userId"));
			     session.setAttribute("hospital_id",obj.get("hospitalId"));
			     System.out.println(obj.get("hospitalId"));
			}else {
				return resp;
			}
		}
		System.out.println(session.getAttribute("hospital_id"));
		return resp;
	}
	
	
}

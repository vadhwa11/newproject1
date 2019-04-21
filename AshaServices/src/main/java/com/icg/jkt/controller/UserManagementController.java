package com.icg.jkt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.icg.jkt.service.UserManagementService;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserManagementController {

	@Autowired
	UserManagementService userManagementService;

	@RequestMapping(value = "/getAllUserApplication", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllUserApplication(@RequestBody Map<String, Object> requestMapObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestMapObject);
		return userManagementService.getAllUserApplication(jsonObject, request, response);
	}

	@RequestMapping(value = "/updateUserApplication", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String updateUserApplication(@RequestBody Map<String, Object> requestMapObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestMapObject);
		return userManagementService.updateUserApplication(jsonObject, request, response);
	}

	@RequestMapping(value = "/addUserApplication", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String addUserApplication(@RequestBody Map<String, Object> requestMapObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestMapObject);
		return userManagementService.addUserApplication(jsonObject, request, response);
	}

	@RequestMapping(value = "/addTemplate", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addTemplate(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.addTemplate(jsonObject, request, response);
	}

	@RequestMapping(value = "/getAllTemplate", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllTemplate(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.getAllTemplate(jsonObject, request, response);
	}

	@RequestMapping(value = "/getTemplateList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getTemplateList(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.getTemplateList(jsonObject, request, response);
	}

	@RequestMapping(value = "/getModuleNameTemplateWise", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getModuleNameTemplateWise(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.getModuleNameTemplateWise(jsonObject, request, response);
	}

	@RequestMapping(value = "/getApplicationListForTemplate", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getApplicationListForTemplate(@RequestBody Map<String, Object> requestObject,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.getApplicationListForTemplate(jsonObject, request, response);
	}

	@RequestMapping(value="/getAllApplicationAndTemplates", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllApplicationAndTemplates(@RequestBody Map<String, Object> requestObject,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.getAllApplicationAndTemplates(jsonObject, request, response);
	}
	
	@RequestMapping(value = "/updateTemplate", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateTemplate(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.updateTemplate(jsonObject, request, response);
	}

	@RequestMapping(value = "/getApplicationAutoComplete", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getApplicationAutoComplete(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.getApplicationAutoComplete(jsonObject, request, response);
	}
	
	@RequestMapping(value ="/addFormAndReports", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public String addFormAndReports(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.addFormAndReports(jsonObject, request, response);
	}
	
	@RequestMapping(value="/addTemplateApplication", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public String addTemplateApplication(@RequestBody Map<String, Object> requestObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.addTemplateApplication(jsonObject, request, response);
	}
	
	/*************************************
	 * Role Rights
	 ***************************************************************/
	/**
	 * 
	 * @param Roles Right
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = "/getRoleRightsList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getRoleRightList(HttpServletRequest request, HttpServletResponse response) {
		String roleList = "";
		roleList = userManagementService.getRoleRightsList(request, response);
		return roleList;
	}
	
	@RequestMapping(value = "/getTemplateNameList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getTemplateNameList(HttpServletRequest request, HttpServletResponse response) {
		String tempNameList = "";
		tempNameList = userManagementService.getTemplateNameList(request, response);
		return tempNameList;
	}
	
	
	@RequestMapping(value = "/getAssingedTemplateNameList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAssingedTemplateNameList(@RequestBody Map<String, Object> roleId, HttpServletRequest request, HttpServletResponse response) {
		String tempAssignNameList = "";
		JSONObject json = new JSONObject(roleId);
		tempAssignNameList = userManagementService.getAssingedTemplateNameList(json,request, response);
		System.out.println();
		return tempAssignNameList;
	}
	

	@RequestMapping(value = "/saveRolesRight", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String saveRolesRight(@RequestBody Map<String, Object> command, HttpServletRequest request,
			HttpServletResponse response) {
		String saveRole = "";
		JSONObject json = new JSONObject(command);
		saveRole = userManagementService.saveRolesRight(json, request, response);
		return saveRole;
	}
	
	@RequestMapping(value="/getApplicationNameFormsAndReport", method=RequestMethod.POST,produces="application/json", consumes="application/json")
	public String getApplicationNameFormsAndReport(@RequestBody Map<String, Object> requestObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.getApplicationNameFormsAndReport(jsonObject, request, response);
	}
	
	@RequestMapping(value="/updateAddFormsAndReport", method=RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes={ MediaType.APPLICATION_JSON_VALUE})
	public String updateAddFormsAndReport(@RequestBody Map<String, Object> requestObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.updateAddFormsAndReport(jsonObject, request, response);
	}
}

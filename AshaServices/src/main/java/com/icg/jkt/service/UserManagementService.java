package com.icg.jkt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface UserManagementService {

	String getAllUserApplication(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	//String userApplication(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String updateUserApplication(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String getAllTemplate(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String getApplicationAutoComplete(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String addUserApplication(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String updateTemplate(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String addTemplate(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String getTemplateList(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String getModuleNameTemplateWise(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String getApplicationListForTemplate(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String addFormAndReports(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String getAllApplicationAndTemplates(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String addTemplateApplication(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
}

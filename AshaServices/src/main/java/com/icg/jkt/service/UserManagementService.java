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
}
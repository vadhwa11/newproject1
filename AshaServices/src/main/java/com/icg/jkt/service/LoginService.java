package com.icg.jkt.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginService {

	String empRegistration(HashMap<String, Object> jsondata, HttpServletRequest request, HttpServletResponse response);

	String registration(JSONObject json, HttpServletRequest request, HttpServletResponse response);

	String getRegistration(HttpServletRequest request, HttpServletResponse response);

	String loginEmployee(HashMap<String, Object> payload, HttpServletRequest request, HttpServletResponse response);

	String registrationMultiple(HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response);

}

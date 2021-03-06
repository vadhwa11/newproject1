package com.asha.icgweb.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;

import com.asha.icgweb.entity.Registration;
import com.asha.icgweb.model.LoginModel;

@Repository
public interface LoginService {

	String empRegistration(HashMap<String, Object> jsondata, HttpServletRequest request, HttpServletResponse response);

	//String registration(Registration payload, HttpServletRequest request, HttpServletResponse response);

	String getRegistration(HttpServletRequest request, HttpServletResponse response);

	String registration(LoginModel payload, HttpServletRequest request, HttpServletResponse response);

	List<Registration> getallRegistration(HttpServletRequest httpServletRequest);

	String executeDbProcedure(String payload, HttpServletRequest request, HttpServletResponse response);

	String executeDbProcedureforStatistics(String payload, HttpServletRequest request, HttpServletResponse response);
	String executeProcedureForDashBoard(String payload, HttpServletRequest request, HttpServletResponse response);

}

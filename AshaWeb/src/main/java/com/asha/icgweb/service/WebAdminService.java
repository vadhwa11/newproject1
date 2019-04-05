package com.asha.icgweb.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public interface WebAdminService {
	
	public String getDepartmentList(String data, HttpServletRequest request, HttpServletResponse response);
	
	public String getDoctorRoasterDetail(String data, HttpServletRequest request, HttpServletResponse response);
	
	public String saveDoctorRoaster(String data, HttpServletRequest request, HttpServletResponse response);
}

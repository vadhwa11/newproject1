package com.icg.jkt.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface AdminService {
	
	public String getDepartmentList(HashMap<String, Object> map);
	
	public String getDoctorList(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response);	
	
	public String getDoctorRoaster(HashMap<String, Object> jsondata);	
	public String submitDepartmentRoaster(HashMap<String, Object> jsondata);
		
	
}

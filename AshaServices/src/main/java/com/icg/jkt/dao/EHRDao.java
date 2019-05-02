package com.icg.jkt.dao;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EHRDao {
	
	public Map<String, Object>  getPatientSummary(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response); 

}

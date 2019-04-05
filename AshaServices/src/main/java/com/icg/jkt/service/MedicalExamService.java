package com.icg.jkt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface MedicalExamService {
	
	String getMedicalExamDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);

}

package com.icg.jkt.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icg.jkt.dao.MedicalExamDao;
import com.icg.jkt.service.MedicalExamService;

@Service("MedicalExamService")
public class MedicalExamServiceImpl implements MedicalExamService{
	
	@Autowired
	MedicalExamDao medicalExamDao; 
	
	@Override
	public String getMedicalExamDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		
		medicalExamDao.getMedicalExamDetails(jsonObject);
		
		return json.toString();
	}

}

package com.icg.jkt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icg.jkt.service.MedicalExamService;

@RequestMapping("/medExam")
@RestController
@CrossOrigin
public class MedicalExamController {

	@Autowired
	MedicalExamService medicalExamService;
	
	public String getMedicalExamDetails(@RequestBody Map<String, Object> medExamRequest, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(medExamRequest);
		return medicalExamService.getMedicalExamDetails(jsonObject,request,response);
	}
}

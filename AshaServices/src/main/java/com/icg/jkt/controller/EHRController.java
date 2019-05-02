package com.icg.jkt.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.icg.jkt.service.EHRService;


public class EHRController {
	
	@Autowired
	EHRService ehrService;
	
	@RequestMapping(value="getPatientSummary", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public String getPatientSummary(@RequestBody HashMap<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		
		return ehrService.getPatientSummary(map,request,response);
	}
}

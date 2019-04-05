package com.icg.jkt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icg.jkt.service.UserManagementService;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserManagementController {
	
	@Autowired
	UserManagementService userManagementService;
	
	@RequestMapping(value="/getAllUserApplication", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public String getAllUserApplication(@RequestBody Map<String, Object> requestMapObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestMapObject);
		return userManagementService.getAllUserApplication(jsonObject,request,response);
	}
	
	@RequestMapping(value="/updateUserApplication", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public String updateUserApplication(@RequestBody Map<String, Object> requestMapObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestMapObject);
		System.out.println("jsonObject :: "+jsonObject);
		return userManagementService.updateUserApplication(jsonObject, request, response);
	}
	
	@RequestMapping(value="/getAllTemplate", method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public String getAllTemplate(@RequestBody Map<String, Object> requestObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.getAllTemplate(jsonObject,request,response);
	}
		
	@RequestMapping(value="/getApplicationAutoComplete", method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public String getApplicationAutoComplete(@RequestBody Map<String, Object> requestObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestObject);
		return userManagementService.getApplicationAutoComplete(jsonObject, request, response);
	}
}

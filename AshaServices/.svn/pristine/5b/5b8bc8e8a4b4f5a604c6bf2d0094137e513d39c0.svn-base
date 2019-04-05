package com.icg.jkt.controller;

/** Copyright 2019 JK Technosoft Ltd. All rights reserved.
* Use is subject to license terms.
* Purpose of the HMS -  This is for login.
* @author  Krishna Thakur
* Create Date: 08/01/2019 
* @version 0.1
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.icg.jkt.service.LoginService;

@RequestMapping("/v0.1/dashboard")
@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	LoginService ls;
	
	
	@RequestMapping(value="/empRegistration", method = RequestMethod.POST)
	public String empRegistration(@RequestBody HashMap<String,Object> payload,HttpServletRequest request,HttpServletResponse response)
	{
	   return "hello jkt";
		//return ls.empRegistration(payload, request, response);
		
	}
	
	@RequestMapping(value="/addEmp", method = RequestMethod.POST,produces="application/json",consumes="application/json")
	public String registration(@RequestBody HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject(payload);
		
		 String ipAddress = request.getHeader("X-FORWARDED-FOR");  
	       if (ipAddress == null) {  
	         ipAddress = request.getRemoteHost(); 
	   }
		System.out.println(ipAddress);
		
		return ls.registration(json, request, response);
	}
	
	
	@RequestMapping(value="/addEmpMultiple", method = RequestMethod.POST,produces="application/json",consumes="application/json")
	public String registrationMultiple(@RequestBody HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		return ls.registrationMultiple(payload, request, response);
	}
	
	
	
	@RequestMapping(value="/loginEmp", method = RequestMethod.POST,produces="application/json",consumes="application/json")
	public String loginEmployee(@RequestBody HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		return ls.loginEmployee(payload, request, response);
	}
	
	@RequestMapping(value="/getEmpList", method = RequestMethod.GET)
	public String getRegistration(HttpServletRequest request,
			HttpServletResponse response) {
		return ls.getRegistration(request, response);
	}
	
	@RequestMapping(value = "/userhome", method = RequestMethod.POST)
	public ModelAndView customerhome(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		ModelAndView modelAndView = null;
		if (session != null && session.isNew())
			session.invalidate();
		session = request.getSession(true);

		if (session == null || session.isNew()) {
			if (request.getParameter("user-id").equals("admin") && request.getParameter("user-pass").equals("admin")) {
				modelAndView = new ModelAndView("Customer_PO");
			} else {
				modelAndView = new ModelAndView("hello1");
			}
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/show",method=RequestMethod.POST)
	public ResponseEntity<Object> showRegistrationJsp(@RequestBody String payload,HttpServletRequest request,HttpServletResponse httpServletResponse) {
     // List<Object> list= new ArrayList<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		//int hospitalId = (Integer) session.getAttribute("");
		map.put("name", "krishna thakur");
		map.put( "age", 29 );
		map.put( "city", "Paschim Vihar" );
		//list.add(map)
		return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
	}
	
	//searchUser
	
	

}

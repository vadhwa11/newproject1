package com.test.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.service.gstr1.MobileLoginService;

@RestController
@RequestMapping(value="/MobileLogin")
public class MobileLoginController {
	@Autowired
	MobileLoginService mobileLoginService;
	

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String  doMobileLogin(@RequestBody HashMap<String,Object> jsonData, HttpServletRequest request)
	{
		System.out.println("Mobiloe Login");
		return mobileLoginService.doLogin(jsonData, request);
	}

}

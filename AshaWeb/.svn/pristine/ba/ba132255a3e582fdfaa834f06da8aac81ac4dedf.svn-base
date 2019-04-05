package com.asha.icgweb.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asha.icgweb.entity.Registration;
import com.asha.icgweb.model.LoginModel;
import com.asha.icgweb.service.LoginService;
import com.asha.icgweb.utils.RestUtils;





@RequestMapping("/v0.1/dashboard")
@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	LoginService loginService;	
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)	
	public ModelAndView masterModules(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("dashboard");			
		return mav;
			
	}
	
	@RequestMapping(value="/empRegistration", method = RequestMethod.POST)
	public String empRegistration(@RequestBody HashMap<String,Object> payload,HttpServletRequest request,HttpServletResponse response)
	{
	   return "hello jkt";
		//return ls.empRegistration(payload, request, response);		
	}
	
	@RequestMapping(value="/addEmp", method = RequestMethod.POST)
	 //HashMap<String,String> listMap =  new HashMap<String,String>();
	public String registration(@ModelAttribute LoginModel payload, HttpServletRequest request,
			HttpServletResponse response) {	
		
		return loginService.registration(payload, request, response);
	}
	
	@RequestMapping(value="/getEmpList", method = RequestMethod.GET)
	public String getRegistration(HttpServletRequest request,
			HttpServletResponse response) {
		return loginService.getRegistration(request, response);
	}
	
	@RequestMapping(value = "/manageRegistration", method = RequestMethod.GET)
	public ModelAndView managemerchant(HttpServletRequest httpServletRequest) {

		String resp = null;
		List<Registration> registrationlist = loginService.getallRegistration(httpServletRequest);

		ModelAndView modelAndView = new ModelAndView("managemerchant");
		modelAndView.addObject("registationlistobject", registrationlist);

		return modelAndView;
	}
	


}


package com.asha.icgweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/ehr")
@RestController
@CrossOrigin
public class WebEHRController {

	@RequestMapping(value="/doctorRoaster", method = RequestMethod.GET)
	public ModelAndView doctorRoaster() {
		Map<String, Object> map = new HashMap<String, Object>();
		String jsp = "patientSummary";
		ModelAndView mv =new ModelAndView();
		mv.setViewName(jsp);
		return mv;		
	}
	
}

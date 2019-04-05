package com.asha.icgweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/exam")
@RestController
@CrossOrigin
public class MedicalExamWebController {
	
	@RequestMapping(value="/medicalExam", method=RequestMethod.GET)	
	public ModelAndView medicalExam(HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView mav = new ModelAndView("medicalExam");			
		return mav;
			
	}

}

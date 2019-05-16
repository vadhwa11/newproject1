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


import com.test.service.impl.gstr1.MobileService;

@RestController
@RequestMapping("/MobileService")
public class MobileServiceController {

	@Autowired
	MobileService mobileService;

	/*@CrossOrigin
	@RequestMapping(value = "/coupanragistration", method = RequestMethod.POST)
	@ResponseBody
	public String CoupanRagistration(@RequestBody HashMap<String, String> JsonData) {

		return mobileService.CoupanRagistration(JsonData);

	}

	@CrossOrigin
	@RequestMapping(value = "/coupandistribution", method = RequestMethod.POST)
	@ResponseBody
	public String CoupanDistribution(@RequestBody HashMap<String, Object> JsonData) {

		return mobileService.CoupanDistribution(JsonData);

	}*/

	@CrossOrigin
	@RequestMapping(value = "/generation", method = RequestMethod.POST)
	@ResponseBody
	public String CoupanGeneration(@RequestBody HashMap<String, String> JsonData,HttpServletRequest Request) {

		return mobileService.CoupanGeneration(JsonData,Request);

	}

/*	@CrossOrigin
	@RequestMapping(value = "/activecoupon", method = RequestMethod.POST)
	@ResponseBody
	public String ShowActiveCoupon(@RequestBody HashMap<String, String> JsonData) {

		JsonObject jsonobject = new JsonObject();
		jsonobject.addProperty("ActiveCoupon", mobileService.ShowActiveCoupon(JsonData).toString());
		return jsonobject.toString();

	}
	
	@CrossOrigin
	@RequestMapping(value = "/availablecoupon", method = RequestMethod.POST)
	@ResponseBody
	public String availableCoupon(@RequestBody HashMap<String, String> JsonData) {

		JsonObject jsonobject = new JsonObject();
		jsonobject.addProperty("available", mobileService.availableCoupon(JsonData).toString());
		return jsonobject.toString();

	}*/
}

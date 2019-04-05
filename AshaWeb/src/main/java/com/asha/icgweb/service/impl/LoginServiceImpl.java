package com.asha.icgweb.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.asha.icgweb.dao.LoginDao;
import com.asha.icgweb.entity.Registration;
import com.asha.icgweb.model.LoginModel;
import com.asha.icgweb.service.LoginService;
import com.asha.icgweb.utils.ProjectUtils;
import com.asha.icgweb.utils.Registration_Validator;
import com.asha.icgweb.utils.RestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;




@Repository
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	Registration_Validator validator;
	
	@Autowired
	LoginDao ld;

	

	@Override
	public String registration(LoginModel payload, HttpServletRequest request,
			HttpServletResponse response) {
		
		JSONObject jsonObject = new JSONObject(payload);
		JSONObject jsonParent = new JSONObject();
		jsonParent.put("registration", jsonObject);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		//http://192.168.10.59:7002/v0.1/dashboard/addEmp
		return RestUtils.postWithHeaders("http://192.168.10.59:7003/IcgPOC/RestService/TST", requestHeaders, jsonParent.toString());
	//http://192.168.10.59:7003/IcgPOC/RestService/TST -->OSB URL
	//http://192.168.10.59:7002/v0.1/dashboard/addEmp  -->Java Service URL	
	}
	
	
	public boolean isEmpty(Object value) {
		if (value != null && !value.toString().trim().isEmpty() && value.toString() != "") {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<Registration> getallRegistration(HttpServletRequest httpServletRequest) {

		return (List<Registration>) ld.getAllRegistration();
	}


	@Override
	public String getRegistration(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		return "Emp get list";
	}

	@Override
	public String empRegistration(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
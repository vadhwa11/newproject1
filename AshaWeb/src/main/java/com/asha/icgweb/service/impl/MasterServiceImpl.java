package com.asha.icgweb.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.asha.icgweb.service.MasterService;
import com.asha.icgweb.utils.RestUtils;

@Repository
public class MasterServiceImpl implements MasterService{

	@Override
	public String getAllCommandDetails(String cmdPayload, HttpServletRequest request, HttpServletResponse response) {
		//JSONObject jsonObject = new JSONObject(masCmdPayload);
		
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		//String restUrl = "http://localhost:8083/AshaServices/v0.1/master/getCommand";
		String restUrl = "http://localhost:8083/AshaServices/v0.1/master/getAllCommand";
		//String osbUrl = "http://192.168.10.59:7003/AshaMasterGetCommand/Get";
		System.out.println("restUrl getAllCommandDetails :: "+restUrl);
		return RestUtils.postWithHeaders(restUrl, requestHeaders, cmdPayload);
		
		//return null;
	}

	@Override
	public String getTradeDetails(String tradeDetailsPayload, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String restUrl = "http://localhost:8083/AshaServices/v0.1/master/getTradeDetails";
		System.out.println("restUrl getTrade :: "+restUrl);
		return RestUtils.postWithHeaders(restUrl, requestHeaders, tradeDetailsPayload);
	}

	@Override
	public String getCommandDetails(String cmdPayload, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		//String restUrl = "http://localhost:8083/AshaServices/v0.1/master/getCommand";
		String restUrl = "http://localhost:8083/AshaServices/v0.1/master/getCommand";
		//String osbUrl = "http://192.168.10.59:7003/AshaMasterGetCommand/Get";
		System.out.println("restUrl :: "+restUrl);
		return RestUtils.postWithHeaders(restUrl, requestHeaders, cmdPayload);
	}

	
}
package com.asha.icgweb.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.asha.icgweb.service.OpdService;
import com.asha.icgweb.utils.AppUrlConstant;
import com.asha.icgweb.utils.HMSUtil;
import com.asha.icgweb.utils.RestUtils;

@Repository
public class OpdServiceImpl implements OpdService {
	String ipAndPort = HMSUtil.getProperties("urlextension.properties", "OSB_IP_AND_PORT");

	@Override
	public String preConsPatientWatingWeb(String payload, HttpServletRequest request, HttpServletResponse response) {
		
		//JSONObject jsonParent = new JSONObject();
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","preConsPatientWatingWeb");
		String OSBURL = ipAndPort + Url;
		System.out.println(OSBURL);
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.preConsPatientWatingWeb, requestHeaders, payload);
	}

	@Override
	public String saveVitailsPatientdetails(String payload, HttpServletRequest request, HttpServletResponse response) {
		
		JSONObject jsonParent = new JSONObject();
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","saveVitailsPatientdetails");
		String OSBURL = ipAndPort + Url;
	    return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.saveVitailsPatientdetails, requestHeaders, payload);
	}

	@Override
	public String getIdealWeight(String payload, HttpServletRequest request, HttpServletResponse response) {
		//JSONObject jsonParent = new JSONObject();
				MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
				String Url = HMSUtil.getProperties("urlextension.properties","getIdealWeight");
				String OSBURL = ipAndPort + Url;
				return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
				//return RestUtils.postWithHeaders(AppUrlConstant.getIdealWeight, requestHeaders, payload);
	}

	@Override
	public String getOpdWaitingList(String payload, HttpServletRequest request, HttpServletResponse response) {
		//JSONObject jsonParent = new JSONObject();
		String Url = HMSUtil.getProperties("urlextension.properties","getOpdWaitingList");
		String OSBURL = ipAndPort + Url;
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.getOpdWaitingList, requestHeaders, payload);
	}

	@Override
	public String getIcdList(String payload, HttpServletRequest request, HttpServletResponse response) {
		//JSONObject jsonParent = new JSONObject();
		String Url = HMSUtil.getProperties("urlextension.properties","getIcdList");
		String OSBURL = ipAndPort + Url;
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.getIcdList, requestHeaders, payload);
	}

	/*@Override
	public String getOpdPatinetDetails(String payload, HttpServletRequest request, HttpServletResponse response) {
		//JSONObject jsonParent = new JSONObject();
				MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
				return RestUtils.postWithHeaders("http://localhost:8181/AshaServices/v0.1/opd/getPatientDetails", requestHeaders, payload);
	}*/

	@Override
	public String getFamilyPatientHistory(String payload, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","getFamilyPatientHistory");
		String OSBURL = ipAndPort + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.getFamilyPatientHistory, requestHeaders, payload);
	}

	@Override
	public String getOpdPatientDetailModel(String payload,HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","getOpdPatientDetailModel");
		String OSBURL = ipAndPort + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.getOpdPatientDetailModel, requestHeaders, payload);
	}

	@Override
	public String saveOpdPatientdetails(String payload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonParent = new JSONObject();
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","saveOpdPatientdetails");
		String OSBURL = ipAndPort + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.saveOpdPatientdetails, requestHeaders, payload);
	}

	@Override
	public String saveOpdTemplates(String payload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonParent = new JSONObject();
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","saveOpdTemplates");
		String OSBURL = ipAndPort + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.saveOpdTemplates, requestHeaders, payload);
	}

	@Override
	public String getIinvestigationList(String payload, HttpServletRequest request, HttpServletResponse response) {
		//JSONObject jsonParent = new JSONObject();
				MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
				String Url = HMSUtil.getProperties("urlextension.properties","getIinvestigationList");
				String OSBURL = ipAndPort + Url;
				return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
				//return RestUtils.postWithHeaders(AppUrlConstant.getIinvestigationList, requestHeaders, payload);
	}

	@Override
	public String getMasStoreItemList(String payload, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","getMasStoreItemList");
		String OSBURL = ipAndPort + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.getMasStoreItemList, requestHeaders, payload);
	}


	@Override
	public String getMasDisposalList(String payload,HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		
		//return RestUtils.postWithHeaders(AppUrlConstant.getMasDisposalList, requestHeaders, payload);
		return RestUtils.postWithHeaders(AppUrlConstant.getMasDisposalList, requestHeaders, payload);
	}

	@Override
	public String getMasFrequency(String payload, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","getMasFrequency");
		String OSBURL = ipAndPort + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.getMasFrequency, requestHeaders, payload);
	}
	
	@Override
	public String getMasNursingCare(String payload, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","getMasStoreItemList");
		String OSBURL = ipAndPort + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.getMasNursingCare, requestHeaders, payload);
	}

	
	@Override
	public String getTemplateName(String payload, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","getTemplateName");
		String OSBURL = ipAndPort + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.getTemplateName, requestHeaders, payload);
	}
	
	@Override
	public String getEmpanelledHospital(String payload, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","getEmpanelledHospital");
		String OSBURL = ipAndPort + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders(AppUrlConstant.getEmpanelledHospital, requestHeaders, payload);
	}

	@Override
	public String obesityWaitingList(String payload) {
		JSONObject jsonParent = new JSONObject();
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		//return RestUtils.postWithHeaders("http://localhost:8081/AshaServices/v0.1/opd/getObesityWaitingList", requestHeaders, payload);
		//osb link
		String osb = HMSUtil.getProperties("urlextension.properties", "obesityWaitingList");
		return RestUtils.postWithHeaders(ipAndPort.trim()+osb.trim(), requestHeaders, payload);
	}
	
	@Override
	public String getObesityDetail(String payload, HttpServletRequest request, HttpServletResponse response){
		JSONObject jsonParent = new JSONObject();
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String osb = HMSUtil.getProperties("urlextension.properties", "getObesityDetail");
		return RestUtils.postWithHeaders(ipAndPort.trim()+osb.trim(), requestHeaders, payload);
		//return RestUtils.postWithHeaders("http://192.168.10.59:7003/GetObesityDetails/ProxyService/GetObesityDetails", requestHeaders, payload);

		
	}


	
}

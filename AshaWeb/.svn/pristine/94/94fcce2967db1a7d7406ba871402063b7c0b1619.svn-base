package com.asha.icgweb.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asha.icgweb.utils.HMSUtil;
import com.asha.icgweb.utils.RestUtils;


@RestController
@CrossOrigin
@RequestMapping(value="/utility")
public class WebImportExportController {
	
	String IpAndPortNo = HMSUtil.getProperties("urlextension.properties", "LOCAL_IP");
	//String IpAndPortNo = HMSUtil.getProperties("urlextension.properties", "OSB_IP_AND_PORT");
	
	@RequestMapping(value="/exportUtil", method=RequestMethod.GET)	
	public ModelAndView exportUtility(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("exportUtility");			
		return mav;
			
	}
	
	@RequestMapping(value="/getExportSyncTableList", method=RequestMethod.POST)
	public String getExportSyncTableList(@RequestBody String payload, 
			HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		/*String Url = HMSUtil.getProperties("urlextension.properties", "getExportSyncTableList");
		String OSBURL = IpAndPortNo + Url;		*/
		return RestUtils.postWithHeaders("http://192.168.203.172:8081/AshaServices/utility/getExportSyncTableList", requestHeaders, jsonObject.toString());
		
	}	
	
	
	@RequestMapping(value="/genrateCSV", method=RequestMethod.POST)
	public String genrateCSV(@RequestBody String payload, 
			HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "genrateCSV");
		String OSBURL = IpAndPortNo + Url;		
		return RestUtils.postWithHeaders("http://192.168.203.172:8081/AshaServices/utility/genrateCSV", requestHeaders, jsonObject.toString());
		
	}	
	
	
	@RequestMapping(value="/importUtil", method=RequestMethod.GET)	
	public ModelAndView importUtility(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("importUtility");			
		return mav;
			
	}
	
	@RequestMapping(value="/importCSV", method=RequestMethod.POST)
	public String importCSV(@RequestBody String payload, 
			HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "importCSV");
		String OSBURL = IpAndPortNo + Url;		
		return RestUtils.postWithHeaders("http://192.168.203.172:8081/AshaServices/utility/importCSV", requestHeaders, jsonObject.toString());
		
	}	
	
	
	@RequestMapping(value="/icdAutocomplete", method=RequestMethod.GET)	
	public ModelAndView icdAutocomplete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("icdAutoComplete");			
		return mav;
			
	}
	
	@RequestMapping(value="/getICDNameSearch", method=RequestMethod.POST)
	public String getICDNameSearch(@RequestBody String payload,
			HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("icdName", payload.replace("icdName=", ""));
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "getICDNameSearch");
		String OSBURL = IpAndPortNo + Url;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, jsonObject.toString());
		
	}	
	
	
	@RequestMapping(value="/patientSummary", method=RequestMethod.GET)	
	public ModelAndView patientSummary(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("patientSummary");			
		return mav;
			
	}	
	
	@RequestMapping(value="/downloadDataInZip", method=RequestMethod.GET)	
	public String downloadDataInZip(HttpServletRequest request, HttpServletResponse response) {
		String msg = "";
		try {
			MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
			String data = RestUtils.postWithHeaders("http://192.168.203.172:8081/AshaServices/utility/zipData",
					requestHeaders, "{}");
			JSONObject obj = new JSONObject(data);
			String dataInBytes = obj.getString("data");
			ServletOutputStream sos = response.getOutputStream();
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment; filename=Data.zip");
			byte[] byteData = Base64.decodeBase64(dataInBytes);
			sos.write(byteData);
			sos.flush();
			msg = "File Downloaded Successfully";
			return msg;
		} catch (Exception ex) {
			ex.printStackTrace();
			msg = "Error while Downloading";
		}
		return msg;
	}
	
	@RequestMapping(value="/getImportSyncTableList", method=RequestMethod.POST)
	public String getImportSyncTableList(@RequestBody String payload, 
			HttpServletRequest request, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(payload);
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		return RestUtils.postWithHeaders("http://192.168.203.172:8082/AshaServices/utility/getImportSyncTableList", requestHeaders, jsonObject.toString());
		
	}	
	
}


package com.icg.jkt.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icg.jkt.entity.MasIcd;
import com.icg.jkt.service.ImportExportUtilService;
import com.icg.jkt.utils.ProjectUtils;

@RestController
@CrossOrigin
@RequestMapping("/utility")
public class ImportExportUtilityController {
	
	@Autowired
	ImportExportUtilService ieu;
	
	@RequestMapping(value = "/getExportSyncTableList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getExportSyncTableList(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(requestObject);		
		return ieu.getExportSyncTableList(jsonObject,request, response);
		
	}
	
	
	@RequestMapping(value = "/genrateCSV", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String genrateCSV(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(requestObject);		
		return ieu.genrateCSV(jsonObject,request, response);
		
	}
	
	
	@RequestMapping(value = "/importCSV", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String importCSV(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(requestObject);		
		return ieu.importCSV(jsonObject,request, response);
		
	}

	@RequestMapping(value = "/getICDNameSearch", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<MasIcd> getICDNameList(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(requestObject);		
		return ieu.getICDNameSearch(jsonObject,request, response);
		
	}
	
	@RequestMapping(value = "/zipData", method=RequestMethod.POST)
	public String downloadZip(HttpServletResponse response, HttpServletRequest request) {
		String msg = "";
        try {
        	//String path = "/u01/app/oracle/Datapump/SYNC_ICG";
        	String path = "E:\\src_folder";
            File directory = new File(path);
            String[] files = directory.list();
            
            if (files != null && files.length > 0) {

                byte[] zip = ProjectUtils.zipFiles(directory, files);
                String base64String = Base64.encodeBase64String(zip);
                JSONObject obj = new JSONObject();
                obj.put("data", base64String);
                return obj.toString();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            msg = "Error occured while downloading";
        }
        return msg;
	}
	
	@RequestMapping(value = "/getImportSyncTableList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getImportSyncTableList(@RequestBody Map<String, Object> requestObject, HttpServletRequest request,
			HttpServletResponse response) {			
		return ieu.getImportSyncTableList(request, response);		
	}
	
}

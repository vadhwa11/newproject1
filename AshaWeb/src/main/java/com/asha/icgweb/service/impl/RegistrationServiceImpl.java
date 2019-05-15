package com.asha.icgweb.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.asha.icgweb.dao.FileUploadDAO;
import com.asha.icgweb.entity.Patient;
import com.asha.icgweb.entity.UploadDocument;
import com.asha.icgweb.service.RegistrationService;
import com.asha.icgweb.utils.HMSUtil;
import com.asha.icgweb.utils.RestUtils;

@Repository
public class RegistrationServiceImpl implements RegistrationService {
	
	
	@Autowired
	FileUploadDAO fileUploadDao;

	String IpAndPortNo=HMSUtil.getProperties("urlextension.properties","OSB_IP_AND_PORT");
	
	
	@Override
	public String getDepartmentBloodGroupAndMedicalCategory(String data, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","DEPT_BlOOD_MEDICAL_CAT");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, data);
		
	}

	
	@Override
	public String getEmployeeAndDependentData(String data, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","PATIEN_DETAILS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders, data);
	}

	
	@Override
	public String getTokenNoForDepartmentMultiVisit(String data, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","TOKEN_FOR_MULTI_VISIT");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders, data);
	}


	@Override
	public String submitPatientDetails(String data, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","SUBMIT_PATIENT_DETAILS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders, data);
	}


	@Override
	public String showRegistrationAndAppointmentOthers(String data, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","APPOINTMENT_FOR_OTHERS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders, data);
	}


	@Override
	public String getTokenNoOfDepartmentForOthers(String data, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","TOKEN_FOR_OTHERS");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders, data);
	}


	@Override
	public String submitPatientDetailsForOthers(String data, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","SUBMIT_OTHERS_PATIENTS_DATA");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders, data);
	}


	@Override
	public String searchOthersRegisteredPatient(String data, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","SEARCH_FOR_REGISTERED_PATIENT");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders, data);
	}


	@Override
	public String getPatientListFromServiceNo(String data, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties","PATIENT_LIST_FOR_UPLOAD_DOCUMENT");
		String OSBURL = IpAndPortNo + Url;
		return RestUtils.postWithHeaders(OSBURL.trim(),requestHeaders, data);
	}
	
	@Override
	public String getAppointmentTypeList(String data, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String,String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String Url = HMSUtil.getProperties("urlextension.properties", "getAppointmentTypeList");
		String OSBURL = IpAndPortNo + Url;		
		return RestUtils.postWithHeaders(OSBURL.trim(), requestHeaders, data);
	}

	@Override
	public String uploadDocumentForPatient(HttpServletRequest request,
            @RequestParam CommonsMultipartFile[] fileUpload) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		boolean flag=false;
		long patientId=0;
		String remarks="";
		
		if(!request.getParameter("patientName").isEmpty()) {
			patientId = Long.parseLong(request.getParameter("patientName"));
		}
		
		if(!request.getParameter("remarks").isEmpty()) {
			remarks = request.getParameter("remarks");
		}
		
	    if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){
                  
                System.out.println("Saving file: " + aFile.getOriginalFilename());
                 
                UploadDocument uploadFile = new UploadDocument();
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setFileData(aFile.getBytes());
                
                Patient patient = new Patient();
                patient.setPatientId(patientId);
                uploadFile.setPatient(patient);
                
                uploadFile.setRemarks(remarks);
                
                flag= fileUploadDao.save(uploadFile); 
                
                if(flag) {
    		    	map.put("message", "File uploaded Sucessfully");
    		    	map.put("status", "1");
                }else {
   	    		 map.put("message", "File is not uploaded Sucessfully, some error is occurred");
 		    	 map.put("status", "0");
                }
            }
        }
	    json.put("data", map);
        return json.toString();
		
	}


	@Override
	@SuppressWarnings("unchecked")
	public String getDocumentListForPatient(String data, HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		List<HashMap<String,Object>> docListObj = new ArrayList<HashMap<String,Object>>();
		JSONObject jsonObj = new JSONObject(data);
		long patientId = jsonObj.getLong("patientId");
		
		map =fileUploadDao.showUploadedDocumentsForPatient(patientId);
		List<UploadDocument> docList = (List<UploadDocument>) map.get("documentList");
		if(docList!=null &&  docList.size()>0 ) {
			for (UploadDocument list : docList) {
				HashMap<String, Object> mapDoc = new HashMap<String, Object>();
				mapDoc.put("Id",list.getFileId());
				mapDoc.put("fileName",list.getFileName());
				mapDoc.put("remarks",list.getRemarks());
				docListObj.add(mapDoc);
			}
			if (docListObj != null && docListObj.size() > 0) {
				json.put("data", docListObj);
				json.put("status", 1);
			} else {
				json.put("data", docListObj);
				json.put("msg", "Data not found");
				json.put("status", 0);
			}
			
		}
		return json.toString();
	}


	
	@SuppressWarnings("deprecation")
	@Override
	public  Map<String,Object> viewUploadDocuments(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		List<UploadDocument> uploadDocuments = new ArrayList<UploadDocument>();
		Map<String,Object> map = new HashMap<String, Object>();
		long documentId=0;
		
		if(Long.parseLong(request.getParameter("fileId"))!=0) {
			documentId=Long.parseLong(request.getParameter("fileId"));
		}
		
		uploadDocuments = fileUploadDao.viewUploadDocuments(documentId);
		String filename =uploadDocuments.get(0).getFileName();
		
		StringTokenizer st1=new StringTokenizer(filename,".");
 		filename=st1.nextToken();
 		String fileExtension=st1.nextToken();
 		
		UploadDocument document = new UploadDocument();
		try {
			 // Adding new code for view
			
			if (fileExtension =="doc" || fileExtension =="docx"){
			   response.setContentType("application/vnd.ms-word");
			   }else if (fileExtension == "xls" || fileExtension == "xlsx"){
			   response.setContentType("application/vnd.ms-excel");
			   }else if (fileExtension == "pdf"){
			   response.setContentType("application/pdf");
			   }else if (fileExtension.trim().equalsIgnoreCase("txt")){
			   response.setContentType("text/plain");
			   }else if (fileExtension.trim().equalsIgnoreCase("ppt")){
			   response.setContentType("application/ppt");
			   }else if (fileExtension == "png"){
			   response.setContentType("image/png");
			   }else if (fileExtension == "jpeg"){
			   response.setContentType("image/jpeg");
			   }else if (fileExtension == "wbmp"){
			   response.setContentType("image/vnd.wap.wbmp");
			   }else if (fileExtension == "gif"){
			   response.setContentType("image/gif");
			   }else if (fileExtension == "jpg"){
			   response.setContentType("image/jpg");
			   }
			   else{
			   response.setContentType("application/octet-stream");
			   }
			
			response.setHeader ("Content-Disposition", "inline;filename="+java.net.URLEncoder.encode(filename)+"");
			
		   for(UploadDocument doc: uploadDocuments)
				   {
					   
				   byte[] bytes = doc.getFileData();
				   Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
				   InputStream inputStream = blob.getBinaryStream();
	                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                byte[] buffer = new byte[4096];
	                int bytesRead = -1;
	                 
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);                  
	                }
				  
	                byte[] imageBytes = outputStream.toByteArray();
	                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	                inputStream.close();
	                outputStream.close();
	                document.setBase64Image(base64Image);
	             // Adding new code for view
		   } 
			
		} catch (IOException ioe) {
 		   ioe.printStackTrace();
 	   }
		map.put("document", document);
		map.put("fileExt", fileExtension);
		return map;
	}


	@Override
	public String deleteUploadDocument(String data, HttpServletRequest request, HttpServletResponse response) {
		boolean status=false;
		Map<String,Object> map = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		JSONObject jsonObj = new JSONObject(data);
		long fileId = jsonObj.getLong("fileId");
		
		status = fileUploadDao.deleteUploadDocument(fileId);
		if(status) {
			map.put("status", "1");
			map.put("msg", "File removed successfully.");
		}else {
			map.put("status", "0");
			map.put("msg", "File can not removed.");
		}
		json.put("data", map);
		return json.toString();
	}


	

	

	

}

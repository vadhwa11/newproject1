package com.icg.jkt.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icg.jkt.dao.UserManagementDao;
import com.icg.jkt.entity.MasApplication;
import com.icg.jkt.entity.MasTemplate;
import com.icg.jkt.entity.UserApplication;
import com.icg.jkt.entity.Users;
import com.icg.jkt.service.UserManagementService;

@Service("UserManagementService")
public class UserManagementServiceImpl implements UserManagementService{

	@Autowired
	UserManagementDao userManagementDao;
	
	@Override
	public String getAllUserApplication(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<UserApplication> userAppList = new ArrayList<UserApplication>();
		List list = new ArrayList();
		List totalMatches = new ArrayList();
		if(jsonObject!=null) {
		Map<String, List<UserApplication>> map = userManagementDao.getAllUserApplication(jsonObject);		
		
		if(map.get("userAppList")!=null) {
			userAppList = map.get("userAppList");
			totalMatches = map.get("totalMatches");
			for(UserApplication userApplication  :userAppList) {
				HashMap<String, Object> mapObj = new HashMap<String, Object>();
				if(userApplication!=null) {
					mapObj.put("id", userApplication.getId()) ;					
					mapObj.put("appName", userApplication.getAppName().trim()) ;					
					mapObj.put("url", userApplication.getUrl()) ;
					mapObj.put("status", userApplication.getStatus());					
				list.add(mapObj);
			 }
			}
			
			if(list!=null && list.size()>0) {
				json.put("data", list);
				json.put("count", totalMatches.size());
				json.put("msg", "Record fetch successfully");
				json.put("status", 1);
			}else {
				json.put("data", list);
				json.put("count", totalMatches.size());
				json.put("msg", "No Record Found");
				json.put("status", 0);
			}
			
		}else {
			json.put("statut", 0);
			json.put("msg", "No Record Found");
		}
		
	}else {
		json.put("msg", "No Record Found");
		json.put("status", 0);
	}
		return json.toString();
	}

	
	@Override
	public String updateUserApplication(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		String userApp = userManagementDao.updateUserApplication(jsonObject);
		if (userApp != null && userApp.equalsIgnoreCase("200")) {
			json.put("userApp", userApp);
			json.put("msg", "Record Updated Successfully.");
			json.put("status", 1);
		} else if(userApp != null && userApp.equalsIgnoreCase("300")){
			json.put("msg", "Record Added Successfully");
			json.put("status", 1);

		}else {
			json.put("msg", "Record Not Updated.");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String getAllTemplate(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasTemplate> templateList = new ArrayList<>();
		List list = new ArrayList();
		if(jsonObject!=null) {
		Map<String, List<MasTemplate>> map = userManagementDao.getAllTemplate(jsonObject);
		
		List totalMatches = new ArrayList();
		if(map.get("templateList")!=null) {
			templateList = map.get("templateList");
			totalMatches = map.get("totalMatches");
			for(MasTemplate masTemplate   :templateList) {
				HashMap<String, Object> mapObj = new HashMap<String, Object>();
				if(masTemplate!=null) {
					mapObj.put("templateId", masTemplate.getTemplateId()) ;					
					mapObj.put("templateCode", masTemplate.getTemplateCode().trim()) ;					
					mapObj.put("templateName", masTemplate.getTemplateName().trim()) ;
					mapObj.put("status", masTemplate.getStatus());					
				list.add(mapObj);
			 }
			}
			
			if(list!=null && list.size()>0) {
				json.put("data", list);
				json.put("count", totalMatches.size());
				json.put("msg", "Record fetch successfully");
				json.put("status", 1);
			}else {
				json.put("data", list);
				json.put("count", totalMatches.size());
				json.put("msg", "No Record Found");
				json.put("status", 0);
			}
			
		}else {
			json.put("statut", 0);
			json.put("msg", "No Record Found");
		}
		
	}else {
		json.put("msg", "No Record Found");
		json.put("status", 0);
	}
		return json.toString();
	}

	

	@Override
	public String getApplicationAutoComplete(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasApplication> applicationList = new ArrayList<MasApplication>();
		List<MasApplication> masApplicationsList = new ArrayList<MasApplication>();
		Map<String, List<MasApplication>> map = userManagementDao.getApplicationAutoComplete(jsonObject);
		
		if(map.get("maxAppIdlist")!=null) {
			applicationList = map.get("maxAppIdlist");
			masApplicationsList = map.get("masApplicationsList");
			for(int i=0;i<applicationList.size();i++) {
								
				json.put("max_app_id", applicationList.get(i));
				
			}
			
			if(masApplicationsList!=null && masApplicationsList.size()>0) {
				json.put("data", masApplicationsList);
				json.put("count", masApplicationsList.size());
				json.put("status", 1);
			}else {
				json.put("data", "No Record Found");
				json.put("count", masApplicationsList.size());
				json.put("status", 0);
			}
			
		}else {
			json.put("statut", 0);
			json.put("msg", "No Record Found");
		}
		
		return json.toString();
	}
	
	/*@Override
	public String getApplicationAutoComplete(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasApplication> masApplicationsList = userManagementDao.getApplicationAutoComplete(jsonObject);
		if(masApplicationsList!=null && masApplicationsList.size()>0) {
			json.put("data", masApplicationsList);
			json.put("count", masApplicationsList.size());
			json.put("status", 1);
		}else {
			json.put("data", "No Record Found");
			json.put("count", masApplicationsList.size());
			json.put("status", 0);
		}
		return json.toString();
	}
*/
	

}

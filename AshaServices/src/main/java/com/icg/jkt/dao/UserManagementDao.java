package com.icg.jkt.dao;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.MasApplication;
import com.icg.jkt.entity.MasTemplate;
import com.icg.jkt.entity.UserApplication;

@Repository
public interface UserManagementDao {
	
	Map<String, List<UserApplication>> getAllUserApplication(JSONObject jsonObject);
	List<UserApplication> validateUserApplication(String applicationName, String url);
	//String saveAndUpdateUserApplication(UserApplication userApplication);
	String updateUserApplication(JSONObject jsonObject);
	Map<String, List<MasTemplate>> getAllTemplate(JSONObject jsonObject);
	Map<String, List<MasApplication>> getApplicationAutoComplete(JSONObject jsonObject);

}

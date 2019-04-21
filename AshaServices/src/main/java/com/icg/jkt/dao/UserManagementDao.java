package com.icg.jkt.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.MasApplication;
import com.icg.jkt.entity.MasRole;
import com.icg.jkt.entity.MasTemplate;
import com.icg.jkt.entity.RoleTemplate;
import com.icg.jkt.entity.TemplateApplication;
import com.icg.jkt.entity.UserApplication;

@Repository
public interface UserManagementDao {
	
	Map<String, List<UserApplication>> getAllUserApplication(JSONObject jsonObject);
	List<UserApplication> validateUserApplication(String applicationName, String url);
	String saveUserApplication(UserApplication userApplication);
	String updateUserApplication(JSONObject jsonObject);
	Map<String, List<MasTemplate>> getAllTemplate(JSONObject jsonObject);
	Map<String, List<UserApplication>> getApplicationAutoComplete(JSONObject jsonObject);
	String updateTemplate(JSONObject jsonObject);
	List<MasTemplate> validateTemplate(String templateCode, String templateName);
	String saveTemplate(MasTemplate masTemplate);
	List<MasTemplate> getTemplateList();
	Map<String, List<TemplateApplication>> getModuleNameTemplateWise(JSONObject jsonObject);
	List<MasApplication> getModuleListForTemplate();
	Map<String, Object> populateApplications(JSONObject jsonObject);
	List<MasApplication> validateAddFormAndReports(MasApplication masApplication);
	String addFormAndReports(MasApplication masApplication);
	String updateUserApplicationStatus(long appID);
	Map<String, Object> getAllApplicationAndTemplates(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String addTemplateApplication(TemplateApplication templateApplication);
	
	List<MasRole> getRoleRightsList();
	List<MasTemplate> getTemplateNameList();
	List<RoleTemplate> getAssingedTemplateNameList(JSONObject json);
	String saveRolesRight(JSONObject json);
	List<MasApplication> getApplicationNameFormsAndReport(JSONObject jsonObject);
	String updateAddFormsAndReport(MasApplication masApplication);

}

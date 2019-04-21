package com.icg.jkt.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterService {

	String departmentList(HashMap<String, String> jsondata, HttpServletRequest request);

	String getICD(HashMap<String, String> jsondata, HttpServletRequest request);

	String getAllStates(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	/******************************
	 * MAS COMMAND
	 ************************************************/
	String getCommandTypeList(HttpServletRequest request, HttpServletResponse response);

	String addCommand(JSONObject json, HttpServletRequest request, HttpServletResponse response);

	String getAllCommand(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response);

	String getCommand(HashMap<String, Object> command, HttpServletRequest request);

	String updateCommand(HashMap<String, Object> command, HttpServletRequest request, HttpServletResponse response);

	String statusCommand(HashMap<String, Object> command, HttpServletRequest request, HttpServletResponse response);

	
	/*************************** MAS UNIT ***********************************/
	String addUnits(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response);

	String getAllUnit(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response);

	String getCommandList(HttpServletRequest request, HttpServletResponse response);

	String getUnitTypeList(HttpServletRequest request, HttpServletResponse response);

	String updateUnit(HashMap<String, Object> unitPayload, HttpServletRequest request, HttpServletResponse response);

	String updateStatus(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	/*********************************
	 * MAS HOSPITAL
	 ***************************************/
	String addMasHospital(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String getUnitNameList(HttpServletRequest request, HttpServletResponse response);

	String getAllHospital(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateHospitalMasterStatus(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateHospitalDetails(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	/*************************************
	 * MAS RELATION
	 *************************************************************************/
	String getAllRelation(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateRelationDetails(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateRelationStatus(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String addRelation(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	/****************************************
	 * ADD DISPOSAL
	 ********************************************************************/
	String addDisposal(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String getAllDisposal(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateDisposalDetails(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateDisposalStatus(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	/****************************************
	 * MAS APPOINTMENT TYPE
	 *************************************************************/
	String addAppointmentType(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String getAllAppointmentType(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateAppointmentTypeDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response);

	String updateAppointmentTypeStatus(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	/****************************************
	 * MAS DEPARTMENT
	 *******************************************************************/
	String getAllDepartment(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String getDepartmentTypeList(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String addDepartment(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateDepartmentDetails(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateDepartmentStatus(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	/****************************************
	 * MAS FREQUENCY
	 *********************************************************************/
	String getAllOpdFrequency(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String addOpdFrequency(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateOpdFrequencyDetails(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateOpdFrequencyStatus(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	/************************************************************************************************/
	String getAllEmpanelledHospital(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String addEmpanelledHospital1(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String addEmpanelledHospital(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updateEmpanelledHospital(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	/**************************************
	 * MAS IDEAL WEIGHT
	 *************************************************/
	String getAllIdealWeight(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String getAge(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String updateIdealWeight(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String addIdealWeight(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	/**************************************
	 * Phsiotherapy
	 *************************************************/

	String getAllPhsiotherapy(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String addPhsiotherapy(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	String updatePhysiotherapyDetails(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);

	/**************************************
	 * MAS SERVICE TYPE
	 *************************************************/
	String getAllServiceType(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response);

	String updateServiceType(HashMap<String, Object> serviceType, HttpServletRequest request,
			HttpServletResponse response);

	String addServiceType(JSONObject json, HttpServletRequest request, HttpServletResponse response);

	String statusServiceType(HashMap<String, Object> serviceType, HttpServletRequest request,
			HttpServletResponse response);
	
	/******************************MAS RANK************************************************/
	String addRank(JSONObject json, HttpServletRequest request, HttpServletResponse response);
	String getAllRank(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response);
	String getRank(HashMap<String, Object> rank, HttpServletRequest request);
	String updateRank(HashMap<String, Object> rank, HttpServletRequest request,HttpServletResponse response);
	String statusRank(HashMap<String, Object> rank, HttpServletRequest request,HttpServletResponse response);
	String getEmployeeCategoryList(HttpServletRequest request, HttpServletResponse response);
	
	/******************************MAS Trade************************************************/
	String addTrade(JSONObject json, HttpServletRequest request, HttpServletResponse response);
	String getAllTrade(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response);
	String getTrade(HashMap<String, Object> trade, HttpServletRequest request);
	String updateTrade(HashMap<String, Object> trade, HttpServletRequest request,HttpServletResponse response);
	String statusTrade(HashMap<String, Object> trade, HttpServletRequest request,HttpServletResponse response);
	String getServiceTypeList(HttpServletRequest request, HttpServletResponse response);
	
	/****************************************MAS RELIGION*********************************************************************/
	String getAllReligion(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addReligion(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateReligionDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateReligionStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	

	/****************************************MAS MARITAL STATUS*********************************************************************/
	String getAllMaritalStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addMaritalStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateMaritalStatusDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateMaritalStatusStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/****************************************MAS EMPLOYEE CATEGORY*********************************************************************/
	String getAllEmployeeCategory(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addEmployeeCategory(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateEmployeeCategoryDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateEmployeeCategoryStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/****************************************MAS Administrative Sex*********************************************************************/
	String getAllAdministrativeSex(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addAdministrativeSex(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateAdministrativeSexDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateAdministrativeSexStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/****************************************MAS Medical Category*********************************************************************/
	String getAllMedicalCategory(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addMedicalCategory(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateMedicalCategoryDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateMedicalCategoryStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/****************************************MAS Blood Group*********************************************************************/
	String getAllBloodGroup(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addBloodGroup(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateBloodGroupDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateBloodGroupStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/****************************************MAS Sample*********************************************************************/
	String getAllSample(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addSample(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateSampleDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateSampleStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/****************************************MAS UOM*********************************************************************/
	String getAllUOM(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addUOM(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateUOMDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateUOMStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);

	/****************************************MAS Item Unit*********************************************************************/
	String getAllItemUnit(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addItemUnit(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateItemUnitDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateItemUnitStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	
	
	/****************************************MAS MainChargecode*********************************************************************/
	String addMainChargecode(JSONObject json, HttpServletRequest request, HttpServletResponse response);
	String getAllMainChargecode(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response);
	String updateMainChargecodeDetails(HashMap<String, Object> mainChargecode, HttpServletRequest request,HttpServletResponse response);
	String updateMainChargecodeStatus(HashMap<String, Object> mainChargecode, HttpServletRequest request,HttpServletResponse response);
	String getDepartmentList(HttpServletRequest request, HttpServletResponse response);
	
	/****************************************Users*********************************************************************/
	String addUsers(JSONObject json, HttpServletRequest request, HttpServletResponse response);
	String getAllUsers(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response);
	String updateUsersDetails(HashMap<String, Object> users, HttpServletRequest request,HttpServletResponse response);
	String updateUsersStatus(HashMap<String, Object> users, HttpServletRequest request,HttpServletResponse response);
	String getHospitalList(HttpServletRequest request, HttpServletResponse response);

	/****************************************MAS Role*********************************************************************/
	String getAllRole(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addRole(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateRoleDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateRoleStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
}

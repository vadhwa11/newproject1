package com.icg.jkt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.MasCommand;
import com.icg.jkt.entity.MasTrade;

@Repository
public interface MasterService {

	String departmentList(HashMap<String, String> jsondata, HttpServletRequest request);
	String getICD(HashMap<String, String> jsondata, HttpServletRequest request);
	
	String getAllStates(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	
	String getTradeDetails(HashMap<String, String> tradePayload, HttpServletRequest request, HttpServletResponse response);	
	Map<String, Object> getTradeList(HttpServletRequest request, HttpServletResponse response);	
	String addTradeDetails(JSONObject json,HttpServletRequest request,HttpServletResponse response);

	/******************************MAS COMMAND************************************************/
	String getCommandTypeList(HttpServletRequest request, HttpServletResponse response);
	
	String addCommand(JSONObject json, HttpServletRequest request, HttpServletResponse response);
	String getAllCommand(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response);
	String getCommand(HashMap<String, Object> command, HttpServletRequest request);
	String updateCommand(HashMap<String, Object> command, HttpServletRequest request,HttpServletResponse response);
	String statusCommand(HashMap<String, Object> command, HttpServletRequest request,HttpServletResponse response);
	
	/***************************MAS UOM********************************************/
	String addMasUOM(JSONObject jsonObject,HttpServletRequest request);
	String getAllUOM(JSONObject jsonObject,HttpServletRequest request,HttpServletResponse response);
	String updateUOMDetails(HashMap<String, Object> masUnit,HttpServletRequest request,HttpServletResponse response);
	String updateUOMStatus(HashMap<String, Object> masUnit,HttpServletRequest request,HttpServletResponse response);
	
	/***************************MAS UNIT***********************************/
	String addUnits(JSONObject jsondata,HttpServletRequest request,HttpServletResponse response);
	String getAllUnit(JSONObject jsondata,HttpServletRequest request,HttpServletResponse response);
	String getCommandList(HttpServletRequest request,HttpServletResponse response);
	String getUnitTypeList(HttpServletRequest request,HttpServletResponse response);
	String updateUnit(HashMap<String, Object> unitPayload,HttpServletRequest request,HttpServletResponse response);
	String updateStatus(JSONObject jsonObject,HttpServletRequest request,HttpServletResponse response);
	
	/*********************************MAS HOSPITAL***************************************/
	String addMasHospital(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String getUnitNameList(HttpServletRequest request,HttpServletResponse response);
	String getAllHospital(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateHospitalMasterStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateHospitalDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/*************************************MAS RELATION*************************************************************************/
	String getAllRelation(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateRelationDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateRelationStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addRelation(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/****************************************ADD DISPOSAL********************************************************************/
	String addDisposal(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String getAllDisposal(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateDisposalDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateDisposalStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/****************************************MAS APPOINTMENT TYPE*************************************************************/
	String addAppointmentType(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String getAllAppointmentType(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateAppointmentTypeDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateAppointmentTypeStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/****************************************MAS DEPARTMENT*******************************************************************/
	String getAllDepartment(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String getDepartmentTypeList(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addDepartment(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateDepartmentDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateDepartmentStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/****************************************MAS FREQUENCY*********************************************************************/
	String getAllOpdFrequency(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addOpdFrequency(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateOpdFrequencyDetails(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String updateOpdFrequencyStatus(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/************************************************************************************************/
	String getAllEmpanelledHospital(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String addEmpanelledHospital1(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	String addEmpanelledHospital(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);	
	String updateEmpanelledHospital(JSONObject jsonObject,HttpServletRequest request, HttpServletResponse response);
	
	/**************************************MAS IDEAL WEIGHT*************************************************/
	String getAllIdealWeight(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String getAge(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
/**************************************	Phsiotherapy*************************************************/
	
	String getAllPhsiotherapy(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String addPhsiotherapy(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
	String updatePhysiotherapyDetails(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response);
}
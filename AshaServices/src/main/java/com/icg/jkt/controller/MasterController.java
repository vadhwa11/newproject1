package com.icg.jkt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icg.jkt.service.MasterService;

/**
 * Copyright 2019 JK Technosoft Ltd. All rights reserved. Use is subject to
 * license terms. Purpose of the HMS - This is for login.
 * 
 * @author Rajdeo Kumar Create Date: 17/01/2019
 * @version 0.1
 */

@RequestMapping("/v0.1/master")
@RestController
@CrossOrigin
public class MasterController {

	@Autowired
	MasterService masterService;

	//////// Get Master Department List ///////////////////////////////

	@RequestMapping(value = "/getDepartmentList", method = RequestMethod.GET)
	public String departmentList(@RequestBody HashMap<String, String> jsondata, HttpServletRequest request) {
		return masterService.departmentList(jsondata, request);
	}

	////////////////// Get Master ICD List /////////////////////////////

	@RequestMapping(value = "/getICDList", method = RequestMethod.GET)
	public String getICD(@RequestBody HashMap<String, String> jsondata, HttpServletRequest request) {
		return masterService.getICD(jsondata, request);
	}

	/**
	 * @author rajdeo.kumar
	 * @param stateData
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAllStates", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllStates(@RequestBody HashMap<String, String> stateDataPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String stateData = "";
		JSONObject jsonObject = new JSONObject(stateDataPayload);
		stateData = masterService.getAllStates(jsonObject, request, response);
		return stateData;
	}

	
	/*************************************
	 * Mas Command
	 ***************************************************************/
	/**
	 * 
	 * @param Mascommand
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/addCommand", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addCommand(@RequestBody Map<String, Object> command, HttpServletRequest request,
			HttpServletResponse response) {
		String addCmd = "";
		JSONObject json = new JSONObject(command);
		addCmd = masterService.addCommand(json, request, response);
		return addCmd;
	}

	@RequestMapping(value = "/getAllCommand", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllCommand(@RequestBody Map<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		String cmd = "";
		JSONObject jsonObj = new JSONObject(payload);
		cmd = masterService.getAllCommand(jsonObj, request, response);
		return cmd;
	}

	@RequestMapping(value = "/getCommand", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getCommand(@RequestBody HashMap<String, Object> command, HttpServletRequest request) {
		String cmd = "";
		cmd = masterService.getCommand(command, request);
		return cmd;
	}

	@RequestMapping(value = "/updateCommand", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateCommand(@RequestBody HashMap<String, Object> command, HttpServletRequest request,
			HttpServletResponse response) {
		String updateCmd = "";
		updateCmd = masterService.updateCommand(command, request, response);
		return updateCmd;
	}

	@RequestMapping(value = "/statusCommand", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String statusCommand(@RequestBody HashMap<String, Object> command, HttpServletRequest request,
			HttpServletResponse response) {
		String status = "";
		status = masterService.statusCommand(command, request, response);
		return status;
	}

	@RequestMapping(value = "/getCommandTypeList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getCommandTypeList(HttpServletRequest request, HttpServletResponse response) {
		String addCmdTyp = "";
		addCmdTyp = masterService.getCommandTypeList(request, response);
		return addCmdTyp;
	}

	/************************************
	 * UNIT MASTER
	 *********************************************/
	@RequestMapping(value = "/getAllUnit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllUnit(@RequestBody Map<String, Object> unitPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String unit = "";
		JSONObject jsonObj = new JSONObject(unitPayload);
		unit = masterService.getAllUnit(jsonObj, request, response);
		return unit;
	}

	@RequestMapping(value = "/getCommandList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getCommandList(HttpServletRequest request, HttpServletResponse response) {
		String commandList = "";
		commandList = masterService.getCommandList(request, response);
		return commandList;
	}

	@RequestMapping(value = "/addUnit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addUnits(@RequestBody Map<String, Object> unitPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addunit = "";
		JSONObject jsonObject = new JSONObject(unitPayload);
		addunit = masterService.addUnits(jsonObject, request, response);
		return addunit;
	}

	@RequestMapping(value = "/getUnitTypeList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getUnitTypeList(HttpServletRequest request, HttpServletResponse response) {
		String unitTyp = "";
		unitTyp = masterService.getUnitTypeList(request, response);
		return unitTyp;
	}

	@RequestMapping(value = "/updateUnit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateUnit(@RequestBody HashMap<String, Object> unitPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String updateUnit = "";
		updateUnit = masterService.updateUnit(unitPayload, request, response);
		return updateUnit;
	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateStatus(@RequestBody HashMap<String, Object> unitPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String updtStatus = "";
		JSONObject jsonObject = new JSONObject(unitPayload);
		updtStatus = masterService.updateStatus(jsonObject, request, response);
		return updtStatus;
	}

	/*****************************************
	 * MAS HOSPITAL
	 ********************************************************************/

	@RequestMapping(value = "/addMasHospital", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addMasHospital(@RequestBody Map<String, Object> hospitalPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addHospital = "";
		JSONObject jsonObject = new JSONObject(hospitalPayload);
		addHospital = masterService.addMasHospital(jsonObject, request, response);
		return addHospital;
	}

	@RequestMapping(value = "/getUnitNameList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getUnitNameList(HttpServletRequest request, HttpServletResponse response) {
		String unitNameList = "";
		unitNameList = masterService.getUnitNameList(request, response);
		return unitNameList;
	}

	@RequestMapping(value = "/getAllHospital", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllHospital(@RequestBody Map<String, Object> hospitalPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String hospitalList = "";
		JSONObject jsonObject = new JSONObject(hospitalPayload);
		hospitalList = masterService.getAllHospital(jsonObject, request, response);

		return hospitalList;
	}

	@RequestMapping(value = "/updateHospitalMasterStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateHospitalMasterStatus(@RequestBody Map<String, Object> hospitalPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateHospital = "";
		JSONObject jsonObject = new JSONObject(hospitalPayload);
		updateHospital = masterService.updateHospitalMasterStatus(jsonObject, request, response);
		return updateHospital;
	}

	@RequestMapping(value = "/updateHospitalDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateHospitalDetails(@RequestBody Map<String, Object> hospitalPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String updateHospital = "";
		JSONObject jsonObject = new JSONObject(hospitalPayload);
		updateHospital = masterService.updateHospitalDetails(jsonObject, request, response);
		return updateHospital;

	}

	/**********************************************
	 * MAS RELATION
	 *********************************************************************/
	@RequestMapping(value = "/getAllRelation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllRelation(@RequestBody Map<String, Object> masRelation, HttpServletRequest request,
			HttpServletResponse response) {
		String unit = "";
		JSONObject jsonObject = new JSONObject(masRelation);
		unit = masterService.getAllRelation(jsonObject, request, response);
		return unit;
	}

	@RequestMapping(value = "/updateRelationDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateRelationDetails(@RequestBody HashMap<String, Object> masRelation, HttpServletRequest request,
			HttpServletResponse response) {
		String updateRel = "";
		JSONObject jsonObject = new JSONObject(masRelation);
		updateRel = masterService.updateRelationDetails(jsonObject, request, response);
		return updateRel;
	}

	@RequestMapping(value = "/updateRelationStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateRelationStatus(@RequestBody HashMap<String, Object> masRelation, HttpServletRequest request,
			HttpServletResponse response) {
		String updateRelStatus = "";
		JSONObject jsonObject = new JSONObject(masRelation);
		updateRelStatus = masterService.updateRelationStatus(jsonObject, request, response);
		return updateRelStatus;
	}

	@RequestMapping(value = "/addRelation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addRelation(@RequestBody Map<String, Object> relationPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addRelation = "";
		JSONObject jsonObject = new JSONObject(relationPayload);
		addRelation = masterService.addRelation(jsonObject, request, response);
		return addRelation;
	}

	/********************************************
	 * MAS DISPOSAL
	 ************************************************************/
	@RequestMapping(value = "/addDisposal", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addDisposal(@RequestBody Map<String, Object> disposalPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addDisposl = "";
		JSONObject jsonObject = new JSONObject(disposalPayload);
		addDisposl = masterService.addDisposal(jsonObject, request, response);
		return addDisposl;
	}

	@RequestMapping(value = "/getAllDisposal", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllDisposal(@RequestBody Map<String, Object> masDisposal, HttpServletRequest request,
			HttpServletResponse response) {
		String alldisposal = "";
		JSONObject jsonObject = new JSONObject(masDisposal);
		alldisposal = masterService.getAllDisposal(jsonObject, request, response);
		return alldisposal;
	}

	@RequestMapping(value = "/updateDisposalDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateDisposalDetails(@RequestBody HashMap<String, Object> masDisposal, HttpServletRequest request,
			HttpServletResponse response) {
		String updateDispo = "";
		JSONObject jsonObject = new JSONObject(masDisposal);
		updateDispo = masterService.updateDisposalDetails(jsonObject, request, response);
		return updateDispo;
	}

	@RequestMapping(value = "/updateDisposalStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateDisposalStatus(@RequestBody HashMap<String, Object> masDisposal, HttpServletRequest request,
			HttpServletResponse response) {
		String updateDispoStatus = "";
		JSONObject jsonObject = new JSONObject(masDisposal);
		updateDispoStatus = masterService.updateDisposalStatus(jsonObject, request, response);
		return updateDispoStatus;
	}

	/****************************************
	 * MAS APPOINTMENT TYPE
	 ***********************************************************/

	@RequestMapping(value = "/addAppointmentType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addAppointmentType(@RequestBody Map<String, Object> appointmentTypePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String addAppointment = "";
		JSONObject jsonObject = new JSONObject(appointmentTypePayload);
		addAppointment = masterService.addAppointmentType(jsonObject, request, response);
		return addAppointment;
	}

	@RequestMapping(value = "/getAllAppointmentType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllAppointmentType(@RequestBody Map<String, Object> appointmentTypePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String alldisposal = "";
		JSONObject jsonObject = new JSONObject(appointmentTypePayload);
		alldisposal = masterService.getAllAppointmentType(jsonObject, request, response);
		return alldisposal;
	}

	@RequestMapping(value = "/updateAppointmentTypeDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateAppointmentTypeDetails(@RequestBody HashMap<String, Object> appointmentTypePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateDispo = "";
		JSONObject jsonObject = new JSONObject(appointmentTypePayload);
		updateDispo = masterService.updateAppointmentTypeDetails(jsonObject, request, response);
		return updateDispo;
	}

	@RequestMapping(value = "/updateAppointmentTypeStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateAppointmentTypeStatus(@RequestBody HashMap<String, Object> appointmentTypePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateDispoStatus = "";
		JSONObject jsonObject = new JSONObject(appointmentTypePayload);
		updateDispoStatus = masterService.updateAppointmentTypeStatus(jsonObject, request, response);
		return updateDispoStatus;
	}

	/*****************************
	 * MAS DEPARTMENT
	 *****************************************************/

	@RequestMapping(value = "/getAllDepartment", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllDepartment(@RequestBody Map<String, Object> departmentPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allDept = "";
		JSONObject jsonObject = new JSONObject(departmentPayload);
		allDept = masterService.getAllDepartment(jsonObject, request, response);
		return allDept;
	}

	@RequestMapping(value = "/getDepartmentTypeList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getDepartmentTypeList(@RequestBody Map<String, Object> departmentPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String deptType = "";
		JSONObject jsonObject = new JSONObject(departmentPayload);
		deptType = masterService.getDepartmentTypeList(jsonObject, request, response);
		return deptType;
	}

	@RequestMapping(value = "/addDepartment", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addDepartment(@RequestBody Map<String, Object> departmentPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addDepart = "";
		JSONObject jsonObject = new JSONObject(departmentPayload);
		addDepart = masterService.addDepartment(jsonObject, request, response);
		return addDepart;
	}

	@RequestMapping(value = "/updateDepartmentDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateDepartmentDetails(@RequestBody HashMap<String, Object> departmentPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateDepart = "";
		JSONObject jsonObject = new JSONObject(departmentPayload);
		updateDepart = masterService.updateDepartmentDetails(jsonObject, request, response);
		return updateDepart;
	}

	@RequestMapping(value = "/updateDepartmentStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateDepartmentStatus(@RequestBody HashMap<String, Object> departmentPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateDeptStatus = "";
		JSONObject jsonObject = new JSONObject(departmentPayload);
		updateDeptStatus = masterService.updateDepartmentStatus(jsonObject, request, response);
		return updateDeptStatus;
	}

	/*********************************************
	 * MAS FREQUENCY
	 ********************************************************/
	@RequestMapping(value = "/getAllOpdFrequency", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllOpdFrequency(@RequestBody Map<String, Object> frequencyPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allDept = "";
		JSONObject jsonObject = new JSONObject(frequencyPayload);
		allDept = masterService.getAllOpdFrequency(jsonObject, request, response);
		return allDept;
	}

	@RequestMapping(value = "/addOpdFrequency", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addOpdFrequency(@RequestBody Map<String, Object> frequencyPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addDepart = "";
		JSONObject jsonObject = new JSONObject(frequencyPayload);
		addDepart = masterService.addOpdFrequency(jsonObject, request, response);
		return addDepart;
	}

	@RequestMapping(value = "/updateOpdFrequencyDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateOpdFrequencyDetails(@RequestBody HashMap<String, Object> frequencyPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateDepart = "";
		JSONObject jsonObject = new JSONObject(frequencyPayload);
		updateDepart = masterService.updateOpdFrequencyDetails(jsonObject, request, response);
		return updateDepart;
	}

	@RequestMapping(value = "/updateOpdFrequencyStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateOpdFrequencyStatus(@RequestBody HashMap<String, Object> frequencyPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateDeptStatus = "";
		JSONObject jsonObject = new JSONObject(frequencyPayload);
		updateDeptStatus = masterService.updateOpdFrequencyStatus(jsonObject, request, response);
		return updateDeptStatus;
	}

	/********************************
	 * MAS EMPANELLED Hospital Master
	 ********************************************************/

	@RequestMapping(value = "/getAllEmpanelledHospital", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllEmpanelledHospital(@RequestBody Map<String, Object> impanneledHospPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String allempHosp = "";
		JSONObject jsonObject = new JSONObject(impanneledHospPayload);
		allempHosp = masterService.getAllEmpanelledHospital(jsonObject, request, response);
		return allempHosp;
	}

	@RequestMapping(value = "/addEmpanelledHospital", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addEmpanelledHospital(@RequestBody Map<String, Object> impanneledHospPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String allempHosp = "";
		JSONObject jsonObject = new JSONObject(impanneledHospPayload);
		allempHosp = masterService.addEmpanelledHospital(jsonObject, request, response);
		return allempHosp;
	}

	@RequestMapping(value = "/updateEmpanelledHospital", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateEmpanelledHospital(@RequestBody HashMap<String, Object> impanneledHospPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateDepart = "";
		JSONObject jsonObject = new JSONObject(impanneledHospPayload);
		updateDepart = masterService.updateEmpanelledHospital(jsonObject, request, response);
		return updateDepart;
	}

	/**********************************
	 * Phsiotherapy/Procedure
	 ******************************************************/

	@RequestMapping(value = "/getAllPhysiotherapy", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllPhysiotherapy(@RequestBody Map<String, Object> medExamPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String physiotherapyList = "";
		JSONObject jsonObject = new JSONObject(medExamPayload);
		physiotherapyList = masterService.getAllPhsiotherapy(jsonObject, request, response);

		return physiotherapyList;
	}

	@RequestMapping(value = "/addPhsiotherapyCare", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addPhsiotherapyCare(@RequestBody Map<String, Object> PhsiotherapyPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addPhsiotherapy = "";
		JSONObject jsonObject = new JSONObject(PhsiotherapyPayload);
		addPhsiotherapy = masterService.addPhsiotherapy(jsonObject, request, response);
		return addPhsiotherapy;
	}

	@RequestMapping(value = "/updatePhysiotherapyDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updatePhysiotherapyDetails(@RequestBody HashMap<String, Object> physiotherapyPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updatePhysiotherapyDetails = "";
		JSONObject jsonObject = new JSONObject(physiotherapyPayload);
		updatePhysiotherapyDetails = masterService.updatePhysiotherapyDetails(jsonObject, request, response);
		return updatePhysiotherapyDetails;
	}

	/**********************************
	 * MAS IDEAL WEIGHT
	 ******************************************************/

	@RequestMapping(value = "/getAllIdealWeight", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllIdealWeight(@RequestBody Map<String, Object> idealweightPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String getIdealWeight = "";
		JSONObject jsonObject = new JSONObject(idealweightPayload);
		getIdealWeight = masterService.getAllIdealWeight(jsonObject, request, response);
		return getIdealWeight;
	}

	@RequestMapping(value = "/getAge", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAge(@RequestBody Map<String, Object> idealweightPayload, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(idealweightPayload);
		String resObject = masterService.getAge(jsonObject, request, response);
		return resObject;
	}

	@RequestMapping(value="/updateIdealWeight", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public String updateIdealWeight(@RequestBody Map<String, Object> idealweightPayload, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(idealweightPayload);
		return masterService.updateIdealWeight(jsonObject, request, response);
	}
	
	@RequestMapping(value="/addIdealWeight", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public String addIdealWeight(@RequestBody Map<String, Object> idealweightPayload, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(idealweightPayload);
		return masterService.addIdealWeight(jsonObject, request, response);
	}

	/*************************************
	 * Mas Service Type
	 ***************************************************************/
	/**
	 * 
	 * @param MasServiceType
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/getAllServiceType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllServiceType(@RequestBody Map<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		String sType = "";
		JSONObject jsonObj = new JSONObject(payload);
		sType = masterService.getAllServiceType(jsonObj, request, response);
		return sType;
	}

	@RequestMapping(value = "/updateServiceType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateServiceType(@RequestBody HashMap<String, Object> serviceType, HttpServletRequest request,
			HttpServletResponse response) {
		String updateServiceType = "";
		updateServiceType = masterService.updateServiceType(serviceType, request, response);
		return updateServiceType;
	}

	@RequestMapping(value = "/addServiceType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addServiceType(@RequestBody Map<String, Object> stype, HttpServletRequest request,
			HttpServletResponse response) {
		String addStype = "";
		JSONObject json = new JSONObject(stype);
		addStype = masterService.addServiceType(json, request, response);
		return addStype;
	}

	@RequestMapping(value = "/statusServiceType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String statusServiceType(@RequestBody HashMap<String, Object> serviceType, HttpServletRequest request,
			HttpServletResponse response) {
		String status = "";
		status = masterService.statusServiceType(serviceType, request, response);
		return status;
	}

	/***********************************
	 * Rank Master
	 *************************************/
	/**
	 * @param Masrank
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/addRank", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addRank(@RequestBody HashMap<String, Object> rank, HttpServletRequest request,
			HttpServletResponse response) {
		String addRank = "";
		System.out.println("API");
		JSONObject json = new JSONObject(rank);
		System.out.println("API json: " + json);
		addRank = masterService.addRank(json, request, response);
		return addRank;
	}

	@RequestMapping(value = "/getAllRank", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllRank(@RequestBody Map<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		String rank = "";
		JSONObject jsonObj = new JSONObject(payload);
		rank = masterService.getAllRank(jsonObj, request, response);
		System.out.println("rank+rank :: "+ rank);
		return rank;
	}

	@RequestMapping(value = "/getRank", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getRank(@RequestBody HashMap<String, Object> rank, HttpServletRequest request) {
		String rank1 = "";
		rank1 = masterService.getRank(rank, request);
		return rank1;
	}

	@RequestMapping(value = "/updateRank", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateRank(@RequestBody HashMap<String, Object> rank, HttpServletRequest request,
			HttpServletResponse response) {
		String updateRank = "";
		System.out.println("API updateRank Rank :: " + rank);
		updateRank = masterService.updateRank(rank, request, response);
		return updateRank;
	}

	@RequestMapping(value = "/statusRank", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String statusRank(@RequestBody HashMap<String, Object> rank, HttpServletRequest request,
			HttpServletResponse response) {
		String status = "";
		status = masterService.statusRank(rank, request, response);
		return status;
	}

	@RequestMapping(value = "/getEmployeeCategoryList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getEmployeeCategoryList(HttpServletRequest request, HttpServletResponse response) {
		String addEmployeeCategory = "";

		addEmployeeCategory = masterService.getEmployeeCategoryList(request, response);
		return addEmployeeCategory;
	}

	/*************************************************
	 * TRADE MASTER
	 *******************************************************************/

	@RequestMapping(value = "/addTrade", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addTrade(@RequestBody HashMap<String, Object> trade, HttpServletRequest request,
			HttpServletResponse response) {
		String addTrade = "";
		JSONObject json = new JSONObject(trade);
		System.out.println("API json: " + json);
		addTrade = masterService.addTrade(json, request, response);
		return addTrade;
	}

	@RequestMapping(value = "/getAllTrade", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllTrade(@RequestBody Map<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		String trade = "";
		JSONObject jsonObj = new JSONObject(payload);
		trade = masterService.getAllTrade(jsonObj, request, response);
		return trade;
	}
	
	@RequestMapping(value = "/getTrade", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getTrade(@RequestBody HashMap<String, Object> trade, HttpServletRequest request) {
		String trade1 = "";
		trade1 = masterService.getTrade(trade, request);
		System.out.println("API getTrade details :: " + trade1);
		return trade1;
	}

	@RequestMapping(value = "/updateTrade", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateTrade(@RequestBody HashMap<String, Object> trade, HttpServletRequest request,
			HttpServletResponse response) {
		String updateTrade = "";
		updateTrade = masterService.updateTrade(trade, request, response);
		return updateTrade;
	}

	@RequestMapping(value = "/statusTrade", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String statusTrade(@RequestBody HashMap<String, Object> trade, HttpServletRequest request,
			HttpServletResponse response) {
		String status = "";
		System.out.println("API updateTrade Trade :: " + trade);
		status = masterService.statusTrade(trade, request, response);
		return status;
	}

	@RequestMapping(value = "/getServiceTypeList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getServiceTypeList(HttpServletRequest request, HttpServletResponse response) {
		String addServiceType = "";

		addServiceType = masterService.getServiceTypeList(request, response);
		return addServiceType;
	}
	
	/*********************************************
	 * MAS RELIGION
	 ********************************************************/
	@RequestMapping(value = "/getAllReligion", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllReligion(@RequestBody Map<String, Object> religionPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allDept = "";
		JSONObject jsonObject = new JSONObject(religionPayload);
		allDept = masterService.getAllReligion(jsonObject, request, response);
		return allDept;
	}
	
	@RequestMapping(value = "/addReligion", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addReligion(@RequestBody Map<String, Object> religionPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addDepart = "";
		JSONObject jsonObject = new JSONObject(religionPayload);
		addDepart = masterService.addReligion(jsonObject, request, response);
		return addDepart;
	}

	@RequestMapping(value = "/updateReligionDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateReligionDetails(@RequestBody HashMap<String, Object> religionPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateDepart = "";
		JSONObject jsonObject = new JSONObject(religionPayload);
		updateDepart = masterService.updateReligionDetails(jsonObject, request, response);
		return updateDepart;
	}

	@RequestMapping(value = "/updateReligionStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateReligionStatus(@RequestBody HashMap<String, Object> religionPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateDeptStatus = "";
		JSONObject jsonObject = new JSONObject(religionPayload);
		updateDeptStatus = masterService.updateReligionStatus(jsonObject, request, response);
		return updateDeptStatus;
	}
	
	/*********************************************
	 * MAS MARITAL STATUS
	 ********************************************************/
	@RequestMapping(value = "/getAllMaritalStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllMaritalStatus(@RequestBody Map<String, Object> maritalStatusPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allMaritalStatus = "";
		JSONObject jsonObject = new JSONObject(maritalStatusPayload);
		allMaritalStatus = masterService.getAllMaritalStatus(jsonObject, request, response);
		return allMaritalStatus;
	}
	
	@RequestMapping(value = "/addMaritalStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addMaritalStatus(@RequestBody Map<String, Object> maritalStatusPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addMaritalStatus = "";
		JSONObject jsonObject = new JSONObject(maritalStatusPayload);
		addMaritalStatus = masterService.addMaritalStatus(jsonObject, request, response);
		return addMaritalStatus;
	}
	
	@RequestMapping(value = "/updateMaritalStatusDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateMaritalStatusDetails(@RequestBody HashMap<String, Object> maritalStatusPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateMaritalStatus = "";
		JSONObject jsonObject = new JSONObject(maritalStatusPayload);
		updateMaritalStatus = masterService.updateMaritalStatusDetails(jsonObject, request, response);
		return updateMaritalStatus;
	}

	@RequestMapping(value = "/updateMaritalStatusStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateMaritalStatusStatus(@RequestBody HashMap<String, Object> maritalStatusPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateMaritalStatusStatus = "";
		JSONObject jsonObject = new JSONObject(maritalStatusPayload);
		updateMaritalStatusStatus = masterService.updateMaritalStatusStatus(jsonObject, request, response);
		System.out.println("updateMaritalStatusStatus :: "+ updateMaritalStatusStatus);
		return updateMaritalStatusStatus;
	}

	/*********************************************
	 * MAS Employee Category
	 ********************************************************/
	@RequestMapping(value = "/getAllEmployeeCategory", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllEmployeeCategory(@RequestBody Map<String, Object> employeeCategoryPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allEmployeeCategory = "";
		JSONObject jsonObject = new JSONObject(employeeCategoryPayload);
		allEmployeeCategory = masterService.getAllEmployeeCategory(jsonObject, request, response);
		return allEmployeeCategory;
	}
	
	@RequestMapping(value = "/addEmployeeCategory", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addEmployeeCategory(@RequestBody Map<String, Object> employeeCategoryPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addEmployeeCategory = "";
		JSONObject jsonObject = new JSONObject(employeeCategoryPayload);
		addEmployeeCategory = masterService.addEmployeeCategory(jsonObject, request, response);
		return addEmployeeCategory;
	}

	@RequestMapping(value = "/updateEmployeeCategoryDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateEmployeeCategoryDetails(@RequestBody HashMap<String, Object> employeeCategoryPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateEmployeeCategory = "";
		JSONObject jsonObject = new JSONObject(employeeCategoryPayload);
		updateEmployeeCategory = masterService.updateEmployeeCategoryDetails(jsonObject, request, response);
		return updateEmployeeCategory;
	}

	@RequestMapping(value = "/updateEmployeeCategoryStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateEmployeeCategoryStatus(@RequestBody HashMap<String, Object> employeeCategoryPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateEmployeeCategoryStatus = "";
		JSONObject jsonObject = new JSONObject(employeeCategoryPayload);
		updateEmployeeCategoryStatus = masterService.updateEmployeeCategoryStatus(jsonObject, request, response);
		return updateEmployeeCategoryStatus;
	}
	
	/*********************************************
	 * MAS Administrative Sex
	 ********************************************************/
	@RequestMapping(value = "/getAllAdministrativeSex", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllAdministrativeSex(@RequestBody Map<String, Object> administrativeSexPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allAdministrativeSex = "";
		JSONObject jsonObject = new JSONObject(administrativeSexPayload);
		allAdministrativeSex = masterService.getAllAdministrativeSex(jsonObject, request, response);
		return allAdministrativeSex;
	}
	
	@RequestMapping(value = "/addAdministrativeSex", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addAdministrativeSex(@RequestBody Map<String, Object> administrativeSexPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addAdministrativeSex = "";
		JSONObject jsonObject = new JSONObject(administrativeSexPayload);
		addAdministrativeSex = masterService.addAdministrativeSex(jsonObject, request, response);
		return addAdministrativeSex;
	}

	@RequestMapping(value = "/updateAdministrativeSexDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateAdministrativeSexDetails(@RequestBody HashMap<String, Object> administrativeSexPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateAdministrativeSex = "";
		JSONObject jsonObject = new JSONObject(administrativeSexPayload);
		updateAdministrativeSex = masterService.updateAdministrativeSexDetails(jsonObject, request, response);
		return updateAdministrativeSex;
	}

	@RequestMapping(value = "/updateAdministrativeSexStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateAdministrativeSexStatus(@RequestBody HashMap<String, Object> administrativeSexPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateAdministrativeSexStatus = "";
		JSONObject jsonObject = new JSONObject(administrativeSexPayload);
		updateAdministrativeSexStatus = masterService.updateAdministrativeSexStatus(jsonObject, request, response);
		return updateAdministrativeSexStatus;
	}
	
	/*********************************************
	 * MAS MedicalCategory
	 ********************************************************/
	@RequestMapping(value = "/getAllMedicalCategory", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllMedicalCategory(@RequestBody Map<String, Object> medicalCategoryPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allMedicalCategory = "";
		JSONObject jsonObject = new JSONObject(medicalCategoryPayload);
		allMedicalCategory = masterService.getAllMedicalCategory(jsonObject, request, response);
		System.out.println("medicalCategoryPayload+medicalCategoryPayload :: "+ medicalCategoryPayload);
		return allMedicalCategory;
	}
	
	@RequestMapping(value = "/addMedicalCategory", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addMedicalCategory(@RequestBody Map<String, Object> medicalCategoryPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addMedicalCategory = "";
		JSONObject jsonObject = new JSONObject(medicalCategoryPayload);
		addMedicalCategory = masterService.addMedicalCategory(jsonObject, request, response);
		return addMedicalCategory;
	}

	@RequestMapping(value = "/updateMedicalCategoryDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateMedicalCategoryDetails(@RequestBody HashMap<String, Object> medicalCategoryPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateMedicalCategory = "";
		JSONObject jsonObject = new JSONObject(medicalCategoryPayload);
		updateMedicalCategory = masterService.updateMedicalCategoryDetails(jsonObject, request, response);
		return updateMedicalCategory;
	}

	@RequestMapping(value = "/updateMedicalCategoryStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateMedicalCategoryStatus(@RequestBody HashMap<String, Object> medicalCategoryPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateMedicalCategoryStatus = "";
		JSONObject jsonObject = new JSONObject(medicalCategoryPayload);
		updateMedicalCategoryStatus = masterService.updateMedicalCategoryStatus(jsonObject, request, response);
		return updateMedicalCategoryStatus;
	}
	
	/*********************************************
	 * MAS BLOODGROUP
	 ********************************************************/
	@RequestMapping(value = "/getAllBloodGroup", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllBloodGroup(@RequestBody Map<String, Object> bloodGroupPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allBloodGroup = "";
		JSONObject jsonObject = new JSONObject(bloodGroupPayload);
		allBloodGroup = masterService.getAllBloodGroup(jsonObject, request, response);
		return allBloodGroup;
	}
	
	@RequestMapping(value = "/addBloodGroup", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addBloodGroup(@RequestBody Map<String, Object> bloodGroupPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addBloodGroup = "";
		JSONObject jsonObject = new JSONObject(bloodGroupPayload);
		addBloodGroup = masterService.addBloodGroup(jsonObject, request, response);
		return addBloodGroup;
	}

	@RequestMapping(value = "/updateBloodGroupDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateBloodGroupDetails(@RequestBody HashMap<String, Object> bloodGroupPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateBloodGroup = "";
		JSONObject jsonObject = new JSONObject(bloodGroupPayload);
		updateBloodGroup = masterService.updateBloodGroupDetails(jsonObject, request, response);
		return updateBloodGroup;
	}

	@RequestMapping(value = "/updateBloodGroupStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateBloodGroupStatus(@RequestBody HashMap<String, Object> bloodGroupPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateBloodGroupStatus = "";
		JSONObject jsonObject = new JSONObject(bloodGroupPayload);
		updateBloodGroupStatus = masterService.updateBloodGroupStatus(jsonObject, request, response);
		return updateBloodGroupStatus;
	}
	

	/*********************************************
	 * MAS Sample
	 ********************************************************/
	@RequestMapping(value = "/getAllSample", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllSample(@RequestBody Map<String, Object> samplePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allSample = "";
		JSONObject jsonObject = new JSONObject(samplePayload);
		allSample = masterService.getAllSample(jsonObject, request, response);
		System.out.println("responseObj_SAMPLELIST_Service"+allSample);
		return allSample;
	}
	
	@RequestMapping(value = "/addSample", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addSample(@RequestBody Map<String, Object> samplePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addSample = "";
		JSONObject jsonObject = new JSONObject(samplePayload);
		addSample = masterService.addSample(jsonObject, request, response);
		return addSample;
	}

	@RequestMapping(value = "/updateSampleDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateSampleDetails(@RequestBody HashMap<String, Object> samplePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateSample = "";
		JSONObject jsonObject = new JSONObject(samplePayload);
		updateSample = masterService.updateSampleDetails(jsonObject, request, response);
		return updateSample;
	}

	@RequestMapping(value = "/updateSampleStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateSampleStatus(@RequestBody HashMap<String, Object> samplePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateSampleStatus = "";
		JSONObject jsonObject = new JSONObject(samplePayload);
		updateSampleStatus = masterService.updateSampleStatus(jsonObject, request, response);
		return updateSampleStatus;
	}
	
	/*********************************************
	 * MAS UOM
	 ********************************************************/
	@RequestMapping(value = "/getAllUOM", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllUOM(@RequestBody Map<String, Object> UOMPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allUOM = "";
		JSONObject jsonObject = new JSONObject(UOMPayload);
		allUOM = masterService.getAllUOM(jsonObject, request, response);
		return allUOM;
	}
	
	@RequestMapping(value = "/addUOM", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addUOM(@RequestBody Map<String, Object> UOMPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addUOM = "";
		JSONObject jsonObject = new JSONObject(UOMPayload);
		addUOM = masterService.addUOM(jsonObject, request, response);
		return addUOM;
	}

	@RequestMapping(value = "/updateUOMDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateUOMDetails(@RequestBody HashMap<String, Object> UOMPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateUOM = "";
		JSONObject jsonObject = new JSONObject(UOMPayload);
		updateUOM = masterService.updateUOMDetails(jsonObject, request, response);
		return updateUOM;
	}

	@RequestMapping(value = "/updateUOMStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateUOMStatus(@RequestBody HashMap<String, Object> UOMPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateUOMStatus = "";
		JSONObject jsonObject = new JSONObject(UOMPayload);
		updateUOMStatus = masterService.updateUOMStatus(jsonObject, request, response);
		return updateUOMStatus;
	}
	
	/*********************************************
	 * Item Unit (MasStoreUnit)
	 ********************************************************/
	@RequestMapping(value = "/getAllItemUnit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllItemUnit(@RequestBody Map<String, Object> itemUnitPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allItemUnit = "";
		JSONObject jsonObject = new JSONObject(itemUnitPayload);
		allItemUnit = masterService.getAllItemUnit(jsonObject, request, response);
		return allItemUnit;
	}
	
	@RequestMapping(value = "/addItemUnit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addItemUnit(@RequestBody Map<String, Object> itemUnitPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addItemUnit = "";
		JSONObject jsonObject = new JSONObject(itemUnitPayload);
		addItemUnit = masterService.addItemUnit(jsonObject, request, response);
		return addItemUnit;
	}

	@RequestMapping(value = "/updateItemUnitDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateItemUnitDetails(@RequestBody HashMap<String, Object> itemUnitPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateItemUnit = "";
		JSONObject jsonObject = new JSONObject(itemUnitPayload);
		updateItemUnit = masterService.updateItemUnitDetails(jsonObject, request, response);
		return updateItemUnit;
	}

	@RequestMapping(value = "/updateItemUnitStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateItemUnitStatus(@RequestBody HashMap<String, Object> itemUnitPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateItemUnitStatus = "";
		JSONObject jsonObject = new JSONObject(itemUnitPayload);
		updateItemUnitStatus = masterService.updateItemUnitStatus(jsonObject, request, response);
		return updateItemUnitStatus;
	}
	
	/*********************************************
	 * Mas MainChargecode
	 ********************************************************/
	@RequestMapping(value = "/getAllMainChargecode", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllMainChargecode(@RequestBody Map<String, Object> mainChargecodePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allMainChargecode = "";
		JSONObject jsonObject = new JSONObject(mainChargecodePayload);
		allMainChargecode = masterService.getAllMainChargecode(jsonObject, request, response);
		return allMainChargecode;
	}
	
	@RequestMapping(value = "/addMainChargecode", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addMainChargecode(@RequestBody Map<String, Object> mainChargecodePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addMainChargecode = "";
		JSONObject jsonObject = new JSONObject(mainChargecodePayload);
		addMainChargecode = masterService.addMainChargecode(jsonObject, request, response);
		return addMainChargecode;
	}

	@RequestMapping(value = "/updateMainChargecodeDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateMainChargecodeDetails(@RequestBody HashMap<String, Object> mainChargecode,
			HttpServletRequest request, HttpServletResponse response) {
		String updateMainChargecode = "";
		JSONObject jsonObject = new JSONObject(mainChargecode);
		updateMainChargecode = masterService.updateMainChargecodeDetails(mainChargecode, request, response);
		return updateMainChargecode;
	}

	@RequestMapping(value = "/updateMainChargecodeStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateMainChargecodeStatus(@RequestBody HashMap<String, Object> mainChargecode,
			HttpServletRequest request, HttpServletResponse response) {
		String updateMainChargecodeStatus = "";
		JSONObject jsonObject = new JSONObject(mainChargecode);
		updateMainChargecodeStatus = masterService.updateMainChargecodeStatus(mainChargecode, request, response);
		return updateMainChargecodeStatus;
	}
	
	@RequestMapping(value = "/getDepartmentList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getDepartmentList(HttpServletRequest request, HttpServletResponse response) {
		String getDepartment = "";
		getDepartment = masterService.getDepartmentList(request, response);
		return getDepartment;
	}
	
	/*********************************************
	 * Users
	 ********************************************************/
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllUsers(@RequestBody Map<String, Object> usersPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allUsers = "";
		JSONObject jsonObject = new JSONObject(usersPayload);
		allUsers = masterService.getAllUsers(jsonObject, request, response);
		return allUsers;
	}
	
	@RequestMapping(value = "/addUsers", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addUsers(@RequestBody Map<String, Object> usersPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addUsers = "";
		JSONObject jsonObject = new JSONObject(usersPayload);
		addUsers = masterService.addUsers(jsonObject, request, response);
		return addUsers;
	}

	@RequestMapping(value = "/updateUsersDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateUsersDetails(@RequestBody HashMap<String, Object> users,
			HttpServletRequest request, HttpServletResponse response) {
		String updateUsers = "";
		JSONObject jsonObject = new JSONObject(users);
		updateUsers = masterService.updateUsersDetails(users, request, response);
		return updateUsers;
	}

	@RequestMapping(value = "/updateUsersStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateUsersStatus(@RequestBody HashMap<String, Object> users,
			HttpServletRequest request, HttpServletResponse response) {
		String updateUsersStatus = "";
		JSONObject jsonObject = new JSONObject(users);
		updateUsersStatus = masterService.updateUsersStatus(users, request, response);
		return updateUsersStatus;
	}
	
	@RequestMapping(value = "/getHospitalList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getHospitalList(HttpServletRequest request, HttpServletResponse response) {
		String addHospital = "";
		addHospital = masterService.getHospitalList(request, response);
		return addHospital;
	}
	
	
	/*********************************************
	 * MAS Role
	 ********************************************************/
	@RequestMapping(value = "/getAllRole", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllRole(@RequestBody Map<String, Object> rolePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allRole = "";
		JSONObject jsonObject = new JSONObject(rolePayload);
		allRole = masterService.getAllRole(jsonObject, request, response);
		return allRole;
	}
	
	@RequestMapping(value = "/addRole", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addRole(@RequestBody Map<String, Object> rolePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addRole = "";
		JSONObject jsonObject = new JSONObject(rolePayload);
		addRole = masterService.addRole(jsonObject, request, response);
		return addRole;
	}

	@RequestMapping(value = "/updateRoleDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateRoleDetails(@RequestBody HashMap<String, Object> rolePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateRole = "";
		JSONObject jsonObject = new JSONObject(rolePayload);
		updateRole = masterService.updateRoleDetails(jsonObject, request, response);
		return updateRole;
	}

	@RequestMapping(value = "/updateRoleStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateRoleStatus(@RequestBody HashMap<String, Object> rolePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateRoleStatus = "";
		JSONObject jsonObject = new JSONObject(rolePayload);
		updateRoleStatus = masterService.updateRoleStatus(jsonObject, request, response);
		return updateRoleStatus;
	}
	
	/*********************************************
	 * Range
	 ********************************************************/
	@RequestMapping(value = "/getAllRange", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllRange(@RequestBody Map<String, Object> rangePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allRange = "";
		JSONObject jsonObject = new JSONObject(rangePayload);
		allRange = masterService.getAllRange(jsonObject, request, response);
		return allRange;
	}
	
	@RequestMapping(value = "/addRange", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addRange(@RequestBody Map<String, Object> rangePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addRange = "";
		JSONObject jsonObject = new JSONObject(rangePayload);
		addRange = masterService.addRange(jsonObject, request, response);
		return addRange;
	}

	@RequestMapping(value = "/updateRangeDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateRangeDetails(@RequestBody HashMap<String, Object> range,
			HttpServletRequest request, HttpServletResponse response) {
		String updateRange = "";
		JSONObject jsonObject = new JSONObject(range);
		updateRange = masterService.updateRangeDetails(range, request, response);
		return updateRange;
	}

	@RequestMapping(value = "/updateRangeStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateRangeStatus(@RequestBody HashMap<String, Object> range,
			HttpServletRequest request, HttpServletResponse response) {
		String updateRangeStatus = "";
		JSONObject jsonObject = new JSONObject(range);
		updateRangeStatus = masterService.updateRangeStatus(range, request, response);
		return updateRangeStatus;
	}
	
	@RequestMapping(value = "/getGenderList", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getGenderList(HttpServletRequest request, HttpServletResponse response) {
		String addGender = "";
		addGender = masterService.getGenderList(request, response);
		return addGender;
	}
	
	@RequestMapping(value="/getMasRange", method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE }, consumes={ MediaType.APPLICATION_JSON_VALUE})
	public String getMasRange(@RequestBody HashMap<String, Object> requestPayload, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject(requestPayload);
		return masterService.getMasRange(jsonObject, request, response);
	}
	
	/*********************************************
	 * MAS StoreGroup
	 ********************************************************/
	@RequestMapping(value = "/getAllStoreGroup", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllStoreGroup(@RequestBody Map<String, Object> storeGroupPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allStoreGroup = "";
		JSONObject jsonObject = new JSONObject(storeGroupPayload);
		allStoreGroup = masterService.getAllStoreGroup(jsonObject, request, response);
		return allStoreGroup;
	}
	
	@RequestMapping(value = "/addStoreGroup", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addStoreGroup(@RequestBody Map<String, Object> storeGroupPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addStoreGroup = "";
		JSONObject jsonObject = new JSONObject(storeGroupPayload);
		addStoreGroup = masterService.addStoreGroup(jsonObject, request, response);
		return addStoreGroup;
	}

	@RequestMapping(value = "/updateStoreGroup", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateStoreGroup(@RequestBody HashMap<String, Object> storeGroupPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateStoreGroup = "";
		JSONObject jsonObject = new JSONObject(storeGroupPayload);
		updateStoreGroup = masterService.updateStoreGroup(jsonObject, request, response);
		return updateStoreGroup;
	}

	@RequestMapping(value = "/updateStoreGroupStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateStoreGroupStatus(@RequestBody HashMap<String, Object> storeGroupPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateStoreGroupStatus = "";
		JSONObject jsonObject = new JSONObject(storeGroupPayload);
		updateStoreGroupStatus = masterService.updateStoreGroupStatus(jsonObject, request, response);
		return updateStoreGroupStatus;
	}
	
	/*********************************************
	 * MAS ItemType
	 ********************************************************/
	@RequestMapping(value = "/getAllItemType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllItemType(@RequestBody Map<String, Object> itemTypePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String allItemType = "";
		JSONObject jsonObject = new JSONObject(itemTypePayload);
		allItemType= masterService.getAllItemType(jsonObject, request, response);
		return allItemType;
	}
	
	@RequestMapping(value = "/addItemType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addItemType(@RequestBody Map<String, Object> itemTypePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String addItemType = "";
		JSONObject jsonObject = new JSONObject(itemTypePayload);
		addItemType = masterService.addItemType(jsonObject, request, response);
		return addItemType;
	}

	@RequestMapping(value = "/updateItemType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateItemType(@RequestBody HashMap<String, Object> itemTypePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateItemType = "";
		JSONObject jsonObject = new JSONObject(itemTypePayload);
		updateItemType = masterService.updateItemType(jsonObject, request, response);
		return updateItemType;
	}

	@RequestMapping(value = "/updateItemTypeStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateItemTypeStatus(@RequestBody HashMap<String, Object> itemTypePayload,
			HttpServletRequest request, HttpServletResponse response) {
		String updateItemTypeStatus = "";
		JSONObject jsonObject = new JSONObject(itemTypePayload);
		updateItemTypeStatus = masterService.updateItemTypeStatus(jsonObject, request, response);
		return updateItemTypeStatus;
	}
	
	/***********************************
	 * ItemSection Master
	 *************************************/
	/**
	 * @param MasStoreSection
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/addStoreSection", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addStoreSection(@RequestBody HashMap<String, Object> storeSection, HttpServletRequest request,
			HttpServletResponse response) {
		String addStoreSection = "";
		JSONObject json = new JSONObject(storeSection);
		addStoreSection = masterService.addStoreSection(json, request, response);
		return addStoreSection;
	}

	@RequestMapping(value = "/getAllStoreSection", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllStoreSection(@RequestBody Map<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		String storeSection = "";
		JSONObject jsonObj = new JSONObject(payload);
		storeSection = masterService.getAllStoreSection(jsonObj, request, response);
		return storeSection;
	}

	@RequestMapping(value = "/getStoreSection", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getStoreSection(@RequestBody HashMap<String, Object> storeSection, HttpServletRequest request) {
		String storeSection1 = "";
		storeSection1 = masterService.getStoreSection(storeSection, request);
		return storeSection1;
	}

	@RequestMapping(value = "/updateStoreSection", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateStoreSection(@RequestBody HashMap<String, Object> storeSection, HttpServletRequest request,
			HttpServletResponse response) {
		String updateStoreSection = "";
		updateStoreSection = masterService.updateStoreSection(storeSection, request, response);
		return updateStoreSection;
	}

	@RequestMapping(value = "/statusStoreSection", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String statusStoreSection(@RequestBody HashMap<String, Object> storeSection, HttpServletRequest request,
			HttpServletResponse response) {
		String status = "";
		status = masterService.statusStoreSection(storeSection, request, response);
		return status;
	}

	/*
	 * @RequestMapping(value = "/getItemTypeList", method = RequestMethod.POST,
	 * produces = "application/json", consumes = "application/json") public String
	 * getItemTypeList(HttpServletRequest request, HttpServletResponse response) {
	 * String addItemType = "";
	 * 
	 * addItemType = masterService.getItemTypeList(request, response); return
	 * addItemType; }
	 */
	
}

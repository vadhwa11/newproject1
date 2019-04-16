package com.icg.jkt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
		System.out.println("updateMaritalStatusStatus :: " + updateMaritalStatusStatus);
		return updateMaritalStatusStatus;
	}

	/*********************************************
	 * MAS Administrative Sex
	 ********************************************************/
	@RequestMapping(value = "/getAllAdministrativeSex", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String getAllAdministrativeSex(@RequestBody Map<String, Object> administrativeSexPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String allAdministrativeSex = "";
		JSONObject jsonObject = new JSONObject(administrativeSexPayload);
		allAdministrativeSex = masterService.getAllAdministrativeSex(jsonObject, request, response);
		return allAdministrativeSex;
	}

	@RequestMapping(value = "/addAdministrativeSex", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addAdministrativeSex(@RequestBody Map<String, Object> administrativeSexPayload,
			HttpServletRequest request, HttpServletResponse response) {
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
	public String getAllMedicalCategory(@RequestBody Map<String, Object> medicalCategoryPayload,
			HttpServletRequest request, HttpServletResponse response) {
		String allMedicalCategory = "";
		JSONObject jsonObject = new JSONObject(medicalCategoryPayload);
		allMedicalCategory = masterService.getAllMedicalCategory(jsonObject, request, response);
		System.out.println("medicalCategoryPayload+medicalCategoryPayload :: " + medicalCategoryPayload);
		return allMedicalCategory;
	}

	@RequestMapping(value = "/addMedicalCategory", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String addMedicalCategory(@RequestBody Map<String, Object> medicalCategoryPayload,
			HttpServletRequest request, HttpServletResponse response) {
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
		System.out.println("responseObj_SAMPLELIST_Service" + allSample);
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
	public String updateSampleDetails(@RequestBody HashMap<String, Object> samplePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String updateSample = "";
		JSONObject jsonObject = new JSONObject(samplePayload);
		updateSample = masterService.updateSampleDetails(jsonObject, request, response);
		return updateSample;
	}

	@RequestMapping(value = "/updateSampleStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateSampleStatus(@RequestBody HashMap<String, Object> samplePayload, HttpServletRequest request,
			HttpServletResponse response) {
		String updateSampleStatus = "";
		JSONObject jsonObject = new JSONObject(samplePayload);
		updateSampleStatus = masterService.updateSampleStatus(jsonObject, request, response);
		return updateSampleStatus;
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
	public String updateItemUnitStatus(@RequestBody HashMap<String, Object> itemUnitPayload, HttpServletRequest request,
			HttpServletResponse response) {
		String updateItemUnitStatus = "";
		JSONObject jsonObject = new JSONObject(itemUnitPayload);
		updateItemUnitStatus = masterService.updateItemUnitStatus(jsonObject, request, response);
		return updateItemUnitStatus;
	}
}

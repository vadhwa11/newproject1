package com.icg.jkt.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icg.jkt.dao.PatientRegistrationDao;
import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasAppointmentSession;
import com.icg.jkt.entity.MasAppointmentType;
import com.icg.jkt.entity.MasBloodGroup;
import com.icg.jkt.entity.MasCommand;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasEmployeeDependent;
import com.icg.jkt.entity.MasHospital;
import com.icg.jkt.entity.MasIdentificationType;
import com.icg.jkt.entity.MasMaritalStatus;
import com.icg.jkt.entity.MasMedicalCategory;
import com.icg.jkt.entity.MasRank;
import com.icg.jkt.entity.MasRecordOfficeAddress;
import com.icg.jkt.entity.MasRegistrationType;
import com.icg.jkt.entity.MasRelation;
import com.icg.jkt.entity.MasReligion;
import com.icg.jkt.entity.MasServiceType;
import com.icg.jkt.entity.MasState;
import com.icg.jkt.entity.MasTrade;
import com.icg.jkt.entity.MasUnit;
import com.icg.jkt.entity.Patient;
import com.icg.jkt.entity.Visit;
import com.icg.jkt.service.PatientRegistrationService;
import com.icg.jkt.utils.HMSUtil;



@Service("PatientRegistrationService")
public class PatientRegistrationServiceImpl implements PatientRegistrationService{

	@Autowired
	PatientRegistrationDao patientRegistrationDao;


	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findPatientAndDependentFromEmployee(Map<String, String> requestData) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> patientListofEmpAndDependent = new HashMap<String, Object>();
		Map<Integer, Map<String, Object>> data = new HashMap<Integer, Map<String, Object>>();
		List<Patient> existingPatientList= new ArrayList<Patient>();
		List<MasEmployee> empAndDependentPatientList= new ArrayList<MasEmployee>();
		List<MasEmployeeDependent> dependentPatientList= new ArrayList<MasEmployeeDependent>();
		
		
		
		long empRankId = 0;
		long empTradeId = 0;
		long empTotalService = 0;
		long empUnitId = 0;
		long empCommandId = 0;
		long empRecordOfficeId = 0;
		long empMaritalStatusId = 0;
		long empReligionId = 0;
		long relationId = 0;
		long genderId=0;
		long stateId=0;
		long nok1RelationId=0;
		long nok2RelationId=0;
		long patientBloodGroupId=0;
		long empMedicalCategoryId=0;
		long employeeId=0;
		
		Date dateOfBirth = null;
		Date dateME=null;
		Date empServiceJoinDate=null;
		int age = 0;

		String name = "";
		String relation = "";
		String gender = "";
		String empName = "";
		String serviceNo = "";
		String empRank="";
		String empTradeName="";
		String empUnitName="";
		String empCommandName="";
		String empRecordOfficeName="";
		String empMaritalStatusName="";
		String empReligion="";
		String patientAddress="";
		String city="";
		String stateName="";
		String mobileNumber = "";
		String patientEmailId="";
		String patientBloodGroup="";
		
		String nok1Name=""; 
		String nok1Relation="";
		String nok1ContactNo="";
		String Nok1Address="";
		String nok1PoliceStation="";
	
		String nok1MobileNo="";
		String nok1EamilId="";
		
		String nok2Name=""; 
		String nok2Relation="";
		String nok2ContactNo="";
		String nok2Address="";
		String nok2PoliceStation="";
		String nok2Pincode="";
		String nok2MobileNo="";
		String nok2EamilId="";
		String empMedicalCategory="";
		
		
		long patientPincode= 0 ;
		long nok1Pincode=0;
		long empAge=0;
		String empDepartmentName="";
		
		
		if (!requestData.isEmpty() && requestData != null ) {
			if (requestData.get("serviceNo") != null && !requestData.get("serviceNo").isEmpty()) {
				serviceNo = requestData.get("serviceNo");
				patientListofEmpAndDependent = patientRegistrationDao.findPatientAndDependentFromEmployee(serviceNo);
				
				if(patientListofEmpAndDependent.size()>0 && !patientListofEmpAndDependent.isEmpty()) {
					existingPatientList = (List<Patient>)patientListofEmpAndDependent.get("patientList");
					if(!existingPatientList.isEmpty() && existingPatientList.size()>0) {
						int rowCount=0;
						for(Patient patient : existingPatientList) {
							Map<String, Object> responsePatientMap = new HashMap<String, Object>();
							String patientName = "";
							if (patient.getPatientName() != null) {
								patientName = patient.getPatientName();
							}
							// Employee related details 
							
							serviceNo= patient.getServiceNo();
							empRankId= patient.getMasRank().getRankId();
							empRank = patient.getMasRank().getRankName();
							empTradeId= patient.getMasTrade().getTradeId();
							empTradeName= patient.getMasTrade().getTradeName();
							empName= patient.getEmployeeName();
							employeeId =patient.getMasEmployee().getEmployeeId();
							empTotalService= HMSUtil.calculateAgeNoOfYear(patient.getServiceJoinDate());
							empServiceJoinDate =patient.getServiceJoinDate();
							empUnitId=patient.getMasUnit().getUnitId();
							empUnitName=patient.getMasUnit().getUnitName();
							empCommandId=patient.getMasCommand().getCommandId();
							empCommandName=patient.getMasCommand().getCommandName();
							empRecordOfficeId=patient.getMasRecordOfficeAddress().getRecordOfficeAddressId();
							empRecordOfficeName=patient.getMasRecordOfficeAddress().getAddress();
							empMaritalStatusId=patient.getMasMaritalStatus().getMaritalStatusId();
							empMaritalStatusName=patient.getMasMaritalStatus().getMaritalStatusName();
							empReligionId=patient.getMasReligion().getReligionId();
							empReligion=patient.getMasReligion().getReligionName();
							empAge=HMSUtil.calculateAgeNoOfYear(patient.getMasEmployee().getDateOfBirth());
							empDepartmentName=patient.getMasEmployee().getMasDepartment().getDepartmentName();
							
							
							// patient related details 
							name = patientName;
							age = HMSUtil.calculateAgeNoOfYear(patient.getDateOfBirth());
							gender = patient.getMasAdministrativeSex().getAdministrativeSexName();
							genderId = patient.getMasAdministrativeSex().getAdministrativeSexId();
							dateOfBirth = patient.getDateOfBirth();
							relation = patient.getMasRelation().getRelationName();
							relationId=patient.getMasRelation().getRelationId();
							mobileNumber=patient.getMobileNumber();
							patientAddress = patient.getAddress();
							city = patient.getCity();
							stateId = patient.getMasState().getStateId();
							stateName = patient.getMasState().getStateName();
							patientPincode = patient.getPincode();
							patientEmailId=patient.getEmailId();
							patientBloodGroup = patient.getMasBloodGroup().getBloodGroupName();
							patientBloodGroupId = patient.getMasBloodGroup().getBloodGroupId();
							
							// Medical category and Date ME only for employee  patient 
							empMedicalCategory=(patient.getMasMedicalCategory()!=null ?patient.getMasMedicalCategory().getMedicalCategoryName():"");
							empMedicalCategoryId=(patient.getMasMedicalCategory()!=null ?patient.getMasMedicalCategory().getMedicalCategoryId():0);
							dateME=patient.getDateMe();
							
							// NOK1 related details 
							
							nok1Name = patient.getNok1Name();
							nok1RelationId = patient.getMasRelationNok1().getRelationId();
							nok1Relation=patient.getMasRelationNok1().getRelationName();
							nok1ContactNo=patient.getNok1ContactNo();
							Nok1Address=patient.getNok1Address();
							nok1PoliceStation=patient.getNok1PoliceStation();
							nok1Pincode=patient.getNok1PinCode();
							nok1MobileNo=patient.getNok1MobileNo();
							nok1EamilId=patient.getNok1EmailId();
							
							
							// NOK2 related details 
							
							
							nok2Name=(patient.getNok2Name()!=null?patient.getNok2Name():"");
							nok2RelationId=(patient.getMasRelationNok2()!=null?patient.getMasRelationNok2().getRelationId():0);
							nok2Relation=(patient.getMasRelationNok2()!=null?patient.getMasRelationNok2().getRelationName():"");
							nok2ContactNo=(patient.getNok2ContactNo()!=null?patient.getNok2ContactNo():"");
							nok2Address=(patient.getNok2Address()!=null?patient.getNok2Address():"");
							nok2PoliceStation=(patient.getNok2PoliceStation()!=null?patient.getNok2PoliceStation():"");
							nok2Pincode=(patient.getNok2PinCode()!=null?patient.getNok2PinCode():"");
							nok2MobileNo=(patient.getNok2MobileNo()!=null?patient.getNok2MobileNo():"");
							nok2EamilId=(patient.getNok2EmailId()!=null?patient.getNok2EmailId():"");
							
							
							responsePatientMap.put("Id", patient.getPatientId());
							responsePatientMap.put("employeeId",employeeId);
							responsePatientMap.put("uhidNo", patient.getUhidNo());
							responsePatientMap.put("name", name);
							responsePatientMap.put("age", age);
							responsePatientMap.put("gender", gender);
							responsePatientMap.put("genderId", genderId);
							responsePatientMap.put("dateOfBirth", dateOfBirth);
							responsePatientMap.put("relation", relation);
							responsePatientMap.put("relationId", relationId);
							responsePatientMap.put("mobileNumber",mobileNumber);
							responsePatientMap.put("patientAddress",patientAddress);
							responsePatientMap.put("city",city);
							responsePatientMap.put("stateId",stateId);
							responsePatientMap.put("stateName",stateName);
							responsePatientMap.put("patientPincode",patientPincode);
							responsePatientMap.put("patientEmailId",patientEmailId);
							responsePatientMap.put("patientBloodGroup",patientBloodGroup);
							responsePatientMap.put("patientBloodGroupId",patientBloodGroupId);
							
							responsePatientMap.put("empMedicalCategory",empMedicalCategory);
							responsePatientMap.put("empMedicalCategoryId",empMedicalCategoryId);
							responsePatientMap.put("dateME",dateME);
							
							responsePatientMap.put("serviceNo",serviceNo);
							responsePatientMap.put("empRankId",empRankId);
							responsePatientMap.put("empAge",empAge);
							responsePatientMap.put("empRank",empRank);
							responsePatientMap.put("empTradeId",empTradeId);
							responsePatientMap.put("empTradeName",empTradeName);
							responsePatientMap.put("empName",empName);
							responsePatientMap.put("empTotalService",empTotalService);
							responsePatientMap.put("empServiceJoinDate",empServiceJoinDate);
							responsePatientMap.put("empUnitId",empUnitId);
							responsePatientMap.put("empUnitName",empUnitName);
							responsePatientMap.put("empCommandId",empCommandId);
							responsePatientMap.put("empCommandName",empCommandName);
							responsePatientMap.put("empRecordOfficeId",empRecordOfficeId);
							responsePatientMap.put("empRecordOfficeName",empRecordOfficeName);
							responsePatientMap.put("empDepartmentName",empDepartmentName);
							
							
							responsePatientMap.put("empMaritalStatusId",empMaritalStatusId);
							responsePatientMap.put("empMaritalStatusName",empMaritalStatusName);
							responsePatientMap.put("empReligionId",empReligionId);
							responsePatientMap.put("empReligion",empReligion);
							
							responsePatientMap.put("nok1Name",nok1Name);
							responsePatientMap.put("nok1RelationId",nok1RelationId);
							responsePatientMap.put("nok1Relation",nok1Relation);
							responsePatientMap.put("nok1ContactNo",nok1ContactNo);
							responsePatientMap.put("Nok1Address",Nok1Address);
							responsePatientMap.put("nok1PoliceStation",nok1PoliceStation);
							responsePatientMap.put("nok1Pincode",nok1Pincode);
							responsePatientMap.put("nok1MobileNo",nok1MobileNo);
							responsePatientMap.put("nok1EamilId",nok1EamilId);
							
							responsePatientMap.put("nok2Name",nok2Name);
							responsePatientMap.put("nok2RelationId",nok2RelationId);
							responsePatientMap.put("nok2Relation",nok2Relation);
							responsePatientMap.put("nok2ContactNo",nok2ContactNo);
							responsePatientMap.put("nok2Address",nok2Address);
							responsePatientMap.put("nok2PoliceStation",nok2PoliceStation);
							responsePatientMap.put("nok2Pincode",nok2Pincode);
							responsePatientMap.put("nok2MobileNo",nok2MobileNo);
							responsePatientMap.put("nok2EamilId",nok2EamilId);
							
							
							data.put(++rowCount, responsePatientMap);
							
							
						}
						
						dependentPatientList = (List<MasEmployeeDependent>)patientListofEmpAndDependent.get("employeeDependentList");
						if(!dependentPatientList.isEmpty() && dependentPatientList.size()>0  ) {
							for(MasEmployeeDependent depList :dependentPatientList ) {
								

								Map<String, Object> responseDepMap = new HashMap<String, Object>();
								String depPatientName = "";
								if (depList.getEmployeeDependentFName() != null) {
									depPatientName = depList.getEmployeeDependentFName();
								}

								if (depList.getEmployeeDependentMName() != null) {
									depPatientName = depPatientName + " "
											+ depList.getEmployeeDependentMName();
								}
								if (depList.getEmployeeDependentLName()!= null) {
									depPatientName = depPatientName + " "
											+ depList.getEmployeeDependentLName();
							 
									}
								
								//Patient related data
								name = depPatientName;
								age = HMSUtil.calculateAgeNoOfYear(depList.getDateOfBirth());
								gender = depList.getMasAdministrativeSex().getAdministrativeSexName();
								genderId = depList.getMasAdministrativeSex().getAdministrativeSexId();
								dateOfBirth = depList.getDateOfBirth();
								relation = depList.getMasRelation().getRelationName();
								relationId=depList.getMasRelation().getRelationId();
								mobileNumber=depList.getContactNo();
								patientAddress = depList.getAddress();
								city = depList.getCity();
								stateId = depList.getMasState().getStateId();
								stateName = depList.getMasState().getStateName();
								patientPincode = depList.getPincode();
								patientEmailId = depList.getEmailId();
								patientBloodGroup = depList.getMasBloodGroup().getBloodGroupName();
								patientBloodGroupId = depList.getMasBloodGroup().getBloodGroupId();
								
								
								//Employee related 
								
								serviceNo= depList.getMasEmployee().getServiceNo();
								empRankId= depList.getMasEmployee().getMasRank().getRankId();
								empRank = depList.getMasEmployee().getMasRank().getRankName();
								empTradeId= depList.getMasEmployee().getMasTrade().getTradeId();
								empTradeName= depList.getMasEmployee().getMasTrade().getTradeName();
								empName= depList.getMasEmployee().getFirstName(); // full employee name need to be fetch
								employeeId = depList.getMasEmployee().getEmployeeId();
								empTotalService= HMSUtil.calculateAgeNoOfYear(depList.getMasEmployee().getJoinDate());
								empServiceJoinDate=depList.getMasEmployee().getJoinDate();
								empUnitId=depList.getMasEmployee().getMasUnit2().getUnitId();
								empUnitName=depList.getMasEmployee().getMasUnit2().getUnitName();
								empCommandId=depList.getMasEmployee().getMasCommand().getCommandId();
								empCommandName=depList.getMasEmployee().getMasCommand().getCommandName();
								empRecordOfficeId=depList.getMasEmployee().getMasRecordOfficeAddress().getRecordOfficeAddressId();
								empRecordOfficeName=depList.getMasEmployee().getMasRecordOfficeAddress().getAddress();
								empMaritalStatusId=depList.getMasEmployee().getMasMaritalStatus().getMaritalStatusId();
								empMaritalStatusName=depList.getMasEmployee().getMasMaritalStatus().getMaritalStatusName();
								empReligionId=depList.getMasEmployee().getMasReligion().getReligionId();
								empReligion=depList.getMasEmployee().getMasReligion().getReligionName();
								empAge=HMSUtil.calculateAgeNoOfYear(depList.getMasEmployee().getDateOfBirth());
								empDepartmentName=depList.getMasEmployee().getMasDepartment().getDepartmentName();
								
								
								// NOK1 related details 
								
								nok1Name = depList.getMasEmployee().getNok1Name();
								nok1RelationId = depList.getMasEmployee().getMasRelation().getRelationId();
								nok1Relation=depList.getMasEmployee().getMasRelation().getRelationName();
								nok1ContactNo=depList.getMasEmployee().getNok1ContactNo();
								Nok1Address=depList.getMasEmployee().getNok1Address();
								nok1PoliceStation=depList.getMasEmployee().getNok1PoliceStation();
								nok1Pincode=depList.getMasEmployee().getNok1Pincode();
								nok1MobileNo=depList.getMasEmployee().getNok1MobileNo();
								nok1EamilId=depList.getMasEmployee().getNok1EmailId();
								
								
								
								
								
								responseDepMap.put("Id", depList.getEmployeeDependentId());
								responseDepMap.put("employeeId", employeeId);
								responseDepMap.put("name", name);
								responseDepMap.put("age", age);
								responseDepMap.put("gender", gender);
								responseDepMap.put("genderId", genderId);
								responseDepMap.put("dateOfBirth", dateOfBirth);
								responseDepMap.put("relation", relation);
								responseDepMap.put("relationId", relationId);
								responseDepMap.put("mobileNumber",mobileNumber);
								responseDepMap.put("patientAddress",patientAddress);
								responseDepMap.put("city",city);
								responseDepMap.put("stateId",stateId);
								responseDepMap.put("stateName",stateName);
								responseDepMap.put("patientPincode",patientPincode);
								responseDepMap.put("patientEmailId",patientEmailId);
								responseDepMap.put("uhidNo", 0);
								
								responseDepMap.put("serviceNo",serviceNo);
								responseDepMap.put("empRankId",empRankId);
								responseDepMap.put("empAge",empAge);
								responseDepMap.put("empRank",empRank);
								responseDepMap.put("empTradeId",empTradeId);
								responseDepMap.put("empTradeName",empTradeName);
								responseDepMap.put("empName",empName);
								responseDepMap.put("empTotalService",empTotalService);
								responseDepMap.put("empServiceJoinDate",empServiceJoinDate);
								responseDepMap.put("empUnitId",empUnitId);
								responseDepMap.put("empUnitName",empUnitName);
								responseDepMap.put("empCommandId",empCommandId);
								responseDepMap.put("empCommandName",empCommandName);
								responseDepMap.put("empRecordOfficeId",empRecordOfficeId);
								responseDepMap.put("empRecordOfficeName",empRecordOfficeName);
								responseDepMap.put("empDepartmentName",empDepartmentName);
								
								
								responseDepMap.put("empMaritalStatusId",empMaritalStatusId);
								responseDepMap.put("empMaritalStatusName",empMaritalStatusName);
								responseDepMap.put("empReligionId",empReligionId);
								responseDepMap.put("empReligion",empReligion);
								
								responseDepMap.put("nok1Name",nok1Name);
								responseDepMap.put("nok1RelationId",nok1RelationId);
								responseDepMap.put("nok1Relation",nok1Relation);
								responseDepMap.put("nok1ContactNo",nok1ContactNo);
								responseDepMap.put("Nok1Address",Nok1Address);
								responseDepMap.put("nok1PoliceStation",nok1PoliceStation);
								responseDepMap.put("nok1Pincode",nok1Pincode);
								responseDepMap.put("nok1MobileNo",nok1MobileNo);
								responseDepMap.put("nok1EamilId",nok1EamilId);
								responseDepMap.put("patientBloodGroup",patientBloodGroup);
								responseDepMap.put("patientBloodGroupId",patientBloodGroupId);
								
								
								data.put(++rowCount, responseDepMap);

							
								
							}
						}
						
						
						empAndDependentPatientList = (List<MasEmployee>)patientListofEmpAndDependent.get("employeeList");
							if(!empAndDependentPatientList.isEmpty() && empAndDependentPatientList.size()>0  ) {
							for(MasEmployee ms : empAndDependentPatientList) {

								
								relation =  HMSUtil.getProperties("adt.properties", "SELF_RELATION");
								relationId =  Long.parseLong(HMSUtil.getProperties("adt.properties", "SELF_RELATION_ID"));
								
								Map<String, Object> responseEmpMap = new HashMap<String, Object>();
								String patientName = "";
								if (ms.getFirstName() != null) {
									patientName = ms.getFirstName();
								}

								if (ms.getMiddleName() != null) {
									patientName = patientName + " "
											+ ms.getMiddleName();
								}
								if (ms.getLastName() != null) {
									patientName = patientName + " "
											+ ms.getLastName();
							 
									}
								
								
								
								serviceNo= ms.getServiceNo();
								employeeId=ms.getEmployeeId();
								empRankId= ms.getMasRank().getRankId();
								empRank = ms.getMasRank().getRankName();
								empTradeId= ms.getMasTrade().getTradeId();
								empTradeName= ms.getMasTrade().getTradeName();
								empName= patientName;
								empTotalService= HMSUtil.calculateAgeNoOfYear(ms.getJoinDate());
								empServiceJoinDate = ms.getJoinDate();
								empAge=HMSUtil.calculateAgeNoOfYear(ms.getDateOfBirth());
								empUnitId=ms.getMasUnit2().getUnitId();
								empUnitName=ms.getMasUnit2().getUnitName();
								empCommandId=ms.getMasCommand().getCommandId();
								empCommandName=ms.getMasCommand().getCommandName();
								empRecordOfficeId=ms.getMasRecordOfficeAddress().getRecordOfficeAddressId();
								empRecordOfficeName=ms.getMasRecordOfficeAddress().getAddress();
								empMaritalStatusId=ms.getMasMaritalStatus().getMaritalStatusId();
								empMaritalStatusName=ms.getMasMaritalStatus().getMaritalStatusName();
								empReligionId=ms.getMasReligion().getReligionId();
								empReligion=ms.getMasReligion().getReligionName();
								empDepartmentName=ms.getMasDepartment().getDepartmentName();
								
								// NOK1 related details 
								
								nok1Name = ms.getNok1Name();
								nok1RelationId = ms.getMasRelation().getRelationId();
								nok1Relation=ms.getMasRelation().getRelationName();
								nok1ContactNo=ms.getNok1ContactNo();
								Nok1Address=ms.getNok1Address();
								nok1PoliceStation=ms.getNok1PoliceStation();
								nok1Pincode=ms.getNok1Pincode();
								nok1MobileNo=ms.getNok1MobileNo();
								nok1EamilId=ms.getNok1EmailId();
								
								
								
								name = patientName;
								age = HMSUtil.calculateAgeNoOfYear(ms.getDateOfBirth());
								gender = ms.getMasAdministrativeSex().getAdministrativeSexName();
								genderId = ms.getMasAdministrativeSex().getAdministrativeSexId();
								dateOfBirth = ms.getDateOfBirth();
								//mobileNumber=ms.getMobileNumber();
								patientAddress = ms.getLocalAddress();
								city = ms.getCity();
								stateId = ms.getMasState2().getStateId();
								stateName = ms.getMasState2().getStateName();
								patientPincode = ms.getPincode();
								patientEmailId=ms.getEmail();
								patientBloodGroup = ms.getMasBloodGroup().getBloodGroupName();
								patientBloodGroupId = ms.getMasBloodGroup().getBloodGroupId();
								
								
								responseEmpMap.put("uhidNo", 0);
								responseEmpMap.put("Id", ms.getEmployeeId());
								responseEmpMap.put("employeeId", employeeId);
								responseEmpMap.put("name", name);
								responseEmpMap.put("age", age);
								responseEmpMap.put("gender", gender);
								responseEmpMap.put("genderId", genderId);
								responseEmpMap.put("dateOfBirth", dateOfBirth);
								responseEmpMap.put("relation", relation);
								responseEmpMap.put("relationId", relationId);
								responseEmpMap.put("mobileNumber",mobileNumber);
								responseEmpMap.put("patientAddress",patientAddress);
								responseEmpMap.put("city",city);
								responseEmpMap.put("stateId",stateId);
								responseEmpMap.put("stateName",stateName);
								responseEmpMap.put("patientPincode",patientPincode);
								responseEmpMap.put("patientEmailId",patientEmailId);
								responseEmpMap.put("patientBloodGroup", patientBloodGroup);
								responseEmpMap.put("patientBloodGroupId", patientBloodGroupId);
								
								
								responseEmpMap.put("serviceNo",serviceNo);
								responseEmpMap.put("empRankId",empRankId);
								responseEmpMap.put("empAge",empAge);
								responseEmpMap.put("empRank",empRank);
								responseEmpMap.put("empTradeId",empTradeId);
								responseEmpMap.put("empTradeName",empTradeName);
								responseEmpMap.put("empName",empName);
								responseEmpMap.put("empTotalService",empTotalService);
								responseEmpMap.put("empServiceJoinDate",empServiceJoinDate);
								responseEmpMap.put("empUnitId",empUnitId);
								responseEmpMap.put("empUnitName",empUnitName);
								responseEmpMap.put("empCommandId",empCommandId);
								responseEmpMap.put("empCommandName",empCommandName);
								responseEmpMap.put("empRecordOfficeId",empRecordOfficeId);
								responseEmpMap.put("empRecordOfficeName",empRecordOfficeName);
								responseEmpMap.put("empDepartmentName",empDepartmentName);
								
								responseEmpMap.put("empMaritalStatusId",empMaritalStatusId);
								responseEmpMap.put("empMaritalStatusName",empMaritalStatusName);
								responseEmpMap.put("empReligionId",empReligionId);
								responseEmpMap.put("empReligion",empReligion);
								
								responseEmpMap.put("nok1Name",nok1Name);
								responseEmpMap.put("nok1RelationId",nok1RelationId);
								responseEmpMap.put("nok1Relation",nok1Relation);
								responseEmpMap.put("nok1ContactNo",nok1ContactNo);
								responseEmpMap.put("Nok1Address",Nok1Address);
								responseEmpMap.put("nok1PoliceStation",nok1PoliceStation);
								responseEmpMap.put("nok1Pincode",nok1Pincode);
								responseEmpMap.put("nok1MobileNo",nok1MobileNo);
								responseEmpMap.put("nok1EamilId",nok1EamilId);
								
								data.put(++rowCount, responseEmpMap);
							}
						}
							map.put("data", data);
							map.put("count", data.size());
							map.put("msg","List of Detail");
							map.put("status", "1");
							return map;
							
					}else {
						empAndDependentPatientList = (List<MasEmployee>)patientListofEmpAndDependent.get("employeeList");
						int rowCount=0;
						for (MasEmployee ms : empAndDependentPatientList) {
							
							relation =  HMSUtil.getProperties("adt.properties", "SELF_RELATION");
							relationId =  Long.parseLong(HMSUtil.getProperties("adt.properties", "SELF_RELATION_ID"));
							
							Map<String, Object> responseEmpMap = new HashMap<String, Object>();
							String patientName = "";
							if (ms.getFirstName() != null) {
								patientName = ms.getFirstName();
							}

							if (ms.getMiddleName() != null) {
								patientName = patientName + " "
										+ ms.getMiddleName();
							}
							if (ms.getLastName() != null) {
								patientName = patientName + " "
										+ ms.getLastName();
						 
								}
							
							serviceNo= ms.getServiceNo();
							employeeId=ms.getEmployeeId();
							empRankId= ms.getMasRank().getRankId();
							empRank = ms.getMasRank().getRankName();
							empTradeId= ms.getMasTrade().getTradeId();
							empTradeName= ms.getMasTrade().getTradeName();
							empName= patientName;
							empTotalService= HMSUtil.calculateAgeNoOfYear(ms.getJoinDate());
							empServiceJoinDate = ms.getJoinDate();
							empAge=HMSUtil.calculateAgeNoOfYear(ms.getDateOfBirth());
							
							empUnitId=ms.getMasUnit2().getUnitId();
							empUnitName=ms.getMasUnit2().getUnitName();
							empCommandId=ms.getMasCommand().getCommandId();
							empCommandName=ms.getMasCommand().getCommandName();
							empRecordOfficeId=ms.getMasRecordOfficeAddress().getRecordOfficeAddressId();
							empRecordOfficeName=ms.getMasRecordOfficeAddress().getAddress();
							empMaritalStatusId=ms.getMasMaritalStatus().getMaritalStatusId();
							empMaritalStatusName=ms.getMasMaritalStatus().getMaritalStatusName();
							empReligionId=ms.getMasReligion().getReligionId();
							empReligion=ms.getMasReligion().getReligionName();
							empDepartmentName=ms.getMasDepartment().getDepartmentName();
							
							// NOK1 related details 
							
							nok1Name = ms.getNok1Name();
							nok1RelationId = ms.getMasRelation().getRelationId();
							nok1Relation=ms.getMasRelation().getRelationName();
							nok1ContactNo=ms.getNok1ContactNo();
							Nok1Address=ms.getNok1Address();
							nok1PoliceStation=ms.getNok1PoliceStation();
							nok1Pincode=ms.getNok1Pincode();
							nok1MobileNo=ms.getNok1MobileNo();
							nok1EamilId=ms.getNok1EmailId();
							
							
							
							name = patientName;
							age = HMSUtil.calculateAgeNoOfYear(ms.getDateOfBirth());
							gender = ms.getMasAdministrativeSex().getAdministrativeSexName();
							genderId = ms.getMasAdministrativeSex().getAdministrativeSexId();
							dateOfBirth = ms.getDateOfBirth();
							//mobileNumber=ms.getMobileNumber();
							patientAddress = ms.getLocalAddress();
							city = ms.getCity();
							stateId = ms.getMasState2().getStateId();
							stateName = ms.getMasState2().getStateName();
							patientPincode = ms.getPincode();
							patientEmailId=ms.getEmail();
							patientBloodGroup = ms.getMasBloodGroup().getBloodGroupName();
							patientBloodGroupId = ms.getMasBloodGroup().getBloodGroupId();
							
							
							
							responseEmpMap.put("uhidNo", 0);
							responseEmpMap.put("Id", ms.getEmployeeId());
							responseEmpMap.put("employeeId", employeeId);
							responseEmpMap.put("name", name);
							responseEmpMap.put("empAge", empAge);
							responseEmpMap.put("age", age);
							responseEmpMap.put("gender", gender);
							responseEmpMap.put("genderId", genderId);
							responseEmpMap.put("dateOfBirth", dateOfBirth);
							responseEmpMap.put("relation", relation);
							responseEmpMap.put("relationId", relationId);
							responseEmpMap.put("mobileNumber",mobileNumber);
							responseEmpMap.put("patientAddress",patientAddress);
							responseEmpMap.put("city",city);
							responseEmpMap.put("stateId",stateId);
							responseEmpMap.put("stateName",stateName);
							responseEmpMap.put("patientPincode",patientPincode);
							responseEmpMap.put("patientEmailId",patientEmailId);
							responseEmpMap.put("patientBloodGroup",patientBloodGroup);
							responseEmpMap.put("patientBloodGroupId",patientBloodGroupId);
							
							
							responseEmpMap.put("serviceNo",serviceNo);
							responseEmpMap.put("empRankId",empRankId);
							responseEmpMap.put("empRank",empRank);
							responseEmpMap.put("empTradeId",empTradeId);
							responseEmpMap.put("empTradeName",empTradeName);
							responseEmpMap.put("empName",empName);
							responseEmpMap.put("empTotalService",empTotalService);
							responseEmpMap.put("empServiceJoinDate",empServiceJoinDate);
							responseEmpMap.put("empUnitId",empUnitId);
							responseEmpMap.put("empUnitName",empUnitName);
							responseEmpMap.put("empCommandId",empCommandId);
							responseEmpMap.put("empCommandName",empCommandName);
							responseEmpMap.put("empRecordOfficeId",empRecordOfficeId);
							responseEmpMap.put("empRecordOfficeName",empRecordOfficeName);
							responseEmpMap.put("empDepartmentName",empDepartmentName);
							
							responseEmpMap.put("empMaritalStatusId",empMaritalStatusId);
							responseEmpMap.put("empMaritalStatusName",empMaritalStatusName);
							responseEmpMap.put("empReligionId",empReligionId);
							responseEmpMap.put("empReligion",empReligion);
							
							responseEmpMap.put("nok1Name",nok1Name);
							responseEmpMap.put("nok1RelationId",nok1RelationId);
							responseEmpMap.put("nok1Relation",nok1Relation);
							responseEmpMap.put("nok1ContactNo",nok1ContactNo);
							responseEmpMap.put("Nok1Address",Nok1Address);
							responseEmpMap.put("nok1PoliceStation",nok1PoliceStation);
							responseEmpMap.put("nok1Pincode",nok1Pincode);
							responseEmpMap.put("nok1MobileNo",nok1MobileNo);
							responseEmpMap.put("nok1EamilId",nok1EamilId);
							
							
							data.put(++rowCount, responseEmpMap);

							List<MasEmployeeDependent> dependentList = ms.getMasEmployeeDependents();
							if (!dependentList.isEmpty()) {

								for (MasEmployeeDependent depList : dependentList) {
									Map<String, Object> responseDepMap = new HashMap<String, Object>();
									String depPatientName = "";
									if (depList.getEmployeeDependentFName() != null) {
										depPatientName = depList.getEmployeeDependentFName();
									}

									if (depList.getEmployeeDependentMName() != null) {
										depPatientName = depPatientName + " "
												+ depList.getEmployeeDependentMName();
									}
									if (depList.getEmployeeDependentLName()!= null) {
										depPatientName = depPatientName + " "
												+ depList.getEmployeeDependentLName();
								 
										}
									
									
									name = depPatientName;
									age = HMSUtil.calculateAgeNoOfYear(depList.getDateOfBirth());
									gender = depList.getMasAdministrativeSex().getAdministrativeSexName();
									genderId = depList.getMasAdministrativeSex().getAdministrativeSexId();
									dateOfBirth = depList.getDateOfBirth();
									relation = depList.getMasRelation().getRelationName();
									relationId=depList.getMasRelation().getRelationId();
									
									mobileNumber=depList.getContactNo();
									patientAddress = depList.getAddress();
									city = depList.getCity();
									stateId = depList.getMasState().getStateId();
									stateName = depList.getMasState().getStateName();
									patientPincode = depList.getPincode();
									patientEmailId = depList.getEmailId();
									patientBloodGroup = depList.getMasBloodGroup().getBloodGroupName();
									patientBloodGroupId = depList.getMasBloodGroup().getBloodGroupId();
									
									//Employee related 
									
									serviceNo= depList.getMasEmployee().getServiceNo();
									empRankId= depList.getMasEmployee().getMasRank().getRankId();
									empRank = depList.getMasEmployee().getMasRank().getRankName();
									empAge = HMSUtil.calculateAgeNoOfYear(depList.getMasEmployee().getDateOfBirth());
									empTradeId= depList.getMasEmployee().getMasTrade().getTradeId();
									empTradeName= depList.getMasEmployee().getMasTrade().getTradeName();
									empName= depList.getMasEmployee().getFirstName(); // full employee name need to be fetch
									employeeId =depList.getMasEmployee().getEmployeeId();
									empTotalService= HMSUtil.calculateAgeNoOfYear(depList.getMasEmployee().getJoinDate());
									empServiceJoinDate = depList.getMasEmployee().getJoinDate();
									empUnitId=depList.getMasEmployee().getMasUnit2().getUnitId();
									empUnitName=depList.getMasEmployee().getMasUnit2().getUnitName();
									empCommandId=depList.getMasEmployee().getMasCommand().getCommandId();
									empCommandName=depList.getMasEmployee().getMasCommand().getCommandName();
									empRecordOfficeId=depList.getMasEmployee().getMasRecordOfficeAddress().getRecordOfficeAddressId();
									empRecordOfficeName=depList.getMasEmployee().getMasRecordOfficeAddress().getAddress();
									empMaritalStatusId=depList.getMasEmployee().getMasMaritalStatus().getMaritalStatusId();
									empMaritalStatusName=depList.getMasEmployee().getMasMaritalStatus().getMaritalStatusName();
									empReligionId=depList.getMasEmployee().getMasReligion().getReligionId();
									empReligion=depList.getMasEmployee().getMasReligion().getReligionName();
									empDepartmentName=depList.getMasEmployee().getMasDepartment().getDepartmentName();
									
									// NOK1 related details 
									
									nok1Name = depList.getMasEmployee().getNok1Name();
									nok1RelationId = depList.getMasEmployee().getMasRelation().getRelationId();
									nok1Relation=depList.getMasEmployee().getMasRelation().getRelationName();
									nok1ContactNo=depList.getMasEmployee().getNok1ContactNo();
									Nok1Address=depList.getMasEmployee().getNok1Address();
									nok1PoliceStation=depList.getMasEmployee().getNok1PoliceStation();
									nok1Pincode=depList.getMasEmployee().getNok1Pincode();
									nok1MobileNo=depList.getMasEmployee().getNok1MobileNo();
									nok1EamilId=depList.getMasEmployee().getNok1EmailId();
									
									
									
									
									responseDepMap.put("uhidNo", 0);
									responseDepMap.put("Id", depList.getEmployeeDependentId());
									responseDepMap.put("employeeId", employeeId);
									responseDepMap.put("name", name);
									responseDepMap.put("age", age);
									responseDepMap.put("empAge", empAge);
									responseDepMap.put("gender", gender);
									responseDepMap.put("genderId", genderId);
									responseDepMap.put("dateOfBirth", dateOfBirth);
									responseDepMap.put("relation", relation);
									responseDepMap.put("relationId", relationId);
									responseDepMap.put("mobileNumber",mobileNumber);
									responseDepMap.put("patientAddress",patientAddress);
									responseDepMap.put("city",city);
									responseDepMap.put("stateId",stateId);
									responseDepMap.put("stateName",stateName);
									responseDepMap.put("patientPincode",patientPincode);
									responseDepMap.put("patientEmailId",patientEmailId);
									responseDepMap.put("patientBloodGroup", patientBloodGroup);
									responseDepMap.put("patientBloodGroupId", patientBloodGroupId);
									
									responseDepMap.put("serviceNo",serviceNo);
									responseDepMap.put("empRankId",empRankId);
									responseDepMap.put("empRank",empRank);
									responseDepMap.put("empTradeId",empTradeId);
									responseDepMap.put("empTradeName",empTradeName);
									responseDepMap.put("empName",empName);
									responseDepMap.put("empTotalService",empTotalService);
									responseDepMap.put("empServiceJoinDate",empServiceJoinDate);
									responseDepMap.put("empUnitId",empUnitId);
									responseDepMap.put("empUnitName",empUnitName);
									responseDepMap.put("empCommandId",empCommandId);
									responseDepMap.put("empCommandName",empCommandName);
									responseDepMap.put("empRecordOfficeId",empRecordOfficeId);
									responseDepMap.put("empRecordOfficeName",empRecordOfficeName);
									responseDepMap.put("empDepartmentName",empDepartmentName);
									responseDepMap.put("empMaritalStatusId",empMaritalStatusId);
									responseDepMap.put("empMaritalStatusName",empMaritalStatusName);
									responseDepMap.put("empReligionId",empReligionId);
									responseDepMap.put("empReligion",empReligion);
									
									responseDepMap.put("nok1Name",nok1Name);
									responseDepMap.put("nok1RelationId",nok1RelationId);
									responseDepMap.put("nok1Relation",nok1Relation);
									responseDepMap.put("nok1ContactNo",nok1ContactNo);
									responseDepMap.put("Nok1Address",Nok1Address);
									responseDepMap.put("nok1PoliceStation",nok1PoliceStation);
									responseDepMap.put("nok1Pincode",nok1Pincode);
									responseDepMap.put("nok1MobileNo",nok1MobileNo);
									responseDepMap.put("nok1EamilId",nok1EamilId);
									
									
									data.put(++rowCount, responseDepMap);

								}
							}
							map.put("data", data);
							map.put("count", data.size());
							map.put("msg","List of Detail");
							map.put("status", "1");
							return map;
						}
					}
				}
			}
		}
		map.put("msg","Service No does not exist.");
		map.put("count", data.size());
		map.put("status", "0");
		return map;
	}


	@Override
	public Map<String, Object> getRecordsFordepartmentBloodGroupAndMedicalCategory() {
		// TODO Auto-generated method stub
		Map<String,Object>map = new HashMap<String, Object>();
		List<MasBloodGroup> bloodGroupList = new ArrayList<MasBloodGroup>();
		List<MasDepartment> departmentList = new ArrayList<MasDepartment>();
		List<MasMedicalCategory> medicalCategoryList = new ArrayList<MasMedicalCategory>();
		List<MasRelation> masRelationList = new ArrayList<MasRelation>();
				
		bloodGroupList = patientRegistrationDao.geBloodGroupList();
		departmentList = patientRegistrationDao.geDepartmentList();
		medicalCategoryList= patientRegistrationDao.getMedicalCategoryList();
		masRelationList =patientRegistrationDao.getRelationList();	
				
		map.put("bloodGroupList",bloodGroupList);
		map.put("departmentList", departmentList);
		map.put("medicalCategoryList",medicalCategoryList);
		map.put("masRelationList",masRelationList);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getTokenNoForDepartmentMultiVisit(Map<String, Object> requestData) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> rosterValueForToken = new HashMap<String, Object>();
		
		long departmentId=0;
		long hospitalId=0;
		
		String tokenMsg="";

		List<Map<String,Object>> jsonList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> jsonInput = (List<Map<String,Object>>) requestData.get("arrParam");
		for(Map<String,Object> json : jsonInput) {
			
			 departmentId = Long.parseLong(json.get("deptId").toString());
			 hospitalId = Long.parseLong(json.get("hospitalId").toString());
			 
			 
			List<String> appointmentTypeList =(List<String>)json.get("appointmentTypeId");
			for(int i=0;i<appointmentTypeList.size();i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				long appointmentTypeId = Long.parseLong((appointmentTypeList.get(i)));
				rosterValueForToken = patientRegistrationDao.getTokenNoForDepartmentMultiVisit(departmentId,hospitalId,appointmentTypeId);
				if(!rosterValueForToken.isEmpty() && rosterValueForToken.size()>0) {
					tokenMsg= (String)rosterValueForToken.get("tokenMsg");
					appointmentTypeId = (long) rosterValueForToken.get("appointmentTypeId");
					map.put("tokenMsg",tokenMsg);
					map.put("appointmentTypeId",appointmentTypeId);
					map.put("status","1");
				}else {
					tokenMsg= "No Data available";
					map.put("tokenMsg",tokenMsg);
					map.put("status","0");
				}
				jsonList.add(map);
			}
			
		}
		responseMap.put("jsonList", jsonList);
		return responseMap; 
	}


	@Override
	public String submitPatientDetails(String requestData ) {
		List<Long> visitList = new ArrayList<Long>();
		JSONObject json = new JSONObject();
		JSONObject jObject = new JSONObject(requestData);
		
		JSONArray appointmentType= jObject.getJSONArray("appointmentTypeId");
		JSONArray tokenIds= jObject.getJSONArray("tokenIds");
		long  departmentId= Long.parseLong(jObject.getString("departmentId"));
		long priorityId = Long.parseLong(jObject.getString("priorityId"));
		JSONObject jsonObj =jObject.getJSONObject("patientDetailsForm");
		
		long hospitalId =1; // HospitalId will come from session
		
		
		long uhidNO=jsonObj.getLong("uhidNo");
		System.out.println(uhidNO);
		
		Patient patient = null;
		long appointmentSessionId=1;  // This need to be discuss how to get appointmentSessionId
		
		if(uhidNO!=0) {
			long patientId = patientRegistrationDao.getPatientFromUhidNo(uhidNO);
		
			Date date= new Date();
			Timestamp dateTime = new Timestamp(date.getTime());
			
			for(int i=0;i<appointmentType.length();i++) {
				
				Visit visit = new Visit();
				
				if(!tokenIds.get(i).toString().isEmpty()) {
					visit.setTokenNo(Long.parseLong(tokenIds.get(i).toString()));
				}
				
				visit.setVisitDate(dateTime);
				visit.setPriority(priorityId);
				visit.setVisitStatus("w");
				
				MasAppointmentSession maps = new MasAppointmentSession();
				maps.setId(appointmentSessionId);
				visit.setMasAppointmentSession(maps);
				
				Patient p = new Patient();
				p.setPatientId(patientId);
				visit.setPatient(p);
				
				MasDepartment md = new MasDepartment();
				md.setDepartmentId(departmentId);
				visit.setMasDepartment(md);
				
				MasHospital mh = new MasHospital();
				mh.setHospitalId(hospitalId);
				visit.setMasHospital(mh);
				
				MasAppointmentType appType = new MasAppointmentType();
				appType.setAppointmentTypeId(Long.parseLong(appointmentType.get(i).toString()));
				visit.setMasAppointmentType(appType);
				
				long visitId= patientRegistrationDao.saveVisitForRegisteredPatient(visit);
				
				if(visitId!=0) {
					visitList.add(visitId);
				}
			}
			json.put("visitList",visitList);
			
			
		}else {
			uhidNO =(new Double(Math.random()* 50 + 1)).longValue();
			patient = new Patient();
			if(!jsonObj.getString("patientname").isEmpty()) {
				String patientName = jsonObj.getString("patientname");
				patient.setPatientName(patientName);
			}
			
			patient.setUhidNo(uhidNO); // this need to generate according to logic
			
			
			if(!jsonObj.getString("empService").isEmpty()) {
			String empService = jsonObj.getString("empService");
			patient.setServiceNo(empService);
			}
			
			
			if(!jsonObj.getString("empName").isEmpty()) {
				String empName = jsonObj.getString("empName");
				patient.setEmployeeName(empName);
			}
			
			if(jsonObj.getLong("empId")!=0) {
				long empId = jsonObj.getLong("empId");
				MasEmployee employee = new MasEmployee();
				employee.setEmployeeId(empId);
				patient.setMasEmployee(employee);
			}
			
			if(!jsonObj.getString("patientAddress").isEmpty()) {
				String patientAddress = jsonObj.getString("patientAddress");
				 patient.setAddress(patientAddress);
			}
			
			 
			 if(!jsonObj.getString("patientPincode").isEmpty()) {
				 String patientPincode = jsonObj.getString("patientPincode");
					patient.setPincode(Long.parseLong(patientPincode));
			 }
			
			
			 if(!jsonObj.getString("patientCity").isEmpty()) {
				 String patientCity = jsonObj.getString("patientCity");
					patient.setCity(patientCity);
			 }
			
			
			 if(!jsonObj.getString("patientDOB").isEmpty()) {
				 String patientDOB = jsonObj.getString("patientDOB");
				 try {
						patient.setDateOfBirth(HMSUtil.dateFormatteryyyymmdd(patientDOB));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			 }
			
			
			 if(!jsonObj.getString("patientEmail").isEmpty()) {
				 String patientEmail = jsonObj.getString("patientEmail");
					patient.setEmailId(patientEmail);
			 }
			
			 if(!jsonObj.getString("nok1Firstname").isEmpty()) {
				 String nok1FirstName= jsonObj.getString("nok1Firstname");
					patient.setNok1Name(nok1FirstName);
			 }
			
			 if(!jsonObj.getString("nok1Address").isEmpty()) {
				 String nok1Address =jsonObj.getString("nok1Address");
					patient.setNok1Address(nok1Address);
			 }
			
			 if(!jsonObj.getString("nok1Policestation").isEmpty()) {
				 String nok1PoliceStation=jsonObj.getString("nok1Policestation");
					patient.setNok1PoliceStation(nok1PoliceStation);
			 }
			
			 if(!jsonObj.getString("nok1Email").isEmpty()) {
				 String nok1Email =jsonObj.getString("nok1Email");
					patient.setNok1EmailId(nok1Email);
			 }
			
			 if(!jsonObj.getString("nok2Firstname").isEmpty()) {
				 String nok2FirstName=jsonObj.getString("nok2Firstname");
					patient.setNok2Name(nok2FirstName);
			 }
			
			 
			 if(!jsonObj.getString("nok2Address").isEmpty()) {
				 String nok2Address=jsonObj.getString("nok2Address");
					patient.setNok2Address(nok2Address);
			 }
			
			 if(!jsonObj.getString("nok2Policestation").isEmpty()) {
				 String nok2PoliceStation=jsonObj.getString("nok2Policestation");
					patient.setNok2PoliceStation(nok2PoliceStation);
			 }
			
			 if(!jsonObj.getString("nok2Email").isEmpty()) {
				 String nok2Email=jsonObj.getString("nok2Email");
					patient.setNok2EmailId(nok2Email); 
			 }
			
			if(jsonObj.getLong("rankId")!=0) {
				long rankId=jsonObj.getLong("rankId");
				 MasRank mr =new MasRank();
				 mr.setRankId(rankId);
				 patient.setMasRank(mr);
			}
			 
			if(jsonObj.getLong("tradeId")!=0) {
				long tradeId= jsonObj.getLong("tradeId"); // trade/branch
				 MasTrade mt = new MasTrade();
				 mt.setTradeId(tradeId);
				 patient.setMasTrade(mt);
			}
			 
			
			 if(!jsonObj.getString("empServiceJoinDate").isEmpty()) {
				 String  empServiceJoinDate=jsonObj.getString("empServiceJoinDate");
					try {
						patient.setServiceJoinDate(HMSUtil.dateFormatteryyyymmdd(empServiceJoinDate));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
			
			
			 if(jsonObj.getLong("unitId")!=0) {
				 long unitId=jsonObj.getLong("unitId");
				 MasUnit mu = new MasUnit();
				 mu.setUnitId(unitId);
				 patient.setMasUnit(mu);
			 }
			
			 
			 if(jsonObj.getLong("regionId")!=0) {
				 long regionId=jsonObj.getLong("regionId");
					MasCommand mCommand = new MasCommand();
					mCommand.setCommandId(regionId);
					patient.setMasCommand(mCommand);
			 }
			
			
			if(jsonObj.getLong("recordofficeId")!=0) {
				long  recordofficeId=jsonObj.getLong("recordofficeId");
				MasRecordOfficeAddress recordOfficeAddress = new MasRecordOfficeAddress();
				recordOfficeAddress.setRecordOfficeAddressId(recordofficeId);
				patient.setMasRecordOfficeAddress(recordOfficeAddress);
			}
			
			
			
			if(jsonObj.getLong("religionId")!=0) {
				long  religionId=jsonObj.getLong("religionId");
				MasReligion mreligion = new MasReligion();
				mreligion.setReligionId(religionId);
				patient.setMasReligion(mreligion);
			}
			
			
			
			if(jsonObj.getLong("patientRelationId")!=0) {
				long patientRelationId=jsonObj.getLong("patientRelationId");
				MasRelation patientRelation = new MasRelation();
				patientRelation.setRelationId(patientRelationId);
				patient.setMasRelation(patientRelation);
			}
			
			
			
			if(jsonObj.getLong("genderId")!=0) {
				long patientGenderId=jsonObj.getLong("genderId");
				MasAdministrativeSex patientGender = new MasAdministrativeSex();
				patientGender.setAdministrativeSexId(patientGenderId);
				patient.setMasAdministrativeSex(patientGender);
			}
			
			
			
			String patientMoblienumber=jsonObj.getString("patientMoblienumber");
			patient.setMobileNumber(patientMoblienumber);
			
			if(jsonObj.getLong("patientStateId")!=0) {
				long  patientStateId=jsonObj.getLong("patientStateId");
				MasState ms = new MasState();
				ms.setStateId(patientStateId);
				patient.setMasState(ms);	
			}
			
			
			if(!jsonObj.getString("nok1Moblienumber").isEmpty()) {
				String  nok1Moblienumber=jsonObj.getString("nok1Moblienumber");
				patient.setNok1MobileNo(nok1Moblienumber);
			}
		
			
			if(jsonObj.getLong("nok1RelationId")!=0) {
				long  nok1RelationId=jsonObj.getLong("nok1RelationId");
				MasRelation nok1Relation = new MasRelation();
				nok1Relation.setRelationId(nok1RelationId);
				patient.setMasRelationNok1(nok1Relation);
			}
			
			
			if(!jsonObj.getString("nok1pincode").isEmpty()) {
				long  nok1pincode=Long.parseLong(jsonObj.getString("nok1pincode"));
				patient.setNok1PinCode(nok1pincode);
			}
			
			
			if(!jsonObj.getString("nok1Contactnumber").isEmpty()) {
				String  nok1Contactnumber=jsonObj.getString("nok1Contactnumber");
				patient.setNok1ContactNo(nok1Contactnumber);
			}
			
			
			if(!jsonObj.getString("nok2Moblienumber").isEmpty()) {
				String nok2Moblienumber=jsonObj.getString("nok2Moblienumber");
				patient.setNok2MobileNo(nok2Moblienumber);
			}
			
			
			
			if(jsonObj.getLong("nok2Relation")!=0) {
				long nok2RelationId=jsonObj.getLong("nok2Relation");
				MasRelation nok2Relation = new MasRelation();
				nok2Relation.setRelationId(nok2RelationId);
				patient.setMasRelationNok2(nok2Relation);
			}
			
			if(!jsonObj.getString("nok2pincode").isEmpty()) {
				String nok2pincode=jsonObj.getString("nok2pincode");
				patient.setNok2PinCode(nok2pincode);
			}
			
			
			if(!jsonObj.getString("nok2Contactnumber").isEmpty()) {
				String nok2Contactnumber=jsonObj.getString("nok2Contactnumber");
				patient.setNok2ContactNo(nok2Contactnumber);
			}
			
			if(jsonObj.getLong("maritalstarusId")!=0) {
				long maritalstarusId = jsonObj.getLong("maritalstarusId");
				MasMaritalStatus mStatus = new MasMaritalStatus();
				mStatus.setMaritalStatusId(maritalstarusId);
				patient.setMasMaritalStatus(mStatus);
			}
			
			if(jsonObj.getLong("bloodGroup")!=0) {
				long bloodGroupId = jsonObj.getLong("bloodGroup");
				MasBloodGroup bloodGroup = new MasBloodGroup();
				bloodGroup.setBloodGroupId(bloodGroupId);
				patient.setMasBloodGroup(bloodGroup);
			}
			
			
			
			long patientId = patientRegistrationDao.savePatient(patient);
			
			if(patientId!=0) {
				Date date= new Date();
				Timestamp dateTime = new Timestamp(date.getTime());
				
				for(int i=0;i<appointmentType.length();i++) {
					
					Visit visit = new Visit();
					
					visit.setTokenNo(Long.parseLong(tokenIds.get(i).toString()));
					visit.setVisitDate(dateTime);
					visit.setPriority(priorityId);
					visit.setVisitStatus("w");
					
					MasAppointmentSession maps = new MasAppointmentSession();
					maps.setId(appointmentSessionId); //this code need to check
					visit.setMasAppointmentSession(maps);;
					
					Patient p = new Patient();
					p.setPatientId(patientId);
					visit.setPatient(p);
					
					MasDepartment md = new MasDepartment();
					md.setDepartmentId(departmentId);
					visit.setMasDepartment(md);
					
					
					MasHospital mh = new MasHospital();
					mh.setHospitalId(hospitalId);
					visit.setMasHospital(mh);
					
					MasAppointmentType appType = new MasAppointmentType();
					appType.setAppointmentTypeId(Long.parseLong(appointmentType.get(i).toString()));
					visit.setMasAppointmentType(appType);
					
					long visitId= patientRegistrationDao.saveVisitForRegisteredPatient(visit);
					
					if(visitId!=0) {
						visitList.add(visitId);
					}
					
				}
			}
			json.put("visitList",visitList);
		}
		return json.toString();
	}


	@Override
	public Map<String, Object> showAppointmentForOthers() {
		// TODO Auto-generated method stub
		Map<String,Object>map = new HashMap<String, Object>();
		List<MasRegistrationType> registrationTypeList = new ArrayList<MasRegistrationType>();
		List<MasDepartment> departmentList = new ArrayList<MasDepartment>();
		List<MasAdministrativeSex> genderList = new ArrayList<MasAdministrativeSex>();
		List<MasIdentificationType> identificationList = new ArrayList<MasIdentificationType>();
		List<MasServiceType> serviceTypeList = new ArrayList<MasServiceType>();
				
		registrationTypeList = patientRegistrationDao.getRegistrationTypeList();
		departmentList = patientRegistrationDao.geDepartmentList();
		genderList= patientRegistrationDao.getGenderList();
		identificationList= patientRegistrationDao.getIdentificationList();
		serviceTypeList=patientRegistrationDao.getServiceTypeList();	
				
		map.put("registrationTypeList",registrationTypeList);
		map.put("departmentList", departmentList);
		map.put("genderList",genderList);
		map.put("identificationList",identificationList);
		map.put("serviceTypeList",serviceTypeList);
		
		return map;
	}

	@Override
	public Map<String, Object> getTokenNoOfDepartmentForOthers(Map<String, Object> requestData) {
		Map<String, Object> rosterValueForToken = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		long departmentId=0;
		long hospitalId=0;
		long appointmentTypeId=0;
		
		String tokenMsg="";

	
		 departmentId = Long.parseLong(requestData.get("deptId").toString());
		 hospitalId = Long.parseLong(requestData.get("hospitalId").toString());
		 appointmentTypeId=Long.parseLong(requestData.get("appointmentTypeId").toString());
		 
		 rosterValueForToken = patientRegistrationDao.getTokenNoForDepartmentMultiVisit(departmentId,hospitalId,appointmentTypeId); 
		
		 if(!rosterValueForToken.isEmpty() && rosterValueForToken.size()>0) {
				tokenMsg= (String)rosterValueForToken.get("tokenMsg");
				appointmentTypeId = (long) rosterValueForToken.get("appointmentTypeId");
				map.put("tokenMsg",tokenMsg);
				map.put("appointmentTypeId",appointmentTypeId);
				map.put("status","1");
			}else {
				tokenMsg= "No Data available";
				map.put("tokenMsg",tokenMsg);
				map.put("status","0");
			}
		 return map;
	}


	@Override
	public String submitPatientDetailsForOthers(String requestData) {
		JSONObject json = new JSONObject();
		JSONObject jObject = new JSONObject(requestData);
		JSONObject jsonObj =jObject.getJSONObject("patientDetailsFormOthers");
		long visitId=0;
		long appointmentSessionId=1; 
		Patient patient =null;
		long hospitalId=1;// This will fetch from session
		
		if(Long.parseLong(jsonObj.get("uhidNo").toString())!=0) {
			long uhidNO =Long.parseLong(jsonObj.get("uhidNo").toString());
			long patientId = patientRegistrationDao.getPatientFromUhidNo(uhidNO);
			
			Date date= new Date();
			Timestamp dateTime = new Timestamp(date.getTime());
			
			Visit visit = new Visit();

			visit.setVisitDate(dateTime);
			visit.setVisitStatus("w");
			
			
			
			if (!jsonObj.get("department").toString().isEmpty()) {
				long department = Long.parseLong(jsonObj.get("department").toString());
				MasDepartment masDepartment = new MasDepartment();
				masDepartment.setDepartmentId(department);
				visit.setMasDepartment(masDepartment);
			}
			
			Patient p = new Patient();
			p.setPatientId(patientId);
			visit.setPatient(p);
			
			MasHospital mh = new MasHospital();
			mh.setHospitalId(hospitalId);
			visit.setMasHospital(mh);
			
			MasAppointmentSession maps = new MasAppointmentSession();
			maps.setId(appointmentSessionId);
			visit.setMasAppointmentSession(maps);

			if (!jsonObj.get("checkDiv").toString().isEmpty()) {
				long appointmentTypeId = Long.parseLong(jsonObj.get("checkDiv").toString());
				MasAppointmentType appointmentType = new MasAppointmentType();
				appointmentType.setAppointmentTypeId(appointmentTypeId);
				visit.setMasAppointmentType(appointmentType);

			}
			if (!jsonObj.get("priority").toString().isEmpty()) {
				long priority = Long.parseLong(jsonObj.get("priority").toString());
				visit.setPriority(priority);
			}
			if (!jsonObj.get("tokenNo").toString().isEmpty()) {
				long tokenNo = Long.parseLong(jsonObj.get("tokenNo").toString());
				visit.setTokenNo(tokenNo);

			}
			visitId= patientRegistrationDao.saveVisitForRegisteredPatient(visit);
			
		}else {
			patient =new Patient();

			if(!jsonObj.get("idnumber").toString().isEmpty()) {
				String idnumber=jsonObj.get("idnumber").toString();
				patient.setIdentificationNo(idnumber);
			}
			
			if(!jsonObj.get("registrationType").toString().isEmpty()) {
			long registrationType = Long.parseLong(jsonObj.get("registrationType").toString());
			MasRegistrationType mrt = new MasRegistrationType();
			mrt.setRegistrationTypeId(registrationType);
			patient.setMasRegistrationType(mrt);
			
			}
			if(!jsonObj.get("gender").toString().isEmpty()) {
			long gender = Long.parseLong(jsonObj.get("gender").toString());
			
			MasAdministrativeSex genderId = new MasAdministrativeSex();
			genderId.setAdministrativeSexId(gender);
			patient.setMasAdministrativeSex(genderId);
			}
			if(!jsonObj.get("serviceTypeId").toString().isEmpty()) {
			long serviceTypeId = Long.parseLong(jsonObj.get("serviceTypeId").toString());
			MasServiceType serviceType  = new MasServiceType();
			serviceType.setServiceTypeId(serviceTypeId);
			patient.setMasServiceType(serviceType);
			}
			
			if(!jsonObj.get("rank").toString().isEmpty()) {
			String rank = jsonObj.get("rank").toString();
			patient.setOtherRank(rank);
			
			
			long uhidNO =(new Double(Math.random()* 50 + 1)).longValue();
			patient.setUhidNo(uhidNO);
			// Need to discuss 
				/*
				 * MasRank mr = new MasRank(); mr.setRankId(rank); patient.setMasRank(mr);
				 */
			}
			
			
			if(!jsonObj.get("identification").toString().isEmpty()) {
			long identification = Long.parseLong(jsonObj.get("identification").toString());
			MasIdentificationType identificationType = new MasIdentificationType();
			identificationType.setIdentificationTypeId(identification);
			patient.setMasIdentificationType(identificationType);
			}
			
			
			if(!jsonObj.get("serviceNo").toString().isEmpty()) {
			String serviceNo = jsonObj.get("serviceNo").toString();
			patient.setServiceNo(serviceNo);
			}
			
			
			String patientName = "";

			if(!jsonObj.get("firstname").toString().isEmpty()) {
				String firstname = jsonObj.get("firstname").toString();
				patientName=firstname;
			}
			
			if(!jsonObj.get("middlename").toString().isEmpty()) {
				String middlename = jsonObj.get("middlename").toString();
				patientName = patientName + " " +middlename;
			}
			
			if(!jsonObj.get("lastname").toString().isEmpty()) {
				String lastname = jsonObj.get("lastname").toString();
				patientName = patientName + " "+lastname;
			
			}
			patient.setPatientName(patientName);
			
			if(!jsonObj.get("dataOfBirth").toString().isEmpty()) {
			String dataOfBirth = jsonObj.get("dataOfBirth").toString();
			 try {
					patient.setDateOfBirth(HMSUtil.dateFormatteryyyymmdd(dataOfBirth));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
			
			if(!jsonObj.get("mobilenumber").toString().isEmpty()) {
			String mobilenumber = jsonObj.get("mobilenumber").toString();
			patient.setMobileNumber(mobilenumber);
			}
			
			long patientId = patientRegistrationDao.savePatient(patient);
			
			if(patientId!=0) {
				Visit visit = new Visit();

				Date date = new Date();
				Timestamp dateTime = new Timestamp(date.getTime());

				visit.setVisitDate(dateTime);
				visit.setVisitStatus("w");
				
				
				
				if (!jsonObj.get("department").toString().isEmpty()) {
					long department = Long.parseLong(jsonObj.get("department").toString());
					MasDepartment masDepartment = new MasDepartment();
					masDepartment.setDepartmentId(department);
					visit.setMasDepartment(masDepartment);
				}
				
				Patient p = new Patient();
				p.setPatientId(patientId);
				visit.setPatient(p);
				
				MasHospital mh = new MasHospital();
				mh.setHospitalId(hospitalId);
				visit.setMasHospital(mh);
				
				MasAppointmentSession maps = new MasAppointmentSession();
				maps.setId(appointmentSessionId);
				visit.setMasAppointmentSession(maps);

				if (!jsonObj.get("checkDiv").toString().isEmpty()) {
					long appointmentTypeId = Long.parseLong(jsonObj.get("checkDiv").toString());
					MasAppointmentType appointmentType = new MasAppointmentType();
					appointmentType.setAppointmentTypeId(appointmentTypeId);
					visit.setMasAppointmentType(appointmentType);

				}
				if (!jsonObj.get("priority").toString().isEmpty()) {
					long priority = Long.parseLong(jsonObj.get("priority").toString());
					visit.setPriority(priority);
				}
				if (!jsonObj.get("tokenNo").toString().isEmpty()) {
					long tokenNo = Long.parseLong(jsonObj.get("tokenNo").toString());
					visit.setTokenNo(tokenNo);

				}
				visitId= patientRegistrationDao.saveVisitForRegisteredPatient(visit);
			}
		}
		json.put("visitId",visitId);
		return json.toString();
	}


	@Override
	public Map<String, Object> searchOthersRegisteredPatient(Map<String, String> requestData) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<Integer, Map<String, Object>> data = new HashMap<Integer, Map<String, Object>>();
		Map<String,List<Patient>> patientMap = new HashMap<String, List<Patient>>();
		List<Patient> existingPatient = new ArrayList<Patient>();
		String serviceNo="";
		String patientName="";
		long uhinNo=0;
		String mobileNo="";
		   
		if (requestData.get("UhidNoId") != null && !requestData.get("UhidNoId").isEmpty()) {
			uhinNo=Long.parseLong(requestData.get("UhidNoId")) ;
		}
		
		if (requestData.get("patientName") != null && !requestData.get("patientName").isEmpty()) {
			patientName=requestData.get("patientName") ;
		}
		
		if (requestData.get("searchServiceNo") != null && !requestData.get("searchServiceNo").isEmpty()) {
			serviceNo=requestData.get("searchServiceNo");
		}
		
		if (requestData.get("searchMobileNo") != null && !requestData.get("searchMobileNo").isEmpty()) {
			mobileNo=requestData.get("searchMobileNo");
		}
		
		patientMap=patientRegistrationDao.searchOthersRegisteredPatient(uhinNo,patientName,serviceNo,mobileNo);
		if(patientMap.get("patientList").size()>0) {
			String name="";
			String mobileNumber="";
			String serviceType="";
			String registrationTypeName="";
			String rank ="";
			String identificationTypeName="";
			String gender="";
			String idNumber="";
			
			int age=0;
			long genderId=0;
			long registrationTypeId=0;
			long serviceTypeId=0;
			long identificationTypeId=0;
			long id=0;
			Date dateOfBirth=null;
			
			existingPatient = patientMap.get("patientList");
			int rowCount=0;
			for(Patient patient: existingPatient) {
				Map<String, Object> responsePatientMap = new HashMap<String, Object>();
				
				id=patient.getPatientId();
				serviceNo =patient.getServiceNo();
				name = patient.getPatientName();
				rank=patient.getOtherRank();
				age = HMSUtil.calculateAgeNoOfYear(patient.getDateOfBirth());
				gender = patient.getMasAdministrativeSex().getAdministrativeSexName();
				genderId = patient.getMasAdministrativeSex().getAdministrativeSexId();
				dateOfBirth = patient.getDateOfBirth();
				serviceTypeId=patient.getMasServiceType().getServiceTypeId();
				serviceType=patient.getMasServiceType().getServiceTypeName();
				uhinNo=patient.getUhidNo();
				mobileNumber=patient.getMobileNumber();
				registrationTypeId=patient.getMasRegistrationType().getRegistrationTypeId();
				registrationTypeName=patient.getMasRegistrationType().getRegistrationTypeName();
				identificationTypeId= patient.getMasIdentificationType().getIdentificationTypeId();
				identificationTypeName= patient.getMasIdentificationType().getIdentificationName();
				idNumber = patient.getIdentificationNo();
				
				
				responsePatientMap.put("Id", id);
				responsePatientMap.put("name", name);
				responsePatientMap.put("serviceNo", serviceNo);
				responsePatientMap.put("rank", rank);
				
				responsePatientMap.put("age", age);
				responsePatientMap.put("gender", gender);
				responsePatientMap.put("genderId", genderId);
				responsePatientMap.put("dateOfBirth", dateOfBirth);
				responsePatientMap.put("serviceTypeId", serviceTypeId);
				responsePatientMap.put("serviceType", serviceType);
				responsePatientMap.put("uhinNo", uhinNo);
				responsePatientMap.put("mobileNumber", mobileNumber);
				responsePatientMap.put("registrationTypeId", registrationTypeId);
				responsePatientMap.put("registrationTypeName", registrationTypeName);
				responsePatientMap.put("identificationTypeId", identificationTypeId);
				responsePatientMap.put("identificationTypeName", identificationTypeName);
				responsePatientMap.put("idNumber", idNumber);
				
				data.put(++rowCount, responsePatientMap);
			}
			
			responseMap.put("count", data.size());
			responseMap.put("data", data);
			responseMap.put("msg", "Record Found");
			responseMap.put("status", 1);
		}else {
			responseMap.put("count", data.size());
			responseMap.put("msg", "No Record Found");
			responseMap.put("status", 0);
		}
		return responseMap;
	}	
		
		
	
	
}

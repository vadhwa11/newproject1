package com.icg.jkt.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icg.jkt.dao.PatientRegistrationDao;
import com.icg.jkt.dao.ReportDao;
//import com.icg.jkt.dao.ReportDao;
import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasAppointmentType;
import com.icg.jkt.entity.MasBloodGroup;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasEmployeeDependent;
import com.icg.jkt.entity.MasIdentificationType;
import com.icg.jkt.entity.MasMedicalCategory;
import com.icg.jkt.entity.MasRegistrationType;
import com.icg.jkt.entity.MasRelation;
import com.icg.jkt.entity.MasServiceType;
import com.icg.jkt.entity.Patient;
import com.icg.jkt.entity.Visit;
import com.icg.jkt.service.PatientRegistrationService;
import com.icg.jkt.utils.HMSUtil;



@Service("PatientRegistrationService")
public class PatientRegistrationServiceImpl implements PatientRegistrationService{

	@Autowired
	PatientRegistrationDao patientRegistrationDao;
	
	@Autowired
	ReportDao reportDao;


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
		
		String dateOfBirth = "";
		String dateME="";
		String empServiceJoinDate="";
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
							empServiceJoinDate=HMSUtil.changeDateToddMMyyyy(patient.getServiceJoinDate());
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
							dateOfBirth = HMSUtil.changeDateToddMMyyyy(patient.getDateOfBirth());
							
							relation = patient.getMasRelation().getRelationName();
							relationId=patient.getMasRelation().getRelationId();
							mobileNumber=patient.getMobileNumber();
							patientAddress = patient.getAddress();
							city = patient.getCity();
							stateId = patient.getMasState().getStateId();
							stateName = patient.getMasState().getStateName();
							patientPincode = patient.getPincode();
							patientEmailId=patient.getEmailId();
							patientBloodGroup = (patient.getMasBloodGroup()!=null?patient.getMasBloodGroup().getBloodGroupName():"");
							patientBloodGroupId = (patient.getMasBloodGroup()!=null?patient.getMasBloodGroup().getBloodGroupId():0);
							
							// Medical category and Date ME only for employee  patient 
							empMedicalCategory=(patient.getMasMedicalCategory()!=null ?patient.getMasMedicalCategory().getMedicalCategoryName():"");
							empMedicalCategoryId=(patient.getMasMedicalCategory()!=null ?patient.getMasMedicalCategory().getMedicalCategoryId():0);
							dateME=(patient.getDateMe()!=null?HMSUtil.changeDateToddMMyyyy(patient.getDateMe()):"");
							
							// NOK1 related details 
							
							nok1Name = patient.getNok1Name();
							nok1RelationId = (patient.getMasRelationNok1()!=null?patient.getMasRelationNok1().getRelationId():0);
							nok1Relation=(patient.getMasRelationNok1()!=null?patient.getMasRelationNok1().getRelationName():"");
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
								dateOfBirth=HMSUtil.changeDateToddMMyyyy(depList.getDateOfBirth());
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
								empServiceJoinDate=HMSUtil.changeDateToddMMyyyy(depList.getMasEmployee().getJoinDate());
								
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
								empServiceJoinDate=HMSUtil.changeDateToddMMyyyy(ms.getJoinDate());
								
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
								dateOfBirth=HMSUtil.changeDateToddMMyyyy(ms.getDateOfBirth());
								
								mobileNumber=ms.getCellNoEmergency();
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
							empServiceJoinDate=HMSUtil.changeDateToddMMyyyy(ms.getJoinDate());
							
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
							dateOfBirth =HMSUtil.changeDateToddMMyyyy(ms.getDateOfBirth());
							
							mobileNumber=ms.getCellNoEmergency();
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
									dateOfBirth = HMSUtil.changeDateToddMMyyyy(depList.getDateOfBirth());
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
									empServiceJoinDate=HMSUtil.changeDateToddMMyyyy(depList.getMasEmployee().getJoinDate());
									
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
		List<HashMap<String, Object>> bloodGroupList = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> departmentList = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> medicalCategoryList = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> masRelationList = new ArrayList<HashMap<String, Object>>();
				
		List<MasBloodGroup>respBloodGroupList = patientRegistrationDao.getBloodGroupList();
		if(!respBloodGroupList.isEmpty() && respBloodGroupList.size()>0) {
			for(MasBloodGroup bloodGroup:respBloodGroupList) {
				HashMap<String,Object> bloodGroupMap=new HashMap<String, Object>();
				bloodGroupMap.put("bloodGroupId", bloodGroup.getBloodGroupId());
				bloodGroupMap.put("bloodGroupName", bloodGroup.getBloodGroupName());
				bloodGroupList.add(bloodGroupMap);
			}
			map.put("bloodGroupList",bloodGroupList);
		}
	
		List<MasDepartment>respDepartmentList = patientRegistrationDao.getDepartmentList();
		if(!respDepartmentList.isEmpty() && respDepartmentList.size()>0) {
			for(MasDepartment department:respDepartmentList) {
				HashMap<String,Object> departmentMap=new HashMap<String, Object>();
				departmentMap.put("departmentId", department.getDepartmentId());
				departmentMap.put("departmentName", department.getDepartmentName());
				departmentList.add(departmentMap);
			}
			map.put("departmentList", departmentList);
		}
		
		List<MasMedicalCategory>respMedicalCategoryList= patientRegistrationDao.getMedicalCategoryList();
		
		if(!respMedicalCategoryList.isEmpty() && respMedicalCategoryList.size()>0) {
			for(MasMedicalCategory medicalCategory:respMedicalCategoryList) {
				HashMap<String,Object> medicalCategoryMap=new HashMap<String, Object>();
				medicalCategoryMap.put("medicalCategoryId", medicalCategory.getMedicalCategoryId());
				medicalCategoryMap.put("medicalCategoryName", medicalCategory.getMedicalCategoryName());
				medicalCategoryList.add(medicalCategoryMap);
			}
			map.put("medicalCategoryList",medicalCategoryList);
		}
		
		List<MasRelation>respMasRelationList =patientRegistrationDao.getRelationList();	
		if(!respMasRelationList.isEmpty() && respMasRelationList.size()>0) {
			for(MasRelation relation:respMasRelationList) {
				HashMap<String,Object> relationMap=new HashMap<String, Object>();
				relationMap.put("relationId", relation.getRelationId());
				relationMap.put("relationName", relation.getRelationName());
				masRelationList.add(relationMap);
			}
			map.put("masRelationList",masRelationList);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getTokenNoForDepartmentMultiVisit(Map<String, Object> requestData) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> rosterValueForToken = new HashMap<String, Object>();
		
		long departmentId=0;
		long hospitalId=0;
		String visitFlag="";
		String tokenMsg="";
		String visitDate="";

		List<Map<String,Object>> jsonList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> jsonInput = (List<Map<String,Object>>) requestData.get("arrParam");

		for(Map<String,Object> json : jsonInput) {   //This loop will require when multi-facility appointment will given in future.
			
			
			 departmentId = Long.parseLong(json.get("deptId").toString());
			 hospitalId = Long.parseLong(json.get("hospitalId").toString());
			 visitFlag= json.get("visitFlag").toString();
			 if( json.get("visitDate")!=null) {
				 visitDate=json.get("visitDate").toString();
			 }
			
			 
			List<String> appointmentTypeList =(List<String>)json.get("appointmentTypeId");
			
			for(int i=0;i<appointmentTypeList.size();i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				long appointmentTypeId = Long.parseLong((appointmentTypeList.get(i)));
				
				rosterValueForToken = patientRegistrationDao.getTokenNoForDepartmentMultiVisit(departmentId,hospitalId,appointmentTypeId,visitFlag,visitDate);
				if(!rosterValueForToken.isEmpty() && rosterValueForToken.size()>0) {
					map.put("tokenMsg",rosterValueForToken.get("tokenMsg"));
					map.put("appointmentTypeId",rosterValueForToken.get("appointmentTypeId"));
					map.put("status","1");
				}else {
					tokenMsg= "Token is not available";
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
		
		long hospitalId =jsonObj.getLong("hospitalId"); // HospitalId will come from session
		long uhidNO=jsonObj.getLong("uhidNo");
	    Date visitDate= null;
		
		if(!jsonObj.getString("visitDate").isEmpty() && jsonObj.getString("visitDate")!=null) {
			String dateString = jsonObj.getString("visitDate");
			visitDate = HMSUtil.convertStringDateToUtilDate(dateString, "dd-MM-yyyy HH:mm:ss");
			
		}else {
			visitDate = new Date();
		}
		
		Patient patient = null;
		
		
		if(uhidNO!=0) {
			String visitFlag="";
			long patientId = patientRegistrationDao.getPatientFromUhidNo(uhidNO);
			
			for(int i=0;i<appointmentType.length();i++) {
				
				boolean existFlag=	patientRegistrationDao.checkExistingAppointmentForPatient(visitDate,hospitalId,departmentId,patientId,Long.parseLong(appointmentType.get(i).toString()));
				
				
				if(!existFlag) {
					Visit visit = new Visit();
					if(!tokenIds.get(i).toString().isEmpty()) {
						visit.setTokenNo(Long.parseLong(tokenIds.get(i).toString()));
					}
					visit.setVisitDate(new Timestamp(visitDate.getTime()));
					visit.setLastChgDate(new Timestamp(visitDate.getTime()));
					
					visit.setPriority(priorityId);
					visit.setVisitStatus("w");
					
					if(!jsonObj.getString("visitFlag").isEmpty()) {
						visitFlag = jsonObj.getString("visitFlag");
						visit.setVisitFlag(visitFlag);
						}
					
					visit.setAppointmentTypeId(Long.parseLong(appointmentType.get(i).toString()));
					visit.setHospitalId(hospitalId);
					visit.setDepartmentId(departmentId);
					visit.setPatientId(patientId);
					
					long appointmentSessionId= patientRegistrationDao.getAppointmentSessionId(hospitalId,departmentId,Long.parseLong(appointmentType.get(i).toString())) ; 
					
					visit.setSessionId(appointmentSessionId);
					
					
					
					long visitId= patientRegistrationDao.saveVisitForRegisteredPatient(visit);
					
					if(visitId!=0) {
						visitList.add(visitId);
					}	
					json.put("status","1");
					json.put("visitList",visitList);
					json.put("msg", "Appointment created successfully");
				}else {
					json.put("status","0");
					json.put("msg", "Appointment is already booked");
				}
			}
			
		}else {
			
			long patientRelationId=0;
			long registrationTypeId=0;
			String serviceNo="";
			
			
			patient = new Patient();
			
			
			if(!jsonObj.getString("empService").isEmpty()) {
				serviceNo = jsonObj.getString("empService");
				patient.setServiceNo(serviceNo);
				}
				
			
			if(jsonObj.getLong("patientRelationId")!=0) {
				patientRelationId=jsonObj.getLong("patientRelationId");
				patient.setRelationId(patientRelationId);
			}
			
			
			if(jsonObj.getLong("registrationTypeId")!=0) {
				registrationTypeId=jsonObj.getLong("registrationTypeId");
				patient.setRegistrationTypeId(registrationTypeId);
			}
			
			
			if(!jsonObj.getString("patientname").isEmpty()) {
				String patientName = jsonObj.getString("patientname");
				patient.setPatientName(patientName);
			}
			
			
			/* uhidNO =(new Double(Math.random()* 50 + 1)).longValue(); 
			patient.setUhidNo(uhidNO); // this need to generate according to logic */
			
			uhidNO = getHinNo(serviceNo,patientRelationId,registrationTypeId);
			patient.setUhidNo(uhidNO);
			
			
			if(!jsonObj.getString("empName").isEmpty()) {
				String empName = jsonObj.getString("empName");
				patient.setEmployeeName(empName);
			}
			
			if(jsonObj.getLong("empId")!=0) {
				long empId = jsonObj.getLong("empId");
				patient.setEmployeeId(empId);
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
					 patient.setDateOfBirth(HMSUtil.convertStringDateToUtilDate(patientDOB, "dd/MM/yyyy"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			 }
			 
			 
			 if(!jsonObj.getString("date").isEmpty()) {
				 String dateME = jsonObj.getString("date");
				 try {
					 patient.setDateMe(HMSUtil.convertStringDateToUtilDate(dateME, "dd/MM/yyyy"));
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
				patient.setRankId(rankId);
			}
			 
			if(jsonObj.getLong("tradeId")!=0) {
				long tradeId= jsonObj.getLong("tradeId"); // trade/branch
				patient.setTradeId(tradeId);
			}
			 
			if(!jsonObj.getString("duration").isEmpty()) {
				long duration= jsonObj.getLong("duration"); 
				patient.setDuration(duration);
			}
			
			
			 if(!jsonObj.getString("empServiceJoinDate").isEmpty()) {
				 String  empServiceJoinDate=jsonObj.getString("empServiceJoinDate");
					try {
						patient.setServiceJoinDate(HMSUtil.convertStringDateToUtilDate(empServiceJoinDate, "dd/MM/yyyy"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
			
			
			 if(jsonObj.getLong("unitId")!=0) {
				 long unitId=jsonObj.getLong("unitId");
				 patient.setUnitId(unitId);
			 }
			
			 
			 if(jsonObj.getLong("regionId")!=0) {
				 long regionId=jsonObj.getLong("regionId");
				 patient.setCommandId(regionId);
			 }
			
			
			if(jsonObj.getLong("recordofficeId")!=0) {
				long  recordofficeId=jsonObj.getLong("recordofficeId");
				patient.setRecordOfficeAddressId(recordofficeId);
			}
			
			
			
			if(jsonObj.getLong("religionId")!=0) {
				long  religionId=jsonObj.getLong("religionId");
				patient.setReligionId(religionId);
			}
			
			
			
			if(jsonObj.getLong("genderId")!=0) {
				long patientGenderId=jsonObj.getLong("genderId");
				patient.setAdministrativeSexId(patientGenderId);
			}
			
			
			if(!jsonObj.getString("patientMoblienumber").isEmpty()) {
				String patientMoblienumber=jsonObj.getString("patientMoblienumber");
				patient.setMobileNumber(patientMoblienumber);
			}
			
			
			
			if(jsonObj.getLong("patientStateId")!=0) {
				long  patientStateId=jsonObj.getLong("patientStateId");
				patient.setStateId(patientStateId);
			}
			
			
			if(!jsonObj.getString("nok1Moblienumber").isEmpty()) {
				String  nok1Moblienumber=jsonObj.getString("nok1Moblienumber");
				patient.setNok1MobileNo(nok1Moblienumber);
			}
		
			
			if(jsonObj.getLong("nok1RelationId")!=0) {
				long  nok1RelationId=jsonObj.getLong("nok1RelationId");
				patient.setNok1RelationId((nok1RelationId));
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
			
			
			
			if(!jsonObj.getString("nok2Relation").isEmpty() && Long.parseLong(jsonObj.get("nok2Relation").toString())!=0) {
				long nok2RelationId=jsonObj.getLong("nok2Relation");
				patient.setNok2RelationId((nok2RelationId));
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
				patient.setMaritalStatusId(maritalstarusId);
			}
			
			if(jsonObj.getLong("bloodGroup")!=0) {
				long bloodGroupId = jsonObj.getLong("bloodGroup");
				patient.setBloodGroupId(bloodGroupId);
			}
			
			
			
			long patientId = patientRegistrationDao.savePatient(patient);
			
			if(patientId!=0) {
				
				
				for(int i=0;i<appointmentType.length();i++) {
					String visitFlag="";
					Visit visit = new Visit();
					
					visit.setTokenNo(Long.parseLong(tokenIds.get(i).toString()));
					visit.setVisitDate(new Timestamp(visitDate.getTime()));
					visit.setLastChgDate(new Timestamp(visitDate.getTime()));
					visit.setPriority(priorityId);
					visit.setVisitStatus("w");
					
					if(!jsonObj.getString("visitFlag").isEmpty()) {
						visitFlag = jsonObj.getString("visitFlag");
						visit.setVisitFlag(visitFlag);
						}
					
					visit.setAppointmentTypeId(Long.parseLong(appointmentType.get(i).toString()));
					visit.setHospitalId(hospitalId);
					visit.setDepartmentId(departmentId);
					visit.setPatientId(patientId);
					
					long appointmentSessionId= patientRegistrationDao.getAppointmentSessionId(hospitalId,departmentId,Long.parseLong(appointmentType.get(i).toString())) ; 
					
					visit.setSessionId(appointmentSessionId);
					
					
					long visitId= patientRegistrationDao.saveVisitForRegisteredPatient(visit);
					if(visitId!=0) {
						visitList.add(visitId);
						
						json.put("status","1");
						json.put("msg","Appointment created successfully.");
						json.put("visitList",visitList);
					}else {
						json.put("status","0");
						json.put("msg","Appointment is not created.");
						json.put("visitList",visitList);
					}
					
				}
			}
			
		}
		return json.toString();
	}


	public long getHinNo(String serviceNo,long patientRelationId,long registrationTypeId) {

		Map<String, Object> serviceAndRelationMap = new HashMap<String, Object>();
		long IcgRegistrationTypeId =Long.parseLong(HMSUtil.getProperties("adt.properties", "ICG_REGISTRATION_TYPE_ID"));
		
		String uhidNO = "";
		String patientCode = "";
		String relationCode = "";
	
		serviceAndRelationMap = patientRegistrationDao
				.getPatientTypeCodeAndRelationCode(patientRelationId,registrationTypeId);

		if (serviceAndRelationMap.get("relationCode") != null) {
			relationCode = (String) serviceAndRelationMap.get("relationCode");
		}
		if (serviceAndRelationMap.get("registrationTypeCode") != null) {
			patientCode = (String) serviceAndRelationMap.get("registrationTypeCode");
		}
		if (registrationTypeId==IcgRegistrationTypeId) {
			uhidNO = patientCode.concat(serviceNo).concat(relationCode);
		} else {
			String maxSequenceNo = "";
			maxSequenceNo = patientRegistrationDao.getHinIdOthers(patientCode);
			Integer i;
			if (!maxSequenceNo.equals("")) {
				i = Integer.parseInt(maxSequenceNo) + 1;

			} else {
				i = 01;
			}
			String seqNo = "";
			if (i <= 9) {
				seqNo = "0" + i.toString();
			} else {
				seqNo = i.toString();
			}
			uhidNO = patientCode.concat(seqNo.toString());
		}

		return Long.parseLong(uhidNO);
	
	}

	@Override
	public Map<String, Object> showAppointmentForOthers() {
		// TODO Auto-generated method stub
		Map<String,Object>map = new HashMap<String, Object>();
		List<HashMap<String, Object>> registrationTypeList = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> departmentList = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> genderList = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> identificationList = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> serviceTypeList = new ArrayList<HashMap<String, Object>>();
				
		List<MasRegistrationType>respRegistrationTypeList = patientRegistrationDao.getRegistrationTypeList();
		if(!respRegistrationTypeList.isEmpty() && respRegistrationTypeList.size()>0) {
			for(MasRegistrationType registrationType:respRegistrationTypeList) {
				HashMap<String,Object> registrationTypetMap=new HashMap<String, Object>();
				registrationTypetMap.put("registrationTypeId", registrationType.getRegistrationTypeId());
				registrationTypetMap.put("registrationTypeName", registrationType.getRegistrationTypeName());
				registrationTypeList.add(registrationTypetMap);
			}
			map.put("registrationTypeList",registrationTypeList);
		}
		
		
		List<MasDepartment>respDepartmentList = patientRegistrationDao.getDepartmentList();
		if(!respDepartmentList.isEmpty() && respDepartmentList.size()>0) {
			for(MasDepartment department:respDepartmentList) {
				HashMap<String,Object> departmentMap=new HashMap<String, Object>();
				departmentMap.put("departmentId", department.getDepartmentId());
				departmentMap.put("departmentName", department.getDepartmentName());
				departmentList.add(departmentMap);
			}
			map.put("departmentList", departmentList);
		}
		
		
		List<MasAdministrativeSex>respGenderList= patientRegistrationDao.getGenderList();
		if(!respGenderList.isEmpty() && respGenderList.size()>0) {
			for(MasAdministrativeSex gender:respGenderList) {
				HashMap<String,Object> genderMap=new HashMap<String, Object>();
				genderMap.put("administrativeSexId", gender.getAdministrativeSexId());
				genderMap.put("administrativeSexName", gender.getAdministrativeSexName());
				genderList.add(genderMap);
			}
			map.put("genderList",genderList);
		}
		
		
		List<MasIdentificationType>respIdentificationList= patientRegistrationDao.getIdentificationList();
		if(!respIdentificationList.isEmpty() && respIdentificationList.size()>0) {
			for(MasIdentificationType identificationType:respIdentificationList) {
				HashMap<String,Object> identificationTypeMap=new HashMap<String, Object>();
				identificationTypeMap.put("identificationTypeId", identificationType.getIdentificationTypeId());
				identificationTypeMap.put("identificationTypetName", identificationType.getIdentificationName());
				identificationList.add(identificationTypeMap);
			}
			map.put("identificationList",identificationList);
		}
		
		List<MasServiceType>respServiceTypeList=patientRegistrationDao.getServiceTypeList();
		if(!respServiceTypeList.isEmpty() && respServiceTypeList.size()>0) {
			for(MasServiceType serviceType:respServiceTypeList) {
				HashMap<String,Object> serviceTypeMap=new HashMap<String, Object>();
				serviceTypeMap.put("serviceTypeId", serviceType.getServiceTypeId());
				serviceTypeMap.put("serviceTypeName", serviceType.getServiceTypeName());
				serviceTypeList.add(serviceTypeMap);
			}
			map.put("serviceTypeList",serviceTypeList);
		}
		
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
		String visitFlag="";
		String visitDate="";
	
		 departmentId = Long.parseLong(requestData.get("deptId").toString());
		 hospitalId = Long.parseLong(requestData.get("hospitalId").toString());
		 appointmentTypeId=Long.parseLong(requestData.get("appointmentTypeId").toString());
		 visitFlag= requestData.get("visitFlag").toString();
		 
		 rosterValueForToken = patientRegistrationDao.getTokenNoForDepartmentMultiVisit(departmentId,hospitalId,appointmentTypeId,visitFlag,visitDate); 
		
		 if(!rosterValueForToken.isEmpty() && rosterValueForToken.size()>0) {
				tokenMsg= (String)rosterValueForToken.get("tokenMsg");
				appointmentTypeId = (long) rosterValueForToken.get("appointmentTypeId");
				map.put("tokenMsg",tokenMsg);
				map.put("appointmentTypeId",appointmentTypeId);
				map.put("status","1");
			}else {
				tokenMsg= "Token is not available";
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
		String msg="";
		Patient patient =null;
		long hospitalId=jsonObj.getLong("hospitalId");// This will fetch from session
		
		if(!jsonObj.get("uhidNoPatient").toString().isEmpty()) {
			long uhidNO =Long.parseLong(jsonObj.get("uhidNoPatient").toString());
			long patientId = patientRegistrationDao.getPatientFromUhidNo(uhidNO);
			
			Date date= new Date();
			Timestamp dateTime = new Timestamp(date.getTime());
			
			Visit visit = new Visit();

			visit.setVisitDate(dateTime);
			visit.setLastChgDate((dateTime));
			visit.setVisitStatus("w");
			
			
			long departmentId=0; 
			if (!jsonObj.get("department").toString().isEmpty() && Long.parseLong(jsonObj.get("department").toString())!=0) {
				 departmentId = Long.parseLong(jsonObj.get("department").toString());
				visit.setDepartmentId(departmentId);
			}	
			
			long appointmentTypeId=0;
			if (!jsonObj.get("checkDiv").toString().isEmpty()) {
				appointmentTypeId = Long.parseLong(jsonObj.get("checkDiv").toString());
				visit.setAppointmentTypeId(appointmentTypeId);
			}
			
			
			visit.setHospitalId(hospitalId);
			visit.setPatientId(patientId);
			
			long appointmentSessionId= patientRegistrationDao.getAppointmentSessionId(hospitalId,departmentId,appointmentTypeId) ; 
			
			visit.setSessionId(appointmentSessionId);
			
			
			String visitFlag="";
			if(!jsonObj.getString("visitFlag").isEmpty()) {
				visitFlag = jsonObj.getString("visitFlag");
				visit.setVisitFlag(visitFlag);
				}
			

			
			if (!jsonObj.get("priority").toString().isEmpty() && Long.parseLong(jsonObj.get("priority").toString())!=0) {
				long priority = Long.parseLong(jsonObj.get("priority").toString());
				visit.setPriority(priority);
			}
			if (!jsonObj.get("tokenNo").toString().isEmpty()) {
				long tokenNo = Long.parseLong(jsonObj.get("tokenNo").toString());
				visit.setTokenNo(tokenNo);

			}
			boolean existFlag=	patientRegistrationDao.checkExistingAppointmentForPatient(dateTime,hospitalId,departmentId,patientId,appointmentTypeId);
			if(!existFlag) {
				visitId= patientRegistrationDao.saveVisitForRegisteredPatient(visit);
				msg= "Appointment created sucessfully.";
				
				json.put("visitId",visitId);
				json.put("status","1");
				json.put("msg",msg);
			}else {
				json.put("status","0");
				json.put("msg","Appointment is already booked.");
				
			}
		}else {
			
			String mobileNo="";
			if(!jsonObj.get("mobilenumber").toString().isEmpty()) {
				mobileNo=jsonObj.get("mobilenumber").toString();
			}
			
			boolean existingFlag = patientRegistrationDao.checkExistingOtherPatient(mobileNo);
			if(!existingFlag) {
				// new patient will create
				patient =new Patient();
				
				if(!jsonObj.get("mobilenumber").toString().isEmpty()) {
					String mobilenumber = jsonObj.get("mobilenumber").toString();
					patient.setMobileNumber(mobilenumber);
					}

				if(!jsonObj.get("idnumber").toString().isEmpty()) {
					String idnumber=jsonObj.get("idnumber").toString();
					patient.setIdentificationNo(idnumber);
				}
				
				long registrationType=0;
				if(!jsonObj.get("registrationType").toString().isEmpty() && Long.parseLong(jsonObj.get("registrationType").toString())!=0) {
				registrationType = Long.parseLong(jsonObj.get("registrationType").toString());
				patient.setRegistrationTypeId(registrationType);
				
				}
				if(!jsonObj.get("gender").toString().isEmpty()) {
				long gender = Long.parseLong(jsonObj.get("gender").toString());
				patient.setAdministrativeSexId(gender);
				
				}
				if(!jsonObj.get("serviceTypeId").toString().isEmpty() && Long.parseLong(jsonObj.get("serviceTypeId").toString())!=0) {
				long serviceTypeId = Long.parseLong(jsonObj.get("serviceTypeId").toString());
				patient.setServiceStatusId(serviceTypeId);;
				}
				
				if(!jsonObj.get("rank").toString().isEmpty()) {
				String rank = jsonObj.get("rank").toString();
				patient.setOtherRank(rank);
				}
				
				
				
				
				if(!jsonObj.get("identification").toString().isEmpty() && Long.parseLong(jsonObj.get("identification").toString())!=0) {
				long identification = Long.parseLong(jsonObj.get("identification").toString());
				patient.setIdentificationTypeId(identification);;
				}
				
				
				String serviceNo="";
				if(!jsonObj.get("serviceNo").toString().isEmpty()) {
				serviceNo= jsonObj.get("serviceNo").toString();
				patient.setServiceNo(serviceNo);
				}
				
				long patientRelationId= Long.parseLong(HMSUtil.getProperties("adt.properties", "SELF_RELATION_ID"));
				long uhidNO = getHinNo(serviceNo,patientRelationId,registrationType);
				patient.setUhidNo(uhidNO);
				
				
				
				String patientName = "";

				if(!jsonObj.get("firstname").toString().isEmpty()) {
					String firstname = jsonObj.get("firstname").toString();
					patientName=firstname;
				}
				
				
				patient.setPatientName(patientName);
				
				if(!jsonObj.get("dataOfBirth").toString().isEmpty()) {
				String dataOfBirth = jsonObj.get("dataOfBirth").toString();
				 try {
				//patient.setDateOfBirth(HMSUtil.convertStringDateToUtilDateForDatabase(dataOfBirth));
				  patient.setDateOfBirth(HMSUtil.convertStringDateToUtilDate(dataOfBirth, "yyyy-MM-dd"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}

				long patientId = patientRegistrationDao.savePatient(patient);
				
				if(patientId!=0) {
					Visit visit = new Visit();

					Date date = new Date();
					Timestamp dateTime = new Timestamp(date.getTime());

					visit.setVisitDate(dateTime);
					visit.setLastChgDate((dateTime));
					visit.setVisitStatus("w");
					

					long departmentId=0; 
					if (!jsonObj.get("department").toString().isEmpty() && Long.parseLong(jsonObj.get("department").toString())!=0) {
						 departmentId = Long.parseLong(jsonObj.get("department").toString());
						visit.setDepartmentId(departmentId);
					}	
					
					long appointmentTypeId=0;
					if (!jsonObj.get("checkDiv").toString().isEmpty()) {
						appointmentTypeId = Long.parseLong(jsonObj.get("checkDiv").toString());
						visit.setAppointmentTypeId(appointmentTypeId);
					}
					
					
					visit.setHospitalId(hospitalId);
					visit.setPatientId(patientId);
					
					long appointmentSessionId= patientRegistrationDao.getAppointmentSessionId(hospitalId,departmentId,appointmentTypeId) ; 
					
					visit.setSessionId(appointmentSessionId);
					
					
					String visitFlag="";
					if(!jsonObj.getString("visitFlag").isEmpty()) {
						visitFlag = jsonObj.getString("visitFlag");
						visit.setVisitFlag(visitFlag);
						}
					

					
					if (!jsonObj.get("priority").toString().isEmpty() && Long.parseLong(jsonObj.get("priority").toString())!=0) {
						long priority = Long.parseLong(jsonObj.get("priority").toString());
						visit.setPriority(priority);
					}
					if (!jsonObj.get("tokenNo").toString().isEmpty()) {
						long tokenNo = Long.parseLong(jsonObj.get("tokenNo").toString());
						visit.setTokenNo(tokenNo);

					}
					visitId= patientRegistrationDao.saveVisitForRegisteredPatient(visit);
					json.put("visitId",visitId);
					json.put("status","1");
					json.put("msg","Appointment created successfully.");
				}
			}else {
				json.put("status","0");
				json.put("msg","Patient is already registred.");
			}
		}
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
				serviceNo =(patient.getServiceNo()!=null?patient.getServiceNo():"");
				name = patient.getPatientName();
				rank=(patient.getOtherRank()!=null?patient.getOtherRank():"");
				age = HMSUtil.calculateAgeNoOfYear(patient.getDateOfBirth());
				gender = patient.getMasAdministrativeSex().getAdministrativeSexName();
				genderId = patient.getMasAdministrativeSex().getAdministrativeSexId();
				dateOfBirth = patient.getDateOfBirth();
				serviceTypeId=(patient.getMasServiceType()!=null?patient.getMasServiceType().getServiceTypeId():0);
				serviceType=(patient.getMasServiceType()!=null?patient.getMasServiceType().getServiceTypeName():"");
				uhinNo=patient.getUhidNo();
				mobileNumber=patient.getMobileNumber();
				registrationTypeId=(patient.getMasRegistrationType()!=null?patient.getMasRegistrationType().getRegistrationTypeId():0);
				registrationTypeName=(patient.getMasRegistrationType()!=null?patient.getMasRegistrationType().getRegistrationTypeName():"");
				identificationTypeId= (patient.getMasIdentificationType()!=null?patient.getMasIdentificationType().getIdentificationTypeId():0);
				identificationTypeName= (patient.getMasIdentificationType()!=null?patient.getMasIdentificationType().getIdentificationName():"");
				idNumber = (patient.getIdentificationNo()!=null?patient.getIdentificationNo():"");
				
				
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


	@Override
	public Map<String, Object> patientListForUploadDocument(Map<String, String> requestData) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<Integer, Map<String, Object>> data = new HashMap<Integer, Map<String, Object>>();
		List<Patient> patientList = new ArrayList<Patient>();
		String serviceNo="";
		
		if(!requestData.get("serviceNo").isEmpty() && requestData.get("serviceNo")!=null) {
			serviceNo =requestData.get("serviceNo").toString();
			
			patientList=patientRegistrationDao.patientListForUploadDocument(serviceNo);
			if(!patientList.isEmpty() && patientList.size()>0) {
				int rowCount=0;
				for(Patient patient: patientList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("patientId", patient.getPatientId());
					map.put("patientName", patient.getPatientName());
					map.put("uhidNo", patient.getUhidNo());
					
					data.put(++rowCount, map);
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
		}
		
		return responseMap;
	}
	

	@Override
	public Map<String, Object> patientAppointmentHistory(Map<String, String> requestData) {
		List<Visit> patientHistoryList = new ArrayList<Visit>();
		List<Map<String,Object>> responseList = new ArrayList<Map<String,Object>>();
		Map<String,Object> responseMap=new HashMap<String, Object>();
		long uhidNo=0;
		String startDate="";
		String endDate="";
		
		if(!requestData.get("uhidNo").isEmpty() && requestData.get("uhidNo")!=null) {
			uhidNo =Long.parseLong(requestData.get("uhidNo").toString());
		}
		
		if(!requestData.get("startDate").isEmpty() && requestData.get("startDate")!=null) {
			startDate =requestData.get("startDate").toString();
		}
		
		if(!requestData.get("endDate").isEmpty() && requestData.get("endDate")!=null) {
			endDate =requestData.get("endDate").toString();
		}
		
		
		
		patientHistoryList = patientRegistrationDao.getPatientAppointmentHistory(uhidNo,startDate,endDate);
		if(patientHistoryList!=null && patientHistoryList.size()>0) {
			for(Visit visit : patientHistoryList) {
				Map<String,Object>map=new HashMap<String, Object>();
				map.put("departmentName", visit.getMasDepartment().getDepartmentName());
				map.put("departmentId", visit.getMasDepartment().getDepartmentId());
				map.put("doctorName", "DoctorName");
				map.put("appointmentDate", HMSUtil.changeDateToddMMyyyy(visit.getVisitDate()));
				map.put("tokenNo", visit.getTokenNo());
				map.put("hospitalName", visit.getMasHospital().getHospitalName());
				map.put("hospitalId", visit.getMasHospital().getHospitalId());
				map.put("visitStatus", visit.getVisitStatus());
				map.put("visitId", visit.getVisitId());
				map.put("appointmentTypeId", visit.getMasAppointmentType().getAppointmentTypeId());
				map.put("appointmentTypeName", visit.getMasAppointmentType().getAppointmentTypeName());
				responseList.add(map);
			}
			if (responseList != null && responseList.size() > 0) {
				responseMap.put("patientHistoryList", responseList);
				responseMap.put("status", 1);
			} 
		}else {
			responseMap.put("patientHistoryList", responseList);
			responseMap.put("msg", "No History Available");
			responseMap.put("status", 0);
		}
		return responseMap;
	}


	@Override
	public Map<String,Object> patientVisitCancellation(Map<String, String> requestData) {
		Map<String,Object> map =  new HashMap<String, Object>();
		long visitId=0;
		boolean status=false;
		
		if(!requestData.get("visitId").isEmpty() && requestData.get("visitId")!=null) {
			visitId =Long.parseLong(requestData.get("visitId").toString());
		}
		status = patientRegistrationDao.getPatientVisitCancellation(visitId);
		if(status) {
			map.put("msg", "Appointment cancelled");
		}else {
			map.put("msg", "Appointment can not be cancelled");
		}
		return map;
	}



	@Override
	public String getAppointmentTypeList(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObj = new JSONObject();
		List<MasAppointmentType> appList = patientRegistrationDao.getAppointmentTypeList();
		List<Map<String,Object>> appointmentTypeList = new ArrayList<Map<String,Object>>();
		if(appList !=null && appList.size()>0) {
		for(MasAppointmentType mas :appList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id",mas.getAppointmentTypeId());
			map.put("appointmentType", mas.getAppointmentTypeName());
			appointmentTypeList.add(map);
		}
		jsonObj.put("data", appointmentTypeList);
		}
	
		System.out.println(jsonObj.toString());
		return jsonObj.toString();
	}


	


	
}

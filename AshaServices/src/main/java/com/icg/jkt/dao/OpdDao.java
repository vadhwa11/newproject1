package com.icg.jkt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.DgOrderdt;
import com.icg.jkt.entity.DgOrderhd;
import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasRelation;
import com.icg.jkt.entity.OpdObesityHd;
import com.icg.jkt.entity.OpdPatientDetail;
import com.icg.jkt.entity.OpdPatientHistory;
import com.icg.jkt.entity.OpdTemplate;
import com.icg.jkt.entity.OpdTemplateInvestigation;
import com.icg.jkt.entity.Patient;
import com.icg.jkt.entity.PatientFamilyHistory;
import com.icg.jkt.entity.PatientPrescriptionDt;
import com.icg.jkt.entity.PatientPrescriptionHd;
import com.icg.jkt.entity.Visit;

@Repository
public interface OpdDao {

	
	String saveOpdPatientDetails(HashMap<String, Object> jsondata);

	

	List<MasEmployee> getEmployee();

	int listPaginatedVisit(int firstResult, int maxResults);

	List<Object[]> getSearchPatinet(String patinetName);

	//List<MasIdealWeight> getIdealWeight(String height);
	
	

	List<Visit> getPatientVisit(Long visitId);

	List<OpdPatientDetail> getVitalRecord(Long visitId);

	List<PatientFamilyHistory> getFamilyHistory();

	String opdPatinetHistory(OpdPatientHistory ob);

	OpdPatientDetail checkVisitOpdPatientDetails(Long visitId);

	OpdPatientHistory checkVisitOpdPatientHistory(Long visitId);

	String opdTemplate(OpdTemplate ob,OpdTemplateInvestigation opdinv);

	String opdTemplatenewMethod(OpdTemplate opdTemp, List<OpdTemplateInvestigation> opdInvestigationList);

	String saveOpdInvestigation(DgOrderhd orderhd, List<DgOrderdt> listofOpdInvest);

	String saveOpdPrescription(PatientPrescriptionHd pphd, List<PatientPrescriptionDt> patientPrescDT);

	String opdObsisty(OpdObesityHd oohd);
	
	String opdVitalDetails(OpdPatientDetail ob);

	MasEmployee checkEmp(Long i);

	List<Visit> getVisit();

	Patient checkPatient(Long i);

	MasAdministrativeSex checkGender(Long i);

	List<Patient> getPatinet();

	MasRelation checkRelation(Long i);
	
	Map<String, Object> getObesityWaitingList(HashMap<String, Object> jsondata);
	
	Map<String,Object> getObesityDetails(HashMap<String, Object> jsondata);
	
	List<String> getIdealWeight(Long height,String age);
	
	String saveObesityDetails(HashMap<String, Object> jsondata);
	
	Map<String,Object> referredPatientList(HashMap<String, String> jsondata);
	
	Map<String,Object> referredPatientDetail(HashMap<String, String> jsondata);
	
	 Map<String,Object> updateReferralDetail(HashMap<String, Object> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	Map<String,Object> getAdmissionDischargeList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	Map<String,Object> admissionAndDischarge(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	String savePatientAdmission(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	Map<String,Object> getServiceWisePatientList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	String saveNewAdmission(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	Map<String,Object> nursingCareWaitingList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	Map<String,Object> getNursingCareDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response);
	
	Map<String,Object> getProcedureDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response);  
	
	String saveProcedureDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response);
	}
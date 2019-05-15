package com.icg.jkt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.DgOrderdt;
import com.icg.jkt.entity.DgOrderhd;
import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasAnesthesia;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasRelation;
import com.icg.jkt.entity.OpdObesityHd;
import com.icg.jkt.entity.OpdPatientDetail;
import com.icg.jkt.entity.OpdPatientHistory;
import com.icg.jkt.entity.OpdTemplate;
import com.icg.jkt.entity.OpdTemplateInvestigation;
import com.icg.jkt.entity.OpdTemplateTreatment;
import com.icg.jkt.entity.Patient;
import com.icg.jkt.entity.PatientFamilyHistory;
import com.icg.jkt.entity.PatientPrescriptionDt;
import com.icg.jkt.entity.PatientPrescriptionHd;
import com.icg.jkt.entity.ProcedureDt;
import com.icg.jkt.entity.Users;
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

	List<OpdPatientDetail> getPreviousVitalRecord(Long patientId);
	
	List<PatientFamilyHistory> getFamilyHistory();

	String opdPatinetHistory(OpdPatientHistory ob);

	OpdPatientDetail checkVisitOpdPatientDetails(Long visitId);

	OpdPatientHistory checkVisitOpdPatientHistory(Long visitId);

	String opdTemplate(OpdTemplate ob,OpdTemplateInvestigation opdinv);

	String opdTemplatenewMethod(OpdTemplate opdTemp, List<OpdTemplateInvestigation> opdInvestigationList);
	
	String saveTreatTemplatenewMethod(OpdTemplate opdTemp, List<OpdTemplateTreatment> opdTreatmentTempList);

	String saveOpdInvestigation(DgOrderhd orderhd, List<DgOrderdt> listofOpdInvest);

	String saveOpdPrescription(PatientPrescriptionHd pphd, List<PatientPrescriptionDt> patientPrescDT);

	String opdObsisty(HashMap<String, Object> jsondata);
	
	String opdVitalDetails(OpdPatientDetail ob);

	MasEmployee checkEmp(Long i);

	Map<String,Object> getVisit(HashMap<String,Object> json);
	
	Map<String, Object> getPreconsulationVisit(HashMap<String, Object> jsonData);

	Patient checkPatient(Long i);

	MasAdministrativeSex checkGender(Long i);

	List<Patient> getPatinet();

	MasRelation checkRelation(Long i);

	List<Object[]> getPreviousVisitRecord(Long patientId);

	List<OpdPatientHistory> getPreviousVisitHistory(Long patientId);
	
	Map<String, Object> getObesityWaitingList(HashMap<String, Object> jsondata);
	
	Map<String,Object> getObesityDetails(HashMap<String, Object> jsondata);
	
	List<String> getIdealWeight(Long height,String age,Long genderId);
	
	String saveObesityDetails(HashMap<String, Object> jsondata);
	
	Map<String,Object> referredPatientList(HashMap<String, String> jsondata);
	
	Map<String,Object> referredPatientDetail(HashMap<String, String> jsondata);
	
	 Map<String,Object> updateReferralDetail(HashMap<String, Object> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	Map<String,Object> getAdmissionDischargeList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	public Map<String,Object> getPendingDischargeList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	Map<String,Object> admissionAndDischarge(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	String savePatientAdmission(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	Map<String,Object> getServiceWisePatientList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	String saveNewAdmission(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	Map<String,Object> nursingCareWaitingList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response);
	
	Map<String,Object> getNursingCareDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response);
	
	Map<String,Object> getProcedureDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response);  
	
	String saveProcedureDetail(Map<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response);
	
	Map<String, Object> physioTherapyWaitingList(Map<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response);
	
	Map<String,Object> getphysioTherapyDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response);

	Map<String, Object> getOpdReportsDetailsbyServiceNo(HashMap<String, Object> jsonData);

	Map<String, Object> getOpdReportsDetailsbyPatinetId(HashMap<String, Object> jsonData);

	Users checkUser(Long i);

	//////////////by dhiraj /////////////////////////
	Map<String, Object> minorSurgeryWaitingList(HashMap<String, String> jsondata, HttpServletRequest request,
	HttpServletResponse response);

	Map<String,Object> getMinorSurgeryDetail(HashMap<String, String> jsondata, HttpServletRequest request,
	HttpServletResponse response);
	List<MasAnesthesia> getAnesthesiaList();
	String saveMinorSurgery(HashMap<String, Object> jsondata);
	String deleteMinorSurgery(HashMap<String, Object> jsondata);
	





	
	}

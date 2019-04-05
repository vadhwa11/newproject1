package com.icg.jkt.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.icg.jkt.utils.RequestConstants;
import com.icg.jkt.utils.ValidateUtils;
import com.icg.jkt.dao.OpdDao;
import com.icg.jkt.entity.AdmissionDischarge;
import com.icg.jkt.entity.DgOrderdt;
import com.icg.jkt.entity.DgOrderhd;
import com.icg.jkt.entity.MasDisposal;
import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasEmployeeDepartment;
import com.icg.jkt.entity.MasRelation;
import com.icg.jkt.entity.OpdObesityDt;
import com.icg.jkt.entity.OpdObesityHd;
import com.icg.jkt.entity.OpdPatientDetail;
import com.icg.jkt.entity.OpdPatientHistory;
import com.icg.jkt.entity.OpdTemplate;
import com.icg.jkt.entity.OpdTemplateInvestigation;
import com.icg.jkt.entity.Patient;
import com.icg.jkt.entity.PatientFamilyHistory;
import com.icg.jkt.entity.PatientPrescriptionDt;
import com.icg.jkt.entity.PatientPrescriptionHd;
import com.icg.jkt.entity.ProcedureDt;
import com.icg.jkt.entity.ProcedureHd;
import com.icg.jkt.entity.ReferralPatientDt;
import com.icg.jkt.entity.ReferralPatientHd;
import com.icg.jkt.entity.Registration;
import com.icg.jkt.entity.Visit;
import com.icg.jkt.hibernateutils.GetHibernateUtils;
import com.icg.jkt.service.OpdService;
import com.icg.jkt.utils.CommonUtil;
import com.icg.jkt.utils.DateTimeUtil;
import com.icg.jkt.utils.HMSUtil;
import com.icg.jkt.utils.ICGUtils;
import com.icg.jkt.utils.ProjectUtils;
import lombok.*;


@Repository
public class OpdServiceImpl implements OpdService {

	@Autowired
	GetHibernateUtils getHibernateUtils;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	OpdDao od;

	
	
	
	

	@Override
	public String PreConsPatientWatingList(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID as a  key or value or it is null\"}";
			} else {
				
				MasEmployee checkEmp = od.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
				
				if (checkEmp != null) {
					
					List<Visit> getvisit = od.getVisit();
					List<Patient> getPatinet = od.getPatinet();
					

					if (getvisit.size() == 0) {
						return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
					}

					else {

						List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
						
						for (Visit v : getvisit) {
							
							Patient p = od.checkPatient(v.getPatientId());
							//MasEmployee me=od.checkEmp(v.getDoctorId());
						    
							for(Patient p1 : getPatinet)
							{
                          	 MasAdministrativeSex mg=od.checkGender(p1.getAdministrativeSexId());
                          	 MasRelation mr=od.checkRelation(p1.getRelationId());
							 //for(MasEmployee ms : getDoctor)
							 //{
							  
							  if (p!= null ) {
								HashMap<String, Object> pt = new HashMap<String, Object>();
								pt.put("status", v.getVisitStatus());
								pt.put("tokenNo", v.getTokenNo());
								pt.put("priority", v.getPriority());
								pt.put("employeeName", p.getEmployeeName());
								pt.put("serviceNo", p.getServiceNo());
								pt.put("patientName", p.getPatientName());
								pt.put("dateOfBirth", p.getDateOfBirth());
								pt.put("gender", mg.getAdministrativeSexName());
								pt.put("relationName", mr.getRelationName());
								pt.put("doctorname", checkEmp.getFirstName());
								pt.put("departmentName",v.getMasDepartment().getDepartmentName());
								//pt.put("patientName", v.getPatient().getPatientName());
								c.add(pt);
							}
							//}
							}

						}

						json.put("Visit List", c);
						// json.put("Visit List", getvisit);
						json.put("msg", "Visit List  get  sucessfull... ");
						json.put("status", "1");

					}

				} else {
					return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";

				}

				return json.toString();
			}
		} finally {
			System.out.println("Exception Occured");
		}

		} 
	

	///////////////////////////// Mapped Multiple Table /////////////////////////////////////////////
	
	@Override
	public String PreConsPatientWatingListMapped(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
	
			if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE ID as a  key or value or it is null\"}";
			} else {
				
				MasEmployee checkEmp = od.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
				
				if (checkEmp != null ) {
					
					List<Visit> getvisit = od.getVisit();
					if (getvisit.size() == 0)
					{
						return "{\"status\":\"0\",\"msg\":\"Visit Data not found\"}";
					}

					else {

						List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
						try
						{
							
						for (Visit v : getvisit) {
							 LocalDate today = LocalDate.now();
							 v.getVisitDate();
							
							if (v.getVisitStatus().equals("w")){
								
								HashMap<String, Object> pt = new HashMap<String, Object>();
							    
								Date s=v.getPatient().getDateOfBirth();
							    Calendar lCal = Calendar.getInstance();
							    lCal.setTime(s);
                                int yr=lCal.get(Calendar.YEAR);
                                int mn=lCal.get(Calendar.MONTH) + 1;
                                int dt=lCal.get(Calendar.DATE);
                               
								System.out.println("today"+today);//Today's date
								LocalDate birthday = LocalDate.of(yr,mn,dt) ; //Birth date
								System.out.println("birthday"+birthday);
								Period p = Period.between(birthday, today);
								
								pt.put("status", v.getVisitStatus());
								pt.put("tokenNo", v.getTokenNo());
								pt.put("priority", v.getPriority());
								pt.put("employeeName", v.getPatient().getEmployeeName());
								pt.put("serviceNo", v.getPatient().getServiceNo());
								pt.put("patientName", v.getPatient().getPatientName());
								pt.put("age", p.getYears());
								pt.put("gender", v.getPatient().getMassex().getAdministrativeSexName());
								pt.put("relation", v.getPatient().getMasrelation().getRelationName());
								pt.put("doctorname", checkEmp.getFirstName());
								pt.put("departmentName",v.getMasDepartment().getDepartmentName());
								pt.put("status", v.getVisitStatus());
								pt.put("priority", v.getPriority());
								pt.put("visitId", v.getVisitId());
								pt.put("patientId", v.getPatient().getPatientId());
								//pt.put("patientName", v.getPatient().getPatientName());
								c.add(pt);							
								
							}						
						
						}
						if(c != null && c.size()>0){
							json.put("data", c);
							json.put("count", c.size());
							json.put("msg", "Visit List  get  sucessfull... ");
							json.put("status", "1");
						}else{
							return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}";
						}

					}
					catch(Exception e)
					{
					  System.out.println(e);
					}

					}
				}
					else
				{
					return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";

				}

				return json.toString();
			}
		
	}
	
	
	@Override
	public String addVitalPreConsulataionDetails(HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {

		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		OpdPatientDetail opddetails = new OpdPatientDetail();
		
		try {
			if (!payload.isEmpty()) {
				JSONObject nullbalankvalidation = null;
				nullbalankvalidation = ValidateUtils.addVitalPreConsulataionDetailsvalidation(payload);
				if (nullbalankvalidation.optString("status").equals("0")) {
					return nullbalankvalidation.toString();
				} else {

					//opddetails.setUhidNo(Long.parseLong(payload.get("uhidNo").toString()));
					opddetails.setPatientId(Long.parseLong(payload.get("patientId").toString()));
					opddetails.setVisitId(Long.parseLong(payload.get("visitId").toString()));
					opddetails.setHeight(payload.get("height").toString());
					opddetails.setWeight(payload.get("weight").toString());
					opddetails.setIdealWeight(payload.get("idealWeight").toString());
					BigDecimal bd = new BigDecimal(payload.get("varation").toString());
					opddetails.setVaration(bd);
					opddetails.setTemperature(payload.get("temperature").toString());
					opddetails.setBp(payload.get("bp").toString());
					opddetails.setPulse(payload.get("pulse").toString());
					opddetails.setSpo2(payload.get("spo2").toString());
					opddetails.setBmi(payload.get("bmi").toString());
					opddetails.setRr(payload.get("rr").toString());
					
				

					String resp = od.opdVitalDetails(opddetails);
				

					if (resp != null && resp.equalsIgnoreCase("200")) {
						json.put("msg", " Vitals Details Insert successfully ");
						json.put("status", "1");
					} else if (resp != null && resp.equalsIgnoreCase("403")) {
						json.put("msg", " you are not authorized for this activity ");
						json.put("status", "0");
					} else {
						json.put("msg", resp);
						json.put("status", "0");
					}
				}
			} else {
				json.put("status", "0");
				json.put("msg", "json not contain any object");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json.toString();
	}


	//////////////////// OPD Waiting List API /////////////////////////////////////////


	@Override
	public String OpdPatientWatingList(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
	
		JSONObject json = new JSONObject();
		if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE ID as a  key or value or it is null\"}";
			} else {
				
				MasEmployee checkEmp = od.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
				
				if (checkEmp != null) {
					
					List<Visit> getvisit = od.getVisit();
					if (getvisit.size() == 0) {
						return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
					}

					else {

						List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
						
						try{
						for (Visit v : getvisit) {
							if (v.getVisitStatus().equals("w")||v.getVisitStatus().equals("p")){					
								HashMap<String, Object> pt = new HashMap<String, Object>();
								
								Date s=v.getPatient().getDateOfBirth();
							    Calendar lCal = Calendar.getInstance();
							    lCal.setTime(s);
                                int yr=lCal.get(Calendar.YEAR);
                                int mn=lCal.get(Calendar.MONTH) + 1;
                                int dt=lCal.get(Calendar.DATE);
                                LocalDate today = LocalDate.now();
								System.out.println("today"+today);//Today's date
								LocalDate birthday = LocalDate.of(yr,mn,dt) ; //Birth date
								System.out.println("birthday"+birthday);
								Period p = Period.between(birthday, today);
								
								pt.put("visitId", v.getVisitId());
								pt.put("patientId", v.getPatientId());
								pt.put("tokenNo", v.getTokenNo());
								pt.put("serviceNo", v.getPatient().getServiceNo());
								pt.put("patinetname", v.getPatient().getPatientName());
								pt.put("mobileNumber", v.getPatient().getMobileNumber());
								pt.put("age", p.getYears());
								pt.put("gender", v.getPatient().getMassex().getAdministrativeSexName());
							    pt.put("relation", v.getPatient().getMasrelation().getRelationName());
								pt.put("empName",v.getPatient().getEmployeeName());
								pt.put("rankName", v.getPatient().getMasRank().getRankName());
								pt.put("departmentName",v.getMasDepartment().getDepartmentName());
							
								c.add(pt);
							}
						}
						if(c != null && c.size()>0){
							json.put("data", c);
							json.put("count", c.size());
							json.put("msg", "Visit List  get  sucessfull... ");
							json.put("status", "1");
						}else{
							return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}";
						}

					}
						
						 catch(Exception e)
						{
							 return "{\"status\":\"0\",\"msg\":\"Somting went wrong}";
						}
					}

				} else
				{
					return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE ID Not Found\"}";

				}

				return json.toString();
			}
		
	}




	@Override
	public String searchPatientWatingList(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		
		/*if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals("")) {
			return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE ID as a  key or value or it is null\"}";
		} else {*/
			
			//MasEmployee checkEmp = od.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
			String patinetName=	jsondata.get("patinetName").toString();
			//if (checkEmp != null ) {
				
			     List<Visit> getvisit = od.getVisit();
				List<Object[]> getSearchPatinet = od.getSearchPatinet(patinetName);
				if (getvisit.size() == 0)
				{
					return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
				}

				else {

					List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
					//Visit v= new Visit();
					
					try
					{
						if(getSearchPatinet != null && getSearchPatinet.size() > 0)
						{
						//if (v.getStatus().equals("w"))
					for (Visit v : getvisit) {
						
							HashMap<String, Object> pt = new HashMap<String, Object>();
						    
							Date s=v.getPatient().getDateOfBirth();
						    Calendar lCal = Calendar.getInstance();
						    lCal.setTime(s);
                            int yr=lCal.get(Calendar.YEAR);
                            int mn=lCal.get(Calendar.MONTH) + 1;
                            int dt=lCal.get(Calendar.DATE);
                            LocalDate today = LocalDate.now();
							System.out.println("today"+today);//Today's date
							LocalDate birthday = LocalDate.of(yr,mn,dt) ; //Birth date
							System.out.println("birthday"+birthday);
							Period p1 = Period.between(birthday, today);
							
							pt.put("status",v.getVisitStatus());
							pt.put("tokenNo", v.getTokenNo());
							pt.put("priority", v.getPriority());
							pt.put("employeeName", v.getPatient().getEmployeeName());
							pt.put("serviceNo", v.getPatient().getServiceNo());
							pt.put("patientName", v.getPatient().getPatientName());
							pt.put("age", p1.getYears());
							pt.put("gender", v.getPatient().getMassex().getAdministrativeSexName());
							//pt.put("doctorname", checkEmp.getFirstName());
							pt.put("departmentName",v.getMasDepartment().getDepartmentName());
							pt.put("status", v.getVisitStatus());
							pt.put("priority", v.getPriority());
							pt.put("visitId", v.getVisitId());
							pt.put("patientId", v.getPatient().getPatientId());
							
							c.add(pt);
						}
						}
					else
					{
						json.put("msg", "Search Data Not Found");
					}
					
						
					json.put("data", c);
					json.put("count", c.size());
					// json.put("Visit List", getvisit);
					json.put("msg", "Visit List  get  sucessfull... ");
					json.put("status", "1");
					}

				
				catch(Exception e)
				{
				  System.out.println(e);
				}

				}
			/*}
				else
			{
				return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";

			}*/

			return json.toString();
		//}
	
}
	//////////////////////  Get Ideal Weight 01-feb-2019 @KrishnaThakur ///////////////////////////////////////
	
	/*@Override
	public String idealWeight(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
		if (!jsondata.isEmpty()) {

			JSONObject nullbalankvalidation = null;
			nullbalankvalidation = ValidateUtils.checkIdealWeight(jsondata);
			if (nullbalankvalidation.optString("status").equals("0")) {
				return nullbalankvalidation.toString();
			} 
		else 
		{
			
			List<String> idealWeight = od.getIdealWeight(Long.parseLong(jsondata.get("height").toString()), jsondata.get("age"));
			
			if (idealWeight != null && ! CollectionUtils.isEmpty(idealWeight) ) {
				
				try {
					HashMap<String, Object> iw = new HashMap<String, Object>();
					iw.put("idealWeight", idealWeight.get(0));
					c.add(iw);
					json.put("data", c);
					json.put("msg", "data get sucessfully......");
					json.put("status", 1);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} 
		else {
				try {
					json.put("msg", "data not found");
					json.put("status", 0);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		
		}
		return json.toString();
		
	}
	*/



	

////////////////////////////// Get OPD Patient Details,Vitals Details for OPD Main #20/02/2019 /////////////// 
	@Override
	public String OpdPatientDetails(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response)
	{
		JSONObject json = new JSONObject();
		List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
		if (!jsondata.isEmpty()) {

			JSONObject nullbalankvalidation = null;
			nullbalankvalidation = ValidateUtils.checkPatientVisit(jsondata);
			if (nullbalankvalidation.optString("status").equals("0")) {
				return nullbalankvalidation.toString();
			} 
		else 
		{
			
			List<Visit> getPatientVisit = od.getPatientVisit(Long.parseLong(jsondata.get("visitId").toString()));
			List<OpdPatientDetail> getvitalDetails = od.getVitalRecord(Long.parseLong(jsondata.get("visitId").toString()));

			
			if (getPatientVisit != null && ! CollectionUtils.isEmpty(getPatientVisit) ) {
				
				try {
					for (Visit v : getPatientVisit) {
						if (v.getVisitStatus().equals("w")||v.getVisitStatus().equals("p")){					
								HashMap<String, Object> pt = new HashMap<String, Object>();
								Date s=v.getPatient().getDateOfBirth();
								Period p=ProjectUtils.getDOB(s);
								
								pt.put("tokenNo", v.getTokenNo());
								pt.put("serviceNo", v.getPatient().getServiceNo());
								pt.put("empName",v.getPatient().getMasEmployee().getFirstName());
								if(v.getPatient().getRankId()!=null)
								{
								pt.put("rank", v.getPatient().getMasRank().getRankName());
								}
								if(v.getPatient().getTradeId()!=null)
								{
								pt.put("tradeBranch", v.getPatient().getMasTrade().getTradeName());
								}
								pt.put("totalService", 18);
								if(v.getPatient().getUnitId()!=null)
								{
								pt.put("unit", v.getPatient().getMasUnit().getUnitName());
								}
								if(v.getPatient().getReligionId()!=null)
								{
								pt.put("religionCommand", v.getPatient().getMasReligion().getReligionName());
								}
								if(v.getPatient().getMedicalCategoryId()!=null)
								{
								pt.put("medicalCategory", v.getPatient().getMasMedicalCategory().getMedicalCategoryName());
								}
								if(v.getPatient().getMaritalStatusId()!=null)
								{
								pt.put("marritalStatus", v.getPatient().getMasMaritalStatus().getMaritalStatusName());
								}
								if(v.getPatient().getRecordOfficeAddressId()!=null)
								{
								pt.put("recordOfficeAddress", v.getPatient().getMasRecordOfficeAddress().getAddress());
								}
								pt.put("patientName", v.getPatient().getPatientName());
								pt.put("age", p.getYears());
								
								pt.put("dob", v.getPatient().getDateOfBirth());
								pt.put("address", v.getPatient().getAddress());
								if(v.getPatient().getCity()!=null)
								{
								pt.put("city", v.getPatient().getCity());
								}
								if(v.getPatient().getStateId()!=null)
								{
								pt.put("state", v.getPatient().getMasState().getStateName());
								}
								pt.put("pincode", v.getPatient().getPincode());
								pt.put("mobileno", v.getPatient().getMobileNumber());
								pt.put("email", v.getPatient().getEmailId());
						   
							if(getvitalDetails!=null && ! CollectionUtils.isEmpty(getvitalDetails))
							{	
								pt.put("height",v.getOpdPatientDetails().getHeight());
								pt.put("idealWeight",v.getOpdPatientDetails().getIdealWeight());
								pt.put("weight",v.getOpdPatientDetails().getWeight());
								pt.put("varation", v.getOpdPatientDetails().getVaration());
								pt.put("tempature",v.getOpdPatientDetails().getTemperature());
								pt.put("bp",v.getOpdPatientDetails().getBp());
								pt.put("pulse",v.getOpdPatientDetails().getPulse());
								pt.put("spo2", v.getOpdPatientDetails().getSpo2());
								pt.put("bmi",v.getOpdPatientDetails().getBmi());
								pt.put("rr",v.getOpdPatientDetails().getRr());
																
							}
							c.add(pt);
						}
						
						else
						{
							return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}";
						}
	
							json.put("data", c);
							json.put("size", c.size());
							// json.put("Visit List", getvisit);
							json.put("msg", "OPD Patients Visit List  get  sucessfull... ");
							json.put("status", "1");

					}
				}
	
				catch(Exception e)
				{
					e.printStackTrace();
					return "{\"status\":\"0\",\"msg\":\"Somting went wrong}";
				}
				} 
			else {
				try {
						json.put("msg", "Visit ID data not found");
						json.put("status", 0);
					}
				    catch (JSONException e)
				    {
						e.printStackTrace();
					}
				}
			}
		
		}
		return json.toString();
	
}


	@Override
	public String familyHistoryDetails(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE ID as a  key or value or it is null\"}";
			} else {
				
				MasEmployee checkEmp = od.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
				
				if (checkEmp != null) {
					
					List<PatientFamilyHistory> getPatinetFamily = od.getFamilyHistory();
					if (getPatinetFamily.size() == 0) {
						return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
					}

					else {

						List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
						
						try{
						for (PatientFamilyHistory phv : getPatinetFamily) {
							//if (phv.getStatus().equals("Y")){					
								HashMap<String, Object> pt = new HashMap<String, Object>();
																							
								pt.put("patientFamilyHistoryId", phv.getPatientFamilyHistoryId());
								pt.put("patientFamilyHistoryCode", phv.getPatientFamilyHistoryCode());
								pt.put("patientFamilyHistoryName", phv.getPatientFamilyHistoryName());
								pt.put("patinetPresentComplaintname", phv.getPatientPresentComplaintName());
								pt.put("patinetTreatmentName", phv.getPatientTreatmentName());
																
								c.add(pt);
							/*}
											
						else
						{
							return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}";
						}*/
						
						json.put("data", c);
						json.put("size", c.size());
						// json.put("Visit List", getvisit);
						json.put("msg", "Family History List  get  sucessfull... ");
						json.put("status", "1");

						}
						}
						
						 catch(Exception e)
						{
							 return "{\"status\":\"0\",\"msg\":\"Somting went wrong}";
						}
					}

				} else
				{
					return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE ID Not Found\"}";

				}

				return json.toString();
			}
	}

	 /////////////////////////////// save opd patinet details created by @KrishnaThakur 27th Feb 2019 ///////////////////

	@Override
	public String saveOpdPatientDetails(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		String opd = null;
		String oph = null;
		String opdInvesti = null;
		String opdPrescription = null;
		String opdDensistyHd=null;
		// TODO Auto-generated method stub
		Date currentDate=ProjectUtils.getCurrentDate();

		JSONObject json = new JSONObject();
		
		//OpdPatientDetail opddetails = new OpdPatientDetail();
		OpdObesityHd oohd=new OpdObesityHd();
		OpdPatientHistory opdPatinetHistory = new OpdPatientHistory();
		DgOrderhd orderhd = new DgOrderhd();
		PatientPrescriptionHd pphd=new PatientPrescriptionHd();
		
		
				
				
		//OpdPatientDetail checkOpdPatientVisit = od.checkVisitOpdPatientDetails(Long.parseLong(jsondata.get("visitId").toString()));
		//OpdPatientHistory checkOpdPatinetHistoryVisit = od.checkVisitOpdPatientHistory(Long.parseLong(jsondata.get("visitId").toString()));
		try {
			if (!jsondata.isEmpty())
			{
				JSONObject nullbalankvalidation = null;
				nullbalankvalidation = ValidateUtils.addVitalPreConsulataionDetailsvalidation(jsondata);
				if (nullbalankvalidation.optString("status").equals("0"))
				{
					return nullbalankvalidation.toString();
				}
				else 
				{
					 opd = od.saveOpdPatientDetails(jsondata);
					/*
					opddetails.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
					opddetails.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
					opddetails.setPollor(String.valueOf(jsondata.get("pallar")));
					opddetails.setEdema(String.valueOf(jsondata.get("edema")));
					opddetails.setCyanosis(String.valueOf(jsondata.get("cyanosis")));
					opddetails.setJauindice(String.valueOf(jsondata.get("jauindice")));
					opddetails.setLymphNode(String.valueOf(jsondata.get("lymphNode")));
					opddetails.setClubbing(String.valueOf(jsondata.get("clubbing")));
					opddetails.setThyroid(String.valueOf(jsondata.get("thyroid")));
					opddetails.setTremors(String.valueOf(jsondata.get("tremors")));
					opddetails.setGeneralOther(String.valueOf(jsondata.get("generalOther")));
					opddetails.setGeneral(String.valueOf(jsondata.get("general")));
					opddetails.setCns(String.valueOf(jsondata.get("cns")));
					opddetails.setChestResp(String.valueOf(jsondata.get("chestresp")));
					opddetails.setMusculoskeletal(String.valueOf(jsondata.get("musculoskeletal")));
					opddetails.setCvs(String.valueOf(jsondata.get("cvs")));
					opddetails.setSkin(String.valueOf(jsondata.get("skin")));
					opddetails.setGi(String.valueOf(jsondata.get("gi")));
					opddetails.setSystemOther(String.valueOf(jsondata.get("systemother")));
					opddetails.setGenitoUrinary(String.valueOf(jsondata.get("genitourinary")));
					opddetails.setHeight(String.valueOf(jsondata.get("height")));
					opddetails.setWeight(String.valueOf(jsondata.get("weight")));
					opddetails.setIdealWeight(String.valueOf(jsondata.get("idealWeight")));
					BigDecimal bd=new BigDecimal(String.valueOf(jsondata.get("varation")));
					opddetails.setVaration(bd);
					opddetails.setTemperature(String.valueOf(jsondata.get("temperature")));
					opddetails.setBp(String.valueOf(jsondata.get("bp")));
					opddetails.setPulse(String.valueOf(jsondata.get("pulse")));
					opddetails.setSpo2(String.valueOf(jsondata.get("spo2")));
					opddetails.setBmi(String.valueOf(jsondata.get("bmi")));
					opddetails.setRr(String.valueOf(jsondata.get("rr")));*/
					if(jsondata.get("obsistyMark")!=null)
					{
						oohd.setPatientId(Long.parseLong(jsondata.get("patientId").toString()));
						oohd.setVisitId(Long.parseLong(jsondata.get("visitId").toString()));
						BigDecimal bd1 = new BigDecimal(jsondata.get("varation").toString());
						oohd.setVaration(bd1);
						oohd.setIniDate(currentDate);
						Calendar calendar = Calendar.getInstance();
						java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
						oohd.setLastChgDate(ourJavaTimestampObject);
						
					}
					
					opdPatinetHistory.setVisitId(Long.parseLong(jsondata.get("visitId").toString()));
					opdPatinetHistory.setPastMedicalHistory(String.valueOf(jsondata.get("pastMedicalHistory")));
					opdPatinetHistory.setChiefComplain(String.valueOf(jsondata.get("chiefComplain")));
					opdPatinetHistory.setPresentIllnessHistory(String.valueOf(jsondata.get("presentIllnessHistory")));
					opdPatinetHistory.setPastSurgicalHistory(String.valueOf(jsondata.get("pastSurgicalHistory")));
					opdPatinetHistory.setMedicationHistory(String.valueOf(jsondata.get("medicationHistory")));
					opdPatinetHistory.setPersonalHistory(String.valueOf(jsondata.get("personalHistory")));
					opdPatinetHistory.setSocialHistory(String.valueOf(jsondata.get("socialHistory")));
					opdPatinetHistory.setFamilyHistory(String.valueOf(jsondata.get("familyHistory")));
					opdPatinetHistory.setAllergyHistory(String.valueOf(jsondata.get("allergyHistory")));
					opdPatinetHistory.setImplantHistory(String.valueOf(jsondata.get("implantHistory")));
					
					////////// DG Order Section ///////////////////////////////////////////////////////////
					
					Calendar calendar = Calendar.getInstance();
					java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
					orderhd.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
					orderhd.setDepartmentId(Long.parseLong(String.valueOf(jsondata.get("departmentId"))));
					orderhd.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
					orderhd.setHospitalId(Long.parseLong(String.valueOf(jsondata.get("hospitalId"))));
					orderhd.setOrderDate(ourJavaTimestampObject);
					orderhd.setOrderStatus(String.valueOf(jsondata.get("orderStatus")));
					
					
					List<HashMap<String, Object>> listofOpdInvest = (List<HashMap<String, Object>>) jsondata.get("listofInvestigation");
					    List<DgOrderdt>  dgorderdt= new ArrayList<>();
					if(listofOpdInvest!=null)  
					{
					for (HashMap<String, Object> singleopd: listofOpdInvest)
					{
						DgOrderdt ob1=new DgOrderdt();
						ob1.setInvestigationId(Long.valueOf(singleopd.get("investigationId").toString()));
						//ob1.setLabMark(String.valueOf(singleopd.get("labMark")));
						ob1.setUrgent(String.valueOf(singleopd.get("urgent")));
						
						//String dddd = ProjectUtils.getSqlDateFormat(singleopd.get("orderDate").toString());
						//java.util.Date d3;
						//d3 = java.sql.Date.valueOf(dddd);
						//billingDetailsEntity.setInvoicedate((java.sql.Date) d3);
						//ob1.setOrderDate((java.sql.Date) d3);
						dgorderdt.add(ob1);			
					}
				    }
					///////////////// Presecription section ///////////////////////////////////
					
					pphd.setStatus(String.valueOf(jsondata.get("prescriptionStatus")));
					pphd.setPrescriptionDate(ourJavaTimestampObject);
					pphd.setInjectionStatus(String.valueOf(jsondata.get("injectionStatus")));
					pphd.setNivStatus(String.valueOf(jsondata.get("nipStatus")));
					List<HashMap<String, Object>> listofOpdPrescription = (List<HashMap<String, Object>>) jsondata.get("listofPrescription");
				    List<PatientPrescriptionDt>  patientPrescDT= new ArrayList<>();
				    for (HashMap<String, Object> singleopd: listofOpdPrescription)
					{
				    	PatientPrescriptionDt ppdt1 = new PatientPrescriptionDt();
				    	if(singleopd!=null)
				    	{
				    	
				       	ppdt1.setItemId(Long.valueOf(singleopd.get("itemId").toString()));
				    	ppdt1.setFrequencyId(Long.valueOf(singleopd.get("frequencyId").toString()));
				    	ppdt1.setDosage(String.valueOf(singleopd.get("dosage")));
				    	ppdt1.setNoOfDays(Long.valueOf(singleopd.get("noOfDays").toString()));
				    	ppdt1.setTotal(Long.valueOf(singleopd.get("total").toString()));
				    	ppdt1.setInstruction(String.valueOf(singleopd.get("instruction")));
				    	ppdt1.setInjectionStatus("N");
				    	}
				    	
				    	patientPrescDT.add(ppdt1);
										
				   }
					//opdPatinetHistory.setFamilyPresentHistory(String.valueOf(jsondata.get("familyPresentHistory")));

				
					 oph =  od.opdPatinetHistory(opdPatinetHistory);
					 opdDensistyHd= od.opdObsisty(oohd);
					 opdInvesti = od.saveOpdInvestigation(orderhd,dgorderdt);
					 opdPrescription=od.saveOpdPrescription(pphd, patientPrescDT);
					 
                   }
                  
                   //if (opd != null ||oph != null  && opd.equalsIgnoreCase("200")||oph.equalsIgnoreCase("200")) {
				if (opd != null ||oph != null  && opd.equalsIgnoreCase("200")||oph.equalsIgnoreCase("200")) {
						json.put("msg", "Opd Patinet Details Saved successfully ");
						json.put("status", "1");
					} else if (opd != null && opd.equalsIgnoreCase("403")) {
						json.put("msg", " you are not authorized for this activity ");
						json.put("status", "0");
					} else {
						json.put("msg", opd);
						json.put("status", "0");
					}
				
			} else {
				json.put("status", "0");
				json.put("msg", "json not contain any object");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json.toString();
	}


	@Override
	public String saveOpdTemplates(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		String opdT = null;
		
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		OpdTemplate opdTemp = new OpdTemplate();
		//OpdTemplateInvestigation opdTempInv = new OpdTemplateInvestigation();
		
		try {
			
		if (!jsondata.isEmpty())
		{
		opdTemp.setDepartmentId(Long.parseLong(String.valueOf(jsondata.get("departmentId"))));
		opdTemp.setDoctorId(Long.parseLong(String.valueOf(jsondata.get("doctorId"))));
		opdTemp.setHospitalId(Long.parseLong(String.valueOf(jsondata.get("hospitalId"))));
		opdTemp.setTemplateCode(String.valueOf(jsondata.get("templateCode")));
		opdTemp.setTemplateName(String.valueOf(jsondata.get("templateName")));
		opdTemp.setTemplateType(String.valueOf(jsondata.get("templateType")));
		//opdTempInv.setStatus(status);
		
		List<HashMap<String, Object>> listofOpdTemp = (List<HashMap<String, Object>>) jsondata.get("listofInvestigationTemplate");
		    List<OpdTemplateInvestigation>  opdInvestigationList= new ArrayList<>();
		   
		for (HashMap<String, Object> singleopd: listofOpdTemp)
		{
			OpdTemplateInvestigation ob1=new OpdTemplateInvestigation();
			ob1.setInvestigationId(Long.valueOf(singleopd.get("investigationId").toString()));
			opdInvestigationList.add(ob1);			
		}
				
		 opdT = od.opdTemplatenewMethod(opdTemp,opdInvestigationList);
		 
		  if (opdT != null  && opdT.equalsIgnoreCase("200"))
		  {
				json.put("msg", "Opd Template Details Saved successfully ");
				json.put("status", "1");
		  } else if (opdT != null && opdT.equalsIgnoreCase("403"))
		  {
				json.put("msg", " you are not authorized for this activity ");
				json.put("status", "0");
		   } else
		   {		
			   json.put("msg", opdT);
				json.put("status", "0");
			}
				
			} else {
				json.put("status", "0");
				json.put("msg", "json not contain any object");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json.toString();
	}


	@Override
	public String saveInvestigationDetails(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
	     String opdInvesti = null;
		
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		DgOrderhd orderhd = new DgOrderhd();
		//OpdTemplateInvestigation opdTempInv = new OpdTemplateInvestigation();
		
		try {
			
		if (!jsondata.isEmpty())
		{
			Calendar calendar = Calendar.getInstance();
			java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
			orderhd.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
			orderhd.setDepartmentId(Long.parseLong(String.valueOf(jsondata.get("departmentId"))));
			orderhd.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
			orderhd.setHospitalId(Long.parseLong(String.valueOf(jsondata.get("hospitalId"))));
			//orderhd.setOrderDate(ourJavaTimestampObject);
			orderhd.setOrderStatus(String.valueOf(jsondata.get("orderStatus")));
			
			
			List<HashMap<String, Object>> listofOpdInvest = (List<HashMap<String, Object>>) jsondata.get("listofInvestigation");
			    List<DgOrderdt>  dgorderdt= new ArrayList<>();
			   
			for (HashMap<String, Object> singleopd: listofOpdInvest)
			{
				DgOrderdt ob1=new DgOrderdt();
				ob1.setInvestigationId(Long.valueOf(singleopd.get("investigationId").toString()));
				ob1.setLabMark(String.valueOf(singleopd.get("labMark")));
				ob1.setUrgent(String.valueOf(singleopd.get("urgent")));
				dgorderdt.add(ob1);			
			}
					
			opdInvesti = od.saveOpdInvestigation(orderhd,dgorderdt);
		 
		  if (opdInvesti != null  && opdInvesti.equalsIgnoreCase("200"))
		  {
				json.put("msg", "Opd Template Details Saved successfully ");
				json.put("status", "1");
		  } else if (opdInvesti != null && opdInvesti.equalsIgnoreCase("403"))
		  {
				json.put("msg", " you are not authorized for this activity ");
				json.put("status", "0");
		   } else
		   {		
			   json.put("msg", opdInvesti);
				json.put("status", "0");
			}
				
			} else {
				json.put("status", "0");
				json.put("msg", "json not contain any object");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json.toString();
	}


	@Override
	public String saveReferalDetails(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(jsondata.toString());
		OpdPatientDetail opdPDt = new OpdPatientDetail();
		String result = od.saveOpdPatientDetails(jsondata);
		JSONObject json = new JSONObject();
		json.put("msg", result);
		return json.toString();
	}

	
	@Override
	public String getObesityWaitingList(HashMap<String, Object> jsondata){
		Map<String, Object> map = new HashMap<>();
		map = od.getObesityWaitingList(jsondata);		
		JSONObject json = new JSONObject();
		if(map.isEmpty()) {
			json.put("status", "0");
			json.put("msg", "No Record found");
		}else {
			json.put("status", "1");
			json.put("msg", "Obesity Waiting List got successfull");
			json.put("count", map.get("count"));			
			List<HashMap<String,Object>> patientObesityList = new ArrayList<>();
			List<OpdObesityHd> patientList = (List<OpdObesityHd>)map.get("patientList");
			
			
			for(int i=0;i<patientList.size();i++) {
				HashMap<String, Object> jsonData = new HashMap<>();
				
				long Id= patientList.get(i).getObesityHdId() !=0?patientList.get(i).getObesityHdId():0;
				jsonData.put("Id",Id);
				String serviceNo="";
				if(patientList.get(i).getPatient() != null) {
					serviceNo = patientList.get(i).getPatient().getServiceNo();
				}
				jsonData.put("serviceNo",serviceNo);
				
				String patientName = "";
				if(patientList.get(i).getPatient() != null) {
					patientName = patientList.get(i).getPatient().getPatientName();
				}
				jsonData.put("patientName",patientName);
				
				String age = "";
				if(patientList.get(i).getPatient() != null) {
					Date date = patientList.get(i).getPatient().getDateOfBirth();
					Period p = ProjectUtils.getDOB(date);
					jsonData.put("age", p.getYears());
				}else {
					jsonData.put("age", age);
				}
				String gender = "";				
				if(patientList.get(i).getPatient().getMasAdministrativeSex() != null) {
					gender = patientList.get(i).getPatient().getMasAdministrativeSex().getAdministrativeSexName();
				}
				jsonData.put("gender", gender);
				
				String DeptName = "";
				/*if(patientList.get(i).getVisit().getMasDepartment() != null) {
					DeptName = patientList.get(i).getVisit().getMasDepartment().getDepartmentName();
				}*/
				jsonData.put("DeptName", DeptName);
				
				jsonData.put("variation",patientList.get(i).getVaration());
				
				patientObesityList.add(jsonData);
			}
			json.put("patientObesityList", patientObesityList);
		}
		return json.toString();
	}
	
	@Override
	public String getObesityDetails(HashMap<String, Object> jsondata){
		Map<String, Object> map = new HashMap<>();
		map = od.getObesityDetails(jsondata);		
		JSONObject json = new JSONObject();
		List<OpdObesityHd> obesityList1 = (List<OpdObesityHd>) map.get("obesityList");
		List<OpdObesityDt> obesityDetailList1 = (List<OpdObesityDt>) map.get("obesityDetailList");
		List<Map<String,Object>> obesityList = new ArrayList<>();
		List<Map<String,Object>> obsesityDetailList = new ArrayList<>();
		
		for(OpdObesityHd list1 : obesityList1) {
			Map<String,Object> map1 = new HashMap<>();
			map1.put("serviceNo", list1.getPatient().getServiceNo());
			map1.put("patientName", list1.getPatient().getPatientName());
			
			Date date = (Date) list1.getPatient().getDateOfBirth();
			Period period = ProjectUtils.getDOB(date);
			map1.put("age", period.getYears());
			map1.put("gender", list1.getPatient().getMasAdministrativeSex().getAdministrativeSexName());
			map1.put("header_id", list1.getObesityHdId());
			obesityList.add(map1);
		}
		for(OpdObesityDt list2 : obesityDetailList1) {
			Map<String,Object> map2 = new HashMap<>();
			map2.put("headerId", list2.getOpdObesityHd().getObesityHdId());
			map2.put("date", list2.getObesityDate());
			map2.put("month", list2.getMonth());
			map2.put("height", list2.getHeight());
			map2.put("weight", list2.getWeight());
			map2.put("idealWeight", list2.getIdealWeight());
			map2.put("variation", list2.getVariation());
			map2.put("bmi", list2.getBmi());
			obsesityDetailList.add(map2);
		}
		
		json.put("obesityList", obesityList);
		json.put("obsesityDetailList", obsesityDetailList);
		return json.toString();
	}
	
	@Override
	public String idealWeight(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
		if (!jsondata.isEmpty()) {

			JSONObject nullbalankvalidation = null;
			nullbalankvalidation = ValidateUtils.checkIdealWeight(jsondata);
			if (nullbalankvalidation.optString("status").equals("0")) {
				return nullbalankvalidation.toString();
			} else {
				List<String> idealWeight = od.getIdealWeight(Long.parseLong(jsondata.get("height").toString()),
						jsondata.get("age"));
				if (idealWeight != null && !CollectionUtils.isEmpty(idealWeight)) {
					try {
						HashMap<String, Object> iw = new HashMap<String, Object>();
						iw.put("idealWeight", idealWeight.get(0));
						c.add(iw);
						json.put("data", c);
						json.put("msg", "data get sucessfully......");
						json.put("status", 1);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					try {
						json.put("msg", "data not found");
						json.put("status", 0);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return json.toString();
	}
	
	public String saveObesityDetails(HashMap<String, Object> jsondata){
		Map<String,Object> map = new HashMap<String,Object>();
		JSONObject  obj = new JSONObject();
		String msg = od.saveObesityDetails(jsondata);
		obj.put("msg", msg);
		return obj.toString();
	}
	@Override
	public String referredPatientList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map = od.referredPatientList(jsondata);
		JSONObject obj = new JSONObject();
		List<Map<String,Object>> headerList = new ArrayList<>();
		if(!map.isEmpty()) {
			List<ReferralPatientHd> patientList = (List<ReferralPatientHd>)map.get("referredPatientList");
			if(patientList.size()>0) {
				for(ReferralPatientHd list : patientList) {
					Map<String,Object> jsonData = new HashMap<>();
					String service_no = "", patient_name="", gender = "", rank="", mobileNo="",referredHospital="",id="",count="",referredDate="";
					Date date=null;
					if(list.getPatient() != null) {
						service_no = (String)list.getPatient().getServiceNo();
						patient_name = (String) list.getPatient().getPatientName();
						String format = "dd-MM-yyyy";
						/*date = HMSUtil.convertDateToStringFormat((Date)list.getPatient().getDateOfBirth(), format);
						System.out.println("date is "+date);*/
						date =  (Date)list.getPatient().getDateOfBirth();						
						gender = (String)list.getPatient().getMasAdministrativeSex().getAdministrativeSexName();
						rank = (String)list.getPatient().getMasRank().getRankName();
						mobileNo =  list.getPatient().getMobileNumber().toString();
						referredHospital = (String) list.getMasImpanneledHospital().getImpanneledHospitalName();		
						referredDate = HMSUtil.convertDateToStringFormat((Date) list.getReferralIniDate(), format);
						System.out.println("date is "+referredDate);
						//referredDate = (Date) list.getReferralIniDate();
						id = list.getRefrealHdId()+"";
						
								}
					jsonData.put("id", id);
					jsonData.put("service_no", service_no);
					jsonData.put("patient_name", patient_name);
					if(date == null) {
						jsonData.put("age", "");
					}else {
						Period p = ProjectUtils.getDOB(date);
						jsonData.put("age", p.getYears());
						
					}
					jsonData.put("gender",gender);
					jsonData.put("rank", rank);
					jsonData.put("mobile_no", mobileNo);
					jsonData.put("referred_hospital", referredHospital);
					jsonData.put("referral_date", referredDate);
					/*if(referredDate == null) {
						jsonData.put("referral_date", referredDate);
					}else {
						jsonData.put("referral_date", referredDate);
					}*/
										
					headerList.add(jsonData);
				}
				obj.put("Status", "0");
				obj.put("msg", "Referral List got successfull");
				obj.put("referral_list", headerList);
				obj.put("count", map.get("count"));
			}
		}else {
			obj.put("status", "0");
			obj.put("msg", "No Record Found");
		}
		return obj.toString();
	}
	@Override
	public String referredPatientDetail(HashMap<String, String> jsondata, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = od.referredPatientDetail(jsondata);
		JSONObject obj = new JSONObject();
		List<Map<String, Object>> headerList = new ArrayList<>();
		List<Map<String, Object>> detailList = new ArrayList<>();
		if (!map.isEmpty()) {
			List<ReferralPatientHd> headerInfo = (List<ReferralPatientHd>) map.get("referredHeaderList");
			List<ReferralPatientDt> detailInfo = (List<ReferralPatientDt>) map.get("referredDetailList");

			if (headerInfo.size() > 0) {
				for (ReferralPatientHd list : headerInfo) {
					Map<String, Object> jsonData = new HashMap<>();
					String service_no = "", patient_name = "", gender = "", rank = "", mobileNo = "", patientId="";
					Date date = null;
					if (list.getPatient() != null) {
						service_no = (String) list.getPatient().getServiceNo();
						patientId = list.getPatient().getPatientId()+"";
						patient_name = (String) list.getPatient().getPatientName();
						date = (Date) list.getPatient().getDateOfBirth();
						gender = (String) list.getPatient().getMasAdministrativeSex().getAdministrativeSexName();
						rank = (String) list.getPatient().getMasRank().getRankName();
						mobileNo = list.getPatient().getMobileNumber().toString();

					}
					jsonData.put("id", list.getRefrealHdId());
					jsonData.put("service_no", service_no);
					jsonData.put("patient_id", patientId);
					jsonData.put("patient_name", patient_name);
					if (date == null) {
						jsonData.put("age", "");
					} else {
						Period p = ProjectUtils.getDOB(date);
						jsonData.put("age", p.getYears());
					}
					jsonData.put("gender", gender);
					jsonData.put("rank", rank);
					jsonData.put("mobile_no", mobileNo);
					headerList.add(jsonData);
				}

				obj.put("header_list", headerList);
			} else {
				obj.put("header_list", new JSONArray());
			}

			if (detailInfo.size() > 0) {
				for(ReferralPatientDt referralPatientDt :detailInfo) {
					Map<String,Object> map2 = new HashMap<>();
					String hospitalName = "", diagnosisName="";
					if(referralPatientDt.getReferralPatientHd() != null) {
						hospitalName = referralPatientDt.getReferralPatientHd().getMasImpanneledHospital().getImpanneledHospitalName();
					}	
					map2.put("id", referralPatientDt.getRefrealHdId());
					map2.put("hospital_name", hospitalName);
					System.out.println("final_note "+referralPatientDt.getFinalNote());
					map2.put("final_note", referralPatientDt.getFinalNote());
					map2.put("department_name", referralPatientDt.getExtDepartment());
					if(referralPatientDt.getMasIcd() != null) {
						diagnosisName = referralPatientDt.getMasIcd().getIcdName();
					}
					map2.put("diagnosis_name", diagnosisName);					
					System.out.println("referralPatientDt.getReferralDate() "+referralPatientDt.getReferralDate());
					map2.put("referral_date", referralPatientDt.getReferralDate());
					map2.put("notifiable_desease", referralPatientDt.getDisease());
					map2.put("instructions", referralPatientDt.getInstruction());
					map2.put("mark_mb", referralPatientDt.getMb());
					map2.put("mark_admitted", referralPatientDt.getAdmitted());
					map2.put("close", referralPatientDt.getClose());
					
					detailList.add(map2);					
					
				}
				obj.put("detail_list", detailList);
			} else {
				obj.put("detail_list", new JSONArray());
			}

		} else {
			obj.put("headerList", new JSONArray());
			obj.put("DetailList", new JSONArray());
		}
		return obj.toString();
	}
	@Override
	public String updateReferralDetail(HashMap<String, Object> jsondata, HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> responseJson = od.updateReferralDetail(jsondata, request, response);
		JSONObject obj = new JSONObject();
		if(!responseJson.isEmpty()) {
			obj.put("msg", responseJson.get("msg"));
			obj.put("status", "1");			
		}else {
			obj.put("msg", "Failed to Update Records");
			obj.put("status", "0");
		}
		return obj.toString();
	}
	@Override
	public String getAdmissionDischargeList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = new JSONObject();
		JSONArray jsArray = new JSONArray();
		Map<String,Object> jsonResponse = od.getAdmissionDischargeList(jsondata, request, response);
		if(!jsonResponse.isEmpty()) {
			Map<String,Object> mapData = new HashMap<>();
			List<Map<String,Object>> responseList = new ArrayList<>();
			List<Map<String,Object>> responseList2 = new ArrayList<>();
			List<AdmissionDischarge> admissionPendingList = (List<AdmissionDischarge>)jsonResponse.get("admissionPendingList");
			String admissionPendingListCount = (String)jsonResponse.get("count1");
			List<AdmissionDischarge> dischargePendingList = (List<AdmissionDischarge>)jsonResponse.get("dischargePendingList");
			String dischargePendingListCount = (String) jsonResponse.get("count2");
			if(admissionPendingList.size() > 0) {
				for(AdmissionDischarge referralPatientDt : admissionPendingList) {
					Map<String,Object> map = new HashMap<>();
					String serviceNo = "",patientName="",age="",gender="",rank="",mobileNo="",name="",relation="",hospitalName="";
					Date dob = null;
					if(referralPatientDt.getReferralPatientHd() != null && referralPatientDt.getReferralPatientHd().getPatient() != null) {
						serviceNo = referralPatientDt.getReferralPatientHd().getPatient().getServiceNo();
						patientName = referralPatientDt.getReferralPatientHd().getPatient().getPatientName();
						dob = referralPatientDt.getReferralPatientHd().getPatient().getDateOfBirth();
						mobileNo = referralPatientDt.getReferralPatientHd().getPatient().getMobileNumber();
						if(referralPatientDt.getReferralPatientHd().getPatient().getMasAdministrativeSex() != null) {
							gender= referralPatientDt.getReferralPatientHd().getPatient().getMasAdministrativeSex().getAdministrativeSexName();
						}
						
					}
					if(referralPatientDt.getReferralPatientHd() != null && referralPatientDt.getReferralPatientHd().getPatient() != null && referralPatientDt.getReferralPatientHd().getPatient().getMasRank() != null) {
						rank = referralPatientDt.getReferralPatientHd().getPatient().getMasRank().getRankName();
					}
						if(referralPatientDt.getReferralPatientHd() != null && referralPatientDt.getReferralPatientHd().getMasImpanneledHospital() != null) {
						hospitalName = referralPatientDt.getReferralPatientHd().getMasImpanneledHospital().getImpanneledHospitalName();
					}
					map.put("id", referralPatientDt.getAdmissionId());
					map.put("service_no", serviceNo);
					map.put("patient_name", patientName);
					//Period period = ProjectUtils.getDOB(dob);
					if (dob == null) {
						map.put("age", "");
					} else {
						Period period = ProjectUtils.getDOB(dob);
						map.put("age", period.getYears());
					}					
					map.put("gender", gender);
					map.put("rank", rank);
					map.put("mobile_no", mobileNo);
					map.put("hospital_name", hospitalName);
					//map.put("count", admissionPendingListCount);
					responseList.add(map);
				}
				
				obj.put("admissionPendingList", responseList); 
				obj.put("count", admissionPendingListCount);
			}else {
				obj.put("admissionPendingList", new JSONArray());
			}
			
			if(dischargePendingList.size()>0) {
				for(AdmissionDischarge admissionDischarge : dischargePendingList) {
					Map<String,Object> map2 = new HashMap<>();
					String serviceNo="",patientName="",gender="",rank="",ward="",disposal="";
					Date age= null, dateOfAdmission = null;
					if(admissionDischarge.getPatient() != null){
						serviceNo = admissionDischarge.getPatient().getServiceNo();
						patientName = admissionDischarge.getPatient().getPatientName();
						age = admissionDischarge.getPatient().getDateOfBirth();
						if(admissionDischarge.getPatient().getMasAdministrativeSex() != null) {
							gender = admissionDischarge.getPatient().getMasAdministrativeSex().getAdministrativeSexName();
						}
						if(admissionDischarge.getPatient().getMasRank() != null) {
							rank = admissionDischarge.getPatient().getMasRank().getRankName();
						}	
						
					}
					if(admissionDischarge.getWardNo() != null) {
						ward = admissionDischarge.getWardNo().toString();
					}
					
					dateOfAdmission = admissionDischarge.getDateOfAdmission();
					if(admissionDischarge.getMasDisposal() != null) {
						disposal = admissionDischarge.getMasDisposal().getDisposalName();
					}
					String admissionDate = HMSUtil.convertDateToStringFormat(dateOfAdmission, "dd-MM-yyyy");
					Period period = ProjectUtils.getDOB(age);
					map2.put("id", admissionDischarge.getAdmissionId());
					map2.put("service_no", serviceNo);
					map2.put("patient_name", patientName);
					map2.put("age", period.getYears());
					map2.put("gender", gender);
					map2.put("rank", rank);
					map2.put("date_of_admission", dateOfAdmission);
					map2.put("ward", ward);
					map2.put("disposal", disposal);
					//map2.put("count", dischargePendingListCount);
					responseList2.add(map2);
				}
				obj.put("dischargePendingList",responseList2);
				obj.put("count",dischargePendingListCount);
			}else {
				obj.put("dischargePendingList", new JSONArray());
			}
		}else {
			obj.put("admissionPendingList", new JSONArray());
			obj.put("dischargePendingList", new JSONArray());
		}
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String admissionAndDischarge(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		List<Map<String,Object>> jsonList = new ArrayList<>();
		Map<String, Object> jsonResponse = od.admissionAndDischarge(jsondata, request, response);
		if(!jsonResponse.isEmpty()) {
			List<AdmissionDischarge> list = (List<AdmissionDischarge>) jsonResponse.get("patientDetail");
			List<MasDisposal> disposalList = (List<MasDisposal>) jsonResponse.get("disposalList");
			if(list.size() >0) {
				String admission_id = "",dateOfAdmission = "",ward ="", disposal="", noOfDays="",remark="", admissionNo="", headerId="",patientId="", serviceNo="", patientName="",gender="", age="", rank="", mobileNo="",referralDate = "", hospitalName="",speciality="",diagnosis="", insturctions="";
				
				for(AdmissionDischarge admissionDischarge : list) {
					Map<String,Object> map = new HashMap<>();
					admission_id = admissionDischarge.getAdmissionId()+"";
					if(admissionDischarge.getDateOfAdmission() != null) {
						dateOfAdmission = HMSUtil.convertDateToStringFormat(admissionDischarge.getDateOfAdmission(),"dd-MM-yyyy");
					}
					
					if(admissionDischarge.getWardNo() != null) {
						ward = admissionDischarge.getWardNo()+"";
					}
					
					if(admissionDischarge.getMasDisposal() != null && admissionDischarge.getMasDisposal().getDisposalName() != null) {
						disposal = admissionDischarge.getMasDisposal().getDisposalName();
					}
					
					if(admissionDischarge.getNoOfDays() != null) {
						noOfDays = admissionDischarge.getNoOfDays()+"";
					}
					
					if(admissionDischarge.getRemarks() != null) {
						remark = admissionDischarge.getRemarks();
					}
					
					if(admissionDischarge.getDateOfAdmission() != null) {
						admissionNo = admissionDischarge.getAdmissionNo();
					}
					if(admissionDischarge.getReferralPatientHd() != null) {
						headerId = admissionDischarge.getReferralPatientHd().getRefrealHdId()+"";
					}					
					//speciality = admissionDischarge.getReferralPatientHd().getReferralPatientDts();
					if(admissionDischarge.getReferralPatientHd() != null) {
						for(ReferralPatientDt referralPatientDt : admissionDischarge.getReferralPatientHd().getReferralPatientDts()) {
							speciality += referralPatientDt.getExtDepartment()+",";
							diagnosis += referralPatientDt.getMasIcd().getIcdName()+",";
							insturctions = referralPatientDt.getInstruction()+",";
							
						}
						if(speciality.length() > 0){
							speciality = speciality.substring(0, speciality.length()-1);
						}
						if(diagnosis.length()>0) {
							diagnosis = diagnosis.substring(0, diagnosis.length()-1);
							 
						}
						if(insturctions.length()>0) {							
							
							insturctions = insturctions.substring(0, insturctions.length()-1);
						}
					}					
					
					if(admissionDischarge.getReferralPatientHd() != null) {
						referralDate = HMSUtil.convertDateToStringFormat(admissionDischarge.getReferralPatientHd().getReferralPatientDts().get(0).getReferralDate(),"dd-MM-yyyy");
					}
					
							
					//if (admissionDischarge.getReferralPatientHd() != null) {
					//	if (admissionDischarge.getReferralPatientHd().getPatient() != null) {
					if(admissionDischarge.getPatient() != null) {
							patientId = admissionDischarge.getPatient().getPatientId() + "";
							serviceNo = admissionDischarge.getPatient().getServiceNo();
							patientName = admissionDischarge.getPatient().getPatientName();
							if (admissionDischarge.getPatient()
									.getMasAdministrativeSex() != null) {
								gender = admissionDischarge.getPatient()
										.getMasAdministrativeSex().getAdministrativeSexName();
							}
							if (admissionDischarge.getPatient().getMasRank() != null) {
								rank = admissionDischarge.getPatient().getMasRank()
										.getRankName();
							}
							mobileNo = admissionDischarge.getPatient().getMobileNumber();
							age = HMSUtil.convertDateToStringFormat(
									admissionDischarge.getPatient().getDateOfBirth(),
									"dd-MM-yyyy");
					}
					//	}
					//}
					if(admissionDischarge.getReferralPatientHd() != null && admissionDischarge.getReferralPatientHd().getMasImpanneledHospital() != null) {
						hospitalName = admissionDischarge.getReferralPatientHd().getMasImpanneledHospital().getImpanneledHospitalName();
					}
					
					map.put("admission_id", admission_id);
					map.put("patient_id", patientId);
					map.put("header_id", headerId);
					map.put("service_no", serviceNo);
					map.put("patient_name", patientName);
					map.put("age", age);
					map.put("gender", gender);
					map.put("rank", rank);
					map.put("mobile_no", mobileNo);
					map.put("referral_date", referralDate);
					map.put("hospital_name", hospitalName);
					map.put("speciality", speciality);
					map.put("diagnosis", diagnosis);
					map.put("instructions", insturctions);
					map.put("admission_date", dateOfAdmission);
					map.put("ward", ward);
					map.put("disposal", disposal);
					map.put("no_of_days", noOfDays);
					map.put("admission_no", admissionNo);
					map.put("remarks", remark);
					jsonList.add(map);
					
				}
				//obj.put("status","1");
				obj.put("referralList", jsonList);
			}else {
				//obj.put("status","0");
				obj.put("referralList", new JSONArray());
			}
			
			if(disposalList.size()>0) {
				List<Map<String,Object>> masDisposalsList = new ArrayList<>();
				for(MasDisposal masDisposal : disposalList) {
					Map<String,Object> map2 = new HashMap<>();
					String disposalId = masDisposal.getDisposalId()+"";
					String disposalName = masDisposal.getDisposalName();
					map2.put("id", disposalId);
					map2.put("disposal_name", disposalName);
					masDisposalsList.add(map2);
				}
				obj.put("disposalList", masDisposalsList);
				
			}else {
				obj.put("disposalList", new JSONArray());
			}
		}else {
			obj.put("referralList", new JSONArray());
			obj.put("disposalList", new JSONArray());
		}
		
		
		return obj.toString();
	}
	@Override
	public String savePatientAdmission(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response) {
		String result = od.savePatientAdmission(jsondata, request, response);
		JSONObject obj = new JSONObject();
		obj.put("msg", result);
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getServiceWisePatientList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map = od.getServiceWisePatientList(jsondata, request, response);
		JSONObject obj = new JSONObject();
		if(!map.isEmpty()) {
			List<Patient> patientList = (List<Patient>)map.get("patientList");
			List<MasDisposal> disposalList = (List<MasDisposal>)map.get("disposalList");
			if(patientList.size() >0) {
				List<Map<String, String>> list1 = new ArrayList<>();
				for(Patient patient : patientList) {
					Map<String, String> patientMap = new HashMap<>();
					patientMap.put("patient_id", patient.getPatientId()+"");
					patientMap.put("patient_name", patient.getPatientName());
					patientMap.put("service_no", patient.getServiceNo());
					Period p = ProjectUtils.getDOB(patient.getDateOfBirth());
					patientMap.put("age", p.getYears()+"");
					if(patient.getMasAdministrativeSex() != null) {
						patientMap.put("gender", patient.getMasAdministrativeSex().getAdministrativeSexName());
					}
					if(patient.getMasRank() != null) {
						patientMap.put("rank", patient.getMasRank().getRankName());
					}
					patientMap.put("mobile_no", patient.getMobileNumber());
					String name = "";
					if(patient.getMasEmployee() != null) {
						String firstName="", middleName="",lastName="";
						if(patient.getMasEmployee().getFirstName() != null) {
							name += patient.getMasEmployee().getFirstName()+" ";
						}
						if(patient.getMasEmployee().getMiddleName() != null) {
							name += patient.getMasEmployee().getMiddleName()+" ";
						}
						if(patient.getMasEmployee().getLastName() != null) {
							name += patient.getMasEmployee().getLastName();
						}
						String relation = "";
						if(patient.getMasEmployee().getMasRelation() != null) { 
							relation = patientMap.put("relation", patient.getMasEmployee().getMasRelation().getRelationName());
						}
						patientMap.put("relation", relation);
					}					
					patientMap.put("emp_name", name);					
					list1.add(patientMap);					
				}
				obj.put("patient_list", list1);
			}else {
				obj.put("patient_list", new JSONArray());
			}
			if(disposalList.size() > 0) {
				List<Map<String, String>> list2 = new ArrayList<>();
				for(MasDisposal masDisposal : disposalList) {
					Map<String, String> disposalMap = new HashMap<>();
					String disposalId = masDisposal.getDisposalId()+"";
					String disposalName = masDisposal.getDisposalName();
					disposalMap.put("id", disposalId);
					disposalMap.put("disposal_name", disposalName);
					list2.add(disposalMap);
				}
				obj.put("disposal_list", list2);
			}else {
				obj.put("disposal_list", new JSONArray());
			}
			
		}else {
			obj.put("disposal_list", new JSONArray());
			obj.put("patient_list", new JSONArray());
		}
		return obj.toString();
		
	}
	
	public String saveNewAdmission(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response) {
		String result = od.saveNewAdmission(jsondata, request, response);
		JSONObject obj = new JSONObject();
		obj.put("msg", result);
		return obj.toString();
	}




	@SuppressWarnings("unchecked")
	@Override
	public String nursingCareWaitingList(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		List<Map<String,Object>> nursingCareWaitingList = new ArrayList<>();
		Map<String,Object> map = od.nursingCareWaitingList(jsondata, request, response);
		if(map != null && !map.isEmpty()) {
			List<ProcedureHd> list = (List<ProcedureHd>) map.get("nursingCareList");
			if(list != null && !list.isEmpty()) {
				for(ProcedureHd hd : list) {
					Map<String,Object> data = new HashMap<>();
					String tokenNo = "", name="", firstName = "", middleName="", lastName="",opdDate="", priority="",patientName="",age="",gender="",status="";
					/*if(hd.getVisit() != null) {
						tokenNo = hd.getVisit().getTokenNo()+"";
						
						if(hd.getVisit().getIntDoctorId() != null) {
							firstName = hd.getVisit().getIntDoctorId().getFirstName();
							middleName = hd.getVisit().getIntDoctorId().getMiddleName();
							lastName =hd.getVisit().getIntDoctorId().getLastName();
						}
						name = firstName + " "+middleName+" "+lastName;					
						priority = hd.getVisit().getPriority()+"";
					}*/
					data.put("id", hd.getProcedureHdId());
					data.put("token_no", tokenNo);
					data.put("doctor_name", name);
					data.put("priority", priority);
					if(hd.getOpdPatientDetailsId() != null) {
						opdDate = HMSUtil.convertDateToStringFormat(hd.getOpdPatientDetailsId().getOpdDate(),"dd-MM-yyyy");
					}
					data.put("opd_date", opdDate);
					if(hd.getPatient() != null) {
						patientName= hd.getPatient().getPatientName();
						Period p = ProjectUtils.getDOB(hd.getPatient().getDateOfBirth());
						age = p.getYears()+"";
						if(hd.getPatient().getMasAdministrativeSex() != null) {
							gender = hd.getPatient().getMasAdministrativeSex().getAdministrativeSexName().toString();
						}
					}
					data.put("patient_name", patientName);
					data.put("age", age);
					data.put("gender", gender); 					
					data.put("status", hd.getStatus());
					nursingCareWaitingList.add(data);
					
				}
				obj.put("status", "1");
				obj.put("count", map.get("count"));
				obj.put("nursingCareWaitingList", nursingCareWaitingList);
			}else {
				obj.put("status", "0");
				obj.put("count", "");
				obj.put("nursingCareWaitingList", new JSONArray());
			}
		}else {
			obj.put("status", "0");
			obj.put("count", "");
			obj.put("nursingCareWaitingList", new JSONArray());
		}
		
		
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNursingCareDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		Map<String,Object> patientMap = new HashMap<>();
		Map<String,Object> map = od.getNursingCareDetail(jsondata, request, response);
		if(map != null && !map.isEmpty()) {
			List<ProcedureDt> list = (List<ProcedureDt>)map.get("detailList");
			if(list != null && list.size()>0) {
				List<Map<String,Object>> nursingDetailList = new ArrayList<>();
				String patientName="",age="",gender="",diagnosis="",procedureName="",frequency="",noOfDays="",finalStatus="";
				
				for(ProcedureDt dt: list) {
					Map<String,Object> data = new HashMap<>();					
					patientMap.put("patientName", dt.getProcedureHd().getPatient().getPatientName());
					patientMap.put("age", ProjectUtils.getDOB(dt.getProcedureHd().getPatient().getDateOfBirth()).getYears());
					patientMap.put("gender", dt.getProcedureHd().getPatient().getMasAdministrativeSex().getAdministrativeSexName());
					data.put("id", dt.getProcedureDtId());
					data.put("procedureName",dt.getMasNursingCare().getNursingName());
					data.put("frequency", dt.getMasFrequency().getFrequencyName());
					data.put("noOfDays", dt.getNoOfDays());
					data.put("finalStatus", dt.getFinalProcedureStatus());
					nursingDetailList.add(data);					
				}
				obj.put("status", "1");
				obj.put("patient_detail",patientMap);
				obj.put("nursingDetailList", nursingDetailList);
			}else {
				obj.put("status", "0");
				obj.put("patient_detail",patientMap);
				obj.put("nursingCareDetailList", new JSONArray());
			}
		}else {
			obj.put("status", "0");
			obj.put("patient_detail",patientMap);
			obj.put("nursingCareDetailList", new JSONArray());
		}
		return obj.toString();
		
	}




	@Override
	public String getProcedureDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,Object> map = od.getProcedureDetail(jsondata, request, response);
		JSONObject obj = new JSONObject();
		if(map != null && !map.isEmpty()) {
			List<ProcedureDt> list = (List<ProcedureDt>) map.get("detailList");
			if(list != null && list.size()>0) {
				List<Map<String,Object>> detailList = new ArrayList<>();
				for(ProcedureDt dt : list) {
					Map<String,Object> detailMap = new HashMap<>();
					detailMap.put("id", dt.getProcedureDtId());
					detailMap.put("procedure_name", dt.getMasNursingCare().getNursingName());
					detailMap.put("frequency", dt.getMasFrequency().getFrequencyName());
					detailMap.put("no_of_days", dt.getNoOfDays());
					detailMap.put("remarks", dt.getRemarks());
					detailMap.put("appointment_date", dt.getAppointmentDate());
					detailList.add(detailMap);
				}
				obj.put("status", "1");
				obj.put("detailList", detailList);
			}
		}else {
			obj.put("status", "0");
			obj.put("detailList", new JSONArray());
		}
		return obj.toString();
	}


	@Override
	public Map<String, Object> getWaitingPatientList(Map mapForDS) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String saveProcedureDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
}








package com.icg.jkt.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.icg.jkt.utils.RequestConstants;
import com.icg.jkt.dao.DgOrderhdDao;
import com.icg.jkt.utils.ValidateUtils;
import com.icg.jkt.dao.OpdDao;
import com.icg.jkt.dao.OpdMasterDao;
import com.icg.jkt.dao.OpdPatientDetailDao;
import com.icg.jkt.entity.AdmissionDischarge;
import com.icg.jkt.entity.DgMasInvestigation;
import com.icg.jkt.entity.DgOrderdt;
import com.icg.jkt.entity.DgOrderhd;
import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasAnesthesia;
import com.icg.jkt.entity.MasDisposal;
import com.icg.jkt.entity.MasEmpanelledHospital;
import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasEmployeeDepartment;
import com.icg.jkt.entity.MasFrequency;
import com.icg.jkt.entity.MasIcd;
import com.icg.jkt.entity.MasMaritalStatus;
import com.icg.jkt.entity.MasMedicalCategory;
import com.icg.jkt.entity.MasRelation;
import com.icg.jkt.entity.MasReligion;
import com.icg.jkt.entity.MasRole;
import com.icg.jkt.entity.MasState;
import com.icg.jkt.entity.MasStoreItem;
import com.icg.jkt.entity.MasTrade;
import com.icg.jkt.entity.MasUnit;
import com.icg.jkt.entity.OpdObesityDt;
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
import com.icg.jkt.entity.ProcedureHd;
import com.icg.jkt.entity.ReferralPatientDt;
import com.icg.jkt.entity.ReferralPatientHd;
import com.icg.jkt.entity.Registration;
import com.icg.jkt.entity.Users;
import com.icg.jkt.entity.Visit;
import com.icg.jkt.hibernateutils.GetHibernateUtils;
import com.icg.jkt.service.OpdService;
import com.icg.jkt.utils.CommonUtil;
import com.icg.jkt.utils.DateTimeUtil;
import com.icg.jkt.utils.HMSUtil;
import com.icg.jkt.utils.ICGUtils;
import com.icg.jkt.utils.ProjectUtils;
import com.icg.jkt.utils.ValidateUtils;
import lombok.*;

@Repository
public class OpdServiceImpl implements OpdService {

	@Autowired
	GetHibernateUtils getHibernateUtils;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	OpdDao od;

	@Autowired
	OpdPatientDetailDao opdPatientDetailDao;

	@Autowired
	DgOrderhdDao dgOrderhdDao;
	@Autowired
	OpdMasterDao md;
	
	@Override
	public String PreConsPatientWatingList(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID as a  key or value or it is null\"}";
			} else {

				Users checkEmp = od.checkUser(Long.parseLong(jsondata.get("employeeId").toString()));

				if (checkEmp != null) {

					// List<Visit> getvisit = od.getVisit(jsondata);
					Map<String, Object> map = od.getVisit(jsondata);
					int count = (int) map.get("count");
					List<Visit> getvisit = (List<Visit>) map.get("list");
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
				
				Users checkEmp = od.checkUser(Long.parseLong(jsondata.get("employeeId").toString()));
				
				if (checkEmp != null ) {
					
					//List<Visit> getvisit = od.getVisit(jsondata);
					Map<String,Object> map = od.getPreconsulationVisit(jsondata);
					int count = (int) map.get("count");
					List<Visit> getvisit = (List<Visit>) map.get("list");
							
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
							
							//if (v.getVisitStatus().equals("w")){
								
								HashMap<String, Object> pt = new HashMap<String, Object>();
							   
								if(v.getVisitStatus()!=null)
								{
								pt.put("status", v.getVisitStatus());
								}
								else
								{
									pt.put("status", "");
								}
								if(v.getTokenNo()!=null)
								{
								pt.put("tokenNo", v.getTokenNo());
								}
								else
								{
									pt.put("tokenNo", "");
								}
								if(v.getPriority()!=null)
								{
								pt.put("priority", v.getPriority());
								}
								else
								{
									pt.put("priority", "");
								}
								if(v.getPatient()!=null && v.getPatient().getEmployeeName()!=null)
								{
								pt.put("employeeName", v.getPatient().getEmployeeName());
								}
								else
								{
									pt.put("employeeName", "");
								}
								if(v.getPatient()!=null && v.getPatient().getServiceNo()!=null)
								{
								pt.put("serviceNo", v.getPatient().getServiceNo());
								}
								else
								{
									pt.put("serviceNo", "");
								}
								if( v.getPatient() != null  && v.getPatient().getPatientName()!=null)
								{
								pt.put("patientName", v.getPatient().getPatientName());
								}
								else
								{
									pt.put("patientName", "");
								}
								 if(v.getPatient()!=null && v.getPatient().getDateOfBirth()!=null)
								    {
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
									pt.put("age", p.getYears());
								    }
								 else
								 {
									 pt.put("age", "");
								 }
								
								if(v.getPatient() != null && v.getPatient().getMasAdministrativeSex()!=null && v.getPatient().getMasAdministrativeSex().getAdministrativeSexName() != null)
								{
								pt.put("gender", v.getPatient().getMasAdministrativeSex().getAdministrativeSexName());
								pt.put("genderId", v.getPatient().getMasAdministrativeSex().getAdministrativeSexId());
								}
								else
								{
									pt.put("gender", "");
									pt.put("genderId", "");
								}
								if(v.getPatient() != null && v.getPatient().getMasrelation() != null)
								{
								pt.put("relation", v.getPatient().getMasrelation().getRelationName());
								}
								else
								{
									pt.put("relation", "");
								}
								pt.put("doctorname","");
								if(v.getMasDepartment() != null && v.getMasDepartment().getDepartmentName()!=null)
								{
								pt.put("departmentName",v.getMasDepartment().getDepartmentName());
								}
								else
								{
									pt.put("departmentName","");	
								}
								pt.put("visitId", v.getVisitId());
								if(v.getPatient() != null && v.getPatient().getPatientId()!=null)
								{
								pt.put("patientId", v.getPatient().getPatientId());
								}
								//pt.put("patientName", v.getPatient().getPatientName());
								c.add(pt);							
								
							//}						
						
						}
						if(c != null && c.size()>0){
							json.put("data", c);
							json.put("count", count);
							json.put("msg", "Visit List  get  sucessfull... ");
							json.put("status", "1");
						}
						/*
						 * else{ return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}"; }
						 */

					}
					catch(Exception e)
					{
					  e.printStackTrace();
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
					if (payload.get("varation") != null && !payload.get("varation").equals("")) 
					{
					BigDecimal bd = new BigDecimal(payload.get("varation").toString());
					opddetails.setVaration(bd);
					}
					opddetails.setTemperature(payload.get("temperature").toString());
					opddetails.setBpSystolic(payload.get("bp").toString());
					opddetails.setBpDiastolic(payload.get("bp1").toString());
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
				
				Users checkEmp = od.checkUser(Long.parseLong(jsondata.get("employeeId").toString()));
				
				if (checkEmp != null) {
					
					//List<Visit> getvisit = od.getVisit(jsondata);
					Map<String,Object> map = od.getVisit(jsondata);
					int count = (int) map.get("count");
					List<Visit> getvisit = (List<Visit>) map.get("list");
					if (getvisit.size() == 0) {
						return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
					}

					else {

						List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
						
						try{
						for (Visit v : getvisit) {
							//if (v.getVisitStatus().equals("w")||v.getVisitStatus().equalsIgnoreCase("p")){					
								HashMap<String, Object> pt = new HashMap<String, Object>();
								
								if(v.getPatient()!=null && v.getPatient().getDateOfBirth()!=null)
								{
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
								pt.put("age", p.getYears());
								}
								else
								{
									pt.put("age", "");	
								}
								pt.put("visitId", v.getVisitId());
								pt.put("patientId", v.getPatientId());
								pt.put("tokenNo", v.getTokenNo());
								if(v.getPatient() != null  && v.getPatient().getServiceNo()!=null)
								{	
								pt.put("serviceNo", v.getPatient().getServiceNo());
								}
								else
								{
									pt.put("serviceNo", "");
								}
								if( v.getPatient() != null  && v.getPatient().getPatientName()!=null)
								{
								pt.put("patinetname", v.getPatient().getPatientName());
								}
								else
								{
									pt.put("patinetname", "");
								}
								if(v.getPatient() != null && v.getPatient().getMobileNumber()!=null)
								{
								pt.put("mobileNumber", v.getPatient().getMobileNumber());
								}
								else
								{
									pt.put("mobileNumber", "");
								}
								
								if(v.getPatient() != null && v.getPatient().getMasAdministrativeSex()!=null && v.getPatient().getMasAdministrativeSex().getAdministrativeSexName() != null)
								{
								pt.put("gender", v.getPatient().getMasAdministrativeSex().getAdministrativeSexName());
								pt.put("genderId", v.getPatient().getMasAdministrativeSex().getAdministrativeSexId());
								}
								else
								{
									pt.put("gender", "");
									pt.put("genderId", "");
								}
								if(v.getPatient().getMobileNumber()!=null)
								{
								pt.put("mobileNumber", v.getPatient().getMobileNumber());
								}
								else
								{
									pt.put("mobileNumber", "");	
								}
								
								if(v.getPatient() != null && v.getPatient().getMasrelation() != null)
								{
							    pt.put("relation", v.getPatient().getMasrelation().getRelationName());
								}
								else
								{
									pt.put("relation", "");
								}
								if(v.getPatient().getEmployeeName()!=null)
								{
								pt.put("empName",v.getPatient().getEmployeeName());
								}
								else
								{
									pt.put("empName","");
								}
								if(v.getPatient() != null && v.getPatient().getMasRank() != null)
								{
								pt.put("rankName", v.getPatient().getMasRank().getRankName());
								}
								else
								{
									pt.put("rankName", "");
								}
								if(v.getMasDepartment() != null && v.getMasDepartment().getDepartmentName()!=null)
								{
								pt.put("departmentName",v.getMasDepartment().getDepartmentName());
								}
								else
								{
									pt.put("departmentName","");
								}
							
								c.add(pt);
							//}
						}
						if(c != null && c.size()>0){
							json.put("data", c);
							json.put("count",count);
							json.put("msg", "Visit List  get  sucessfull... ");
							json.put("status", "1");
						}else{
							return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}";
						}

					}
						
						 catch(Exception e)
						{
							 e.printStackTrace();
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
	public String searchPatientWatingList(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		
		/*if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals("")) {
			return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE ID as a  key or value or it is null\"}";
		} else {*/
			
			//MasEmployee checkEmp = od.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
			String patinetName=	jsondata.get("patinetName").toString();
			//if (checkEmp != null ) {
				
			     //List<Visit> getvisit = od.getVisit(jsondata);
			Map<String,Object> map = od.getVisit(jsondata);
			int count = (int) map.get("count");
			List<Visit> getvisit = (List<Visit>) map.get("list");
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
							pt.put("gender", v.getPatient().getMasAdministrativeSex().getAdministrativeSexName());
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

			Map<String,Object> mapObject = dgOrderhdDao.getOpdObesityHd(Long.parseLong(jsondata.get("visitId").toString()));
			
			if (getPatientVisit != null && ! CollectionUtils.isEmpty(getPatientVisit) ) {
				
				try {
					for (Visit v : getPatientVisit) {
						//if (v.getVisitStatus().equals("w")||v.getVisitStatus().equals("p")){					
								HashMap<String, Object> pt = new HashMap<String, Object>();
								if(v.getPatient().getDateOfBirth()!=null)
								{
								Date s=v.getPatient().getDateOfBirth();
								Period p=ProjectUtils.getDOB(s);
								pt.put("age", p.getYears());
								}
								
								pt.put("visitId", v.getVisitId());
								pt.put("patientId", v.getPatientId());
								
								pt.put("tokenNo", v.getTokenNo());
								
								pt.put("serviceNo", v.getPatient().getServiceNo());
								if(v.getPatient()!=null && v.getPatient().getMasEmployee() !=null && v.getPatient().getMasEmployee().getFirstName()!=null)
								{
								pt.put("empName",v.getPatient().getMasEmployee().getFirstName());
								}
								if(v.getPatient()!=null && v.getPatient().getRankId()!=null)
								{
								pt.put("rank", v.getPatient().getMasRank().getRankName());
								}
								if(v.getPatient()!=null && v.getPatient().getTradeId()!=null)
								{
								pt.put("tradeBranch", v.getPatient().getMasTrade().getTradeName());
								}
								if(v.getPatient()!=null && v.getPatient().getServiceJoinDate()!=null)
								{
								
								Date s1=v.getPatient().getServiceJoinDate();
								Period serviceDate=ProjectUtils.getDOB(s1);
								pt.put("totalService",serviceDate.getYears());
								}
								
								if(v.getPatient()!=null && v.getPatient().getUnitId()!=null)
								{
								pt.put("unit", v.getPatient().getMasUnit().getUnitName());
								}
								if(v.getPatient()!=null && v.getPatient().getReligionId()!=null)
								{
								pt.put("religionCommand", v.getPatient().getMasReligion().getReligionName());
								}
								if(v.getPatient()!=null && v.getPatient().getMasAdministrativeSex()!=null && v.getPatient().getMasAdministrativeSex().getAdministrativeSexName()!=null)
								{
								pt.put("gender", v.getPatient().getMasAdministrativeSex().getAdministrativeSexName());
								pt.put("genderId", v.getPatient().getMasAdministrativeSex().getAdministrativeSexId());
								}
								if(v.getPatient()!=null && v.getPatient().getMasRelation()!=null && v.getPatient().getMasRelation().getRelationName()!=null)
								{
								pt.put("relation", v.getPatient().getMasRelation().getRelationName());
								}
								if(v.getPatient()!=null && v.getPatient().getMedicalCategoryId()!=null)
								{
								pt.put("medicalCategory", v.getPatient().getMasMedicalCategory().getMedicalCategoryName());
								}
								if(v.getPatient()!=null && v.getPatient().getMasMaritalStatus()!=null && v.getPatient().getMaritalStatusId()!=null)
								{
								pt.put("marritalStatus", v.getPatient().getMasMaritalStatus().getMaritalStatusName());
								pt.put("maritalStatusId", v.getPatient().getMaritalStatusId());
								}
								if(v.getPatient()!=null && v.getPatient().getRecordOfficeAddressId()!=null)
								{
								pt.put("recordOfficeAddress", v.getPatient().getMasRecordOfficeAddress().getAddress());
								}
								pt.put("patientName", v.getPatient().getPatientName());
							
								
								if(v.getPatient()!=null && v.getPatient().getDateOfBirth()!=null ) {
									String dateOfBirth = HMSUtil.convertDateToStringFormat(v.getPatient().getDateOfBirth(), "dd/MM/yyyy");

									//String dateOfBirth = HMSUtil.getDateWithoutTime1(v.getPatient().getDateOfBirth());
								pt.put("dob", dateOfBirth);
								}
								pt.put("address", v.getPatient().getAddress());
								if(v.getPatient()!=null && v.getPatient().getCity()!=null)
								{
								pt.put("city", v.getPatient().getCity());
								}
								if(v.getPatient()!=null && v.getPatient().getStateId()!=null)
								{
								pt.put("state", v.getPatient().getMasState().getStateName());
								}
								pt.put("pincode", v.getPatient().getPincode());
								pt.put("mobileno", v.getPatient().getMobileNumber());
								pt.put("email", v.getPatient().getEmailId());
								
								if(v.getPatient()!=null && v.getPatient().getDuration()!=null)
								{
								pt.put("duration", v.getPatient().getDuration());
								}
								if(v.getOpdPatientDetails()!=null && v.getOpdPatientDetails().getOpdPatientDetailsId()!=null)
								{
								pt.put("opdPatientDetailId",v.getOpdPatientDetails().getOpdPatientDetailsId());
								}
								if(v.getDepartmentId()!=null)
								{
								pt.put("departmentId",v.getDepartmentId());
								}
								if(v.getOpdPatientDetails()!=null && StringUtils.isNotEmpty(v.getOpdPatientDetails().getDisposalDays())) {
									pt.put("disposalDays",v.getOpdPatientDetails().getDisposalDays());
									}
								if(v.getOpdPatientDetails()!=null && v.getOpdPatientDetails().getDisposal1Id()!=null) {
									Long disposalId	=v.getOpdPatientDetails().getDisposal1Id();
									pt.put("disposalIdValue",disposalId);
								}
								if(MapUtils.isNotEmpty(mapObject)) {
									OpdObesityHd opdObesityHd=(OpdObesityHd) mapObject.get("opdObesityHd");
									pt.put("obesityHdId",opdObesityHd.getObesityHdId());
								}
							if(getvitalDetails!=null && ! CollectionUtils.isEmpty(getvitalDetails))
							{	
								pt.put("height",v.getOpdPatientDetails().getHeight());
								pt.put("idealWeight",v.getOpdPatientDetails().getIdealWeight());
								pt.put("weight",v.getOpdPatientDetails().getWeight());
								pt.put("varation", v.getOpdPatientDetails().getVaration());
								pt.put("tempature",v.getOpdPatientDetails().getTemperature());
								pt.put("bp",v.getOpdPatientDetails().getBpSystolic());
								pt.put("bp1",v.getOpdPatientDetails().getBpDiastolic());
								pt.put("pulse",v.getOpdPatientDetails().getPulse());
								pt.put("spo2", v.getOpdPatientDetails().getSpo2());
								pt.put("bmi",v.getOpdPatientDetails().getBmi());
								pt.put("rr",v.getOpdPatientDetails().getRr());
																
							}
							c.add(pt);
						//}
						
						/*else
						{
							return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}";
						}*/
	
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
				
				Users checkEmp = od.checkUser(Long.parseLong(jsondata.get("employeeId").toString()));
				
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
		
		OpdPatientHistory opdPatinetHistory = new OpdPatientHistory();
		DgOrderhd orderhd = new DgOrderhd();
		List<DgOrderdt>  dgorderdt= new ArrayList<>();
		PatientPrescriptionHd pphd=new PatientPrescriptionHd();
		MasStoreItem msit=new MasStoreItem();
		
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
				
				
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
					
					if(jsondata.get("obsistyMark")!=null && jsondata.get("obsistyMark")!="")
					{
						
					    opdDensistyHd= od.opdObsisty(jsondata);
					}
					
					opdPatinetHistory.setVisitId(Long.parseLong(jsondata.get("visitId").toString()));
					opdPatinetHistory.setPatientId(Long.parseLong(jsondata.get("patientId").toString()));
					opdPatinetHistory.setHospitalId(Long.parseLong(jsondata.get("hospitalId").toString()));
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
					opdPatinetHistory.setLastChgBy(Long.parseLong(jsondata.get("userId").toString()));
					oph =  od.opdPatinetHistory(opdPatinetHistory);
					
				
					///////////////// Presecription section ///////////////////////////////////
					List<HashMap<String, Object>> listofOpdPrescription = (List<HashMap<String, Object>>) jsondata.get("listofPrescription");
					if(listofOpdPrescription!=null)
				    {
					pphd.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
					pphd.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
					pphd.setStatus(String.valueOf(jsondata.get("prescriptionStatus")));
					pphd.setOtherTreatment(String.valueOf(jsondata.get("otherPresecription")));
					pphd.setPrescriptionDate(ourJavaTimestampObject);
					pphd.setInjectionStatus(String.valueOf(jsondata.get("injectionStatus")));
					pphd.setNivStatus(String.valueOf(jsondata.get("nipStatus")));
					pphd.setHospitalId(Long.parseLong(String.valueOf(jsondata.get("hospitalId"))));
					//pphd.setLastChgBy(Long.parseLong(jsondata.get("userId").toString()));
				    List<PatientPrescriptionDt>  patientPrescDT= new ArrayList<>();
				   				  
				    for (HashMap<String, Object> singleopd: listofOpdPrescription)
					{
				       
				    	PatientPrescriptionDt ppdt1 = new PatientPrescriptionDt();
				    	if(singleopd.get("itemId")!=null && singleopd.get("itemId")!="")
				    	{
				       	ppdt1.setItemId(Long.valueOf(singleopd.get("itemId").toString()));
				    	}
				    	if(singleopd.get("frequencyId")!=null && singleopd.get("frequencyId")!="")
				    	{
				    	ppdt1.setFrequencyId(Long.valueOf(singleopd.get("frequencyId").toString()));
				    	}
				    	ppdt1.setDosage(String.valueOf(singleopd.get("dosage")));
				    	if(singleopd.get("noOfDays")!=null && singleopd.get("noOfDays")!="")
				    	{
				    	ppdt1.setNoOfDays(Long.valueOf(singleopd.get("noOfDays").toString()));
				    	}
				    	if(singleopd.get("total")!=null && singleopd.get("total")!="")
				    	{
				    	ppdt1.setTotal(Long.valueOf(singleopd.get("total").toString()));
				    	}
				    	ppdt1.setInstruction(String.valueOf(singleopd.get("instruction")));
				    	ppdt1.setInjectionStatus("N");
				    	patientPrescDT.add(ppdt1);
				    	}
				    	
			    	opdPrescription=od.saveOpdPrescription(pphd, patientPrescDT);
										
				   }
					//opdPatinetHistory.setFamilyPresentHistory(String.valueOf(jsondata.get("familyPresentHistory")));
                   
					
					/*
					 * msit.setHospitalId(Long.parseLong(String.valueOf(jsondata.get("hospitalId")))
					 * ); msit.setNomenclature(String.valueOf(jsondata.get("nip")));
					 * msit.setItemClassId(Long.parseLong(String.valueOf(jsondata.get("itemClassId")
					 * )));
					 * msit.setDispUnitId(Long.parseLong(String.valueOf(jsondata.get("dispUnitId")))
					 * );
					 */
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
			if(singleopd.get("investigationId")!=null && singleopd.get("investigationId")!="")
			{
			ob1.setInvestigationId(Long.valueOf(singleopd.get("investigationId").toString()));
			opdInvestigationList.add(ob1);	
			}
			
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

///////////////////////// add opd treatment Template by @krishna /////////////////////////
	@Override
	public String saveOpdTreatementTemplates(HashMap<String, Object> jsondata, HttpServletRequest request,
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
		
		List<HashMap<String, Object>> listofOpdTemp = (List<HashMap<String, Object>>) jsondata.get("listofTreatmentTemplate");
		    List<OpdTemplateTreatment>  opdTreatmentTempList= new ArrayList<>();
		   
		for (HashMap<String, Object> singleopd: listofOpdTemp)
		{
			OpdTemplateTreatment ob=new OpdTemplateTreatment();
			ob.setItemId(Long.valueOf(singleopd.get("itemId").toString()));
			ob.setDosage(String.valueOf(singleopd.get("dosage")));
			if(singleopd.get("frequencyId")!=null && singleopd.get("frequencyId")!="")
			{
			ob.setFrequencyId(Long.valueOf(singleopd.get("frequencyId").toString()));
			}
			else
			{
			ob.setFrequencyId((long) 1);	
			}
			if(singleopd.get("noOfDays")!=null && singleopd.get("noOfDays")!="")
			{
			ob.setNoofdays(Long.valueOf(singleopd.get("noOfDays").toString()));
			}
			else
			{
				ob.setNoofdays((long) 1);	
			}
			if(singleopd.get("total")!=null && singleopd.get("total")!="")
			{
			ob.setTotal(Long.valueOf(singleopd.get("total").toString()));
			}
			else
			{
			ob.setTotal((long) 0);	
			}
			ob.setInstruction(String.valueOf(singleopd.get("instruction")));
			opdTreatmentTempList.add(ob);			
		}
				
		 opdT = od.saveTreatTemplatenewMethod(opdTemp,opdTreatmentTempList);
		 
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
	
//////////////////////////////// OPD Reports ///////////////////////////////////////////////////////
	
	@Override
	public String getOpdReportsDetailsbyServiceNo(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		
		if (jsondata.get("serviceNo") == null || jsondata.get("serviceNo").toString().trim().equals("")) {
			return "{\"status\":\"0\",\"msg\":\"json is not contain Service No as a  key or value or it is null\"}";
			
		}
				
		else {
			
			
			Map<String,Object> map = od.getOpdReportsDetailsbyServiceNo(jsondata);
			List<Patient> getPatinetDetails = (List<Patient>) map.get("list");
			List<HashMap<String, Object>> p = new ArrayList<HashMap<String, Object>>();
			try
			{
				
			for (Patient ptDetails : getPatinetDetails) {
				HashMap<String, Object> pt = new HashMap<String, Object>();
				pt.put("patinetName",ptDetails.getPatientName());
				pt.put("patientId",ptDetails.getPatientId());
				pt.put("patinetUHIDNo",ptDetails.getUhidNo());
				p.add(pt);
				
		}
			if(p != null && p.size()>0){
				json.put("data", p);
				json.put("count", p.size());
				json.put("msg", "Patinet List  get  sucessfull... ");
				json.put("status", "1");
			}else{
				return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}";
			}
			}
			catch(Exception e)
			{
			  System.out.println(e);
			}

			return json.toString();
		}
	
	}

    
	@Override
	public String getOpdReportsDetailsbyPatinetId(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		
		if (jsondata.get("patientId") == null || jsondata.get("patientId").toString().trim().equals("")) {
			return "{\"status\":\"0\",\"msg\":\"json is not contain Patient Id as a  key or value or it is null\"}";
			
		}
				
		else {
					
			Map<String,Object> map = od.getOpdReportsDetailsbyPatinetId(jsondata);
			List<Visit> getPatinetDetails = (List<Visit>) map.get("list");
			List<HashMap<String, Object>> p = new ArrayList<HashMap<String, Object>>();
			try
			{
				
			for (Visit ptDetails : getPatinetDetails) {
				HashMap<String, Object> pt = new HashMap<String, Object>();
				
				Timestamp vd=ptDetails.getVisitDate();
				Calendar lCal = Calendar.getInstance();
			    lCal.setTime(vd);
                int yr=lCal.get(Calendar.YEAR);
                int mn=lCal.get(Calendar.MONTH) + 1;
                int dt=lCal.get(Calendar.DATE);
                LocalDate visitDate = LocalDate.of(yr,mn,dt) ; //Birth date
				pt.put("visitDate",visitDate);
				pt.put("visitNo",ptDetails.getVisitId());
				pt.put("tokenNo",ptDetails.getTokenNo());
				pt.put("departmentNo",ptDetails.getMasDepartment().getDepartmentName());
				p.add(pt);
				
		}
			if(p != null && p.size()>0){
				json.put("data", p);
				json.put("count", p.size());
				json.put("msg", "Patinet List  get  sucessfull... ");
				json.put("status", "1");
			}else{
				return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}";
			}
			}
			catch(Exception e)
			{
			  System.out.println(e);
			}

			return json.toString();
		}
	
	}
	
	@Override
	public String getOpdReportsDetailsbyVisitId(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		
		if (jsondata.get("visitId") == null || jsondata.get("visitId").toString().trim().equals("")) {
			return "{\"status\":\"0\",\"msg\":\"json is not contain Visit Id as a  key or value or it is null\"}";
			
		}
				
		else {
					
			Map<String,Object> map = od.getOpdReportsDetailsbyPatinetId(jsondata);
			List<Visit> getPatinetDetails = (List<Visit>) map.get("list");
			List<HashMap<String, Object>> p = new ArrayList<HashMap<String, Object>>();
			try
			{
				
			for (Visit ptDetails : getPatinetDetails) {
				HashMap<String, Object> pt = new HashMap<String, Object>();
				
				Timestamp vd=ptDetails.getVisitDate();
				Calendar lCal = Calendar.getInstance();
			    lCal.setTime(vd);
                int yr=lCal.get(Calendar.YEAR);
                int mn=lCal.get(Calendar.MONTH) + 1;
                int dt=lCal.get(Calendar.DATE);
                LocalDate visitDate = LocalDate.of(yr,mn,dt) ; //Birth date
				pt.put("visitDate",visitDate);
				pt.put("visitNo",ptDetails.getVisitId());
				pt.put("patientName",ptDetails.getPatient().getPatientName());
				pt.put("relation",ptDetails.getPatient().getMasRelation().getRelationName());
				pt.put("employeeName",ptDetails.getPatient().getEmployeeName());
				if(ptDetails.getMasEmployee()!= null && ptDetails.getMasEmployee().getFirstName()!= null)
				{
				pt.put("doctor",ptDetails.getMasEmployee().getFirstName());
				}
				Date s=ptDetails.getPatient().getDateOfBirth();
				Period dob=ProjectUtils.getDOB(s);
				pt.put("age",dob);
				if(ptDetails.getOpdPatientDetails()!= null && ptDetails.getOpdPatientDetails().getIcdDiagnosis()!= null)
				{
				pt.put("icdDiagnosis",ptDetails.getOpdPatientDetails().getIcdDiagnosis());
				}
				if(ptDetails.getMasDepartment()!= null && ptDetails.getMasDepartment().getDepartmentName()!= null)
				{
				pt.put("departmentNo",ptDetails.getMasDepartment().getDepartmentName());
				}
			
				//pt.put("prescrptionHdId",ptDetails.getPatinetPrescriptionHd().getPrescriptionHdId());
				
				
				p.add(pt);
				
		}
			if(p != null && p.size()>0){
				json.put("data", p);
				json.put("count", p.size());
				json.put("msg", "OpdData List  get  sucessfull... ");
				json.put("status", "1");
			}else{
				return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}";
			}
			}
			catch(Exception e)
			{
			  System.out.println(e);
			}

			return json.toString();
		}
	
	}
////////////////////////////////OPD Previous Visit Record ///////////////////////////////////////////////////////
	@Override
	public String getOpdPreviousVisitRecord(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
		if (!jsondata.isEmpty()) {
			List<Object[]> getVisitRecord = null;  
			JSONObject nullbalankvalidation = null;
			nullbalankvalidation = ValidateUtils.checkPatientRecord(jsondata);
			if (nullbalankvalidation.optString("status").equals("0")) {
				return nullbalankvalidation.toString();
			} else {
				
				getVisitRecord= od.getPreviousVisitRecord(Long.parseLong(jsondata.get("patientId").toString()));
				if (getVisitRecord != null && !CollectionUtils.isEmpty(getVisitRecord)) {
					
						String visistDate = null;
						String icdDiagnosis = null;
						String workingDiagnosis = null;
						String chiefComplaint = "";
						String familyHistory = "";
						String presentHistory="";
						String presentHistoryIllness="";
						String visitId="";
						/*String orderDate = "";
						Long visitId = null;
						String otherInvestigation = "";
						Long departId = null;
						Long hospitalId = null;
						Long dgOrderDtId = null;*/
						try {
							for (Iterator<?> it = getVisitRecord.iterator(); it.hasNext();) {
								Object[] row = (Object[]) it.next();

								HashMap<String, Object> pt = new HashMap<String, Object>();

								if (row[0] != null) {
									Timestamp vd=(Timestamp) row[0];
									Calendar lCal = Calendar.getInstance();
								    lCal.setTime(vd);
					                int yr=lCal.get(Calendar.YEAR);
					                int mn=lCal.get(Calendar.MONTH) + 1;
					                int dt=lCal.get(Calendar.DATE);
					                
					               
					                LocalDate visitDate = LocalDate.of(yr,mn,dt) ; //Birth date
					                
					                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
					                String text = visitDate.format(formatters);
					                
					                visistDate =text;
									//pt.put("visitDate",visitDate);
									
													
								}
								if (row[1] != null) {
									icdDiagnosis =row[1].toString();
								}
								
								if (row[2] != null) {
									workingDiagnosis =row[2].toString();
								}
								if (row[3] != null && row[3] != "") {
									chiefComplaint =row[3].toString();
								}
								if (row[4] != null) {
									familyHistory =row[4].toString();
								}
								if (row[5] != null) {
									presentHistory =row[5].toString();
								}
								if (row[6] != null) {
									presentHistoryIllness =row[6].toString();
								}
								if (row[7] != null) {
									visitId =row[7].toString();
								}
								
								if(visistDate!=null)
								{
								pt.put("visistDate", visistDate);
								}
								else
								{
									pt.put("visistDate", "");
								}
								if(icdDiagnosis!=null)
								{
								pt.put("icdDiagnosis", icdDiagnosis);
								}
								else
								{
									pt.put("icdDiagnosis", "");	
								}
								if(chiefComplaint!=null)
								{
								pt.put("chiefComplaint", chiefComplaint);
								}
								else
								{
									pt.put("chiefComplaint", "");	
								}
								if(familyHistory!=null)
								{
								pt.put("familyHistory", familyHistory);
								}
								else
								{
									pt.put("familyHistory", "");	
								}
								if(presentHistory!=null)
								{
								pt.put("presentHistory", presentHistory);
								}
								else
								{
									pt.put("presentHistory", "");	
								}
								if(workingDiagnosis!=null)
								{
								pt.put("workingDiagnosis", workingDiagnosis);
								}
								else
								{
									pt.put("workingDiagnosis", "");	
								}
								if(presentHistoryIllness!=null)
								{
								pt.put("presentHistoryIllness", presentHistoryIllness);
								}
								else
								{
									pt.put("presentHistoryIllness", "");	
								}
								if(visitId!=null)
								{
								pt.put("visitId", visitId);
								}
								else
								{
									pt.put("visitId", "");	
								}
								
						        pt.put("doctor","");
						        pt.put("diagnosis","");
						        pt.put("specialisty","");
					
						c.add(pt);
						json.put("data", c);
						json.put("msg", "data get sucessfully......");
						json.put("status", 1);
							}
					 }catch (Exception e) {
							e.printStackTrace();
							return "{\"status\":\"0\",\"msg\":\"Somting went wrong}";
					}
					}else {
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
	

	@Override
	public String getOpdPreviousVitalRecord(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
		if (!jsondata.isEmpty()) {

			JSONObject nullbalankvalidation = null;
			nullbalankvalidation = ValidateUtils.checkPatientRecord(jsondata);
			if (nullbalankvalidation.optString("status").equals("0")) {
				return nullbalankvalidation.toString();
			} 
		else 
		{
			
			//List<Visit> getPatientVisit = od.getPatientVisit(Long.parseLong(jsondata.get("patientId").toString()));
			List<OpdPatientDetail> getvitalDetails = od.getPreviousVitalRecord(Long.parseLong(jsondata.get("patientId").toString()));
			System.out.println("aaaaaaaa "+Long.parseLong(jsondata.get("patientId").toString()));
			if (getvitalDetails != null && ! CollectionUtils.isEmpty(getvitalDetails) ) {
				
				try {
					for (OpdPatientDetail v : getvitalDetails) {
						//if (v.getVisitStatus().equals("w")||v.getVisitStatus().equals("p")){					
								HashMap<String, Object> pt = new HashMap<String, Object>();
								String bpS,bpD=null;
								if(v.getHeight()!=null)
								{	
								pt.put("height",v.getHeight());
								}
								else
								{
									pt.put("height","");	
								}
								if(v.getIdealWeight()!=null)
								{	
								pt.put("idealWeight",v.getIdealWeight());
								}
								else
								{
								pt.put("idealWeight","");	
								}
								if(v.getWeight()!=null)
								{	
								pt.put("weight",v.getWeight());
								}
								else
								{
									pt.put("weight","");	
								}
								if(v.getVaration()!=null)
								{	
								pt.put("varation", v.getVaration());
								}
								else
								{
									pt.put("varation","");	
								}
								if(v.getTemperature()!=null)
								{	
								pt.put("tempature",v.getTemperature());
								}
								else
								{
									pt.put("tempature","");	
								}
								if(v.getBpSystolic()!=null)
								{	
								 bpS=v.getBpSystolic();
								}
								else
								{
									bpS="";	
								}
								if(v.getBpDiastolic()!=null)
								{	
								 bpD=v.getBpDiastolic();
								}
								else
								{
									bpD="";	
								}
								
								if(v.getBpDiastolic()!=null && v.getBpSystolic()!=null)
								{
									String bp=bpS+"/"+bpD;
									pt.put("bp",bp);
								}
																
								else
								{
									pt.put("bp1","");	
								}
								if(v.getPulse()!=null)
								{	
								pt.put("pulse",v.getPulse());
								}
								else
								{
									pt.put("pulse","");	
								}
								if( v.getSpo2()!=null)
								{	
								pt.put("spo2", v.getSpo2());
								}
								else
								{
									pt.put("spo2", "");	
								}
								if(v.getBmi()!=null)
								{	
								pt.put("bmi",v.getBmi());
								}
								else
								{
									pt.put("bmi","");	
								}
								if(v.getRr()!=null)
								{	
								pt.put("rr",v.getRr());
								}
								else
								{
									pt.put("rr","");	
								}
								if(v.getOpdDate()!=null)
								{
								Timestamp vd=(v.getOpdDate());
								Calendar lCal = Calendar.getInstance();
							    lCal.setTime(vd);
				                int yr=lCal.get(Calendar.YEAR);
				                int mn=lCal.get(Calendar.MONTH) + 1;
				                int dt=lCal.get(Calendar.DATE);
				                
				               
				                LocalDate visitDate = LocalDate.of(yr,mn,dt) ; //Birth date
				                
				                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
				                String text = visitDate.format(formatters);
				                
				                String visistDate = text;
				                pt.put("visitdate",visistDate);
								}
								else
								{
								pt.put("visitdate","");	
								}
								
																
								c.add(pt);
					
					}
							json.put("data", c);
							json.put("count", c.size());
							// json.put("Visit List", getvisit);
							json.put("msg", "OPD Patients Visit List  get  sucessfull... ");
							json.put("status", "1");

					}
				
	
				catch(Exception e)
				{
					e.printStackTrace();
					return "{\"status\":\"0\",\"msg\":\"Somting went wrong}";
				}
				} 
			else {
				try {
						json.put("msg", "Patient ID data not found");
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
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String getObesityWaitingList(HashMap<String, Object> jsondata) {
		Map<String, Object> map = new HashMap<>();
		map = od.getObesityWaitingList(jsondata);
		JSONObject json = new JSONObject();
		if (map.isEmpty()) {
			json.put("status", "0");
			json.put("msg", "No Record found");
		} else {
			json.put("status", "1");
			json.put("msg", "Obesity Waiting List got successfull");
			json.put("count", map.get("count"));
			List<HashMap<String, Object>> patientObesityList = new ArrayList<>();
			List<OpdObesityHd> patientList = (List<OpdObesityHd>) map.get("patientList");

			for (int i = 0; i < patientList.size(); i++) {
				HashMap<String, Object> jsonData = new HashMap<>();

				long Id = patientList.get(i).getObesityHdId() != 0 ? patientList.get(i).getObesityHdId() : 0;
				jsonData.put("Id", Id);
				String serviceNo = "";
				if (patientList.get(i).getPatient() != null) {
					serviceNo = HMSUtil.convertNullToEmptyString(patientList.get(i).getPatient().getServiceNo());
				}
				jsonData.put("serviceNo", serviceNo);

				String patientName = "";
				if (patientList.get(i).getPatient() != null && patientList.get(i).getPatient().getPatientName() != null) {
					patientName = patientList.get(i).getPatient().getPatientName();
				}
				jsonData.put("patientName", patientName);

				String age = "";
				if (patientList.get(i).getPatient() != null) {
					Date date = patientList.get(i).getPatient().getDateOfBirth();
					Period p = ProjectUtils.getDOB(date);
					jsonData.put("age", HMSUtil.convertNullToEmptyString(p.getYears()));
				} else {
					jsonData.put("age", age);
				}
				String gender = "";
				if (patientList.get(i).getPatient().getMasAdministrativeSex() != null) {
					gender = HMSUtil.convertNullToEmptyString(patientList.get(i).getPatient().getMasAdministrativeSex().getAdministrativeSexName());
				}
				jsonData.put("gender", gender);

				String DeptName = "";				
				 if(patientList.get(i).getVisit().getMasDepartment() != null) {
					 DeptName = HMSUtil.convertNullToEmptyString(patientList.get(i).getVisit().getMasDepartment().getDepartmentName());
				 }
				 
				jsonData.put("DeptName", DeptName);

				jsonData.put("variation", HMSUtil.convertNullToEmptyString(patientList.get(i).getVaration()));

				patientObesityList.add(jsonData);
			}
			json.put("patientObesityList", patientObesityList);
		}
		return json.toString();
	}

	@Override
	public String getObesityDetails(HashMap<String, Object> jsondata) {
		Map<String, Object> map = new HashMap<>();
		map = od.getObesityDetails(jsondata);

		JSONObject json = new JSONObject();
		List<OpdObesityHd> obesityList1 = (List<OpdObesityHd>) map.get("obesityList");
		List<OpdObesityDt> obesityDetailList1 = (List<OpdObesityDt>) map.get("obesityDetailList");
		List<Map<String, Object>> obesityList = new ArrayList<>();
		List<Map<String, Object>> obsesityDetailList = new ArrayList<>();
		for (OpdObesityHd list1 : obesityList1) {
			Map<String, Object> map1 = new HashMap<>();
			map1.put("serviceNo", list1.getPatient().getServiceNo());
			map1.put("patientName", list1.getPatient().getPatientName());
			Date date = (Date) list1.getPatient().getDateOfBirth();
			Period period = ProjectUtils.getDOB(date);
			map1.put("age", period.getYears());
			map1.put("gender", list1.getPatient().getMasAdministrativeSex().getAdministrativeSexName());
			map1.put("header_id", list1.getObesityHdId());
			map1.put("gender_id", list1.getPatient().getMasAdministrativeSex().getAdministrativeSexId());
			obesityList.add(map1);
		}
		for (OpdObesityDt list2 : obesityDetailList1) {
			Map<String, Object> map2 = new HashMap<>();
			map2.put("headerId", list2.getOpdObesityHd().getObesityHdId());
			map2.put("date", HMSUtil.convertNullToEmptyString(HMSUtil.convertDateToStringFormat(list2.getObesityDate(),"dd-MM-yyyy")));
			map2.put("month", HMSUtil.convertNullToEmptyString(list2.getMonth()));
			map2.put("height", HMSUtil.convertNullToEmptyString(list2.getHeight()));
			map2.put("weight", HMSUtil.convertNullToEmptyString(list2.getWeight()));
			map2.put("idealWeight", HMSUtil.convertNullToEmptyString(list2.getIdealWeight()));
			map2.put("variation", HMSUtil.convertNullToEmptyString(list2.getVariation()));
			map2.put("bmi", HMSUtil.convertNullToEmptyString(list2.getBmi()));
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
						jsondata.get("age"),Long.parseLong(jsondata.get("genderId").toString()));
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

	public String saveObesityDetails(HashMap<String, Object> jsondata) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = new JSONObject();
		String msg = od.saveObesityDetails(jsondata);
		obj.put("msg", msg);
		return obj.toString();
	}

	@Override
	public String referredPatientList(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = od.referredPatientList(jsondata);
		JSONObject obj = new JSONObject();
		List<Map<String, Object>> headerList = new ArrayList<>();
		if (!map.isEmpty()) {
			List<ReferralPatientHd> patientList = (List<ReferralPatientHd>) map.get("referredPatientList");
			if (patientList.size() > 0) {
				for (ReferralPatientHd list : patientList) {
					Map<String, Object> jsonData = new HashMap<>();
					String service_no = "", patient_name = "", gender = "", rank = "", mobileNo = "",
							referredHospital = "", id = "", count = "", referredDate = "";
					Date date = null;
					if (list.getPatient() != null) {
						service_no = (String) list.getPatient().getServiceNo();
						patient_name = (String) list.getPatient().getPatientName();
						String format = "dd-MM-yyyy";
						/*
						 * date =
						 * HMSUtil.convertDateToStringFormat((Date)list.getPatient().getDateOfBirth(),
						 * format); System.out.println("date is "+date);
						 */
						date = (Date) list.getPatient().getDateOfBirth();
						if (list.getPatient().getMasAdministrativeSex() != null) {
							gender = (String) list.getPatient().getMasAdministrativeSex().getAdministrativeSexName();
						}
						if (list.getPatient().getMasRank() != null) {
							rank = (String) list.getPatient().getMasRank().getRankName();
						}
						mobileNo = list.getPatient().getMobileNumber().toString();
						referredDate = HMSUtil.convertDateToStringFormat((Date) list.getReferralIniDate(), format);
						System.out.println("date is " + referredDate);
						// referredDate = (Date) list.getReferralIniDate();
						id = list.getRefrealHdId() + "";

					}
					if (list.getMasImpanneledHospital() != null) {
						referredHospital = (String) list.getMasImpanneledHospital().getImpanneledHospitalName();
					}

					jsonData.put("id", id);
					jsonData.put("service_no", service_no);
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
					jsonData.put("referred_hospital", referredHospital);
					jsonData.put("referral_date", referredDate);
					/*
					 * if(referredDate == null) { jsonData.put("referral_date", referredDate); }else
					 * { jsonData.put("referral_date", referredDate); }
					 */

					headerList.add(jsonData);
				}
				obj.put("Status", "0");
				obj.put("msg", "Referral List got successfull");
				obj.put("referral_list", headerList);
				obj.put("count", map.get("count"));
			}
		} else {
			obj.put("status", "0");
			obj.put("msg", "No Record Found");
			obj.put("referral_list", new JSONArray());
			obj.put("count", 0);
		}
		return obj.toString();
	}

	@Override
	public String referredPatientDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
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
					String service_no = "", patient_name = "", gender = "", rank = "", mobileNo = "", patientId = "";
					Date date = null;
					if (list.getPatient() != null) {
						service_no = (String) list.getPatient().getServiceNo();
						patientId = list.getPatient().getPatientId() + "";
						patient_name = (String) list.getPatient().getPatientName();
						date = (Date) list.getPatient().getDateOfBirth();
						if (list.getPatient().getMasAdministrativeSex() != null) {
							gender = (String) list.getPatient().getMasAdministrativeSex().getAdministrativeSexName();
						}
						if (list.getPatient().getMasRank() != null) {
							rank = (String) list.getPatient().getMasRank().getRankName();
						}
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
				for (ReferralPatientDt referralPatientDt : detailInfo) {
					Map<String, Object> map2 = new HashMap<>();
					String hospitalName = "", diagnosisName = "";
					if (referralPatientDt.getReferralPatientHd() != null) {
						hospitalName = referralPatientDt.getReferralPatientHd().getMasImpanneledHospital()
								.getImpanneledHospitalName();
					}
					map2.put("id", referralPatientDt.getRefrealDtId());
					map2.put("header_id", referralPatientDt.getRefrealHdId());
					map2.put("hospital_name", hospitalName);
					System.out.println("final_note " + referralPatientDt.getFinalNote());
					map2.put("final_note", referralPatientDt.getFinalNote());
					map2.put("department_name", referralPatientDt.getExtDepartment());
					if (referralPatientDt.getMasIcd() != null) {
						diagnosisName = referralPatientDt.getMasIcd().getIcdName();
					}
					map2.put("diagnosis_name", diagnosisName);
					System.out.println("referralPatientDt.getReferralDate() " + referralPatientDt.getReferralDate());
					map2.put("referral_date", referralPatientDt.getReferralPatientHd().getReferralIniDate());
					if (referralPatientDt.getDisease() == null) {
						map2.put("notifiable_desease", "N");
					} else {
						map2.put("notifiable_desease", referralPatientDt.getDisease());
					}
					map2.put("instructions", referralPatientDt.getInstruction());
					if (referralPatientDt.getMb() == null) {
						map2.put("mark_mb", "N");
					} else {
						map2.put("mark_mb", referralPatientDt.getMb());
					}
					if (referralPatientDt.getAdmitted() == null) {
						map2.put("mark_admitted", "N");
					} else {
						map2.put("mark_admitted", referralPatientDt.getAdmitted());
					}
					if (referralPatientDt.getClose() == null) {
						map2.put("close", "N");
					} else {
						map2.put("close", referralPatientDt.getClose());
					}

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
	public String updateReferralDetail(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> responseJson = od.updateReferralDetail(jsondata, request, response);
		JSONObject obj = new JSONObject();
		obj.put("msg", responseJson.get("msg"));
		System.out.println("msg is " + responseJson.get("msg"));
		return obj.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAdmissionDischargeList(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		JSONArray jsArray = new JSONArray();
		Map<String, Object> jsonResponse = od.getAdmissionDischargeList(jsondata, request, response);
		if (!jsonResponse.isEmpty()) {
			Map<String, Object> mapData = new HashMap<>();
			List<Map<String, Object>> responseList = new ArrayList<>();
			List<Map<String, Object>> responseList2 = new ArrayList<>();
			List<AdmissionDischarge> admissionPendingList = (List<AdmissionDischarge>) jsonResponse.get("admissionPendingList");
			String admissionPendingListCount = jsonResponse.get("count1") + "";
			/*
			 * List<AdmissionDischarge> dischargePendingList =
			 * (List<AdmissionDischarge>)jsonResponse.get("dischargePendingList"); String
			 * dischargePendingListCount = (String) jsonResponse.get("count2");
			 */
			if (admissionPendingList.size() > 0) {
				for (AdmissionDischarge referralPatientDt : admissionPendingList) {
					Map<String, Object> map = new HashMap<>();
					String serviceNo = "", patientName = "", age = "", gender = "", rank = "", mobileNo = "", name = "",
							relation = "", hospitalName = "";
					Date dob = null;
					if (referralPatientDt.getReferralPatientHd() != null && referralPatientDt.getReferralPatientHd().getPatient() != null) {
						serviceNo = referralPatientDt.getReferralPatientHd().getPatient().getServiceNo();
						patientName = referralPatientDt.getReferralPatientHd().getPatient().getPatientName();
						dob = referralPatientDt.getReferralPatientHd().getPatient().getDateOfBirth();
						mobileNo = referralPatientDt.getReferralPatientHd().getPatient().getMobileNumber();
						name = referralPatientDt.getReferralPatientHd().getPatient().getEmployeeName();
						if(referralPatientDt.getReferralPatientHd().getPatient().getMasrelation() != null) {
							relation = referralPatientDt.getReferralPatientHd().getPatient().getMasrelation().getRelationName();
						}
						
						if (referralPatientDt.getReferralPatientHd().getPatient().getMasAdministrativeSex() != null) {
							gender = referralPatientDt.getReferralPatientHd().getPatient().getMasAdministrativeSex()
									.getAdministrativeSexName();
						}

					}
					if (referralPatientDt.getReferralPatientHd() != null
							&& referralPatientDt.getReferralPatientHd().getPatient() != null
							&& referralPatientDt.getReferralPatientHd().getPatient().getMasRank() != null) {
						rank = referralPatientDt.getReferralPatientHd().getPatient().getMasRank().getRankName();
					}
					if (referralPatientDt.getReferralPatientHd() != null
							&& referralPatientDt.getReferralPatientHd().getMasImpanneledHospital() != null) {
						hospitalName = referralPatientDt.getReferralPatientHd().getMasImpanneledHospital()
								.getImpanneledHospitalName();
					}
					map.put("id", referralPatientDt.getAdmissionId());
					map.put("service_no", HMSUtil.convertNullToEmptyString(serviceNo));
					map.put("patient_name", HMSUtil.convertNullToEmptyString(patientName));
					// Period period = ProjectUtils.getDOB(dob);
					if (dob == null) {
						map.put("age", "");
					} else {
						Period period = ProjectUtils.getDOB(dob);
						map.put("age", period.getYears());
					}
					map.put("gender", HMSUtil.convertNullToEmptyString(gender));
					map.put("rank", HMSUtil.convertNullToEmptyString(rank));
					map.put("mobile_no", HMSUtil.convertNullToEmptyString(mobileNo));
					map.put("name", HMSUtil.convertNullToEmptyString(name));
					map.put("relation", HMSUtil.convertNullToEmptyString(relation));
					map.put("hospital_name", HMSUtil.convertNullToEmptyString(hospitalName));
					// map.put("count", admissionPendingListCount);
					responseList.add(map);
				}

				obj.put("admissionPendingList", responseList);
				obj.put("count", admissionPendingListCount);
			} else {
				obj.put("admissionPendingList", new JSONArray());
				obj.put("count", 0);
			}

		} else {
			obj.put("admissionPendingList", new JSONArray());
			obj.put("count", 0);
		}
		return obj.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getPendingDischargeList(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> jsonResponse = od.getPendingDischargeList(jsondata, request, response);
		JSONObject obj = new JSONObject();
		List<Map<String, Object>> responseList = new ArrayList<>();
		List<AdmissionDischarge> dischargePendingList = (List<AdmissionDischarge>) jsonResponse
				.get("dischargePendingList");
		String dischargePendingListCount = jsonResponse.get("count") + "";
		if (dischargePendingList != null && dischargePendingList.size() > 0) {
			for (AdmissionDischarge admissionDischarge : dischargePendingList) {
				Map<String, Object> map2 = new HashMap<>();
				String serviceNo = "", patientName = "", gender = "", rank = "", ward = "", disposal = "";
				Date age = null, dateOfAdmission = null;
				if (admissionDischarge.getPatient() != null) {
					serviceNo = admissionDischarge.getPatient().getServiceNo();
					patientName = admissionDischarge.getPatient().getPatientName();
					age = admissionDischarge.getPatient().getDateOfBirth();
					if (admissionDischarge.getPatient().getMasAdministrativeSex() != null) {
						gender = admissionDischarge.getPatient().getMasAdministrativeSex().getAdministrativeSexName();
					}
					if (admissionDischarge.getPatient().getMasRank() != null) {
						rank = admissionDischarge.getPatient().getMasRank().getRankName();
					}

				}
				if (admissionDischarge.getWardNo() != null) {
					ward = admissionDischarge.getWardNo().toString();
				}

				dateOfAdmission = admissionDischarge.getDateOfAdmission();
				if (admissionDischarge.getMasDisposal() != null) {
					disposal = admissionDischarge.getMasDisposal().getDisposalName();
				}
				String admissionDate = HMSUtil.convertDateToStringFormat(dateOfAdmission, "dd/MM/yyyy");
				Period period = ProjectUtils.getDOB(age);
				map2.put("id", admissionDischarge.getAdmissionId());
				map2.put("service_no", HMSUtil.convertNullToEmptyString(serviceNo));
				map2.put("patient_name", HMSUtil.convertNullToEmptyString(patientName));
				map2.put("age", HMSUtil.convertNullToEmptyString(period.getYears()));
				map2.put("gender", HMSUtil.convertNullToEmptyString(gender));
				map2.put("rank", HMSUtil.convertNullToEmptyString(rank));
				map2.put("date_of_admission", HMSUtil.convertNullToEmptyString(admissionDate));
				map2.put("ward", HMSUtil.convertNullToEmptyString(ward));
				map2.put("disposal", HMSUtil.convertNullToEmptyString(disposal));
				responseList.add(map2);
			}
			obj.put("dischargePendingList", responseList);
			obj.put("count", dischargePendingListCount);

		} else {
			obj.put("dischargePendingList", new JSONArray());
			obj.put("count", 0);
		}
		return obj.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String admissionAndDischarge(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		List<Map<String, Object>> jsonList = new ArrayList<>();
		Map<String, Object> jsonResponse = od.admissionAndDischarge(jsondata, request, response);
		if (!jsonResponse.isEmpty()) {
			List<AdmissionDischarge> list = (List<AdmissionDischarge>) jsonResponse.get("patientDetail");
			List<MasDisposal> disposalList = (List<MasDisposal>) jsonResponse.get("disposalList");
			if (list.size() > 0) {
				String admission_id = "", dateOfAdmission = "", ward = "", disposal = "", disposalId = "",
						noOfDays = "", remark = "", admissionNo = "", headerId = "", patientId = "", serviceNo = "",
						patientName = "", gender = "", age = "", rank = "", mobileNo = "", referralDate = "",
						hospitalName = "", speciality = "", diagnosis = "", insturctions = "";

				for (AdmissionDischarge admissionDischarge : list) {
					Map<String, Object> map = new HashMap<>();
					admission_id = admissionDischarge.getAdmissionId() + "";
					if (admissionDischarge.getDateOfAdmission() != null) {
						dateOfAdmission = HMSUtil.convertDateToStringFormat(admissionDischarge.getDateOfAdmission(),
								"dd-MM-yyyy");
					}

					if (admissionDischarge.getWardNo() != null) {
						ward = admissionDischarge.getWardNo() + "";
					}

					if (admissionDischarge.getMasDisposal() != null
							&& admissionDischarge.getMasDisposal().getDisposalName() != null) {
						disposal = admissionDischarge.getMasDisposal().getDisposalName();
						disposalId = admissionDischarge.getMasDisposal().getDisposalId() + "";
					}

					if (admissionDischarge.getNoOfDays() != null) {
						noOfDays = admissionDischarge.getNoOfDays() + "";
					}

					if (admissionDischarge.getRemarks() != null) {
						remark = admissionDischarge.getRemarks();
					}

					if (admissionDischarge.getDateOfAdmission() != null) {
						admissionNo = admissionDischarge.getAdmissionNo();
					}
					if (admissionDischarge.getReferralPatientHd() != null) {
						headerId = admissionDischarge.getReferralPatientHd().getRefrealHdId() + "";
					}
					// speciality =
					// admissionDischarge.getReferralPatientHd().getReferralPatientDts();
					if (admissionDischarge.getReferralPatientHd() != null) {
						for (ReferralPatientDt referralPatientDt : admissionDischarge.getReferralPatientHd()
								.getReferralPatientDts()) {
							if (referralPatientDt.getExtDepartment() != null) {
								speciality += referralPatientDt.getExtDepartment() + ",";
							}
							
							if (referralPatientDt.getMasIcd() != null && referralPatientDt.getMasIcd().getIcdName() != null) {
								diagnosis += referralPatientDt.getMasIcd().getIcdName() + ",";
							}
							if (referralPatientDt.getInstruction() != null) {
								insturctions = referralPatientDt.getInstruction() + ",";
							}

						}
						if (speciality.length() > 0) {
							speciality = speciality.substring(0, speciality.length() - 1);
						}
						if (diagnosis.length() > 0) {
							diagnosis = diagnosis.substring(0, diagnosis.length() - 1);

						}
						if (insturctions.length() > 0) {

							insturctions = insturctions.substring(0, insturctions.length() - 1);
						}
					}

					if (admissionDischarge.getReferralPatientHd() != null) {
						referralDate = HMSUtil.convertDateToStringFormat(admissionDischarge.getReferralPatientHd().getReferralIniDate(), "dd/MM/yyyy");
						
						}

					// if (admissionDischarge.getReferralPatientHd() != null) {
					// if (admissionDischarge.getReferralPatientHd().getPatient() != null) {
					if (admissionDischarge.getPatient() != null) {
						patientId = admissionDischarge.getPatient().getPatientId() + "";
						serviceNo = admissionDischarge.getPatient().getServiceNo();
						patientName = admissionDischarge.getPatient().getPatientName();
						if (admissionDischarge.getPatient().getMasAdministrativeSex() != null) {
							gender = admissionDischarge.getPatient().getMasAdministrativeSex()
									.getAdministrativeSexName();
						}
						if (admissionDischarge.getPatient().getMasRank() != null) {
							rank = admissionDischarge.getPatient().getMasRank().getRankName();
						}
						mobileNo = admissionDischarge.getPatient().getMobileNumber();
						/*age = HMSUtil.convertDateToStringFormat(admissionDischarge.getPatient().getDateOfBirth(),
								"dd-MM-yyyy");*/
						Period p = ProjectUtils.getDOB(admissionDischarge.getPatient().getDateOfBirth());
						age = p.getYears()+"";
					}
					// }
					// }
					if (admissionDischarge.getReferralPatientHd() != null
							&& admissionDischarge.getReferralPatientHd().getMasImpanneledHospital() != null) {
						hospitalName = admissionDischarge.getReferralPatientHd().getMasImpanneledHospital()
								.getImpanneledHospitalName();
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
					map.put("disposalId", disposalId);
					map.put("no_of_days", noOfDays);
					map.put("admission_no", admissionNo);
					map.put("remarks", remark);
					jsonList.add(map);

				}
				// obj.put("status","1");
				obj.put("referralList", jsonList);
			} else {
				// obj.put("status","0");
				obj.put("referralList", new JSONArray());
			}

			if (disposalList.size() > 0) {
				List<Map<String, Object>> masDisposalsList = new ArrayList<>();
				for (MasDisposal masDisposal : disposalList) {
					Map<String, Object> map2 = new HashMap<>();
					String disposalId = masDisposal.getDisposalId() + "";
					String disposalName = masDisposal.getDisposalName();
					map2.put("id", disposalId);
					map2.put("disposal_name", disposalName);
					masDisposalsList.add(map2);
				}
				obj.put("disposalList", masDisposalsList);

			} else {
				obj.put("disposalList", new JSONArray());
			}
		} else {
			obj.put("referralList", new JSONArray());
			obj.put("disposalList", new JSONArray());
		}

		return obj.toString();
	}

	@Override
	public String savePatientAdmission(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		String result = od.savePatientAdmission(jsondata, request, response);
		JSONObject obj = new JSONObject();
		obj.put("msg", result);
		return obj.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getServiceWisePatientList(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = od.getServiceWisePatientList(jsondata, request, response);
		JSONObject obj = new JSONObject();
		if (!map.isEmpty()) {
			List<Patient> patientList = (List<Patient>) map.get("patientList");
			List<MasDisposal> disposalList = (List<MasDisposal>) map.get("disposalList");
			if (patientList.size() > 0) {
				List<Map<String, String>> list1 = new ArrayList<>();
				for (Patient patient : patientList) {
					Map<String, String> patientMap = new HashMap<>();
					patientMap.put("patient_id", patient.getPatientId() + "");
					patientMap.put("patient_name", patient.getPatientName());
					patientMap.put("service_no", patient.getServiceNo());
					Period p = ProjectUtils.getDOB(patient.getDateOfBirth());
					patientMap.put("age", p.getYears() + "");
					if (patient.getMasAdministrativeSex() != null) {
						patientMap.put("gender", patient.getMasAdministrativeSex().getAdministrativeSexName());
					}
					if (patient.getMasRank() != null) {
						patientMap.put("rank", patient.getMasRank().getRankName());
					}
					patientMap.put("mobile_no", patient.getMobileNumber());
					String name = "";
					if (patient.getMasEmployee() != null) {
						String firstName = "", middleName = "", lastName = "";
						if (patient.getMasEmployee().getFirstName() != null) {
							name += patient.getMasEmployee().getFirstName() + " ";
						}
						if (patient.getMasEmployee().getMiddleName() != null) {
							name += patient.getMasEmployee().getMiddleName() + " ";
						}
						if (patient.getMasEmployee().getLastName() != null) {
							name += patient.getMasEmployee().getLastName();
						}						
					}
					String relation = "";
					if (patient.getMasRelation() != null) {
					patientMap.put("relation",
							HMSUtil.convertNullToEmptyString(patient.getMasRelation().getRelationName()));
					}
					
					patientMap.put("emp_name", name);
					list1.add(patientMap);
				}
				obj.put("patient_list", list1);
			} else {
				obj.put("patient_list", new JSONArray());
			}
			if (disposalList.size() > 0) {
				List<Map<String, String>> list2 = new ArrayList<>();
				for (MasDisposal masDisposal : disposalList) {
					Map<String, String> disposalMap = new HashMap<>();
					String disposalId = masDisposal.getDisposalId() + "";
					String disposalName = masDisposal.getDisposalName();
					disposalMap.put("id", disposalId);
					disposalMap.put("disposal_name", disposalName);
					list2.add(disposalMap);
				}
				obj.put("disposal_list", list2);
			} else {
				obj.put("disposal_list", new JSONArray());
			}

		} else {
			obj.put("disposal_list", new JSONArray());
			obj.put("patient_list", new JSONArray());
		}
		return obj.toString();

	}

	public String saveNewAdmission(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
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
		List<Map<String, Object>> nursingCareWaitingList = new ArrayList<>();
		Map<String, Object> map = od.nursingCareWaitingList(jsondata, request, response);
		if (map != null && !map.isEmpty()) {
			List<ProcedureHd> list = (List<ProcedureHd>) map.get("nursingCareList");
			if (list != null && !list.isEmpty()) {
				for (ProcedureHd hd : list) {
					Map<String, Object> data = new HashMap<>();
					String tokenNo = "", name = "", firstName = "", middleName = "", lastName = "", opdDate = "",
							priority = "", patientName = "", age = "", gender = "", status = "", departmentName="", serviceNo="";
					if (hd.getVisit() != null) {
						tokenNo = hd.getVisit().getTokenNo() + "";

						/*if (hd.getVisit().getIntDoctorId() != null) {
							firstName = hd.getVisit().getDoctorId().getFirstName() + "";
							middleName = hd.getVisit().getDoctorId().getMiddleName() + "";
							lastName = hd.getVisit().getDoctorId().getLastName() + "";
						}*/
						name = firstName + " " + middleName + " " + lastName;
						priority = hd.getVisit().getPriority() + "";
						if(hd.getVisit().getMasDepartment() != null) {
							departmentName = HMSUtil.convertNullToEmptyString(hd.getVisit().getMasDepartment().getDepartmentName());
						}	
					}
					data.put("id", hd.getProcedureHdId());
					data.put("token_no", HMSUtil.convertNullToEmptyString(tokenNo));
					data.put("service_no", HMSUtil.convertNullToEmptyString(hd.getPatient().getServiceNo()));
					data.put("doctor_name", departmentName);
					data.put("priority", HMSUtil.convertNullToEmptyString(priority));					
					if(hd.getOpdPatientDetails() != null) {
						opdDate = HMSUtil.convertNullToEmptyString(HMSUtil.convertDateToStringFormat(hd.getOpdPatientDetails().getOpdDate(), "dd-MM-yyyy"));
					}
					 
					data.put("opd_date", opdDate);
					if (hd.getPatient() != null) {
						patientName = hd.getPatient().getPatientName();
						Period p = ProjectUtils.getDOB(hd.getPatient().getDateOfBirth());
						age = p.getYears() + "";
						if (hd.getPatient().getMasAdministrativeSex() != null) {
							gender = hd.getPatient().getMasAdministrativeSex().getAdministrativeSexName().toString();
						}
					}
					data.put("patient_name", HMSUtil.convertNullToEmptyString(patientName));
					data.put("age", HMSUtil.convertNullToEmptyString(age));
					data.put("gender", HMSUtil.convertNullToEmptyString(gender));
					data.put("status", HMSUtil.convertNullToEmptyString(hd.getStatus()));
					nursingCareWaitingList.add(data);

				}
				obj.put("status", "1");
				obj.put("count", map.get("count"));
				obj.put("nursingCareWaitingList", nursingCareWaitingList);
			} else {
				obj.put("status", "0");
				obj.put("count", "0");
				obj.put("nursingCareWaitingList", new JSONArray());
			}
		} else {
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
		Map<String, Object> patientMap = new HashMap<>();
		Map<String, Object> map = od.getNursingCareDetail(jsondata, request, response);
		if (map != null && !map.isEmpty()) {
			List<ProcedureDt> list = (List<ProcedureDt>) map.get("detailList");
			List<ProcedureDt> list2 = new ArrayList<>();
			List<Long> ids = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				if (ids.contains(list.get(i).getMasNursingCare().getNursingId())) {
					continue;
				}
				ids.add(list.get(i).getMasNursingCare().getNursingId());
				list2.add(list.get(i));

			}
			if (list2 != null && list2.size() > 0) {
				List<Map<String, Object>> nursingDetailList = new ArrayList<>();
				String patientName = "", age = "", gender = "", diagnosis = "", procedureName = "", frequency = "",
						noOfDays = "", finalStatus = "";

				for (ProcedureDt dt : list2) {
					Map<String, Object> data = new HashMap<>();
					patientMap.put("patientName", HMSUtil.convertNullToEmptyString(dt.getProcedureHd().getPatient().getPatientName()));
					patientMap.put("age",
							HMSUtil.convertNullToEmptyString(ProjectUtils.getDOB(dt.getProcedureHd().getPatient().getDateOfBirth()).getYears()));
					patientMap.put("gender",
							HMSUtil.convertNullToEmptyString(dt.getProcedureHd().getPatient().getMasAdministrativeSex().getAdministrativeSexName()));
					patientMap.put("header_id", HMSUtil.convertNullToEmptyString(dt.getProcedureHd().getProcedureHdId()));
					patientMap.put("icd_diagnosis", HMSUtil
							.convertNullToEmptyString(dt.getProcedureHd().getOpdPatientDetails().getIcdDiagnosis()));
					patientMap.put("working_diagnosis", HMSUtil.convertNullToEmptyString(
							dt.getProcedureHd().getOpdPatientDetails().getWorkingDiagnosis()));
					
					data.put("id", dt.getProcedureDtId());
					data.put("procedureName", HMSUtil.convertNullToEmptyString(dt.getMasNursingCare().getNursingName()));
					if(dt.getMasFrequency() != null) {
						frequency = dt.getMasFrequency().getFrequencyName();
					}
					data.put("frequency", frequency);
					data.put("noOfDays", HMSUtil.convertNullToEmptyString(dt.getNoOfDays()));
					data.put("finalStatus", HMSUtil.convertNullToEmptyString(dt.getFinalProcedureStatus()));
					data.put("procedure_id", HMSUtil.convertNullToEmptyString(dt.getMasNursingCare().getNursingId()));
					
					nursingDetailList.add(data);
				}
				obj.put("status", "1");
				obj.put("patient_detail", patientMap);
				obj.put("nursingDetailList", nursingDetailList);
			} else {
				obj.put("status", "0");
				obj.put("patient_detail", patientMap);
				obj.put("nursingCareDetailList", new JSONArray());
			}
		} else {
			obj.put("status", "0");
			obj.put("patient_detail", patientMap);
			obj.put("nursingCareDetailList", new JSONArray());
		}
		return obj.toString();

	}

	@SuppressWarnings("unchecked")
	@Override
	public String getProcedureDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = od.getProcedureDetail(jsondata, request, response);
		JSONObject obj = new JSONObject();
		Map<String, Object> detailInfo = new HashMap<>();
		if (map != null && !map.isEmpty()) {
			List<ProcedureDt> list = (List<ProcedureDt>) map.get("detailList");
			if (list != null && list.size() > 0) {
				List<Map<String, Object>> detailList = new ArrayList<>();
				for (ProcedureDt dt : list) {
					Map<String, Object> detailMap = new HashMap<>();
					detailInfo.put("id", dt.getProcedureDtId());
					detailInfo.put("procedure_name", dt.getMasNursingCare().getNursingName());
					detailInfo.put("frequency", dt.getMasFrequency().getFrequencyName());
					detailInfo.put("feq", dt.getMasFrequency().getFeq());
					detailInfo.put("no_of_days", dt.getNoOfDays());
					detailInfo.put("op_remarks", dt.getRemarks());
					String procedureID = "", frequency_id="", header_id = "";
					if(dt.getMasNursingCare() != null) {
						procedureID = HMSUtil.convertNullToEmptyString(dt.getMasNursingCare().getNursingId());
					}
					detailInfo.put("procedure_id", procedureID);
					if(dt.getMasFrequency() != null) {
						frequency_id = HMSUtil.convertNullToEmptyString(dt.getMasFrequency().getFrequencyId());
					}
					detailInfo.put("frequency_id", frequency_id);
					if(dt.getProcedureHd() != null) {
						header_id = HMSUtil.convertNullToEmptyString(dt.getProcedureHd().getProcedureHdId());
					}
					detailInfo.put("header_id", header_id);
					Date date = dt.getAppointmentDate();
					String appDate = HMSUtil.convertDateToStringFormat(date, "dd-MM-yyyy");
					detailMap.put("appointment_date", appDate);
					Date pDate = dt.getProcedureDate();
					String proDate = HMSUtil.convertDateToStringFormat(pDate, "dd-MM-yyyy");
					detailMap.put("procedure_date", proDate);
					String nurRemarks = "";
					if (dt.getNursingRemark() != null) {
						nurRemarks = dt.getNursingRemark();
					}
					detailMap.put("nursing_remarks", nurRemarks);
					System.out.println("dt.getStatus() "+dt.getStatus());
					detailMap.put("status", HMSUtil.convertNullToEmptyString(dt.getStatus()));
					detailMap.put("id", dt.getProcedureDtId());
					detailList.add(detailMap);
				}
				obj.put("status", "1");
				obj.put("detailList", detailList);
				obj.put("detailInfo", detailInfo);

			}
		} else {
			obj.put("status", "0");
			obj.put("detailList", new JSONArray());
			obj.put("detailInfo", detailInfo);
		}
		return obj.toString();
	}

	@Override
	public Map<String, Object> getWaitingPatientList(Map mapForDS) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveProcedureDetail(Map<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		String result = od.saveProcedureDetail(jsondata, request, response);
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		return obj.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String physioTherapyWaitingList(Map<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		List<Map<String, Object>> physioTherapyWaitingList = new ArrayList<>();
		JSONObject obj = new JSONObject();
		Map<String, Object> map = (Map<String, Object>) od.physioTherapyWaitingList(jsondata, request, response);
		if (map != null && !map.isEmpty()) {
			List<ProcedureHd> list = (List<ProcedureHd>) map.get("physioTherapyWaitingList");
			if (list != null && !list.isEmpty()) {
				for (ProcedureHd hd : list) {
					Map<String, Object> data = new HashMap<>();
					String serviceNo = "", name = "", firstName = "", middleName = "", lastName = "", opdDate = "",
							priority = "", patientName = "", age = "", gender = "", status = "", departmentName="";
					if (hd.getVisit() != null) {
						// tokenNo = hd.getVisit().getTokenNo()+"";

					/*	if (hd.getVisit().getIntDoctorId() != null) {
							firstName = hd.getVisit().getDoctorId().getFirstName() + "";
							middleName = hd.getVisit().getDoctorId().getMiddleName() + "";
							lastName = hd.getVisit().getDoctorId().getLastName() + "";
						}*/
						name = firstName + " " + middleName + " " + lastName;
						priority = hd.getVisit().getPriority() + "";
						if(hd.getVisit().getMasDepartment() != null) {
							departmentName = HMSUtil.convertNullToEmptyString(hd.getVisit().getMasDepartment().getDepartmentName());
						}						
					}
					data.put("id", hd.getProcedureHdId());
					// data.put("token_no", tokenNo);
					data.put("doctor_name", departmentName);
					data.put("priority", HMSUtil.convertNullToEmptyString(priority));
					/*
					 * if(hd.getOpdPatientDetail() != null) { opdDate =
					 * HMSUtil.convertDateToStringFormat(hd.getOpdPatientDetail().getOpdDate(),
					 * "dd-MM-yyyy"); }
					 */
					data.put("opd_date", HMSUtil.convertDateToStringFormat(hd.getOpdPatientDetails().getOpdDate(),"dd-MM-yyyy"));
					if (hd.getPatient() != null) {
						serviceNo = hd.getPatient().getServiceNo();
						patientName = hd.getPatient().getPatientName();
						Period p = ProjectUtils.getDOB(hd.getPatient().getDateOfBirth());
						age = p.getYears() + "";
						if (hd.getPatient().getMasAdministrativeSex() != null) {
							gender = hd.getPatient().getMasAdministrativeSex().getAdministrativeSexName().toString();
						}
					}
					data.put("service_no", HMSUtil.convertNullToEmptyString(serviceNo));
					data.put("patient_name", HMSUtil.convertNullToEmptyString(patientName));
					data.put("age", HMSUtil.convertNullToEmptyString(age));
					data.put("gender", HMSUtil.convertNullToEmptyString(gender));
					data.put("status", HMSUtil.convertNullToEmptyString(hd.getStatus()));
					physioTherapyWaitingList.add(data);

				}
				obj.put("status", "1");
				obj.put("count", map.get("count"));
				obj.put("physioTherapyWaitingList", physioTherapyWaitingList);
			} else {
				obj.put("status", "0");
				obj.put("count", "");
				obj.put("physioTherapyWaitingList", new JSONArray());
			}
		} else {
			obj.put("status", "0");
			obj.put("count", "");
			obj.put("physioTherapyWaitingList", new JSONArray());
		}

		return obj.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getphysioTherapyDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		Map<String, Object> patientMap = new HashMap<>();
		Map<String, Object> map = od.getphysioTherapyDetail(jsondata, request, response);
		if (map != null && !map.isEmpty()) {
			List<ProcedureDt> list = (List<ProcedureDt>) map.get("detailList");
			List<ProcedureDt> list2 = new ArrayList<>();
			List<Long> ids = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				if (ids.contains(list.get(i).getMasNursingCare().getNursingId())) {
					continue;
				}
				ids.add(list.get(i).getMasNursingCare().getNursingId());
				list2.add(list.get(i));

			}
			System.out.println("size of list is " + list.size() + " & size of list2 is " + list2.size());
			if (list2 != null && list2.size() > 0) {
				List<Map<String, Object>> physioDetailList = new ArrayList<>();
				String patientName = "", age = "", gender = "", diagnosis = "", procedureName = "", frequency = "",
						noOfDays = "", finalStatus = "", nursing_remarks;

				for (ProcedureDt dt : list2) {
					Map<String, Object> data = new HashMap<>();

					patientMap.put("patientName",
							HMSUtil.convertNullToEmptyString(dt.getProcedureHd().getPatient().getPatientName()));
					patientMap.put("age", HMSUtil.convertNullToEmptyString(
							ProjectUtils.getDOB(dt.getProcedureHd().getPatient().getDateOfBirth()).getYears()));
					patientMap.put("gender", HMSUtil.convertNullToEmptyString(
							dt.getProcedureHd().getPatient().getMasAdministrativeSex().getAdministrativeSexName()));
					patientMap.put("header_id",
							HMSUtil.convertNullToEmptyString(dt.getProcedureHd().getProcedureHdId()));
					patientMap.put("icd_diagnosis", HMSUtil
							.convertNullToEmptyString(dt.getProcedureHd().getOpdPatientDetails().getIcdDiagnosis()));
					patientMap.put("working_diagnosis", HMSUtil.convertNullToEmptyString(
							dt.getProcedureHd().getOpdPatientDetails().getWorkingDiagnosis()));
					data.put("id", HMSUtil.convertNullToEmptyString(dt.getProcedureDtId()));
					data.put("physiotherapy_name",
							HMSUtil.convertNullToEmptyString(dt.getMasNursingCare().getNursingName()));
					data.put("start_date", HMSUtil.convertNullToEmptyString(dt.getAppointmentDate()));
					data.put("frequency", HMSUtil.convertNullToEmptyString(dt.getMasFrequency().getFrequencyName()));
					data.put("noOfDays", HMSUtil.convertNullToEmptyString(dt.getNoOfDays()));
					data.put("op_remarks", HMSUtil.convertNullToEmptyString(dt.getRemarks()));
					data.put("finalStatus", HMSUtil.convertNullToEmptyString(dt.getFinalProcedureStatus()));
					data.put("procedure_id", HMSUtil.convertNullToEmptyString(dt.getMasNursingCare().getNursingId()));
					physioDetailList.add(data);
				}
				obj.put("status", "1");
				obj.put("patient_detail", patientMap);
				obj.put("physioDetailList", physioDetailList);
			} else {
				obj.put("status", "0");
				obj.put("patient_detail", patientMap);
				obj.put("physioDetailList", new JSONArray());
			}
		} else {
			obj.put("status", "0");
			obj.put("patient_detail", patientMap);
			obj.put("physioDetailList", new JSONArray());
		}
		return obj.toString();

	}

	////////////////////////////// Examination Detail ///////////////
	@Override
	public String getExaminationDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
		if (!jsondata.isEmpty()) {

			JSONObject nullbalankvalidation = null;
			nullbalankvalidation = ValidateUtils.checkPatientVisit(jsondata);
			if (nullbalankvalidation.optString("status").equals("0")) {
				return nullbalankvalidation.toString();
			} else {
				OpdPatientDetail opdPatientDetail = null;
				if (jsondata.get("visitId") != null) {
					opdPatientDetail = opdPatientDetailDao
							.getOpdPatientDetails(Long.parseLong(jsondata.get("visitId").toString()));
				}
				if (opdPatientDetail != null) {
					try {

						HashMap<String, Object> pt = new HashMap<String, Object>();
						/*
						 * Date s = v.getPatient().getDateOfBirth(); Period p = ProjectUtils.getDOB(s);
						 */
						pt.put("pollor", opdPatientDetail.getPollor());
						pt.put("ordema", opdPatientDetail.getEdema());

						pt.put("cyanosis", opdPatientDetail.getCyanosis());
						pt.put("hairnail", opdPatientDetail.getHairNail());

						if (opdPatientDetail.getJauindice() != null) {
							pt.put("jaundice", opdPatientDetail.getJauindice());
						}
						pt.put("lymphnode", opdPatientDetail.getLymphNode());
						if (opdPatientDetail.getClubbing() != null) {
							pt.put("clubbing", opdPatientDetail.getClubbing());
						}
						if (opdPatientDetail.getThyroid() != null) {
							pt.put("thyroid", opdPatientDetail.getThyroid());
						}

						if (opdPatientDetail.getTremors() != null) {
							pt.put("tremors", opdPatientDetail.getTremors());
						}
						if (opdPatientDetail.getGeneralOther() != null) {
							pt.put("others", opdPatientDetail.getGeneralOther());
						}
						if (opdPatientDetail.getCns() != null) {
							pt.put("cns", opdPatientDetail.getCns());
						}
						pt.put("chest", opdPatientDetail.getChestResp());
						pt.put("musculoskeletal", opdPatientDetail.getMusculoskeletal());

						pt.put("cvs", opdPatientDetail.getCvs());
						pt.put("skin", opdPatientDetail.getSkin());
						if (opdPatientDetail.getGi() != null) {
							pt.put("gi", opdPatientDetail.getGi());
						}

						if (opdPatientDetail.getGenitoUrinary() != null) {
							pt.put("geneticurinary", opdPatientDetail.getGenitoUrinary());
						}
						pt.put("OthersForSystem", opdPatientDetail.getSystemOther());
						pt.put("workingdiagnosis", opdPatientDetail.getWorkingDiagnosis());
						pt.put("snomeddiagnosis", opdPatientDetail.getSnomedDiagnosis());
						pt.put("opdPatientDetailId", opdPatientDetail.getOpdPatientDetailsId());
						pt.put("allergyId", opdPatientDetail.getAllergyId());
						c.add(pt);
						json.put("data", c);
						json.put("msg", "OPD Patients Visit List  get  sucessfull... ");
						json.put("status", "1");

					}

					catch (Exception e) {
						e.printStackTrace();
						return "{\"status\":\"0\",\"msg\":\"Somting went wrong}";
					}
				} else {
					try {
						json.put("msg", "Visit ID data not found");
						json.put("status", 0);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return json.toString();
	}

	////////////////////////////// Examination Detail ///////////////
	@Override
	public String getInvestigationDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<Object[]> listObject = null;
		List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
		if (!jsondata.isEmpty()) {

			JSONObject nullbalankvalidation = null;
			nullbalankvalidation = ValidateUtils.checkPatientVisit(jsondata);
			if (nullbalankvalidation.optString("status").equals("0")) {
				return nullbalankvalidation.toString();
			} else {
				List<DgMasInvestigation> listDgMasInvestigation = null;
				if (jsondata.get("visitId") != null) {
					listObject = dgOrderhdDao
							.getDgMasInvestigations(Long.parseLong(jsondata.get("visitId").toString()));
				}
				Long investigationId = null;
				String investigationName = "";
				Long orderHdId = null;
				String urgent = "";
				String labMark = "";
				String orderDate = "";
				Long visitId = null;
				String otherInvestigation = "";
				Long departId = null;
				Long hospitalId = null;
				Long dgOrderDtId = null;
				if (listObject != null) {
					try {
						for (Iterator<?> it = listObject.iterator(); it.hasNext();) {
							Object[] row = (Object[]) it.next();

							/*
							 * for(Object dgMasInvestigation : listObject) { Object row=dgMasInvestigation;
							 */
							HashMap<String, Object> pt = new HashMap<String, Object>();

							if (row[0] != null) {
								investigationId = Long.parseLong(row[0].toString());
							}
							if (row[1] != null) {
								investigationName = row[1].toString();
							}

							if (row[2] != null) {
								orderHdId = Long.parseLong(row[2].toString());
							}

							if (row[3] != null) {
								labMark = row[3].toString();
							}
							if (row[4] != null) {
								urgent = row[4].toString();
							}

							if (row[5] != null) {
								orderDate = row[5].toString();
								Date dd1 = HMSUtil.dateFormatteryyyymmdd(orderDate);
								orderDate = HMSUtil.getDateWithoutTime(dd1);

							}
							if (row[6] != null) {
								visitId = Long.parseLong(row[6].toString());
							}
							if (row[7] != null) {
								otherInvestigation = row[7].toString();
							}
							if (row[8] != null) {
								departId = Long.parseLong(row[8].toString());
							}
							if (row[9] != null) {
								hospitalId = Long.parseLong(row[10].toString());
							}
							if (row[10] != null) {
								dgOrderDtId = Long.parseLong(row[10].toString());
							}

							pt.put("investigationName", investigationName);
							pt.put("investigationId", investigationId);
							pt.put("labMark", labMark);
							pt.put("urgent", urgent);
							pt.put("orderDate", orderDate);
							pt.put("visitId", visitId);
							pt.put("otherInvestigation", otherInvestigation);
							pt.put("departId", departId);
							pt.put("hospitalId", hospitalId);
							pt.put("dgOrderDtId", dgOrderDtId);
							pt.put("orderHdId", orderHdId);

							c.add(pt);
						}
						json.put("listObject", c);
						json.put("msg", "OPD Patients Visit List  get  sucessfull... ");
						json.put("status", "1");

					}

					catch (Exception e) {
						e.printStackTrace();
						return "{\"status\":\"0\",\"msg\":\"Somting went wrong}";
					}
				} else {
					try {
						json.put("msg", "Visit ID data not found");
						json.put("status", 0);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return json.toString();

	}

	@Override
	public String getTreatmentPatientDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<Object[]> listObject = null;
		List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
		if (!jsondata.isEmpty()) {

			JSONObject nullbalankvalidation = null;
			nullbalankvalidation = ValidateUtils.checkPatientVisit(jsondata);
			if (nullbalankvalidation.optString("status").equals("0")) {
				return nullbalankvalidation.toString();
			} else {
				if (jsondata.get("visitId") != null) {
					listObject = dgOrderhdDao.getTreatementDetail(Long.parseLong(jsondata.get("visitId").toString()));
				}
				List<MasFrequency> mas_frequency = md.getMasFrequency();

				String nomenclature = "";
				Long itemId = null;
				Long frequencyId = null;
				String frequencyName = "";
				String frequencyCode = "";
				String dosage = "";
				Long noOfDays = null;
				Long precryptionHdId = null;
				Long precriptionDtId = null;
				String dispStock = "";
				Long total = null;
				String instruction = "";
				String storeStoke = "";
				String PVMSno="";
				String otherTreatement="";
				if (listObject != null) {
					try {
						for (Iterator<?> it = listObject.iterator(); it.hasNext();) {
							Object[] row = (Object[]) it.next();

							/*
							 * for(Object dgMasInvestigation : listObject) { Object row=dgMasInvestigation;
							 */
							HashMap<String, Object> pt = new HashMap<String, Object>();

							if (row[0] != null) {
								nomenclature = row[0].toString();
							}
							if (row[1] != null) {
								itemId = Long.parseLong(row[1].toString());
							}

							if (row[2] != null) {
								frequencyId = Long.parseLong(row[2].toString());
							}

							// odt.LAB_MARK,odt.urgent,odt.ORDER_DATE
							if (row[3] != null) {
								frequencyName = row[3].toString();
							}
							if (row[4] != null) {
								frequencyCode = row[4].toString();
							}

							if (row[5] != null) {
								noOfDays = Long.parseLong(row[5].toString());
							}
							if (row[6] != null) {
								dosage = row[6].toString();
							}
							if (row[7] != null) {
								precryptionHdId = Long.parseLong(row[7].toString());
							}
							if (row[8] != null) {
								precriptionDtId = Long.parseLong(row[8].toString());
							}
							if (row[13] != null) {
								dispStock = row[13].toString();
							}

							if (row[10] != null) {
								total = Long.parseLong(row[10].toString());
							}
							if (row[11] != null) {
								instruction = row[11].toString();
							}
							if (row[12] != null) {
								storeStoke = row[12].toString();
							}
							if (row[14] != null) {
								PVMSno = row[14].toString();
							}
							if (row[15] != null) {
								otherTreatement = row[15].toString();
							}
							 
							pt.put("nomenclature", nomenclature);
							pt.put("itemId", itemId);
							pt.put("frequencyId", frequencyId);
							pt.put("frequencyName", frequencyName);
							pt.put("frequencyCode", frequencyCode);
							pt.put("dosage", dosage);
							pt.put("noOfDays", noOfDays);
							pt.put("precryptionHdId", precryptionHdId);
							pt.put("precriptionDtId", precriptionDtId);
							pt.put("dispStock", dispStock);
							pt.put("total", total);
							pt.put("instruction", instruction);
							pt.put("storeStoke", storeStoke);
							pt.put("PVMSno", PVMSno);
							pt.put("otherTreatement", otherTreatement);
							c.add(pt);
						}
						json.put("listObject", c);
						json.put("visitId", jsondata.get("visitId"));
						json.put("MasFrequencyList", mas_frequency);
						json.put("msg", "OPD Patients Visit List  get  sucessfull... ");
						json.put("status", "1");

					}

					catch (Exception e) {
						e.printStackTrace();
						return "{\"status\":\"0\",\"msg\":\"Somting went wrong}";
					}
				} else {
					try {
						json.put("msg", "Visit ID data not found");
						json.put("status", 0);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return json.toString();

	}

	@Override
	public String submitPatientRecall(HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		String visitId = "";
		String opdPatientDetaiId = "";
		String patientId ="";
		try {
			opdPatientDetaiId = payload.get("opdPatientDetailId").toString();
			opdPatientDetaiId = getReplaceString(opdPatientDetaiId);

			visitId = payload.get("VisitID").toString();
			visitId = getReplaceString(visitId);

			// SaveUpdate DgOrderDt
			String investigationIdValue = payload.get("investigationIdValue").toString();
			investigationIdValue = getReplaceString(investigationIdValue);

			String investigationDate = payload.get("investigationDate").toString();
			investigationDate = getReplaceString(investigationDate);

			String labradiologyCheck1 = payload.get("labradiologyCheck").toString();
			labradiologyCheck1 = getReplaceString(labradiologyCheck1);
			String urgent1 = "";
			if (payload.get("urgent") != null && !payload.get("urgent").equals("")) {
				urgent1 = payload.get("urgent").toString();
				urgent1 = getReplaceString(urgent1);
			}

			String marksAsLabValue = "";
			if (payload.get("marksAsLabValue") != null && !payload.get("marksAsLabValue").equals("")) {
				marksAsLabValue = payload.get("marksAsLabValue").toString();
				marksAsLabValue = getReplaceString(marksAsLabValue);
			}

			String urgentValue = "";
			if (payload.get("urgentValue") != null && !payload.get("urgentValue").equals("")) {
				urgentValue = payload.get("urgentValue").toString();
				urgentValue = getReplaceString(urgentValue);
			}

			String dgOrderDtIdValue = payload.get("dgOrderDtIdValue").toString();
			dgOrderDtIdValue = getReplaceString(dgOrderDtIdValue);

			String dgOrderHdIdValue = payload.get("dgOrderHdId").toString();
			dgOrderHdIdValue = getReplaceString(dgOrderHdIdValue);

			saveUpdateOpdPatientDetail(payload);

			/*
			 * saveOrUpDatedDgOrderDt(investigationIdValue, investigationDate,
			 * labradiologyCheck1, urgent1, dgOrderDtIdValue, dgOrderHdIdValue);
			 */
			saveOrUpDatedDgOrderDt(investigationIdValue, investigationDate, marksAsLabValue, urgentValue,
					dgOrderDtIdValue, dgOrderHdIdValue, payload);

			// SaveUpdate Mass store Item
			String itemIdValue = payload.get("itemId").toString();
			itemIdValue = getReplaceString(itemIdValue);

			String prescriptionHdId = payload.get("prescriptionHdId").toString();
			prescriptionHdId = getReplaceString(prescriptionHdId);

			String prescriptionDtIdValue = payload.get("prescriptionDtId").toString();
			prescriptionDtIdValue = getReplaceString(prescriptionDtIdValue);
			String dispensingUnitValue="";
			if(payload.get("dispensingUnit1")!=null) {
			  dispensingUnitValue = payload.get("dispensingUnit1").toString();
			dispensingUnitValue = getReplaceString(dispensingUnitValue);
			}
			String dosage1Value = payload.get("dosage1").toString();
			dosage1Value = getReplaceString(dosage1Value);

			String frequency1Value = payload.get("frequencyTre").toString();
			frequency1Value = getReplaceString(frequency1Value);

			String total1Value = payload.get("total1").toString();
			total1Value = getReplaceString(total1Value);

			String remarks1Value = payload.get("remarks1").toString();
			remarks1Value = getReplaceString(remarks1Value);

			String noOfDays1Value = payload.get("noOfDays1").toString();
			noOfDays1Value = getReplaceString(noOfDays1Value);

			String closingStock1 = payload.get("closingStock1").toString();
			closingStock1 = getReplaceString(closingStock1);

			saveOrUpDatedMassStoreItems(itemIdValue, dispensingUnitValue, dosage1Value, frequency1Value, noOfDays1Value,
					total1Value, remarks1Value, closingStock1, prescriptionDtIdValue, prescriptionHdId, payload);

			// For Referal update and inserrt only save contain one yes
			
			String referralForNew  =payload.get("referralForNew").toString();
			if(StringUtils.isNotBlank(referralForNew) && referralForNew.contains("1")) {
			
			String referHospitalList = payload.get("referHospitalList").toString();
			referHospitalList = getReplaceString(referHospitalList);
			String referHospitalValues = "";
			if (StringUtils.isNotBlank(referHospitalList)) {
				if (referHospitalList.contains("@")) {
					String[] referHospitalListValue = referHospitalList.split(",");
					int count = 0;
					for (int i = count; i < referHospitalListValue.length; i++) {
						String[] referHospitalListValueNew = referHospitalListValue[i].split("@");
						referHospitalValues += referHospitalListValueNew[0] + ",";
						// count+=2;
					}
				}
				referHospitalList = referHospitalValues;
			}

			String departmentValue = payload.get("departmentValue").toString();
			departmentValue = getReplaceString(departmentValue);

			String diagonsisId = payload.get("diagonsisId").toString();
			diagonsisId = getReplaceString(diagonsisId);

			String hos = payload.get("hos").toString();
			hos = getReplaceString(hos);

			String referalPatientDtValue = payload.get("referalPatientDt").toString();
			referalPatientDtValue = getReplaceString(referalPatientDtValue);

			String referalPatientHdValue = payload.get("referalPatientHd").toString();
			referalPatientHdValue = getReplaceString(referalPatientHdValue);

			String opdPatientDetailsId = payload.get("opdPatientDetailId").toString();
			opdPatientDetailsId = getReplaceString(opdPatientDetailsId);

			  patientId = payload.get("patientId").toString();
			patientId = getReplaceString(patientId);
			
			String referralNote ="";
			String referVisitDate ="";
			try {
				if(payload.get("referralNote")!=null) {
					  referralNote = payload.get("referralNote").toString();
					referralNote = getReplaceString(referralNote);
				}
				
				if(payload.get("referVisitDate")!=null) {
					  referVisitDate = payload.get("referVisitDate").toString();
					referVisitDate = getReplaceString(referVisitDate);
				}
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			
			saveOrUpdateReferrPatient(referHospitalList, departmentValue, diagonsisId, hos, referalPatientDtValue,
					referalPatientHdValue, opdPatientDetailsId, patientId,referralNote,referVisitDate,payload);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		saveOrUpdateProcedure(payload,visitId,opdPatientDetaiId,patientId);
		
		
		JSONObject json = new JSONObject();
		json.put("visitId", visitId);
		json.put("opdPatientDetaiId", opdPatientDetaiId);
		return json.toString();
	}

	
	
	
	
	
	
	// Saveupdadate Referal candidate
	public void saveOrUpdateReferrPatient(String referHospitalList, String departmentValue, String diagonsisId,
			String hos, String referalPatientDtValue, String referalPatientHdValue, String opdPatientDetailsId,
			String patient,String referalNotes,String referalDates,HashMap<String, Object> payload) {
		try {

			String[] referHospitalListValue = referHospitalList.split(",");
			String[] departmentValueArray = departmentValue.split(",");
			String[] diagonsisIdValue = diagonsisId.split(",");
			String[] hosValue = hos.split(",");

			String[] referalPatientDtValueArray = referalPatientDtValue.split(",");
			String[] referalPatientHdValueArray = referalPatientHdValue.split(",");
			HashMap<String, String> mapInvestigationMap = new HashMap<>();

			String finalValue = "";
			Integer counter = 1;
			String hospitalId="";
			String userId="";
			try {
			hospitalId = payload.get("hospitalId").toString();
			hospitalId = getReplaceString(hospitalId);

			userId = payload.get("userId").toString();
			userId = getReplaceString(userId);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			for (int i = 0; i < referHospitalListValue.length; i++) {

				if (StringUtils.isNotEmpty(referHospitalListValue[i].toString())
						&& !referHospitalListValue[i].equalsIgnoreCase("0")) {
					finalValue += referHospitalListValue[i].trim();
					if (!referalPatientDtValueArray[i].equalsIgnoreCase("0")
							&& StringUtils.isNotBlank(referalPatientDtValueArray[i])) {
						for (int m = i; m < referalPatientDtValueArray.length; m++) {
							finalValue += "," + referalPatientDtValueArray[m].trim();
							if (m == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}

					if (!departmentValueArray[i].equalsIgnoreCase("0")
							&& StringUtils.isNotBlank(departmentValueArray[i])) {
						for (int j = i; j < departmentValueArray.length; j++) {
							finalValue += "," + departmentValueArray[j].trim();
							if (j == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}
					if (i < diagonsisIdValue.length && StringUtils.isNotBlank(diagonsisIdValue[i])) {
						for (int k = i; k < diagonsisIdValue.length; k++) {
							finalValue += "," + diagonsisIdValue[k].trim();
							if (k == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}
					if (i < hosValue.length && StringUtils.isNotBlank(hosValue[i])) {
						for (int l = i; l < hosValue.length; l++) {
							finalValue += "," + hosValue[l].trim();
							if (l == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}
					mapInvestigationMap.put(referHospitalListValue[i].trim() + "@#" + counter, finalValue);
					finalValue = "";
					counter++;
				}
			}
			counter = 1;
			for (String referHospitalId : referHospitalListValue) {
				if (StringUtils.isNotEmpty(referHospitalId)) {
					if (mapInvestigationMap.containsKey(referHospitalId.trim() + "@#" + counter)) {
						String referHospitalIdValue = mapInvestigationMap.get(referHospitalId.trim() + "@#" + counter);

						if (StringUtils.isNotEmpty(referHospitalIdValue)) {

							String[] finalValueReferal = referHospitalIdValue.split(",");
							ReferralPatientDt referralPatientDt = null;
							if (finalValueReferal[1] != null && !finalValueReferal[1].equalsIgnoreCase("0")
									&& StringUtils.isNotBlank(finalValueReferal[1])) {
								referralPatientDt = dgOrderhdDao.getReferralPatientDtByReferaldtId(
										Long.parseLong(finalValueReferal[1].toString()));
							} else {
								referralPatientDt = new ReferralPatientDt();
								if (referalPatientHdValueArray != null
										&& StringUtils.isNotBlank(referalPatientHdValueArray[0])) {
									referralPatientDt
											.setRefrealHdId(Long.parseLong(referalPatientHdValueArray[0].toString()));
								}

							}

							if (finalValueReferal != null) {

								if (StringUtils.isNotEmpty(finalValueReferal[2]) && !finalValueReferal[2].equals("0")) {
									referralPatientDt.setExtDepartment(finalValueReferal[2]);
								}
								if (finalValueReferal[3] != null &&   StringUtils.isNotBlank(finalValueReferal[3]) &&  !finalValueReferal[3].equals("0"))
									referralPatientDt.setDiagnosisId(Long.parseLong(finalValueReferal[3].toString()));

								if (finalValueReferal[4] != null &&   StringUtils.isNotBlank(finalValueReferal[4]) && !finalValueReferal[4].equals("0"))
									referralPatientDt.setInstruction(finalValueReferal[4].toString());

								if (finalValueReferal[0] != null &&   StringUtils.isNotBlank(finalValueReferal[0]) && !finalValueReferal[0].equals("0")) {

									ReferralPatientHd referralPatientHd = dgOrderhdDao
											.getPatientReferalHdByExtHospitalId(
													Long.parseLong(finalValueReferal[0].trim()),
													Long.parseLong(opdPatientDetailsId.trim()));
									if (referralPatientHd != null) {
										referralPatientHd.setExtHospitalId(Long.parseLong(finalValueReferal[0].trim()));
									} else {
										referralPatientHd = new ReferralPatientHd();
										referralPatientHd.setExtHospitalId(Long.parseLong(finalValueReferal[0].trim()));
										referralPatientHd.setStatus("W");
										if(StringUtils.isNotBlank(hospitalId)) {
											Long hospitalIdValue=Long.parseLong(hospitalId);
											referralPatientHd.setHospitalId(hospitalIdValue);
										}
										
									}
									
									if(StringUtils.isNotEmpty(referalNotes)) {
										referralPatientHd.setReferralNote(referalNotes);
									}
									if(StringUtils.isNotBlank(referalDates)) {
										Date referalDate = HMSUtil.convertStringDateToUtilDate(referalDates, "yyyy-MM-dd");
										referralPatientHd.setReferralIniDate(referalDate);
									}
									referralPatientHd.setOpdPatientDetailsId(Long.parseLong(opdPatientDetailsId));
									referralPatientHd.setPatientId(Long.parseLong(patient));
									referralPatientHd.setTreatmentType("E");
									
									
									Long referalPatientHdId = dgOrderhdDao.saveOrUpdateReferalHd(referralPatientHd);
									referralPatientDt.setRefrealHdId(referalPatientHdId);
								}

							}

							dgOrderhdDao.saveOrUpdateReferalDt(referralPatientDt);
						}
					}
				}
				counter++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getReplaceString(String replaceValue) {
		return replaceValue.replaceAll("[\\[\\]]", "");
	}

	public static String getReplaceStringOnlyLastAndFirst(String replaceValue) {
		String value= StringUtils.removeStart(StringUtils.removeEnd(replaceValue, "]"), "[");
		return value;
	}
	public void saveOrUpDatedDgOrderDt(String investigationIdValue, String investigationDate, String labradiologyCheck1,
			String urgent1, String dgOrderDtIdValue, String dgOrderHdIdValue, HashMap<String, Object> payload) {
		try {

			String[] investigationIdValues = investigationIdValue.split(",");
			String[] investigationDateValues = investigationDate.split(",");
			String[] labradiologyCheck1Values = labradiologyCheck1.split(",");
			String[] urgent1Values = urgent1.split(",");

			String[] dgOrderDtIdValues = dgOrderDtIdValue.split(",");
			String[] dgOrderHdIdValues = dgOrderHdIdValue.split(",");
			HashMap<String, String> mapInvestigationMap = new HashMap<>();

			String otherInvestigation = payload.get("otherInvestigation").toString();
			otherInvestigation = getReplaceString(otherInvestigation);
			String finalValue = "";
			DgOrderhd dgOrderhd = null;
			Integer counter = 1;
			for (int i = 0; i < investigationIdValues.length; i++) {

				if (StringUtils.isNotEmpty(investigationIdValues[i].toString())
						&& !investigationIdValues[i].equalsIgnoreCase("0")) {
					finalValue += investigationIdValues[i].trim();
					if (!dgOrderDtIdValues[i].equalsIgnoreCase("0") && StringUtils.isNotBlank(dgOrderDtIdValues[i])) {
						for (int m = i; m < dgOrderDtIdValues.length; m++) {
							finalValue += "," + dgOrderDtIdValues[m].trim();
							if (m == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}

					if (!investigationDateValues[i].equalsIgnoreCase("0")
							&& StringUtils.isNotBlank(investigationDateValues[i])) {
						for (int j = i; j < investigationDateValues.length; j++) {
							finalValue += "," + investigationDateValues[j].trim();
							if (j == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}
					if (i < labradiologyCheck1Values.length && StringUtils.isNotBlank(labradiologyCheck1Values[i])) {
						for (int k = i; k < labradiologyCheck1Values.length; k++) {
							finalValue += "," + labradiologyCheck1Values[k].trim();
							if (k == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "O";
					}
					if (i < urgent1Values.length && StringUtils.isNotBlank(urgent1Values[i])) {
						for (int l = i; l < urgent1Values.length; l++) {
							finalValue += "," + urgent1Values[l].trim();
							if (l == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "N";
					}
					mapInvestigationMap.put(investigationIdValues[i].trim() + "@#" + counter, finalValue);
					finalValue = "";
					counter++;
				}

			}
			counter = 1;
			for (String investigationId : investigationIdValues) {
				if (StringUtils.isNotEmpty(investigationId)) {
					if (mapInvestigationMap.containsKey(investigationId.trim() + "@#" + counter)) {
						String investigationValue = mapInvestigationMap.get(investigationId.trim() + "@#" + counter);

						if (StringUtils.isNotEmpty(investigationValue)) {

							String[] finalValueInvestigation = investigationValue.split(",");
							DgOrderdt dgOrderdt = null;
							if (finalValueInvestigation[1] != null && StringUtils.isNotBlank(finalValueInvestigation[1] ) && !finalValueInvestigation[1].equalsIgnoreCase("0")
									&& StringUtils.isNotBlank(finalValueInvestigation[1])) {
								dgOrderdt = dgOrderhdDao
										.getDgOrderDtByDgOrderdtId(Long.parseLong(finalValueInvestigation[1]));
							} else {
								dgOrderdt = new DgOrderdt();
							}

							if (finalValueInvestigation != null) {

								if (dgOrderHdIdValues != null && dgOrderHdIdValues[0] != null
										&& StringUtils.isNotBlank(dgOrderHdIdValues[0])) {
									dgOrderdt.setOrderhdId(Long.parseLong(dgOrderHdIdValues[0].toString()));

									dgOrderhd = dgOrderhdDao
											.getDgOrderhdByDgOrderhdId(Long.parseLong(dgOrderHdIdValues[0].toString()));
									if(StringUtils.isNotEmpty(otherInvestigation)) {
										dgOrderhd.setOtherInvestigation(otherInvestigation);
									}
									dgOrderhdDao.saveOrUpdateDgOrderHd(dgOrderhd);
								} else {
									
									String visitId = payload.get("VisitID").toString();
									visitId = getReplaceString(visitId);
									DgOrderhd dgOrderDt=dgOrderhdDao.getDgOrderHdByVisitId(Long.parseLong(visitId.trim()));
									Long dgOrderHdId=null;
									
									if(dgOrderDt!=null) {
										dgOrderHdId=dgOrderDt.getOrderhdId();
										
									}
									else {
										String hospitalId="";
										dgOrderhd = new DgOrderhd();
										if(payload.get("hospitalId")!=null) {
										  hospitalId = payload.get("hospitalId").toString();
										hospitalId = getReplaceString(hospitalId);
										}
										String patientId = payload.get("patientId").toString();
										patientId = getReplaceString(patientId);

										dgOrderhd.setVisitId(Long.parseLong(visitId.trim()));
										if (StringUtils.isNotBlank(hospitalId))
											dgOrderhd.setHospitalId(Long.parseLong(hospitalId.trim()));
										if (StringUtils.isNotBlank(patientId))
											dgOrderhd.setPatientId(Long.parseLong(patientId));
										dgOrderhd.setOtherInvestigation(otherInvestigation);
										
										try { 
											dgOrderhd.setOrderStatus("y");
											//dgOrderhd.setOrderDate((Timestamp) new Date());
										}
										catch(Exception e) {e.printStackTrace();}
										
										dgOrderHdId = dgOrderhdDao.saveOrUpdateDgOrderHd(dgOrderhd);
									}
									dgOrderdt.setOrderhdId(dgOrderHdId);

								}

								if (StringUtils.isNotEmpty(finalValueInvestigation[0]) && !finalValueInvestigation[0].equals("0"))
									dgOrderdt.setInvestigationId(Long.parseLong(finalValueInvestigation[0].toString()));

								if (StringUtils.isNotEmpty(finalValueInvestigation[2])
										&& !finalValueInvestigation[2].equals("0")) {
									try {
										dgOrderdt.setOrderDate(
												HMSUtil.dateFormatteryyyymmdd(finalValueInvestigation[2].toString()));
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								if (finalValueInvestigation[3] != null &&  StringUtils.isNotBlank(finalValueInvestigation[3]) && !finalValueInvestigation[3].equals("0"))
									dgOrderdt.setLabMark(finalValueInvestigation[3].toString());
								else {
									dgOrderdt.setLabMark("O");
								}

								if (finalValueInvestigation[4] != null && StringUtils.isNotBlank(finalValueInvestigation[4]) && !finalValueInvestigation[4].equals("0"))
									dgOrderdt.setUrgent(finalValueInvestigation[4].toString());
								else {
									dgOrderdt.setUrgent("N");
								}

							}

							dgOrderhdDao.saveOrUpdateDgOrderdt(dgOrderdt);
						}
					}
					counter++;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Save opdate MassStoreItems

	public void saveOrUpDatedMassStoreItems(String items, String dispUnit, String dosages, String frequencys,
			String days, String totals, String instructions, String stocks, String precriptionDtId,
			String precriptionHdId, HashMap<String, Object> payload) {
		try {
			String[] itemsValue = items.split(",");
			String[] dispUnitValue = dispUnit.split(",");
			String[] dosagesValue = dosages.split(",");
			String[] frequencysValue = frequencys.split(",");

			String[] daysValue = days.split(",");
			String[] totalsValue = totals.split(",");

			String[] instructionsValue = instructions.split(",");
			String[] stocksValue = stocks.split(",");
			String[] precriptionHdIdValue = precriptionHdId.split(",");

			String[] precriptionDtIdValue = precriptionDtId.split(",");
			String recommendedMedicalAdvice = payload.get("recommendedMedicalAdvice").toString();
			recommendedMedicalAdvice = getReplaceString(recommendedMedicalAdvice);
			
			HashMap<String, String> mapInvestigationMap = new HashMap<>();
			
			Integer counter = 1;
			String finalValue = "";
			String visitId="";
			for (int i = 0; i < itemsValue.length; i++) {

				if (StringUtils.isNotEmpty(itemsValue[i].toString()) && !itemsValue[i].equalsIgnoreCase("0")) {

					finalValue += itemsValue[i].trim();

					if (!precriptionDtIdValue[i].equalsIgnoreCase("0")
							&& StringUtils.isNotBlank(precriptionDtIdValue[i])) {
						for (int m = i; m < precriptionDtIdValue.length; m++) {
							finalValue += "," + precriptionDtIdValue[m].trim();
							if (m == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}

					if (i < dispUnitValue.length && !dispUnitValue[i].equalsIgnoreCase("0")
							&& StringUtils.isNotBlank(dispUnitValue[i])) {
						for (int j = i; j < dispUnitValue.length; j++) {
							finalValue += "," + dispUnitValue[j].trim();
							if (j == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}

					if (i < dosagesValue.length && StringUtils.isNotBlank(dosagesValue[i])) {
						for (int k = i; k < dosagesValue.length; k++) {
							finalValue += "," + dosagesValue[k].trim();
							if (k == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}
					if (i < frequencysValue.length && StringUtils.isNotBlank(frequencysValue[i])) {
						for (int l = i; l < frequencysValue.length; l++) {
							finalValue += "," + frequencysValue[l].trim();
							if (l == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}

					if (i < daysValue.length && StringUtils.isNotBlank(daysValue[i])) {
						for (int l = i; l < daysValue.length; l++) {
							finalValue += "," + daysValue[l].trim();
							if (l == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}

					if (i < totalsValue.length && StringUtils.isNotBlank(totalsValue[i])) {
						for (int l = i; l < totalsValue.length; l++) {
							finalValue += "," + totalsValue[l].trim();
							if (l == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}

					if (i < instructionsValue.length && StringUtils.isNotBlank(instructionsValue[i])) {
						for (int l = i; l < instructionsValue.length; l++) {
							finalValue += "," + instructionsValue[l].trim();
							if (l == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}

					if (i < stocksValue.length && StringUtils.isNotBlank(stocksValue[i])) {
						for (int l = i; l < stocksValue.length; l++) {
							finalValue += "," + stocksValue[l].trim();
							if (l == i) {
								break;
							}
						}
					} else {
						finalValue += "," + "0";
					}

					mapInvestigationMap.put(itemsValue[i].trim() +"@#"+ counter, finalValue);
					finalValue = "";
					counter++;
				}
			}
			counter = 1;
			PatientPrescriptionHd patientPrescriptionHd =null;
			for (String item : itemsValue) {
				if (StringUtils.isNotEmpty(item)) {
					if (mapInvestigationMap.containsKey(item.trim() + "@#" + counter)) {
						String itemValue = mapInvestigationMap.get(item.trim() + "@#" + counter);

						if (StringUtils.isNotEmpty(itemValue)) {

							String[] finalValueItem = itemValue.split(",");
							PatientPrescriptionDt patientPrescriptionDt = null;
							if (finalValueItem[1] != null && !finalValueItem[1].equalsIgnoreCase("0")
									&& StringUtils.isNotBlank(finalValueItem[1])) {
								patientPrescriptionDt = dgOrderhdDao
										.getMasStoreItemByPatientPrecriptionDtId(Long.parseLong(finalValueItem[1]));
								if(patientPrescriptionDt==null) {
									patientPrescriptionDt = new PatientPrescriptionDt();
								}

							} else {
								patientPrescriptionDt = new PatientPrescriptionDt();
							}

							if (finalValueItem != null) {
								if (finalValueItem[0] != null && StringUtils.isNotEmpty(finalValueItem[0]) && !finalValueItem[0].equals("0"))
									patientPrescriptionDt
											.setItemId(Long.parseLong(finalValueItem[0].trim().toString()));

								if (finalValueItem[2]!=null && StringUtils.isNotEmpty(finalValueItem[2]) && !finalValueItem[2].equals("0")) {
									patientPrescriptionDt.setDispStock(finalValueItem[2]);
								}

								if (finalValueItem[3] != null && StringUtils.isNotEmpty(finalValueItem[3]) && !finalValueItem[3].equals("0"))
									patientPrescriptionDt.setDosage(finalValueItem[3]);

								if (finalValueItem[4] != null && StringUtils.isNotEmpty(finalValueItem[4]) && !finalValueItem[4].equals("0")) {
									patientPrescriptionDt.setFrequencyId(Long.parseLong(finalValueItem[4]));
								}

								if (finalValueItem[5] != null && StringUtils.isNotEmpty(finalValueItem[5]) && !finalValueItem[5].equals("0")) {
									patientPrescriptionDt.setNoOfDays(Long.parseLong(finalValueItem[5]));
								}

								if (finalValueItem[6] != null && StringUtils.isNotEmpty(finalValueItem[6]) && !finalValueItem[6].equals("0")&& !finalValueItem[6].equals("NaN")) {
									patientPrescriptionDt.setTotal(Long.parseLong(finalValueItem[6]));
								}
								if (finalValueItem[7] != null && StringUtils.isNotEmpty(finalValueItem[7])  && !finalValueItem[7].equals("0")) {
									try {
									patientPrescriptionDt.setInstruction(finalValueItem[7]);
									}
									catch(Exception e) {
										e.printStackTrace();
									}
								}
								if (finalValueItem[8] != null && StringUtils.isNotEmpty(finalValueItem[8]) && !finalValueItem[8].equals("0")) {
									patientPrescriptionDt.setStoreStock(finalValueItem[8]);
								}

								if (precriptionHdIdValue[0] != null
										&& StringUtils.isNotBlank(precriptionHdIdValue[0])) {
									patientPrescriptionDt.setPrescriptionHdId(
											Long.parseLong(precriptionHdIdValue[0].trim().toString()));
									patientPrescriptionHd=dgOrderhdDao.getPatientPrecriptionHdByPPHdId(Long.parseLong(precriptionHdIdValue[0].trim().toString()));
									patientPrescriptionHd.setOtherTreatment(recommendedMedicalAdvice);
									  dgOrderhdDao
												.saveOrUpdatePatientPrescriptionHd(patientPrescriptionHd);
								} else {
									visitId = payload.get("VisitID").toString();
									visitId = getReplaceString(visitId);
									
									
									patientPrescriptionHd=dgOrderhdDao.getPatientPrecriptionHdByVisitId(Long.parseLong(visitId.trim()));
									Long patientPrescriptionHdId=null;
									if(patientPrescriptionHd!=null) {
										patientPrescriptionHdId=patientPrescriptionHd.getPrescriptionHdId();
										patientPrescriptionHd.setOtherTreatment(recommendedMedicalAdvice);
									}
									else {
										patientPrescriptionHd = new PatientPrescriptionHd();
										String hospitalId ="";
										String patientId="";
										try {
									  hospitalId = payload.get("hospitalId").toString();
									hospitalId = getReplaceString(hospitalId);

									  patientId = payload.get("patientId").toString();
									patientId = getReplaceString(patientId);
										}
										catch(Exception e) {
											e.printStackTrace();
										}
									patientPrescriptionHd.setVisitId(Long.parseLong(visitId.trim()));
									patientPrescriptionHd.setOtherTreatment(recommendedMedicalAdvice);
									
									try {
										String opdPatientDetaiId = payload.get("opdPatientDetailId").toString();
										opdPatientDetaiId = getReplaceString(opdPatientDetaiId);
										patientPrescriptionHd.setOpdPatientDetailsId(Long.parseLong(opdPatientDetaiId));
										Date date=new Date();
										patientPrescriptionHd.setPrescriptionDate(new Timestamp(date.getTime()));
									}
									catch(Exception e) {
										e.printStackTrace();
									}
									patientPrescriptionHd.setStatus("y");
									patientPrescriptionHd.setInjectionStatus("N");
									
									
									if (StringUtils.isNotBlank(hospitalId))
										patientPrescriptionHd.setHospitalId(Long.parseLong(hospitalId.trim()));
									if (StringUtils.isNotBlank(patientId))
										patientPrescriptionHd.setPatientId(Long.parseLong(patientId));
									}
									  patientPrescriptionHdId = dgOrderhdDao
											.saveOrUpdatePatientPrescriptionHd(patientPrescriptionHd);
									patientPrescriptionDt.setPrescriptionHdId(patientPrescriptionHdId);
								}
								dgOrderhdDao.saveOrUpdatePatientPrecriptionDt(patientPrescriptionDt);
							}
							
						}
					}
				}
				counter++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveUpdateOpdPatientDetail(HashMap<String, Object> obj) {

		String opdPatientDetailId = obj.get("opdPatientDetailId").toString();
		opdPatientDetailId = getReplaceString(opdPatientDetailId);

		Long opdPatientDetailIdValue = Long.parseLong(opdPatientDetailId.trim());

		OpdPatientDetail opdPatientDetail = opdPatientDetailDao
				.getOpdPatientDetailsByOpdPatientDetailId(opdPatientDetailIdValue);
		if(obj.get("Chest")!=null) {
		String chest = obj.get("Chest").toString();
		chest = getReplaceString(chest);
		opdPatientDetail.setChestResp(chest);
		}
		if(obj.get("Address")!=null) {
		String address = obj.get("Address").toString();
		address = getReplaceString(address);
		}
		Long patientIdValue=null;
		if(obj.get("patientId")!=null) {
		String patientId = obj.get("patientId").toString();
		patientId = getReplaceString(patientId);
		  patientIdValue = Long.parseLong(patientId.trim());
		opdPatientDetail.setPatientId(patientIdValue);
		Patient patient = opdPatientDetailDao.getPatientByPatientId(patientIdValue);
		}
		//Not save
		/*if (patient != null) {

			patient.setAddress(address);
			String patientName = obj.get("patients_name").toString();
			patientName = getReplaceString(patientName);
			patient.setPatientName(patientName);

			String emailId = obj.get("email").toString();
			emailId = getReplaceString(emailId);
			patient.setEmailId(emailId);

			String mobileNumber = obj.get("Mobile No").toString();
			mobileNumber = getReplaceString(mobileNumber);
			patient.setMobileNumber(mobileNumber);

			String serviceNo = obj.get("service_no.").toString();
			serviceNo = getReplaceString(serviceNo);
			patient.setServiceNo(serviceNo);

			String dateOfBirth = obj.get("DOB").toString();
			dateOfBirth = getReplaceString(dateOfBirth);
			if (StringUtils.isNotBlank(dateOfBirth)) {
				Date dateOfBirthValue = null;
				try {
					dateOfBirthValue = HMSUtil.dateFormatteryyyymmdd(dateOfBirth);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				patient.setDateOfBirth(dateOfBirthValue);
			}
			String marritalStatus = obj.get("Marrital Status").toString();
			marritalStatus = getReplaceString(marritalStatus);
			// Long maritalStatusId=Long.parseLong(obj.get("maritalStatusId").toString());
			marritalStatus = getReplaceString(marritalStatus);
			MasMaritalStatus masMaritalStatus = opdPatientDetailDao.getMasMaritalStatusByMaritalId(null,
					marritalStatus);
			patient.setMaritalStatusId(masMaritalStatus.getMaritalStatusId());
			if (masMaritalStatus != null) {
				masMaritalStatus.setMaritalStatusName(marritalStatus);

			}

			String city = obj.get("City").toString();
			city = getReplaceString(city);
			patient.setCity(city);

			String stateName = getReplaceString(obj.get("State").toString());
			MasState masState = opdPatientDetailDao.getMasStateByStateName(stateName);
			// Long stateId=Long.parseLong(obj.get("stateId").toString());
			if (masState != null && masState.getStateId() != 0)
				patient.setStateId(masState.getStateId());

			String pinCodeValue = getReplaceString(obj.get("Pin Code").toString());
			Long pinCode = Long.parseLong(pinCodeValue);
			patient.setPincode(pinCode);

			String gender = obj.get("Gender").toString();
			gender = getReplaceString(gender);
			MasAdministrativeSex masAdministrativeSex = opdPatientDetailDao.getMasAdministrativeSexByGender(gender);
			patient.setMasAdministrativeSex(masAdministrativeSex);

			String tradeName = getReplaceString(obj.get("Trade/Branch").toString());
			MasTrade masTrade = opdPatientDetailDao.getMasTradeByTradeName(tradeName);
			if (masTrade != null && masTrade.getTradeId() != 0)
				patient.setTradeId(masTrade.getTradeId());

			String medicalCategories = obj.get("Medical Categories").toString();
			medicalCategories = getReplaceString(medicalCategories);

			MasMedicalCategory masMedicalCategory = opdPatientDetailDao
					.getMasMedicalCategoryByCategory(medicalCategories);

			patient.setMasMedicalCategory(masMedicalCategory);

			String religion = obj.get("Religion").toString();
			religion = getReplaceString(religion);
			MasReligion masReligion = opdPatientDetailDao.getMasReligionByReligion(religion.trim());
			patient.setReligionId(masReligion.getReligionId());

			String unitName = obj.get("Unit").toString();
			unitName = getReplaceString(unitName);
			MasUnit masUnit = opdPatientDetailDao.getMasUnitByUnitId(unitName);
			if(masUnit!=null && masUnit.getUnitId()!=0)
			patient.setUnitId(masUnit.getUnitId());

			String durationValue = getReplaceString(obj.get("Duration").toString());
			if(StringUtils.isNotEmpty(durationValue)) {
				Long Duration = Long.parseLong(durationValue);
			patient.setDuration(Duration);
			}
			updatePatient(patient);
		}*/

		/*
		 * if(obj.get("departmentId")!=null) { String
		 * departMentValue=getReplaceString(obj.get("departmentId").toString());
		 * String[] departMentValues = departMentValue.split(","); for(String
		 * depart:departMentValues) { if(depart!=null && StringUtils.isNotBlank(depart))
		 * { Long departmentId= Long.parseLong(depart);
		 * opdPatientDetail.setDepartmentId(departmentId); } } }
		 */
		
		if(obj.get("Allergy")!=null) {
		String allergy = obj.get("Allergy").toString();
		allergy = getReplaceString(allergy);
		}
		// Not implemented the Bean of AlleryDetail
		// AllergyDetail
		// allergyDetail=opdPatientDetailDao.getAllergyByAllergyName(allergy.trim());

		// Working this value not getting
		// String allergyIdValue=getReplaceString(obj.get("allergyId").toString());
		// Long allergyId=Long.parseLong(allergyIdValue.trim());

		// opdPatientDetail.setAllergyId(allergyId);
		if(obj.get("Ordema")!=null) {
		String edema = obj.get("Ordema").toString();
		edema = getReplaceString(edema);
		opdPatientDetail.setEdema(edema.trim());
		}
		if(obj.get("height")!=null) {
		String height = obj.get("height").toString();
		height = getReplaceString(height.trim());
		opdPatientDetail.setHeight(height);
		}
		if(obj.get("Thyroid")!=null) {
		String thyroid = obj.get("Thyroid").toString();
		thyroid = getReplaceString(thyroid.trim());
		opdPatientDetail.setThyroid(thyroid);
		}
		if(obj.get("cyanosis")!=null) {
		String cyanosis = obj.get("cyanosis").toString();
		cyanosis = getReplaceString(cyanosis.trim());
		opdPatientDetail.setCyanosis(cyanosis);
		}
		// String total1=obj.get("total1").toString();

		// String noOfDays1=obj.get("noOfDays1").toString();

		// String doa=obj.get("doa").toString();
		if(obj.get("patientId")!=null) {
		String PatientIDValue = getReplaceString(obj.get("patientId").toString());
		if(StringUtils.isNotBlank(PatientIDValue)) {
		Long PatientID = Long.parseLong(PatientIDValue.trim());
		opdPatientDetail.setPatientId(PatientID);
		}
		}
		// String dod=obj.get("dod").toString();
		// String remarks1=obj.get("remarks1").toString();
		if(obj.get("rr")!=null) {
		String rr = obj.get("rr").toString();
		rr = getReplaceString(rr.trim());
		opdPatientDetail.setRr(rr);
		}
		
		if(obj.get("CVS")!=null) {
		String cvs = obj.get("CVS").toString();
		cvs = getReplaceString(cvs.trim());
		opdPatientDetail.setCvs(cvs);
		}
		
		if(obj.get("CNS")!=null) {
		String cns = obj.get("CNS").toString();
		cns = getReplaceString(cns.trim());
		opdPatientDetail.setCns(cns);
		
		}
		if(obj.get("bp")!=null) {
		String bp = obj.get("bp").toString();
		bp = getReplaceString(bp.trim());
		opdPatientDetail.setBpDiastolic(bp);
		}
		if(obj.get("bp1")!=null) {
			String bp1 = obj.get("bp1").toString();
			bp1 = getReplaceString(bp1.trim());
			opdPatientDetail.setBpSystolic(bp1);
			}
			// String labradiologyCheck1=obj.get("labradiologyCheck1").toString();
		OpdPatientHistory opdPatientHistory=null;
		String visitID ="";
		if(obj.get("VisitID")!=null) {
		  visitID = getReplaceString(obj.get("VisitID").toString());
		// OpdPatient History
		  opdPatientHistory = opdPatientDetailDao
				.getOpdPatientHistoryByVisitId(Long.parseLong(visitID));
		}
		String allergyHistory=""; 
		
		if(obj.get("allergyHistory")!=null  ) {
		allergyHistory= obj.get("allergyHistory").toString();
		allergyHistory = getReplaceString(allergyHistory.trim());
		if(StringUtils.isNotEmpty(allergyHistory))
			opdPatientHistory.setAllergyHistory(allergyHistory);
		}
		
		if(obj.get("familyHistory")!=null ) {
		String familyHistory = obj.get("familyHistory").toString();
		familyHistory = getReplaceString(familyHistory.trim());
		if( StringUtils.isNotEmpty(familyHistory))
			opdPatientHistory.setFamilyHistory(familyHistory);
		}
		if(obj.get("implantHistory")!=null ) {
		String implantHistory = obj.get("implantHistory").toString();
		implantHistory = getReplaceString(implantHistory.trim());
		if( StringUtils.isNotEmpty(implantHistory))
		opdPatientHistory.setImplantHistory(implantHistory);
		}
		if(obj.get("historyPresentIllness")!=null) {
		String presentIllnessHistory = obj.get("historyPresentIllness").toString();
		presentIllnessHistory = getReplaceString(presentIllnessHistory.trim());
		if( StringUtils.isNotEmpty(presentIllnessHistory))
			opdPatientHistory.setPresentIllnessHistory(presentIllnessHistory);
		}
		if(obj.get("socialHistory")!=null) {
		String socialHistory = obj.get("socialHistory").toString();
		socialHistory = getReplaceString(socialHistory.trim());
		if( StringUtils.isNotEmpty(socialHistory))
			opdPatientHistory.setSocialHistory(socialHistory);
		}
		if(obj.get("surgicalHistory")!=null  ) {
		String surgicalHistory = obj.get("surgicalHistory").toString();
		surgicalHistory = getReplaceString(surgicalHistory.trim());
		if( StringUtils.isNotEmpty(surgicalHistory))
		opdPatientHistory.setPastSurgicalHistory(surgicalHistory);
		}
		
		if(obj.get("medicationHistory")!=null ) {
		String medicationHistory = obj.get("medicationHistory").toString();
		medicationHistory = getReplaceString(medicationHistory.trim());
		if( StringUtils.isNotEmpty(medicationHistory))
		opdPatientHistory.setMedicationHistory(medicationHistory);
		}
		if(obj.get("personalHistory")!=null   ) {
		String personalHistory = obj.get("personalHistory").toString();
		personalHistory = getReplaceString(personalHistory.trim());
		if( StringUtils.isNotEmpty(personalHistory))
		opdPatientHistory.setPersonalHistory(personalHistory);
		}
		if(obj.get("chiefCompliant")!=null ) {
		String chiefComplain = obj.get("chiefCompliant").toString();
		chiefComplain = getReplaceString(chiefComplain.trim());
		if( StringUtils.isNotEmpty(chiefComplain))
		opdPatientHistory.setChiefComplain(chiefComplain);
		}
		
		if(obj.get("pastMedicalHistory")!=null) {
		String pastMedicalHistory = obj.get("pastMedicalHistory").toString();
		pastMedicalHistory = getReplaceString(pastMedicalHistory.trim());
		if( StringUtils.isNotEmpty(pastMedicalHistory))
		opdPatientHistory.setPastMedicalHistory(pastMedicalHistory);
		}
		updatePatientHistory(opdPatientHistory);
		
		// END Patient history

		//Working Obesity Mark when not check  Pending
		/*if(obj.get("obsitymarkvalue")!=null) {
		String obesityMark = obj.get("obsitymarkvalue").toString();
			obesityMark = getReplaceString(obesityMark.trim());
			if(StringUtils.isNotBlank(obesityMark) && obesityMark.equals("0")) {
				Map<String,Object> mapObject	=dgOrderhdDao.getOpdObesityHd(Long.parseLong(visitID));
				if(MapUtils.isNotEmpty(mapObject)) {
					List<OpdObesityDt>listOpdObesityDt=(List<OpdObesityDt>) mapObject.get("listOpdObesityDt");
					
					OpdObesityHd opdObesityHd=(OpdObesityHd) mapObject.get("opdObesityHd");
					String status=dgOrderhdDao.deleteObesityMark(opdObesityHd,listOpdObesityDt);
				}
			}
			
		}*/
		
		
		// String labradiologyCheck=obj.get("labradiologyCheck").toString();

		String clubbing = obj.get("Clubbing").toString();
		clubbing = getReplaceString(clubbing.trim());
		opdPatientDetail.setClubbing(clubbing);

		//Check for update
		/*String empname = obj.get("empname").toString();
		clubbing = getReplaceString(empname.trim());
		MasEmployee masEmployee = opdPatientDetailDao.getMasEmployeeByFirstName(empname.trim());
		if (masEmployee != null && masEmployee.getEmployeeId() != 0)
			opdPatientDetail.setEmployeeId(masEmployee.getEmployeeId());*/
			
		/*String hinId = obj.get("hinId").toString();

		String Total_Service = obj.get("Total Service").toString();

		String regioncommand = obj.get("regioncommand").toString();

		String itemIdNom = obj.get("itemIdNom").toString();
		String investigationDate = obj.get("investigationDate").toString();*/
	try {
		String bmi = obj.get("bmi").toString();
		bmi = getReplaceString(bmi.trim());
		opdPatientDetail.setBmi(bmi);

		//String dosage1 = obj.get("dosage1").toString();
		
		String pollor = obj.get("Pollar").toString();
		pollor = getReplaceString(pollor.trim());
		opdPatientDetail.setPollor(pollor);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
		 

		String genitoUrinary = obj.get("geneticurinary").toString();
		if (StringUtils.isNotBlank(genitoUrinary)) {
			genitoUrinary = getReplaceString(genitoUrinary.trim());
			opdPatientDetail.setGenitoUrinary(genitoUrinary);
		}
		if(obj.get("snomeddiagnosis")!=null) {
			String snomeddiagnosis = obj.get("snomeddiagnosis").toString();
			snomeddiagnosis = getReplaceString(snomeddiagnosis.trim());
			opdPatientDetail.setSnomedDiagnosis(snomeddiagnosis);
		}
		// String urgent1=obj.get("urgent1").toString();
		String temperature = obj.get("tempature").toString();
		temperature = getReplaceString(temperature.trim());
		opdPatientDetail.setTemperature(temperature);

		/*String pvmsNo1 = obj.get("pvmsNo1").toString();
		String pastDiagnosis = obj.get("pastDiagnosis").toString();
		String hospName = obj.get("hospName").toString();
		String recordoffice = obj.get("recordoffice").toString();
		String investigationIdValue = obj.get("investigationIdValue").toString();*/

		String workingdiagnosis = obj.get("workingdiagnosis").toString();
		workingdiagnosis = getReplaceString(workingdiagnosis.trim());
		opdPatientDetail.setWorkingDiagnosis(workingdiagnosis);

		String hairNail = obj.get("hairNail").toString();
		hairNail = getReplaceString(hairNail.trim());
		opdPatientDetail.setHairNail(hairNail);
/*
		String adviceOnDischarge = obj.get("adviceOnDischarge").toString();
		String weight = obj.get("Weight").toString();
		String variant_in_weight = obj.get("variant_in_weight").toString();

		String investigationTemplateId = obj.get("investigationTemplateId").toString();*/
		if(obj.get("variant_in_weight")!=null  ) {
		String variant_in_weight = obj.get("variant_in_weight").toString();
		variant_in_weight = getReplaceString(variant_in_weight.trim());
		if(StringUtils.isNotBlank(variant_in_weight))
		opdPatientDetail.setVaration(new BigDecimal(variant_in_weight));
		}
		String skin = obj.get("Skin").toString();
		skin = getReplaceString(skin.trim());
		opdPatientDetail.setSkin(skin);

		String tremors = obj.get("Tremors").toString();
		tremors = getReplaceString(tremors.trim());
		opdPatientDetail.setTremors(tremors);

		// String dispensingUnit1 = obj.get("dispensingUnit1").toString();
		/*String frequency1 = obj.get("frequencyTre").toString();
		String hiddenValue = obj.get("hiddenValue").toString();
		String musculoskeletal = obj.get("Musculoskeletal").toString();
		String hospitalId = obj.get("hospitalId").toString();
		String age = obj.get("Age").toString();
		*/
		if( obj.get("Jaundice")!=null) {
			String jaundice = obj.get("Jaundice").toString();
			jaundice = getReplaceString(jaundice.trim());
			opdPatientDetail.setJauindice(jaundice);
		}
		String OthersForSystem = obj.get("OthersForSystem").toString();// multiple value
		OthersForSystem = getReplaceString(OthersForSystem.trim());
		opdPatientDetail.setSystemOther(OthersForSystem);

		String others = obj.get("Others").toString();// multiple value
		others = getReplaceString(others.trim());
		opdPatientDetail.setGeneralOther(others);

		String Musculoskeletal = obj.get("Musculoskeletal").toString();
		Musculoskeletal = getReplaceString(Musculoskeletal.trim());
		opdPatientDetail.setMusculoskeletal(Musculoskeletal);

		String gi = obj.get("GI").toString();
		gi = getReplaceString(gi);
		opdPatientDetail.setGi(gi);

		String lymphNode = obj.get("Lymph node").toString();
		lymphNode = getReplaceString(lymphNode.trim());
		opdPatientDetail.setLymphNode(lymphNode);
		
		//Working on ICgDiagonosis
		
		if(obj.get("diagonsisText")!=null) {
		String icdDiagnosis = obj.get("diagonsisText").toString();
			icdDiagnosis = getReplaceStringOnlyLastAndFirst(icdDiagnosis.trim());
			opdPatientDetail.setIcdDiagnosis(icdDiagnosis);
		}
		
		//Dischage IcdUpdate	
		if(obj.get("icdIdValue")!=null) {
			try {
			String icdIdValue = obj.get("icdIdValue").toString();
			icdIdValue = getReplaceString(icdIdValue.trim());
				//opdPatientDetail.setIcdDiagnosis(icdDiagnosis);
			String [] icdCodeArray=icdIdValue.split(",");
				if(icdIdValue!="" && StringUtils.isNotEmpty(visitID) &&patientIdValue!=null &&  opdPatientDetailIdValue!=null)
				dgOrderhdDao.updateAndInsertDischargeICDCode(icdCodeArray,Long.parseLong(visitID),patientIdValue,opdPatientDetailIdValue);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			}
		
		//String nomenclature1 = obj.get("nomenclature1").toString();

		String spo2 = obj.get("spo2").toString();
		spo2 = getReplaceString(spo2.trim());
		opdPatientDetail.setSpo2(spo2);
		if(obj.get("pulse")!=null) {	
		String pulse = obj.get("pulse").toString();// multiple value
			pulse = getReplaceString(pulse.trim());
			opdPatientDetail.setPulse(pulse);
		}
		try {
		String idealWeight = obj.get("ideal_weight").toString();
		idealWeight = getReplaceString(idealWeight.trim());
		opdPatientDetail.setIdealWeight(idealWeight);
		if(obj.get("Weight")!=null) {
		String Weight = obj.get("Weight").toString();
		Weight = getReplaceString(Weight.trim());
		opdPatientDetail.setWeight(Weight);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(obj.get("disposalDays")!=null) {
		String disposalDays = obj.get("disposalDays").toString();
		disposalDays = getReplaceString(disposalDays.trim());
		opdPatientDetail.setDisposalDays(disposalDays);
		}
		if(obj.get("disposalId")!=null) {
		String disposalId = obj.get("disposalId").toString();
		disposalId = getReplaceString(disposalId.trim());
			if(StringUtils.isNotEmpty(disposalId))
			opdPatientDetail.setDisposal1Id(Long.parseLong(disposalId));
		}
		
		updateOpdPatientDetail(opdPatientDetail);
	}

	public Long updatePatient(Patient patient) {
		return dgOrderhdDao.saveOrUpdatePatient(patient);
	}

	public Long updatePatientHistory(OpdPatientHistory opdPatientHistory) {
		return dgOrderhdDao.updatePatientHistory(opdPatientHistory);
	}

	public Long updateOpdPatientDetail(OpdPatientDetail opdPatientDetail) {
		return dgOrderhdDao.updateOpdPatientDetail(opdPatientDetail);
	}

	@Override
	public String getPatientHistoryDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
		if (!jsondata.isEmpty()) {

			JSONObject nullbalankvalidation = null;
			nullbalankvalidation = ValidateUtils.checkPatientVisit(jsondata);
			if (nullbalankvalidation.optString("status").equals("0")) {
				return nullbalankvalidation.toString();
			} else {
				List<OpdPatientHistory> listOpdPatientHistory = null;
				if (jsondata.get("visitId") != null) {
					listOpdPatientHistory = dgOrderhdDao
							.getPatientHistoryList(Long.parseLong(jsondata.get("visitId").toString()));
				}
				if (listOpdPatientHistory != null) {
					try {
						for (OpdPatientHistory opdPatientHistory : listOpdPatientHistory) {

							HashMap<String, Object> pt = new HashMap<String, Object>();

							pt.put("chiefComplain", opdPatientHistory.getChiefComplain());
							pt.put("pastMedicalHistory", opdPatientHistory.getPastMedicalHistory());
							pt.put("presentIllnessHistory", opdPatientHistory.getPresentIllnessHistory());
							pt.put("medicationHistory", opdPatientHistory.getMedicationHistory());
							pt.put("surgicalHistory", opdPatientHistory.getPastSurgicalHistory());
							pt.put("personalHistory", opdPatientHistory.getPersonalHistory());
							pt.put("socialHistory", opdPatientHistory.getSocialHistory());
							pt.put("familyHistory", opdPatientHistory.getFamilyHistory());
							pt.put("allergyHistory", opdPatientHistory.getAllergyHistory());
							pt.put("implantHistory", opdPatientHistory.getImplantHistory());
							c.add(pt);
						}

						json.put("listOpdPatientHistory", c);
						json.put("visitId", jsondata.get("visitId"));
						json.put("msg", "OPD Patients Visit List  get  sucessfull... ");
						json.put("status", "1");

					}

					catch (Exception e) {
						e.printStackTrace();
						return "{\"status\":\"0\",\"msg\":\"Somting went wrong}";
					}
				} else {
					try {
						json.put("msg", "Visit ID data not found");
						json.put("status", 0);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return json.toString();

	}

	@Override
	public String getPatientReferalDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
		List<MasEmpanelledHospital> masEmpanelledHospitalList = null;
		List<MasIcd>listMasIcd=null;
		Long visitId=null;
		Long patientId=null;
		Long opdPatientDetailId=null;
		if (!jsondata.isEmpty()) {
			List<Object[]> listReferralPatientDt = null;
			if (jsondata.get("opdPatientDetailId") != null) {
				listReferralPatientDt = dgOrderhdDao
						.getReferralPatientDtList(Long.parseLong(jsondata.get("opdPatientDetailId").toString()));

				masEmpanelledHospitalList = md.getEmpanelledHospital();
				try {
				if(jsondata.get("visitId")!=null) {
					visitId=Long.parseLong(jsondata.get("visitId").toString());
				}
				if(jsondata.get("opdPatientDetailId")!=null) {
					opdPatientDetailId=Long.parseLong(jsondata.get("opdPatientDetailId").toString());
				}
				
				if(jsondata.get("patientId")!=null) {
					patientId=Long.parseLong(jsondata.get("patientId").toString());
				}
				 listMasIcd= dgOrderhdDao.getMasIcdByVisitPatAndOpdPD(visitId,patientId,opdPatientDetailId);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			if (listReferralPatientDt != null) {
				try {
					Long masEmpanalId = null;
					String masEmpanalName = "";
					Long masDepatId = null;
					String massDeptName = "";
					Long diagonisId = null;
					String daiganosisName = "";
					String instruction = "";
					Long referalPatientDt = null;
					Long referalPatientHd = null;
					String exDepartmentValue = "";
					String masCode="";
					String referalNotes="";
					String referalDate="";
					for (Iterator<?> it = listReferralPatientDt.iterator(); it.hasNext();) {
						Object[] row = (Object[]) it.next();

						HashMap<String, Object> pt = new HashMap<String, Object>();

						if (row[0] != null) {
							masEmpanalId = Long.parseLong(row[0].toString());
						}
						if (row[1] != null) {
							masEmpanalName = row[1].toString();
						}
						if (row[2] != null) {
							masDepatId = Long.parseLong(row[2].toString());
						}
						if (row[3] != null) {
							massDeptName = row[3].toString();
						}

						 
						  if (row[4] != null) { diagonisId = Long.parseLong(row[4].toString()); }
						  
						if (row[5] != null) {
							daiganosisName = row[5].toString();
						}
						if (row[6] != null) {
							instruction = row[6].toString();
						}
						if (row[7] != null) {
							referalPatientDt = Long.parseLong(row[7].toString());
						}
						if (row[8] != null) {
							referalPatientHd = Long.parseLong(row[8].toString());
						}

						if (row[9] != null) {
							exDepartmentValue = row[9].toString();
						}
						if (row[10] != null) {
							masCode = row[10].toString();
						}
						if (row[11] != null) {
							referalNotes = row[11].toString();
						}
						if (row[12] != null) {
							referalDate = row[12].toString();
							Date dd1 = HMSUtil.dateFormatteryyyymmdd(referalDate);
							referalDate = HMSUtil.getDateWithoutTime(dd1);
							
							
						}
						pt.put("masEmpanalId", masEmpanalId);
						pt.put("masEmpanalName", masEmpanalName);
						pt.put("masDepatId", masDepatId);
						pt.put("massDeptName", massDeptName);
						pt.put("diagonisId", diagonisId);
						pt.put("daiganosisName", daiganosisName);
						pt.put("instruction", instruction);

						pt.put("referalPatientDt", referalPatientDt);
						pt.put("referalPatientHd", referalPatientHd);
						pt.put("exDepartmentValue", exDepartmentValue);
						pt.put("masCode", masCode);
						pt.put("referalNotes", referalNotes);
						pt.put("referalDate", referalDate);
						c.add(pt);
					}
					json.put("listReferralPatientDt", c);
					json.put("masEmpanelledHospitalList", masEmpanelledHospitalList);
					json.put("listMasIcd", listMasIcd);
					json.put("msg", "OPD Patients Visit List  get  sucessfull... ");
					json.put("status", "1");

				}

				catch (Exception e) {
					e.printStackTrace();
					return "{\"status\":\"0\",\"msg\":\"Somting went wrong}";
				}
			} else {
				try {
					json.put("msg", "Visit ID data not found");
					json.put("status", 0);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		return json.toString();

	}

	// Delete for common functyion like INVESTIGATION,TREATMENT,REFERAL
	@Override
	public String deleteGridRow(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		String flag = "";
		String status = "";
		if (!jsondata.get("flag").equalsIgnoreCase("0")) {
			flag = jsondata.get("flag");
		}
		if (jsondata.get("valueForDelete") != null && flag.equalsIgnoreCase("1")) {
			Long dgOrderDt = Long.parseLong(jsondata.get("valueForDelete").toString());
			if (dgOrderDt != null) {
				status = dgOrderhdDao.deleteInvestigatRow(dgOrderDt, flag);

			}
		}
		if (jsondata.get("valueForDelete") != null && flag.equalsIgnoreCase("2")) {
			Long patientPrecriptionDt = Long.parseLong(jsondata.get("valueForDelete").toString());
			if (patientPrecriptionDt != null) {
				status = dgOrderhdDao.deleteInvestigatRow(patientPrecriptionDt, flag);
			}
		}
		if (jsondata.get("valueForDelete") != null && flag.equalsIgnoreCase("3")) {
			String referPatientDtOrDiagnosis=jsondata.get("valueForDelete").toString(); 
			String[] refrerandDisChargeValue=null;
			if(StringUtils.isNotBlank(referPatientDtOrDiagnosis)) {
				  refrerandDisChargeValue=referPatientDtOrDiagnosis.split("&&&");
			}
			//Delete DisChanrgeCode
			Long referalDt=null;
			if(StringUtils.isNotBlank(referPatientDtOrDiagnosis) &&  referPatientDtOrDiagnosis.contains("&&&") ) {
			if(StringUtils.isNotBlank(refrerandDisChargeValue[1]) && refrerandDisChargeValue[1].equalsIgnoreCase("0")) {
				Long visitId=Long.parseLong(jsondata.get("visitId").toString());
				Long opdPatientDetailId=Long.parseLong(jsondata.get("opdPatientDetailId").toString());
				Long patientId=Long.parseLong(jsondata.get("patientId").toString());
				status =dgOrderhdDao.deleteChangeIcdCode(Long.parseLong(refrerandDisChargeValue[0].toString().trim()),visitId,opdPatientDetailId,patientId);
			}
			else {
				  referalDt = Long.parseLong(refrerandDisChargeValue[1].toString());
				if (referalDt != null) {
					status = dgOrderhdDao.deleteInvestigatRow(referalDt, flag);
				}
			}
			}
			else {
			//Long referalDt = Long.parseLong(jsondata.get("valueForDelete").toString());
				  referalDt = Long.parseLong(refrerandDisChargeValue[0].toString());
			if (referalDt != null) {
				status = dgOrderhdDao.deleteInvestigatRow(referalDt, flag);
			}
		}
		}
		
		if(flag.equalsIgnoreCase("4")) {
			
			Long opdPatientDetailId=Long.parseLong(jsondata.get("opdPatientDetailId").toString());
			Long patientId=Long.parseLong(jsondata.get("patientId").toString());
			
			 Map<String,Object> mapObject =dgOrderhdDao.getPatientReferalHdByVisitIdAndOpdPdAndPatient(patientId,opdPatientDetailId);			
			
			 List<ReferralPatientHd>listReferralPatientHd=(List<ReferralPatientHd>) mapObject.get("listReferralPatientHd");
			 List<ReferralPatientDt> listReferralPatientDt=(List<ReferralPatientDt>) mapObject.get("listReferralPatientDt");
			
			 if(CollectionUtils.isNotEmpty(listReferralPatientHd) && CollectionUtils.isNotEmpty(listReferralPatientDt)) {
				 status = dgOrderhdDao.deleteForReferalTypeNo(listReferralPatientDt,
						 listReferralPatientHd);
			 }
			
		}
		json.put("status", status);
		return json.toString();
	}

//User for Nursing
	@Override
	public String getPocedureDetailRecall(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json =null;
		try {
			  json = new JSONObject();
			List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
			List<MasEmpanelledHospital> masEmpanelledHospitalList = null;
			if (!jsondata.isEmpty()) {
				List<Object[]> listProcedureDt = null;
				if (jsondata.get("opdPatientDetailId") != null) {
					listProcedureDt = dgOrderhdDao
							.getProcedureDtList(Long.parseLong(jsondata.get("opdPatientDetailId").toString()),
									Long.parseLong(jsondata.get("visitId").toString()));
					String nursingName="";	
					String noOfDays="";
					String nursingRemarks="";
					Long procedureDtId=null;
					Long procedureHdId=null;
					Long frequencyId=null;
					Long nursingId=null;
					String proceduretype="";
					String frequencyName="";
					Long procedureId=null;
					String remarks="";
					if(listProcedureDt!=null) {
					for (Iterator<?> it = listProcedureDt.iterator(); it.hasNext();) {
						Object[] row = (Object[]) it.next();
						HashMap<String, Object> pt = new HashMap<String, Object>();
						if(row[0]!=null) {
							nursingName= row[0].toString();
						}
						
						if(row[1]!=null) {
							frequencyName= row[1].toString();
						}
						
						
						if(row[2]!=null) {
							noOfDays= row[2].toString();
						}
						if(row[3]!=null) {
							nursingRemarks= row[3].toString();
						}
						if(row[4]!=null) {
							nursingId= Long.parseLong(row[4].toString());
						}
						if(row[5]!=null) {
							procedureId= Long.parseLong(row[5].toString());
						}
						if(row[9]!=null) {
							procedureDtId= Long.parseLong(row[9].toString());
						}
						if(row[6]!=null) {
							procedureHdId= Long.parseLong(row[6].toString());
						}
						if(row[7]!=null) {
							frequencyId= Long.parseLong(row[7].toString());
						}
						if(row[8]!=null) {
							proceduretype=  row[8].toString();
						}
						if(row[10]!=null) {
							remarks= row[10].toString();
						}
						pt.put("nursingName", nursingName);
						pt.put("noOfDays", noOfDays);
						pt.put("nursingRemarks", nursingRemarks);
						pt.put("remarks", remarks);
						pt.put("nursingId", nursingId);
						pt.put("procedureDtId", procedureDtId);
						pt.put("procedureHdId", procedureHdId);
						pt.put("frequencyId", frequencyId);
						pt.put("proceduretype", proceduretype);
						pt.put("frequencyName", frequencyName);
						pt.put("procedureId", procedureId);
						c.add(pt);
					}
					
					List<MasFrequency> mas_frequency = md.getMasFrequency();
					json.put("listOfProcedure", c);
					json.put("masFrequency", mas_frequency);
					json.put("msg", "OPD Patients Visit List  get  sucessfull... ");
					json.put("status", "1");
				}
					else {
						json.put("msg", "Visit ID data not found");
						json.put("status", 0);
					}
				}
				else {
					return "{\"status\":\"0\",\"msg\":\"OpdPatientDetail Not Exist }";
				}
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return json.toString();
	}
	
	

/////////////////////////code by dhiraj ///////////////////

@SuppressWarnings("unchecked")
@Override
public String minorSurgeryWaitingList(HashMap<String, String> jsondata, HttpServletRequest request,
HttpServletResponse response) {
JSONObject obj = new JSONObject();
List<Map<String, Object>> minorSurgeryWaitingList = new ArrayList<>();
Map<String, Object> map = od.minorSurgeryWaitingList(jsondata, request, response);
if (map != null && !map.isEmpty()) {
List<ProcedureHd> list = (List<ProcedureHd>) map.get("minorSurgeryWaitingList");
if (list != null && !list.isEmpty()) {
String name = "", firstName = "", middleName = "", lastName = "", opdDate = "", priority = "",
patientName = "", age = "", gender = "", status = "", departmentName = "", serviceNo = "";
for (ProcedureHd hd : list) {
Map<String, Object> data = new HashMap<>();

if (hd.getVisit() != null) {

/*
* if (hd.getVisit().getIntDoctorId() != null) { firstName =
* hd.getVisit().getDoctorId().getFirstName() + ""; middleName =
* hd.getVisit().getDoctorId().getMiddleName() + ""; lastName =
* hd.getVisit().getDoctorId().getLastName() + ""; }
*/
name = firstName + " " + middleName + " " + lastName;
priority = hd.getVisit().getPriority() + "";
if (hd.getVisit().getMasDepartment() != null) {
departmentName = HMSUtil
		.convertNullToEmptyString(hd.getVisit().getMasDepartment().getDepartmentName());
}
}
data.put("id", hd.getProcedureHdId());
data.put("doctor_name", departmentName);
data.put("priority", priority);
if (hd.getOpdPatientDetails() != null) {
opdDate = HMSUtil.convertNullToEmptyString(HMSUtil
	.convertDateToStringFormat(hd.getOpdPatientDetails().getOpdDate(), "dd-MM-yyyy"));
}

data.put("opd_date", opdDate);
if (hd.getPatient() != null) {
patientName = hd.getPatient().getPatientName();
Period p = ProjectUtils.getDOB(hd.getPatient().getDateOfBirth());
age = p.getYears() + "";
if (hd.getPatient().getMasAdministrativeSex() != null) {
gender = hd.getPatient().getMasAdministrativeSex().getAdministrativeSexName().toString();
}

if (hd.getPatient().getServiceNo() != null) {
serviceNo = hd.getPatient().getServiceNo();
}
}
data.put("patient_name", patientName);
data.put("age", age);
data.put("gender", gender);
data.put("serviceNo", serviceNo);
data.put("status", hd.getStatus());
minorSurgeryWaitingList.add(data);

}
obj.put("status", "1");
obj.put("count", map.get("count"));
obj.put("minorSurgeryWaitingList", minorSurgeryWaitingList);
} else {
obj.put("status", "0");
obj.put("count", "0");
obj.put("minorSurgeryWaitingList", new JSONArray());
}
} else {
obj.put("status", "0");
obj.put("count", "");
obj.put("minorSurgeryWaitingList", new JSONArray());
}

return obj.toString();
}

@SuppressWarnings("unchecked")
@Override
public String getMinorSurgeryDetail(HashMap<String, String> jsondata, HttpServletRequest request,
HttpServletResponse response) {
JSONObject obj = new JSONObject();
String vitalData = "";
List<OpdPatientDetail> vitalRecord = new ArrayList<OpdPatientDetail>();
Map<String, Object> patientMap = new HashMap<>();
Map<String, Object> vitalMap = new HashMap<>();
Map<String, Object> map = od.getMinorSurgeryDetail(jsondata, request, response);
if (map != null && !map.isEmpty()) {
List<ProcedureDt> list = (List<ProcedureDt>) map.get("detailList");
List<ProcedureDt> list2 = new ArrayList<>();
List<Long> ids = new ArrayList<>();
for (int i = 0; i < list.size(); i++) {
if (ids.contains(list.get(i).getMasNursingCare().getNursingId())) {
continue;
}
ids.add(list.get(i).getMasNursingCare().getNursingId());
list2.add(list.get(i));

}
if (list2 != null && list2.size() > 0) {
List<Map<String, Object>> nursingDetailList = new ArrayList<>();
@SuppressWarnings("unused")
String patientName = "", age = "", gender = "", diagnosis = "", prescribedBy = "", serviceNo = "",
opdDate = "", remarks = "";

for (ProcedureDt dt : list2) {
System.out.println("id="+dt.getProcedureHd().getPatient().getPatientId());
Map<String, Object> data = new HashMap<>();
patientMap.put("patientName", dt.getProcedureHd().getPatient().getPatientName());
patientMap.put("patientId", dt.getProcedureHd().getPatient().getPatientId());
patientMap.put("serviceNo", dt.getProcedureHd().getPatient().getServiceNo());

/*
* if(dt.getProcedureHd().getVisitId() !=null) { HashMap<String, String>
* visitmap=new HashMap<String, String>();
* visitmap.put("visitId",dt.getProcedureHd().getVisitId().toString());
* vitalData=OpdPatientDetails(visitmap, request, response);
* System.out.println("vitalData"+vitalData);
* 
* }
*/

patientMap.put("opdDate", HMSUtil.convertNullToEmptyString(HMSUtil.convertDateToStringFormat(
dt.getProcedureHd().getOpdPatientDetails().getOpdDate(), "dd-MM-yyyy")));
patientMap.put("age",
ProjectUtils.getDOB(dt.getProcedureHd().getPatient().getDateOfBirth()).getYears());
patientMap.put("gender",
dt.getProcedureHd().getPatient().getMasAdministrativeSex().getAdministrativeSexName());
patientMap.put("header_id", dt.getProcedureHd().getProcedureHdId());
patientMap.put("icd_diagnosis", HMSUtil
.convertNullToEmptyString(dt.getProcedureHd().getOpdPatientDetails().getIcdDiagnosis()));
patientMap.put("working_diagnosis", HMSUtil.convertNullToEmptyString(
dt.getProcedureHd().getOpdPatientDetails().getWorkingDiagnosis()));

data.put("id", dt.getProcedureDtId());
data.put("minorSurgryName",
dt.getMasNursingCare().getNursingName() != null ? dt.getMasNursingCare().getNursingName()
		: "");
data.put("userName",
dt.getProcedureHd().getUser() != null ? dt.getProcedureHd().getUser().getFirstName() : "");

data.put("prescribedBy",
dt.getProcedureHd().getUser() != null ? dt.getProcedureHd().getUser().getUserId() : "");

data.put("procedure_id", dt.getMasNursingCare().getNursingId());
data.put("remarks", dt.getRemarks() != null ? dt.getRemarks() : "");
nursingDetailList.add(data);

}

System.out.println("patientMap="+patientMap);
obj.put("status", "1");
obj.put("patient_detail", patientMap);
obj.put("nursingDetailList", nursingDetailList);
obj.put("vitalDetailst", vitalData);
} else {
obj.put("status", "0");
obj.put("patient_detail", patientMap);
obj.put("nursingCareDetailList", new JSONArray());
}
} else {
obj.put("status", "0");
obj.put("patient_detail", patientMap);
obj.put("nursingCareDetailList", new JSONArray());
}
return obj.toString();

}

@Override
public String getAnesthesiaList(HttpServletRequest request, HttpServletResponse response) {
JSONObject jsonObj = new JSONObject();
List<MasAnesthesia> anesthesiaList = od.getAnesthesiaList();
if (anesthesiaList != null && anesthesiaList.size() > 0) {

jsonObj.put("data", anesthesiaList);
jsonObj.put("count", anesthesiaList.size());
jsonObj.put("status", 1);
} else {
jsonObj.put("data", anesthesiaList);
jsonObj.put("count", 0);
jsonObj.put("msg", "No Record Found");
jsonObj.put("status", 0);
}
System.out.println(jsonObj.toString());
return jsonObj.toString();
}

@Override
public String saveMinorSurgery(HashMap<String, Object> jsondata, HttpServletRequest request,
HttpServletResponse response) {

JSONObject jsonObj = new JSONObject();

if (jsondata != null) {

String dtObj = od.saveMinorSurgery(jsondata);

if (dtObj != null && dtObj.equalsIgnoreCase("success")) {
jsonObj.put("status", 1);
jsonObj.put("msg", "Record Added Successfully");

} else {
jsonObj.put("status", 0);
jsonObj.put("msg", "Error occured");
}
}
return jsonObj.toString();
}

@Override
public String deleteMinorSurgery(HashMap<String, Object> jsondata, HttpServletRequest request,
HttpServletResponse response) {

JSONObject jsonObj = new JSONObject();

if (jsondata != null) {

String dtObj = od.deleteMinorSurgery(jsondata);
if (dtObj != null && dtObj.equalsIgnoreCase("success")) {
jsonObj.put("status", 1);
jsonObj.put("msg", "Minor surgery Deleted Successfully");

} else {
jsonObj.put("status", 0);
jsonObj.put("msg", "Error occured");
}
}
return jsonObj.toString();
}


///////////////////////////////////////////////////

//Save Updadate Procedure candidate
public void saveOrUpdateProcedure(HashMap<String, Object> payload,String visitId,String opdPatientDetaiId,String patientId)
{
String hospitalId = "";
String userId = "";
try {

String procedureNameNursingId = payload.get("procedureNameNursingId").toString();
procedureNameNursingId = getReplaceString(procedureNameNursingId);

String procedureDtIdValue = payload.get("procedureDtIdValue").toString();
procedureDtIdValue = getReplaceString(procedureDtIdValue);

String procedureHdId = payload.get("procedureHdId").toString();
procedureHdId = getReplaceString(procedureHdId);

String frequencyNursing = payload.get("freProcedure").toString();
frequencyNursing = getReplaceString(frequencyNursing);
try {
hospitalId = payload.get("hospitalId").toString();
hospitalId = getReplaceString(hospitalId);

userId = payload.get("userId").toString();
userId = getReplaceString(userId);
} catch (Exception e) {
e.printStackTrace();
}
String procedureFreValues = "";
if (StringUtils.isNotBlank(frequencyNursing)) {
if (frequencyNursing.contains("@")) {
String[] preFreListValue = frequencyNursing.split(",");
int count = 0;
for (int i = count; i < preFreListValue.length; i++) {
	 if(StringUtils.isNotEmpty(preFreListValue[i])) {
	String[] procedureListValueNew = preFreListValue[i].split("@");
	procedureFreValues += procedureListValueNew[1] + ",";
	 }
	 else {
		 procedureFreValues += "0" + ",";
	 }
	// count+=2;
}
}
frequencyNursing = procedureFreValues;
}

String noOfDaysNursing = payload.get("noOfDaysPro").toString();
noOfDaysNursing = getReplaceString(noOfDaysNursing);

String procedureType = payload.get("procedureType").toString();
procedureType = getReplaceString(procedureType);

String remarkNursing = payload.get("remark_nursing").toString();
remarkNursing = getReplaceString(remarkNursing);

String[] procedureNameNursingIdValue = procedureNameNursingId.split(",");
String[] procedureDtIdValueArray = procedureDtIdValue.split(",");
String[] procedureHdIdValueArray = procedureHdId.split(",");

String[] proFreListValue = frequencyNursing.split(",");

String[] noOfDaysNursingValueArray = noOfDaysNursing.split(",");

String[] procedureTypeValue = procedureType.split(",");

String[] remarkNursingValue = remarkNursing.split(",");

HashMap<String, String> mapInvestigationMap = new HashMap<>();

String finalValue = "";
Integer counter = 1;
for (int i = 0; i < procedureNameNursingIdValue.length; i++) {

if (StringUtils.isNotEmpty(procedureNameNursingIdValue[i].toString())
	&& !procedureNameNursingIdValue[i].equalsIgnoreCase("0")) {
finalValue += procedureNameNursingIdValue[i].trim();
if (!procedureDtIdValueArray[i].equalsIgnoreCase("0")
		&& StringUtils.isNotBlank(procedureDtIdValueArray[i])) {
	for (int m = i; m < procedureDtIdValueArray.length; m++) {
		finalValue += "," + procedureDtIdValueArray[m].trim();
		if (m == i) {
			break;
		}
	}
} else {
	finalValue += "," + "0";
}

if (i < noOfDaysNursingValueArray.length && !proFreListValue[i].equalsIgnoreCase("0") && StringUtils.isNotBlank(proFreListValue[i])) {
	for (int j = i; j < proFreListValue.length; j++) {
		finalValue += "," + proFreListValue[j].trim();
		if (j == i) {
			break;
		}
	}
} else {
	finalValue += "," + "0";
}
if (i < noOfDaysNursingValueArray.length && StringUtils.isNotBlank(noOfDaysNursingValueArray[i])) {
	for (int k = i; k < noOfDaysNursingValueArray.length; k++) {
		finalValue += "," + noOfDaysNursingValueArray[k].trim();
		if (k == i) {
			break;
		}
	}
} else {
	finalValue += "," + "0";
}
if (i < procedureTypeValue.length && StringUtils.isNotBlank(procedureTypeValue[i])) {
	for (int l = i; l < procedureTypeValue.length; l++) {
		finalValue += "," + procedureTypeValue[l].trim();
		if (l == i) {
			break;
		}
	}
} else {
	finalValue += "," + "0";
}

if (i < remarkNursingValue.length && StringUtils.isNotBlank(remarkNursingValue[i])) {
	for (int l = i; l < remarkNursingValue.length; l++) {
		finalValue += "," + remarkNursingValue[l].trim();
		if (l == i) {
			break;
		}
	}
} else {
	finalValue += "," + "0";
}

mapInvestigationMap.put(procedureNameNursingIdValue[i].trim() + "@#" + counter, finalValue);
finalValue = "";
counter++;
}
}
counter = 1;
for (String procedureNursingId : procedureNameNursingIdValue) {
if (StringUtils.isNotEmpty(procedureNursingId)) {
if (mapInvestigationMap.containsKey(procedureNursingId.trim() + "@#" + counter)) {
	String procedureIdValue = mapInvestigationMap.get(procedureNursingId.trim() + "@#" + counter);

	if (StringUtils.isNotEmpty(procedureIdValue)) {

		String[] finalValueProcedure = procedureIdValue.split(",");
		ProcedureDt procedureDt = null;
		if (finalValueProcedure[1] != null && !finalValueProcedure[1].equalsIgnoreCase("0")
				&& StringUtils.isNotBlank(finalValueProcedure[1])) {

			procedureDt = dgOrderhdDao.getProcedureDtByProcedureDtId(
					Long.parseLong(finalValueProcedure[1].toString()));

		} else {

			procedureDt = new ProcedureDt();
			if (procedureHdIdValueArray != null
					&& StringUtils.isNotBlank(procedureHdIdValueArray[0])) {
				procedureDt.setProcedureHdId(Long.parseLong(procedureHdIdValueArray[0].toString()));
			}

		}

		if (finalValueProcedure != null) {

			if (StringUtils.isNotEmpty(finalValueProcedure[0])
					&& !finalValueProcedure[0].equals("0")) {
				procedureDt.setProcedureId(Long.parseLong(finalValueProcedure[0]));
			}

			if (finalValueProcedure[2] != null && StringUtils.isNotBlank(finalValueProcedure[2])
					&& !finalValueProcedure[2].equals("0"))
				procedureDt.setFrequencyId(Long.parseLong(finalValueProcedure[2].toString()));

			if (finalValueProcedure[3] != null && StringUtils.isNotBlank(finalValueProcedure[3])
					&& !finalValueProcedure[3].equals("0"))
				procedureDt.setNoOfDays(Long.parseLong(finalValueProcedure[3].toString()));

			if (finalValueProcedure[5] != null && StringUtils.isNotBlank(finalValueProcedure[5])
					&& !finalValueProcedure[5].equals("0")) {
				procedureDt.setRemarks(finalValueProcedure[5].toString());
			}

			if (finalValueProcedure[0] != null && StringUtils.isNotBlank(finalValueProcedure[0])
					&& !finalValueProcedure[0].equals("0")) {
				ProcedureHd procedureHd = null;
				if (finalValueProcedure[4] != null && StringUtils.isNotBlank(finalValueProcedure[4])
						&& !finalValueProcedure[4].equals("0")) {
					procedureHd = dgOrderhdDao.getProcedureHdByVisitIdAndType(
							Long.parseLong(visitId), finalValueProcedure[4].trim());
				}

				if (procedureHd != null) {
					procedureHd.setProcedureType(finalValueProcedure[4].trim());
				} else {
					procedureHd = new ProcedureHd();
				}
				procedureHd.setProcedureType(finalValueProcedure[4].trim());
				procedureHd.setStatus("N");
				Date date = new Date();
				procedureHd.setRequisitionDate(new Timestamp(date.getTime()));
				if (StringUtils.isNotBlank(hospitalId))
					procedureHd.setHospitalId(Long.parseLong(hospitalId));
				if (StringUtils.isNotBlank(userId)) {
					procedureHd.setLastChgBy(Long.parseLong(userId));
				}
				if (StringUtils.isNotBlank(patientId))
					procedureHd.setPatientId(Long.parseLong(patientId));
				if (StringUtils.isNotBlank(opdPatientDetaiId))
					procedureHd.setOpdPatientDetailsId(Long.parseLong(opdPatientDetaiId));
				if (StringUtils.isNotBlank(visitId))
					procedureHd.setVisitId(Long.parseLong(visitId));

				Long procedureHdIdValue = dgOrderhdDao.saveOrUpdateProcedureHd(procedureHd);
				if (procedureHdIdValue != null)
					procedureDt.setProcedureHdId(procedureHdIdValue);
			}
		}

		dgOrderhdDao.saveOrUpdateProcedureDd(procedureDt);
	}
}
}
counter++;
}

} catch (Exception e) {
e.printStackTrace();
}
}



//////////////////////////////////////////////////





}


















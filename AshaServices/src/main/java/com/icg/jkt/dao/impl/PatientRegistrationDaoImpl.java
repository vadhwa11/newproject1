package com.icg.jkt.dao.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icg.jkt.dao.PatientRegistrationDao;
import com.icg.jkt.entity.AppSetup;
import com.icg.jkt.entity.DoctorRoaster;
import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasAppointmentSession;
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
import com.icg.jkt.hibernateutils.GetHibernateUtils;
import com.icg.jkt.utils.HMSUtil;


@Repository
@Transactional
public class PatientRegistrationDaoImpl implements PatientRegistrationDao{

	@Autowired
	GetHibernateUtils getHibernateUtils;
	


	@SuppressWarnings({ "unchecked"})
	@Override
	public Map<String,Object> findPatientAndDependentFromEmployee(String serviceNo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<MasEmployee> employeeList = new ArrayList<MasEmployee>();
		List<Patient> patientList = new ArrayList<Patient>();
		List<MasEmployeeDependent> employeeDependentList = new ArrayList<MasEmployeeDependent>();
		
		long selfRelationId= Long.parseLong(HMSUtil.getProperties("adt.properties","SELF_RELATION_ID"));
		int i=0;
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			
			patientList = session.createCriteria(Patient.class)
							.add(Restrictions.eq("serviceNo",serviceNo))
							.add(Restrictions.isNotNull("uhidNo")).list();
			
			
			if(patientList.size()>0 && !patientList.isEmpty()) {
				
				Long []relationArray = new Long [patientList.size()];
				for(Patient patient:patientList) {
					relationArray[i] = patient.getMasRelation().getRelationId();
					i++;
				}
				
				boolean contains = Arrays.stream(relationArray).anyMatch(x -> x ==selfRelationId);
				long employeeId = patientList.get(0).getMasEmployee().getEmployeeId();
				
				if(contains)  { //true
					employeeDependentList = session.createCriteria(MasEmployeeDependent.class)
							.add(Restrictions.eq("masEmployee.employeeId", employeeId))
							.add(Restrictions.not(Restrictions.in("masRelation.relationId", relationArray)))
							.list();
				}else { // false
					
					employeeList = session.createCriteria(MasEmployee.class)
							.add(Restrictions.eq("serviceNo",serviceNo))
							.add(Restrictions.eq("status","y").ignoreCase())
							.list();
					employeeDependentList = session.createCriteria(MasEmployeeDependent.class)
							.add(Restrictions.eq("masEmployee.employeeId", employeeId))
							.add(Restrictions.not(Restrictions.in("masRelation.relationId", relationArray)))
							.list();
				}
				
			}
			else {
				employeeList = session.createCriteria(MasEmployee.class)
				.add(Restrictions.eq("serviceNo",serviceNo))
				.add(Restrictions.eq("status","y").ignoreCase())
				.list();
			}
			map.put("patientList", patientList);
			map.put("employeeList", employeeList);
			map.put("employeeDependentList", employeeDependentList);
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<MasDepartment> getDepartmentList() {
		
		List<MasDepartment> departmentList = new ArrayList<MasDepartment>();
		long departmentTypeId = Long.parseLong(HMSUtil.getProperties("adt.properties", "DEPARTMENT_TYPE_ID").trim());
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			departmentList=session.createCriteria(MasDepartment.class)
					.add(Restrictions.eq("status","y").ignoreCase())
					.createAlias("masDepartmentType", "dt")
					.add(Restrictions.eq("dt.departmentTypeId",departmentTypeId))
					.addOrder(Order.asc("departmentName"))
					.list();
			
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return departmentList;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<MasBloodGroup> getBloodGroupList() {
		List<MasBloodGroup> bloodGroupList = new ArrayList<MasBloodGroup>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			bloodGroupList= session.createCriteria(MasBloodGroup.class)
					.add(Restrictions.eq("status","y").ignoreCase())
					.addOrder(Order.asc("bloodGroupName")).list();
		}catch(Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return bloodGroupList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MasMedicalCategory> getMedicalCategoryList() {
		List<MasMedicalCategory> medicalCategoryList = new ArrayList<MasMedicalCategory>();
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			medicalCategoryList = session.createCriteria(MasMedicalCategory.class)
					.add(Restrictions.eq("status","y").ignoreCase())
					.addOrder(Order.asc("medicalCategoryName")).list();
		}catch(Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return medicalCategoryList;
	}

	
	//Code for web+portal+online  token
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getTokenNoForDepartmentMultiVisit(long departmentId,long hospitalId,long appointmentTypeId,String visitFlag,String visitDate  ) {
		Map<String, Object>map = new HashMap<String, Object>();
		 List<DoctorRoaster> doctorRosterList = new ArrayList<DoctorRoaster>();
		 List<Long>existingTokenNoList = new ArrayList<Long>();
		 long tokenNo = 0;
		 long appSessionId=0;
		 String tokenMsg="";
		 
		 try {
			 Date visitStartDate= null;
			 Date visitEndDate= null;
			 
			 Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			 
			 
			 if(!visitDate.isEmpty() && visitDate!=null) {
				 visitStartDate = HMSUtil.convertStringDateToUtilDate(visitDate, "dd-MM-yyyy");    
				 	Date nextDatefromVisit = HMSUtil.convertStringDateToUtilDate(visitDate, "dd-MM-yyyy");
					visitEndDate =HMSUtil.getNextDate(nextDatefromVisit) ; 
				 
			 }else {
			
				 Date today = new Date();
			     String dateString = HMSUtil.convertDateToStringFormat(today, "dd-MM-yyyy");
					try {
					visitStartDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
					} catch (ParseException e) {
						e.printStackTrace();
					}    
				
					visitEndDate =HMSUtil.getNextDate(today) ;
			 }
			 
						
						
				doctorRosterList =session.createCriteria(DoctorRoaster.class)
									.add(Restrictions.eq("masDepartment.departmentId", departmentId))
									.add(Restrictions.eq("masHospital.hospitalId", hospitalId))
									.add(Restrictions.eq("roasterDate", visitStartDate))
									.list();
				
				
				if(doctorRosterList.size()>0) {
					 String rosterValues = doctorRosterList.get(0).getRoasterValue();
					 String[] arrayValues = rosterValues.split(",");
					 String appTypeAndValue= "";
					 for(int i=0;i<arrayValues.length;i++) {
						 appTypeAndValue = arrayValues[i];
						 if(appTypeAndValue.substring(appTypeAndValue.lastIndexOf('@')+1).equalsIgnoreCase(String.valueOf(appointmentTypeId))) {
							 
					appSessionId = getAppointmentSessionId(hospitalId, departmentId, appointmentTypeId);
					
					if(appSessionId!=0) {// start with appointmentsessionId not equal zero	
						
						List<AppSetup>setupList = new ArrayList<AppSetup>();				 	
						 String dayName = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(visitStartDate.getTime());
						
						if(visitFlag.equalsIgnoreCase("P")) {
							 // for portal
							List<AppSetup> appointmentSetupListForPortal = new ArrayList<AppSetup>();
							
							appointmentSetupListForPortal=session.createCriteria(AppSetup.class)
									    .add(Restrictions.eq("masHospital.hospitalId", hospitalId)) 
									    .add(Restrictions.eq("masDepartment.departmentId", departmentId))
									    .add(Restrictions.eq("days", dayName))
										.add(Restrictions.eq("masAppointmentSession.id", appSessionId))
										.list();
							
							
							if(!appointmentSetupListForPortal.isEmpty() && appointmentSetupListForPortal.size()>0) {
								List<HashMap<String, Object>> portalTokenList = new ArrayList<HashMap<String, Object>>();
								long portalToken =(appointmentSetupListForPortal.get(0).getTotalPortalToken().longValue()); //Check this line while execution 
								long tokenInterval = (appointmentSetupListForPortal.get(0).getTotalInterval().longValue());
								long startToken = (appointmentSetupListForPortal.get(0).getStartToken().longValue());
								long totalToken = (appointmentSetupListForPortal.get(0).getTotalToken().longValue());
								String startTime = appointmentSetupListForPortal.get(0).getStartTime();
								String endTime =   appointmentSetupListForPortal.get(0).getEndTime();
								
								long minDaysLimit = (appointmentSetupListForPortal.get(0).getMinNoOfDays().longValue());
								long maxDaysLimt = (appointmentSetupListForPortal.get(0).getMaxNoOfDays().longValue());
								
								
								long minutes = HMSUtil.calculateTotalMinutes(startTime, endTime);
								long timePerTokenInMinutes = ((minutes)/totalToken);
								
								 String tokenStartTime="";
								 String tokenEndTime=""; 
									 
								String tokenStatus="";
								long portalTokenCount=0;
								List<Visit> tokenList = new ArrayList<Visit>();
								long tokenValue = startToken;
								if(totalToken>0  && portalToken>0 && tokenInterval!=0){
									while (portalTokenCount<=portalToken  && tokenValue <= totalToken) {
										HashMap<String,Object> tokenMap = new HashMap<String, Object>();
										portalTokenCount = portalTokenCount + 1;
										
										//token status need to fetch here
										tokenList= session.createCriteria(Visit.class)
														   .add(Restrictions.eq("tokenNo", tokenValue))
														   .createAlias("masHospital", "hospital")
														   .add(Restrictions.eq("hospital.hospitalId", hospitalId))
														   .add(Restrictions.ge("visitDate", visitStartDate))
														   .add(Restrictions.lt("visitDate", visitEndDate)).list();
										if(tokenList.size()>0) {
											// Here I have to check record with status for cancel
											for(Visit visit : tokenList) {
												if(visit.getVisitStatus().equalsIgnoreCase("W") && !(visit.getVisitStatus().equalsIgnoreCase("N"))) {
													tokenStatus ="booked";
													tokenMap.put("tokenValue", tokenValue);
													tokenMap.put("tokenStatus", tokenStatus);
												}else {
													tokenStatus ="available";
													tokenMap.put("tokenValue", tokenValue);
													tokenMap.put("tokenStatus", tokenStatus);
												}
											}
										//	for each loop will apply here
										}else {
											tokenStatus ="available";
											tokenMap.put("tokenValue", tokenValue);
											tokenMap.put("tokenStatus", tokenStatus);
											
										}
										
										tokenStartTime = startTime;
										tokenEndTime = HMSUtil.addingMinutes(tokenStartTime, (int)timePerTokenInMinutes);
										startTime = HMSUtil.addingMinutes(tokenEndTime, (int)(timePerTokenInMinutes*tokenInterval));
										
										tokenValue = tokenValue + tokenInterval; // This token-value will add in next count.
										
										tokenMap.put("tokenStartTime", tokenStartTime);
										tokenMap.put("tokenEndTime", tokenEndTime);
										tokenMap.put("tokenDate", HMSUtil.convertDateToStringFormat(visitStartDate, "dd-MM-yyyy"));
										tokenMap.put("maxDaysLimt",maxDaysLimt);
										tokenMap.put("minDaysLimit",minDaysLimit);
										
										portalTokenList.add(tokenMap);
										}
									map.put("tokenMsg", portalTokenList);
									map.put("appointmentTypeId",appointmentTypeId );	 
								}
							}
							
						
							
						}else if(visitFlag.equalsIgnoreCase("M")) {
							 // for Mobile
							List<AppSetup> appointmentSetupListForMobile = new ArrayList<AppSetup>();
							
							appointmentSetupListForMobile=session.createCriteria(AppSetup.class)
									    .add(Restrictions.eq("masHospital.hospitalId", hospitalId)) 
									    .add(Restrictions.eq("masDepartment.departmentId", departmentId))
									    .add(Restrictions.eq("days", dayName))
										.add(Restrictions.eq("masAppointmentSession.id", appSessionId))
										.list();
							
							if(!appointmentSetupListForMobile.isEmpty() && appointmentSetupListForMobile.size()>0) {
								List<HashMap<String, Object>> mobileTokenList = new ArrayList<HashMap<String, Object>>();
								long portalToken =(appointmentSetupListForMobile.get(0).getTotalPortalToken().longValue());
								long mobileToken =(appointmentSetupListForMobile.get(0).getTotalMobileToken().longValue()); //Check this line while execution 
								long tokenInterval = (appointmentSetupListForMobile.get(0).getTotalInterval().longValue());
								long startToken = (appointmentSetupListForMobile.get(0).getStartToken().longValue());
								long totalToken = (appointmentSetupListForMobile.get(0).getTotalToken().longValue());
								String startTime = appointmentSetupListForMobile.get(0).getStartTime();
								String endTime =   appointmentSetupListForMobile.get(0).getEndTime();
								
								long minDaysLimit = (appointmentSetupListForMobile.get(0).getMinNoOfDays().longValue());
								long maxDaysLimt = (appointmentSetupListForMobile.get(0).getMaxNoOfDays().longValue());
								
								long minutes = HMSUtil.calculateTotalMinutes(startTime, endTime);
								float timePerTokenInMinutes = ((minutes)/totalToken);
								
									
								String tokenStartTime=""; 
								String tokenEndTime="";
									 
								String tokenStatus="";
								long mobileTokenCount=0;
								List<Visit> tokenList = new ArrayList<Visit>();
								long tokenValue = stratTokenValueForMobile(startToken,portalToken,totalToken,tokenInterval);
								
								String tokenStartTimeMobile=startTokenTimeForMobile(startTime,timePerTokenInMinutes,tokenInterval,startToken,portalToken,totalToken);
								
								if(totalToken>0  && mobileToken>0 && tokenInterval!=0){
									while (mobileTokenCount<=mobileToken  && tokenValue <= totalToken) {
										HashMap<String,Object> tokenMap = new HashMap<String, Object>();
										mobileTokenCount = mobileTokenCount + 1;
										//token status need to fetch here
										tokenList= session.createCriteria(Visit.class)
														  .add(Restrictions.eq("tokenNo", tokenValue))
														  .createAlias("masHospital", "hospital")
														  .add(Restrictions.eq("hospital.hospitalId", hospitalId))
														  .add(Restrictions.ge("visitDate", visitStartDate))
												          .add(Restrictions.lt("visitDate", visitEndDate)).list();
										if(tokenList.size()>0) {
											// Here I have to check record with status for cancel // for each loop will apply here
											for(Visit visit : tokenList) {
												if(visit.getVisitStatus().equalsIgnoreCase("W") && !(visit.getVisitStatus().equalsIgnoreCase("N"))) {
													tokenStatus ="booked";
													tokenMap.put("tokenValue", tokenValue);
													tokenMap.put("tokenStatus", tokenStatus);
												}else {
													tokenStatus ="available";
													tokenMap.put("tokenValue", tokenValue);
													tokenMap.put("tokenStatus", tokenStatus);
												}
											}
										
										}else {
											tokenStatus ="available";
											tokenMap.put("tokenValue", tokenValue);
											tokenMap.put("tokenStatus", tokenStatus);
											
										}
																				
										tokenStartTime = tokenStartTimeMobile;
										tokenEndTime = HMSUtil.addingMinutes(tokenStartTime, (int)timePerTokenInMinutes);
										tokenStartTimeMobile = HMSUtil.addingMinutes(tokenEndTime, (int)(timePerTokenInMinutes*tokenInterval));
										
										tokenValue = tokenValue + tokenInterval; //This token-value will add in next count
										
										tokenMap.put("tokenStartTime", tokenStartTime);
										tokenMap.put("tokenEndTime", tokenEndTime);
										tokenMap.put("tokenDate", HMSUtil.convertDateToStringFormat(visitStartDate, "dd-MM-yyyy"));
										tokenMap.put("maxDaysLimt",maxDaysLimt);
										tokenMap.put("minDaysLimit",minDaysLimit);
										
										mobileTokenList.add(tokenMap);
										}
									map.put("tokenMsg", mobileTokenList);
									map.put("appointmentTypeId",appointmentTypeId );	 
								}
							}
							
						}else {
							// start for web, this will need to update for available token for walking if not used in online
							
							existingTokenNoList = session.createCriteria(Visit.class)
									.createAlias("masAppointmentSession", "session").add(Restrictions.eq("session.id", appSessionId)) 
									.createAlias("masAppointmentType", "appType").add(Restrictions.eq("appType.appointmentTypeId", appointmentTypeId))
									.createAlias("masHospital", "hospital") .add(Restrictions.eq("hospital.hospitalId", hospitalId))
									 .add(Restrictions.ge("visitDate", visitStartDate))
									 .add(Restrictions.lt("visitDate", visitEndDate))
									 .setProjection(Projections.property("tokenNo"))
									 .list();
							
							 setupList=session.createCriteria(AppSetup.class)
									    .add(Restrictions.eq("masHospital.hospitalId", hospitalId)) 
									    .add(Restrictions.eq("masDepartment.departmentId", departmentId))
									    .add(Restrictions.eq("days", dayName))
										.add(Restrictions.eq("masAppointmentSession.id", appSessionId))
										.list();
							 
								if (setupList != null && setupList.size() > 0) {
									long totalToken = setupList.get(0).getTotalToken().longValue();
									List<Long> totalTokenValue =new ArrayList<Long>();
									if (!existingTokenNoList.isEmpty() && existingTokenNoList != null ) {
											for(long count=1;count<=totalToken;count++) {
												totalTokenValue.add(count);
											}
								
									totalTokenValue.removeAll(existingTokenNoList);
									Collections.sort(totalTokenValue);		
									tokenNo = totalTokenValue.get(0);

									} else { // tokenNoList is null
										tokenNo = 1;
									}
									if(tokenNo > (setupList.get(0).getTotalToken().intValue()))
									{
										tokenNo = 0;
										tokenMsg="Token is Full for " + "("+ dayName+")";
										map.put("tokenMsg",tokenMsg );
										map.put("appointmentTypeId",appointmentTypeId );	 
									}
									else{
											tokenMsg = String.valueOf(tokenNo);
											map.put("appointmentTypeId",appointmentTypeId );	 
											map.put("tokenMsg",tokenMsg );
									}
								}
								else
								{
									tokenMsg ="No Appointment Setup";
									map.put("tokenMsg",tokenMsg );
									map.put("appointmentTypeId",appointmentTypeId );	 
								}
							// End for web
							}
						}//End of appointmentsessionId not equal zero
						 	break;
					}else {
						tokenMsg ="Doctor Roster is not available.";
						map.put("tokenMsg",tokenMsg );
						map.put("appointmentTypeId",appointmentTypeId );	 
					}
				}

			}else {
				tokenMsg ="Doctor Roster is not available";
				map.put("tokenMsg",tokenMsg );
				map.put("appointmentTypeId",appointmentTypeId );	 
			}
		 }catch(Exception e) {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		 
		  return map;
	}
	
	
	private String startTokenTimeForMobile(String startTime, float timePerTokenInMinutes, long tokenInterval,long startToken,long portalToken,long totalToken) {
	
		long tokenValue = startToken;
		long portalTokenCount = 0;
		
		String tokenStartTime = startTime;
		String tokenEndTime="";
		
		while (portalTokenCount <= portalToken && tokenValue <= totalToken) {
			portalTokenCount = portalTokenCount + 1;
			tokenEndTime = HMSUtil.addingMinutes(tokenStartTime, (int)timePerTokenInMinutes);
			tokenStartTime = HMSUtil.addingMinutes(tokenEndTime, (int)(timePerTokenInMinutes*tokenInterval));
		}
		return tokenStartTime;
		
		
		
	}



	private long stratTokenValueForMobile(long startToken,long portalToken,long totalToken,long tokenInterval) {
		long tokenValue = startToken;
		long portalTokenCount = 0;
		while (portalTokenCount <= portalToken && tokenValue <= totalToken) {
			portalTokenCount = portalTokenCount + 1;
			tokenValue = tokenValue + tokenInterval;
		}
		return tokenValue;
}


	// Ending the code for web+portal+online
	

	@SuppressWarnings("unchecked")
	@Override
	public List<MasRegistrationType> getRegistrationTypeList() {
		List<MasRegistrationType> masRegistrationTypeList = new ArrayList<MasRegistrationType>();
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			masRegistrationTypeList = session.createCriteria(MasRegistrationType.class)
					.add(Restrictions.eq("status","y").ignoreCase())
					.addOrder(Order.desc("registrationTypeName")).list();
		}catch(Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return masRegistrationTypeList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MasAdministrativeSex> getGenderList() {
		List<MasAdministrativeSex> genderList = new ArrayList<MasAdministrativeSex>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			genderList = session.createCriteria(MasAdministrativeSex.class)
					.add(Restrictions.eq("status","y").ignoreCase())
					.addOrder(Order.asc("administrativeSexName")).list();
		}catch(Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return genderList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MasIdentificationType> getIdentificationList() {
		List<MasIdentificationType> identificationTypeList = new ArrayList<MasIdentificationType>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			identificationTypeList = session.createCriteria(MasIdentificationType.class)
					.add(Restrictions.eq("status","y").ignoreCase())
					.addOrder(Order.asc("identificationName")).list();
		}catch(Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return identificationTypeList;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<MasServiceType> getServiceTypeList() {
		List<MasServiceType> serviceTypeList = new ArrayList<MasServiceType>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			serviceTypeList = session.createCriteria(MasServiceType.class)
					.add(Restrictions.eq("status","y").ignoreCase())
					.addOrder(Order.asc("serviceTypeName")).list();
		}catch(Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return serviceTypeList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public long getPatientFromUhidNo(long uhidNO) {
		long patientId=0;
		List<Patient> patientList =new ArrayList<Patient>(); 
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			patientList =	session.createCriteria(Patient.class)
			.add(Restrictions.eq("uhidNo", uhidNO)).list();
			
			if(patientList.size()>0) {
				patientId=	patientList.get(0).getPatientId();
			}
		}catch(Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return patientId;
	}



	@SuppressWarnings("unused")
	@Override
	public long saveVisitForRegisteredPatient(Visit visit) {
	
		long visitId =0;
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(Visit.class);
			Transaction tx = session.beginTransaction();
			session.save(visit);
			tx.commit();
			if (visit != null) {
				visitId = visit.getVisitId();
			} else {
				visitId = 0;
			}
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return visitId;
	}



	@SuppressWarnings("unused")
	@Override
	public long savePatient(Patient patient) {
		long patientId =0;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(Patient.class);
			Transaction tx = session.beginTransaction(); 
			Serializable id = session.save(patient);
			 tx.commit(); 
			if (id != null) {
				patientId = (long)id;
			} else {
				patientId = 0;
			}
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return patientId;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<MasRelation> getRelationList() {
		List<MasRelation> masRelationList = new ArrayList<MasRelation>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		try {
			masRelationList = session.createCriteria(MasRelation.class)
					.add(Restrictions.eq("status","y").ignoreCase())
					.addOrder(Order.asc("relationName")).list();
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return masRelationList;
	}



	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<Patient>> searchOthersRegisteredPatient(long uhinNo, String patientName, String serviceNo,
			String mobileNo) {
		Map<String,List<Patient>> map = new HashMap<String, List<Patient>>();
		List<Patient> patientList = new ArrayList<Patient>();
		long ICG_REGISTRATION_TYPE_ID= Long.parseLong(HMSUtil.getProperties("adt.properties","ICG_REGISTRATION_TYPE_ID"));
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr=null;
			cr=session.createCriteria(Patient.class);
			if(uhinNo!=0) {
				cr=cr.add(Restrictions.eq("uhidNo", uhinNo));
			}
			if(!patientName.isEmpty()) {
				cr=cr.add(Restrictions.ilike("patientName", "%"+patientName+"%"));
				
			}
			if(!serviceNo.isEmpty()) {
				cr=cr.add(Restrictions.eq("serviceNo", serviceNo));
			}
			if(!mobileNo.isEmpty()) {
				cr=cr.add(Restrictions.eq("mobileNumber", mobileNo));
			}
			
			// condition for registration type not equal to ICG
			cr=cr.add(Restrictions.ne("masRegistrationType.registrationTypeId", ICG_REGISTRATION_TYPE_ID));
			patientList= cr.list();
			
			map.put("patientList", patientList);
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}



	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getPatientTypeCodeAndRelationCode(long patientRelationId, long registrationTypeId) {

		List<MasRelation> masRelationList = new ArrayList<MasRelation>();
		List<MasRegistrationType> registrationTypeList = new ArrayList<MasRegistrationType>();	
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			String relationCode = "";
			String registrationTypeCode = "";

			
			masRelationList = session.createCriteria(MasRelation.class)
					.add(Restrictions.eq("status", "y").ignoreCase())
					.add(Restrictions.idEq(patientRelationId)).list();
			
			
			for (MasRelation masRelation : masRelationList) {
				relationCode =String.valueOf(masRelation.getNewRelationCode()) ;
				map.put("relationCode", relationCode);
			}	
			
			registrationTypeList = session.createCriteria(MasRegistrationType.class)
					.add(Restrictions.eq("status", "y").ignoreCase())
					.add(Restrictions.idEq(registrationTypeId)).list();
				
			for (MasRegistrationType registrationType : registrationTypeList) {
				registrationTypeCode = String.valueOf(registrationType.getRegistrationTypeCode());
				map.put("registrationTypeCode", registrationTypeCode);
			}
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}



	@SuppressWarnings("unchecked")
	@Override
	public String getHinIdOthers(String patientCode) {
		String previousHinNo = "";
		String maxSequenceNo = "";
		List<Patient> previousHinNoList = new ArrayList<Patient>();
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			
				previousHinNoList = session.createCriteria(Patient.class).createAlias("masRegistrationType", "mt")
						.add(Restrictions.eq("mt.registrationTypeCode", Long.parseLong(patientCode))).list();
			
			if (previousHinNoList.size() > 0) {

				ArrayList hinNoSequenceList = new ArrayList();
				for (Patient patient : previousHinNoList) {
					
						previousHinNo = Long.toString(patient.getUhidNo());						
						String sequenceNo = previousHinNo.substring(2);
						int i = Integer.parseInt(sequenceNo);
						hinNoSequenceList.add(i);
						
					
				}

				if (hinNoSequenceList.size() > 0) {
					maxSequenceNo = Collections.max(hinNoSequenceList)
							.toString();
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		//	session.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return maxSequenceNo;

	
	}



	@SuppressWarnings("unchecked")
	@Override
	public long getAppointmentSessionId(long hospitalId, long departmentId, long appointmentTypeId) {
		List<Long> appSession = new ArrayList<Long>();
		long appSessionId = 0;

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			appSession = session.createCriteria(MasAppointmentSession.class)
					.add(Restrictions.eq("masHospital.hospitalId", hospitalId))
					.add(Restrictions.eq("masDepartment.departmentId", departmentId))
					.add(Restrictions.eq("masAppointmentType.appointmentTypeId", appointmentTypeId))
					.setProjection(Projections.property("id")).list();

			if (appSession.size() > 0) {
				appSessionId = appSession.get(0);
			}
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		} finally {
			//This session will not be close 
			//getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return appSessionId;
	}


//By Anita
	@Override
	public Map<String, Object> findPatientAndVisitList(JSONObject json,String serviceNo ,Timestamp fromdateTime, Timestamp todateTime) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		//long selfRelationId= Long.parseLong(HMSUtil.getProperties("adt.properties","SELF_RELATION_ID"));
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo= 0;
		Date visitStratDate=null;
		Date visitEndDate=null;
		Date finalVisitEndDate=null;
		///start
		if(json.has("fromDate"))  {
	    String startDate = json.getString("fromDate");
	    String endDate = json.getString("toDate");
		if((!startDate.isEmpty() && startDate!=null) && (!endDate.isEmpty() && endDate!=null)) {
			 visitStratDate = HMSUtil.convertStringDateToUtilDate(startDate, "dd/MM/yyyy");   
			 visitEndDate = HMSUtil.convertStringDateToUtilDate(endDate, "dd/MM/yyyy");   
			 
			 Calendar cal = Calendar.getInstance();
				cal.setTime(visitEndDate);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				//Date from = cal.getTime();
				cal.set(Calendar.HOUR_OF_DAY, 23);
				 finalVisitEndDate = cal.getTime();
			
		 }
		}
		
		
		//end

		List totalMatches = new ArrayList();
		if (json.get("PN") != null)
			pageNo = Integer.parseInt(json.get("PN").toString());
		List<Visit> visitList = new ArrayList<Visit>();
		Criteria cr=null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			 cr = session.createCriteria(Visit.class).createAlias("patient", "pt");
			 cr.addOrder(Order.desc("visitDate"));
			 if(json.get("flag").toString().equalsIgnoreCase("W")) {
			 cr.add(Restrictions.eq("visitFlag", "P").ignoreCase());//show only portal appointment
			 }
			 Criterion criterion=null;
			 Criterion crrr=null;
			 Criterion crrr1=null;
			 Criterion crvisit=null;
			if(fromdateTime==null && todateTime==null) {
				criterion=Restrictions.eq("pt.serviceNo", serviceNo).ignoreCase();
				cr.add(criterion);
				if(json.get("flag").toString().equalsIgnoreCase("c")) {
					 crrr1=Restrictions.eq("visitStatus",'c').ignoreCase();
					 cr.add(crrr1);
			     	}
			}else {
				 criterion=Restrictions.eq("pt.serviceNo", serviceNo).ignoreCase();
				
				if(json.get("flag").toString().equalsIgnoreCase("c")) {
				 crvisit=Restrictions.eq("visitStatus",'c').ignoreCase();;
				cr.add(crvisit);
		     	}
				 if(fromdateTime!=null) {
				 crrr=Restrictions.ge("visitDate",visitStratDate);
				 }
				 if(todateTime!=null) {
                 crrr1=Restrictions.le("visitDate",finalVisitEndDate);
				 }
				cr.add(criterion).add(crrr).add(crrr1);
				
					 
			}
			totalMatches=cr.list();
			cr.setFirstResult((pageSize) * (pageNo - 1));
			cr.setMaxResults(pageSize);
			visitList= cr.list();
					
						
	}catch (Exception e) {
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		e.printStackTrace();
	}finally {
		getHibernateUtils.getHibernateUtlis().CloseConnection();
	}
		map.put("visitList", visitList);
		map.put("totalMatches", totalMatches.size());
	return map;
	}
	

	@Override
	public Map<String, Object> findOpdList(String serviceNo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		//long selfRelationId= Long.parseLong(HMSUtil.getProperties("adt.properties","SELF_RELATION_ID"));
		List<Visit> opdList = new ArrayList<Visit>();
		Criteria cr=null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			 cr = session.createCriteria(Visit.class).createAlias("patient", "pt");
			 
			 Criterion criterion=null;
			//if(fromdateTime==null && todateTime==null) {
				criterion=Restrictions.eq("pt.serviceNo", serviceNo);
				criterion=Restrictions.eq("visitStatus", 'c').ignoreCase();
				cr.add(criterion);
			
				opdList= cr.list();
					
						
	}catch (Exception e) {
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		e.printStackTrace();
	}finally {
		getHibernateUtils.getHibernateUtlis().CloseConnection();
	}
		map.put("opdList", opdList);
	return map;
	}


///for Web portal(Anita)///
//update appointment Status
@Override
public void cancelAppointment(long visitId) {
	// TODO Auto-generated method stub
	Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
	 Transaction tx = null;
     try{
        tx = session.beginTransaction();
        Visit visit = 
                   (Visit)session.get(Visit.class, visitId); 
        visit.setVisitStatus("N");
        session.update(visit); 
        tx.commit();
     }catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace(); 
     }finally {
        session.close(); 
     }
}
 //reschedule Appointment
@Override
public String rescheduleAppointment(long visitId, long tokenNo, Timestamp dateTime) {
	// TODO Auto-generated method stub
	Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
	 Transaction tx = null;
    try{
       tx = session.beginTransaction();
       Visit visit = 
                  (Visit)session.get(Visit.class, visitId); 
       visit.setTokenNo(tokenNo);
       visit.setVisitDate(dateTime);
       session.update(visit); 
       tx.commit();
    }catch (HibernateException e) {
       if (tx!=null) tx.rollback();
       e.printStackTrace(); 
    }finally {
       session.close(); 
    }
	return "Appointment has been rescheduled.";
}

//End Web Portal//

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> patientListForUploadDocument(String serviceNo) {
		List<Patient> patientList = new ArrayList<Patient>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			patientList = session.createCriteria(Patient.class)
							.add(Restrictions.eq("serviceNo",serviceNo))
							.add(Restrictions.isNotNull("uhidNo")).list();
			
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		} finally {
			
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return patientList;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Visit> getPatientAppointmentHistory(long uhidNo, String startDate, String endDate) {
		List<Visit> visitHistoryList = new ArrayList<Visit>();
		List<Object> patient= new ArrayList<Object>();
		Date visitStratDate=null;
		Date visitEndDate=null;
		Criteria cr=null;
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			
			patient = session.createCriteria(Patient.class)
					.add(Restrictions.eq("uhidNo", uhidNo))
					.setProjection(Projections.property("patientId"))
					.list();
					
			if(!patient.isEmpty() && patient.size()>0) {
				long patientId = (long) patient.get(0);
				 cr = session.createCriteria(Visit.class)
							.add(Restrictions.eq("patient.patientId", patientId))
							.add(Restrictions.eq("visitFlag", "M").ignoreCase());
				 
				 if((!startDate.isEmpty() && startDate!=null) && (!endDate.isEmpty() && endDate!=null)) {
					 visitStratDate = HMSUtil.convertStringDateToUtilDate(startDate, "dd-MM-yyyy");   
					 visitEndDate = HMSUtil.convertStringDateToUtilDate(endDate, "dd-MM-yyyy");   
					 
					 cr=cr.add(Restrictions.ge("visitDate", visitStratDate))
					       .add(Restrictions.lt("visitDate", visitEndDate));
				 }
				 visitHistoryList=cr.list();
			}		
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		} finally {

			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return visitHistoryList;
	}



	@Override
	public boolean getPatientVisitCancellation(long visitId) {
		boolean status=false;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction(); 
			Visit visit = (Visit) session.get(Visit.class, visitId);
			if(visit!=null) {
				visit.setVisitStatus("N");
				session.update(visit);
				tx.commit();
				status =true;
			}
			
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		} finally {

			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return status;
	}



	@Override
	public Map<String, Object> checkVisitExist(long deptId, long appointmentTypeId, long hospitalId, long uhidNo,
			String visitFlag, Date visitDate) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<String, Object>();
		//long selfRelationId= Long.parseLong(HMSUtil.getProperties("adt.properties","SELF_RELATION_ID"));
		List<Visit> visitList = new ArrayList<Visit>();
		Criteria cr=null;
		try {
			
			
			//Date date = new Date(visitDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(visitDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date from = cal.getTime();
			cal.set(Calendar.HOUR_OF_DAY, 23);
			Date to = cal.getTime();
			
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			 cr = session.createCriteria(Visit.class).createAlias("patient", "pt")
					 .createAlias("masHospital", "mh").createAlias("masDepartment", "md")
					 .createAlias("masAppointmentType", "mat");
			
				cr=cr.add(Restrictions.eq("pt.uhidNo", uhidNo))
					 .add(Restrictions.eq("visitStatus", 'w').ignoreCase())
				     .add(Restrictions.eq("mat.appointmentTypeId", appointmentTypeId))
				     .add(Restrictions.eq("mh.hospitalId", hospitalId))
				    //.add(Restrictions.eq("visitDate", visitDate))
				     .add( Restrictions.between("visitDate", from, to))
				     .add(Restrictions.eq("md.departmentId", deptId));
				
			
				visitList= cr.list();
				if(visitList.size()>0) 
					map.put("status", "booked"); 
				else
					map.put("status",0);
						
	}catch (Exception e) {
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		e.printStackTrace();
	}finally {
		getHibernateUtils.getHibernateUtlis().CloseConnection();
	}
		/*
		 * if(visitList.size()>0) map.put("status", "booked"); else map.put("status",
		 * "Not booked");
		 */
	return map;
	
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MasAppointmentType> getAppointmentTypeList() {
		List<MasAppointmentType> appList = new ArrayList<MasAppointmentType>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasAppointmentType.class);
			criteria.add(Restrictions.eq("status", "Y"));
			appList=criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return appList;
	}



	@SuppressWarnings("unchecked")
	@Override
	public boolean checkExistingAppointmentForPatient(Date visitDate, long hospitalId, long departmentId,
			long patientId, long appointmentTypeId) {
		List<Visit> visitList= new ArrayList<Visit>();
		
		boolean flag=false;
		Date visitStartDate=null;
		Date visitEndDate=null;
		String date=  HMSUtil.convertDateToStringFormat(visitDate, "dd-MM-yyyy");
		
		
			visitStartDate = HMSUtil.convertStringDateToUtilDate(date, "dd-MM-yyyy");    
		 	Date nextDatefromVisit = HMSUtil.convertStringDateToUtilDate(date, "dd-MM-yyyy");
			visitEndDate =HMSUtil.getNextDate(nextDatefromVisit) ; 
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			visitList = session.createCriteria(Visit.class)	
					.add(Restrictions.eq("patient.patientId", patientId))
					.add(Restrictions.eq("visitStatus", 'w').ignoreCase())
					.createAlias("masDepartment", "department").add(Restrictions.eq("department.departmentId", departmentId)) 
					.createAlias("masAppointmentType", "appType").add(Restrictions.eq("appType.appointmentTypeId", appointmentTypeId))
					.createAlias("masHospital", "hospital") .add(Restrictions.eq("hospital.hospitalId", hospitalId))
					 .add(Restrictions.ge("visitDate", visitStartDate))
					 .add(Restrictions.lt("visitDate", visitEndDate)).list();
			
			
			if(visitList.size()>0) {
				flag =true;
			}else {
				flag =false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return flag;
	}



	@SuppressWarnings("unchecked")
	@Override
	public boolean checkExistingOtherPatient(String mobileNo) {
		List<Patient> patientList = new ArrayList<Patient>();
		boolean status=false;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			patientList =session.createCriteria(Patient.class).add(Restrictions.eq("mobileNumber", mobileNo)).list();
			if(patientList.size()>0) {
				status=true;
			}
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		} finally {

			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return status;
	}

}

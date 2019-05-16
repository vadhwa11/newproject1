package com.icg.jkt.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Array;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icg.jkt.dao.OpdDao;
import com.icg.jkt.entity.AdmissionDischarge;
import com.icg.jkt.entity.DgOrderdt;
import com.icg.jkt.entity.DgOrderhd;
import com.icg.jkt.entity.DischargeIcdCode;
import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasAnesthesia;
import com.icg.jkt.entity.MasDisposal;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasFrequency;
import com.icg.jkt.entity.MasHospital;
import com.icg.jkt.entity.MasIdealWeight;
import com.icg.jkt.entity.MasHospital;
import com.icg.jkt.entity.MasIdealWeight;
import com.icg.jkt.entity.MasNursingCare;
import com.icg.jkt.entity.MasRelation;
import com.icg.jkt.entity.MasServiceType;
import com.icg.jkt.entity.MasTemplate;
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
import com.icg.jkt.entity.Users;
import com.icg.jkt.entity.Visit;
import com.icg.jkt.hibernateutils.GetHibernateUtils;
import com.icg.jkt.utils.CommonUtil;
import com.icg.jkt.utils.HMSUtil;
import com.icg.jkt.utils.ProjectUtils;

@Repository
@Transactional
public class OpdDaoImpl implements OpdDao {

	@Autowired
	GetHibernateUtils getHibernateUtils;

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String opdVitalDetails(OpdPatientDetail ob) {

		String Result = null;

		try {
			// Session session=sessionFactory.getCurrentSession();
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(OpdPatientDetail.class);
			cr.add(Restrictions.eq("visitId", ob.getVisitId()));
			OpdPatientDetail list = (OpdPatientDetail) cr.uniqueResult();
			Transaction t = session.beginTransaction();
			if (list != null) {
				list.setPollor(ob.getPollor());
				list.setEdema(ob.getEdema());
				list.setCyanosis(ob.getCyanosis());
				list.setJauindice(ob.getJauindice());
				list.setLymphNode(ob.getLymphNode());
				list.setClubbing(ob.getClubbing());
				list.setThyroid(ob.getThyroid());
				list.setTremors(ob.getTremors());
				list.setGeneralOther(ob.getGeneralOther());
				//list.setGeneral(ob.getGeneral());
				list.setCns(ob.getCns());
				list.setChestResp(ob.getChestResp());
				list.setMusculoskeletal(ob.getMusculoskeletal());
				list.setCvs(ob.getCvs());
				list.setSkin(ob.getSkin());
				list.setGi(ob.getGi());
				list.setSystemOther(ob.getSystemOther());
				list.setGenitoUrinary(ob.getGenitoUrinary());
				list.setHeight(ob.getHeight());
				list.setWeight(ob.getWeight());
				list.setIdealWeight(ob.getIdealWeight());
				list.setVaration(ob.getVaration());
				list.setTemperature(ob.getTemperature());
				list.setBpDiastolic(ob.getBpDiastolic());
				list.setBpSystolic(ob.getBpSystolic());
				list.setPulse(ob.getPulse());
				list.setSpo2(ob.getSpo2());
				list.setBmi(ob.getBmi());
				list.setRr(ob.getRr());

				session.update(list);
				Long visitId = ob.getVisitId();
				if (visitId != null) {
					Visit visit = (Visit) session.get(Visit.class, visitId);
					if (visit != null) {
						visit.setVisitStatus("p");
						session.update(visit);
					}
				}
				t.commit();
				Result = "200";

			} else {
				session.save(ob);
				Long visitId = ob.getVisitId();
				if (visitId != null) {
					Visit visit = (Visit) session.get(Visit.class, visitId);
					if (visit != null) {
						visit.setVisitStatus("p");
						session.update(visit);
					}
				}

				t.commit();
				Result = "200";
			}
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			Result = e.getMessage();
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return Result;
	}

	

	@Override
	public String opdObsisty(HashMap<String, Object> jsondata) {
		String Result=null;
		Transaction t = null;
		try { 
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(OpdObesityHd.class);
		t = session.beginTransaction();
		Long visitId = Long.parseLong((String) jsondata.get("visitId"));
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		Date currentDate=ProjectUtils.getCurrentDate();
			cr.add(Restrictions.eq("visitId", visitId));
			OpdObesityHd list = (OpdObesityHd) cr.uniqueResult();
			if (list != null) {
				list.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
				if (jsondata.get("varation") != null && !jsondata.get("varation").equals("")) {
					BigDecimal bd = new BigDecimal(String.valueOf(jsondata.get("varation")));
					list.setVaration(bd);
				}
				
				list.setHospitalId(Long.parseLong(String.valueOf(jsondata.get("hospitalId"))));
				list.setLastChgDate(ourJavaTimestampObject);
				list.setIniDate(currentDate);
				session.update(list);
				Result = "200";

			} else {
				OpdObesityHd oohd=new OpdObesityHd();
				
				  LocalDate currentDate1 = LocalDate.now(); // 2016-06-17
				  DayOfWeek dow = currentDate1.getDayOfWeek(); // FRIDAY
				  int dom = currentDate1.getDayOfMonth(); // 17
				  int doy = currentDate1.getDayOfYear(); // 169
				  String m = currentDate1.getMonth()+""; // JUNE
				  System.out.println("months"+m);
					
                
				oohd.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
				oohd.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
				oohd.setHospitalId(Long.parseLong(String.valueOf(jsondata.get("hospitalId"))));
				if (jsondata.get("varation") != null && !jsondata.get("varation").equals("")) {
					BigDecimal bd = new BigDecimal(String.valueOf(jsondata.get("varation")));
					oohd.setVaration(bd);
				}
				oohd.setIniDate(currentDate);
				oohd.setLastChgDate(ourJavaTimestampObject);
				long obesistyId = Long.parseLong(session.save(oohd).toString());
				System.out.println(obesistyId);
				t.commit();
				t=session.beginTransaction();
				   OpdObesityDt oodt=new OpdObesityDt();
				   if (jsondata.get("bmi") != null && !jsondata.get("bmi").equals("")) {
				   BigDecimal bmi = new BigDecimal(String.valueOf(jsondata.get("bmi")));
				   oodt.setBmi(bmi);
				   }
				   oodt.setObesityDate(currentDate);
				   if (jsondata.get("height") != null && !jsondata.get("height").equals("")) {
				   BigDecimal height = new BigDecimal(String.valueOf(jsondata.get("height")));
				   oodt.setHeight(height);
				   }
				   if (jsondata.get("weight") != null && !jsondata.get("weight").equals("")) {
				   BigDecimal weight = new BigDecimal(String.valueOf(jsondata.get("weight")));
				   oodt.setWeight(weight);
				   }
				   if (jsondata.get("idealWeight") != null && !jsondata.get("idealWeight").equals("")) {
				   BigDecimal idealWeight = new BigDecimal(String.valueOf(jsondata.get("idealWeight")));
				   oodt.setIdealWeight(idealWeight);
				   }
				   if (jsondata.get("varation") != null && !jsondata.get("varation").equals("")) {
						BigDecimal bd = new BigDecimal(String.valueOf(jsondata.get("varation")));
						oodt.setVariation(bd);
					}
				   oodt.setMonth(m);
				   oodt.setObesityHdId(obesistyId);
				   session.save(oodt);
			
				t.commit();
				Result = "200";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return Result;
	}

	@Override
	public MasEmployee checkEmp(Long i) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(MasEmployee.class);
		cr.add(Restrictions.eq("employeeId", i));
		MasEmployee list = (MasEmployee) cr.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}
	
	@Override
	public Users checkUser(Long i) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(Users.class);
		cr.add(Restrictions.eq("userId", i));
		Users list = (Users) cr.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	@Override
	public Patient checkPatient(Long i) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(Patient.class);
		cr.add(Restrictions.eq("patientId", i));
		Patient list = (Patient) cr.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	@Override
	public MasAdministrativeSex checkGender(Long i) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(MasAdministrativeSex.class);
		cr.add(Restrictions.eq("administrativeSexId", i));
		MasAdministrativeSex list = (MasAdministrativeSex) cr.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	@Override
	public MasRelation checkRelation(Long i) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(MasRelation.class);
		cr.add(Restrictions.eq("relationId", i));
		MasRelation list = (MasRelation) cr.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	@Override
	public Map<String,Object> getPreconsulationVisit(HashMap<String,Object> jsonData) {
		
		List<Visit> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String opdPre = "opdPre";
			String checkOdPre = jsonData.get("opdPre").toString();

			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date from = cal.getTime();
			cal.set(Calendar.HOUR_OF_DAY, 23);
			Date to = cal.getTime();

			System.out.println(from);
			System.out.println(to);
			Criteria cr = null;
			;
			int pageNo = Integer.parseInt(jsonData.get("pageNo") + "");
			long hospitalId = Long.parseLong((String.valueOf(jsonData.get("hospitalId"))));
			System.out.println("PreConsulation hospitalId "+hospitalId);
			String serviceNo = (String) jsonData.get("serviceNo");
			String patientName = (String) jsonData.get("patientName");
			int pagingSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
			int count = 0;
			
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			  cr = session.createCriteria(Visit.class, "visit").createAlias("visit.masHospital", "mh")
					.createAlias("visit.patient", "patient")
					.addOrder(Order.asc("tokenNo"));
			Criterion c1 = null;
			Criterion c2 = Restrictions.between("visitDate", from, to);
			Criterion c3 = Restrictions.eq("mh.hospitalId", hospitalId);
		
			if (checkOdPre.equalsIgnoreCase("C")) {
				c1 = Restrictions.eq("visitStatus", "c");
			}
			else {
				if (checkOdPre.equals(opdPre)) {

					c1 = Restrictions.eq("visitStatus", "w");
				} else {
					c1 = Restrictions.or(Restrictions.eq("visitStatus", "w"), Restrictions.eq("visitStatus", "p"));

				}
			}

			if (serviceNo != null && !serviceNo.equals("") && !serviceNo.equals("null")) {
				 cr.add(Restrictions.eq("patient.serviceNo", serviceNo));
			}
			if (patientName != null && !patientName.equals("") && !patientName.equals("null")) {
				String pName = "%" + patientName + "%";
				  cr.add(Restrictions.like("patient.patientName", pName));
			}
			cr.add(c1).add(c2).add(c3);
			list = cr.list();
			count = list.size();
			System.out.println(count);
			map.put("count", count);

			cr = cr.setFirstResult(pagingSize * (pageNo - 1));
			cr = cr.setMaxResults(pagingSize);
			list = cr.list();

			System.out.println("");
			map.put("list", list);
			getHibernateUtils.getHibernateUtlis().CloseConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	@Override
	public Map<String,Object> getVisit(HashMap<String,Object> jsonData) {
		
		List<Visit> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String opdPre = "opdPre";
			String checkOdPre = jsonData.get("opdPre").toString();

			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date from = cal.getTime();
			cal.set(Calendar.HOUR_OF_DAY, 23);
			Date to = cal.getTime();

			System.out.println(from);
			System.out.println(to);
			Criteria cr = null;
			;
			int pageNo = Integer.parseInt(jsonData.get("pageNo") + "");
			long hospitalId = Long.parseLong((String.valueOf(jsonData.get("hospitalId"))));
		//	Long departmentId = Long.parseLong((String.valueOf(jsonData.get("departmentId"))));
			System.out.println("Opd hospitalId "+hospitalId);
			String serviceNo = (String) jsonData.get("serviceNo");
			String patientName = (String) jsonData.get("patientName");
			int pagingSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
			int count = 0;
			
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			  cr = session.createCriteria(Visit.class, "visit").createAlias("visit.masHospital", "mh")
					 .createAlias("visit.patient", "patient")
					.addOrder(Order.asc("tokenNo"));
			Criterion c1 = null;
			Criterion c2 = Restrictions.between("visitDate", from, to);
			Criterion c3 = Restrictions.eq("mh.hospitalId", hospitalId);
			//Criterion c4 = Restrictions.eq("msDept.departmentId", departmentId);
			
			

			if (checkOdPre.equalsIgnoreCase("C")) {
				c1 = Restrictions.eq("visitStatus", "c");
			}
			else {
				if (checkOdPre.equals(opdPre)) {

					c1 = Restrictions.eq("visitStatus", "w");
				} else {
					c1 = Restrictions.or(Restrictions.eq("visitStatus", "w"), Restrictions.eq("visitStatus", "p"));

				}
			}

			if (serviceNo != null && !serviceNo.equals("") && !serviceNo.equals("null")) {
				 cr.add(Restrictions.eq("patient.serviceNo", serviceNo));
			}
			if (patientName != null && !patientName.equals("") && !patientName.equals("null")) {
				String pName = "%" + patientName + "%";
				  cr.add(Restrictions.like("patient.patientName", pName));
			}
			cr.add(c1).add(c2).add(c3);
		//	cr.add(c1).add(c2).add(c3).add(c4);
			list = cr.list();
			count = list.size();
			System.out.println(count);
			map.put("count", count);

			cr = cr.setFirstResult(pagingSize * (pageNo - 1));
			cr = cr.setMaxResults(pagingSize);
			list = cr.list();

			System.out.println("");
			map.put("list", list);
			getHibernateUtils.getHibernateUtlis().CloseConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

	/////////////////////// for visit,patient and OpdPatinetDetails for get
	/////////////////////// Visit/////////////////////

	@Override
	public List<Visit> getPatientVisit(Long visitId) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(Visit.class);
		cr.add(Restrictions.eq("visitId", visitId));
		List<Visit> list = cr.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	@Override
	public List<OpdPatientDetail> getVitalRecord(Long visitId) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(OpdPatientDetail.class);
		cr.add(Restrictions.eq("visitId", visitId));
		List<OpdPatientDetail> list = cr.list();
		System.out.println(list.size());
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}
	
	@Override
	public List<OpdPatientDetail> getPreviousVitalRecord(Long patientId) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(OpdPatientDetail.class);
		cr.add(Restrictions.eq("patientId", patientId));
		List<OpdPatientDetail> list = cr.list();
		System.out.println(list.size());
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}
	
	@Override
	
	public List<Object[]> getPreviousVisitRecord(Long patientId) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasIdealWeight> list = null;
		List<Object[]> searchList = null;
		try {	
			String Query =" SELECT VT.VISIT_DATE,ICD_DIAGNOSIS,WORKING_DIAGNOSIS,CHIEF_COMPLAIN,FAMILY_HISTORY,PAST_MEDICAL_HISTORY,PRESENT_ILLNESS_HISTORY,VT.VISIT_ID FROM  VISIT VT LEFT OUTER JOIN  OPD_PATIENT_DETAILS OPD ON OPD.VISIT_ID=VT.VISIT_ID LEFT OUTER JOIN  OPD_PATIENT_HISTORY OPH ON OPH.VISIT_ID=VT.VISIT_ID  WHERE   VT.PATIENT_ID='"+patientId+"' AND Upper(VT.VISIT_STATUS)='C'";
			
		System.out.println(Query);
		if (Query != null) 
		{
			searchList = session.createSQLQuery(Query).list();
		} 
		else
		{
			System.out.println("No Record Found");
		}
		return searchList;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return searchList;
	}
	
	@Override
	public List<OpdPatientHistory> getPreviousVisitHistory(Long patientId) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(OpdPatientHistory.class);
		cr.add(Restrictions.eq("patientId", patientId));
		List<OpdPatientHistory> list = cr.list();
		System.out.println(list.size());
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	@Override
	public int listPaginatedVisit(int firstResult, int maxResults) {
		int paginatedCount = 0;
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		try {
			Criteria criteria = session.createCriteria(Visit.class);
			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(maxResults);
			List<Visit> visit = (List<Visit>) criteria.list();
			if (visit != null) {
				paginatedCount = visit.size();
				System.out.println("Total Results: " + paginatedCount);
				for (Visit visit1 : visit) {
					System.out.println("Retrieved visit using Criteria. Name: " + visit.get(0));
				}
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return paginatedCount;
	}

	@Override
	public List<Patient> getPatinet() {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(Patient.class);
		List<Patient> list = cr.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	@Override
	public List<Object[]> getSearchPatinet(String patinetName) {
		Session session = sessionFactory.getCurrentSession();
		List<Object[]> searchList;
		String Query = "select p,v from patient p,visit v where PATIENT_NAME='" + patinetName
				+ "' AND visit.patient_id = patient.patient_id ";
		searchList = session.createSQLQuery(Query).list();
		System.out.println(Query);
		// sessionFactory.close();
		return searchList;
	}

	@Override
	public List<MasEmployee> getEmployee() {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(MasEmployee.class);
		List<MasEmployee> list = cr.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	/////////////////// check Patient Family History /////////////////
	@Override
	public List<PatientFamilyHistory> getFamilyHistory() {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(PatientFamilyHistory.class);
		List<PatientFamilyHistory> list = cr.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	//////////////////// save opd details on opdPatientHistory ////////////////////
	@Override
	public String opdPatinetHistory(OpdPatientHistory ob) {

		String Result = null;
		Transaction t = null;

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(OpdPatientHistory.class);
			cr.add(Restrictions.eq("visitId", ob.getVisitId()));
			OpdPatientHistory list = (OpdPatientHistory) cr.uniqueResult();
			t = session.beginTransaction();
			if (list != null) {
				list.setPastMedicalHistory(ob.getPastMedicalHistory());
				list.setPresentIllnessHistory(ob.getPresentIllnessHistory());
				list.setChiefComplain(ob.getChiefComplain());
				list.setPastSurgicalHistory(ob.getPastSurgicalHistory());
				list.setMedicationHistory(ob.getMedicationHistory());
				list.setPersonalHistory(ob.getPersonalHistory());
				list.setSocialHistory(ob.getSocialHistory());
				list.setFamilyHistory(ob.getFamilyHistory());
				list.setAllergyHistory(ob.getAllergyHistory());
				list.setImplantHistory(ob.getImplantHistory());

				// list.setFamilyPresentHistory(ob.getFamilyPresentHistory());
				session.update(list);
				t.commit();
				Result = "200";
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			} else {
				session.save(ob);
				t.commit();
				Result = "200";

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return Result;
	}

	////////////////// Update Vital Details ////////////////////
	@Override
	public OpdPatientDetail checkVisitOpdPatientDetails(Long visitId) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(OpdPatientDetail.class);
		cr.add(Restrictions.eq("visitId", visitId));
		OpdPatientDetail list = (OpdPatientDetail) cr.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	@Override
	public OpdPatientHistory checkVisitOpdPatientHistory(Long visitId) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(OpdPatientHistory.class);
		cr.add(Restrictions.eq("visitId", visitId));
		OpdPatientHistory list = (OpdPatientHistory) cr.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	//////////////////// save opd details on opdTemplate ////////////////////
	//////////////////// ////////////////////
	@Override
	public String opdTemplate(OpdTemplate ob, OpdTemplateInvestigation opdinv) {

		String Result = null;
		Transaction t = null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(OpdPatientHistory.class);
			cr.add(Restrictions.eq("visitId", ob.getTemplateId()));
			OpdPatientHistory list = (OpdPatientHistory) cr.uniqueResult();
			t = session.beginTransaction();
			
			Serializable id = session.save(ob);

			// OpdTemplateInvestigation opdtInv= new OpdTemplateInvestigation();
			opdinv.setTemplateId(Long.valueOf(id.toString()));

			System.out.println("hi this is id" + id);

			Serializable id2 = session.save(opdinv);

			System.out.println("hi this is id2=====" + id2);
			t.commit();
			Result = "200";
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			// }
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			Result = null;
			e.printStackTrace();
		}
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return Result;
	}

	@Override
	public String opdTemplatenewMethod(OpdTemplate opdTemp, List<OpdTemplateInvestigation> opdInvestigationList) {

		String result = null;
		Transaction t = null;
		Session session = null;
		try {
			session = getHibernateUtils.getHibernateUtlis().OpenSession();
			t = session.beginTransaction();
			Serializable id = session.save(opdTemp);

			for (OpdTemplateInvestigation single : opdInvestigationList) {

				single.setTemplateId(Long.valueOf(id.toString()));
				session.save(single);

			}

			t.commit();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		} catch (Exception ex) {
			t.rollback();

			return "500";

		}

		return "200";
	}
	
	@Override
	public String saveTreatTemplatenewMethod(OpdTemplate opdTemp, List<OpdTemplateTreatment> opdTreatmentTempList) {

		String result = null;
		Transaction t = null;
		Session session = null;
		try {
			session = getHibernateUtils.getHibernateUtlis().OpenSession();
			t = session.beginTransaction();
			Serializable id = session.save(opdTemp);

			for (OpdTemplateTreatment single : opdTreatmentTempList) {

				single.setTemplateId(Long.valueOf(id.toString()));
				session.save(single);

			}

			t.commit();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		} catch (Exception ex) {
			t.rollback();

			return "500";

		}

		return "200";
	}
	

	@Override
	public String saveOpdInvestigation(DgOrderhd orderhd, List<DgOrderdt> dgorderdt) {

		String result = null;
		Transaction t = null;
		Session session = null;
		try {
			session = getHibernateUtils.getHibernateUtlis().OpenSession();
			t = session.beginTransaction();
			Serializable id = session.save(orderhd);

			for (DgOrderdt single : dgorderdt) {

				single.setOrderhdId(Long.valueOf(id.toString()));
				session.save(single);

			}

			t.commit();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();

			return "500";

		}

		return "200";
	}

	@Override
	public String saveOpdPrescription(PatientPrescriptionHd pphd, List<PatientPrescriptionDt> patientPrescDT) {

		String result = null;
		Transaction t = null;
		Session session = null;
		try {
			session = getHibernateUtils.getHibernateUtlis().OpenSession();
			t = session.beginTransaction();
			Serializable id = session.save(pphd);

			for (PatientPrescriptionDt single : patientPrescDT) {

				single.setPrescriptionHdId(Long.valueOf(id.toString()));
				session.save(single);

			}

			t.commit();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();

			return "500";

		}

		return "200";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String saveOpdPatientDetails(HashMap<String, Object> jsondata) {
		Date currentDate = ProjectUtils.getCurrentDate();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(OpdPatientDetail.class);
		Transaction tx = session.beginTransaction();
		Long visitId = Long.parseLong((String) jsondata.get("visitId"));
		Long patientId;
		Long hospitalId;
		Long opdId;
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		String procedureType=null;
		cr.add(Restrictions.eq("visitId", visitId));
		OpdPatientDetail opdlist = (OpdPatientDetail) cr.uniqueResult();
		try {

			if (opdlist != null) {
				opdlist.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
				opdlist.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
				opdlist.setPollor(String.valueOf(jsondata.get("pallar")));
				opdlist.setEdema(String.valueOf(jsondata.get("edema")));
				opdlist.setCyanosis(String.valueOf(jsondata.get("cyanosis")));
				opdlist.setJauindice(String.valueOf(jsondata.get("jauindice")));
				opdlist.setHairNail(String.valueOf(jsondata.get("hairnail")));
				opdlist.setLymphNode(String.valueOf(jsondata.get("lymphNode")));
				opdlist.setClubbing(String.valueOf(jsondata.get("clubbing")));
				opdlist.setThyroid(String.valueOf(jsondata.get("thyroid")));
				opdlist.setTremors(String.valueOf(jsondata.get("tremors")));
				opdlist.setGeneralOther(String.valueOf(jsondata.get("generalOther")));
				//opdlist.setGeneral(String.valueOf(jsondata.get("general")));
				opdlist.setCns(String.valueOf(jsondata.get("cns")));
				opdlist.setChestResp(String.valueOf(jsondata.get("chestresp")));
				opdlist.setMusculoskeletal(String.valueOf(jsondata.get("musculoskeletal")));
				opdlist.setCvs(String.valueOf(jsondata.get("cvs")));
				opdlist.setSkin(String.valueOf(jsondata.get("skin")));
				opdlist.setGi(String.valueOf(jsondata.get("gi")));
				opdlist.setOpdDate(ourJavaTimestampObject);
				opdlist.setIcdDiagnosis(String.valueOf(jsondata.get("icdDiagnosis")));
				opdlist.setSnomedDiagnosis(String.valueOf(jsondata.get("snomedDiagnosis")));
				opdlist.setWorkingDiagnosis(String.valueOf(jsondata.get("workingDiagnosis")));
				opdlist.setSystemOther(String.valueOf(jsondata.get("systemother")));
				opdlist.setGenitoUrinary(String.valueOf(jsondata.get("genitourinary")));
				opdlist.setHeight(String.valueOf(jsondata.get("height")));
				opdlist.setWeight(String.valueOf(jsondata.get("weight")));
				opdlist.setIdealWeight(String.valueOf(jsondata.get("idealWeight")));
				if(jsondata.get("obsistyMark")!=null)
				{
				 opdlist.setObeseFlag("Y");	
				}
				if (jsondata.get("disposal1Id") != null && !jsondata.get("disposal1Id").equals("")) {
					opdlist.setDisposal1Id(Long.parseLong(String.valueOf(jsondata.get("disposal1Id"))));
				}
				opdlist.setDisposalDays(String.valueOf(jsondata.get("disposalDays")));
				if (jsondata.get("varation") != null && !jsondata.get("varation").equals("")) {
					BigDecimal bd = new BigDecimal(String.valueOf(jsondata.get("varation")));
					opdlist.setVaration(bd);
				}
				opdlist.setTemperature(String.valueOf(jsondata.get("temperature")));
				opdlist.setBpSystolic(String.valueOf(jsondata.get("bp")));
				opdlist.setBpDiastolic(String.valueOf(jsondata.get("bp1")));
				opdlist.setPulse(String.valueOf(jsondata.get("pulse")));
				opdlist.setSpo2(String.valueOf(jsondata.get("spo2")));
				opdlist.setBmi(String.valueOf(jsondata.get("bmi")));
				opdlist.setRr(String.valueOf(jsondata.get("rr")));
				opdlist.setHospitalId(Long.parseLong(String.valueOf(jsondata.get("hospitalId"))));
				session.update(opdlist);
				opdId = opdlist.getOpdPatientDetailsId();
				if (visitId != null) {
					Visit visit = (Visit) session.get(Visit.class, visitId);
					if (visit != null) {
						visit.setVisitStatus("c");
						session.update(visit);
					}
				}
        /////////////////////////Discharge ICD Code Details Entry /////////////////////////////
		if(jsondata.get("icdValue")!=null)
		{
		List<HashMap<String, Object>> listIcdValue = (List<HashMap<String, Object>>) (Object) jsondata.get("icdValue");
		if (listIcdValue != null) {
			
		List<DischargeIcdCode>  dischargeDT= new ArrayList<>();				
	    for (HashMap<String, Object> icdOpd: listIcdValue)
		{
	    	
	    	DischargeIcdCode disIcdCode=new DischargeIcdCode();
	    	disIcdCode.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
	    	disIcdCode.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));	
	    	disIcdCode.setOpdPatientDetailsId(opdId);
	    	disIcdCode.setIcdId(Long.parseLong(String.valueOf(icdOpd.get("icdId"))));
	    	dischargeDT.add(disIcdCode);
	    	session.save(disIcdCode);
	    	
		}
		}   
		}
		
			/////////////   Referal Details Section //////////////////////////////// 
			if(jsondata.get("listofReferallHD")!=null)
			{
			List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) (Object) jsondata
					.get("listofReferallHD");
		
			if (list != null) {
				for (HashMap<String, Object> map : list) {
					 
					String extId = (String) map.get("extHospitalId");
					Long empID = Long.parseLong(extId);
					String referralNote = (String) map.get("referralNote");
					patientId = Long.parseLong((String) map.get("patientId"));
					hospitalId = Long.parseLong(String.valueOf(map.get("hospitalId")));
					// String treatmentType=(String)map.get("treatmentType");
					ReferralPatientHd header=null;
					if(empID!=null && opdId!=null) {
						header=getReferralPatientHdByExeHosAndOpdPd(empID, opdId);
					}
					Long id=null;
					if(header==null) {
						
					  header = new ReferralPatientHd();
					header.setPatientId(patientId);
					header.setHospitalId(hospitalId);
					header.setExtHospitalId(empID);
					header.setReferralIniDate(currentDate);
					header.setTreatmentType("E");
					if(map.get("extReferalDate")!=null && map.get("extReferalDate")!="")
					{
					Date d = HMSUtil.convertStringDateToUtilDate(map.get("extReferalDate").toString(), "yyyy-MM-dd");
					header.setReferralIniDate(d);
					}
					header.setReferralNote(String.valueOf(referralNote));
					header.setStatus("W");
					header.setOpdPatientDetailsId(opdId);
					  id = Long.parseLong(session.save(header).toString());
					}
					else {
						id=header.getRefrealHdId();
					}
					Long extHosId=header.getExtHospitalId();
					System.out.println(extHosId);
					List<HashMap<String, Object>> diagnosisList = (List<HashMap<String, Object>>) (Object) map.get("listofReferalDT");
					for (HashMap<String, Object> diagnosisMap : diagnosisList) {
						long diagnosisId = Long.parseLong((String) diagnosisMap.get("diagnosisId"));
						ReferralPatientDt refDt = new ReferralPatientDt();
						refDt.setDiagnosisId(diagnosisId);
						refDt.setExtDepartment(String.valueOf(diagnosisMap.get("extDepartment"))); 
						refDt.setInstruction(String.valueOf(diagnosisMap.get("instruction")));
						refDt.setRefrealHdId(id);
						session.save(refDt);
						
					}
				}

			}
		}
			  /////////////   Nursing Care Details Section //////////////////////////////// 
			if(jsondata.get("listofNursingCareHD")!=null)
			{
			List<HashMap<String, Object>> listNursing = (List<HashMap<String, Object>>) (Object) jsondata
					.get("listofNursingCareHD");
		
			if (listNursing != null) {
				for (HashMap<String, Object> map : listNursing) {
					visitId=Long.parseLong((String) map.get("visitId"));
					patientId = Long.parseLong((String) map.get("patientId"));
					hospitalId = Long.parseLong(String.valueOf(map.get("hospitalId")));
					// String treatmentType=(String)map.get("treatmentType");
                    procedureType=(String) map.get("procedureType");
					ProcedureHd pHd = new ProcedureHd();
					pHd.setVisitId(visitId);
					pHd.setPatientId(patientId);
					pHd.setHospitalId(hospitalId);
					pHd.setStatus("N");
					pHd.setRequisitionDate(ourJavaTimestampObject);
					pHd.setProcedureType(procedureType);
					pHd.setOpdPatientDetailsId(opdId);
					Long headerId = Long.parseLong(session.save(pHd).toString());
					List<HashMap<String, Object>> nursingList = (List<HashMap<String, Object>>) (Object) map.get("listofNursingDT");
					ProcedureDt refNursingDt = new ProcedureDt();
					for (HashMap<String, Object> nursingMap : nursingList) {
						if(nursingMap.get("nursingId")!=null && nursingMap.get("nursingId")!="" )
						{	
						Long nursingId = Long.parseLong((String) nursingMap.get("nursingId"));
						refNursingDt.setProcedureId(nursingId);
						}
						refNursingDt.setStatus("N"); 
						if(nursingMap.get("frequencyId")!=null && nursingMap.get("frequencyId")!="" )
						{
						refNursingDt.setFrequencyId(Long.parseLong((String) nursingMap.get("frequencyId")));
						}
						if(nursingMap.get("noOfDays")!=null && nursingMap.get("noOfDays")!="" )
						{
						refNursingDt.setNoOfDays(Long.parseLong((String) nursingMap.get("noOfDays")));
						}
						refNursingDt.setRemarks(String.valueOf(nursingMap.get("remarks")));
						refNursingDt.setProcedureDate(ourJavaTimestampObject);
						refNursingDt.setAppointmentDate(ourJavaTimestampObject);
						refNursingDt.setProcedureHdId(headerId);
						session.save(refNursingDt);
						
					}
				}

			}
		}
			////////////////////////////////////////  Investigation Section ////////////////////////////////////////
			if(jsondata.get("listofInvestigation")!=null)
				{
				List<HashMap<String, Object>> listInvestigation= (List<HashMap<String, Object>>) (Object) jsondata
						.get("listofInvestigation");
			
				if (listInvestigation != null) {
					for (HashMap<String, Object> map : listInvestigation) {
						//DgOrderhd orderhd = new DgOrderhd();
						DgOrderhd orderhd=null;
						Long headerInveId=null;
						
						//Date orderD = (Date) map.get("orderDate");
												
						Date orderDate= HMSUtil.convertStringDateToUtilDate(map.get("orderDate").toString(), "yyyy-MM-dd");
						Long visitId11=(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
						
						
						if(orderDate !=null && visitId11!=null) {
							orderhd=getOrderDatebyInvestigation(orderDate, visitId11);
						}
						if(orderhd==null)
						{	
						orderhd = new DgOrderhd();	
						orderhd.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
    					orderhd.setDepartmentId(Long.parseLong(String.valueOf(jsondata.get("departmentId"))));
    					orderhd.setOrderDate(orderDate);
    					orderhd.setOtherInvestigation(String.valueOf(jsondata.get("otherInvestigation")));
    					orderhd.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
    					orderhd.setHospitalId(Long.parseLong(String.valueOf(jsondata.get("hospitalId"))));
    					//orderhd.setOtherInvestigation(String.valueOf(jsondata.get("otherInvestigation")));
    					orderhd.setOrderStatus(String.valueOf(jsondata.get("orderStatus")));
    					orderhd.setLastChgBy(Long.parseLong(jsondata.get("userId").toString()));
						headerInveId = Long.parseLong(session.save(orderhd).toString());
						}
						else
						{
							headerInveId=orderhd.getOrderhdId();	
						}
						List<HashMap<String, Object>> invList = (List<HashMap<String, Object>>) (Object) map.get("listofInvestigationDT");
						
						for (HashMap<String, Object> invMap : invList) {
							DgOrderdt ob1=new DgOrderdt();
							if(invMap.get("investigationId")!=null && invMap.get("investigationId")!="")
							{	
							ob1.setInvestigationId(Long.valueOf(invMap.get("investigationId").toString()));
							}
							ob1.setLabMark(String.valueOf(invMap.get("labMark")));
							ob1.setUrgent(String.valueOf(invMap.get("urgent")));
							if(invMap.get("orderDate")!=null && invMap.get("orderDate")!="")
							{
							Date d = HMSUtil.convertStringDateToUtilDate(invMap.get("orderDate").toString(), "yyyy-MM-dd");
							ob1.setOrderDate(d);
							}
						   ob1.setOrderhdId(headerInveId);
						   session.save(ob1);
							
						}
					}

				}
			}	
			}

			 else {

				OpdPatientDetail opddetails = new OpdPatientDetail();
				opddetails.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
				opddetails.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
				opddetails.setPollor(String.valueOf(jsondata.get("pallar")));
				opddetails.setEdema(String.valueOf(jsondata.get("edema")));
				opddetails.setCyanosis(String.valueOf(jsondata.get("cyanosis")));
				opddetails.setJauindice(String.valueOf(jsondata.get("jauindice")));
				opddetails.setHairNail(String.valueOf(jsondata.get("hairnail")));
				opddetails.setLymphNode(String.valueOf(jsondata.get("lymphNode")));
				opddetails.setClubbing(String.valueOf(jsondata.get("clubbing")));
				opddetails.setThyroid(String.valueOf(jsondata.get("thyroid")));
				opddetails.setTremors(String.valueOf(jsondata.get("tremors")));
				opddetails.setGeneralOther(String.valueOf(jsondata.get("generalOther")));
				//opddetails.setGeneral(String.valueOf(jsondata.get("general")));
				opddetails.setCns(String.valueOf(jsondata.get("cns")));
				opddetails.setChestResp(String.valueOf(jsondata.get("chestresp")));
				opddetails.setMusculoskeletal(String.valueOf(jsondata.get("musculoskeletal")));
				opddetails.setCvs(String.valueOf(jsondata.get("cvs")));
				opddetails.setSkin(String.valueOf(jsondata.get("skin")));
				opddetails.setGi(String.valueOf(jsondata.get("gi")));
				opddetails.setIcdDiagnosis(String.valueOf(jsondata.get("icdDiagnosis")));
				opddetails.setSnomedDiagnosis(String.valueOf(jsondata.get("snomedDiagnosis")));
				opddetails.setWorkingDiagnosis(String.valueOf(jsondata.get("workingDiagnosis")));
				opddetails.setSystemOther(String.valueOf(jsondata.get("systemother")));
				opddetails.setGenitoUrinary(String.valueOf(jsondata.get("genitourinary")));
				opddetails.setHeight(String.valueOf(jsondata.get("height")));
				opddetails.setOpdDate(ourJavaTimestampObject);
				opddetails.setHospitalId(Long.parseLong(String.valueOf(jsondata.get("hospitalId"))));
				opddetails.setWeight(String.valueOf(jsondata.get("weight")));
				opddetails.setIdealWeight(String.valueOf(jsondata.get("idealWeight")));
				//opddetails.setDepartmentId(Long.parseLong(String.valueOf(jsondata.get("hospitalId"))));
				if (jsondata.get("disposal1Id") != null && !jsondata.get("disposal1Id").equals("")) {
					opddetails.setDisposal1Id(Long.parseLong(String.valueOf(jsondata.get("disposal1Id"))));
				}
				opddetails.setDisposalDays(String.valueOf(jsondata.get("disposalDays")));
				if (jsondata.get("varation") != null && !jsondata.get("varation").equals("")) {
					BigDecimal bd = new BigDecimal(String.valueOf(jsondata.get("varation")));
					opddetails.setVaration(bd);
				}
				if(jsondata.get("obsistyMark")!=null)
				{
					opddetails.setObeseFlag("Y");	
				}
				opddetails.setTemperature(String.valueOf(jsondata.get("temperature")));
				opddetails.setBpSystolic(String.valueOf(jsondata.get("bp")));
				opddetails.setBpDiastolic(String.valueOf(jsondata.get("bp1")));
				opddetails.setPulse(String.valueOf(jsondata.get("pulse")));
				opddetails.setSpo2(String.valueOf(jsondata.get("spo2")));
				opddetails.setBmi(String.valueOf(jsondata.get("bmi")));
				opddetails.setRr(String.valueOf(jsondata.get("rr")));
				
				opdId = Long.parseLong(session.save(opddetails).toString());
				if (visitId != null) {
					Visit visit = (Visit) session.get(Visit.class, visitId);
					if (visit != null) {
						visit.setVisitStatus("c");
						session.update(visit);
					}
				}
				
				/////////////////////////Discharge ICD Code Details Entry /////////////////////////////
				if(jsondata.get("icdValue")!=null)
				{
				List<HashMap<String, Object>> listIcdValue = (List<HashMap<String, Object>>) (Object) jsondata.get("icdValue");
				if (listIcdValue != null) {
					
				List<DischargeIcdCode>  dischargeDT= new ArrayList<>();				
			    for (HashMap<String, Object> icdOpd: listIcdValue)
				{
			    	
			    	DischargeIcdCode disIcdCode=new DischargeIcdCode();
			    	disIcdCode.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
			    	disIcdCode.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));	
			    	disIcdCode.setOpdPatientDetailsId(opdId);
			    	disIcdCode.setIcdId(Long.parseLong(String.valueOf(icdOpd.get("icdId"))));
			    	dischargeDT.add(disIcdCode);
			    	session.save(disIcdCode);
			    	
				}
				}   
				}
				
	           /////////////   Referal Details Section //////////////////////////////// 
				if(jsondata.get("listofReferallHD")!=null)
				{
				List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) (Object) jsondata
						.get("listofReferallHD");
			
				if (list != null) {
					for (HashMap<String, Object> map : list) {
						 
						String extId = (String) map.get("extHospitalId");
						Long empID = Long.parseLong(extId);
						String referralNote = (String) map.get("referralNote");
						patientId = Long.parseLong((String) map.get("patientId"));
						hospitalId = Long.parseLong(String.valueOf(map.get("hospitalId")));
						// String treatmentType=(String)map.get("treatmentType");
						ReferralPatientHd header=null;
						if(empID!=null && opdId!=null) {
							header=getReferralPatientHdByExeHosAndOpdPd(empID, opdId);
						}
						Long id=null;
						if(header==null) {
							
						  header = new ReferralPatientHd();
						header.setPatientId(patientId);
						header.setHospitalId(hospitalId);
						header.setExtHospitalId(empID);
						header.setReferralIniDate(currentDate);
						header.setTreatmentType("E");
						if(map.get("extReferalDate")!=null && map.get("extReferalDate")!="")
						{
						Date d = HMSUtil.convertStringDateToUtilDate(map.get("extReferalDate").toString(), "yyyy-MM-dd");
						header.setReferralIniDate(d);
						}
						header.setReferralNote(String.valueOf(referralNote));
						header.setStatus("W");
						header.setOpdPatientDetailsId(opdId);
						  id = Long.parseLong(session.save(header).toString());
						}
						else {
							id=header.getRefrealHdId();
						}
						Long extHosId=header.getExtHospitalId();
						System.out.println(extHosId);
						List<HashMap<String, Object>> diagnosisList = (List<HashMap<String, Object>>) (Object) map.get("listofReferalDT");
						for (HashMap<String, Object> diagnosisMap : diagnosisList) {
							long diagnosisId = Long.parseLong((String) diagnosisMap.get("diagnosisId"));
							ReferralPatientDt refDt = new ReferralPatientDt();
							refDt.setDiagnosisId(diagnosisId);
							refDt.setExtDepartment(String.valueOf(diagnosisMap.get("extDepartment"))); 
							refDt.setInstruction(String.valueOf(diagnosisMap.get("instruction")));
							refDt.setRefrealHdId(id);
							session.save(refDt);
							
						}
					}

				}
			}
				  /////////////   Nursing Care Details Section //////////////////////////////// 
				if(jsondata.get("listofNursingCareHD")!=null)
				{
				List<HashMap<String, Object>> listNursing = (List<HashMap<String, Object>>) (Object) jsondata
						.get("listofNursingCareHD");
			
				if (listNursing != null) {
					for (HashMap<String, Object> map : listNursing) {
						visitId=Long.parseLong((String) map.get("visitId"));
						patientId = Long.parseLong((String) map.get("patientId"));
						hospitalId = Long.parseLong(String.valueOf(map.get("hospitalId")));
						// String treatmentType=(String)map.get("treatmentType");
                        procedureType=(String) map.get("procedureType");
						ProcedureHd pHd = new ProcedureHd();
						pHd.setVisitId(visitId);
						pHd.setPatientId(patientId);
						pHd.setHospitalId(hospitalId);
						pHd.setStatus("N");
						pHd.setRequisitionDate(ourJavaTimestampObject);
						pHd.setProcedureType(procedureType);
						pHd.setOpdPatientDetailsId(opdId);
						Long headerId = Long.parseLong(session.save(pHd).toString());
						List<HashMap<String, Object>> nursingList = (List<HashMap<String, Object>>) (Object) map.get("listofNursingDT");
						
						for (HashMap<String, Object> nursingMap : nursingList) {
							ProcedureDt refNursingDt = new ProcedureDt();
							if(nursingMap.get("nursingId")!=null && nursingMap.get("nursingId")!="" )
							{	
							Long nursingId = Long.parseLong((String) nursingMap.get("nursingId"));
							refNursingDt.setProcedureId(nursingId);
							}
							refNursingDt.setStatus("N"); 
							if(nursingMap.get("frequencyId")!=null && nursingMap.get("frequencyId")!="" )
							{
							refNursingDt.setFrequencyId(Long.parseLong((String) nursingMap.get("frequencyId")));
							}
							if(nursingMap.get("noOfDays")!=null && nursingMap.get("noOfDays")!="" )
							{
							refNursingDt.setNoOfDays(Long.parseLong((String) nursingMap.get("noOfDays")));
							}
							refNursingDt.setRemarks(String.valueOf(nursingMap.get("remarks")));
							refNursingDt.setProcedureDate(ourJavaTimestampObject);
							refNursingDt.setAppointmentDate(ourJavaTimestampObject);
							refNursingDt.setProcedureHdId(headerId);
							session.save(refNursingDt);
							
						}
					}

				}
			}
			////////////////////////////////////////  Investigation Section ////////////////////////////////////////
			if(jsondata.get("listofInvestigation")!=null)
				{
				List<HashMap<String, Object>> listInvestigation= (List<HashMap<String, Object>>) (Object) jsondata
						.get("listofInvestigation");
			
				if (listInvestigation != null) {
					for (HashMap<String, Object> map : listInvestigation) {
						//DgOrderhd orderhd = new DgOrderhd();
						DgOrderhd orderhd=null;
						Long headerInveId=null;
						
						//Date orderD = (Date) map.get("orderDate");
												
						Date orderDate= HMSUtil.convertStringDateToUtilDate(map.get("orderDate").toString(), "yyyy-MM-dd");
						Long visitId11=(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
						
						
						if(orderDate !=null && visitId11!=null) {
							orderhd=getOrderDatebyInvestigation(orderDate, visitId11);
						}
						if(orderhd==null)
						{	
						orderhd = new DgOrderhd();	
						orderhd.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
    					orderhd.setDepartmentId(Long.parseLong(String.valueOf(jsondata.get("departmentId"))));
    					orderhd.setOrderDate(orderDate);
    					orderhd.setOtherInvestigation(String.valueOf(map.get("otherInvestigation")));
    					orderhd.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
    					orderhd.setHospitalId(Long.parseLong(String.valueOf(jsondata.get("hospitalId"))));
    					//orderhd.setOtherInvestigation(String.valueOf(jsondata.get("otherInvestigation")));
    					orderhd.setOrderStatus(String.valueOf(jsondata.get("orderStatus")));
    					orderhd.setLastChgBy(Long.parseLong(jsondata.get("userId").toString()));
						headerInveId = Long.parseLong(session.save(orderhd).toString());
						}
						else
						{
							headerInveId=orderhd.getOrderhdId();	
						}
						List<HashMap<String, Object>> invList = (List<HashMap<String, Object>>) (Object) map.get("listofInvestigationDT");
						
						for (HashMap<String, Object> invMap : invList) {
							DgOrderdt ob1=new DgOrderdt();
							if(invMap.get("investigationId")!=null && invMap.get("investigationId")!="")
							{	
							ob1.setInvestigationId(Long.valueOf(invMap.get("investigationId").toString()));
							}
							ob1.setLabMark(String.valueOf(invMap.get("labMark")));
							ob1.setUrgent(String.valueOf(invMap.get("urgent")));
							if(invMap.get("orderDate")!=null && invMap.get("orderDate")!="")
							{
							Date d = HMSUtil.convertStringDateToUtilDate(invMap.get("orderDate").toString(), "yyyy-MM-dd");
							ob1.setOrderDate(d);
							}
						   ob1.setOrderhdId(headerInveId);
						   session.save(ob1);
							
						}
					}

				}
			}	
			}
		} catch (Exception ex) {
			
			//System.out.println("Exception e="+ex.);
			ex.printStackTrace();
			tx.rollback();
			return "Error while saving records";
		} finally {
			tx.commit();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return "Successfully saved";
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getObesityWaitingList(HashMap<String, Object> jsondata){
		
		long hospitalId = (Integer)jsondata.get("hospitalId");
		Session session = getHibernateUtils.getHibernateUtlis().getCurrentSession();
		@SuppressWarnings("unchecked")
		String serviceNo = String.valueOf(jsondata.get("service_no"));
		String SpageNo = String.valueOf(jsondata.get("pageNo"));
		int pageNo= Integer.parseInt(SpageNo);
		int pagingSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int count=0;
		String patientName = String.valueOf(jsondata.get("patient_name")).trim();
		System.out.println("service no "+serviceNo);
		List<OpdObesityHd> patientObesityList = new ArrayList<OpdObesityHd>();
		Map<String,Object> map = new HashMap<String,Object>();
		
		Criteria criteria = null;
		
		criteria  = session.createCriteria(OpdObesityHd.class,"csr")
				.add(Restrictions.eq("masHospital.hospitalId",hospitalId))
				.add(Restrictions.isNull("closeDate"));
				
		
		criteria= criteria.createAlias("csr.patient", "pt");
		if(serviceNo != null && !serviceNo.equals("") && !serviceNo.equals("null"))
		{						
			
			criteria= criteria.add(Restrictions.eq("pt.serviceNo",serviceNo));			
					
		}
			
	
	if(patientName != null && !patientName.equals("") && !patientName.equals("null"))
	{
		String pName = "%"+patientName+"%";
		criteria= criteria.add(Restrictions.like("pt.patientName", pName).ignoreCase());
			
	}
			
	patientObesityList = criteria.list();	
	count=patientObesityList.size();
	
	
	criteria = criteria.setFirstResult(pagingSize * (pageNo - 1));
	criteria = criteria.setMaxResults(pagingSize);
	patientObesityList = criteria.list();	
		
	    
		
		if(patientObesityList != null) {
			map.put("patientList", patientObesityList);
			map.put("count", count);
		}		
		return map;			
	}	
	
/*	@Override
	public Map<String,Object> getObesityDetails(HashMap<String, Object> jsondata){
		long headerId = (Integer)jsondata.get("id");
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String,Object> jsonData = new HashMap<>();
		@SuppressWarnings("unchecked")
		List<OpdObesityHd> obesityList = session.createCriteria(OpdObesityHd.class)
				.add(Restrictions.eq("obesityHdId",headerId))
				.list();
		
		@SuppressWarnings("unchecked")
		List<OpdObesityDt> obesityDetailList = session.createCriteria(OpdObesityDt.class)
				.add(Restrictions.eq("opdObesityHd.obesityHdId",headerId))
				.list();		
		
		jsonData.put("obesityList", obesityList);
		jsonData.put("obesityDetailList", obesityDetailList);
		return jsonData;
	}*/
	
	@Override
	public Map<String,Object> getObesityDetails(HashMap<String, Object> jsondata){
	long headerId = (Integer)jsondata.get("id");
	Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
	Map<String,Object> jsonData = new HashMap<>();
	@SuppressWarnings("unchecked")
	List<OpdObesityHd> obesityList = session.createCriteria(OpdObesityHd.class)
	.add(Restrictions.eq("obesityHdId",headerId))
	.list();
	@SuppressWarnings("unchecked")
	List<OpdObesityDt> obesityDetailList = session.createCriteria(OpdObesityDt.class)
	.add(Restrictions.eq("opdObesityHd.obesityHdId",headerId))
	.list(); 
	jsonData.put("obesityList", obesityList);
	jsonData.put("obesityDetailList", obesityDetailList);
	return jsonData;
	}

	
	@SuppressWarnings("unused")
	@Override
	public List<String> getIdealWeight(Long height, String age,Long genderId) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasIdealWeight> list = null;
		List<String> searchList = null;
		try {	
			String Query ="select MIW.WEIGHT from MAS_IDEAL_WEIGHT MIW INNER  join MAS_RANGE A on  A.RANGE_id =  MIW.AGE_RANGE_ID and '"+age+"' between A.FROM_RANGE and A.TO_range and A.range_Flag='A' AND A.GENDER_ID='"+genderId+"' INNER  join MAS_RANGE H on  H.RANGE_id =  MIW.HEIGHT_RANGE_ID and '"+height+"' between H.FROM_RANGE and H.TO_range and H.range_Flag='H' AND A.GENDER_ID='"+genderId+"' WHERE  MIW.GENDER_ID='"+genderId+"'";
			
			/*
			 * String Query = "select weight as idealWeight from mas_ideal_weight where '" +
			 * age + "' between FROM_AGE and TO_AGE and '" + height +
			 * "' between FROM_HEIGHT and TO_HEIGHT and GENDER_ID = '"+genderId+"'";
			 */
		System.out.println(Query);
		if (Query != null) 
		{
			searchList = session.createSQLQuery(Query).list();
		} 
		else
		{
			System.out.println("No Record Found");
		}
		return searchList;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return searchList;
	}
	
	@Override
	public String saveObesityDetails(HashMap<String, Object> jsondata) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		try {
			OpdObesityHd opdHeader = new OpdObesityHd();
			Map<String, Object> jsonData = new HashMap<>();
			Transaction tx = session.beginTransaction();
			//get input value
			long headerId = (Integer.valueOf((int) jsondata.get("header_id")));
			//long headerId = Long.parseLong(hId) ;
			
			String date = (String) jsondata.get("obesity_date");
			Date obesityDate = CommonUtil.convertStringTypeDateToDateType(date);
			
			String month = ( String.valueOf(jsondata.get("month_name")));
			
			String h = (String.valueOf(jsondata.get("height")));
			BigDecimal height = new BigDecimal(h);
			
			int w = (Integer.valueOf((int)jsondata.get("weight")));
			BigDecimal weight = new BigDecimal(w);
			
			String iw = (String.valueOf(jsondata.get("ideal_weight")));
			BigDecimal idealWeight = new BigDecimal(iw);
			
			String v = (String.valueOf(jsondata.get("variation")));
			BigDecimal variation = new BigDecimal(v);
			//BigDecimal variation = new BigDecimal(String.valueOf(jsondata.get("variation")));
/*			
			long b = (Integer) jsondata.get("bmi");
			BigDecimal bmi = BigDecimal.valueOf(b).movePointLeft(0);*/
			
			//BigDecimal bmi = new BigDecimal(String.valueOf(jsondata.get("bmi")));
			
			String obesityCheck = String.valueOf(jsondata.get("obesity_check"));
			
			opdHeader = (OpdObesityHd)session.get(OpdObesityHd.class, headerId);
			if(opdHeader != null) {
			
			opdHeader.setVaration(variation);
			session.update(opdHeader);
			}
			
			if(obesityCheck.equals("y")) {
				opdHeader = (OpdObesityHd)session.get(OpdObesityHd.class, headerId);
				if(opdHeader != null) {
				
				opdHeader.setCloseDate(obesityDate);
				session.update(opdHeader);
				}
			}
			
			/*OpdObesityHd opdHd = new OpdObesityHd();
			opdHd.setObesityHdId(headerId);*/
			OpdObesityDt dt = new OpdObesityDt();
			// set input value to entity and save 
			//dt.setOpdObesityHd(opdHd);
			dt.setObesityHdId(headerId);
			dt.setObesityDate(obesityDate);
			dt.setMonth(month);
			dt.setHeight(height);
			dt.setWeight(weight);
			dt.setIdealWeight(idealWeight);
			dt.setVariation(variation);
			if(jsondata.get("bmi") != null && !jsondata.get("bmi").equals("")) {
				BigDecimal bmi = new BigDecimal(String.valueOf(jsondata.get("bmi")));
				dt.setBmi(bmi);
			}
			
			session.save(dt);
			tx.commit();
			return "Obesity detail updated Successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return "Error occured while updating obesity detail";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> referredPatientList(HashMap<String, String> jsondata) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String,Object> map = new HashMap<>();
		try {		
		long hospitalId = Long.parseLong(String.valueOf(jsondata.get("hospital_id")));
		int pageNo = Integer.parseInt(String.valueOf(jsondata.get("pageNo")));
		int pagingSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int count=0;
		String mobileNo = String.valueOf(jsondata.get("mobile_no"));
		String patientName = String.valueOf(jsondata.get("patient_name"));
		String serviceNo = String.valueOf(jsondata.get("serviceNo"));
		List<ReferralPatientHd> list = new ArrayList<>();
		
		Criteria criteria = null; 
		
		criteria = session.createCriteria(ReferralPatientHd.class,"rph")
				.add(Restrictions.isNull("intHospitalId"))
				.add(Restrictions.eq("status", "W"))
				.createAlias("rph.masHospital2", "mih")
				.add(Restrictions.eq("mih.hospitalId", hospitalId));
		
		criteria = criteria.createAlias("rph.patient", "patient");
		if(mobileNo != null && !mobileNo.equals("")&& !mobileNo.equals("null")) {			
						
			criteria = criteria.add(Restrictions.eq("patient.mobileNumber", mobileNo.trim()));
					
		}else if(patientName != null && !patientName.equals("") && !patientName.equals("null")) {
						
			String pName = "%"+patientName+"%";
			 criteria = criteria.add(Restrictions.like("patient.patientName", pName).ignoreCase());
					
		}else if(serviceNo != null && !serviceNo.equals("") && !serviceNo.equals("null")) {
							
			  criteria = criteria.add(Restrictions.eq("patient.serviceNo", serviceNo));
				
		}
		
		list = criteria.list();
		count = list.size();
		
		criteria = criteria.setFirstResult(pagingSize * (pageNo - 1));
		criteria = criteria.setMaxResults(pagingSize);
		list = criteria.list();
		
		if(list != null && list.size()>0) {
			map.put("referredPatientList", list);
			map.put("count", count);
		}
		return map;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> referredPatientDetail(HashMap<String, String> jsondata) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String, Object> map = new HashMap<>();
		try {		
		long id = Long.parseLong(String.valueOf(jsondata.get("id")));		
		List<ReferralPatientHd> headerList = session.createCriteria(ReferralPatientHd.class,"rph")				
				.add(Restrictions.eq("id", id)).list();

/*		List<ReferralPatientDt> detailList = (List<ReferralPatientDt>) session.createCriteria(ReferralPatientDt.class)
				.add(Restrictions.eq("referralPatientHd.id", id))
				.add(Restrictions.isNull("masDepartment"))
				.add(Restrictions.eq("mb", "N"))
				.add(Restrictions.eq("admitted", "N"))
				.add(Restrictions.eq("close", "N")).list();*/
		
		List<ReferralPatientDt> detailList = (List<ReferralPatientDt>) session.createCriteria(ReferralPatientDt.class)
				.add(Restrictions.eq("referralPatientHd.id", id))
				.add(Restrictions.isNull("masDepartment"))
				.add(Restrictions.isNull("mb"))
				.add(Restrictions.isNull("admitted"))
				.add(Restrictions.isNull("close")).list();
		
		if(headerList != null) {
			map.put("referredHeaderList", headerList);
		}
		
		if(detailList != null) {
			map.put("referredDetailList", detailList);
		}		

		return map;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateReferralDetail(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String, Object> map = new HashMap<>();
		boolean flag = true;
		try {
		String message = "";
		long headerId = Long.parseLong((String) jsondata.get("header_id"));
		long patientId = Long.parseLong((String) jsondata.get("patient_id"));
		long hospitalId = Long.parseLong(jsondata.get("hospital_id")+"");
		List<HashMap<String, String>> detailList = (List<HashMap<String, String>>)(jsondata.get("detail_list"));
		int listSize = detailList.size();
		ReferralPatientHd referralPatientHd = null;
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) jsondata.get("detail_list");
		Transaction tx;
		tx = session.beginTransaction();
		try {
			//for (int i = 0; i < jsondata.size(); i++) {
				for (HashMap<String, String> data : detailList) {

					long id = Long.parseLong(String.valueOf((String) data.get("id")));
					String finalNote = String.valueOf(((String) data.get("final_note")));
					String notifiable_disease = String.valueOf(((String) data.get("notifiable_disease")));
					if(notifiable_disease.trim().equals("N")) {
						notifiable_disease = null;
					}
					String markMb = String.valueOf(((String) data.get("mark_mb")));
					if(markMb.trim().equals("N")) {
						markMb = null;
					}
					String markAdmitted = String.valueOf(((String) data.get("mark_admitted")));
					if(markAdmitted.trim().equals("N")) {
						markAdmitted = null;
					}
					String close = String.valueOf(((String) data.get("close")));
					if(close.trim().equals("N")) {
						close = null;
					}
					ReferralPatientDt referralPatientDt = (ReferralPatientDt) session.get(ReferralPatientDt.class, id);
					if (referralPatientDt != null) {

						referralPatientDt.setFinalNote(finalNote);
						referralPatientDt.setDisease(notifiable_disease);
						referralPatientDt.setMb(markMb);
						referralPatientDt.setClose(close);
						referralPatientDt.setAdmitted(markAdmitted);
						session.update(referralPatientDt);
						
					} else {
						message = "Patient detail could not be update";
						map.put("msg",message);
						return map;
					}					

				//}

			}				
			tx.commit();
			referralPatientHd = (ReferralPatientHd) session.get(ReferralPatientHd.class, headerId);
			if (referralPatientHd != null) {
				tx = session.beginTransaction();
				referralPatientHd.setStatus("D");
				session.update(referralPatientHd);
				tx.commit();
			}
			
			AdmissionDischarge admissionDischarge = new AdmissionDischarge();
			tx = session.beginTransaction();
			Patient patient = new Patient();
			patient.setPatientId(patientId);
			ReferralPatientHd referralPatientHd2 = new ReferralPatientHd();
			referralPatientHd2.setRefrealHdId(headerId);
			admissionDischarge.setReferralPatientHd(referralPatientHd2);
			admissionDischarge.setPatient(patient);
			MasHospital mh = new MasHospital();
			mh.setHospitalId(hospitalId);
			admissionDischarge.setMasHospital(mh);
			session.save(admissionDischarge);
			tx.commit();
		} catch (Exception ex) {
			flag = false;
			tx.rollback();
			ex.printStackTrace();
			message = "Records updated Failed";
			map.put("msg", message);
		} finally {			
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			if(flag) {
				message = "Records updated";
				map.put("msg", message);
			}else {
				message = "Patient detail could not be update";
				map.put("msg",message);
			}
		
		}
		return map;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getAdmissionDischargeList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response){
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String,Object> map = new HashMap<>();
		try {
		long hospitalId = Long.parseLong(String.valueOf(jsondata.get("hospital_id")));
		String serviceNo = String.valueOf(jsondata.get("service_no"));
		String patientName = String.valueOf(jsondata.get("patient_name"));	
		
		String SpageNo = String.valueOf(jsondata.get("pageNo"));
		int pageNo= Integer.parseInt(SpageNo);
		int pagingSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int count=0;
		
		List<AdmissionDischarge> admissionPendingList = new ArrayList<>();		
		//List<AdmissionDischarge> dischargePendingList = new ArrayList<>();
		Criteria criteria = session.createCriteria(AdmissionDischarge.class, "ad")
							.createAlias("ad.masHospital", "mh")
							.add(Restrictions.eq("mh.hospitalId", hospitalId))
							.add(Restrictions.isNull("ad.dateOfAdmission"))
							.add(Restrictions.isNull("ad.dateOfDischarge"));
		
		criteria = criteria.createAlias("ad.patient", "patient");
		if (serviceNo != null && !serviceNo.isEmpty() && !serviceNo.equals("null")) {
			
				criteria = 	criteria.add(Restrictions.eq("patient.serviceNo", serviceNo));				

		} else if (patientName != null && !patientName.isEmpty() && !patientName.equals("null")) {
					String pName = "%"+patientName+"%";
					criteria = criteria.add(Restrictions.like("patient.patientName", pName).ignoreCase());
		} 			
		
		admissionPendingList = criteria.list();
		count = admissionPendingList.size();
		
		criteria = criteria.setFirstResult(pagingSize * (pageNo - 1));
		criteria = criteria.setMaxResults(pagingSize);
		admissionPendingList = criteria.list();
		
		if(admissionPendingList != null) {
			map.put("admissionPendingList", admissionPendingList);
			map.put("count1", count);
		}
		return map;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getPendingDischargeList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response){
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String,Object> map = new HashMap<>();
		try {
		long hospitalId = Long.parseLong(String.valueOf(jsondata.get("hospital_id")));
		String serviceNo = String.valueOf(jsondata.get("service_no"));
		String patientName = String.valueOf(jsondata.get("patient_name"));	
		
		String SpageNo = String.valueOf(jsondata.get("pageNo"));
		int pageNo= Integer.parseInt(SpageNo);
		int pagingSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int count=0;
		
		List<AdmissionDischarge> dischargePendingList = new ArrayList<>();		
		Criteria criteria = session.createCriteria(AdmissionDischarge.class, "ad")
				.add(Restrictions.isNotNull("ad.dateOfAdmission")).add(Restrictions.isNull("ad.dateOfDischarge"))
				.createAlias("ad.masHospital", "mih")
				.add(Restrictions.eq("mih.hospitalId", hospitalId));
		
		criteria = criteria.createAlias("ad.patient", "patient");
		if (serviceNo != null && !serviceNo.isEmpty() && !serviceNo.equals("null")) {
		
				criteria = 	criteria.add(Restrictions.eq("patient.serviceNo", serviceNo));				

		} else if (patientName != null && !patientName.isEmpty() && !patientName.equals("null")) {
					String pName = "%"+patientName+"%";
					criteria = criteria.add(Restrictions.like("patient.patientName", pName).ignoreCase());
					
		} 			
		
		dischargePendingList = criteria.list();
		count = dischargePendingList.size();
		System.out.println("count is "+count);
		criteria = criteria.setFirstResult(pagingSize * (pageNo - 1));
		criteria = criteria.setMaxResults(pagingSize);
		dischargePendingList = criteria.list();
		
		if(dischargePendingList != null) {
			map.put("dischargePendingList",dischargePendingList);
			map.put("count",count);
		}
		return map;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> admissionAndDischarge(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response) {
		
		Map<String,Object> map = new HashMap<>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		//try {
		long id = Long.parseLong(jsondata.get("id"));
		List<AdmissionDischarge> patientDetail = session.createCriteria(AdmissionDischarge.class)
				.add(Restrictions.eq("id", id)).list();
		
		List<MasDisposal> disposalList = session.createCriteria(MasDisposal.class).add(Restrictions.isNotNull("disposalCode")).list();
		
		if(patientDetail != null) {
			map.put("patientDetail", patientDetail);
		}
		
		if(disposalList != null) {
			map.put("disposalList", disposalList);
		}
		
		return map;
	/*	}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;*/
	}
	
	@Override
	public String savePatientAdmission(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response) {
		String msg = "";
		try {
		ReferralPatientHd referHeaderId = null;
		if(jsondata.containsKey("header_id")) {
			if(jsondata.get("header_id") != null && !jsondata.get("header_id").equals("")) {
				long headerId = Long.parseLong(jsondata.get("header_id"));
				referHeaderId = new ReferralPatientHd();
				referHeaderId.setRefrealHdId(headerId);
			}			
		}
		
		MasHospital masHospital= null;
		if(jsondata.containsKey("hospital_id")) {
			if(jsondata.get("hospital_id") != null && !jsondata.get("hospital_id").equals("")) {
				long hospitalId = Long.parseLong(jsondata.get("hospital_id"));
				masHospital = new MasHospital();
				masHospital.setHospitalId(hospitalId);
			}			
		}
		
		long admissionId = Long.parseLong(jsondata.get("admission_id"));
		long patientId = Long.parseLong(String.valueOf(jsondata.get("patient_id")));
		Date admissionDate = null;
		if(jsondata.get("admission_date") != null && !jsondata.get("admission_date").isEmpty()) {
			admissionDate = HMSUtil.convertStringDateToUtilDate(String.valueOf(jsondata.get("admission_date")), "yyyy-MM-dd");
			msg = "Patient admitted successfully";
		}
		String wardNo = null;
		if(jsondata.get("ward") != null && !jsondata.get("ward").equals("")) {
			wardNo = String.valueOf(jsondata.get("ward"));
			// wardNo = new BigDecimal(ward);
		}		
		
		String noOfDays = String.valueOf(jsondata.get("no_of_days"));
		
		String remarks = String.valueOf(jsondata.get("remark"));
		Date dischargeDate = null;
		if(jsondata.get("discharge_date") != null && !jsondata.get("discharge_date").isEmpty()) {
			dischargeDate = HMSUtil.convertStringDateToUtilDate((String.valueOf(jsondata.get("discharge_date"))),"yyyy-MM-dd");
			msg = "Patient discharged successfully";
		}
		String admissionNo = null;
		if(jsondata.get("admission_no") != null && !jsondata.get("admission_no").isEmpty()) {
			admissionNo = String.valueOf(jsondata.get("admission_no"));
		}				
		String flag = jsondata.get("flag");
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Transaction tx = session.beginTransaction();
		AdmissionDischarge admissionDischarge = null;
		admissionDischarge =  (AdmissionDischarge) session.get(AdmissionDischarge.class, admissionId);
		if(admissionDischarge != null) {          
			admissionDischarge.setAdmissionNo(admissionNo);
			admissionDischarge.setDateOfAdmission(admissionDate);
			admissionDischarge.setDateOfDischarge(dischargeDate);
			/*MasDisposal masDisposal = new MasDisposal();
			masDisposal.setDisposalId(disposalId);
			admissionDischarge.setMasDisposal(masDisposal);
			admissionDischarge.setNoOfDays(days);*/
			Patient patient = new Patient();
			patient.setPatientId(patientId);
			admissionDischarge.setPatient(patient);
			/*ReferralPatientHd referHeaderId = new ReferralPatientHd();
			referHeaderId.setId(headerId);*/
			admissionDischarge.setReferralPatientHd(referHeaderId);
			admissionDischarge.setRemarks(remarks);
			admissionDischarge.setWardNo(wardNo);
			admissionDischarge.setMasHospital(masHospital);
			session.update(admissionDischarge);
		}else {
			AdmissionDischarge admission = new AdmissionDischarge();
			admission.setAdmissionNo(admissionNo);
			admission.setDateOfAdmission(admissionDate);
			/*MasDisposal masDisposal = new MasDisposal();
			masDisposal.setDisposalId(disposalId);
			admission.setMasDisposal(masDisposal);
			admission.setNoOfDays(days);*/
			Patient patient = new Patient();
			patient.setPatientId(patientId);
			admission.setPatient(patient);
			/*ReferralPatientHd referHeaderId = new ReferralPatientHd();
			referHeaderId.setId(headerId);*/
			admission.setReferralPatientHd(referHeaderId);
			admission.setRemarks(remarks);
			admission.setWardNo(wardNo);	
			admissionDischarge.setMasHospital(masHospital);
			session.save(admission);
		}
		
		tx.commit();
		System.out.println("msg is "+msg);
		return msg;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return "Error While Saving Admission";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getServiceWisePatientList(HashMap<String, String> jsondata, HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		try {
		String serviceNo = String.valueOf(jsondata.get("service_no"));		
		List<MasDisposal> disposalList = session.createCriteria(MasDisposal.class).add(Restrictions.isNotNull("disposalCode")).list();
		if(disposalList != null) {
			map.put("disposalList", disposalList);
		}
		List<Patient> patientList = session.createCriteria(Patient.class).add(Restrictions.eq("serviceNo", serviceNo)).list();
		if(patientList != null) {
			map.put("patientList", patientList);
		}else {
			map.put("patientList", new JSONArray());
		}
		return map;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;		
	}
	
	@Override
	public String saveNewAdmission(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Transaction tx = session.beginTransaction();
		try {
			long patientId = Long.parseLong(jsondata.get("patient_id"));
			Patient patient = new Patient();
			patient.setPatientId(patientId);
			long hospitalId = Long.parseLong(jsondata.get("hospital_id"));
			MasHospital masHospital = new MasHospital();
			masHospital.setHospitalId(hospitalId);
			Date dateOfAdmission = HMSUtil.convertStringDateToUtilDate(jsondata.get("admission_date"),
					"yyyy-MM-dd");
			String admissionNo = String.valueOf(jsondata.get("admission_no"));
			String w = jsondata.get("ward");			
			//BigDecimal ward = new BigDecimal(w);
			//long disposalId = Long.parseLong(jsondata.get("disposal"));
			//MasDisposal masDisposal = new MasDisposal();
			//masDisposal.setDisposalId(disposalId);
			String remarks = jsondata.get("remarks");
			AdmissionDischarge admissionDischarge = new AdmissionDischarge();
			admissionDischarge.setPatient(patient);
			admissionDischarge.setMasHospital(masHospital);
			admissionDischarge.setAdmissionNo(admissionNo);
			admissionDischarge.setDateOfAdmission(dateOfAdmission);
			//admissionDischarge.setMasDisposal(masDisposal);
			admissionDischarge.setRemarks(remarks);
			admissionDischarge.setWardNo(w);
			session.save(admissionDischarge);
			return "Admission done Successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {			
			tx.commit();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return "Error while saving admission";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> nursingCareWaitingList(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String,Object> map = new HashMap<>();
		try {
		long hospital_id = Long.parseLong(jsondata.get("hospital_id"));
		Date fromDate = null;
		if(jsondata.get("from_Date") != null && !jsondata.get("from_Date").equals("")) {
			fromDate = HMSUtil.convertStringDateToUtilDate(jsondata.get("from_Date"),"yyyy-MM-dd");
		}
		Date toDate = null;
		if(jsondata.get("from_Date") != null && !jsondata.get("from_Date").equals("")) {
			toDate = HMSUtil.convertStringDateToUtilDate(jsondata.get("to_date"),"yyyy-MM-dd");
		}
		
		String SpageNo = String.valueOf(jsondata.get("pageNo"));
		int pageNo= Integer.parseInt(SpageNo);
		int pagingSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int count=0;
		List<ProcedureHd> ncWaitingList = new ArrayList<>();
		Criteria criteria = session.createCriteria(ProcedureHd.class,"phd")
					.createAlias("phd.masHospital", "mh")
					.createAlias("phd.procedureDts", "pDt")
					.add(Restrictions.eq("mh.hospitalId", hospital_id))
						.add(Restrictions.eq("status", "N"))
						.add(Restrictions.eq("procedureType", "N"))
						.createAlias("phd.opdPatientDetails", "opd")
						.addOrder(Order.desc("opd.opdDate"));
			
		if(fromDate != null && !fromDate.equals("") ) {
			criteria = criteria.add(Restrictions.isNull("pDt.status"))
					.add(Restrictions.ge("pDt.appointmentDate", fromDate));
		}
		if(toDate != null && !toDate.equals("") ) {
			criteria = criteria.add(Restrictions.isNull("pDt.status"))
					.add(Restrictions.le("pDt.appointmentDate", toDate));
		}
		
		//map.put("nursingCareList", ncWaitingList);
		ncWaitingList = criteria.list();
		count = ncWaitingList.size();
		map.put("count", count);
		
		criteria = criteria.setFirstResult(pagingSize * (pageNo - 1));
		criteria = criteria.setMaxResults(pagingSize);
		ncWaitingList = criteria.list();
		map.put("nursingCareList", ncWaitingList);
		return map;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getNursingCareDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response){
		
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String,Object> map = new HashMap<>();
		long headerId = Long.parseLong(jsondata.get("header_id"));
		List<ProcedureDt> list = session.createCriteria(ProcedureDt.class)
				.add(Restrictions.eq("procedureHd.procedureHdId", headerId))
				.list();
			    	//.setResultTransformer(Transformers.aliasToBean(ProcedureDt.class)); 
			    	//	.setResultTransformer(Transformers.aliasToBean(ProcedureDt.class));
			  
		
		map.put("detailList", list);		
		return map;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getProcedureDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String,Object> map = new HashMap<>();
		//Transaction tx=null;
		//tx=session.beginTransaction();
	try {
		
		String[] ids = jsondata.get("header_id").split("\\.");
		List<ProcedureDt> list =null;
		long header_id = Long.parseLong(ids[0]);	
		long procedure_id = Long.parseLong(ids[1]);
		list = session.createCriteria(ProcedureDt.class)
				.add(Restrictions.eq("procedureId", procedure_id))
				.add(Restrictions.eq("procedureHdId", header_id))				
				.list();
			map.put("detailList", list);
		}
		catch(Exception e) {			
			e.printStackTrace();
		}finally {
			session.clear();
			session.flush();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@SuppressWarnings({ "finally", "unchecked" })
	@Override
	public String saveProcedureDetail(Map<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		String msg = "";
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Transaction tx = session.beginTransaction();
		String result = "";
		try {
			/*	 Map<String, Object> complete = (Map<String, Object>)jsondata.get(0);
				 Map<String, Object> pending = (Map<String, Object>)jsondata.get(1);*/
				 
				 //info for updates
				 long id = Long.parseLong((String)jsondata.get("id"));
				 Date procedureDate = HMSUtil.convertStringDateToUtilDate((String)jsondata.get("procedure_date"), "yyyy-MM-dd");
				 
				 String nursingRemarks = (String)jsondata.get("nursing_remarks");
				 
				 //info for saving
				 Date appointmentDate = HMSUtil.convertStringDateToUtilDate((String)jsondata.get("appointment_date"),"yyyy-MM-dd");
				 long headerId = Long.parseLong((String)jsondata.get("header_id"));
				 long procedureId = Long.parseLong((String)jsondata.get("procedure_id"));
				 long frequencyId = Long.parseLong((String)jsondata.get("frequency_id"));
				 long noOfDays = Long.parseLong((String)jsondata.get("no_of_days"));
				 String final_status = (String)jsondata.get("final_status");
				 String opRemarks = (String)jsondata.get("op_remarks");
				 Timestamp timestamp = new Timestamp(System.currentTimeMillis());	 
				 ProcedureDt pDt = (ProcedureDt)session.get(ProcedureDt.class, id);
				if(pDt != null) {
					Timestamp ts=new Timestamp(procedureDate.getTime());  
					pDt.setProcedureDate(ts);
					pDt.setNursingRemark(nursingRemarks);
					pDt.setStatus("Y");
					session.update(pDt);
					tx.commit();
					tx = null;
					msg = "Records Saved Successfully";
				}
				
				if(final_status.equals("Y")) {
					tx = session.beginTransaction();
					session.createSQLQuery("update procedure_dt set FINAL_PROCEDURE_STATUS = 'Y' where PROCEDURE_HD_ID = "+headerId+" and PROCEDURE_ID = "+procedureId+"").executeUpdate();
					tx.commit();
					tx = null;
					return msg;
				}
				
				List<ProcedureDt> procedureDtList = session.createCriteria(ProcedureDt.class,"pDt")
						.add(Restrictions.eq("procedureHdId", headerId))
						.add(Restrictions.eq("status", "N")).list();
				
				if(procedureDtList.isEmpty()) {
					tx = session.beginTransaction();
					ProcedureHd procedureHd = null;
					procedureHd = (ProcedureHd)session.get(ProcedureHd.class, headerId);
					if(procedureHd != null) {
						procedureHd.setStatus("Y");
						session.update(procedureHd);
						tx.commit();
						tx = null;
					}
				}
				
				tx = session.beginTransaction();
				ProcedureDt dt = new ProcedureDt();
				Timestamp ts=new Timestamp(appointmentDate.getTime());  
				dt.setAppointmentDate(ts);
				ProcedureHd hd = new ProcedureHd();
				hd.setProcedureHdId(headerId);
				//dt.setProcedureHd(hd);
				dt.setProcedureHdId(headerId);
				MasNursingCare ms = new MasNursingCare();
				ms.setNursingId(procedureId);
				//dt.setMasNursingCare(ms);
				dt.setProcedureId(procedureId);
				MasFrequency msf = new MasFrequency();
				msf.setFrequencyId(frequencyId);
//				dt.setMasFrequency(msf);
				dt.setFrequencyId(frequencyId);
				dt.setNoOfDays(noOfDays);
				dt.setRemarks(opRemarks);
				session.save(dt);				
				tx.commit();
				tx = null;
				msg = "Records Saved Successfully";
				return msg;
		}catch(Exception ex) {
			msg = "Error While Saving Records";
			ex.printStackTrace();
		}finally {			
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			return msg;
		}		
		
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> physioTherapyWaitingList(Map<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String,Object> map = new HashMap<>();
		long hospital_id = Long.parseLong(jsondata.get("hospital_id"));
		String serviceNo = jsondata.get("service_no");
		String SpageNo = String.valueOf(jsondata.get("pageNo"));
		int pageNo= Integer.parseInt(SpageNo);
		int pagingSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int count=0;
		List<ProcedureHd> ptWaitingList = new ArrayList<>();
		Criteria criteria = session.createCriteria(ProcedureHd.class,"phd")
						.createAlias("phd.masHospital", "mh")						
						.add(Restrictions.eq("mh.hospitalId", hospital_id))
						.add(Restrictions.eq("status", "N"))
						.add(Restrictions.eq("procedureType", "P"))
						.createAlias("phd.opdPatientDetails", "opd")
						.addOrder(Order.desc("opd.opdDate"));
								
		//map.put("physioTherapyWaitingList", ptWaitingList);
		
		if(serviceNo != null && !serviceNo.equals("") && !serviceNo.equals(null)) {
			criteria = criteria.createAlias("phd.patient", "pt")
						.add(Restrictions.eq("pt.serviceNo", serviceNo));
		}
		ptWaitingList = criteria.list();
		count = ptWaitingList.size();	
		
		criteria = criteria.setFirstResult(pagingSize * (pageNo - 1));
		criteria = criteria.setMaxResults(pagingSize);
		ptWaitingList = criteria.list();
		map.put("physioTherapyWaitingList", ptWaitingList);
		map.put("count", count);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getphysioTherapyDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response){
		
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String,Object> map = new HashMap<>();
		long headerId = Long.parseLong(jsondata.get("header_id"));
		List<ProcedureDt> list = session.createCriteria(ProcedureDt.class,"pt")
				.createAlias("pt.procedureHd", "pHd")
				.createAlias("pt.masNursingCare", "mnc")
				.add(Restrictions.eq("pHd.procedureHdId", headerId))
				.list();			  
		
		map.put("detailList", list);		
		return map;		
	}
	@Override
	public Map<String,Object> getOpdReportsDetailsbyServiceNo(HashMap<String,Object> jsonData) {
		
		List<Patient> list =null;
		String serviceNo = (String) jsonData.get("serviceNo");
		Map<String,Object> map = new HashMap<String, Object>();
		try{
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(Patient.class)
				      .add(Restrictions.eq("serviceNo", serviceNo));
		 list= cr.list();
		 map.put("list", list);
		 getHibernateUtils.getHibernateUtlis().CloseConnection();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String,Object> getOpdReportsDetailsbyPatinetId(HashMap<String,Object> jsonData) {
		
		List<Visit> list =null;
		//long visitId = Long.parseLong((String) jsonData.get("visitId"));
		long patientId = Long.parseLong((String) jsonData.get("patientId"));
		Map<String,Object> map = new HashMap<String, Object>();
		try{
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(Visit.class)
				      .add(Restrictions.eq("patientId", patientId));
		 list= cr.list();
		 map.put("list", list);
		 getHibernateUtils.getHibernateUtlis().CloseConnection();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}

public ReferralPatientHd getReferralPatientHdByExeHosAndOpdPd(Long exeHoss,Long opdPdId) {
	Session session =null;
	ReferralPatientHd referralPatientHd=null;
	try {
		session=getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(ReferralPatientHd.class)
				.add(Restrictions.eq("extHospitalId", exeHoss)).add(Restrictions.eq("opdPatientDetailsId", opdPdId));
		List<ReferralPatientHd>listReferralPatientHd=cr.list();
		if(CollectionUtils.isNotEmpty(listReferralPatientHd)) {
			referralPatientHd=listReferralPatientHd.get(0);
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	} 
	return referralPatientHd;
}

public DgOrderhd getOrderDatebyInvestigation(Date orderdate,Long visitId) {
	Session session =null;
	DgOrderhd investigationHeaderHd=null;
	try {
		session=getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(DgOrderhd.class)
				.add(Restrictions.eq("orderDate", orderdate)).add(Restrictions.eq("visitId", visitId));
		List<DgOrderhd>listInvHd=cr.list();
		if(CollectionUtils.isNotEmpty(listInvHd)) {
			investigationHeaderHd=listInvHd.get(0);
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	} 
	return investigationHeaderHd;
}

//////////////////////code by dhiraj /////////////////


@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
@Override
public Map<String, Object> minorSurgeryWaitingList(HashMap<String, String> jsondata, HttpServletRequest request,
HttpServletResponse response) {
Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
Map<String, Object> map = new HashMap<>();
try {
long hospital_id = Long.parseLong(jsondata.get("hospital_id"));
Date fromDate = null;
String serviceNo = jsondata.get("serviceNo");
if (jsondata.get("from_date") != null && !jsondata.get("from_date").equals("")) {
fromDate = HMSUtil.convertStringDateToUtilDate(jsondata.get("from_Date"), "yyyy-MM-dd");
}
Date toDate = null;
if (jsondata.get("to_date") != null && !jsondata.get("to_date").equals("")) {
toDate = HMSUtil.convertStringDateToUtilDate(jsondata.get("to_date"), "yyyy-MM-dd");
}

String SpageNo = String.valueOf(jsondata.get("pageNo"));
int pageNo = Integer.parseInt(SpageNo);
int pagingSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
int count = 0;

List<ProcedureHd> msWaitingList = new ArrayList<>();
Criteria criteria = session.createCriteria(ProcedureHd.class, "phd").createAlias("phd.masHospital", "mh")
.createAlias("phd.procedureDts", "pDt").createAlias("phd.patient", "pt")
.add(Restrictions.eq("mh.hospitalId", hospital_id)).add(Restrictions.eq("procedureType", "M"))
.add(Restrictions.eq("phd.status", "N")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

if (fromDate != null && !fromDate.equals("")) {
criteria = criteria.add(Restrictions.isNull("pDt.status"))
	.add(Restrictions.ge("pDt.appointmentDate", fromDate));
}
if (toDate != null && !toDate.equals("")) {
criteria = criteria.add(Restrictions.isNull("pDt.status"))
	.add(Restrictions.le("pDt.appointmentDate", toDate));
}

if (serviceNo != null && !serviceNo.equals("")) {

criteria = criteria.add(Restrictions.eq("pt.serviceNo", serviceNo));

}
//map.put("nursingCareList", ncWaitingList);
msWaitingList = criteria.list();
count = msWaitingList.size();
map.put("count", count);

criteria = criteria.setFirstResult(pagingSize * (pageNo - 1));
criteria = criteria.setMaxResults(pagingSize);
msWaitingList = criteria.list();
map.put("minorSurgeryWaitingList", msWaitingList);
return map;
} catch (Exception ex) {
ex.printStackTrace();
} finally {
getHibernateUtils.getHibernateUtlis().CloseConnection();
}
return map;
}

@SuppressWarnings("unchecked")
@Override
public Map<String, Object> getMinorSurgeryDetail(HashMap<String, String> jsondata, HttpServletRequest request,
HttpServletResponse response) {

Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
Map<String, Object> map = new HashMap<>();
long headerId = Long.parseLong(jsondata.get("header_id"));		

List<ProcedureDt> list = session.createCriteria(ProcedureDt.class)
.add(Restrictions.eq("procedureHd.procedureHdId", headerId)).list();


		//.add(Restrictions.eq("procedureHd.procedureHdId", headerId)).list();
//.setResultTransformer(Transformers.aliasToBean(ProcedureDt.class)); 
//	.setResultTransformer(Transformers.aliasToBean(ProcedureDt.class));

map.put("detailList", list);
return map;

}

@SuppressWarnings("unchecked")
@Override
public List<MasAnesthesia> getAnesthesiaList() {
List<MasAnesthesia> anesthesiaList = new ArrayList<MasAnesthesia>();
try {
Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
Criteria criteria = session.createCriteria(MasAnesthesia.class);
ProjectionList projectionList = Projections.projectionList();
projectionList.add(Projections.property("anesthesiaId").as("anesthesiaId"));
projectionList.add(Projections.property("anesthesiaName").as("anesthesiaName"));
criteria.setProjection(projectionList);
criteria.add(Restrictions.eq("status", "Y"));
anesthesiaList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasAnesthesia.class))
.list();

} catch (Exception e) {
e.printStackTrace();
} finally {
getHibernateUtils.getHibernateUtlis().CloseConnection();
}

return anesthesiaList;
}

@Override
public String saveMinorSurgery(HashMap<String, Object> jsondata) {

Transaction tx = null;
Session session = null;
ProcedureDt refNursingDt = null;
String result="";
try {	

session = getHibernateUtils.getHibernateUtlis().OpenSession();		
if (jsondata.get("listofMinorDT") != null) {
@SuppressWarnings("unchecked")
List<HashMap<String, Object>> listNursing =  (List<HashMap<String, Object>>) jsondata.get("listofMinorDT");

if (listNursing != null ) {
for (HashMap<String, Object> map : listNursing) {
	
	if (map.get("msId").toString() !=null && !map.get("msId").toString().equals("")  ) {
		String value1 = map.get("msId").toString();
		Criteria cr=session.createCriteria(ProcedureDt.class).add(Restrictions.eq("procedureDtId",Long.parseLong(value1)));
		List<ProcedureDt> dtlist=cr.list();
		if(dtlist !=null && dtlist.size()>0) {
			
			refNursingDt=dtlist.get(0);
			Long anthesiaId=refNursingDt.getMasAnesthesia().getAnesthesiaId();
			
			 if (map.get("anethesiaType").toString() != null && !map.get("anethesiaType").toString().equals("") ) {
				
				 String value3 = map.get("anethesiaType").toString();
				 MasAnesthesia mas = new MasAnesthesia();
				 mas.setAnesthesiaId(Long.parseLong(value3));
				  refNursingDt.setMasAnesthesia(mas);
				 
				 }
			 	
			 if(map.get("userId").toString() !=null &&  !map.get("userId").toString().equals("")) {
				 Long userId=Long.parseLong(map.get("userId").toString());
				 Users user=new Users();
				 user.setUserId(userId);
				 refNursingDt.setUsers(user);
				 
			 }
			 
			if (map.get("remarks").toString() != null && !(map.get("remarks").toString().equals(""))) {
				String value4 = map.get("remarks").toString();
				refNursingDt.setNursingRemark(value4);
			}
			
			if(anthesiaId !=null && !anthesiaId.toString().equals("")) {
				refNursingDt.setStatus("Y");
				}
		}
		else {
			refNursingDt=new ProcedureDt();
			
			if (map.get("prescribedById").toString() != null && !map.get("prescribedById").toString().equals("") ) {
				String value2 = map.get("prescribedById").toString();
				refNursingDt.setProcedureDtId(Long.parseLong(value2));
			}
			
			 if (map.get("anethesiaType").toString() != null && !map.get("anethesiaType").toString().equals("") ) {
				 String value3 = map.get("anethesiaType").toString();
				 MasAnesthesia mas = new MasAnesthesia();
				 mas.setAnesthesiaId(Long.parseLong(value3));
				 refNursingDt.setMasAnesthesia(mas); 
				 }
			 
			if (map.get("remarks").toString() != null && !(map.get("remarks").toString().equals(""))) {
				String value4 = map.get("remarks").toString();
				refNursingDt.setRemarks(value4);
			}
			
			if (map.get("headerId").toString() != null && !map.get("headerId").toString().equals("")) {
				String value5 = map.get("headerId").toString();
				refNursingDt.setProcedureHdId(Long.parseLong(value5));
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			refNursingDt.setAppointmentDate(timestamp);
			refNursingDt.setProcedureDate(timestamp);								
				//refNursingDt.setStatus("N");
			
			
		}
		
	}
	
	
	Long headerId= Long.parseLong(map.get("headerId").toString());
	@SuppressWarnings("unchecked")
	List<ProcedureDt> procedureDtList = session.createCriteria(ProcedureDt.class, "pDt")
			.add(Restrictions.eq("procedureHdId", headerId)).add(Restrictions.eq("status", "N")).list();

	if (procedureDtList.isEmpty()) {
		tx = session.beginTransaction();
		ProcedureHd procedureHd = null;
		procedureHd = (ProcedureHd) session.get(ProcedureHd.class, headerId);
		if (procedureHd != null) {
			procedureHd.setStatus("Y");
			session.update(procedureHd);
			tx.commit();
			tx = null;
		}
	}
	

}



	
		tx = session.beginTransaction();
		session.saveOrUpdate(refNursingDt);							
		result="success";
	}
}


} catch (Exception ex) {

ex.printStackTrace();
tx.rollback();
result="error";
return result;
} finally {

tx.commit();
getHibernateUtils.getHibernateUtlis().CloseConnection();
}

return result;

}




@Override
public String deleteMinorSurgery(HashMap<String, Object> jsondata) {
Transaction tx = null;
Session session = null;		
String result = "";
try {
Long procedureDtId=Long.parseLong(jsondata.get("procedureDtId").toString());
session = getHibernateUtils.getHibernateUtlis().OpenSession();
if (procedureDtId != null && !procedureDtId.equals("") ) {
ProcedureDt pdt=(ProcedureDt) session.createCriteria(ProcedureDt.class)
	.add(Restrictions.eq("procedureDtId", procedureDtId)).uniqueResult();
tx = session.beginTransaction();
session.delete(pdt);
tx.commit();
result = "success";
}

} catch (Exception ex) {

ex.printStackTrace();
tx.rollback();
result = "error";
return result;
} finally {
tx.commit();
getHibernateUtils.getHibernateUtlis().CloseConnection();
}

return result;

}




}
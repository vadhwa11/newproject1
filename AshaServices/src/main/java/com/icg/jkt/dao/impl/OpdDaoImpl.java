package com.icg.jkt.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icg.jkt.dao.OpdDao;
import com.icg.jkt.entity.AdmissionDischarge;
import com.icg.jkt.entity.DgOrderdt;
import com.icg.jkt.entity.DgOrderhd;
import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasDisposal;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasHospital;
import com.icg.jkt.entity.MasIdealWeight;
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
			//Session session=sessionFactory.getCurrentSession(); 
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(OpdPatientDetail.class);
			cr.add(Restrictions.eq("visitId", ob.getVisitId()));
			OpdPatientDetail list = (OpdPatientDetail) cr.uniqueResult();
			Transaction t = session.beginTransaction();
			if(list!=null)
			{
				list.setPollor(ob.getPollor());
				list.setEdema(ob.getEdema());
				list.setCyanosis(ob.getCyanosis());
				list.setJauindice(ob.getJauindice());
				list.setLymphNode(ob.getLymphNode());
				list.setClubbing(ob.getClubbing());
				list.setThyroid(ob.getThyroid());
				list.setTremors(ob.getTremors());
				list.setGeneralOther(ob.getGeneralOther());
				list.setGeneral(ob.getGeneral());
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
				list.setBp(ob.getBp());
				list.setPulse(ob.getPulse());
				list.setSpo2(ob.getSpo2());
				list.setBmi(ob.getBmi());
				list.setRr(ob.getRr());
								
				session.update(list);
				Long visitId=ob.getVisitId();
				 if(visitId!=null)
				 {
					 Visit visit = (Visit)session.get(Visit.class, visitId);
					 if(visit != null){						
						 visit.setVisitStatus("p");
						 session.update(visit);
					 }
				 }
				t.commit();
				Result="200";
			
			}
			else
			{
				 session.save(ob);
				 Long visitId=ob.getVisitId();
				 if(visitId!=null)
				 {
					 Visit visit = (Visit)session.get(Visit.class, visitId);
					 if(visit != null){						
						 visit.setVisitStatus("p");
						 session.update(visit);
					 }
				 }
				
				t.commit();
				Result="200";	
			}
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			Result = e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return Result;
	}
	
/*	@Override
	public String updateVisit(Visit v) {

		String Result = null;

		try {
			//Session session=sessionFactory.getCurrentSession(); 
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(Visit.class);
			cr.add(Restrictions.eq("visitId", v.getVisitId()));
			Visit list = (Visit) cr.uniqueResult();
			Transaction t = session.beginTransaction();
			if(list!=null)
			{
				list.setVisitStatus("c");
				list.setVaration(oohd.getVaration());
				list.setHospitalId(oohd.getHospitalId());
				list.setLastChgDate(oohd.getLastChgDate());
				list.setIniDate(oohd.getIniDate());
												
				session.update(list);
				t.commit();
				Result="200";
			
			}
			else
			{
				session.save(oohd);
				t.commit();
				Result="200";	
			}
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			Result = e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return Result;
	}*/
	
	
	@Override
	public String opdObsisty(OpdObesityHd oohd) {

		String Result = null;
		Transaction t= null;   
		try {
			//Session session=sessionFactory.getCurrentSession(); 
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(OpdObesityHd.class);
			cr.add(Restrictions.eq("visitId", oohd.getVisitId()));
			OpdObesityHd list = (OpdObesityHd) cr.uniqueResult();
			t = session.beginTransaction();
			if(list!=null)
			{
				list.setPatientId(oohd.getPatientId());
				list.setVaration(oohd.getVaration());
				list.setHospitalId(oohd.getHospitalId());
				list.setLastChgDate(oohd.getLastChgDate());
				list.setIniDate(oohd.getIniDate());
												
				session.update(list);
				t.commit();
				Result="200";
			
			}
			else
			{
				session.save(oohd);
				t.commit();
				Result="200";	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			
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
	public List<Visit> getVisit() {
		List<Visit> list =null;
		try{
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(Visit.class);
		 list= cr.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	/////////////////////// for visit,patient and OpdPatinetDetails for get Visit/////////////////////
	
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
	
	////////////////////save opd details on opdPatientHistory ////////////////////
	@Override
	public String opdPatinetHistory(OpdPatientHistory ob) {

		String Result = null;
		Transaction t =null;

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(OpdPatientHistory.class);
			cr.add(Restrictions.eq("visitId", ob.getVisitId()));
			OpdPatientHistory list=(OpdPatientHistory) cr.uniqueResult();
			t = session.beginTransaction();
			if(list!=null)
			{
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
				
				//list.setFamilyPresentHistory(ob.getFamilyPresentHistory());
				session.update(list);
				t.commit();
				Result="200";
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			}else
			{
				session.save(ob);
				t.commit();
				Result="200";
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
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
	        
	//////////////////// save opd details on opdTemplate	//////////////////// ////////////////////
	@Override
	public String opdTemplate(OpdTemplate ob,OpdTemplateInvestigation opdinv) {

		String Result = null;
		Transaction t =null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(OpdPatientHistory.class);
			cr.add(Restrictions.eq("visitId", ob.getTemplateId()));
			OpdPatientHistory list = (OpdPatientHistory) cr.uniqueResult();
			t = session.beginTransaction();
			/*if (list != null) {
				

				// list.setFamilyPresentHistory(ob.getFamilyPresentHistory());
				session.update(list);
				t.commit();
				Result = "200";
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			} else {*/
			   //session.save(ob);
			   Serializable id = session.save(ob);
			   
			   //OpdTemplateInvestigation opdtInv= new  OpdTemplateInvestigation();
			   opdinv.setTemplateId(Long.valueOf(id.toString()));
			   
			   System.out.println("hi this is id"+id);
			   
			   Serializable id2 =session.save(opdinv);
			   
			   System.out.println("hi this is id2====="+id2);
				//OpdTemplate ot = (OpdTemplate) session.get(OpdTemplate.class, templateId);
				//System.out.println("tempalte id is "+ot.toString());
				t.commit();
				Result = "200";
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			//}
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
	
		String result=null;
		Transaction t=null;
		Session session=null;
		try{
			 session= getHibernateUtils.getHibernateUtlis().OpenSession();
			 t=session.beginTransaction();
			 Serializable id=session.save(opdTemp);
			
			 for(OpdTemplateInvestigation single:opdInvestigationList){
				
				 single.setTemplateId(Long.valueOf(id.toString()));
				 session.save(single); 
				 
			 }

		t.commit();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		}catch(Exception ex){
			t.rollback();
			
			return "500";
			 
		}
		
		return "200";
	}
	
	@Override
	public String saveOpdInvestigation(DgOrderhd orderhd, List<DgOrderdt> dgorderdt) {
	
		String result=null;
		Transaction t=null;
		Session session=null;
		try{
			 session= getHibernateUtils.getHibernateUtlis().OpenSession();
			 t=session.beginTransaction();
			 Serializable id=session.save(orderhd);
			
			 for(DgOrderdt single:dgorderdt){
				
				 single.setOrderhdId(Long.valueOf(id.toString()));
				 session.save(single); 
				 
			 }

		t.commit();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			
			return "500";
			 
		}
		
		return "200";
	}


@Override
public String saveOpdPrescription(PatientPrescriptionHd pphd, List<PatientPrescriptionDt> patientPrescDT) {

	String result=null;
	Transaction t=null;
	Session session=null;
	try{
		 session= getHibernateUtils.getHibernateUtlis().OpenSession();
		 t=session.beginTransaction();
		 Serializable id=session.save(pphd);
		
		 for(PatientPrescriptionDt single:patientPrescDT){
			
			 single.setPrescriptionHdId(Long.valueOf(id.toString()));
			 session.save(single); 
			 
		 }

	t.commit();
	getHibernateUtils.getHibernateUtlis().CloseConnection();
	}catch(Exception ex){
		ex.printStackTrace();
		t.rollback();
		
		return "500";
		 
	}
	
	return "200";
}

	@SuppressWarnings("unchecked")
	@Override
	public String saveOpdPatientDetails(HashMap<String, Object> jsondata) {
		Date currentDate=ProjectUtils.getCurrentDate();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(OpdPatientDetail.class);
		Transaction tx = session.beginTransaction();
		Long visitId = Long.parseLong((String)jsondata.get("visitId"));	
		long patientId;
		long hospitalId;
		
		cr.add(Restrictions.eq("visitId", visitId));
		OpdPatientDetail opdlist = (OpdPatientDetail) cr.uniqueResult();
		try{
			
		if(opdlist!=null)
		{
			opdlist.setPatientId(Long.parseLong(String.valueOf(jsondata.get("patientId"))));
			opdlist.setVisitId(Long.parseLong(String.valueOf(jsondata.get("visitId"))));
			opdlist.setPollor(String.valueOf(jsondata.get("pallar")));
			opdlist.setEdema(String.valueOf(jsondata.get("edema")));
			opdlist.setCyanosis(String.valueOf(jsondata.get("cyanosis")));
			opdlist.setJauindice(String.valueOf(jsondata.get("jauindice")));
			opdlist.setLymphNode(String.valueOf(jsondata.get("lymphNode")));
			opdlist.setClubbing(String.valueOf(jsondata.get("clubbing")));
			opdlist.setThyroid(String.valueOf(jsondata.get("thyroid")));
			opdlist.setTremors(String.valueOf(jsondata.get("tremors")));
			opdlist.setGeneralOther(String.valueOf(jsondata.get("generalOther")));
			opdlist.setGeneral(String.valueOf(jsondata.get("general")));
			opdlist.setCns(String.valueOf(jsondata.get("cns")));
			opdlist.setChestResp(String.valueOf(jsondata.get("chestresp")));
			opdlist.setMusculoskeletal(String.valueOf(jsondata.get("musculoskeletal")));
			opdlist.setCvs(String.valueOf(jsondata.get("cvs")));
			opdlist.setSkin(String.valueOf(jsondata.get("skin")));
			opdlist.setGi(String.valueOf(jsondata.get("gi")));
			opdlist.setIcdDiagnosis(String.valueOf(jsondata.get("icdDiagnosis")));
			opdlist.setSnomedDiagnosis(String.valueOf(jsondata.get("snomedDiagnosis")));
			opdlist.setWorkingDiagnosis(String.valueOf(jsondata.get("workingDiagnosis")));
			opdlist.setSystemOther(String.valueOf(jsondata.get("systemother")));
			opdlist.setGenitoUrinary(String.valueOf(jsondata.get("genitourinary")));
			opdlist.setHeight(String.valueOf(jsondata.get("height")));
			opdlist.setWeight(String.valueOf(jsondata.get("weight")));
			opdlist.setIdealWeight(String.valueOf(jsondata.get("idealWeight")));
			if(jsondata.get("varation")!=null)
			{
			BigDecimal bd=new BigDecimal(String.valueOf(jsondata.get("varation")));
			opdlist.setVaration(bd);
			}
			opdlist.setTemperature(String.valueOf(jsondata.get("temperature")));
			opdlist.setBp(String.valueOf(jsondata.get("bp")));
			opdlist.setPulse(String.valueOf(jsondata.get("pulse")));
			opdlist.setSpo2(String.valueOf(jsondata.get("spo2")));
			opdlist.setBmi(String.valueOf(jsondata.get("bmi")));
			opdlist.setRr(String.valueOf(jsondata.get("rr")));
			session.update(opdlist) ;
			long opdId=opdlist.getOpdPatientDetailsId();
			if(visitId!=null)
			 {
				 Visit visit = (Visit)session.get(Visit.class, visitId);
				 if(visit != null){						
					 visit.setVisitStatus("c");
					 session.update(visit);
				 }
			 }
			
			List<HashMap<String,Object>> list = (List<HashMap<String,Object>>)(Object)jsondata.get("listofReferallHD");
			if(list!=null)
			{
			for(HashMap<String,Object> map : list){
				String extId = (String)map.get("extHospitalId");
				long empID = Long.parseLong(extId);			
				String referralNote = (String)map.get("referralNote");
				patientId = Long.parseLong((String)map.get("patientId"));
				hospitalId = Long.parseLong((String)map.get("hospitalId"));			
				//String treatmentType=(String)map.get("treatmentType");
				
				ReferralPatientHd header = new ReferralPatientHd();
				header.setPatientId(patientId);
				header.setHospitalId(hospitalId);
				header.setExtHospitalId(empID);
				header.setReferralIniDate(currentDate);
				header.setTreatmentType("E");
				header.setReferralNote(referralNote);
				header.setOpdPatientDetailsId(opdId);
				header.setStatus("D");
				long id = Long.parseLong(session.save(header).toString());
				List<HashMap<String,Object>> diagnosisList = (List<HashMap<String,Object>>)(Object)map.get("listofReferalDT");
				for(HashMap<String,Object> diagnosisMap : diagnosisList){
					long diagnosisId = Long.parseLong((String) diagnosisMap.get("diagnosisId"));
					ReferralPatientDt refDt = new ReferralPatientDt();
					refDt.setDiagnosisId(diagnosisId);
					refDt.setRefrealHdId(id);
					session.save(refDt);
					
				}
						
			}
			
			}
			
		}
		else
		{
					
		OpdPatientDetail opddetails = new OpdPatientDetail();
						
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
		opddetails.setIcdDiagnosis(String.valueOf(jsondata.get("icdDiagnosis")));
		opddetails.setSnomedDiagnosis(String.valueOf(jsondata.get("snomedDiagnosis")));
		opddetails.setWorkingDiagnosis(String.valueOf(jsondata.get("workingDiagnosis")));
		opddetails.setSystemOther(String.valueOf(jsondata.get("systemother")));
		opddetails.setGenitoUrinary(String.valueOf(jsondata.get("genitourinary")));
		opddetails.setHeight(String.valueOf(jsondata.get("height")));
		opddetails.setWeight(String.valueOf(jsondata.get("weight")));
		opddetails.setIdealWeight(String.valueOf(jsondata.get("idealWeight")));
		if(jsondata.get("varation")!=null)
		{
		BigDecimal bd=new BigDecimal(String.valueOf(jsondata.get("varation")));
		opddetails.setVaration(bd);
		}
		opddetails.setTemperature(String.valueOf(jsondata.get("temperature")));
		opddetails.setBp(String.valueOf(jsondata.get("bp")));
		opddetails.setPulse(String.valueOf(jsondata.get("pulse")));
		opddetails.setSpo2(String.valueOf(jsondata.get("spo2")));
		opddetails.setBmi(String.valueOf(jsondata.get("bmi")));
		opddetails.setRr(String.valueOf(jsondata.get("rr")));
		long opdId = Long.parseLong(session.save(opddetails).toString());
		if(visitId!=null)
		 {
			 Visit visit = (Visit)session.get(Visit.class, visitId);
			 if(visit != null){						
				 visit.setVisitStatus("c");
				 session.update(visit);
			 }
		 }
		List<HashMap<String,Object>> list = (List<HashMap<String,Object>>)(Object)jsondata.get("listofReferallHD");
				/*
				 * if(list==null) { tx.commit(); }
				 */
		if(list!=null)
		{
		for(HashMap<String,Object> map : list){
			String extId = (String)map.get("extHospitalId");
			long empID = Long.parseLong(extId);			
			String referralNote = (String)map.get("referralNote");
			patientId = Long.parseLong((String)map.get("patientId"));
			hospitalId = Long.parseLong((String)map.get("hospitalId"));			
			//String treatmentType=(String)map.get("treatmentType");
			
			ReferralPatientHd header = new ReferralPatientHd();
			header.setPatientId(patientId);
			header.setHospitalId(hospitalId);
			header.setExtHospitalId(empID);
			header.setReferralIniDate(currentDate);
			header.setTreatmentType("E");
			header.setReferralNote(String.valueOf(referralNote));
			header.setStatus("D");
			header.setOpdPatientDetailsId(opdId);
			long id = Long.parseLong(session.save(header).toString());
			List<HashMap<String,Object>> diagnosisList = (List<HashMap<String,Object>>)(Object)map.get("listofReferalDT");
			for(HashMap<String,Object> diagnosisMap : diagnosisList){
				long diagnosisId = Long.parseLong((String) diagnosisMap.get("diagnosisId"));
				ReferralPatientDt refDt = new ReferralPatientDt();
				refDt.setDiagnosisId(diagnosisId);
				refDt.setRefrealHdId(id);
				session.save(refDt);
				//tx.commit();
				//session.close();
			}
		}
					
		}
		}
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
			return "Error while saving records";
		}finally{
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
		String patientName = String.valueOf(jsondata.get("patient_name")).trim();
		System.out.println("service no "+serviceNo);
		List<OpdObesityHd> patientObesityList = new ArrayList<OpdObesityHd>();
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(serviceNo != null && !serviceNo.equals("") && !serviceNo.equals("null")) {
			Criteria criteria = session.createCriteria(OpdObesityHd.class,"csr");
			criteria.createAlias("csr.patient", "pt");
			criteria.add(Restrictions.eq("pt.serviceNo",serviceNo))
			.add(Restrictions.eq("masHospital.hospitalId",hospitalId))
			.add(Restrictions.isNull("closeDate"));
			patientObesityList=criteria.list();			
			
			/*if(patientObesityList != null && patientObesityList.size()>0) {
				map.put("patientList", patientObesityList);
				map.put("count", patientObesityList.size());
			}*/
		}else if(patientName != null && !patientName.equals("") && !patientName.equals("null")){
			Criteria criteria = session.createCriteria(OpdObesityHd.class,"csr");
			criteria.createAlias("csr.patient", "pt");
			criteria.add(Restrictions.eq("pt.patientName",patientName))
			.add(Restrictions.eq("masHospital.hospitalId",hospitalId))
			.add(Restrictions.isNull("closeDate"));
			patientObesityList=criteria.list();			
			
			/*if(patientObesityList != null && patientObesityList.size()>0) {
				map.put("patientList", patientObesityList);
				map.put("count", patientObesityList.size());
			}*/
			
		}else {
		patientObesityList  = session.createCriteria(OpdObesityHd.class)
						.add(Restrictions.eq("masHospital.hospitalId",hospitalId))
						.add(Restrictions.isNull("closeDate"))
						.list();
		/*if(patientObesityList != null && patientObesityList.size()>0) {
			map.put("patientList", patientObesityList);
			map.put("count", patientObesityList.size());
		}*/
		}
		
		if(patientObesityList != null) {
			map.put("patientList", patientObesityList);
			map.put("count", patientObesityList.size());
		}		
		return map;			
	}	
	
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
	public List<String> getIdealWeight(Long height, String age) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasIdealWeight> list = null;
		List<String> searchList = null;
		try {		
		String Query = "select weight as idealWeight from mas_ideal_weight where '" + age
				+ "' between FROM_AGE and TO_AGE and '" + height + "' between FROM_HEIGHT and TO_HEIGHT";
		if (Query != null) {
			searchList = session.createSQLQuery(Query).list();
		} else {
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
			BigDecimal bmi = new BigDecimal(String.valueOf(jsondata.get("bmi")));
			
			String obesityCheck = String.valueOf(jsondata.get("obesity_check"));
			
			if(obesityCheck.equals("y")) {
				opdHeader = (OpdObesityHd)session.get(OpdObesityHd.class, headerId);
				if(opdHeader != null) {
				
				opdHeader.setCloseDate(obesityDate);
				session.update(opdHeader);
				}
			}
			
			OpdObesityHd opdHd = new OpdObesityHd();
			opdHd.setObesityHdId(headerId);
			OpdObesityDt dt = new OpdObesityDt();
			// set input value to entity and save 
			dt.setOpdObesityHd(opdHd);
			dt.setObesityDate(obesityDate);
			dt.setMonth(month);
			dt.setHeight(height);
			dt.setWeight(weight);
			dt.setIdealWeight(idealWeight);
			dt.setVariation(variation);
			dt.setBmi(bmi);
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
		long pageNo = Long.parseLong(String.valueOf(jsondata.get("pageNo")));
		String mobileNo = String.valueOf(jsondata.get("mobile_no"));
		String patientName = String.valueOf(jsondata.get("patient_name"));
		String serviceNo = String.valueOf(jsondata.get("serviceNo"));
		List<ReferralPatientHd> list = new ArrayList<>();
		if(mobileNo != null && !mobileNo.equals("")&& !mobileNo.equals("null")) {
			list = session.createCriteria(ReferralPatientHd.class,"rph")
					.add(Restrictions.eq("status", "W"))
					.add(Restrictions.isNull("masHospital1"))
					.createAlias("rph.patient", "patient")
					.add(Restrictions.eq("patient.mobileNo", mobileNo))
					.add(Restrictions.eq("masImpanneledHospital.impanneledHospitalId", hospitalId)).list();	
		}else if(patientName != null && !patientName.equals("") && !patientName.equals("null")) {
			list = session.createCriteria(ReferralPatientHd.class,"rph")
					.add(Restrictions.eq("status", "W"))
					.add(Restrictions.isNull("masHospital1"))
					.createAlias("rph.patient", "patient")					
					.add(Restrictions.eq("patient.patientName", patientName))
					.add(Restrictions.eq("masImpanneledHospital.impanneledHospitalId", hospitalId)).list();	
		}else if(serviceNo != null && !serviceNo.equals("") && !serviceNo.equals("null")) {
			list = session.createCriteria(ReferralPatientHd.class,"rph")
					.add(Restrictions.eq("status", "W"))
					.add(Restrictions.isNull("masHospital1"))
					.createAlias("rph.patient", "patient")					
					.add(Restrictions.eq("patient.serviceNo", serviceNo))
					.add(Restrictions.eq("masImpanneledHospital.impanneledHospitalId", hospitalId)).list();	
		}else {
			list = session.createCriteria(ReferralPatientHd.class,"rph")
					.add(Restrictions.isNull("masHospital1"))
					.add(Restrictions.eq("status", "W"))
					.createAlias("rph.masImpanneledHospital", "mih")
					.add(Restrictions.eq("mih.impanneledHospitalId", hospitalId)).list();	
		}					
		
		if(list != null && list.size()>0) {
			map.put("referredPatientList", list);
			map.put("count", list.size());
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

		List<ReferralPatientDt> detailList = (List<ReferralPatientDt>) session.createCriteria(ReferralPatientDt.class)
				.add(Restrictions.eq("referralPatientHd.id", id))
				.add(Restrictions.isNull("masDepartment"))
				.add(Restrictions.eq("mb", "N"))
				.add(Restrictions.eq("admitted", "N"))
				.add(Restrictions.eq("close", "N")).list();
		
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
		try {
		String message = "";
		long headerId = Long.parseLong((String) jsondata.get("header_id"));
		long patientId = Long.parseLong((String) jsondata.get("patient_id"));
		ReferralPatientHd referralPatientHd = null;
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) jsondata.get("detail_list");
		
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < jsondata.size(); i++) {
				for (HashMap<String, String> data : list) {

					long id = Long.parseLong(String.valueOf((String) data.get("id")));
					String finalNote = String.valueOf(((String) data.get("final_note")));
					String notifiable_disease = String.valueOf(((String) data.get("notifiable_disease")));
					String markMb = String.valueOf(((String) data.get("mark_mb")));
					String markAdmitted = String.valueOf(((String) data.get("mark_admitted")));
					String close = String.valueOf(((String) data.get("close")));
					ReferralPatientDt referralPatientDt = (ReferralPatientDt) session.get(ReferralPatientDt.class, id);
					if (referralPatientDt != null) {

						referralPatientDt.setFinalNote(finalNote);
						referralPatientDt.setDisease(notifiable_disease);
						referralPatientDt.setMb(markMb);
						referralPatientDt.setClose(close);
						referralPatientDt.setAdmitted(markAdmitted);
						session.update(referralPatientDt);
						
					} else {
						message = "Record not found for id =" + id;
						map.put("msg",message);
						return map;
					}					

				}

			}
			
			referralPatientHd = (ReferralPatientHd) session.get(ReferralPatientHd.class, headerId);
			if (referralPatientHd != null) {
				referralPatientHd.setStatus("D");
				session.update(referralPatientHd);
			}
			
			AdmissionDischarge admissionDischarge = new AdmissionDischarge();
			Patient patient = new Patient();
			patient.setPatientId(patientId);
			ReferralPatientHd referralPatientHd2 = new ReferralPatientHd();
			referralPatientHd2.setRefrealHdId(headerId);
			admissionDischarge.setReferralPatientHd(referralPatientHd2);
			admissionDischarge.setPatient(patient);
			session.save(admissionDischarge);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			message = "Records updated Failed";
			map.put("msg", message);
		} finally {
			tx.commit();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			message = "Records updated";
			map.put("msg", message);
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
		List<AdmissionDischarge> admissionPendingList = new ArrayList<>();		
		List<AdmissionDischarge> dischargePendingList = new ArrayList<>();
		if (serviceNo != null && !serviceNo.isEmpty() && !serviceNo.equals("null")) {

			admissionPendingList = session.createCriteria(AdmissionDischarge.class, "ad")
					.add(Restrictions.isNull("ad.dateOfAdmission")).add(Restrictions.isNull("ad.dateOfDischarge"))
					.createAlias("ad.patient", "patient")
					.add(Restrictions.eq("patient.serviceNo", serviceNo))
					.list();

			dischargePendingList = session.createCriteria(AdmissionDischarge.class, "ad")
					.add(Restrictions.isNotNull("ad.dateOfAdmission")).add(Restrictions.isNull("ad.dateOfDischarge"))
					.createAlias("ad.masHospital", "mih")
					.add(Restrictions.eq("mih.hospitalId", hospitalId))
					.createAlias("ad.patient", "patient")
					.add(Restrictions.eq("patient.serviceNo", serviceNo)).list();

		} else if (patientName != null && !patientName.isEmpty() && !patientName.equals("null")) {
			admissionPendingList = session.createCriteria(AdmissionDischarge.class, "ad")
					.add(Restrictions.isNull("ad.dateOfAdmission")).add(Restrictions.isNull("ad.dateOfDischarge"))
					.createAlias("ad.patient", "patient")
					.add(Restrictions.eq("patient.patientName", patientName))
					.list();

			dischargePendingList = session.createCriteria(AdmissionDischarge.class, "ad")
					.add(Restrictions.isNotNull("ad.dateOfAdmission")).add(Restrictions.isNull("ad.dateOfDischarge"))
					.createAlias("ad.masHospital", "mih")
					.add(Restrictions.eq("mih.hospitalId", hospitalId))
					.createAlias("ad.patient", "patient")
					.add(Restrictions.eq("patient.patientName", patientName)).list();

		} else {
			admissionPendingList = session.createCriteria(AdmissionDischarge.class, "ad")
					.add(Restrictions.isNull("ad.dateOfAdmission")).add(Restrictions.isNull("ad.dateOfDischarge"))
					.list();

			dischargePendingList = session.createCriteria(AdmissionDischarge.class, "ad")
					.add(Restrictions.isNotNull("ad.dateOfAdmission")).add(Restrictions.isNull("ad.dateOfDischarge"))
					.createAlias("ad.masHospital", "mih")
					.add(Restrictions.eq("mih.hospitalId", hospitalId)).list();
		}
			
		
		System.out.println("conunt value is "+admissionPendingList.size());
		if(admissionPendingList != null) {
			map.put("admissionPendingList", admissionPendingList);
			map.put("count1", admissionPendingList.size()+"");
		}
		if(dischargePendingList != null) {
			map.put("dischargePendingList",dischargePendingList);
			map.put("count2",dischargePendingList.size()+"");
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
		Date admissionDate = HMSUtil.convertStringDateToUtilDate(String.valueOf(jsondata.get("admission_date")), "yyyy-MM-dd");
		BigDecimal wardNo = null;
		if(jsondata.get("ward") != null && !jsondata.get("ward").equals("")) {
			String ward = String.valueOf(jsondata.get("ward"));
			 wardNo = new BigDecimal(ward);
		}		
		long disposalId = Long.parseLong(String.valueOf(jsondata.get("disposal")));
		String noOfDays = String.valueOf(jsondata.get("no_of_days"));
		BigDecimal days = new BigDecimal(noOfDays);
		String remarks = String.valueOf(jsondata.get("remark"));
		Date dischargeDate = null;
		if(jsondata.get("discharge_date") != null && !jsondata.get("discharge_date").isEmpty()) {
			dischargeDate = HMSUtil.convertStringDateToUtilDate((String.valueOf(jsondata.get("discharge_date"))),"yyyy-MM-dd");
		}
		String admissionNo = null;
		if(jsondata.get("admission_no") != null && !jsondata.get("admission_no").isEmpty()) {
			admissionNo = String.valueOf(jsondata.get("admission_no"));
		}				
		
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Transaction tx = session.beginTransaction();
		AdmissionDischarge admissionDischarge = null;
		admissionDischarge =  (AdmissionDischarge) session.get(AdmissionDischarge.class, admissionId);
		if(admissionDischarge != null) {          
			admissionDischarge.setAdmissionNo(admissionNo);
			admissionDischarge.setDateOfAdmission(admissionDate);
			admissionDischarge.setDateOfDischarge(dischargeDate);
			MasDisposal masDisposal = new MasDisposal();
			masDisposal.setDisposalId(disposalId);
			admissionDischarge.setMasDisposal(masDisposal);
			admissionDischarge.setNoOfDays(days);
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
			MasDisposal masDisposal = new MasDisposal();
			masDisposal.setDisposalId(disposalId);
			admission.setMasDisposal(masDisposal);
			admission.setNoOfDays(days);
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
		
		return "Admission Done Successfully";
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
			BigDecimal ward = new BigDecimal(w);
			long disposalId = Long.parseLong(jsondata.get("disposal"));
			MasDisposal masDisposal = new MasDisposal();
			masDisposal.setDisposalId(disposalId);
			String remarks = jsondata.get("remarks");
			AdmissionDischarge admissionDischarge = new AdmissionDischarge();
			admissionDischarge.setPatient(patient);
			admissionDischarge.setMasHospital(masHospital);
			admissionDischarge.setAdmissionNo(admissionNo);
			admissionDischarge.setDateOfAdmission(dateOfAdmission);
			admissionDischarge.setMasDisposal(masDisposal);
			admissionDischarge.setRemarks(remarks);
			admissionDischarge.setWardNo(ward);
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
		long hospital_id = Long.parseLong(jsondata.get("hospital_id"));
		
		List<ProcedureHd> ncWaitingList = session.createCriteria(ProcedureHd.class)
					.add(Restrictions.eq("masHospital.hospitalId", hospital_id))
						.add(Restrictions.eq("status", "N")).list();
		
		System.out.println(ncWaitingList.size());
		System.out.println(ncWaitingList.toString());
		map.put("nursingCareList", ncWaitingList);	
		map.put("count", ncWaitingList.size());
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
				.add(Restrictions.eq("procedureHd.procedureHdId", headerId)).list();
				//.add(Restrictions.ne("status", "N")).list();
		
		map.put("detailList", list);		
		return map;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getProcedureDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Map<String,Object> map = new HashMap<>();
		long detail_id = Long.parseLong((String)map.get("detail_id"));		
		List<ProcedureDt> list = session.createCriteria(ProcedureDt.class)
				.add(Restrictions.eq("procedureDtId", detail_id)).list();
				//.add(Restrictions.eq("status", "N")).list();
		map.put("detailList", list);
		return map;
	}

	@Override
	public String saveProcedureDetail(HashMap<String, String> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Transaction tx = session.beginTransaction();
		String result = "";
		try {
			
			Date appDate = HMSUtil.convertStringDateToUtilDate(jsondata.get("appointment_date"),"dd/MM/yyyy");
			String nursing_remarks = jsondata.get("nursing_remarks");
			
			String save = session.save(ProcedureDt.class)+"";
			System.out.println("save is "+save);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			tx.commit();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}		
		return "Error While saving detail";
	}	

}












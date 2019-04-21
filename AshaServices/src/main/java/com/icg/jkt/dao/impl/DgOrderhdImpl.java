package com.icg.jkt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icg.jkt.dao.DgOrderhdDao;
import com.icg.jkt.entity.DgMasInvestigation;
import com.icg.jkt.entity.DgOrderdt;
import com.icg.jkt.entity.DgOrderhd;
import com.icg.jkt.entity.OpdPatientDetail;
import com.icg.jkt.entity.OpdPatientHistory;
import com.icg.jkt.entity.Patient;
import com.icg.jkt.entity.PatientPrescriptionDt;
import com.icg.jkt.entity.PatientPrescriptionHd;
import com.icg.jkt.entity.ReferralPatientDt;
import com.icg.jkt.entity.ReferralPatientHd;
import com.icg.jkt.hibernateutils.GetHibernateUtils;

@Repository
@Transactional
public class DgOrderhdImpl implements DgOrderhdDao{
	
	@Autowired
	GetHibernateUtils getHibernateUtils;

	@Override
	public List<Object[]> getDgMasInvestigations(Long visitId) {
		List<DgMasInvestigation>  listDgMasInvestigation =null;
		Transaction transation=null;
		 List<Object[]> listObject=null;
		try {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		transation=session.beginTransaction();
		/*Criteria criteria = session.createCriteria(DgOrderdt.class,"dgordt");
		transation=session.beginTransaction();
		criteria.createAlias("dgordt.dgOrderhd", "dgordh");
		criteria.add(Restrictions.eq("dgordh.visit.visitId",visitId));*/
		
		 StringBuilder sbQuery = new StringBuilder();
		    sbQuery.append(" select dgmas.INVESTIGATION_ID,dgmas.INVESTIGATION_NAME,ohd.ORDERHD_ID,odt.LAB_MARK,odt.urgent,odt.ORDER_DATE, ohd.VISIT_ID,ohd.OTHER_INVESTIGATION,");
		    		sbQuery.append( "ohd.DEPARTMENT_ID,ohd.HOSPITAL_ID,odt.ORDERDT_ID from  DG_ORDER_HD ohd ");
		    sbQuery.append(" join DG_ORDER_DT odt on  ohd.orderhd_id=odt.orderhd_id join DG_MAS_INVESTIGATION "); 
		    sbQuery.append(" dgmas on dgmas.INVESTIGATION_ID=odt.INVESTIGATION_ID "); 
		    sbQuery.append(" where ohd.VISIT_ID =:visitId ");
		    Query query = session.createSQLQuery(sbQuery.toString());

		    query.setParameter("visitId", visitId);

		     listObject = query.list();
		
		
		//List<DgOrderdt> list= criteria.list();
	/*	if(CollectionUtils.isNotEmpty(list))
			listDgMasInvestigation=list.get(0).getDgMasInvestigations();*/
		transation.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		
		}
		return listObject;
	}

	@Override
	public List<Object[]> getTreatementDetail(Long visitId) {
		Transaction transation=null;
		 List<Object[]> listObject=null;
		try {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		transation=session.beginTransaction();
		/*Criteria criteria = session.createCriteria(DgOrderdt.class,"dgordt");
		transation=session.beginTransaction();
		criteria.createAlias("dgordt.dgOrderhd", "dgordh");
		criteria.add(Restrictions.eq("dgordh.visit.visitId",visitId));*/
		
		 StringBuilder sbQuery = new StringBuilder();
		    sbQuery.append(" select msi.nomenclature,msi.ITEM_ID,mf.FREQUENCY_ID,mf.FREQUENCY_NAME,mf.FREQUENCY_CODE," );
		    sbQuery.append("ppdt.NO_OF_DAYS,ppdt.dosage,pphd.PRESCRIPTION_HD_ID,ppdt.PRESCRIPTION_DT_ID,ppdt.DISP_STOCK,ppdt.total,ppdt.instruction,ppdt.STORE_STOCK from ");
		    sbQuery.append(" PATIENT_PRESCRIPTION_HD pphd join PATIENT_PRESCRIPTION_DT ppdt on pphd.PRESCRIPTION_HD_ID=ppdt.PRESCRIPTION_HD_ID "); 
		    sbQuery.append(" join MAS_STORE_ITEM msi on msi.ITEM_ID =ppdt.ITEM_ID join MAS_STORE_UNIT msu on msu.STORE_UNIT_ID=msi.DISP_UNIT_ID ");
		    sbQuery.append("  join MAS_FREQUENCY mf on mf.FREQUENCY_ID=ppdt.FREQUENCY_ID");
		    sbQuery.append(" where pphd.VISIT_ID =:visitId ");
		    Query query = session.createSQLQuery(sbQuery.toString());

		    query.setParameter("visitId", visitId);

		     listObject = query.list();
		
		
		//List<DgOrderdt> list= criteria.list();
	/*	if(CollectionUtils.isNotEmpty(list))
			listDgMasInvestigation=list.get(0).getDgMasInvestigations();*/
		transation.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		
		}
		return listObject;
	}

	 public Map<String,Object> getDgOrderDtByOrderHdIdAndInvestigationId(List<String> orderhdIds, List<String> investigationIds ){
		Session session=null;
		List<String>listDgMasInvestigation=null;
		List<DgOrderdt>listDgOrderDt=null;
		Map<String,Object>hashMapDgMasInvestigation=null;
		 try {
			 session= getHibernateUtils.getHibernateUtlis().OpenSession();
			 Criteria criteria=session.createCriteria(DgOrderdt.class).add(Restrictions.in("ORDERHD_ID", orderhdIds)).add(Restrictions.in("INVESTIGATION_ID", investigationIds))
			 .setProjection(
			            Projections.projectionList().add(Projections.property("INVESTIGATION_ID"))) ;
		 listDgMasInvestigation=criteria.list();
		 
		 Criteria masCriteria=session.createCriteria(DgOrderdt.class).add(Restrictions.in("ORDERHD_ID", orderhdIds)).add(Restrictions.in("INVESTIGATION_ID", investigationIds));
		 listDgOrderDt=criteria.list();
		 
		 hashMapDgMasInvestigation=new HashMap<>();
		 hashMapDgMasInvestigation.put("listDgMasInvestigation", listDgMasInvestigation);
		 hashMapDgMasInvestigation.put("listDgOrderDt", listDgOrderDt);
		 } 
		 catch(Exception e) {
			 e.printStackTrace();
		 }finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
		 }
		 return hashMapDgMasInvestigation;
	 }

	@Override
	public DgOrderdt getDgOrderDtByDgOrderdtId(Long dgOrderDtId) {
		DgOrderdt dgOrderdt=null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			
			dgOrderdt=(DgOrderdt) session.get(DgOrderdt.class, dgOrderDtId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return dgOrderdt;
	}

	@Override
	public Long saveOrUpdateDgOrderdt(DgOrderdt dgOrderDt) {
		Session session=null;
		Long dgOrderDtId=null;
		Transaction tx=null;
		try{
			session= getHibernateUtils.getHibernateUtlis().OpenSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(dgOrderDt);
			dgOrderDtId=dgOrderDt.getOrderdtId();
			session.flush();
	        session.clear();
			tx.commit();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			 
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			
			}
		return dgOrderDtId;
	}
	
	@Override
	public Long saveOrUpdateDgOrderHd(DgOrderhd dgOrderhd) {
		Session session=null;
		Long dgOrderhdId=null;
		Transaction tx=null;
		try{
			session= getHibernateUtils.getHibernateUtlis().OpenSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(dgOrderhd);
			dgOrderhdId=dgOrderhd.getOrderhdId();
			session.flush();
	        session.clear();
			tx.commit();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			 
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			
			}
		return dgOrderhdId;
	}
	
	@Override
	public Long saveOrUpdatePatient(Patient patient) {
		Session session=null;
		Long patientId=null;
		Transaction tx=null;
		try{
			session= getHibernateUtils.getHibernateUtlis().OpenSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(patient);
			patientId=patient.getPatientId();
			session.flush();
	        session.clear();
			tx.commit();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			
			}
		return patientId;
	}
	@Override
	public Long updatePatientHistory(OpdPatientHistory opdPatientHistory) {
		Session session=null;
		Long patientHistoryId=null;
		Transaction tx=null;
		try{
			session= getHibernateUtils.getHibernateUtlis().OpenSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(opdPatientHistory);
			patientHistoryId=opdPatientHistory.getOpdPatientHistoryId();
			session.flush();
	        session.clear();
			tx.commit();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			
			}
		return patientHistoryId;
	}

	@Override
	public Long updateOpdPatientDetail(OpdPatientDetail opdPatientDetail) {
		Long opdPatientDetailId=null;
		Session session=null;
		Transaction tx=null;
		try{
			session= getHibernateUtils.getHibernateUtlis().OpenSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(opdPatientDetail);
			opdPatientDetailId=opdPatientDetail.getOpdPatientDetailsId();
			session.flush();
	        session.clear();
			tx.commit();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			
			}
		return opdPatientDetailId;
	}

	
	@Override
	public List<OpdPatientHistory> getPatientHistoryList(Long visitId) {
		List<OpdPatientHistory>opdPatientHistoryList=null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			opdPatientHistoryList =session.createCriteria(OpdPatientHistory.class).add(Restrictions.eq("visitId", visitId)).list();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return opdPatientHistoryList;
	}

	@Override
	public PatientPrescriptionDt getMasStoreItemByPatientPrecriptionDtId(Long patientPrecriptionDtId) {
		PatientPrescriptionDt patientPrescriptionDt=null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			
			patientPrescriptionDt=(PatientPrescriptionDt) session.get(PatientPrescriptionDt.class, patientPrecriptionDtId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return patientPrescriptionDt;
		
	}
	
	@Override
	public Long saveOrUpdatePatientPrecriptionDt(PatientPrescriptionDt patientPrescriptionDt) {
		Session session=null;
		Long patientPrecriptiondtId=null;
		Transaction tx=null;
		try{
			session= getHibernateUtils.getHibernateUtlis().OpenSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(patientPrescriptionDt);
			patientPrecriptiondtId=patientPrescriptionDt.getPrescriptionDtId();
			session.flush();
	        session.clear();
			tx.commit();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			
			}
		return patientPrecriptiondtId;
	}

	@Override
	public List<Object[]> getReferralPatientDtList(Long opdPatientDetailId) {
		 List<Object[]>listReferralPatientDt=null;
		 try {
			 /*Session session=getHibernateUtils.getHibernateUtlis().OpenSession();
			 Criteria criteria=session.createCriteria(ReferralPatientDt.class,"referalDt").createAlias("referralPatientHd", "referalHd");
			 listReferralPatientDt=criteria.add(Restrictions.eq("referalHd.opdPatientDetailsId", opdPatientDetailId)).list();
			 */
			 Session session=getHibernateUtils.getHibernateUtlis().OpenSession();
			 StringBuilder sbQuery = new StringBuilder();
			 
			    sbQuery.append(" select masEmpHos.EMPANELLED_HOSPITAL_ID,masEmpHos.EMPANELLED_HOSPITAL_NAME,masDepart.DEPARTMENT_ID, " );
			    sbQuery.append(" masDepart.DEPARTMENT_NAME,masIcd.ICD_ID,masIcd.ICD_NAME,refdt.instruction,refdt.REFREAL_DT_ID,refhd.REFREAL_HD_ID,refdt.EXT_DEPARTMENT from ");
			    sbQuery.append(" REFERRAL_PATIENT_DT refdt  join REFERRAL_PATIENT_HD refhd on refhd.REFREAL_HD_ID=refdt.REFREAL_HD_ID "); 
			    sbQuery.append(" left join MAS_EMPANELLED_HOSPITAL masEmpHos on masEmpHos.EMPANELLED_HOSPITAL_ID=refhd.EXT_HOSPITAL_ID left join MAS_ICD masIcd on masIcd.ICD_ID=refdt.DIAGNOSIS_ID ");
			    sbQuery.append(" left join MAS_DEPARTMENT masDepart on masDepart.DEPARTMENT_ID =refdt.INT_DEPARTMENT_ID ");
			    sbQuery.append(" where refhd.OPD_PATIENT_DETAILS_ID =:OPD_PATIENT_DETAILS_ID ");
			    Query query = session.createSQLQuery(sbQuery.toString());

			    query.setParameter("OPD_PATIENT_DETAILS_ID", opdPatientDetailId);

			    listReferralPatientDt = query.list();
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 return listReferralPatientDt;
	}
	
	@Override
	public ReferralPatientDt getReferralPatientDtByReferaldtId(Long referalDtId) {
		ReferralPatientDt referralPatientDt=null;
 
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
 
			referralPatientDt=(ReferralPatientDt) session.createCriteria(ReferralPatientDt.class)
					.add(Restrictions.eq("refrealDtId", referalDtId)).uniqueResult();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return referralPatientDt;
	}
	
	@Override
	public ReferralPatientHd getPatientReferalHdByExtHospitalId(Long extHospitalId,Long opdPatientDetailId) {
		ReferralPatientHd referralPatientHd=null;
		List<ReferralPatientHd>listReferralPatientHd=null;
		Transaction tx=null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			tx=session.beginTransaction();
			Criteria criteria = session.createCriteria(ReferralPatientHd.class).
					add(Restrictions.eq("extHospitalId", extHospitalId))
					.add(Restrictions.eq("opdPatientDetailsId", opdPatientDetailId)) ;
			listReferralPatientHd=criteria.list();
			
			if(CollectionUtils.isNotEmpty(listReferralPatientHd)) {
				referralPatientHd=listReferralPatientHd.get(0);
			}
			 tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return referralPatientHd;
	}
	
	@Override
	public Long saveOrUpdateReferalDt(ReferralPatientDt referralPatientDt) {
		Session session=null;
		Long patientReferalDtId=null;
		Transaction tx=null;
		try{
			session= getHibernateUtils.getHibernateUtlis().OpenSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(referralPatientDt);
			patientReferalDtId=referralPatientDt.getRefrealDtId();
			session.flush();
	        session.clear();
			tx.commit();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			
			}
		return patientReferalDtId;
	}
	
	@Override
	public Long saveOrUpdateReferalHd(ReferralPatientHd referralPatientHd) {
		Session session=null;
		Long patientReferalHdId=null;
		Transaction tx=null;
		try{
			session= getHibernateUtils.getHibernateUtlis().OpenSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(referralPatientHd);
			patientReferalHdId=referralPatientHd.getRefrealHdId();
			session.flush();
	        session.clear();
			tx.commit();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			
			}
		return patientReferalHdId;
	}

	public String  deleteInvestigatRow(Long dgOrderDt, String flag) {
		Session session = null;
		Transaction tx = null;
		String status="";
		try {
			session = getHibernateUtils.getHibernateUtlis().OpenSession();
			tx = session.beginTransaction();
			Object object = null;
			
			if (flag.equalsIgnoreCase("1")) {
				object = session.get(DgOrderdt.class, dgOrderDt);
				DgOrderdt dgOrderdt = (DgOrderdt) object;
				if (dgOrderdt != null) {
					//session.delete(dgOrderdt);
					status=deleteObject("DgOrderdt","orderdtId",  dgOrderdt.getOrderdtId());
					
				}
			}
			
			if (flag.equalsIgnoreCase("2")) {
				object = session.get(PatientPrescriptionDt.class, dgOrderDt);
				PatientPrescriptionDt patientPrescriptionDt = (PatientPrescriptionDt) object;
				if (patientPrescriptionDt != null)
					//session.delete(patientPrescriptionDt);
					status=deleteObject("PatientPrescriptionDt","prescriptionDtId",  patientPrescriptionDt.getPrescriptionDtId());
			}
			
			if (flag.equalsIgnoreCase("3")) {
				object = session.get(ReferralPatientDt.class, dgOrderDt);
				
				ReferralPatientDt referralPatientDt = (ReferralPatientDt) object;

				Long referalPatientHd = referralPatientDt.getRefrealHdId();
				
				if (referralPatientDt != null) {
					//session.delete(referralPatientDt);
					status=deleteObject("ReferralPatientDt","refrealDtId",  referralPatientDt.getRefrealDtId());
					
					//ReferralPatientHd referralPatientHd=getReferralPatientHdByRereralHdId(referalPatientHd);

					//session.delete(referralPatientHd);
					//status =deleteObject("ReferralPatientHd","refrealHdId",  referralPatientHd.getRefrealHdId());

				}
			}
			
			session.flush();
			session.clear();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();

		}
		return status;
	}
	
	public ReferralPatientHd getReferralPatientHdByRereralHdId(Long referalPatientHd) {
		ReferralPatientHd referralPatientHd=null;
		try{
			Session session= getHibernateUtils.getHibernateUtlis().OpenSession();
			referralPatientHd=(ReferralPatientHd) session.createCriteria(ReferralPatientHd.class).add(Restrictions.eq("refrealHdId", referalPatientHd)).uniqueResult();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 
		return referralPatientHd;
	}

	@Override
	public Long saveOrUpdatePatientPrescriptionHd(PatientPrescriptionHd patientPrescriptionHd) {
		 	Session session=null;
			Long patientPrecriptionHdId=null;
			Transaction tx=null;
			try{
				session= getHibernateUtils.getHibernateUtlis().OpenSession();
				tx=session.beginTransaction();
				session.saveOrUpdate(patientPrescriptionHd);
				patientPrecriptionHdId=patientPrescriptionHd.getPrescriptionHdId();
				session.flush();
		        session.clear();
				tx.commit();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
				
				}
			return patientPrecriptionHdId;
		 

	}
	@Override
	public DgOrderhd getDgOrderhdByDgOrderhdId(Long dgOrderhdId) {
		DgOrderhd dgOrderhd=null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			
			dgOrderhd=(DgOrderhd) session.get(DgOrderhd.class, dgOrderhdId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return dgOrderhd;
	}

 
public String deleteObject(String  className,String columnName,Long columnValue) {
 String status="";
		try {

			String hql = "delete from " + className + " WHERE " + columnName + "=" + columnValue;
			Query query = null;
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			query = session.createQuery(hql);
			query.executeUpdate();
			status = ""+columnValue;
		} catch(Exception e) {
	  status="error"+000;
		e.printStackTrace();
	}
	 return  status;
}
public PatientPrescriptionHd getPatientPrecriptionHdByVisitId(Long visitId) {
	PatientPrescriptionHd patientPrescriptionHd=null;
	try{
		Session session= getHibernateUtils.getHibernateUtlis().OpenSession();
		patientPrescriptionHd=(PatientPrescriptionHd) session.createCriteria(PatientPrescriptionHd.class).add(Restrictions.eq("visitId", visitId)).uniqueResult();
		
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	 
	return patientPrescriptionHd;
}
public DgOrderhd getDgOrderHdByVisitId(Long visitId) {
	DgOrderhd dgOrderhd=null;
	try{
		Session session= getHibernateUtils.getHibernateUtlis().OpenSession();
		dgOrderhd=(DgOrderhd) session.createCriteria(DgOrderhd.class).add(Restrictions.eq("visitId", visitId)).uniqueResult();
		
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	 
	return dgOrderhd;
}

}








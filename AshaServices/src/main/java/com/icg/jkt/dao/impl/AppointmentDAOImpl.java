package com.icg.jkt.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icg.jkt.dao.AppointmentDAO;
import com.icg.jkt.entity.AppSetup;
import com.icg.jkt.entity.MasAppointmentSession;
import com.icg.jkt.entity.MasAppointmentType;
import com.icg.jkt.entity.MasCommand;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasHospital;
import com.icg.jkt.hibernateutils.GetHibernateUtils;
import com.icg.jkt.utils.HMSUtil;

@Repository
@Transactional
public class AppointmentDAOImpl implements AppointmentDAO{

	@Autowired
	GetHibernateUtils getHibernateUtils;
	
	
	
	@Override
	public Map<String, Object> getRecordsForDoctorAppointment() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MasDepartment> departmentList = new ArrayList<MasDepartment>();
		departmentList = getDepartmentList();
		map.put("departmentList", departmentList);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MasAppointmentSession>getlocationWiseAppointmentType(long departmentId) {
		List<MasAppointmentSession> appointmentSessionList = new ArrayList<MasAppointmentSession>();
		/* Dummy hospitalId , It will be fetch from session */
		long hospitalId=1; 
		Session session=null;
		 
		try {
			session = getHibernateUtils.getHibernateUtlis().OpenSession();
			appointmentSessionList=session.createCriteria(MasAppointmentSession.class)
					.createAlias("masDepartment", "dept")
					.createAlias("masHospital", "hospital")
					.add(Restrictions.eq("hospital.hospitalId", hospitalId))
					.add(Restrictions.eq("dept.departmentId", departmentId))
					.list();
			
			 
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		}finally {
			
			 
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			
		}
		return appointmentSessionList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getappointmentSetupDetails(long departmentId, long appointmentTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MasAppointmentSession> appointmentSessionList = new ArrayList<MasAppointmentSession>();
		List<AppSetup>appSetupList=new ArrayList<AppSetup>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		/* Dummy hospitalId , It will be fetch from session */
		long hospitalId=1; 
		 try {
			 System.out.println("appointmentTypeId :"+appointmentTypeId);
			 
			 appointmentSessionList=session.createCriteria(MasAppointmentSession.class)
					 .createAlias("masHospital", "hospital")
					 .createAlias("masDepartment", "md")
					 .createAlias("masAppointmentType", "appType")
						.add(Restrictions.eq("hospital.hospitalId", hospitalId))
						.add(Restrictions.eq("md.departmentId", departmentId))
						.add(Restrictions.eq("appType.appointmentTypeId", appointmentTypeId))
						.list();
			 
			 if(appointmentSessionList.size()>0) {
				long sessionId= appointmentSessionList.get(0).getId();
				
				 appSetupList=session.createCriteria(AppSetup.class) 
						 .createAlias("masHospital", "hospital")
						 .createAlias("masDepartment", "md")
						 .createAlias("masAppointmentSession", "session")
						  .add(Restrictions.eq("md.departmentId", departmentId))
						  .add(Restrictions.eq("hospital.hospitalId", hospitalId))
						  .add(Restrictions.eq("session.id", sessionId))
						  .list();
						
			 }
			  map.put("appSetupList",appSetupList);
			  map.put("appointmentSessionList",appointmentSessionList);
		 }catch (Exception e) {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
				e.getMessage();
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return map;
	}

	@Override
	public AppSetup getAppSetupObject(long appointmentId) {
		AppSetup appSetup=null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			appSetup= (AppSetup) session.load(AppSetup.class,appointmentId);
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return appSetup;
	}

	
	@SuppressWarnings("unused")
	@Override
	public String saveAppointmentSetup(AppSetup appSetup) {
		String Result = "";
		boolean save= false;
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(AppSetup.class);
			Transaction tx = session.beginTransaction();
			if(appSetup.getId()!=0) {
				session.update(appSetup);
			}else {
				session.save(appSetup);
			}
			
			tx.commit();
			save = true;
			if (save) {
				Result = "200";
			} else {
				Result = "500";
			}
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			Result = e.getMessage();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return Result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MasHospital> getHospitalList() {
		List<MasHospital> hospitalList = new ArrayList<MasHospital>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			hospitalList = session.createCriteria(MasHospital.class)
					.addOrder(Order.asc("hospitalName")).list();
			System.out.println(hospitalList.size());
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return hospitalList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MasDepartment> getDepartmentList() {
		List<MasDepartment> departmentList = new ArrayList<MasDepartment>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		long departmentTypeId = Long.parseLong(HMSUtil.getProperties("adt.properties", "DEPARTMENT_TYPE_ID").trim());
		try {
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
	public List<MasAppointmentType> getAppointmentTypeList() {
		List<MasAppointmentType> appointmentTypeList = new ArrayList<MasAppointmentType>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			appointmentTypeList = session.createCriteria(MasAppointmentType.class)
					.addOrder(Order.asc("appointmentTypeName")).list();
		}catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return appointmentTypeList;
	}

	@SuppressWarnings("unused")
	@Override
	public String submitAppointmentSession(MasAppointmentSession appointmentSession) {
		String Result = "";
		boolean save= false;
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasAppointmentSession.class);
			Transaction tx = session.beginTransaction();
			session.save(appointmentSession);
			tx.commit();
			save = true;
			if (save) {
				Result = "200";
			} else {
				Result = "500";
			}
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			Result = e.getMessage();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		 
		return Result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List<MasAppointmentSession>> getAllAppointmentSession(JSONObject jsonObj) {
		Map<String, List<MasAppointmentSession>> mapObj = new HashMap<String, List<MasAppointmentSession>>();
		try {		
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());
		System.out.println("jsonObj :: "+jsonObj);
		List totalMatches = new ArrayList();
		List<MasAppointmentSession> masAppointSessionList = new ArrayList<MasAppointmentSession>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria = session.createCriteria(MasAppointmentSession.class,"masAppointment")
				.createAlias("masAppointment.masHospital", "masHospital")
				.createAlias("masAppointment.masDepartment", "masDepartment");

		if (jsonObj.get("PN") != null)
			pageNo = Integer.parseInt(jsonObj.get("PN").toString());
		
		int hId = 0;
		if (jsonObj.has("hospitalId")) {
			hId = Integer.parseInt(jsonObj.get("hospitalId").toString());
			if (jsonObj.get("hospitalId").toString().length()!= 0) {
				criteria.add(Restrictions.eq("hospitalId", hId));
				ProjectionList projectionList = Projections.projectionList();
				//projectionList.add(projections.)
			}
		}
		
		
		criteria.addOrder(Order.asc("masHospital"));
		totalMatches = criteria.list();
		
		criteria.setFirstResult((pageSize) * (pageNo - 1));
		criteria.setMaxResults(pageSize);
		masAppointSessionList = criteria.list();			
		mapObj.put("masAppointSessionList", masAppointSessionList);
		mapObj.put("totalMatches", totalMatches);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	
	}

	@Override
	public List<MasAppointmentSession> validateAppointmentSetup(AppSetup appSetup) {
		List<MasAppointmentSession> appointmentSessionsList = new ArrayList<MasAppointmentSession>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasAppointmentSession.class, "appointmentSession")
					.createAlias("appointmentSession.masHopital", "mashospital")
					.createAlias("appointmentSession.masDepartment", "masdepartment")
					.createAlias("appointmentSession.masAppointmentType", "masappointmenttype")
					.add(Restrictions.or(Restrictions.eq("mashospital.hospitalName", appSetup.getMasHospital().getHospitalName()), 
							Restrictions.eq("masDepartment.departmentName", appSetup.getMasDepartment().getDepartmentName()),
							Restrictions.eq("masappointmenttype.appointmentTypeName", appSetup.getMasAppointmentSession().getMasAppointmentType().getAppointmentTypeName())));
			appointmentSessionsList =criteria.list();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return appointmentSessionsList;
	}
}
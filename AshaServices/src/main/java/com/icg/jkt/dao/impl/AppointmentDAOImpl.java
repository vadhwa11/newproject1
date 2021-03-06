package com.icg.jkt.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icg.jkt.dao.AppointmentDAO;
import com.icg.jkt.entity.AppSetup;
import com.icg.jkt.entity.MasAppointmentSession;
import com.icg.jkt.entity.MasAppointmentType;
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
	public Map<String, Object> getDataForDoctorAppointment() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MasDepartment> departmentList = new ArrayList<MasDepartment>();
		departmentList = getDepartmentList();
		map.put("departmentList", departmentList);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MasAppointmentSession>getlocationWiseAppointmentType(long departmentId,long hospitalId) {
		List<MasAppointmentSession> appointmentSessionList = new ArrayList<MasAppointmentSession>();
		
		Session session=null;
		 
		try {
			session = getHibernateUtils.getHibernateUtlis().OpenSession();
			appointmentSessionList=session.createCriteria(MasAppointmentSession.class)
					.add(Restrictions.eq("Status","y").ignoreCase())
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
	public Map<String, Object> getappointmentSetupDetails(long departmentId, long appointmentTypeId,long hospitalId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MasAppointmentSession> appointmentSessionList = new ArrayList<MasAppointmentSession>();
		List<AppSetup>appSetupList=new ArrayList<AppSetup>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		
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
					.add(Restrictions.eq("status","y").ignoreCase())
					.addOrder(Order.asc("hospitalName")).list();
			
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
					.add(Restrictions.eq("status","y").ignoreCase())
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

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<MasAppointmentSession>> getAllAppointmentSession(JSONObject jsonObj) {
		Map<String, List<MasAppointmentSession>> mapObj = new HashMap<String, List<MasAppointmentSession>>();
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo=1;
		//long hospitalId=jsonObj.getLong("hospitalId"); //HospitaId will fetch from session 
		long hospitalId = Long.parseLong((jsonObj.get("hospitalId").toString()));
		System.out.println("hospitalId :"+hospitalId);
		List<MasAppointmentSession> totalMatches = new ArrayList<MasAppointmentSession>();
		List<MasAppointmentSession> masAppointSessionList = new ArrayList<MasAppointmentSession>();
		try {		
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		
		Criteria criteria = session.createCriteria(MasAppointmentSession.class)
				.createAlias("masDepartment", "department")
				.createAlias("masAppointmentType", "appType")
				.add(Restrictions.eq("department.status", "y").ignoreCase())
				.add(Restrictions.eq("appType.status", "y").ignoreCase())
				.add(Restrictions.eq("masHospital.hospitalId", hospitalId));

		if (jsonObj.get("PN") != null)
			pageNo = Integer.parseInt(jsonObj.get("PN").toString());
		
		/*
		String cName = "";
		if (jsonObj.has("masDepartment")) {
			cName = jsonObj.get("masHospital") + "%";
			if (jsonObj.get("masHospital").toString().length() > 0
					&& !jsonObj.get("masHospital").toString().trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.like("masHospital", cName));

			}
		}
		
		if (jsonObj.has("masAppointmentType")) {
			cName = jsonObj.get("masAppointmentType") + "%";
			if (jsonObj.get("masHospital").toString().length() > 0
					&& !jsonObj.get("masHospital").toString().trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.like("masHospital", cName));

			}
		}
		*/
		criteria.addOrder(Order.asc("department.departmentName"));
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean validateAppSession(long departmentId, long appointmentTypeId, long hospitalId) {
		boolean flag = false;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			List<MasAppointmentSession> appSessionList = new ArrayList<MasAppointmentSession>();
			
			appSessionList = session.createCriteria(MasAppointmentSession.class)
					.add(Restrictions.eq("masDepartment.departmentId", departmentId))
					.add(Restrictions.eq("masHospital.hospitalId", hospitalId))
					.add(Restrictions.eq("masAppointmentType.appointmentTypeId", appointmentTypeId))
					.list();
			
			
			if(appSessionList.size()>0) {
				flag = true;
			}else {
				flag = false;
			}
		}catch(Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return flag;
	}

	/*
	 * @Override public String ChangeStatus(long rowId) { String status=""; String
	 * msg=""; String res=""; try { Session session =
	 * getHibernateUtils.getHibernateUtlis().OpenSession(); Transaction tx =
	 * session.beginTransaction(); MasAppointmentSession appSession =
	 * (MasAppointmentSession) session.get(MasAppointmentSession.class, rowId);
	 * if(appSession !=null) { status = appSession.getStatus();
	 * if(status.equalsIgnoreCase("y")) { appSession.setStatus("N");
	 * res="Record Deactivated Successfully"; }else { appSession.setStatus("Y");
	 * res="Record Activated Successfully"; } session.update(appSession);
	 * tx.commit(); msg=res; }
	 * 
	 * }catch(Exception e) {
	 * getHibernateUtils.getHibernateUtlis().CloseConnection(); e.getMessage();
	 * e.printStackTrace(); }finally {
	 * getHibernateUtils.getHibernateUtlis().CloseConnection(); }
	 * 
	 * return msg; }
	 */
	@Override
	public String updateAppointmentSession(long rowId, long departmentId, long appointmentType, String stratTime,
			String endTime, String status) {
		String res="";
		String resStatus="";
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			MasAppointmentSession appSession  = (MasAppointmentSession) session.get(MasAppointmentSession.class, rowId);
			if(appSession !=null) {
				resStatus = appSession.getStatus();
				if(!resStatus.equalsIgnoreCase(status)) {
					appSession.setStatus(status);
				}else {
					MasDepartment md = new MasDepartment();
					md.setDepartmentId(departmentId);
					appSession.setMasDepartment(md);
					
					MasAppointmentType appType= new MasAppointmentType();
					appType.setAppointmentTypeId(appointmentType);
					appSession.setMasAppointmentType(appType);
					
					appSession.setFromTime(stratTime);
					appSession.setToTime(endTime);
				}
				session.update(appSession);
				tx.commit();
				res = "Record Updated Successfully ";
			}else {
				res="Record not updated";
			}
		}catch(Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.getMessage();
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
	 return res;
	}
}

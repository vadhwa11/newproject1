package com.icg.jkt.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icg.jkt.dao.UserManagementDao;
import com.icg.jkt.entity.MasApplication;
import com.icg.jkt.entity.MasTemplate;
import com.icg.jkt.entity.UserApplication;
import com.icg.jkt.entity.Users;
import com.icg.jkt.hibernateutils.GetHibernateUtils;
import com.icg.jkt.utils.HMSUtil;

@Repository
@Transactional
public class UserManagementDaoImpl implements UserManagementDao{

	@Autowired
	GetHibernateUtils getHibernateUtils;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Map<String, List<UserApplication>> getAllUserApplication(JSONObject jsonObject) {
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());
		
		Map<String, List<UserApplication>> mapObj = new HashMap<String, List<UserApplication>>();
		List<UserApplication> userAppList = new ArrayList<UserApplication>();
		List totalMatches = new ArrayList();
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(UserApplication.class);			
					
			if( jsonObject.get("PN")!=null)
			 pageNo = Integer.parseInt(jsonObject.get("PN").toString());
					
			String appName="";			
				 if (jsonObject.has("appName"))
				 {
					 appName = jsonObject.get("appName")+"%";
					  if(jsonObject.get("appName").toString().length()>0 && !jsonObject.get("appName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("appName", appName));
						}
				 }				
				 criteria.addOrder(Order.asc("appName"));
				 totalMatches =criteria.list();
			
			
			 criteria.setFirstResult((pageSize) * (pageNo - 1));		
			 criteria.setMaxResults(pageSize);		
			 userAppList = criteria.list();
			
			 mapObj.put("totalMatches", totalMatches);
			mapObj.put("userAppList", userAppList);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	}

	@Override
	public List<UserApplication> validateUserApplication(String applicationName, String url) {
		List<UserApplication> userAppList=new ArrayList<UserApplication>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().getCurrentSession();
			Criteria criteria = session.createCriteria(UserApplication.class).
					add(Restrictions.or(Restrictions.eq("appName", applicationName), Restrictions.eq("url", url)));
			criteria.list();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return userAppList;
	}

	/*@Override
	public String saveAndUpdateUserApplication(UserApplication userApplication) {
		String result="";
		Criteria criteria = null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().getCurrentSession();
			criteria = session.createCriteria(UserApplication.class).add(Restrictions.eq("id", userApplication.getId()));
			UserApplication uApplication = (UserApplication)criteria.uniqueResult();
			Transaction transaction = session.beginTransaction();
			if(uApplication!=null) {
				uApplication.setAppName(userApplication.getAppName());
				uApplication.setUrl(userApplication.getUrl());
				uApplication.setLastChgDate(userApplication.getLastChgDate());
				uApplication.setUser(userApplication.getUser());
				
				session.update(uApplication);
				transaction.commit();
				result="201";
			}else if(uApplication.getStatus().equalsIgnoreCase(userApplication.getStatus())){
					if(uApplication.getStatus().equalsIgnoreCase("y"))
							uApplication.setStatus("N");
						else
							uApplication.setStatus("Y");
						session.update(uApplication);
						transaction.commit();
						result="202";
				}else {
					session.save(userApplication);
					transaction.commit();
					result="200";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}*/

	@Override
	public String updateUserApplication(JSONObject jsonObject) {
		String result = "";
		Timestamp timestamp = new Timestamp(new Date().getTime());
		try {
			if (jsonObject != null) {
				System.out.println("jsonObject :: " + jsonObject);
				List<UserApplication> userApplicationList = new ArrayList<UserApplication>();
				Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(UserApplication.class);
				String hStatus = "";
				Long appId;
				if (jsonObject.has("id")) {
					appId = Long.parseLong(jsonObject.get("id").toString());
					criteria.add(Restrictions.eq("id", appId));
					userApplicationList = criteria.list();

					for (int i = 0; i < userApplicationList.size(); i++) {
						Long applicationId = userApplicationList.get(i).getId();
						System.out.println("applicationId :: " + applicationId);
						Object object = session.load(UserApplication.class, applicationId);
						UserApplication userApplication = (UserApplication) object;

						Transaction transaction = session.beginTransaction();

						if (jsonObject.has("status")) {
							System.out.println("status ::  " + jsonObject.has("status"));
							hStatus = jsonObject.get("status").toString();
							System.out.println("hStatus :: " + hStatus);
							if (hStatus.equalsIgnoreCase("active") || hStatus.equalsIgnoreCase("inactive")) {
								if (hStatus.equalsIgnoreCase("active"))
									userApplication.setStatus("Y");
								else
									userApplication.setStatus("N");
								session.update(userApplication);
								transaction.commit();
								result = "200";

							} else {
								userApplication.setAppName(jsonObject.get("appName").toString().toUpperCase());
								userApplication.setUrl(jsonObject.get("url").toString());
								
								Users user = new Users();
								user.setUserId(new Long(1));
								userApplication.setUser(user);
								
								userApplication.setLastChgDate(timestamp);
								session.update(userApplication);
								transaction.commit();
								result = "200";
							}

						}
					}

				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public Map<String, List<MasTemplate>> getAllTemplate(JSONObject jsonObject) {
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());
				
		Map<String, List<MasTemplate>> mapObj = new HashMap<String, List<MasTemplate>>();
		List<MasTemplate> templateList = new ArrayList<MasTemplate>();
		List totalMatches = new ArrayList();
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasTemplate.class);			
					
			if(jsonObject.get("PN")!=null)
			 pageNo = Integer.parseInt(jsonObject.get("PN").toString());
					
			String tempName="";			
				 if (jsonObject.has("templateName"))
				 {
					 tempName = jsonObject.get("templateName")+"%";
					  if(jsonObject.get("templateName").toString().length()>0 && !jsonObject.get("templateName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("templateName", tempName));
						}
				 }				
				 criteria.addOrder(Order.asc("templateName"));
				 totalMatches =criteria.list();			
			 criteria.setFirstResult((pageSize) * (pageNo) - 1);		
			 criteria.setMaxResults(pageSize);		
			 templateList = criteria.list();
			
			 mapObj.put("totalMatches", totalMatches);
			mapObj.put("templateList", templateList);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	}

	

	@Override
	public Map<String, List<MasApplication>> getApplicationAutoComplete(JSONObject jsonObject) {
		Map<String, List<MasApplication>> mapObj = new HashMap<String, List<MasApplication>>();
		List<MasApplication> masApplicationsList = new ArrayList<MasApplication>();
		List maxAppIdlist = new ArrayList();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasApplication.class);
			criteria.setProjection(Projections.max("applicationtId").as("applicationtId"));	
			maxAppIdlist = criteria.list();
			
			Criteria criteria1 = session.createCriteria(MasApplication.class);
			if (jsonObject.has("applicationName"))
					 {
						String appName = "%"+jsonObject.get("applicationName")+"%";
						  if(jsonObject.get("applicationName").toString().length()>0 && !jsonObject.get("applicationName").toString().trim().equalsIgnoreCase("")) {
							  criteria1.add(Restrictions.like("applicationName", appName));
							}
					 }
					
			masApplicationsList = criteria1.list();
			
			mapObj.put("maxAppIdlist", maxAppIdlist);
			mapObj.put("masApplicationsList", masApplicationsList);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	}

	/*@Override
	public List<MasApplication> getApplicationAutoComplete(JSONObject jsonObject) {
		List<MasApplication> masApplicationsList = new ArrayList<MasApplication>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasApplication.class);				
				if (jsonObject.has("applicationName"))
				 {
					String appName = "%"+jsonObject.get("applicationName")+"%";
					  if(jsonObject.get("applicationName").toString().length()>0 && !jsonObject.get("applicationName").toString().trim().equalsIgnoreCase("")) {
						  criteria.add(Restrictions.like("applicationName", appName));
						}
				 }
				
				masApplicationsList = criteria.list();		
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return masApplicationsList;
	}*/
	

}

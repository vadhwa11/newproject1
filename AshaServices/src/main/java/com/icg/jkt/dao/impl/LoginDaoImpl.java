package com.icg.jkt.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icg.jkt.dao.LoginDao;
import com.icg.jkt.entity.EmpProfile;
import com.icg.jkt.entity.Registration;
import com.icg.jkt.hibernateutils.GetHibernateUtils;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	GetHibernateUtils getHibernateUtils;

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Registration> validateExitingUserInfo(String user_name, String email_id) {

		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(Registration.class);
		cr.add(Restrictions.or(Restrictions.eq("user_name", user_name),
				Restrictions.eq("email_id", email_id)));
		List<Registration> list = cr.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	@Override
	public String empRegistration(Registration ob) {

		// String currentdate=ProjectUtils.getCurrentDate();
		// ob.setRegdate(currentdate);
		String Result = null;

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(Registration.class);
			Transaction t = session.beginTransaction();
			Serializable id = session.save(ob);
			t.commit();
			if (id != null) {
				Result = "200";
			} else {
				Result = "500";
			}
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			Result = e.getMessage();
			e.printStackTrace();
		}
		return Result;
	}

	@Override
	public String registration(Registration registration) {
		String Result = null;

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(Registration.class);
			Transaction t = session.beginTransaction();
			Serializable id = session.save(registration);
			t.commit();
			if (id != null) {
				Result = "200";
			} else {
				Result = "500";
			}
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			Result = e.getMessage();
			e.printStackTrace();
		}
		return Result;
	}

	@Override
	public List<EmpProfile> validateEmpUserInfo(String emp_id) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(EmpProfile.class);
		cr.add(Restrictions.or(Restrictions.eq("emp_id", emp_id)));
		List<EmpProfile> list = cr.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	@Override
	public String empCheck(EmpProfile empCheck) {
		String Result = null;

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(EmpProfile.class);
			Transaction t = session.beginTransaction();
			Serializable id = session.save(empCheck);
			t.commit();
			if (id != null) {
				Result = "200";
			} else {
				Result = "500";
			}
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			Result = e.getMessage();
			e.printStackTrace();
		}
		return Result;
	}
}

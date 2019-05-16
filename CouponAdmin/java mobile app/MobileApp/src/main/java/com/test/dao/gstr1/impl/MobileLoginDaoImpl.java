package com.test.dao.gstr1.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.test.dao.genericdao.AbstractDao;
import com.test.dao.gstr1.MobileLoginDao;
import com.test.entity.MobileLoginEntity;

@Repository
public class MobileLoginDaoImpl extends AbstractDao<Integer, MobileLoginEntity> implements MobileLoginDao {

	@Override
	public MobileLoginEntity doLogin(MobileLoginEntity mobileLoginEntity) {

		Session session = getNewSession();
		Transaction t = session.getTransaction();
		t.begin();
		Criteria cr = session.createCriteria(MobileLoginEntity.class);
		cr.add(Restrictions.eq("username", mobileLoginEntity.getUsername()));
		cr.add(Restrictions.eq("password", mobileLoginEntity.getPassword()));
		MobileLoginEntity mobileLogin = (MobileLoginEntity) cr.uniqueResult();

		session.close();
		if (mobileLogin != null) {
			return mobileLogin;
		} else {
			mobileLoginEntity.setName("Login Failed");
			return mobileLoginEntity;
		}

	}

}

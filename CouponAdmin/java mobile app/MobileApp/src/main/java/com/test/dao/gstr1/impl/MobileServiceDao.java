package com.test.dao.gstr1.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.test.dao.genericdao.AbstractDao;
import com.test.entity.CoupanGenration;
import com.test.entity.Coupan_Distributor;
import com.test.entity.VidEntity;

@Repository
public class MobileServiceDao extends AbstractDao<Integer, VidEntity> {

	public String CoupanRagistration(VidEntity VidEntity) {
		Session session = getNewSession();
		String result = null;

		Transaction t = session.beginTransaction();
		session.update(VidEntity);
		t.commit();
		// Criteria cr = session.createCriteria(VidEntity.class);
		// cr.add(Restrictions.eq("vochuer_id", VidEntity.getVochuer_id()));

		/*
		 * VidEntity cdnr = (VidEntity) cr.uniqueResult(); if (cdnr != null) {
		 * Integer flag = cdnr.getFlag();
		 * 
		 * if (flag == 0) { try {
		 * cdnr.setActivate_date(VidEntity.getActivate_date());
		 * cdnr.setAddress(VidEntity.getAddress());
		 * cdnr.setEmail(VidEntity.getEmail()); cdnr.setFlag(1);
		 * cdnr.setGstn_no(VidEntity.getGstn_no());
		 * cdnr.setMobile(VidEntity.getMobile());
		 * cdnr.setName(VidEntity.getName()); cdnr.setPan(VidEntity.getPan());
		 * cdnr.setPwd(VidEntity.getPwd());
		 * cdnr.setUser_name(VidEntity.getUser_name());
		 * cdnr.setCompany_name(VidEntity.getCompany_name());
		 * cdnr.setReference(VidEntity.getReference()); Transaction t =
		 * session.beginTransaction(); session.update(cdnr); t.commit(); result
		 * = "vochuer_id activate successfully at  " +
		 * VidEntity.getActivate_date().toString(); } catch (Exception e) {
		 * e.printStackTrace(); } } else { result =
		 * "vochuer_id already activated"; } } else { result =
		 * "not a valid vochuer_id"; }
		 */
		return result;
	}

	public String CoupanDistributor(Object Entitty) {
		Session session = getNewSession();
		Transaction t = session.beginTransaction();
		Serializable id = session.save(Entitty);
		t.commit();
		return id.toString();

	}

	public VidEntity checkavailability(Integer flag) {
		Session session = getNewSession();
		Criteria cr = session.createCriteria(VidEntity.class);
		cr.add(Restrictions.eq("vochuer_id", flag));
		List<VidEntity> withoutActivateCoupan = cr.list();
		return (VidEntity) withoutActivateCoupan;

	}

	public String CoupanGeneration(CoupanGenration generation) {
		Session session = getNewSession();
		Transaction t = session.beginTransaction();
		Serializable id = session.save(generation);
		t.commit();
		return id.toString();
	}

	public List<CoupanGenration> FatchCoupanForDistribution(Coupan_Distributor Coupan_Distributor) {
		Session session = getNewSession();
		Transaction t = session.beginTransaction();
		Criteria cr = session.createCriteria(CoupanGenration.class);
		cr.add(Restrictions.eq("flag", 0));
		cr.setMaxResults(15);
		List<CoupanGenration> list = cr.list();
		for (CoupanGenration coupanGenration : list) {
			coupanGenration.setFlag(1);
			coupanGenration.setDistriButor_id(1);
			coupanGenration.setDistributor_name(Coupan_Distributor.getName());
			session.update(coupanGenration);
		}

		t.commit();
		return list;
	}

	public List<CoupanGenration> ShowActiveCoupon() {
		Session session = getNewSession();

		Criteria cr = session.createCriteria(CoupanGenration.class);
		cr.add(Restrictions.eq("flag", 1));

		List<CoupanGenration> list = cr.list();

		return list;
	}

	public List<CoupanGenration> availableCoupon() {
		Session session = getNewSession();

		Criteria cr = session.createCriteria(CoupanGenration.class);
		cr.add(Restrictions.eq("flag", 0));

		List<CoupanGenration> list = cr.list();

		return list;
	}

}

package com.icg.jkt.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icg.jkt.dao.GenericApiDao;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.Registration;
import com.icg.jkt.hibernateutils.GetHibernateUtils;

@Repository
public class GenericApiDaoImpl implements GenericApiDao {

	@Autowired
	GetHibernateUtils getHibernateUtils;

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<MasDepartment> getDepartmentList() {
		
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(MasDepartment.class);
	//r.add(Restrictions.eq("user_name", string));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("DEPARTMENT_CODE").as("DEPARTMENT_CODE"));
		projectionList.add(Projections.property("DEPARTMENT_NAME").as("DEPARTMENT_NAME"));
		projectionList.add(Projections.property("DEPARTMENT_ID").as("DEPARTMENT_ID"));
		cr.setProjection(projectionList);
		List<MasDepartment> list = cr.setResultTransformer(new AliasToBeanResultTransformer(MasDepartment.class)).list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}
	
@Override
public MasEmployee checkEmp(Long i) {
		
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(MasEmployee.class);
	    cr.add(Restrictions.eq("EMPLOYEE_ID", i));
	    MasEmployee list = (MasEmployee) cr.uniqueResult();
	    getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

}

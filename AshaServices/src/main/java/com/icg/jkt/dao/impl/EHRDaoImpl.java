package com.icg.jkt.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.icg.jkt.dao.EHRDao;
import com.icg.jkt.hibernateutils.GetHibernateUtils;

public class EHRDaoImpl implements EHRDao {
	
	@Autowired
	GetHibernateUtils getHibernateUtils;	

	@Override
	public Map<String, Object> getPatientSummary(HashMap<String, Object> jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		long patientId = Long.parseLong((String)jsondata.get("patient_id"));
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();		
		/*session.doWork(new Work() {
		    @Override
		    public void execute(Connection connection) throws SQLException {
		        //connection, finally!
		    }
		});
		CallableStatement call = conn.prepareCall("{ call Procedure_Name('448525', ?, ?,0, ?) }");*/
		
	/*	Query query = session.createSQLQuery(
				"CALL ASP_EHR_PATIENT_DATA(:stockCode)")
				.addEntity(USERS.class)
				.setParameter("stockCode", "7277");*/
						
		return null;
	}

}

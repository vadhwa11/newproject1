package com.icg.jkt.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icg.jkt.dao.ReportDao;
import com.icg.jkt.hibernateutils.GetHibernateUtils;

@Repository
public class ReportDaoImpl implements ReportDao {
	
	@Autowired
	GetHibernateUtils getHibernateUtils;

	@Override
	public Map<String, Object> getConnectionForReport() {
		System.out.println("inside connection report method");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Connection conn = getHibernateUtils.getHibernateUtlis().getConnection();
			map.put("conn", conn);
		}catch(Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		}
		return map;
	}	

	

}

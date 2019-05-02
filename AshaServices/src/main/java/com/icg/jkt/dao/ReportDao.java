package com.icg.jkt.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface ReportDao {

	Map<String, Object> getConnectionForReport();
	

}

package com.icg.jkt.dao;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalExamDao {

	Map<String, Object> getMedicalExamDetails(JSONObject jsonObject);
}

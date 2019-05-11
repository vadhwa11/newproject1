package com.icg.jkt.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.icg.jkt.dao.impl.EHRDaoImpl;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EHRDaoImpl ehr = new EHRDaoImpl();
		HashMap<String, Object> jsondata = new HashMap<>();
		jsondata.put("patient_id", 1);
		HttpServletRequest request= null;
		HttpServletResponse response = null;
		Map<String, Object> map = ehr.getPatientSummary(jsondata, request, response);
		String result = map.toString();
		System.out.println("result "+result);
		
	}

}

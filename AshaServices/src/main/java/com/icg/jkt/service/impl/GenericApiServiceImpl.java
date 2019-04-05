package com.icg.jkt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icg.jkt.dao.GenericApiDao;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.service.GenericApiService;

@Repository
public class GenericApiServiceImpl implements GenericApiService {

	@Autowired
	GenericApiDao gad;
	
	/////////////////////// MasDepartmrent get Method /////////////////////////
	@Override
	public String departmentList(HashMap<String, String> jsondata, HttpServletRequest request) {
		JSONObject json = new JSONObject();
        try
        {
		if (jsondata.get("EMPLOYEE_ID") == null || jsondata.get("EMPLOYEE_ID").toString().trim().equals(""))
		{
			return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID as a  key or value or it is null\"}";
		} 
		else
		{
			    MasEmployee checkEmp = gad.checkEmp(Long.parseLong(jsondata.get("EMPLOYEE_ID").toString()));
			    if(checkEmp!=null)
			    {
				List<MasDepartment> mst_depart = gad.getDepartmentList();
			    if (mst_depart.size() == 0)
			    {
			    	 return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
			    } 
			    else 
			    {
			    	json.put("departmentList", checkEmp);
			    	json.put("departmentList", mst_depart);
			    	json.put("msg", "department List  get  sucessfull... ");
			    	json.put("status", "1");

			   }

		}
	   else
	   {
		   return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";
    	
		}

		return json.toString();
	}
        }finally
        {
        	System.out.println("Exception Occured");
        }
	}
}

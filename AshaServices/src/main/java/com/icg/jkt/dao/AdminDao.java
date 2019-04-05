package com.icg.jkt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasDoctorMapping;
import com.icg.jkt.entity.UserApplication;

@Repository
public interface AdminDao {
	
	public List<MasDepartment> getDepartmentList(HashMap<String, Object> map);
	
	public List<MasDoctorMapping> getDoctorList(HashMap<String, Object> jsondata);
	
	public Map<String, Object> getDoctorRoaster(HashMap<String, Object> jsondata);
	
	public String  submitDepartmentRoaster(List<String[]> allrowdata, String changeDate, String changeTime, Long changeBy, Long dept_id, Long hostpitalID);
	
	
}

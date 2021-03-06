package com.icg.jkt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.DgMasInvestigation;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasDisposal;
import com.icg.jkt.entity.MasEmpanelledHospital;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasFrequency;
import com.icg.jkt.entity.MasIcd;
import com.icg.jkt.entity.MasItemClass;
import com.icg.jkt.entity.MasNursingCare;
import com.icg.jkt.entity.MasStoreItem;
import com.icg.jkt.entity.MasStoreUnit;
import com.icg.jkt.entity.OpdTemplate;
import com.icg.jkt.entity.Users;

@Repository
public interface OpdMasterDao {

	List<MasDepartment> getDepartmentList();

	MasEmployee checkEmp(Long i);

	List<MasIcd> getIcd();

	List<DgMasInvestigation> getInvestigationList(Long mainChargeCode);

	List<MasStoreItem> getMasStoreItem();

	List<MasFrequency> getMasFrequency();

	List<OpdTemplate> getTemplateName(String templateType);

	List<MasEmpanelledHospital> getEmpanelledHospital();

	List<MasDisposal> getMasDisposal();

	Map<String, Object> getMasNursingCare(HashMap<String, Object> jsonData);

	Map<String,Object> getTemplateInvestigation(HashMap<String,Object> JsonData);
	
	Map<String, Object> getTemplateTreatment(HashMap<String, Object> jsonData);

	Users checkUser(Long i);
	Map<String, Object> executeDbProcedure(long hospitalId,long userId);

	Map<String, Object> executeDbProcedureforStatistics(long userhospitalId, long combohospitalId, long userId);

	List<MasItemClass> getMasItemClass();

	List<MasStoreUnit> getMasStoreUnit();
	
		
	Map<String, Object> executeProcedureForDashBoard(Map<String, Object> jsondata, HttpServletRequest request, HttpServletResponse response);



	

}

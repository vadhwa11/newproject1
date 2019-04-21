package com.icg.jkt.dao;

import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasMaritalStatus;
import com.icg.jkt.entity.MasMedicalCategory;
import com.icg.jkt.entity.MasReligion;
import com.icg.jkt.entity.MasState;
import com.icg.jkt.entity.MasTrade;
import com.icg.jkt.entity.MasUnit;
import com.icg.jkt.entity.OpdPatientDetail;
import com.icg.jkt.entity.OpdPatientHistory;
import com.icg.jkt.entity.Patient;

@Repository
public interface OpdPatientDetailDao {
	
	public OpdPatientDetail getOpdPatientDetails(Long visitId);
	
	public OpdPatientDetail getOpdPatientDetailsByOpdPatientDetailId(Long opdPatientDetailId);
	public Patient getPatientByPatientId(Long patientId);
	public MasMaritalStatus getMasMaritalStatusByMaritalId(Long MaritalId,String masMaritalStatus);
	
	public MasAdministrativeSex getMasAdministrativeSexByGender(String gender);
	public MasMedicalCategory getMasMedicalCategoryByCategory(String category);
	
	public OpdPatientHistory getOpdPatientHistoryByVisitId(Long visitId);
	public MasEmployee getMasEmployeeByFirstName(String empname);
	public MasReligion getMasReligionByReligion(String religion);
	public MasUnit getMasUnitByUnitId(String unitName);
	public MasState getMasStateByStateName(String stateName);
	public MasTrade getMasTradeByTradeName(String masTradeName);
	
	
	
	
}


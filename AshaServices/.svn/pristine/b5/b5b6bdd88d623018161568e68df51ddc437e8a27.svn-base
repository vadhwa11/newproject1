package com.icg.jkt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasBloodGroup;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasIdentificationType;
import com.icg.jkt.entity.MasMedicalCategory;
import com.icg.jkt.entity.MasRegistrationType;
import com.icg.jkt.entity.MasRelation;
import com.icg.jkt.entity.MasServiceType;
import com.icg.jkt.entity.Patient;
import com.icg.jkt.entity.Visit;

@Repository
public interface PatientRegistrationDao {


	Map<String,Object> findPatientAndDependentFromEmployee(String serviceNo);
	List<MasDepartment> geDepartmentList();
	List<MasBloodGroup> geBloodGroupList();
	List<MasMedicalCategory> getMedicalCategoryList();
	Map<String, Object> getTokenNoForDepartmentMultiVisit(long departmentId, long hospitalId,long appointmentTypeId);
	List<MasRegistrationType> getRegistrationTypeList();
	List<MasAdministrativeSex> getGenderList();
	List<MasIdentificationType> getIdentificationList();
	List<MasServiceType> getServiceTypeList();
	long getPatientFromUhidNo(long uhidNO);
	long saveVisitForRegisteredPatient(Visit visit);
	long savePatient(Patient patient);
	List<MasRelation> getRelationList();
	Map<String, List<Patient>> searchOthersRegisteredPatient(long uhinNo, String patientName, String serviceNo,
			String mobileNo);

}

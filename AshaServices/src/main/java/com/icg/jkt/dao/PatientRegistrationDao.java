package com.icg.jkt.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasAppointmentType;
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
	List<MasDepartment> getDepartmentList();
	List<MasBloodGroup> getBloodGroupList();
	List<MasMedicalCategory> getMedicalCategoryList();
	Map<String, Object> getTokenNoForDepartmentMultiVisit(long departmentId, long hospitalId,long appointmentTypeId, String visitFlag, String visitDate);
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
	Map<String, Object> getPatientTypeCodeAndRelationCode(long patientRelationId, long registrationTypeId);
	String getHinIdOthers(String patientCode);
	long getAppointmentSessionId(long hospitalId, long departmentId, long appointmentTypeId);
	//by Anita
	Map<String, Object> findPatientAndVisitList(JSONObject json, String serviceNo, Timestamp fromdateTime, Timestamp todateTime);
	void cancelAppointment(long visitId);

	List<Patient> patientListForUploadDocument(String serviceNo);
	String rescheduleAppointment(long visitId, long tokenNo, Timestamp dateTime);

	List<Visit> getPatientAppointmentHistory(long uhidNo, String startDate, String endDate);
	boolean getPatientVisitCancellation(long visitId);
	
	Map<String, Object> findOpdList(String serviceNo);
	Map<String, Object> checkVisitExist(long deptId, long appointmentTypeId, long hospitalId, long uhidNo,
			String visitFlag, Date visitDate);
	List<MasAppointmentType> getAppointmentTypeList();
	boolean checkExistingAppointmentForPatient(Date visitDate, long hospitalId, long departmentId, long patientId,
			long appointmentType);
	boolean checkExistingOtherPatient(String mobileNo);
	


}

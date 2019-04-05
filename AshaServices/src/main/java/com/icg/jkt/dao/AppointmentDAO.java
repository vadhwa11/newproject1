package com.icg.jkt.dao;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.AppSetup;
import com.icg.jkt.entity.MasAppointmentSession;
import com.icg.jkt.entity.MasAppointmentType;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasHospital;

@Repository
public interface AppointmentDAO {

	Map<String, Object> getRecordsForDoctorAppointment();
	Map<String, Object> getappointmentSetupDetails(long deptId, long appointmentTypeId);
	List<MasAppointmentSession> getlocationWiseAppointmentType(long deptId);
	String saveAppointmentSetup(AppSetup appSetup);
	AppSetup getAppSetupObject(long appointmentId);
	List<MasHospital> getHospitalList();
	List<MasDepartment> getDepartmentList();
	List<MasAppointmentType> getAppointmentTypeList();
	String submitAppointmentSession(MasAppointmentSession appointmentSession);
	Map<String, List<MasAppointmentSession>> getAllAppointmentSession(JSONObject jsonObject);
	List<MasAppointmentSession> validateAppointmentSetup(AppSetup appSetup);

}

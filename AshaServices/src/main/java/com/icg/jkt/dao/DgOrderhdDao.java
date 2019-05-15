package com.icg.jkt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.DgOrderdt;
import com.icg.jkt.entity.DgOrderhd;
import com.icg.jkt.entity.MasIcd;
import com.icg.jkt.entity.MasStoreItem;
import com.icg.jkt.entity.OpdObesityHd;
import com.icg.jkt.entity.OpdPatientDetail;
import com.icg.jkt.entity.OpdPatientHistory;
import com.icg.jkt.entity.Patient;
import com.icg.jkt.entity.PatientPrescriptionDt;
import com.icg.jkt.entity.PatientPrescriptionHd;
import com.icg.jkt.entity.ProcedureDt;
import com.icg.jkt.entity.ProcedureHd;
import com.icg.jkt.entity.ReferralPatientDt;
import com.icg.jkt.entity.ReferralPatientHd;

@Repository
public interface DgOrderhdDao {
	
	public List<Object[]> getDgMasInvestigations(Long visitId);
	public List<Object[]> getTreatementDetail(Long visitId);
	public Map<String,Object> getDgOrderDtByOrderHdIdAndInvestigationId(List<String> orderDtIds, List<String> investigationIds );
	public DgOrderdt getDgOrderDtByDgOrderdtId(Long dgOrderDtId);
	public Long saveOrUpdateDgOrderdt(DgOrderdt dgOrderDt);
	public Long saveOrUpdateDgOrderHd(DgOrderhd dgOrderhd);
	
	public Long saveOrUpdatePatient(Patient patient);
	public Long updatePatientHistory(OpdPatientHistory patient);
	public Long updateOpdPatientDetail(OpdPatientDetail opdPatientDetail) ;
	public List<OpdPatientHistory>getPatientHistoryList(Long visitId);
	public PatientPrescriptionDt getMasStoreItemByPatientPrecriptionDtId(Long itemId);
	public Long saveOrUpdatePatientPrescriptionHd(PatientPrescriptionHd patientPrescriptionHd);
	
	public Long saveOrUpdatePatientPrecriptionDt(PatientPrescriptionDt patientPrescriptionDt);
	public List<Object[]>getReferralPatientDtList(Long opdPatientDetailId);
	public ReferralPatientDt getReferralPatientDtByReferaldtId(Long referalDtId);
	
	public ReferralPatientHd getPatientReferalHdByExtHospitalId(Long extHospitalId,Long opdPatientDetailId) ;
	public Long saveOrUpdateReferalDt(ReferralPatientDt referralPatientDt);
	public Long saveOrUpdateReferalHd(ReferralPatientHd referralPatientHd);
	public String deleteInvestigatRow(Long dgOrderDtId,String flag);
	public DgOrderhd getDgOrderhdByDgOrderhdId(Long dgOrderhdId);
	public String deleteObject(String  className,String columnName,Long columnValue);
	public PatientPrescriptionHd getPatientPrecriptionHdByVisitId(Long visitId);
	
	public DgOrderhd getDgOrderHdByVisitId(Long visitId);
	
	public List<Object[]>getProcedureDtList(Long opdPatientDetailId,Long visitId);
	public Map<String,Object> getOpdObesityHd(Long visitId);
	
	//public String deleteObesityMark(OpdObesityHd pdObesityHd);
	
	public  String updateAndInsertDischargeICDCode(String [] icdCodeArray,Long visitId,Long patientId,Long opdPatientDetailId);
	List<MasIcd>getMasIcdByVisitPatAndOpdPD(Long visitId,Long patientId,Long opdPatientDetailId);
	public String deleteChangeIcdCode(Long  dischargeIcdCodeId,Long visitId,Long opdPatientDetailId,Long patientId);
	
	public String deleteForReferalTypeNo(List<ReferralPatientDt> listReferralPatientDt,
			List<ReferralPatientHd> listReferralPatientHd);
	public Map<String,Object> getPatientReferalHdByVisitIdAndOpdPdAndPatient(Long patientId,Long opdPatientDetailId) ;
	public ProcedureHd getProcedureHdByProcedureHdId(Long procedureHdId);
	public ProcedureDt getProcedureDtByProcedureDtId(Long procedureDtId);
	ProcedureHd getProcedureHdByVisitIdAndType(Long visitId,String procedureType);
	public Long saveOrUpdateProcedureHd(ProcedureHd procedureHd) ;
	public Long saveOrUpdateProcedureDd(ProcedureDt procedureDt);
	public PatientPrescriptionHd getPatientPrecriptionHdByPPHdId(Long patientPreciptionHdId);
	
}

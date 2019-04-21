package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the INPATIENT database table.
 * 
 */
@Entity
@NamedQuery(name="Inpatient.findAll", query="SELECT i FROM Inpatient i")
public class Inpatient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INPATIENT_INPATIENTID_GENERATOR", sequenceName="INPATIENT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INPATIENT_INPATIENTID_GENERATOR")
	@Column(name="INPATIENT_ID")
	private long inpatientId;

	@Column(name="AD_NO")
	private String adNo;

	@Column(name="AD_NO_TYPE")
	private String adNoType;

	@Column(name="AD_STATUS")
	private String adStatus;

	@Column(name="AD_WARD_ID")
	private BigDecimal adWardId;

	@Column(name="ADMISSION_TYPE_ID")
	private BigDecimal admissionTypeId;

	private String age;

	@Column(name="AT_OR_DT")
	private String atOrDt;

	@Column(name="ATTACHED_PATIENT")
	private String attachedPatient;

	@Column(name="ATTACHED_UNIT")
	private String attachedUnit;

	@Column(name="BED_ID")
	private BigDecimal bedId;

	@Column(name="CASE_TYPE_ID")
	private BigDecimal caseTypeId;

	@Column(name="CONDITION_STATUS")
	private String conditionStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_OF_ADDMISSION")
	private Date dateOfAddmission;

	@Column(name="DELIVERY_OT_STATUS")
	private String deliveryOtStatus;

	@Column(name="DELIVERY_STATUS")
	private String deliveryStatus;

	@Column(name="DEPARTMENT_ID")
	private BigDecimal departmentId;

	@Column(name="DIAGNOSIS_ID")
	private BigDecimal diagnosisId;

	@Column(name="DIET_ID")
	private BigDecimal dietId;

	@Column(name="DIET_TYPE")
	private String dietType;

	@Column(name="DISCHARGE_DATE")
	private Timestamp dischargeDate;

	@Column(name="DOCTOR_ID")
	private BigDecimal doctorId;

	@Column(name="DOCUMENT_ID")
	private BigDecimal documentId;

	@Column(name="EMPLOYEE_ID")
	private BigDecimal employeeId;

	@Column(name="FRW_ISSUED")
	private String frwIssued;

	@Column(name="FRW_SL_NO")
	private String frwSlNo;

	@Column(name="HIN_NO")
	private String hinNo;

	@Column(name="HL7_FLAG")
	private String hl7Flag;

	@Column(name="HSR_AMOUNT")
	private BigDecimal hsrAmount;

	@Column(name="HSR_RECEIPT_NO")
	private String hsrReceiptNo;

	@Column(name="ICD_ID")
	private BigDecimal icdId;

	@Column(name="INIT_DIAGNOSIS")
	private String initDiagnosis;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	@Column(name="\"LIST\"")
	private String list;

	@Column(name="LIST_DATE")
	private Timestamp listDate;

	@Column(name="LR_TRANSFER_STATUS")
	private String lrTransferStatus;

	private String mlc;

	@Column(name="MOTHER_AD_NO")
	private String motherAdNo;

	@Column(name="PARENT_AD_NO")
	private String parentAdNo;

	@Column(name="PATIENT_CONDITION")
	private String patientCondition;

	@Column(name="PLACE_OF_ISSUE")
	private String placeOfIssue;

	@Column(name="PREV_AD_NO")
	private String prevAdNo;

	@Column(name="PREV_DIAGNOSIS")
	private String prevDiagnosis;

	@Column(name="PREV_DISPOSAL")
	private String prevDisposal;

	@Column(name="PREV_HOSPITAL_NAME")
	private String prevHospitalName;

	@Column(name="PREVIOUS_AD_NO")
	private String previousAdNo;

	private String priority;

	@Column(name="RECORD_OFFICE_ADDRESS_ID")
	private BigDecimal recordOfficeAddressId;

	private String remarks;

	@Column(name="SERVICE_CARD_STATUS")
	private String serviceCardStatus;

	@Column(name="STAFF_SL_NO")
	private BigDecimal staffSlNo;

	private String status;

	@Column(name="SURGERY_STATUS")
	private String surgeryStatus;

	@Column(name="TIME_OF_ADDMISSION")
	private String timeOfAddmission;

	@Column(name="TRANS_FROM")
	private String transFrom;

	private String vip;

	//bi-directional many-to-one association to MasHospital
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOSPITAL_ID")
	private MasHospital masHospital;

	//bi-directional many-to-one association to Patient
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PATIENT_ID")
	private Patient patient;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LAST_CHG_BY")
	private Users user1;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private Users user2;

	//bi-directional many-to-one association to Visit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="VISIT_ID")
	private Visit visit;

	//bi-directional many-to-one association to ProcedureHd
	@OneToMany(mappedBy="inpatient")
	@JsonBackReference
	private List<ProcedureHd> procedureHds;

	public Inpatient() {
	}

	public long getInpatientId() {
		return this.inpatientId;
	}

	public void setInpatientId(long inpatientId) {
		this.inpatientId = inpatientId;
	}

	public String getAdNo() {
		return this.adNo;
	}

	public void setAdNo(String adNo) {
		this.adNo = adNo;
	}

	public String getAdNoType() {
		return this.adNoType;
	}

	public void setAdNoType(String adNoType) {
		this.adNoType = adNoType;
	}

	public String getAdStatus() {
		return this.adStatus;
	}

	public void setAdStatus(String adStatus) {
		this.adStatus = adStatus;
	}

	public BigDecimal getAdWardId() {
		return this.adWardId;
	}

	public void setAdWardId(BigDecimal adWardId) {
		this.adWardId = adWardId;
	}

	public BigDecimal getAdmissionTypeId() {
		return this.admissionTypeId;
	}

	public void setAdmissionTypeId(BigDecimal admissionTypeId) {
		this.admissionTypeId = admissionTypeId;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAtOrDt() {
		return this.atOrDt;
	}

	public void setAtOrDt(String atOrDt) {
		this.atOrDt = atOrDt;
	}

	public String getAttachedPatient() {
		return this.attachedPatient;
	}

	public void setAttachedPatient(String attachedPatient) {
		this.attachedPatient = attachedPatient;
	}

	public String getAttachedUnit() {
		return this.attachedUnit;
	}

	public void setAttachedUnit(String attachedUnit) {
		this.attachedUnit = attachedUnit;
	}

	public BigDecimal getBedId() {
		return this.bedId;
	}

	public void setBedId(BigDecimal bedId) {
		this.bedId = bedId;
	}

	public BigDecimal getCaseTypeId() {
		return this.caseTypeId;
	}

	public void setCaseTypeId(BigDecimal caseTypeId) {
		this.caseTypeId = caseTypeId;
	}

	public String getConditionStatus() {
		return this.conditionStatus;
	}

	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}

	public Date getDateOfAddmission() {
		return this.dateOfAddmission;
	}

	public void setDateOfAddmission(Date dateOfAddmission) {
		this.dateOfAddmission = dateOfAddmission;
	}

	public String getDeliveryOtStatus() {
		return this.deliveryOtStatus;
	}

	public void setDeliveryOtStatus(String deliveryOtStatus) {
		this.deliveryOtStatus = deliveryOtStatus;
	}

	public String getDeliveryStatus() {
		return this.deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public BigDecimal getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(BigDecimal departmentId) {
		this.departmentId = departmentId;
	}

	public BigDecimal getDiagnosisId() {
		return this.diagnosisId;
	}

	public void setDiagnosisId(BigDecimal diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public BigDecimal getDietId() {
		return this.dietId;
	}

	public void setDietId(BigDecimal dietId) {
		this.dietId = dietId;
	}

	public String getDietType() {
		return this.dietType;
	}

	public void setDietType(String dietType) {
		this.dietType = dietType;
	}

	public Timestamp getDischargeDate() {
		return this.dischargeDate;
	}

	public void setDischargeDate(Timestamp dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public BigDecimal getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(BigDecimal doctorId) {
		this.doctorId = doctorId;
	}

	public BigDecimal getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(BigDecimal documentId) {
		this.documentId = documentId;
	}

	public BigDecimal getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(BigDecimal employeeId) {
		this.employeeId = employeeId;
	}

	public String getFrwIssued() {
		return this.frwIssued;
	}

	public void setFrwIssued(String frwIssued) {
		this.frwIssued = frwIssued;
	}

	public String getFrwSlNo() {
		return this.frwSlNo;
	}

	public void setFrwSlNo(String frwSlNo) {
		this.frwSlNo = frwSlNo;
	}

	public String getHinNo() {
		return this.hinNo;
	}

	public void setHinNo(String hinNo) {
		this.hinNo = hinNo;
	}

	public String getHl7Flag() {
		return this.hl7Flag;
	}

	public void setHl7Flag(String hl7Flag) {
		this.hl7Flag = hl7Flag;
	}

	public BigDecimal getHsrAmount() {
		return this.hsrAmount;
	}

	public void setHsrAmount(BigDecimal hsrAmount) {
		this.hsrAmount = hsrAmount;
	}

	public String getHsrReceiptNo() {
		return this.hsrReceiptNo;
	}

	public void setHsrReceiptNo(String hsrReceiptNo) {
		this.hsrReceiptNo = hsrReceiptNo;
	}

	public BigDecimal getIcdId() {
		return this.icdId;
	}

	public void setIcdId(BigDecimal icdId) {
		this.icdId = icdId;
	}

	public String getInitDiagnosis() {
		return this.initDiagnosis;
	}

	public void setInitDiagnosis(String initDiagnosis) {
		this.initDiagnosis = initDiagnosis;
	}

	public Timestamp getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Timestamp lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public String getList() {
		return this.list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public Timestamp getListDate() {
		return this.listDate;
	}

	public void setListDate(Timestamp listDate) {
		this.listDate = listDate;
	}

	public String getLrTransferStatus() {
		return this.lrTransferStatus;
	}

	public void setLrTransferStatus(String lrTransferStatus) {
		this.lrTransferStatus = lrTransferStatus;
	}

	public String getMlc() {
		return this.mlc;
	}

	public void setMlc(String mlc) {
		this.mlc = mlc;
	}

	public String getMotherAdNo() {
		return this.motherAdNo;
	}

	public void setMotherAdNo(String motherAdNo) {
		this.motherAdNo = motherAdNo;
	}

	public String getParentAdNo() {
		return this.parentAdNo;
	}

	public void setParentAdNo(String parentAdNo) {
		this.parentAdNo = parentAdNo;
	}

	public String getPatientCondition() {
		return this.patientCondition;
	}

	public void setPatientCondition(String patientCondition) {
		this.patientCondition = patientCondition;
	}

	public String getPlaceOfIssue() {
		return this.placeOfIssue;
	}

	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}

	public String getPrevAdNo() {
		return this.prevAdNo;
	}

	public void setPrevAdNo(String prevAdNo) {
		this.prevAdNo = prevAdNo;
	}

	public String getPrevDiagnosis() {
		return this.prevDiagnosis;
	}

	public void setPrevDiagnosis(String prevDiagnosis) {
		this.prevDiagnosis = prevDiagnosis;
	}

	public String getPrevDisposal() {
		return this.prevDisposal;
	}

	public void setPrevDisposal(String prevDisposal) {
		this.prevDisposal = prevDisposal;
	}

	public String getPrevHospitalName() {
		return this.prevHospitalName;
	}

	public void setPrevHospitalName(String prevHospitalName) {
		this.prevHospitalName = prevHospitalName;
	}

	public String getPreviousAdNo() {
		return this.previousAdNo;
	}

	public void setPreviousAdNo(String previousAdNo) {
		this.previousAdNo = previousAdNo;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public BigDecimal getRecordOfficeAddressId() {
		return this.recordOfficeAddressId;
	}

	public void setRecordOfficeAddressId(BigDecimal recordOfficeAddressId) {
		this.recordOfficeAddressId = recordOfficeAddressId;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getServiceCardStatus() {
		return this.serviceCardStatus;
	}

	public void setServiceCardStatus(String serviceCardStatus) {
		this.serviceCardStatus = serviceCardStatus;
	}

	public BigDecimal getStaffSlNo() {
		return this.staffSlNo;
	}

	public void setStaffSlNo(BigDecimal staffSlNo) {
		this.staffSlNo = staffSlNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSurgeryStatus() {
		return this.surgeryStatus;
	}

	public void setSurgeryStatus(String surgeryStatus) {
		this.surgeryStatus = surgeryStatus;
	}

	public String getTimeOfAddmission() {
		return this.timeOfAddmission;
	}

	public void setTimeOfAddmission(String timeOfAddmission) {
		this.timeOfAddmission = timeOfAddmission;
	}

	public String getTransFrom() {
		return this.transFrom;
	}

	public void setTransFrom(String transFrom) {
		this.transFrom = transFrom;
	}

	public String getVip() {
		return this.vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public MasHospital getMasHospital() {
		return this.masHospital;
	}

	public void setMasHospital(MasHospital masHospital) {
		this.masHospital = masHospital;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Users getUser1() {
		return this.user1;
	}

	public void setUser1(Users user1) {
		this.user1 = user1;
	}

	public Users getUser2() {
		return this.user2;
	}

	public void setUser2(Users user2) {
		this.user2 = user2;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public List<ProcedureHd> getProcedureHds() {
		return this.procedureHds;
	}

	public void setProcedureHds(List<ProcedureHd> procedureHds) {
		this.procedureHds = procedureHds;
	}

	public ProcedureHd addProcedureHd(ProcedureHd procedureHd) {
		getProcedureHds().add(procedureHd);
		procedureHd.setInpatient(this);

		return procedureHd;
	}

	public ProcedureHd removeProcedureHd(ProcedureHd procedureHd) {
		getProcedureHds().remove(procedureHd);
		procedureHd.setInpatient(null);

		return procedureHd;
	}

}
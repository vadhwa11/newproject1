package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the DISCHARGE_ICD_CODE database table.
 * 
 */
@Entity
@Table(name="DISCHARGE_ICD_CODE")
@NamedQuery(name="DischargeIcdCode.findAll", query="SELECT d FROM DischargeIcdCode d")
public class DischargeIcdCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DISCHARGE_ICD_CODE_DISCHARGEICDCODEID_GENERATOR", sequenceName="DISCHARGE_ICD_CODE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DISCHARGE_ICD_CODE_DISCHARGEICDCODEID_GENERATOR")
	@Column(name="DISCHARGE_ICD_CODE_ID")
	private long dischargeIcdCodeId;

	@Column(name="DIAGNOSIS_STATUS")
	private String diagnosisStatus;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	@Column(name="OPD_PATIENT_DETAILS_ID")
	private BigDecimal opdPatientDetailsId;

	private String status;

	private String z03;

	private String z09;

	//bi-directional many-to-one association to Inpatient
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INPATIENT_ID")
	private Inpatient inpatient;

	//bi-directional many-to-one association to MasIcd
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ICD_ID")
	private MasIcd masIcd;

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

	public DischargeIcdCode() {
	}

	public long getDischargeIcdCodeId() {
		return this.dischargeIcdCodeId;
	}

	public void setDischargeIcdCodeId(long dischargeIcdCodeId) {
		this.dischargeIcdCodeId = dischargeIcdCodeId;
	}

	public String getDiagnosisStatus() {
		return this.diagnosisStatus;
	}

	public void setDiagnosisStatus(String diagnosisStatus) {
		this.diagnosisStatus = diagnosisStatus;
	}

	public Timestamp getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Timestamp lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public BigDecimal getOpdPatientDetailsId() {
		return this.opdPatientDetailsId;
	}

	public void setOpdPatientDetailsId(BigDecimal opdPatientDetailsId) {
		this.opdPatientDetailsId = opdPatientDetailsId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getZ03() {
		return this.z03;
	}

	public void setZ03(String z03) {
		this.z03 = z03;
	}

	public String getZ09() {
		return this.z09;
	}

	public void setZ09(String z09) {
		this.z09 = z09;
	}

	public Inpatient getInpatient() {
		return this.inpatient;
	}

	public void setInpatient(Inpatient inpatient) {
		this.inpatient = inpatient;
	}

	public MasIcd getMasIcd() {
		return this.masIcd;
	}

	public void setMasIcd(MasIcd masIcd) {
		this.masIcd = masIcd;
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

}
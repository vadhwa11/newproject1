package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the REFERRAL_PATIENT_HD database table.
 * 
 */
@Entity
@Table(name="REFERRAL_PATIENT_HD")
@NamedQuery(name="ReferralPatientHd.findAll", query="SELECT r FROM ReferralPatientHd r")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReferralPatientHd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3130144085591770694L;

	@Id
	@SequenceGenerator(name="REFERRAL_PATIENT_HD_SEQ", sequenceName="REFERRAL_PATIENT_HD_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REFERRAL_PATIENT_HD_SEQ")
	@Column(name="REFREAL_HD_ID")
	private Long refrealHdId;

	@Column(name="EXT_HOSPITAL_ID")
	private long extHospitalId;

	@Column(name="HOSPITAL_ID")
	private Long hospitalId;

	@Column(name="INT_HOSPITAL_ID")
	private Long intHospitalId;

	@Column(name="OPD_PATIENT_DETAILS_ID")
	private Long opdPatientDetailsId;

	@Column(name="PATIENT_ID")
	private Long patientId;

	@Temporal(TemporalType.DATE)
	@Column(name="REFERRAL_INI_DATE")
	private Date referralIniDate;

	@Column(name="REFERRAL_NO")
	private String referralNo;

	@Column(name="REFERRAL_NOTE")
	private String referralNote;

	private String status;

	@Column(name="TREATMENT_TYPE")
	private String treatmentType;

	
	//bi-directional many-to-one association to MasHospital
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INT_HOSPITAL_ID",nullable=false,insertable=false,updatable=false)
	private MasHospital masHospital1;

	//bi-directional many-to-one association to MasHospital
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOSPITAL_ID",nullable=false,insertable=false,updatable=false)
	private MasHospital masHospital2;

	//bi-directional many-to-one association to MasImpanneledHospital
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EXT_HOSPITAL_ID",nullable=false,insertable=false,updatable=false)
	private MasImpanneledHospital masImpanneledHospital;

	//bi-directional many-to-one association to OpdPatientDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OPD_PATIENT_DETAILS_ID",nullable=false,insertable=false,updatable=false)
	private OpdPatientDetail opdPatientDetail;

	//bi-directional many-to-one association to Patient
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PATIENT_ID",nullable=false,insertable=false,updatable=false)
	private Patient patient;

	@OneToMany(mappedBy="referralPatientHd")
	@JsonBackReference
	private List<AdmissionDischarge> admissionDischarge;

	
	//bi-directional many-to-one association to ReferralPatientDt
	@OneToMany(mappedBy = "referralPatientHd")
	@JsonBackReference	
	private List<ReferralPatientDt> referralPatientDts;


	public Long getRefrealHdId() {
		return refrealHdId;
	}


	public void setRefrealHdId(Long refrealHdId) {
		this.refrealHdId = refrealHdId;
	}


	public long getExtHospitalId() {
		return extHospitalId;
	}


	public void setExtHospitalId(long extHospitalId) {
		this.extHospitalId = extHospitalId;
	}


	public Long getHospitalId() {
		return hospitalId;
	}


	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}


	public Long getIntHospitalId() {
		return intHospitalId;
	}


	public void setIntHospitalId(Long intHospitalId) {
		this.intHospitalId = intHospitalId;
	}


	public Long getOpdPatientDetailsId() {
		return opdPatientDetailsId;
	}


	public void setOpdPatientDetailsId(Long opdPatientDetailsId) {
		this.opdPatientDetailsId = opdPatientDetailsId;
	}


	public Long getPatientId() {
		return patientId;
	}


	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}


	public Date getReferralIniDate() {
		return referralIniDate;
	}


	public void setReferralIniDate(Date referralIniDate) {
		this.referralIniDate = referralIniDate;
	}


	public String getReferralNo() {
		return referralNo;
	}


	public void setReferralNo(String referralNo) {
		this.referralNo = referralNo;
	}


	public String getReferralNote() {
		return referralNote;
	}


	public void setReferralNote(String referralNote) {
		this.referralNote = referralNote;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTreatmentType() {
		return treatmentType;
	}


	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}


	public MasHospital getMasHospital1() {
		return masHospital1;
	}


	public void setMasHospital1(MasHospital masHospital1) {
		this.masHospital1 = masHospital1;
	}


	public MasHospital getMasHospital2() {
		return masHospital2;
	}


	public void setMasHospital2(MasHospital masHospital2) {
		this.masHospital2 = masHospital2;
	}


	public MasImpanneledHospital getMasImpanneledHospital() {
		return masImpanneledHospital;
	}


	public void setMasImpanneledHospital(MasImpanneledHospital masImpanneledHospital) {
		this.masImpanneledHospital = masImpanneledHospital;
	}


	public OpdPatientDetail getOpdPatientDetail() {
		return opdPatientDetail;
	}


	public void setOpdPatientDetail(OpdPatientDetail opdPatientDetail) {
		this.opdPatientDetail = opdPatientDetail;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public List<AdmissionDischarge> getAdmissionDischarge() {
		return admissionDischarge;
	}


	public void setAdmissionDischarge(List<AdmissionDischarge> admissionDischarge) {
		this.admissionDischarge = admissionDischarge;
	}


	public List<ReferralPatientDt> getReferralPatientDts() {
		return referralPatientDts;
	}


	public void setReferralPatientDts(List<ReferralPatientDt> referralPatientDts) {
		this.referralPatientDts = referralPatientDts;
	}
	
}
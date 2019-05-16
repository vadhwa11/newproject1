package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the PROCEDURE_HD database table.
 * 
 */
@Entity
@Table(name="PROCEDURE_HD")
@NamedQuery(name="ProcedureHd.findAll", query="SELECT p FROM ProcedureHd p")
public class ProcedureHd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROCEDURE_HD_SEQ", sequenceName="PROCEDURE_HD_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROCEDURE_HD_SEQ")
	@Column(name="PROCEDURE_HD_ID")
	private long procedureHdId;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	@ManyToOne
	@JoinColumn(name="OPD_PATIENT_DETAILS_ID")
	private OpdPatientDetail opdPatientDetailsId;

	@Column(name="PROCEDURE_TYPE")
	private String procedureType;

	@Column(name="REQUISITION_DATE")
	private Timestamp requisitionDate;

	private String status;

	//bi-directional many-to-one association to ProcedureDt
	@OneToMany(mappedBy="procedureHd")
	private List<ProcedureDt> procedureDts;

	//bi-directional many-to-one association to Inpatient
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INPATIENT_ID")
	private Inpatient inpatient;

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
	private Users user;

	//bi-directional many-to-one association to Visit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="VISIT_ID")
	private Visit visit;

	public ProcedureHd() {
	}

	public long getProcedureHdId() {
		return this.procedureHdId;
	}

	public void setProcedureHdId(long procedureHdId) {
		this.procedureHdId = procedureHdId;
	}

	public Timestamp getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Timestamp lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public OpdPatientDetail getOpdPatientDetailsId() {
		return this.opdPatientDetailsId;
	}

	public void setOpdPatientDetailsId(OpdPatientDetail opdPatientDetailsId) {
		this.opdPatientDetailsId = opdPatientDetailsId;
	}

	public String getProcedureType() {
		return this.procedureType;
	}

	public void setProcedureType(String procedureType) {
		this.procedureType = procedureType;
	}

	public Timestamp getRequisitionDate() {
		return this.requisitionDate;
	}

	public void setRequisitionDate(Timestamp requisitionDate) {
		this.requisitionDate = requisitionDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ProcedureDt> getProcedureDts() {
		return this.procedureDts;
	}

	public void setProcedureDts(List<ProcedureDt> procedureDts) {
		this.procedureDts = procedureDts;
	}

	public ProcedureDt addProcedureDt(ProcedureDt procedureDt) {
		getProcedureDts().add(procedureDt);
		procedureDt.setProcedureHd(this);

		return procedureDt;
	}

	public ProcedureDt removeProcedureDt(ProcedureDt procedureDt) {
		getProcedureDts().remove(procedureDt);
		procedureDt.setProcedureHd(null);

		return procedureDt;
	}

	public Inpatient getInpatient() {
		return this.inpatient;
	}

	public void setInpatient(Inpatient inpatient) {
		this.inpatient = inpatient;
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

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

}
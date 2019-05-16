package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the PROCEDURE_DT database table.
 * 
 */
@Entity
@Table(name="PROCEDURE_DT")
@NamedQuery(name="ProcedureDt.findAll", query="SELECT p FROM ProcedureDt p")
public class ProcedureDt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROCEDURE_DETAILS_SEQ", sequenceName="PROCEDURE_DETAILS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROCEDURE_DETAILS_SEQ")
	@Column(name="PROCEDURE_DT_ID")
	private long procedureDtId;

	@Column(name="APPOINTMENT_DATE")
	private Timestamp appointmentDate;

	@Column(name="FINAL_PROCEDURE_STATUS")
	private String finalProcedureStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="NEXT_APPOINTMENT_DATE")
	private Date nextAppointmentDate;

	@Column(name="NO_OF_DAYS")
	private BigDecimal noOfDays;

	@Column(name="NURSING_REMARK")
	private String nursingRemark;

	@Column(name="PROCEDURE_DATE")
	private Timestamp procedureDate;

	private String remarks;

	private String status;

	//bi-directional many-to-one association to MasFrequency
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FREQUENCY_ID")
	private MasFrequency masFrequency;

	//bi-directional many-to-one association to MasNursingCare
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROCEDURE_ID")
	private MasNursingCare masNursingCare;

	//bi-directional many-to-one association to ProcedureHd
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROCEDURE_HD_ID")
	private ProcedureHd procedureHd;

	public ProcedureDt() {
	}

	public long getProcedureDtId() {
		return this.procedureDtId;
	}

	public void setProcedureDtId(long procedureDtId) {
		this.procedureDtId = procedureDtId;
	}

	public Timestamp getAppointmentDate() {
		return this.appointmentDate;
	}

	public void setAppointmentDate(Timestamp appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getFinalProcedureStatus() {
		return this.finalProcedureStatus;
	}

	public void setFinalProcedureStatus(String finalProcedureStatus) {
		this.finalProcedureStatus = finalProcedureStatus;
	}

	public Date getNextAppointmentDate() {
		return this.nextAppointmentDate;
	}

	public void setNextAppointmentDate(Date nextAppointmentDate) {
		this.nextAppointmentDate = nextAppointmentDate;
	}

	public BigDecimal getNoOfDays() {
		return this.noOfDays;
	}

	public void setNoOfDays(BigDecimal noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getNursingRemark() {
		return this.nursingRemark;
	}

	public void setNursingRemark(String nursingRemark) {
		this.nursingRemark = nursingRemark;
	}

	public Timestamp getProcedureDate() {
		return this.procedureDate;
	}

	public void setProcedureDate(Timestamp procedureDate) {
		this.procedureDate = procedureDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MasFrequency getMasFrequency() {
		return this.masFrequency;
	}

	public void setMasFrequency(MasFrequency masFrequency) {
		this.masFrequency = masFrequency;
	}

	public MasNursingCare getMasNursingCare() {
		return this.masNursingCare;
	}

	public void setMasNursingCare(MasNursingCare masNursingCare) {
		this.masNursingCare = masNursingCare;
	}

	public ProcedureHd getProcedureHd() {
		return this.procedureHd;
	}

	public void setProcedureHd(ProcedureHd procedureHd) {
		this.procedureHd = procedureHd;
	}

}
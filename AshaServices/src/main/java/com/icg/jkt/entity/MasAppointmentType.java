package com.icg.jkt.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the MAS_APPOINTMENT_TYPE database table.
 * 
 */
@Entity
@Table(name = "MAS_APPOINTMENT_TYPE")
@NamedQuery(name = "MasAppointmentType.findAll", query = "SELECT m FROM MasAppointmentType m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasAppointmentType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3789966956721404737L;

	@Id
	@SequenceGenerator(name = "MAS_APPOINTMENT_TYPE_APPOINTMENTTYPEID_GENERATOR", sequenceName = "MAS_APPOINTMENT_TYPE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAS_APPOINTMENT_TYPE_APPOINTMENTTYPEID_GENERATOR")
	@Column(name = "APPOINTMENT_TYPE_ID")
	private long appointmentTypeId;

	@Column(name = "APPOINTMENT_TYPE_CODE")
	private String appointmentTypeCode;

	@Column(name = "APPOINTMENT_TYPE_NAME")
	private String appointmentTypeName;

	@Column(name = "LAST_CHG_DATE")
	private Date lastChgDate;

	@Column(name = "STATUS")
	private String status;

	@OneToMany(mappedBy = "masAppointmentType")
	@JsonBackReference
	private List<MasAppointmentSession> masAppointmentSessions;

	@OneToMany(mappedBy = "masAppointmentType")
	@JsonBackReference
	private List<Visit> visit;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "LAST_CHG_BY")
	private Users user;

	public MasAppointmentType() {
	}

	public long getAppointmentTypeId() {
		return this.appointmentTypeId;
	}

	public void setAppointmentTypeId(long appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}

	public String getAppointmentTypeCode() {
		return this.appointmentTypeCode;
	}

	public void setAppointmentTypeCode(String appointmentTypeCode) {
		this.appointmentTypeCode = appointmentTypeCode;
	}

	public String getAppointmentTypeName() {
		return this.appointmentTypeName;
	}

	public void setAppointmentTypeName(String appointmentTypeName) {
		this.appointmentTypeName = appointmentTypeName;
	}

	public Date getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Date lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MasAppointmentSession> getMasAppointmentSessions() {
		return this.masAppointmentSessions;
	}

	public void setMasAppointmentSessions(List<MasAppointmentSession> masAppointmentSessions) {
		this.masAppointmentSessions = masAppointmentSessions;
	}

	public MasAppointmentSession addMasAppointmentSession(MasAppointmentSession masAppointmentSession) {
		getMasAppointmentSessions().add(masAppointmentSession);
		masAppointmentSession.setMasAppointmentType(this);

		return masAppointmentSession;
	}

	public MasAppointmentSession removeMasAppointmentSession(MasAppointmentSession masAppointmentSession) {
		getMasAppointmentSessions().remove(masAppointmentSession);
		masAppointmentSession.setMasAppointmentType(null);

		return masAppointmentSession;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Visit> getVisit() {
		return visit;
	}

	public void setVisit(List<Visit> visit) {
		this.visit = visit;
	}
}
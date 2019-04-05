package com.icg.jkt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the MAS_IMPANNELED_HOSPITAL database table.
 * 
 */
@Entity
@Table(name = "MAS_EMPANELLED_HOSPITAL")
@NamedQuery(name = "MasEmpanelledHospital.findAll", query = "SELECT m FROM MasEmpanelledHospital m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasEmpanelledHospital implements Serializable {
	private static final long serialVersionUID = -583276470562642050L;
	@Id
	@SequenceGenerator(name = "MAS_EMPANELLED_HOSPITAL_EMPANELLEDHOSPITALID_GENERATOR", sequenceName = "MAS_IMPANNELED_HOSPITAL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAS_EMPANELLED_HOSPITAL_EMPANELLEDHOSPITALID_GENERATOR")
	@Column(name = "EMPANELLED_HOSPITAL_ID")
	private long empanelledHospitalId;

	@Column(name = "EMPANELLED_HOSPITAL_CODE")
	private String empanelledHospitalCode;

	@Column(name = "EMPANELLED_HOSPITAL_NAME")
	private String empanelledHospitalName;

	@Column(name = "ADDRESS")
	private String empanelledHospitalAddress;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "LAST_CHG_BY")
	private Users user;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_CHG_DATE")
	private Date lastChgDate;

	@Column(name = "STATUS")
	private String status;

	public MasEmpanelledHospital() {
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
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

	public long getEmpanelledHospitalId() {
		return empanelledHospitalId;
	}

	public void setEmpanelledHospitalId(long empanelledHospitalId) {
		this.empanelledHospitalId = empanelledHospitalId;
	}

	public String getEmpanelledHospitalCode() {
		return empanelledHospitalCode;
	}

	public void setEmpanelledHospitalCode(String empanelledHospitalCode) {
		this.empanelledHospitalCode = empanelledHospitalCode;
	}

	public String getEmpanelledHospitalName() {
		return empanelledHospitalName;
	}

	public void setEmpanelledHospitalName(String empanelledHospitalName) {
		this.empanelledHospitalName = empanelledHospitalName;
	}

	public String getEmpanelledHospitalAddress() {
		return empanelledHospitalAddress;
	}

	public void setEmpanelledHospitalAddress(String empanelledHospitalAddress) {
		this.empanelledHospitalAddress = empanelledHospitalAddress;
	}

}
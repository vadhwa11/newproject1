package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the MAS_MARITAL_STATUS database table.
 * 
 */
@Entity
@Table(name="MAS_MARITAL_STATUS")
@NamedQuery(name="MasMaritalStatus.findAll", query="SELECT m FROM MasMaritalStatus m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasMaritalStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 764092483200346942L;

	@Id
	@SequenceGenerator(name="MAS_MARITAL_STATUS_MARITALSTATUSID_GENERATOR", sequenceName="MARITAL_STATUS_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_MARITAL_STATUS_MARITALSTATUSID_GENERATOR")
	@Column(name="MARITAL_STATUS_ID")
	private long maritalStatusId;

	@Column(name="LAST_CHG_BY")
	private String lastChgBy;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;



	@Column(name="MARITAL_STATUS_CODE")
	private String maritalStatusCode;

	@Column(name="MARITAL_STATUS_NAME")
	private String maritalStatusName;

	private String status;

	//bi-directional many-to-one association to MasEmployee
	@OneToMany(mappedBy="masMaritalStatus")
	@JsonBackReference
	private List<MasEmployee> masEmployees;

	public MasMaritalStatus() {
	}

	public long getMaritalStatusId() {
		return this.maritalStatusId;
	}

	public void setMaritalStatusId(long maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}

	public String getLastChgBy() {
		return this.lastChgBy;
	}

	public void setLastChgBy(String lastChgBy) {
		this.lastChgBy = lastChgBy;
	}

	public Timestamp getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Timestamp lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public String getMaritalStatusCode() {
		return this.maritalStatusCode;
	}

	public void setMaritalStatusCode(String maritalStatusCode) {
		this.maritalStatusCode = maritalStatusCode;
	}

	public String getMaritalStatusName() {
		return this.maritalStatusName;
	}

	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MasEmployee> getMasEmployees() {
		return this.masEmployees;
	}

	public void setMasEmployees(List<MasEmployee> masEmployees) {
		this.masEmployees = masEmployees;
	}

	public MasEmployee addMasEmployee(MasEmployee masEmployee) {
		getMasEmployees().add(masEmployee);
		masEmployee.setMasMaritalStatus(this);

		return masEmployee;
	}

	public MasEmployee removeMasEmployee(MasEmployee masEmployee) {
		getMasEmployees().remove(masEmployee);
		masEmployee.setMasMaritalStatus(null);

		return masEmployee;
	}

}
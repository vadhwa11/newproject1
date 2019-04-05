package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


/**
 * The persistent class for the MAS_NURSING_CARE database table.
 * 
 */
@Entity
@Table(name="MAS_NURSING_CARE")
@NamedQuery(name="MasNursingCare.findAll", query="SELECT m FROM MasNursingCare m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasNursingCare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 789165630360994809L;

	@Id
	@SequenceGenerator(name="MAS_NURSING_CARE_NURSINGID_GENERATOR", sequenceName="MAS_NURSING_CARE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_NURSING_CARE_NURSINGID_GENERATOR")
	@Column(name="NURSING_ID")
	private long nursingId;

	private String defaultstatus;

	/*
	 * @Column(name="LAST_CHG_BY") private String lastChgBy;
	 */

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_CHG_DATE")
	private Date lastChgDate;

	@Column(name="NURSING_CODE")
	private String nursingCode;

	@Column(name="NURSING_NAME")
	private String nursingName;

	@Column(name="NURSING_TYPE")
	private String nursingType;
	
	@Column(name="STATUS")
	private String status;
	
	//bi-directional many-to-one association to User
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="LAST_CHG_BY")
		private Users user;

	public MasNursingCare() {
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public long getNursingId() {
		return this.nursingId;
	}

	public void setNursingId(long nursingId) {
		this.nursingId = nursingId;
	}

	public String getDefaultstatus() {
		return this.defaultstatus;
	}

	public void setDefaultstatus(String defaultstatus) {
		this.defaultstatus = defaultstatus;
	}

	/*
	 * public String getLastChgBy() { return this.lastChgBy; }
	 * 
	 * public void setLastChgBy(String lastChgBy) { this.lastChgBy = lastChgBy; }
	 */

	public Date getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Date lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public String getNursingCode() {
		return this.nursingCode;
	}

	public void setNursingCode(String nursingCode) {
		this.nursingCode = nursingCode;
	}

	public String getNursingName() {
		return this.nursingName;
	}

	public void setNursingName(String nursingName) {
		this.nursingName = nursingName;
	}

	public String getNursingType() {
		return this.nursingType;
	}

	public void setNursingType(String nursingType) {
		this.nursingType = nursingType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
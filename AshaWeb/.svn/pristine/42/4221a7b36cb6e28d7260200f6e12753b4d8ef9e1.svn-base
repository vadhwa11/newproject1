package com.asha.icgweb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MAS_HOSPITAL database table.
 * 
 */
@Entity
@Table(name="MAS_HOSPITAL")
@NamedQuery(name="MasHospital.findAll", query="SELECT m FROM MasHospital m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasHospital implements Serializable {
	private static final long serialVersionUID = -8647496571766655879L;

	@Id
	@SequenceGenerator(name="MAS_HOSPITAL_HOSPITALID_GENERATOR", sequenceName="MAS_HOSPITAL_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_HOSPITAL_HOSPITALID_GENERATOR")
	@Column(name="HOSPITAL_ID")
	private long hospitalId;

	private String address;

	@Column(name="COMMAND_ID")
	private BigDecimal commandId;

	@Column(name="CONTACT_NUMBER")
	private String contactNumber;

	@Column(name="DISTRICT_ID")
	private BigDecimal districtId;

	@Column(name="HOSPITAL_CODE")
	private String hospitalCode;

	@Column(name="HOSPITAL_NAME")
	private String hospitalName;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_CHG_DATE")
	private Date lastChgDate;
	
	@Column(name="STATUS")
	private String status;

	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getCommandId() {
		return commandId;
	}

	public void setCommandId(BigDecimal commandId) {
		this.commandId = commandId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public BigDecimal getDistrictId() {
		return districtId;
	}

	public void setDistrictId(BigDecimal districtId) {
		this.districtId = districtId;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Date getLastChgDate() {
		return lastChgDate;
	}

	public void setLastChgDate(Date lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
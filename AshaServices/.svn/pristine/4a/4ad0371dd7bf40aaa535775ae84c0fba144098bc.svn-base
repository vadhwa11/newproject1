package com.icg.jkt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the MAS_EMPLOYEE_DEPENDENT database table.
 * 
 */
@Entity
@Table(name = "MAS_EMPLOYEE_DEPENDENT")
@NamedQuery(name = "MasEmployeeDependent.findAll", query = "SELECT m FROM MasEmployeeDependent m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasEmployeeDependent implements Serializable {
	private static final long serialVersionUID = 7384469056673691414L;

	@Id
	@SequenceGenerator(name = "MAS_EMPLOYEE_DEPENDENT_EMPLOYEEDEPENDENTID_GENERATOR", sequenceName = "EMPLOYEE_DEPENDENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAS_EMPLOYEE_DEPENDENT_EMPLOYEEDEPENDENTID_GENERATOR")
	@Column(name = "EMPLOYEE_DEPENDENT_ID")
	private long employeeDependentId;

	private String address;

	private String authority;

	@Column(name = "CITY")
	private String city;

	@Column(name = "CONTACT_NO")
	private String contactNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_DEPENDENCY")
	private Date dateOfDependency;

	@Temporal(TemporalType.DATE)
	@Column(name = "DEPENDENCY_REMOVAL_DATE")
	private Date dependencyRemovalDate;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "EMPLOYEE_DEPENDENT_CODE")
	private String employeeDependentCode;

	@Column(name = "EMPLOYEE_DEPENDENT_F_NAME")
	private String employeeDependentFName;

	@Column(name = "EMPLOYEE_DEPENDENT_L_NAME")
	private String employeeDependentLName;

	@Column(name = "EMPLOYEE_DEPENDENT_M_NAME")
	private String employeeDependentMName;

	@Column(name = "EPH_ISN")
	private BigDecimal ephIsn;

	@Column(name = "HOSPITAL_ID")
	private BigDecimal hospitalId;

	@Column(name = "LAST_CHG_BY")
	private String lastChgBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_CHG_DATE")
	private Date lastChgDate;

	private long pincode;
	private String status;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "GENDER_ID")
	private MasAdministrativeSex masAdministrativeSex;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "RELATION_ID")
	private MasRelation masRelation;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "BLOOD_GROUP_ID")
	private MasBloodGroup masBloodGroup;

	// bi-directional many-to-one association to MasEmployee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	private MasEmployee masEmployee;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "STATE_ID")
	private MasState masState;

	public MasEmployeeDependent() {
	}

	public long getEmployeeDependentId() {
		return this.employeeDependentId;
	}

	public void setEmployeeDependentId(long employeeDependentId) {
		this.employeeDependentId = employeeDependentId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfDependency() {
		return this.dateOfDependency;
	}

	public void setDateOfDependency(Date dateOfDependency) {
		this.dateOfDependency = dateOfDependency;
	}

	public Date getDependencyRemovalDate() {
		return this.dependencyRemovalDate;
	}

	public void setDependencyRemovalDate(Date dependencyRemovalDate) {
		this.dependencyRemovalDate = dependencyRemovalDate;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmployeeDependentCode() {
		return this.employeeDependentCode;
	}

	public void setEmployeeDependentCode(String employeeDependentCode) {
		this.employeeDependentCode = employeeDependentCode;
	}

	public String getEmployeeDependentFName() {
		return this.employeeDependentFName;
	}

	public void setEmployeeDependentFName(String employeeDependentFName) {
		this.employeeDependentFName = employeeDependentFName;
	}

	public String getEmployeeDependentLName() {
		return this.employeeDependentLName;
	}

	public void setEmployeeDependentLName(String employeeDependentLName) {
		this.employeeDependentLName = employeeDependentLName;
	}

	public String getEmployeeDependentMName() {
		return this.employeeDependentMName;
	}

	public void setEmployeeDependentMName(String employeeDependentMName) {
		this.employeeDependentMName = employeeDependentMName;
	}

	public BigDecimal getEphIsn() {
		return this.ephIsn;
	}

	public void setEphIsn(BigDecimal ephIsn) {
		this.ephIsn = ephIsn;
	}

	public BigDecimal getHospitalId() {
		return this.hospitalId;
	}

	public void setHospitalId(BigDecimal hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getLastChgBy() {
		return this.lastChgBy;
	}

	public void setLastChgBy(String lastChgBy) {
		this.lastChgBy = lastChgBy;
	}

	public Date getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Date lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public long getPincode() {
		return this.pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

	public MasRelation getMasRelation() {
		return masRelation;
	}

	public void setMasRelation(MasRelation masRelation) {
		this.masRelation = masRelation;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MasBloodGroup getMasBloodGroup() {
		return this.masBloodGroup;
	}

	public void setMasBloodGroup(MasBloodGroup masBloodGroup) {
		this.masBloodGroup = masBloodGroup;
	}

	public MasEmployee getMasEmployee() {
		return this.masEmployee;
	}

	public void setMasEmployee(MasEmployee masEmployee) {
		this.masEmployee = masEmployee;
	}

	public MasAdministrativeSex getMasAdministrativeSex() {
		return masAdministrativeSex;
	}

	public void setMasAdministrativeSex(MasAdministrativeSex masAdministrativeSex) {
		this.masAdministrativeSex = masAdministrativeSex;
	}

	public MasState getMasState() {
		return masState;
	}

	public void setMasState(MasState masState) {
		this.masState = masState;
	}

}
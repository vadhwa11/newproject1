package com.icg.jkt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="Users.findAll", query="SELECT u FROM Users u")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Users implements Serializable {
	private static final long serialVersionUID = -863915371702584702L;

	@Id
	@SequenceGenerator(name="USERS_SEQ", sequenceName="USERS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_SEQ")
	@Column(name="USER_ID")
	private long userId;

	@Column(name="COUNT_USER")
	private BigDecimal countUser;

	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="LOGIN_NAME")
	private String loginName;

	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name="RESET_COMPLETE_ON")
	private Date resetCompleteOn;

	@Column(name="RESET_COUNT")
	private BigDecimal resetCount;

	@Column(name="RESET_FLAG")
	private String resetFlag;

	@Temporal(TemporalType.DATE)
	@Column(name="RESET_REQ_ON")
	private Date resetReqOn;

	private String status;

	@Column(name="STATUS_SHA1")
	private String statusSha1;

	@Column(name="USER_NAME")
	private String userName;
	
	@OneToMany(mappedBy="user")
	@JsonBackReference
	private List<MasAppointmentType> masAppointmentSession;
	
	@OneToMany(mappedBy="users")
	@JsonBackReference
	private List<MasDisposal> masDisposal;

	//bi-directional many-to-one association to DoctorRoaster
	@OneToMany(mappedBy="user")
	@JsonBackReference
	private List<DoctorRoaster> doctorRoasters;

	//bi-directional many-to-one association to MasDepartment
	@OneToMany(mappedBy="user")
	@JsonBackReference
	private List<MasDepartment> masDepartments;

	//bi-directional many-to-one association to MasEmployee
	@OneToMany(mappedBy="user")
	@JsonBackReference
	private List<MasEmployee> masEmployees;

	//bi-directional many-to-one association to MasHospital
	@OneToMany(mappedBy="user")
	@JsonBackReference
	private List<MasHospital> masHospitals;
	
	//bi-directional many-to-one association to MasNursingCare
	@OneToMany(mappedBy="user")
	@JsonBackReference
	private List<MasNursingCare> masNursingCare;				
		
	//bi-directional many-to-one association to MasNursingCare
	@OneToMany(mappedBy="user")	
	@JsonBackReference
	private List<MasEmpanelledHospital> empanelledHospitals;
	
	@OneToMany(mappedBy="user")	
	@JsonBackReference
	private List<TemplateApplication> templateApplications; 
			
	//bi-directional many-to-one association to MasEmployee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID")
	private MasEmployee masEmployee;

	public Users() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public BigDecimal getCountUser() {
		return this.countUser;
	}

	public void setCountUser(BigDecimal countUser) {
		this.countUser = countUser;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getResetCompleteOn() {
		return this.resetCompleteOn;
	}

	public void setResetCompleteOn(Date resetCompleteOn) {
		this.resetCompleteOn = resetCompleteOn;
	}

	public BigDecimal getResetCount() {
		return this.resetCount;
	}

	public void setResetCount(BigDecimal resetCount) {
		this.resetCount = resetCount;
	}

	public String getResetFlag() {
		return this.resetFlag;
	}

	public void setResetFlag(String resetFlag) {
		this.resetFlag = resetFlag;
	}

	public Date getResetReqOn() {
		return this.resetReqOn;
	}

	public void setResetReqOn(Date resetReqOn) {
		this.resetReqOn = resetReqOn;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusSha1() {
		return this.statusSha1;
	}

	public void setStatusSha1(String statusSha1) {
		this.statusSha1 = statusSha1;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<DoctorRoaster> getDoctorRoasters() {
		return this.doctorRoasters;
	}

	public void setDoctorRoasters(List<DoctorRoaster> doctorRoasters) {
		this.doctorRoasters = doctorRoasters;
	}

	public DoctorRoaster addDoctorRoaster(DoctorRoaster doctorRoaster) {
		getDoctorRoasters().add(doctorRoaster);
		doctorRoaster.setUser(this);

		return doctorRoaster;
	}

	public DoctorRoaster removeDoctorRoaster(DoctorRoaster doctorRoaster) {
		getDoctorRoasters().remove(doctorRoaster);
		doctorRoaster.setUser(null);

		return doctorRoaster;
	}

	public List<MasDepartment> getMasDepartments() {
		return this.masDepartments;
	}

	public void setMasDepartments(List<MasDepartment> masDepartments) {
		this.masDepartments = masDepartments;
	}

	public MasDepartment addMasDepartment(MasDepartment masDepartment) {
		getMasDepartments().add(masDepartment);
		masDepartment.setUser(this);

		return masDepartment;
	}

	public MasDepartment removeMasDepartment(MasDepartment masDepartment) {
		getMasDepartments().remove(masDepartment);
		masDepartment.setUser(null);

		return masDepartment;
	}

	public List<MasEmployee> getMasEmployees() {
		return this.masEmployees;
	}

	public void setMasEmployees(List<MasEmployee> masEmployees) {
		this.masEmployees = masEmployees;
	}

	public MasEmployee addMasEmployee(MasEmployee masEmployee) {
		getMasEmployees().add(masEmployee);
		masEmployee.setUser(this);

		return masEmployee;
	}

	public MasEmployee removeMasEmployee(MasEmployee masEmployee) {
		getMasEmployees().remove(masEmployee);
		masEmployee.setUser(null);

		return masEmployee;
	}

	public List<MasHospital> getMasHospitals() {
		return this.masHospitals;
	}

	public void setMasHospitals(List<MasHospital> masHospitals) {
		this.masHospitals = masHospitals;
	}

	public MasHospital addMasHospital(MasHospital masHospital) {
		getMasHospitals().add(masHospital);
		masHospital.setUser(this);

		return masHospital;
	}

	public MasHospital removeMasHospital(MasHospital masHospital) {
		getMasHospitals().remove(masHospital);
		masHospital.setUser(null);

		return masHospital;
	}

	public MasEmployee getMasEmployee() {
		return this.masEmployee;
	}

	public void setMasEmployee(MasEmployee masEmployee) {
		this.masEmployee = masEmployee;
	}
	
	public List<MasAppointmentType> getMasAppointmentSession() {
		return masAppointmentSession;
	}

	public void setMasAppointmentSession(List<MasAppointmentType> masAppointmentSession) {
		this.masAppointmentSession = masAppointmentSession;
	}

	public List<MasNursingCare> getMasNursingCare() {
		return masNursingCare;
	}

	public void setMasNursingCare(List<MasNursingCare> masNursingCare) {
		this.masNursingCare = masNursingCare;
	}
	
	public List<MasDisposal> getMasDisposal() {
		return masDisposal;
	}

	public void setMasDisposal(List<MasDisposal> masDisposal) {
		this.masDisposal = masDisposal;
	}

	public List<MasEmpanelledHospital> getEmpanelledHospitals() {
		return empanelledHospitals;
	}

	public void setEmpanelledHospitals(List<MasEmpanelledHospital> empanelledHospitals) {
		this.empanelledHospitals = empanelledHospitals;
	}

	public List<TemplateApplication> getTemplateApplications() {
		return templateApplications;
	}

	public void setTemplateApplications(List<TemplateApplication> templateApplications) {
		this.templateApplications = templateApplications;
	}

}
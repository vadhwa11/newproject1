package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MAS_ADMINISTRATIVE_SEX database table.
 * 
 */
@Entity
@Table(name="MAS_ADMINISTRATIVE_SEX")
@NamedQuery(name="MasAdministrativeSex.findAll", query="SELECT m FROM MasAdministrativeSex m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasAdministrativeSex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5936461079386972893L;

	@Id
	@SequenceGenerator(name="MAS_ADMINISTRATIVE_SEX_SEQ", sequenceName="MAS_ADMINISTRATIVE_SEX_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_ADMINISTRATIVE_SEX_SEQ")
	@Column(name="ADMINISTRATIVE_SEX_ID")
	private long administrativeSexId;

	@Column(name="ADMINISTRATIVE_SEX_CODE")
	private String administrativeSexCode;

	@Column(name="ADMINISTRATIVE_SEX_NAME")
	private String administrativeSexName;

	//@ManyToOne(fetch=FetchType.LAZY)(fetch = FetchType.LAZY)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LAST_CHG_BY")
	private Users user;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_CHG_DATE")
	private Date lastChgDate;
	
	@Column(name="STATUS")
	private String status;
	
	
	@OneToMany(mappedBy="masAdministrativeSex")
	@JsonBackReference
	private List<Patient> patient;

	public List<Patient> getPatient() {
		return patient;
	}

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}

	//bi-directional many-to-one association to MasEmployee
	@OneToMany(mappedBy="masAdministrativeSex")
	@JsonBackReference
	private List<MasEmployee> masEmployees;

	@OneToMany(mappedBy="masAdministrativeSex")
	@JsonBackReference
	private List<MasEmployeeDependent> masEmployeeDependents;
	
	//bi-directional many-to-one association to MasRange
	@OneToMany(mappedBy="masAdministrativeSex")
	private List<MasRange> masRanges;

	//bi-directional many-to-one association to MasIdealWeight
		@OneToMany(mappedBy="masAdministrativeSex")
		private List<MasIdealWeight> masIdealWeights;
		
	public MasAdministrativeSex() {
	}

	public List<MasRange> getMasRanges() {
		return masRanges;
	}

	public void setMasRanges(List<MasRange> masRanges) {
		this.masRanges = masRanges;
	}

	public long getAdministrativeSexId() {
		return this.administrativeSexId;
	}

	public void setAdministrativeSexId(long administrativeSexId) {
		this.administrativeSexId = administrativeSexId;
	}

	public String getAdministrativeSexCode() {
		return this.administrativeSexCode;
	}

	public void setAdministrativeSexCode(String administrativeSexCode) {
		this.administrativeSexCode = administrativeSexCode;
	}

	public String getAdministrativeSexName() {
		return this.administrativeSexName;
	}

	public void setAdministrativeSexName(String administrativeSexName) {
		this.administrativeSexName = administrativeSexName;
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

	
	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public List<MasEmployee> getMasEmployees() {
		return this.masEmployees;
	}

	public void setMasEmployees(List<MasEmployee> masEmployees) {
		this.masEmployees = masEmployees;
	}



}
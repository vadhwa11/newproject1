package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MAS_MEDICAL_CATEGORY database table.
 * 
 */
@Entity
@Table(name="MAS_MEDICAL_CATEGORY")
@NamedQuery(name="MasMedicalCategory.findAll", query="SELECT m FROM MasMedicalCategory m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasMedicalCategory implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7865180468809685402L;

	@Id
	@SequenceGenerator(name="MAS_MEDICAL_CATEGORY_SEQ", sequenceName="MAS_MEDICAL_CATEGORY_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_MEDICAL_CATEGORY_SEQ")
	@Column(name="MEDICAL_CATEGORY_ID")
	private long medicalCategoryId;

	@Column(name="LAST_CHG_BY")
	private Long lastChgBy;

	@Column(name="LAST_CHG_DATE")
	private Date lastChgDate;

	@Column(name="MEDICAL_CATEGORY_CODE")
	private Long medicalCategoryCode;

	@Column(name="MEDICAL_CATEGORY_NAME")
	private String medicalCategoryName;

	private String status;

	@OneToMany(mappedBy="masMedicalCategory")
	@JsonBackReference
	private List<Patient> patients;

	public MasMedicalCategory() {
	}

	public long getMedicalCategoryId() {
		return this.medicalCategoryId;
	}

	public void setMedicalCategoryId(long medicalCategoryId) {
		this.medicalCategoryId = medicalCategoryId;
	}

	public Long getLastChgBy() {
		return this.lastChgBy;
	}

	public void setLastChgBy(Long lastChgBy) {
		this.lastChgBy = lastChgBy;
	}

	public Date getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Date lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public Long getMedicalCategoryCode() {
		return this.medicalCategoryCode;
	}

	public void setMedicalCategoryCode(Long medicalCategoryCode) {
		this.medicalCategoryCode = medicalCategoryCode;
	}

	public String getMedicalCategoryName() {
		return this.medicalCategoryName;
	}

	public void setMedicalCategoryName(String medicalCategoryName) {
		this.medicalCategoryName = medicalCategoryName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Patient addPatient(Patient patient) {
		getPatients().add(patient);
		patient.setMasMedicalCategory(this);

		return patient;
	}

	public Patient removePatient(Patient patient) {
		getPatients().remove(patient);
		patient.setMasMedicalCategory(null);

		return patient;
	}

}
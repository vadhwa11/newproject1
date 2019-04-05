package com.icg.jkt.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the MAS_COMMAND database table.
 * 
 */
@Entity
@Table(name="MAS_COMMAND")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasCommand implements Serializable {
	/*private static final long serialVersionUID = 1L;*/

	/**
	 * 
	 */
	private static final long serialVersionUID = -6248054786135223665L;

	@Id
	@SequenceGenerator(name="MAS_COMMAND_GENERATOR", sequenceName="MAS_COMMAND_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_COMMAND_GENERATOR")
	@Column(name="COMMAND_ID")
	private long commandId;

	@Column(name="COMMAND_CODE")
	private String commandCode;

	@Column(name="COMMAND_NAME")
	private String commandName;

	@Column(name="LAST_CHG_DATE")
	private Date lastChgDate;
	
	@Column(name="STATUS")
	private String status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COMMAND_TYPE_ID")
	private MasCommandType masCommandType;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LAST_CHG_BY")
	private Users user;

	//bi-directional many-to-one association to MasEmployee
	@OneToMany(mappedBy="masCommand")
	@JsonBackReference
	private List<MasEmployee> masEmployees;

	//bi-directional many-to-one association to MasUnit
	@OneToMany
	@JsonBackReference
	@JoinColumn(name="UNIT_ID")		
	private List<MasUnit> masUnits;

	//bi-directional many-to-one association to Patient
	@OneToMany
	@JsonBackReference
	@JoinColumn(name="PATIENT_ID")		
	private List<Patient> patients;

	public MasCommand() {
	}

	public long getCommandId() {
		return this.commandId;
	}

	public void setCommandId(long commandId) {
		this.commandId = commandId;
	}

	public String getCommandCode() {
		return this.commandCode;
	}

	public void setCommandCode(String commandCode) {
		this.commandCode = commandCode;
	}

	public String getCommandName() {
		return this.commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
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

	public MasCommandType getMasCommandType() {
		return this.masCommandType;
	}

	public void setMasCommandType(MasCommandType masCommandType) {
		this.masCommandType = masCommandType;
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

	public MasEmployee addMasEmployee(MasEmployee masEmployee) {
		getMasEmployees().add(masEmployee);
		masEmployee.setMasCommand(this);

		return masEmployee;
	}

	public MasEmployee removeMasEmployee(MasEmployee masEmployee) {
		getMasEmployees().remove(masEmployee);
		masEmployee.setMasCommand(null);

		return masEmployee;
	}

	public List<MasUnit> getMasUnits() {
		return this.masUnits;
	}

	public void setMasUnits(List<MasUnit> masUnits) {
		this.masUnits = masUnits;
	}

	public MasUnit addMasUnit(MasUnit masUnit) {
		getMasUnits().add(masUnit);
		masUnit.setMasCommand(this);

		return masUnit;
	}

	public MasUnit removeMasUnit(MasUnit masUnit) {
		getMasUnits().remove(masUnit);
		masUnit.setMasCommand(null);

		return masUnit;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Patient addPatient(Patient patient) {
		getPatients().add(patient);
		patient.setMasCommand(this);

		return patient;
	}

	public Patient removePatient(Patient patient) {
		getPatients().remove(patient);
		patient.setMasCommand(null);

		return patient;
	}

}
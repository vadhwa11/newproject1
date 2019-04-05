package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the MAS_EMPLOYEE_DEPARTMENT database table.
 * 
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="MAS_EMPLOYEE_DEPARTMENT")
@NamedQuery(name="MasEmployeeDepartment.findAll", query="SELECT m FROM MasEmployeeDepartment m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasEmployeeDepartment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2279542263948641926L;

	/**
	 * 
	 */


	@Id
	@SequenceGenerator(name="MAS_EMPLOYEE_DEPARTMENT_SEQ", sequenceName="MAS_EMPLOYEE_DEPARTMENT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_EMPLOYEE_DEPARTMENT_SEQ")
	
	@Column(name="EMPLOYEE_DEPARTMENT_ID")
	private Long employeeDepartmentId;

	@Column(name="DEPARTMENT_ID")
	private Long departmentId;

	@Column(name="DIVISION_ID")
	private Long divisionId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID")
	private MasEmployee masEmployee;

	
	@Column(name="LAST_CHG_BY")
	private String lastChgBy;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;



	private String status;

	public MasEmployee getMasEmployee() {
		return masEmployee;
	}

	public void setMasEmployee(MasEmployee masEmployee) {
		this.masEmployee = masEmployee;
	}


}
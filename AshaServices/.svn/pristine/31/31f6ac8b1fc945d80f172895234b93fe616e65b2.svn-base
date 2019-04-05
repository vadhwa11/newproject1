package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the MAS_GRADE database table.
 * 
 */
@Entity
@Table(name="MAS_GRADE")
@NamedQuery(name="MasGrade.findAll", query="SELECT m FROM MasGrade m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasGrade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9131343921402962852L;

	@Id
	@SequenceGenerator(name="MAS_GRADE_GRADEID_GENERATOR", sequenceName="GRADE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_GRADE_GRADEID_GENERATOR")
	@Column(name="GRADE_ID")
	private long gradeId;

	@Column(name="GRADE_CODE")
	private String gradeCode;

	@Column(name="GRADE_NAME")
	private String gradeName;

	@Column(name="LAST_CHG_BY")
	private String lastChgBy;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	@Column(name="RANK_CATEGORY_ID")
	private BigDecimal rankCategoryId;

	private String status;

	//bi-directional many-to-one association to MasEmployee
	@OneToMany(mappedBy="masGrade")
	@JsonBackReference
	private List<MasEmployee> masEmployees;

	public MasGrade() {
	}

	public long getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeCode() {
		return this.gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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

	public BigDecimal getRankCategoryId() {
		return this.rankCategoryId;
	}

	public void setRankCategoryId(BigDecimal rankCategoryId) {
		this.rankCategoryId = rankCategoryId;
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
		masEmployee.setMasGrade(this);

		return masEmployee;
	}

	public MasEmployee removeMasEmployee(MasEmployee masEmployee) {
		getMasEmployees().remove(masEmployee);
		masEmployee.setMasGrade(null);

		return masEmployee;
	}

}
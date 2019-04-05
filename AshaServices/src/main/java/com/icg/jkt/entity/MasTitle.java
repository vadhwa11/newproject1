package com.icg.jkt.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the MAS_TITLE database table.
 * 
 */
@Entity
@Table(name="MAS_TITLE")
@NamedQuery(name="MasTitle.findAll", query="SELECT m FROM MasTitle m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasTitle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1119337834092820208L;

	@Id
	@SequenceGenerator(name="MAS_TITLE_TITLEID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_TITLE_TITLEID_GENERATOR")
	@Column(name="TITLE_ID")
	private long titleId;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	private String status;

	@Column(name="TITLE_CODE")
	private String titleCode;

	@Column(name="TITLE_NAME")
	private String titleName;

	//bi-directional many-to-one association to MasEmployee
	@OneToMany(mappedBy="masTitle")
	@JsonBackReference
	private List<MasEmployee> masEmployees;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LAST_CHG_BY")
	private Users user;

	public MasTitle() {
	}

	public long getTitleId() {
		return this.titleId;
	}

	public void setTitleId(long titleId) {
		this.titleId = titleId;
	}

	public Object getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Timestamp lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitleCode() {
		return this.titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	public String getTitleName() {
		return this.titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public List<MasEmployee> getMasEmployees() {
		return this.masEmployees;
	}

	public void setMasEmployees(List<MasEmployee> masEmployees) {
		this.masEmployees = masEmployees;
	}

	public MasEmployee addMasEmployee(MasEmployee masEmployee) {
		getMasEmployees().add(masEmployee);
		masEmployee.setMasTitle(this);

		return masEmployee;
	}

	public MasEmployee removeMasEmployee(MasEmployee masEmployee) {
		getMasEmployees().remove(masEmployee);
		masEmployee.setMasTitle(null);

		return masEmployee;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
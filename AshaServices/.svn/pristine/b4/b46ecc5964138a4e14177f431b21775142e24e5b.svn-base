package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the MAS_DISTRICT database table.
 * 
 */
@Entity
@Table(name="MAS_DISTRICT")
@NamedQuery(name="MasDistrict.findAll", query="SELECT m FROM MasDistrict m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasDistrict implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7773826869990687351L;

	@Id
	@SequenceGenerator(name="MAS_DISTRICT_DISTRICTID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_DISTRICT_DISTRICTID_GENERATOR")
	@Column(name="DISTRICT_ID")
	private long districtId;

	@Column(name="DISTRICT_CODE")
	private String districtCode;

	@Column(name="DISTRICT_NAME")
	private String districtName;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	@Column(name="STATE_ID")
	private BigDecimal stateId;

	private String status;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LAST_CHG_BY")
	private Users user;

	//bi-directional many-to-one association to MasEmployee
	@OneToMany(mappedBy="masDistrict1")
	@JsonBackReference
	private List<MasEmployee> masEmployees1;

	//bi-directional many-to-one association to MasEmployee
	@OneToMany(mappedBy="masDistrict2")
	@JsonBackReference
	private List<MasEmployee> masEmployees2;

	public MasDistrict() {
	}

	public long getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Timestamp getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Timestamp lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public BigDecimal getStateId() {
		return this.stateId;
	}

	public void setStateId(BigDecimal stateId) {
		this.stateId = stateId;
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

	public List<MasEmployee> getMasEmployees1() {
		return this.masEmployees1;
	}

	public void setMasEmployees1(List<MasEmployee> masEmployees1) {
		this.masEmployees1 = masEmployees1;
	}

	public MasEmployee addMasEmployees1(MasEmployee masEmployees1) {
		getMasEmployees1().add(masEmployees1);
		masEmployees1.setMasDistrict1(this);

		return masEmployees1;
	}

	public MasEmployee removeMasEmployees1(MasEmployee masEmployees1) {
		getMasEmployees1().remove(masEmployees1);
		masEmployees1.setMasDistrict1(null);

		return masEmployees1;
	}

	public List<MasEmployee> getMasEmployees2() {
		return this.masEmployees2;
	}

	public void setMasEmployees2(List<MasEmployee> masEmployees2) {
		this.masEmployees2 = masEmployees2;
	}

	public MasEmployee addMasEmployees2(MasEmployee masEmployees2) {
		getMasEmployees2().add(masEmployees2);
		masEmployees2.setMasDistrict2(this);

		return masEmployees2;
	}

	public MasEmployee removeMasEmployees2(MasEmployee masEmployees2) {
		getMasEmployees2().remove(masEmployees2);
		masEmployees2.setMasDistrict2(null);

		return masEmployees2;
	}

}
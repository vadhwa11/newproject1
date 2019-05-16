package com.test.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "distributor")
public class Coupan_Distributor {
	@Id
	@Column(name = "Distributor_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer Distributor_id;
	private String name;
	private String email;
	private Integer coupan_count;
	private String Mobile;

	@Embedded
	private Distributor_Address address;

	public Distributor_Address getAddress() {
		return address;
	}

	public void setAddress(Distributor_Address address) {
		this.address = address;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Distributor_id")

	private Set<Coupan_Info> coupan_info = new HashSet<Coupan_Info>();

	private String sell_date;

	public Integer getDistributor_id() {
		return Distributor_id;
	}

	public void setDistributor_id(Integer distributor_id) {
		Distributor_id = distributor_id;
	}

	public String getSell_date() {
		return sell_date;
	}

	public void setSell_date(String sell_date) {
		this.sell_date = sell_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCoupan_count() {
		return coupan_count;
	}

	public void setCoupan_count(Integer coupan_count) {
		this.coupan_count = coupan_count;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public Set<Coupan_Info> getCoupan_info() {
		return coupan_info;
	}

	public void setCoupan_info(Set<Coupan_Info> coupan_info) {
		this.coupan_info = coupan_info;
	}

	

}

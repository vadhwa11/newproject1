package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mobile_apps.coupan_master")
public class VidEntity {
	@Id
	@Column(name = "ser_no", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ser_no;
	@Column(name = "vochuer_id")
	private String vochuer_id;
	private String CoupanCode;

	private String gstn_no;
	private String pwd;
	private String name;
	private String email;
	private String mobile;
	private String address;
	private String pan;
	private String activate_date;
	private Integer flag;
	private String user_name;
	private String company_name;
	private String reference;

	public String getVochuer_id() {
		return vochuer_id;
	}

	public void setVochuer_id(String vochuer_id) {
		this.vochuer_id = vochuer_id;
	}

	public String getCoupanCode() {
		return CoupanCode;
	}

	public void setCoupanCode(String coupanCode) {
		CoupanCode = coupanCode;
	}

	public String getGstn_no() {
		return gstn_no;
	}

	public void setGstn_no(String gstn_no) {
		this.gstn_no = gstn_no;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getActivate_date() {
		return activate_date;
	}

	public void setActivate_date(String activate_date) {
		this.activate_date = activate_date;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String referenc) {
		this.reference = referenc;
	}

}

package com.test.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mobile_apps.add_coupon")
public class CoupanGenration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5084045954339440540L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ser_no;

	private int flag;

	public Integer getSer_no() {
		return ser_no;
	}

	public void setSer_no(Integer ser_no) {
		this.ser_no = ser_no;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Column(name = "coupan_code")
	private String coupanCode;

	private int Distributor_id;
	
	private String AddBy;

	private String Distributor_name;
	
	private String date;

	public int getDistriButor_id() {
		return Distributor_id;
	}

	public void setDistriButor_id(int distriButor_id) {
		Distributor_id = distriButor_id;
	}

	public String getDistributor_name() {
		return Distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		Distributor_name = distributor_name;
	}

	

	public String getCoupanCode() {
		return coupanCode;
	}

	public void setCoupanCode(String coupanCode) {
		this.coupanCode = coupanCode;
	}

	public int getDistributor_id() {
		return Distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		Distributor_id = distributor_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAddBy() {
		return AddBy;
	}

	public void setAddBy(String addBy) {
		AddBy = addBy;
	}
	
 
}

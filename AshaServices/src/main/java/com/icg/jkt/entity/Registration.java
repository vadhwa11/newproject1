package com.icg.jkt.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="REGISTRATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Registration  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8898351420837076798L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGISTRATION_SEQ")
	@SequenceGenerator(name="REGISTRATION_SEQ", sequenceName="REGISTRATION_SEQ", allocationSize=1)
	private int id;
	
	private  String emp_name;
	private String user_name;
	private String password;
	private String email_id;
	private String  mobile_no;
	private String address;
	private String state;
	private String country;
	
	

	public String getEmp_name() {
		return emp_name;
	}


	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail_id() {
		return email_id;
	}


	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}



	public String getMobile_no() {
		return mobile_no;
	}


	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Registration(){}
	

}

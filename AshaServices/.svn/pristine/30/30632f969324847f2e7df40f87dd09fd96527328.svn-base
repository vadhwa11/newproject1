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
@Table(name="EMP_PROFILE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmpProfile  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8240719160217808076L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGISTRATION_SEQ")
	@SequenceGenerator(name="REGISTRATION_SEQ", sequenceName="REGISTRATION_SEQ", allocationSize=1)
	private int id;
	
	private String emp_id;
	private String company_name;
	private String dept;
	private String mobile_no;

	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public EmpProfile(){}
	
	
	
}

package com.icg.jkt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="users_emp_group")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserUsergroupHospital implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5151374768712019318L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_EMP_GROUP_SEQ")
	@SequenceGenerator(name="USER_EMP_GROUP_SEQ", sequenceName="USER_EMP_GROUP_SEQ", allocationSize=1)
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
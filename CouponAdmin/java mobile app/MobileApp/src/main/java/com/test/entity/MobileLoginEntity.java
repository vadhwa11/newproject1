package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mobile_apps.admin")
public class MobileLoginEntity {
	@Id
	@Column(name = "serno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer serno;
	private String name;
	private String username;
	private String password;
	private String gmail;
	public Integer getSerno() {
		return serno;
	}
	public void setSerno(Integer serno) {
		this.serno = serno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	

}

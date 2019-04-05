package com.asha.icgweb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter

@JsonIgnoreProperties
public class LoginModel {

	
    private int id;
	
	private  String emp_name;
	private String user_name;
	private String password;
	private String email_id;
	private String  mobile_no;
	private String address;
	private String state;
	private String country;
	private String value1;
	private String value2;
	private String ip;
}

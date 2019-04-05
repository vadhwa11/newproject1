package com.icg.jkt.service;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

@Repository
public interface GenericApiService {

	String departmentList(HashMap<String, String> jsondata, HttpServletRequest request);

}

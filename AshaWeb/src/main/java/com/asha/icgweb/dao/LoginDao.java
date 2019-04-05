package com.asha.icgweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.asha.icgweb.entity.Registration;

@Repository
public interface LoginDao {

	List<Registration> validateExitingUserInfo(String user_name, String email_id);

	String empRegistration(Registration registration);

	String registration(Registration registration);

	List<Registration> getAllRegistration();

	

}

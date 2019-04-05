package com.icg.jkt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.EmpProfile;
import com.icg.jkt.entity.Registration;

@Repository
public interface LoginDao {

	List<Registration> validateExitingUserInfo(String user_name, String email_id);

	String empRegistration(Registration registration);

	String registration(Registration registration);
	String empCheck(EmpProfile empCheck);

	List<EmpProfile> validateEmpUserInfo(String emp_id);

	

}

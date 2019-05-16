package com.test.dao.gstr1;

import org.springframework.stereotype.Repository;

import com.test.entity.MobileLoginEntity;

@Repository
public interface MobileLoginDao {
	
	public MobileLoginEntity doLogin(MobileLoginEntity mobileLoginEntity);

}

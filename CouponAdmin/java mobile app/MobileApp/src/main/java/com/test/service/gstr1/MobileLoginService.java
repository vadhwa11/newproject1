package com.test.service.gstr1;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public interface MobileLoginService {

	public String doLogin(HashMap<String, Object> list, HttpServletRequest request);

}

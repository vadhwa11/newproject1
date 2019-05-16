package com.test.service.impl.gstr1;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.test.dao.gstr1.MobileLoginDao;
import com.test.entity.MobileLoginEntity;
import com.test.service.gstr1.MobileLoginService;
import com.test.utils.SessionManagement;

@Service
public class MobileLoginServiceImpl implements MobileLoginService {
	@Autowired
	MobileLoginDao mobileLoginDao;

	@Override
	public String doLogin(HashMap<String, Object> list, HttpServletRequest request) {
		
	
		JsonObject json = new JsonObject();
		if(list.containsKey("username")&&list.containsKey("password"))
		{
		MobileLoginEntity mobileLoginEntity = new MobileLoginEntity();
		mobileLoginEntity.setUsername(list.get("username").toString());
		mobileLoginEntity.setPassword(list.get("password").toString());
		MobileLoginEntity value = mobileLoginDao.doLogin(mobileLoginEntity);
		if (!value.getName().equalsIgnoreCase("Login failed")) {
			SessionManagement sessionManagement = new SessionManagement();
			HttpSession session=request.getSession(true);
			sessionManagement.setAuthToken(value.getSerno().toString());
			sessionManagement.setSessionId(session.getId());
			session.setAttribute("userInfo", sessionManagement);
			
			json.addProperty("Status", "Login Successfully");
			json.addProperty("authtoken", value.getSerno());
			json.addProperty("username", value.getUsername());
			
		} else {
			json.addProperty("Status", "please enter valid username and password");
			
		}
		}else
		{
			json.addProperty("status", "please enter username or password");
		}

		return json.toString();
	}

}

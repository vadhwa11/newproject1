package com.icg.jkt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.icg.jkt.dao.LoginDao;
import com.icg.jkt.entity.EmpProfile;
import com.icg.jkt.entity.Registration;
import com.icg.jkt.service.LoginService;
import com.icg.jkt.utils.ProjectUtils;
import com.icg.jkt.utils.Registration_Validator;
import com.sun.mail.imap.protocol.Status;



@Repository
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	Registration_Validator validator;
	
	@Autowired
	LoginDao ld;

	
	@Override
	public String empRegistration(HashMap<String, Object> jsondata, HttpServletRequest request,HttpServletResponse response) {
		// JsonObjectBuilder jsonResponse = Json.createObjectBuilder();
		JSONObject json = new JSONObject();
		Registration Registration = new Registration();
		if (!jsondata.isEmpty()) {

			// this is the validation on the registration
			
			if (!jsondata.containsKey("name") || jsondata.get("name") == null
					|| jsondata.get("name").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"emp_name is not contain in json data or it will be null or blank please check\"}";
			
			} else if (!jsondata.containsKey("user_name") || jsondata.get("user_name") == null
					|| jsondata.get("user_name").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"user_name is not contain in json data or it will be null or blank please check\"}";
			} else if (!jsondata.containsKey("password") || jsondata.get("password") == null
					|| jsondata.get("password").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"password is not contain in json data or it will be null or blank please check\"}";
			} else if (!jsondata.containsKey("email_id") || jsondata.get("email_id") == null
					|| jsondata.get("email_id").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"email_id is not contain in json data or it will be null or blank please check\"}";
			} else if (!jsondata.containsKey("mobile_no") || jsondata.get("mobile_no") == null
					|| jsondata.get("mobile_no").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"mobile_no is not contain in json data or it will be null or blank please check\"}";
			} else if (!jsondata.containsKey("address") || jsondata.get("address") == null
					|| jsondata.get("address").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"address is not contain in json data or it will be null or blank please check\"}";
			} else if (!jsondata.containsKey("state") || jsondata.get("state") == null
					|| jsondata.get("state").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"state is not contain in json data or it will be null or blank please check\"}";
			} else if (!jsondata.containsKey("country") || jsondata.get("country") == null
					|| jsondata.get("country").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"country is not contain in json data or it will be null or blank please check\"}";
			}

		
			 else {

				String email = jsondata.get("email_id").toString();
				// String username = jsondata.get("username").toString();
				// String pan = jsondata.get("panno").toString();
				String mobile = jsondata.get("mobile_no").toString();

				// if (validator.panValidate(pan) == false)
				// return "{\"status\":\"0\",\"msg\":\" Incorrect pan
				// format.\"}";

				if (validator.emailValidate(email) == false)
					return "{\"status\":\"0\",\"msg\":\" Incorrect email id format.\"}";

				if (validator.phoneValidate(mobile) == false)
					return "{\"status\":\"0\",\"msg\":\"Incorrect mobile number format.\"}";

				Registration.setEmp_name(jsondata.get("emp_name").toString());
				Registration.setUser_name(jsondata.get("user_name").toString());
				Registration.setPassword(jsondata.get("password").toString());
				Registration.setEmail_id(jsondata.get("email_id").toString());
				Registration.setMobile_no(jsondata.get("mobile_no").toString());
				Registration.setAddress(jsondata.get("address").toString());
				Registration.setState(jsondata.get("state").toString());
				Registration.setCountry(jsondata.get("country").toString());
				
				// Need to update below code
				//String currentdate = ProjectUtils.getCurrentDate();


				//Registration.setRegdate(currentdate.toString());
				List<Registration> empprofile = ld.validateExitingUserInfo(Registration.getUser_name().toString(), Registration.getEmail_id());

				if (empprofile.size() != 0) {
					if (empprofile.get(0).getUser_name()
							.equalsIgnoreCase(jsondata.get("user_nmae").toString())) {
						return "{\"status\":\"2\",\"msg\":\"user_name is already registered.so we can't Registered.\"}";
					} else if (empprofile.get(0).getEmail_id()
							.equalsIgnoreCase(jsondata.get("owner_gmail").toString())) {
						return "{\"status\":\"2\",\"msg\":\"email is already registered.so we can't Registered.\"}";
					}
				} else {

					String resp = ld.empRegistration(Registration);

					if (resp != null && resp.equalsIgnoreCase("200")) {
						json.put("msg", " emp registered successfully ");
						json.put("status", "1");
					} else if (resp != null && resp.equalsIgnoreCase("403")) {
						json.put("msg", " you are not authorized for this activity ");
						json.put("status", "0");
					} else {
						json.put("msg", resp);
						json.put("status", "0");

					}
				}
			}

		} else {

			json.put("status", "0");
			json.put("msg", "json not contain any object");

		}

		return json.toString();
	}


	
	@Override
	public String registration(JSONObject json, HttpServletRequest request,	HttpServletResponse response) 
	{
		JSONObject registration = json.getJSONObject("registration");
		JSONObject jsonObject = new JSONObject();
		Registration Registration = new Registration();
		
		if (!registration.equals(null)) {

			// this is the validation on the registration
			
			if (registration.get("emp_name") == null) {
				return "{\"status\":\"0\",\"msg\":\"emp_name is not contain in json data or it will be null or blank please check\"}";
			
			} else if (registration.get("user_name")==null) {
				return "{\"status\":\"0\",\"msg\":\"user_name is not contain in json data or it will be null or blank please check\"}";
			} else if (registration.get("password")==null){
				return "{\"status\":\"0\",\"msg\":\"password is not contain in json data or it will be null or blank please check\"}";
			} else if (registration.get("email_id")==null){
				return "{\"status\":\"0\",\"msg\":\"email_id is not contain in json data or it will be null or blank please check\"}";
			} else if (registration.get("mobile_no")==null) {
				return "{\"status\":\"0\",\"msg\":\"mobile_no is not contain in json data or it will be null or blank please check\"}";
			} else if (registration.get("address")==null) {
				return "{\"status\":\"0\",\"msg\":\"address is not contain in json data or it will be null or blank please check\"}";
			} else if (registration.get("state")==null) {
				return "{\"status\":\"0\",\"msg\":\"state is not contain in json data or it will be null or blank please check\"}";
			} else if (registration.get("country")==null) {
				return "{\"status\":\"0\",\"msg\":\"country is not contain in json data or it will be null or blank please check\"}";
			} else if (registration.get("value1")==null) {
					return "{\"status\":\"0\",\"msg\":\"value1 is not contain in json data or it will be null or blank please check\"}";
			}else if (registration.get("value2")==null) {
					return "{\"status\":\"0\",\"msg\":\"value2 is not contain in json data or it will be null or blank please check\"}";
			}			
		 else {

				String email = registration.get("email_id").toString();
				String mobile = registration.get("mobile_no").toString();

				if (validator.emailValidate(email) == false)
					return "{\"status\":\"0\",\"msg\":\" Incorrect email id format.\"}";

				if (validator.phoneValidate(mobile) == false)
					return "{\"status\":\"0\",\"msg\":\"Incorrect mobile number format.\"}";
				
				Registration.setEmp_name(registration.get("emp_name").toString());
				Registration.setUser_name(registration.get("user_name").toString());
				Registration.setPassword(registration.get("password").toString());
				Registration.setEmail_id(registration.get("email_id").toString());
				Registration.setMobile_no(registration.get("mobile_no").toString());
				Registration.setAddress(registration.get("address").toString());
				Registration.setState(registration.get("state").toString());
				Registration.setCountry(registration.get("country").toString());
				
				// Need to update below code
				// String currentdate = ProjectUtils.getCurrentDate();
                
				int total;
                String val1,val2;
                val1=(String) registration.get("value1");
                val2=(String) registration.get("value2");
                total=Integer.parseInt(val1)+Integer.parseInt(val2);
                System.out.println(total);
                
                String ip=(String) registration.get("ip");
                System.out.println("IP address" + ip);
                

				//Registration.setRegdate(currentdate.toString());
				List<Registration> empprofile = ld.validateExitingUserInfo(Registration.getUser_name().toString(), Registration.getEmail_id());
			


				if (empprofile.size() != 0) {
					if (empprofile.get(0).getUser_name()
							.equalsIgnoreCase(registration.get("user_name").toString())) {
						return "{\"status\":\"2\",\"msg\":\"user_name is already registered.Please change the user name.\"}";
					}
					else if (empprofile.get(0).getEmail_id()
							.equalsIgnoreCase(registration.get("email_id").toString())) {
						return "{\"status\":\"2\",\"msg\":\"email is already registered.Please change the e-mail address.\"}";
					}
				} else {

					String resp = ld.registration(Registration);

					if (resp != null && resp.equalsIgnoreCase("200")) {
						jsonObject.put("msg", " emp registered successfully ");
						jsonObject.put("status", "1");
					} else if (resp != null && resp.equalsIgnoreCase("403")) {
						jsonObject.put("msg", " you are not authorized for this activity ");
						jsonObject.put("status", "0");
					} else {
						jsonObject.put("msg", resp);
						jsonObject.put("status", "0");

					}
				}
			}

		} else {

			jsonObject.put("status", "0");
			jsonObject.put("msg", "json not contain any object");

		}
		

		return jsonObject.toString();
	}
	
	public boolean isEmpty(Object value) {
		if (value != null && !value.toString().trim().isEmpty() && value.toString() != "") {
			return true;
		} else {
			return false;
		}

	}


	
	@Override
	public String registrationMultiple(HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		Registration Registration = new Registration();
		EmpProfile emp_profile =new EmpProfile();
		if (!payload.isEmpty()) {

			// this is the validation on the registration
			
			if (!payload.containsKey("emp_name") || payload.get("emp_name") == null
					|| payload.get("emp_name").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"emp_name is not contain in json data or it will be null or blank please check\"}";
			
			} else if (!payload.containsKey("user_name") || payload.get("user_name") == null
					|| payload.get("user_name").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"user_name is not contain in json data or it will be null or blank please check\"}";
			} else if (!payload.containsKey("password") || payload.get("password") == null
					|| payload.get("password").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"password is not contain in json data or it will be null or blank please check\"}";
			} else if (!payload.containsKey("email_id") || payload.get("email_id") == null
					|| payload.get("email_id").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"email_id is not contain in json data or it will be null or blank please check\"}";
			} else if (!payload.containsKey("mobile_no") || payload.get("mobile_no") == null
					|| payload.get("mobile_no").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"mobile_no is not contain in json data or it will be null or blank please check\"}";
			} else if (!payload.containsKey("address") || payload.get("address") == null
					|| payload.get("address").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"address is not contain in json data or it will be null or blank please check\"}";
			} else if (!payload.containsKey("state") || payload.get("state") == null
					|| payload.get("state").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"state is not contain in json data or it will be null or blank please check\"}";
			} else if (!payload.containsKey("country") || payload.get("country") == null
					|| payload.get("country").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"country is not contain in json data or it will be null or blank please check\"}";
			}
		 else if (!payload.containsKey("value1") || payload.get("value1") == null
				|| payload.get("value1").toString().trim().equals("")) {
			return "{\"status\":\"0\",\"msg\":\"value1 is not contain in json data or it will be null or blank please check\"}";
		}
		 else if (!payload.containsKey("value2") || payload.get("value2") == null
				|| payload.get("value2").toString().trim().equals("")) {
			return "{\"status\":\"0\",\"msg\":\"value2 is not contain in json data or it will be null or blank please check\"}";
		}

		
			 else {

				String email = payload.get("email_id").toString();
				// String username = payload.get("username").toString();
				// String pan = payload.get("panno").toString();
				String mobile = payload.get("mobile_no").toString();

				// if (validator.panValidate(pan) == false)
				// return "{\"status\":\"0\",\"msg\":\" Incorrect pan
				// format.\"}";

				if (validator.emailValidate(email) == false)
					return "{\"status\":\"0\",\"msg\":\" Incorrect email id format.\"}";

				if (validator.phoneValidate(mobile) == false)
					return "{\"status\":\"0\",\"msg\":\"Incorrect mobile number format.\"}";
				
				Registration.setEmp_name(payload.get("emp_name").toString());
				Registration.setUser_name(payload.get("user_name").toString());
				Registration.setPassword(payload.get("password").toString());
				Registration.setEmail_id(payload.get("email_id").toString());
				Registration.setMobile_no(payload.get("mobile_no").toString());
				Registration.setAddress(payload.get("address").toString());
				Registration.setState(payload.get("state").toString());
				Registration.setCountry(payload.get("country").toString());
				emp_profile.setEmp_id(payload.get("emp_id").toString());
				emp_profile.setCompany_name(payload.get("company_name").toString());
				emp_profile.setDept(payload.get("dept").toString());
				emp_profile.setMobile_no(payload.get("mobile_no").toString());
				
				// Need to update below code
				// String currentdate = ProjectUtils.getCurrentDate();
				
				
				int total;
                String val1,val2;
                val1=(String) payload.get("value1");
                val2=(String) payload.get("value2");
                total=Integer.parseInt(val1)+Integer.parseInt(val2);
                System.out.println(total);
                
                String ip=(String) payload.get("ip");
                System.out.println("IP address" + ip);

				//Registration.setRegdate(currentdate.toString());
				List<Registration> empprofile = ld.validateExitingUserInfo(Registration.getUser_name().toString(), Registration.getEmail_id());
				List<EmpProfile> check_profile = ld.validateEmpUserInfo(emp_profile.getEmp_id().toString());
				if (empprofile.size() != 0) {
					if (empprofile.get(0).getUser_name()
							.equalsIgnoreCase(payload.get("user_name").toString())) {
						return "{\"status\":\"2\",\"msg\":\"user_name is already registered.Please change the user name.\"}";
					}
					else if (empprofile.get(0).getEmail_id()
							.equalsIgnoreCase(payload.get("email_id").toString())) {
						return "{\"status\":\"2\",\"msg\":\"email is already registered.Please change the e-mail address.\"}";
					}
					else if (check_profile.get(0).getEmp_id()
							.equalsIgnoreCase(payload.get("emp_id").toString())) {
						return "{\"status\":\"2\",\"msg\":\"emp_id is already registered.Please change the emp-id.\"}";
					}
				} else {

					String resp = ld.registration(Registration);
                    String resp1=ld.empCheck(emp_profile);
					if (resp != null || resp1 != null && resp.equalsIgnoreCase("200")||resp1.equalsIgnoreCase("200")) {
						json.put("msg", " emp registered successfully ");
						json.put("status", "1");
					} else if (resp != null && resp.equalsIgnoreCase("403")) {
						json.put("msg", " you are not authorized for this activity ");
						json.put("status", "0");
					} else {
						json.put("msg", resp);
						json.put("status", "0");

					}
				}
			}

		} else {

			json.put("status", "0");
			json.put("msg", "json not contain any object");

		}

		return json.toString();
	}
	
	public boolean isEmpty1(Object value) {
		if (value != null && !value.toString().trim().isEmpty() && value.toString() != "") {
			return true;
		} else {
			return false;
		}

	}
	

	@Override
	public String getRegistration(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		return "Emp get list";
	}



	@Override
	public String loginEmployee(HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		return null;
	}




	
}

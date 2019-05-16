package com.test.service.impl.gstr1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.test.dao.gstr1.impl.MobileServiceDao;
import com.test.entity.CoupanGenration;
import com.test.entity.Coupan_Distributor;
import com.test.entity.Coupan_Info;
import com.test.entity.Distributor_Address;
import com.test.entity.VidEntity;
import com.test.utils.CurrentDate;
import com.test.utils.SessionManagement;

@Service("MobileService")
public class MobileService {
	@Autowired
	MobileServiceDao mobileServiceDao;

	@Transactional
	public String CoupanRagistration(HashMap<String, String> jsondata) {
		VidEntity VidEntity = new VidEntity();
		VidEntity.setAddress(jsondata.get("address"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		String Activatedate = dateFormat.format(date);
		VidEntity.setActivate_date(Activatedate);
		VidEntity.setEmail(jsondata.get("email"));
		VidEntity.setUser_name(jsondata.get("user_name"));
		VidEntity.setGstn_no(jsondata.get("gstn_no"));
		VidEntity.setMobile(jsondata.get("mobile"));
		VidEntity.setName(jsondata.get("name"));
		VidEntity.setPan(jsondata.get("pan"));
		VidEntity.setPwd(jsondata.get("pwd"));
		VidEntity.setPan(jsondata.get("company_name"));
		VidEntity.setPwd(jsondata.get("reference"));

		System.out.println(VidEntity.toString());

		System.out.println(VidEntity.toString());

		String result = mobileServiceDao.CoupanRagistration(VidEntity);
		return result;
	}

	public String CoupanDistribution(HashMap<String, Object> jsondata) {
		Coupan_Distributor coupan_Distributor = new Coupan_Distributor();

		coupan_Distributor.setEmail(jsondata.get("email").toString());
		coupan_Distributor.setName(jsondata.get("name").toString());
		coupan_Distributor.setMobile(jsondata.get("mobile").toString());
		coupan_Distributor.setSell_date(CurrentDate.Date());
		Distributor_Address distributor_Address = new Distributor_Address();
		distributor_Address.setCity(jsondata.get("city").toString());
		distributor_Address.setState(jsondata.get("state").toString());
		distributor_Address.setPincode(Integer.parseInt(jsondata.get("pincode").toString()));
		distributor_Address.setTown(jsondata.get("town").toString());
		coupan_Distributor.setAddress(distributor_Address);

		Set<Coupan_Info> set = coupan_Distributor.getCoupan_info();
		List<CoupanGenration> list = mobileServiceDao.FatchCoupanForDistribution(coupan_Distributor);
		coupan_Distributor.setCoupan_count(list.size());
		for (CoupanGenration singleObject : list) {
			Coupan_Info coupan_Info = new Coupan_Info();
			coupan_Info.setCoupan_code(singleObject.getCoupanCode().toString());
			set.add(coupan_Info);
		}
		return mobileServiceDao.CoupanDistributor(coupan_Distributor);

	}

	public String CoupanGeneration(HashMap<String, String> jsonData, HttpServletRequest request) {
		CoupanGenration generation = new CoupanGenration();
		JsonObject json = new JsonObject();
		HttpSession session = request.getSession();
		SessionManagement sessionManagement = (SessionManagement) session.getAttribute("userInfo");
		if (sessionManagement.getAuthToken().equalsIgnoreCase( jsonData.get("authtoken").toString())) {
			generation.setCoupanCode(jsonData.get("coupancode").toString());
			generation.setDate(CurrentDate.Date());
			generation.setFlag(0);
			generation.setAddBy("jitendra");

			json.addProperty("status", "Voucher No "+mobileServiceDao.CoupanGeneration(generation)+" sucessfully added");
		} else {
			json.addProperty("status","you are not allowed to add Voucher ");
		}
		return json.toString();

	}

	public List<HashMap<String, String>> ShowActiveCoupon(HashMap<String, String> jsonData) {
		List<CoupanGenration> list = mobileServiceDao.ShowActiveCoupon();
		List<HashMap<String, String>> listofcoupon = new ArrayList<HashMap<String, String>>();
		for (CoupanGenration coupanGenration : list) {
			HashMap<String, String> singObject = new HashMap<String, String>();
			singObject.put("CouponCode", coupanGenration.getCoupanCode().toString());
			singObject.put("DistributorId", "123");
			listofcoupon.add(singObject);
		}

		return listofcoupon;
	}

	public List<HashMap<String, String>> availableCoupon(HashMap<String, String> jsonData) {
		// TODO Auto-generated method stub\

		List<CoupanGenration> list = mobileServiceDao.availableCoupon();
		List<HashMap<String, String>> listofcoupon = new ArrayList<HashMap<String, String>>();
		for (CoupanGenration coupanGenration : list) {
			HashMap<String, String> singObject = new HashMap<String, String>();
			singObject.put("CouponCode", coupanGenration.getCoupanCode().toString());
			singObject.put("DistributorId", "NA");
			listofcoupon.add(singObject);
		}
		return listofcoupon;
	}
}

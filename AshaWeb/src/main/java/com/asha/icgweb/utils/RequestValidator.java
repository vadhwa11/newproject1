package com.asha.icgweb.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestValidator {

	public static boolean loginPayloadValid(HashMap<String, String> payload) {
		Boolean result = false;
		try {
			String username = payload.get("username");
			String password = payload.get("password");

			if (username == null || password == null || username.length() == 0 || password.length() == 0) {
				result = false;
				
			} else {
				result = true;	
			}

		} catch (Exception e) {
			return false;
		}
		return result;
	}

	public static boolean saveInvoicePayloadValid(HashMap<String, Object> payload) {

		try {
			String cgst = (String) payload.get("cgst");
			String grand_tatal = (String) payload.get("grand_tatal");

			ArrayList<HashMap<String, String>> product_Item_list = (ArrayList<HashMap<String, String>>) payload
					.get("product_list");

			for (int index = 0; index < product_Item_list.size(); index++) {
				System.out.println(product_Item_list.get(index));
			}

			if (cgst == null || grand_tatal == null || cgst.length() == 0 || grand_tatal.length() == 0) {
				return false;
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

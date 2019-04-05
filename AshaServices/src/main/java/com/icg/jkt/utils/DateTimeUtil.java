package com.icg.jkt.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.RandomStringUtils;

public class DateTimeUtil {

	public synchronized static String genToken() {
		DateFormat sdf = new SimpleDateFormat("yyMMddHHmmssS");
		Date date = new Date();
		String tokenID = sdf.format(date);
		tokenID = tokenID.replace("-", "");
		tokenID = tokenID.replace("+", "");
		return RandomStringUtils.randomAlphabetic(5) + "" + tokenID;
	}

	public static long uniqueCurrentTimeMS() {

		DateFormat sdf = new SimpleDateFormat("YYMMdd");
		Date date = new Date();

		String value = sdf.format(date);
		Long id = Long.parseLong(value);

		UUID uUID = UUID.randomUUID();
		String uidst = uUID.toString();
		int length = uidst.length();
		StringBuilder builder = new StringBuilder();
		int ascci;
		for (int i = 0; i < length; i++) {
			ascci = uidst.charAt(i);
			builder.append(ascci);
		}
		String barcode = builder.toString();
		StringBuilder vuilder = new StringBuilder();
		Random random = new Random();
		for (int k = 0; k < 6; k++) {
			vuilder.append(barcode.charAt(random.nextInt(barcode.length())));
		}

		String uniqueid = vuilder.toString();
		Long longid = Long.parseLong(uniqueid);
		StringBuilder builder1 = new StringBuilder();
		Long longiduniuqe = Long.parseLong((builder1.append(id.toString()).append(longid.toString())).toString());
		return longiduniuqe;

	}

	public static String getOrderId() {
		String res = "ODI";

		DateFormat sdf = new SimpleDateFormat("YYYYMMdd");

		Date date = new Date();

		String value = sdf.format(date);

		UUID uUID = UUID.randomUUID();
		String uidst = uUID.toString();
		int length = uidst.length();
		StringBuilder builder = new StringBuilder();
		int ascci;

		for (int i = 0; i < length; i++) {
			ascci = uidst.charAt(i);
			builder.append(ascci);
		}
		String barcode = builder.toString();

		StringBuilder vuilder = new StringBuilder();
		vuilder.append(res).append(value);
		Random random = new Random();
		for (int k = 0; k < 9; k++) {
			vuilder.append(barcode.charAt(random.nextInt(barcode.length())));
		}

		return vuilder.toString();

	}

	public static String getDate() {
		DateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh-mm-ss");
		Date date = new Date();

		String value = sdf.format(date);
		return value;
	}

	public static String getID(String type) {
		StringBuilder builder = new StringBuilder();
		DateFormat sdf = new SimpleDateFormat("ddhhmm");
		String randomid = "12345678996321598785";
		Date date = new Date();

		String value = sdf.format(date);
		builder.append(value);

		Random random = new Random();
		for (int k = 0; k < 5; k++) {
			builder.append(randomid.charAt(random.nextInt(randomid.length())));
		}

		return builder.toString();

	}
	
	
	
	public static  String getShopId(String ownername)
	{
		StringBuilder builder = new StringBuilder();
		String startingStrinng=ownername.substring(0, 4);
		
		String randomid = "12345678996321598785";
		Date date = new Date();
	
		DateFormat sdf = new SimpleDateFormat("ddhhmm");
	
		String value = sdf.format(date);
		builder.append(startingStrinng);
		builder.append(value);

		Random random = new Random();
		for (int k = 0; k < 5; k++) {
			builder.append(randomid.charAt(random.nextInt(randomid.length())));
		}
		return builder.toString();
	}
		
	public static Map<String, Object> getCurrentDateAndTime() {

		Map<String, Object> map = new HashMap<String, Object>();
		String currentDate = "";
		String currentTime = "";
		String currentTimeWithoutSc = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String datetime = dateFormat.format(date);
		StringTokenizer s = new StringTokenizer(datetime, " ");
		while (s.hasMoreTokens()) {
			currentDate = s.nextToken();
			currentTime = s.nextToken();
		}
		map.put("currentDate", currentDate);
		/**
		 * Added By Ritu
		 */
		SimpleDateFormat dateFormatWithoutSc = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String datetimeWithoutSc = dateFormatWithoutSc.format(date);
		StringTokenizer strWithoutSc = new StringTokenizer(datetimeWithoutSc, " ");
		while (strWithoutSc.hasMoreTokens()) {
			strWithoutSc.nextToken();
			currentTimeWithoutSc = strWithoutSc.nextToken();
		}
		map.put("currentTime", currentTimeWithoutSc);
		map.put("currentTimeWithoutSc", currentTimeWithoutSc);
		return map;
	}
	
	public static Map<String, Object> getCurrentTimeWithoutSecond() {

		Map<String, Object> map = new HashMap<String, Object>();
		String currentDate = "";
		String currentTime = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		String datetime = dateFormat.format(date);
		StringTokenizer s = new StringTokenizer(datetime, " ");
		while (s.hasMoreTokens()) {
			currentDate = s.nextToken();
			currentTime = s.nextToken();
		}
		map.put("currentDate", currentDate);
		map.put("currentTime", currentTime);
		return map;
	}
	
	public static Date convertStringTypeDateToDateType(String date) {
		Date orderDateTime = null;

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (date != null) {
			try {
				orderDateTime = df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return orderDateTime;
	}


	public static void main(String[] args) {
		//System.out.println(getDate());
	}

}
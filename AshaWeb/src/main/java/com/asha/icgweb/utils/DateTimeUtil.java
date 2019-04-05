package com.asha.icgweb.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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
		
		


	public static void main(String[] args) {
		//System.out.println(getDate());
	}

}

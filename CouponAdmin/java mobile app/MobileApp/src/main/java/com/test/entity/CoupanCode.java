package com.test.entity;

import java.io.Serializable;
import java.util.Random;

import com.test.utils.CurrentDate;

public class CoupanCode implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private static Integer Coupan_code;

	private int coupanCode;
	private String Date = null;
	Random randam = new Random();

	public CoupanCode() {

		this.coupanCode = randam.nextInt((1000000 * 850) / 450);
		this.Date = CurrentDate.CoupanGenrationDate();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Date == null) ? 0 : Date.hashCode());
		result = prime * result + coupanCode + randam.nextInt();

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoupanCode other = (CoupanCode) obj;
		if (Date == null) {
			if (other.Date != null)
				return false;
		} else if (!Date.equals(other.Date))
			return false;
		if (coupanCode != other.coupanCode)
			return false;
		return true;
	}

}

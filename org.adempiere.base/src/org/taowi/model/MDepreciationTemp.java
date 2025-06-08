package org.taowi.model;

import java.math.BigDecimal;

public class MDepreciationTemp {

	int year;
	BigDecimal amtdepperyear;
	BigDecimal amtleftperyear;

	public MDepreciationTemp() {

	}

	public void create(int year, BigDecimal amtdepperyear, BigDecimal amtleftperyear) {
		this.setYear(year);
		this.setAmtdepperyear(amtdepperyear);
		this.setAmtleftperyear(amtleftperyear);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public BigDecimal getAmtDepPerYear() {
		return amtdepperyear;
	}

	public void setAmtdepperyear(BigDecimal amtdepperyear) {
		this.amtdepperyear = amtdepperyear;
	}

	public BigDecimal getAmtLeftPerYear() {
		return amtleftperyear;
	}

	public void setAmtleftperyear(BigDecimal amtleftperyear) {
		this.amtleftperyear = amtleftperyear;
	}

}

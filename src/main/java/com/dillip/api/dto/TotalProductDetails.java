package com.dillip.api.dto;

public class TotalProductDetails {
	private String totalQuantity;
	private String totalTaxableValue;
	private String totalCgstAmount;
	private String totalSgstAmount;
	private String totalAllAmount;
	private String gstAmount;
	private String totalAmtInWord;
	public String getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getTotalTaxableValue() {
		return totalTaxableValue;
	}
	public void setTotalTaxableValue(String totalTaxableValue) {
		this.totalTaxableValue = totalTaxableValue;
	}
	public String getTotalCgstAmount() {
		return totalCgstAmount;
	}
	public void setTotalCgstAmount(String totalCgstAmount) {
		this.totalCgstAmount = totalCgstAmount;
	}
	public String getTotalSgstAmount() {
		return totalSgstAmount;
	}
	public void setTotalSgstAmount(String totalSgstAmount) {
		this.totalSgstAmount = totalSgstAmount;
	}
	public String getTotalAllAmount() {
		return totalAllAmount;
	}
	public void setTotalAllAmount(String totalAllAmount) {
		this.totalAllAmount = totalAllAmount;
	}
	public String getGstAmount() {
		return gstAmount;
	}
	public void setGstAmount(String gstAmount) {
		this.gstAmount = gstAmount;
	}
	public String getTotalAmtInWord() {
		return totalAmtInWord;
	}
	public void setTotalAmtInWord(String totalAmtInWord) {
		this.totalAmtInWord = totalAmtInWord;
	}
	@Override
	public String toString() {
		return "TotalProductDetails [totalQuantity=" + totalQuantity + ", totalTaxableValue=" + totalTaxableValue
				+ ", totalCgstAmount=" + totalCgstAmount + ", totalSgstAmount=" + totalSgstAmount + ", totalAllAmount="
				+ totalAllAmount + ", gstAmount=" + gstAmount + ", totalAmtInWord=" + totalAmtInWord + "]";
	}
}

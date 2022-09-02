package com.dillip.api.dto;

public class BillToDetails {
	private String billToName;
	private String billToAddress;
	private String billToGstin;
	private String billToState;
	private String billToStateCode;
	public String getBillToName() {
		return billToName;
	}
	public void setBillToName(String billToName) {
		this.billToName = billToName;
	}
	public String getBillToAddress() {
		return billToAddress;
	}
	public void setBillToAddress(String billToAddress) {
		this.billToAddress = billToAddress;
	}
	public String getBillToGstin() {
		return billToGstin;
	}
	public void setBillToGstin(String billToGstin) {
		this.billToGstin = billToGstin;
	}
	public String getBillToState() {
		return billToState;
	}
	public void setBillToState(String billToState) {
		this.billToState = billToState;
	}
	public String getBillToStateCode() {
		return billToStateCode;
	}
	public void setBillToStateCode(String billToStateCode) {
		this.billToStateCode = billToStateCode;
	}
	@Override
	public String toString() {
		return "BillToDetails [billToName=" + billToName + ", billToAddress=" + billToAddress + ", billToGstin="
				+ billToGstin + ", billToState=" + billToState + ", billToStateCode=" + billToStateCode + "]";
	}
}

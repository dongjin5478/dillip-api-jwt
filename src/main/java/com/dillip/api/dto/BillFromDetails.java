package com.dillip.api.dto;

public class BillFromDetails {
	private String reverseCharge;
	private String invoiceNo;
	private String invoiceDate;
	private String billFromState;
	private String billFromStateCode;
	public String getReverseCharge() {
		return reverseCharge;
	}
	public void setReverseCharge(String reverseCharge) {
		this.reverseCharge = reverseCharge;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getBillFromState() {
		return billFromState;
	}
	public void setBillFromState(String billFromState) {
		this.billFromState = billFromState;
	}
	public String getBillFromStateCode() {
		return billFromStateCode;
	}
	public void setBillFromStateCode(String billFromStateCode) {
		this.billFromStateCode = billFromStateCode;
	}
	@Override
	public String toString() {
		return "BillFromDetails [reverseCharge=" + reverseCharge + ", invoiceNo=" + invoiceNo + ", invoiceDate="
				+ invoiceDate + ", billFromState=" + billFromState + ", billFromStateCode=" + billFromStateCode + "]";
	}
}

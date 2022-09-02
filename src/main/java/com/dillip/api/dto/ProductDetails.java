package com.dillip.api.dto;

public class ProductDetails {
	private String serialNo;
	private String productName;
	private String hsnCode;
	private String quantity;
	private String unit;
	private String rate;
	private String taxableValue;
	private String cgstRate;
	private String cgstAmount;
	private String sgstRate;
	private String sgstAmount;
	private String total;
	
	public ProductDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDetails( String serialNo, String productName, String hsnCode, String quantity, String unit, String rate,
			String taxableValue, String cgstRate, String cgstAmount, String sgstRate, String sgstAmount, String total) {
		super();
		this.serialNo = serialNo;
		this.productName = productName;
		this.hsnCode = hsnCode;
		this.quantity = quantity;
		this.unit = unit;
		this.rate = rate;
		this.taxableValue = taxableValue;
		this.cgstRate = cgstRate;
		this.cgstAmount = cgstAmount;
		this.sgstRate = sgstRate;
		this.sgstAmount = sgstAmount;
		this.total = total;
	}

	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTaxableValue() {
		return taxableValue;
	}
	public void setTaxableValue(String taxableValue) {
		this.taxableValue = taxableValue;
	}
	public String getCgstRate() {
		return cgstRate;
	}
	public void setCgstRate(String cgstRate) {
		this.cgstRate = cgstRate;
	}
	public String getCgstAmount() {
		return cgstAmount;
	}
	public void setCgstAmount(String cgstAmount) {
		this.cgstAmount = cgstAmount;
	}
	public String getSgstRate() {
		return sgstRate;
	}
	public void setSgstRate(String sgstRate) {
		this.sgstRate = sgstRate;
	}
	public String getSgstAmount() {
		return sgstAmount;
	}
	public void setSgstAmount(String sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "ProductDetails [productName=" + productName + ", hsnCode=" + hsnCode + ", quantity=" + quantity
				+ ", unit=" + unit + ", rate=" + rate + ", taxableValue=" + taxableValue + ", cgstRate=" + cgstRate
				+ ", cgstAmount=" + cgstAmount + ", sgstRate=" + sgstRate + ", sgstAmount=" + sgstAmount + ", total="
				+ total + "]";
	}
}

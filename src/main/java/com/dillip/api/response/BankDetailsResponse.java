package com.dillip.api.response;

public class BankDetailsResponse {
	private String micrCode;
	private String branchName;
	private String address;
	private String state;
	private String contact;
	private String cityName;
	private String center;
	private String district;
	private String swift;
	private String bankName;
	private String bankCode;
	private String ifscCode;

	public String getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	@Override
	public String toString() {
		return "BankDetailsResponse [micrCode=" + micrCode + ", branchName=" + branchName + ", address=" + address
				+ ", state=" + state + ", contact=" + contact + ", cityName=" + cityName + ", center=" + center
				+ ", district=" + district + ", swift=" + swift + ", bankName=" + bankName + ", bankCode=" + bankCode
				+ ", ifscCode=" + ifscCode + "]";
	}

}

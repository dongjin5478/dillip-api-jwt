package com.dillip.api.dto;

public class PinCodeDTO {
	private String Name;
	private String Description;
	private String BranchType;
	private String DeliveryStatus;
	private String Circle;
	private String District;
	private String Division;
	private String Region;
	private String Block;
	private String State;
	private String Country;
	private String Pincode;

	public PinCodeDTO() {
		super();
	}

	public PinCodeDTO(String name, String description, String branchType, String deliveryStatus, String circle,
			String district, String division, String region, String block, String state, String country,
			String pincode) {
		super();
		Name = name;
		Description = description;
		BranchType = branchType;
		DeliveryStatus = deliveryStatus;
		Circle = circle;
		District = district;
		Division = division;
		Region = region;
		Block = block;
		State = state;
		Country = country;
		Pincode = pincode;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getBranchType() {
		return BranchType;
	}

	public void setBranchType(String branchType) {
		BranchType = branchType;
	}

	public String getDeliveryStatus() {
		return DeliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		DeliveryStatus = deliveryStatus;
	}

	public String getCircle() {
		return Circle;
	}

	public void setCircle(String circle) {
		Circle = circle;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getDivision() {
		return Division;
	}

	public void setDivision(String division) {
		Division = division;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public String getBlock() {
		return Block;
	}

	public void setBlock(String block) {
		Block = block;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getPincode() {
		return Pincode;
	}

	public void setPincode(String pincode) {
		Pincode = pincode;
	}

	@Override
	public String toString() {
		return "PinCodeDTO [Name=" + Name + ", Description=" + Description + ", BranchType=" + BranchType
				+ ", DeliveryStatus=" + DeliveryStatus + ", Circle=" + Circle + ", District=" + District + ", Division="
				+ Division + ", Region=" + Region + ", Block=" + Block + ", State=" + State + ", Country=" + Country
				+ ", Pincode=" + Pincode + "]";
	}

}

package com.dillip.api.response;

public class ReportResponse {
	private String address;
	private String vehicleNumber;
	private String grossWeight;
	private String tareWeight;
	private String netWeight;
	private String grossWeightDate;
	private String tareWeightDate;
	private String createdDate;
	public ReportResponse() {
		super();
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}
	public String getTareWeight() {
		return tareWeight;
	}
	public void setTareWeight(String tareWeight) {
		this.tareWeight = tareWeight;
	}
	public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	public String getGrossWeightDate() {
		return grossWeightDate;
	}
	public void setGrossWeightDate(String grossWeightDate) {
		this.grossWeightDate = grossWeightDate;
	}
	public String getTareWeightDate() {
		return tareWeightDate;
	}
	public void setTareWeightDate(String tareWeightDate) {
		this.tareWeightDate = tareWeightDate;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}

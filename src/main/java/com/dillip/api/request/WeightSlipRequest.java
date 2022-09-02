package com.dillip.api.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WeightSlipRequest {
	private String address;
	private String vehicleNumber;
	private String grossWeight;
	private String tareWeight;
	private LocalDateTime grossWeightDate;
	private LocalDateTime tareWeightDate;
	@JsonIgnore
	private String message;
	private boolean checked;
	
	public WeightSlipRequest() {
		super();
		this.message = "Thank You  !  Visit Again";
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
	public LocalDateTime getGrossWeightDate() {
		return grossWeightDate;
	}
	public void setGrossWeightDate(LocalDateTime grossWeightDate) {
		this.grossWeightDate = grossWeightDate;
	}
	public LocalDateTime getTareWeightDate() {
		return tareWeightDate;
	}
	public void setTareWeightDate(LocalDateTime tareWeightDate) {
		this.tareWeightDate = tareWeightDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "WeightSlipRequest [address=" + address + ", vehicleNumber=" + vehicleNumber + ", grossWeight="
				+ grossWeight + ", tareWeight=" + tareWeight + ", grossWeightDate=" + grossWeightDate
				+ ", tareWeightDate=" + tareWeightDate + ", checked=" + checked + "]";
	}
}

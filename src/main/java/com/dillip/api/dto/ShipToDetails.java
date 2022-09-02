package com.dillip.api.dto;

public class ShipToDetails {
	private String shipToName;
	private String shipToAddress;
	private String shipToGstin;
	private String shipToState;
	private String shipToStateCode;
	public String getShipToName() {
		return shipToName;
	}
	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}
	public String getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	public String getShipToGstin() {
		return shipToGstin;
	}
	public void setShipToGstin(String shipToGstin) {
		this.shipToGstin = shipToGstin;
	}
	public String getShipToState() {
		return shipToState;
	}
	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}
	public String getShipToStateCode() {
		return shipToStateCode;
	}
	public void setShipToStateCode(String shipToStateCode) {
		this.shipToStateCode = shipToStateCode;
	}
	@Override
	public String toString() {
		return "ShipToDetails [shipToName=" + shipToName + ", shipToAddress=" + shipToAddress + ", shipToGstin="
				+ shipToGstin + ", shipToState=" + shipToState + ", shipToStateCode=" + shipToStateCode + "]";
	}
}

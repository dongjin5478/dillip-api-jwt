package com.dillip.api.dto;

public class TransportDetails {
	private String transMode;
	private String vehicleNo;
	private String supplyDate;
	private String supplyPlace;
	private String transporterName;
	public String getTransMode() {
		return transMode;
	}
	public void setTransMode(String transMode) {
		this.transMode = transMode;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getSupplyDate() {
		return supplyDate;
	}
	public void setSupplyDate(String supplyDate) {
		this.supplyDate = supplyDate;
	}
	public String getSupplyPlace() {
		return supplyPlace;
	}
	public void setSupplyPlace(String supplyPlace) {
		this.supplyPlace = supplyPlace;
	}
	public String getTransporterName() {
		return transporterName;
	}
	public void setTransporterName(String transporterName) {
		this.transporterName = transporterName;
	}
	@Override
	public String toString() {
		return "TransportDetails [transMode=" + transMode + ", vehicleNo=" + vehicleNo + ", supplyDate=" + supplyDate
				+ ", supplyPlace=" + supplyPlace + ", transporterName=" + transporterName + "]";
	}
}

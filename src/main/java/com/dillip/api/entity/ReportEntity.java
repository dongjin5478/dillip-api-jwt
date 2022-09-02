package com.dillip.api.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_report_data")
public class ReportEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sl_no")
	private int reportSlNo;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "vehicle_number")
	private String vehicleNumber;
	
	@Column(name = "gross_weight")
	private String grossWeight;
	
	@Column(name = "tare_weight")
	private String tareWeight;
	
	@Column(name = "net_weight")
	private String netWeight;
	
	@Column(name = "gross_weight_date")
	private LocalDateTime grossWeightDate;
	
	@Column(name = "tare_weight_date")
	private LocalDateTime tareWeightDate;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	public ReportEntity() {
		super();
	}

	public int getReportSlNo() {
		return reportSlNo;
	}

	public void setReportSlNo(int reportSlNo) {
		this.reportSlNo = reportSlNo;
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	
}

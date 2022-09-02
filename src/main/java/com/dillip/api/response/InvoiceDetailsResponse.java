package com.dillip.api.response;

import java.util.List;

import com.dillip.api.dto.BillFromDetails;
import com.dillip.api.dto.BillToDetails;
import com.dillip.api.dto.ProductDetails;
import com.dillip.api.dto.ShipToDetails;
import com.dillip.api.dto.TotalProductDetails;
import com.dillip.api.dto.TransportDetails;

public class InvoiceDetailsResponse {
	private BillFromDetails billFromDetails;
	private TransportDetails transportDetails;
	private BillToDetails billToDetails;
	private ShipToDetails shipToDetails;
	private List<ProductDetails> productDetailsList;
	private TotalProductDetails totalProductDetails;
	public BillFromDetails getBillFromDetails() {
		return billFromDetails;
	}
	public void setBillFromDetails(BillFromDetails billFromDetails) {
		this.billFromDetails = billFromDetails;
	}
	public TransportDetails getTransportDetails() {
		return transportDetails;
	}
	public void setTransportDetails(TransportDetails transportDetails) {
		this.transportDetails = transportDetails;
	}
	public BillToDetails getBillToDetails() {
		return billToDetails;
	}
	public void setBillToDetails(BillToDetails billToDetails) {
		this.billToDetails = billToDetails;
	}
	public ShipToDetails getShipToDetails() {
		return shipToDetails;
	}
	public void setShipToDetails(ShipToDetails shipToDetails) {
		this.shipToDetails = shipToDetails;
	}
	public List<ProductDetails> getProductDetailsList() {
		return productDetailsList;
	}
	public void setProductDetailsList(List<ProductDetails> productDetailsList) {
		this.productDetailsList = productDetailsList;
	}
	public TotalProductDetails getTotalProductDetails() {
		return totalProductDetails;
	}
	public void setTotalProductDetails(TotalProductDetails totalProductDetails) {
		this.totalProductDetails = totalProductDetails;
	}
	@Override
	public String toString() {
		return "InvoiceDetailsResponse [billFromDetails=" + billFromDetails + ", transportDetails=" + transportDetails
				+ ", billToDetails=" + billToDetails + ", shipToDetails=" + shipToDetails + ", productDetailsList="
				+ productDetailsList + ", totalProductDetails=" + totalProductDetails + "]";
	}
}

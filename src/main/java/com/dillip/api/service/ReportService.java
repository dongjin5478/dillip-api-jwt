package com.dillip.api.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dillip.api.dto.UniversityDetailsDTO;
import com.dillip.api.entity.ContactDetails;
import com.dillip.api.request.WeightSlipRequest;
import com.dillip.api.response.MediaFile;
import com.dillip.api.response.ReportResponse;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
	String startReportApi();
	
	MediaFile exportReport(WeightSlipRequest weightSlipRequest) throws JRException, IOException;
	
	List<ReportResponse> findAllWeightSlipDetails();
	
	List<ReportResponse> findByVehicleNumber(String vehicleNumber);
	
	String deleteAllWeightSlip();
	
	String sendEmail(ContactDetails contact);
	
	List<UniversityDetailsDTO> getUniversityDetailsByCountryName(String countryName);

//	MediaFile generateInvoice() throws JRException, IOException;
	
	ResponseEntity<byte[]> generateInvoice() throws JRException, IOException;
}

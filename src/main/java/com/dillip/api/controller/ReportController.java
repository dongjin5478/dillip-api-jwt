package com.dillip.api.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dillip.api.entity.ContactDetails;
import com.dillip.api.request.WeightSlipRequest;
import com.dillip.api.response.ApiEntity;
import com.dillip.api.response.ApiResponseObject;
import com.dillip.api.response.MediaFile;
import com.dillip.api.response.ReportResponse;
import com.dillip.api.service.ReportService;
import com.dillip.api.util.ProjectConstant;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;

@RestController
@CrossOrigin
@Api(tags = "4. Report Controller")
@Slf4j
public class ReportController {

	@Autowired
	private ReportService reportService;

	@Operation(summary = "Welcome Message")
	@GetMapping(path = "/")
	public ResponseEntity<ApiResponseObject> startApi() {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		String reMessage = null;
		try {
			reMessage = reportService.startReportApi();

			if (reMessage.equals(ProjectConstant.SUCCESS_MSG)) {
				message = "API has been Started";
				status = HttpStatus.OK;
				log.info("########## API is UP ##########");
			} else {
				message = ProjectConstant.ERR_MSG;
				status = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			log.info("########## Exception Occured ########## " + e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(new ApiEntity<String>(message, reMessage), httpHeaders, status);
	}

	@Operation(summary = "Download Weight Slip in PDF")
	@PostMapping(path = "/weightslip")
	public ResponseEntity<ApiResponseObject> generateReport(
			@Parameter(name = "in_weightSlipRequest", description = "WeightSlipRequest", required = true) @RequestBody WeightSlipRequest weightSlipRequest)
			throws JRException, IOException {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		MediaFile response = null;
		log.info("########## Hitting generateReport() in Controller ########### :: WeightSlipRequest :: "
				+ weightSlipRequest);
		try {
			response = reportService.exportReport(weightSlipRequest);

			if (response != null) {
				message = "Weight Slip has been generated Successfully";
				status = HttpStatus.OK;
			} else {
				message = ProjectConstant.ERR_MSG;
				status = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			log.info("########## Exception Occured ########## " + e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(new ApiEntity<MediaFile>(message, response), httpHeaders, status);
	}

	@Operation(summary = "Find All Weight Slip Details")
	@GetMapping(path = "/weightslipdetails")
	public ResponseEntity<ApiResponseObject> findAllWeightSlipDetails() {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		List<ReportResponse> response = null;
		log.info("########## Hitting findAllWeightSlipDetails() in Controller Layer ##########");
		try {
			response = reportService.findAllWeightSlipDetails();

			if (!response.isEmpty()) {
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			} else {
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info("########## Exception Occured in findAllWeightSlipDetails() in Controller Layer ########## " + e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(new ApiEntity<List<ReportResponse>>(message, response), httpHeaders, status);
	}

	@Operation(summary = "Find Weight Slip Details With Vehicle Number")
	@GetMapping(path = "/weightslipdetails/{vehicleNumber}")
	public ResponseEntity<ApiResponseObject> findReportDetailsByVehicleNumber(@PathVariable String vehicleNumber) {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		List<ReportResponse> response = null;
		log.info("########## Hitting findReportDetailsByVehicleNumber() in Controller Layer :: vehicleNumber :: "
				+ vehicleNumber);
		try {
			response = reportService.findByVehicleNumber(vehicleNumber);

			if (!response.isEmpty()) {
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			} else {
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info(
					"########## Exception Occured in findReportDetailsByVehicleNumber() in Controller Layer ########## "
							+ e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(new ApiEntity<List<ReportResponse>>(message, response), httpHeaders, status);
	}

	@Operation(summary = "Delete all the weight slip record data from Database")
	@DeleteMapping(path = "/delete")
	public ResponseEntity<ApiResponseObject> deleteAllWeightSlipRecord() {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		String response = null;
		log.info("########## Hitting deleteAllWeightSlipRecord() in Controller Layer ##########");
		try {
			response = reportService.deleteAllWeightSlip();

			if (response.equals(ProjectConstant.SUCCESS_MSG)) {
				message = ProjectConstant.DELETED_MSG;
				status = HttpStatus.OK;
			} else {
				message = "Error While Deleting the Resources";
				status = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			log.info("########## Exception Occured in deleteAllWeightSlipRecord() in Controller Layer ########## " + e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(new ApiEntity<String>(message, response), httpHeaders, status);
	}

	@Operation(summary = "Send Email")
	@PostMapping(path = "/send-email")
	public ResponseEntity<ApiResponseObject> sendEmail(@RequestBody ContactDetails contact) {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		String response = null;
		log.info("########## Hitting sendEmail() in Controller Layer :: ContactDetails :: " + contact);
		try {
			response = reportService.sendEmail(contact);

			if (response.equals(ProjectConstant.SUCCESS_MSG)) {
				message = "Thank You ! Your feedback has been Submitted.";
				status = HttpStatus.OK;
			} else {
				message = "Sorry ! An Error Occured While Sending Your Message.";
				status = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			log.info("########## Exception Occured sendEmail() in Controller Layer ########## " + e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(new ApiEntity<String>(message, response), httpHeaders, status);
	}

	@Operation(summary = "Download Invoice Bill In PDF")
	@GetMapping(path = "/invoice")
	public ResponseEntity<byte[]> generateInvoice() throws JRException, IOException {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		ResponseEntity<byte[]> generateInvoice = null;
//		String message = null;
//		MediaFile response = null;
//		LOGGER.log(Level.INFO, "########## Hitting generateReport() in Controller ########### :: WeightSlipRequest :: "+weightSlipRequest);
		try {
			generateInvoice = reportService.generateInvoice();

//			if(response!=null)
//			{
//				message = "Invoice Bill has been generated Successfully";
//				status = HttpStatus.OK;
//			}
//			else
//			{
//				message = ProjectConstant.ERR_MSG;
//				status = HttpStatus.BAD_REQUEST;
//			}
		} catch (Exception e) {
			log.info("########## Exception Occured ########## " + e);
//			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return generateInvoice;
	}

}

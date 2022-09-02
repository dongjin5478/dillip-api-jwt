package com.dillip.api.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dillip.api.dto.RandomQuoteDTO;
import com.dillip.api.response.ApiEntity;
import com.dillip.api.response.ApiResponseObject;
import com.dillip.api.response.BankDetailsResponse;
import com.dillip.api.response.PostOfficeDetailsResponse;
import com.dillip.api.service.CommonService;
import com.dillip.api.util.ProjectConstant;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@CrossOrigin
@Api(tags = "2. Common Controller")
public class CommonController {
	
	private final static Logger LOGGER = Logger.getLogger("Dillip Logger");
	
	@Autowired
	private CommonService commonService;
	
	@Operation(summary = "getHelloMessage")
	@GetMapping(path = "/hello/{name}")
	public ResponseEntity<ApiResponseObject> getHelloMessage(@PathVariable(name = "name") String name)
	{	
		
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		String response = null;
		LOGGER.log(Level.INFO, "############# Hitting /hello API in Controller Layer ###############");
		try {
			response = commonService.getHelloMessage(name);
			if(response != null)
			{
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			}
			else
			{
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "############# Exception Occured in /hello API in Controller Layer ##########"+e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(new ApiEntity<>(message, response), httpHeaders, status);
	}
	
	@Operation(summary = "getRandomQuote")
	@GetMapping(path = "/quote")
	public ResponseEntity<ApiResponseObject> getRandomQuote()
	{	
		
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		RandomQuoteDTO response = null;
		LOGGER.log(Level.INFO, "############# Hitting /quote API in Controller Layer ###############");
		try {
			response = commonService.getRandomQuote();
			if(response != null)
			{
				message = "Quote Found";
				status = HttpStatus.OK;
			}
			else
			{
				message = "Quote Not Found";
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "############# Exception Occured in getRandomQuote() in Controller Layer ##########"+e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(new ApiEntity<RandomQuoteDTO>(message, response), httpHeaders, status);
	}
	
	@Operation(summary = "getPostOfficeDetailsByPinCode")
	@GetMapping(path = "/postoffice-details/{pin_code}")
	public ResponseEntity<ApiResponseObject> getPostOfficeDetailsByPinCode(
			 @Parameter(description = "String",required = true) @PathVariable(name = "pin_code",required = true) String pinCode
			)
	{	
		
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		List<PostOfficeDetailsResponse> response = null;
		LOGGER.log(Level.INFO, "############# Hitting getPostOfficeDetailsByPinCode() in Controller Layer :: PinCode :: "+pinCode);
		try {
			response = commonService.getPostOfficeDetailsByPinCode(pinCode);
			if(!response.isEmpty() && response != null)
			{
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			}
			else
			{
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "############# Exception Occured in getPostOfficeDetailsByPinCode() in Controller Layer ##########"+e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(new ApiEntity<List<PostOfficeDetailsResponse>>(message, response), httpHeaders, status);
	}
	
	@Operation(summary = "getPostOfficeDetailsByPostOfficeBranchName")
	@GetMapping(path = "/postoffice-details")
	public ResponseEntity<ApiResponseObject> getPostOfficeDetailsByBranchName(
			 @Parameter(description = "String",required = true) @RequestParam (name = "branch_name",required = true) String branchName
			)
	{	
		
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		List<PostOfficeDetailsResponse> response = null;
		LOGGER.log(Level.INFO, "############# Hitting getPostOfficeDetailsByBranchName() in Controller Layer :: PostOfficeBranchName :: "+branchName);
		try {
			response = commonService.getPostOfficeDetailsByBranchName(branchName);
			if(!response.isEmpty() && response != null)
			{
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			}
			else
			{
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "############# Exception Occured in getPostOfficeDetailsByBranchName() in Controller Layer ##########"+e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(new ApiEntity<List<PostOfficeDetailsResponse>>(message, response), httpHeaders, status);
	}
	
	@Operation(summary = "getBankDetailsByIfscCode")
	@GetMapping(path = "/bank-details/{ifsc_code}")
	public ResponseEntity<ApiResponseObject> getBankDetailsByIfsc(
			 @Parameter(description = "String",required = true) @PathVariable(name = "ifsc_code",required = true) String ifscCode
			)
	{	
		
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		BankDetailsResponse response = null;
		LOGGER.log(Level.INFO, "############# Hitting getBankDetailsByIfsc() in Controller Layer :: IFSC Code :: "+ifscCode);
		try {
			response = commonService.getBankDetailsByIfsc(ifscCode);
			if(response != null)
			{
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			}
			else
			{
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "############# Exception Occured in getBankDetailsByIfsc() in Controller Layer ##########"+e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(new ApiEntity<BankDetailsResponse>(message, response), httpHeaders, status);
	}
}

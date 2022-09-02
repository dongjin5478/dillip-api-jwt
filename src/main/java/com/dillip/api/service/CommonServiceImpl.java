package com.dillip.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.dillip.api.dto.BankDetailsDTO;
import com.dillip.api.dto.PinCodeDTO;
import com.dillip.api.dto.PinCodeDetailsDTO;
import com.dillip.api.dto.RandomQuoteDTO;
import com.dillip.api.response.BankDetailsResponse;
import com.dillip.api.response.PostOfficeDetailsResponse;
import com.google.gson.Gson;

@Service
public class CommonServiceImpl implements CommonService {
	
	private final static Logger LOGGER = Logger.getLogger("Dillip Logger");
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Gson gson;
	
	@Value("${randomquote.url}")
	private String randomQuoteUrl;
	@Value("${pincode.url}")
	private String pinCodeUrl;
	@Value("${bankdetails.url}")
	private String bankDetailsUrl;
	

	@Override
	public RandomQuoteDTO getRandomQuote() {
		RandomQuoteDTO response = null;
		String apiUrl = randomQuoteUrl;
		String fetchDataFromOtherApi = fetchDataFromOtherApi(apiUrl);
		try {
			LOGGER.log(Level.INFO, "############# Hitting getRandomQuote() ServiceImpl Layer :: apiUrl :: "+apiUrl);
			if(fetchDataFromOtherApi != null)
			{
				response = gson.fromJson(fetchDataFromOtherApi, RandomQuoteDTO.class);
			}
			else
			{
				response = new RandomQuoteDTO();
				response.setAuthor("Dillip K Sahoo");
				response.setContent("The greatest battle are fought with the closest people.");
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "############# Exception Occured in getRandomQuote() in ServiceImpl Layer ##########"+e);
		}
		return response;
	}
	
	@Override
	public List<PostOfficeDetailsResponse> getPostOfficeDetailsByPinCode(String pinCode) {
		String apiUrl = pinCodeUrl+"pincode/"+pinCode;
		LOGGER.log(Level.INFO, "############# Hitting getPostOfficeDetailsByPinCode() ServiceImpl Layer :: apiUrl :: "+apiUrl);
		List<PostOfficeDetailsResponse> reList = getPostOfficeDetailsByPinCodeOrBranchName(apiUrl);
		return reList;
	}
	
	@Override
	public List<PostOfficeDetailsResponse> getPostOfficeDetailsByBranchName(String branchName) {
		String apiUrl = pinCodeUrl+"postoffice/"+branchName;
		LOGGER.log(Level.INFO, "############# Hitting getPostOfficeDetailsByBranchName() ServiceImpl Layer :: apiUrl :: "+apiUrl);
		List<PostOfficeDetailsResponse> reList = getPostOfficeDetailsByPinCodeOrBranchName(apiUrl);
		return reList;
	}
	
	@Override
	public BankDetailsResponse getBankDetailsByIfsc(String ifscCode) {
		BankDetailsResponse response = null;
		String apiUrl = bankDetailsUrl+ifscCode;
		LOGGER.log(Level.INFO, "############# Hitting getBankDetailsByIfsc() ServiceImpl Layer :: apiUrl :: "+apiUrl);
		String fetchDataFromOtherApi = fetchDataFromOtherApi(apiUrl);
		try {
			if(fetchDataFromOtherApi != null)
			{
				BankDetailsDTO fetchBankDetailsByIfsc = gson.fromJson(fetchDataFromOtherApi, BankDetailsDTO.class);
				if(fetchBankDetailsByIfsc != null)
				{
					response = new BankDetailsResponse();
					response.setMicrCode(fetchBankDetailsByIfsc.getMICR());
					response.setBranchName(fetchBankDetailsByIfsc.getBRANCH());
					response.setAddress(fetchBankDetailsByIfsc.getADDRESS());
					response.setState(fetchBankDetailsByIfsc.getSTATE());
					response.setContact(fetchBankDetailsByIfsc.getCONTACT());
					response.setCityName(fetchBankDetailsByIfsc.getCITY());
					response.setCenter(fetchBankDetailsByIfsc.getCENTRE());
					response.setDistrict(fetchBankDetailsByIfsc.getDISTRICT());
					response.setSwift(fetchBankDetailsByIfsc.getSWIFT());
					response.setBankName(fetchBankDetailsByIfsc.getBANK());
					response.setBankCode(fetchBankDetailsByIfsc.getBANKCODE());
					response.setIfscCode(fetchBankDetailsByIfsc.getIFSC());
					LOGGER.log(Level.INFO, "############# Executed Logic in getBankDetailsByIfsc() in ServiceImpl Layer ##########");
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "############# Exception Occured in getBankDetailsByIfsc() ServiceImpl Layer ##########"+e);
		}
		return response;
	}
	
	public List<PostOfficeDetailsResponse> getPostOfficeDetailsByPinCodeOrBranchName(String apiUrl) {
		PinCodeDetailsDTO[] response = null;
		List<PinCodeDTO> reResponse = null;
		List<PostOfficeDetailsResponse> reList = new ArrayList<>();
		String fetchDataFromOtherApi = fetchDataFromOtherApi(apiUrl);
		try {
			LOGGER.log(Level.INFO, "############# Hitting getPostOfficeDetailsByPinCodeOrBranchName() ServiceImpl Layer :: apiUrl ########## "+apiUrl);
			if(fetchDataFromOtherApi != null)
			{
				response = gson.fromJson(fetchDataFromOtherApi, PinCodeDetailsDTO[].class);
				List<PinCodeDetailsDTO> asList = Arrays.asList(response);
				for(PinCodeDetailsDTO tempObj : asList)
				{
					reResponse = tempObj.getPostOffice();
				}
			}
			if(reResponse != null)
			{
				for(PinCodeDTO tempPinCodeDto : reResponse)
				{
					PostOfficeDetailsResponse tempPostOfficeDetailsResponse = new PostOfficeDetailsResponse();
					tempPostOfficeDetailsResponse.setPostOfficeName(tempPinCodeDto.getName());
					tempPostOfficeDetailsResponse.setDescription(tempPinCodeDto.getDescription());
					tempPostOfficeDetailsResponse.setBranchType(tempPinCodeDto.getBranchType());
					tempPostOfficeDetailsResponse.setDeliveryStatus(tempPinCodeDto.getDeliveryStatus());
					tempPostOfficeDetailsResponse.setCircleName(tempPinCodeDto.getCircle());
					tempPostOfficeDetailsResponse.setDistrictName(tempPinCodeDto.getDistrict());
					tempPostOfficeDetailsResponse.setDivisionName(tempPinCodeDto.getDivision());
					tempPostOfficeDetailsResponse.setRegionName(tempPinCodeDto.getRegion());
					tempPostOfficeDetailsResponse.setBlockName(tempPinCodeDto.getBlock());
					tempPostOfficeDetailsResponse.setState(tempPinCodeDto.getState());
					tempPostOfficeDetailsResponse.setCountry(tempPinCodeDto.getCountry());
					tempPostOfficeDetailsResponse.setPincode(tempPinCodeDto.getPincode());
					reList.add(tempPostOfficeDetailsResponse);
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "############# Exception Occured in getPostOfficeDetailsByPinCodeOrBranchName() ServiceImpl Layer ##########"+e);
		}
		return reList;
	}
	
	
	public String fetchDataFromOtherApi(String apiUrl) {
		String body = null;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		LOGGER.log(Level.INFO,"###### APIURL :: "+apiUrl);
		try {
			ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
					String.class);
			body = response.getBody();
			LOGGER.log(Level.INFO,
					"############# Hitting fetchDataFromOtherApi() ServiceImpl Layer ##########");
		} catch (Exception e) {
			LOGGER.log(Level.INFO,
					"############# Exception Occured in fetchDataFromOtherApi() ServiceImpl Layer ##########", e);
		}
		return body;
	}

	@Override
	public String getHelloMessage(String name) {
		return "Hello "+name+" . ! Welcome to DILLIPFOLIO";
	}
}

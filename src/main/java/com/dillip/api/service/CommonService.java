package com.dillip.api.service;

import java.util.List;

import com.dillip.api.dto.RandomQuoteDTO;
import com.dillip.api.response.BankDetailsResponse;
import com.dillip.api.response.PostOfficeDetailsResponse;

public interface CommonService {
	RandomQuoteDTO getRandomQuote();
	List<PostOfficeDetailsResponse> getPostOfficeDetailsByPinCode(String pinCode);
	List<PostOfficeDetailsResponse> getPostOfficeDetailsByBranchName(String branchName);
	BankDetailsResponse getBankDetailsByIfsc(String ifscCode);
}

package com.dillip.api.dto;

import java.util.List;

public class PinCodeDetailsDTO {
	private String Message;
	private String Status;
	private List<PinCodeDTO> PostOffice;

	public PinCodeDetailsDTO() {
		super();
	}

	public PinCodeDetailsDTO(String message, String status, List<PinCodeDTO> postOffice) {
		super();
		Message = message;
		Status = status;
		PostOffice = postOffice;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public List<PinCodeDTO> getPostOffice() {
		return PostOffice;
	}

	public void setPostOffice(List<PinCodeDTO> postOffice) {
		PostOffice = postOffice;
	}

	@Override
	public String toString() {
		return "PinCodeDetailsDTO [Message=" + Message + ", Status=" + Status + ", PostOffice=" + PostOffice + "]";
	}
}

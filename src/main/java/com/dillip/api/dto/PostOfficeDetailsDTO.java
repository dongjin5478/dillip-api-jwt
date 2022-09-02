package com.dillip.api.dto;

import java.util.List;

public class PostOfficeDetailsDTO {
	private List<PinCodeDetailsDTO> data;

	public PostOfficeDetailsDTO() {
		super();
	}

	public PostOfficeDetailsDTO(List<PinCodeDetailsDTO> data) {
		super();
		this.data = data;
	}

	public List<PinCodeDetailsDTO> getData() {
		return data;
	}

	public void setData(List<PinCodeDetailsDTO> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PostOfficeDetailsDTO [data=" + data + "]";
	}
}

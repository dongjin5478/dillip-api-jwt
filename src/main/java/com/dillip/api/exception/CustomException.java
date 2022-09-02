package com.dillip.api.exception;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private LocalDateTime timestamp;
	private String errorCode;
	private String errorMessage;
	private String description;
	public CustomException() {
		super();
	}
	public CustomException(String errorCode, String errorMessage, String description) {
		super();
		this.timestamp = LocalDateTime.now();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.description = description;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "StudentException [timestamp=" + timestamp + ", errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + ", description=" + description + "]";
	}
}

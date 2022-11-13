package com.ProfileManagement.exception;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private	Map<String,List<String>> errorMessages;
	private String details;
	

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	public ErrorDetails(Date timestamp, Map<String,List<String>> errorMessages, String details) {
		super();
		this.timestamp = timestamp;
		this.errorMessages = errorMessages;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public Map<String, List<String>> getErrorMessages() {
		return this.errorMessages;
	}
	
	
}
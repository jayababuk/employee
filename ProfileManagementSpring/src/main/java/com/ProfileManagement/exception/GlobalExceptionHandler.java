package com.ProfileManagement.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(ExperienceExceed.class)
	public ResponseEntity<?> experienceExceedException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(DesignationInvalidException.class)
	public ResponseEntity<?> designationInvalidException(DesignationInvalidException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(PhonenumberException.class)
	public ResponseEntity<?> phonenumberException(PhonenumberException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
		ErrorDetails errorDetails =null;
		if(ex.getBindingResult().getFieldErrors().size() > 1) {
			Map<String,List<String>> errorMap = new HashMap<>();
			ex.getBindingResult().getFieldErrors().forEach(error -> {
				List<String> errorsList = null;
				if(errorMap.containsKey(error.getField())) {
					errorsList = errorMap.get(error.getField());
					errorsList.add(error.getDefaultMessage());
				}else {
					errorsList = new ArrayList<>();
					errorsList.add(error.getDefaultMessage());
				}
				errorMap.put(error.getField(),errorsList);
			});
			errorDetails = new ErrorDetails(new Date(), errorMap, request.getDescription(false));
		} else {
			errorDetails = new ErrorDetails(new Date(), ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage(), request.getDescription(false));
		}
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
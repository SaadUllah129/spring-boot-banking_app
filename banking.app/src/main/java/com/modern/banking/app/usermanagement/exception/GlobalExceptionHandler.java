package com.modern.banking.app.usermanagement.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = CustomException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ResponseEntity<ErrorResponse> handleexcErrorResponse(CustomException ce) {
		ErrorResponse errorResponse = new ErrorResponse(ce.getStatus().value(), ce.getMessage());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ce.getStatus().value()));
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidation(
	        MethodArgumentNotValidException ex
	) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors()
	            .forEach(error ->
	                    errors.put(
	                            error.getField(),
	                            error.getDefaultMessage()
	                    ));

	    return ResponseEntity.badRequest().body(errors);
	}
}

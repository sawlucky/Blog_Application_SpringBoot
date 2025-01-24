package com.blogapplication.blogapplicationapi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogapplication.blogapplicationapi.Utils.ApiResponse;
import java.util.*;

@RestControllerAdvice
public class AllExceptionHandler {
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> HandleIdNotFoundException(IdNotFoundException e) {
		ApiResponse<String> api = new ApiResponse<String>(HttpStatus.NOT_FOUND.value(), "NO Data Found", e.getMsg());
		return new ResponseEntity<ApiResponse<String>>(api, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> HandleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		Map<String, String> mp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((errors) -> {
			String fieldName = ((FieldError) errors).getField();
			String message = errors.getDefaultMessage();
			mp.put(fieldName, message);

		});
		ApiResponse<Map<String, String>> api = new ApiResponse<Map<String, String>>(HttpStatus.BAD_REQUEST.value(),
				"Error in above fields", mp);
		return new ResponseEntity<ApiResponse<Map<String, String>>>(api, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse<String>> HandleIllegalArgumentException(IllegalArgumentException i) {
		ApiResponse<String> api = new ApiResponse<String>(HttpStatus.CONFLICT.value(), "Email Alreday Exist",i.getMessage());
		return new ResponseEntity<ApiResponse<String>>(api, HttpStatus.CONFLICT);
	}

}
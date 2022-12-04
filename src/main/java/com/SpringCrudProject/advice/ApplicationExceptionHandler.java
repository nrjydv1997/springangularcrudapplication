package com.SpringCrudProject.advice;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.SpringCrudProject.service.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleIncvalidArgument(MethodArgumentNotValidException ex){
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String,String>> handleConstraintViolationException(ConstraintViolationException ex){
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String,String>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", "Please provide valid request");
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}
	
	
}

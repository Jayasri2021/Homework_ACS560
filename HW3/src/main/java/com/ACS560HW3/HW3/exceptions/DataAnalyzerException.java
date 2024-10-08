package com.ACS560HW3.HW3.exceptions;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

import org.springframework.core.Ordered;

/**
 * The data analyzer common exception handler
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataAnalyzerException {

	/**
	 * Handler for NoSuchElementException
	 * @return Response entity
	 */
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handle(NoSuchElementException ex){
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handle(IllegalArgumentException ex){
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(DataRepoManagementException.class)
	public ResponseEntity<String> handle(DataRepoManagementException ex){
		return ResponseEntity.internalServerError().body(ex.getMessage());
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<String> handle(NoResourceFoundException ex){
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Endpoint does not exist");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle(Exception ex){
		ex.printStackTrace();
		return ResponseEntity.internalServerError().body("We're sorry...but we failed :(");
	}
}

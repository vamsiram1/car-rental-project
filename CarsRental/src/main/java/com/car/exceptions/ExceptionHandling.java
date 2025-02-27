package com.car.exceptions;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {
	
	
	@ExceptionHandler(NoSuchElementException.class)
	public String NoSuchElementException(Exception  ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String runTimeException(Exception  ex) {
		return ex.getMessage();
	}
	
}

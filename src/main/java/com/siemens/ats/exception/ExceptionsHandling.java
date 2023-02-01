package com.siemens.ats.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionsHandling {
	//handle specific exception
	@ExceptionHandler(ResourceNotFoundException.class) 
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		
	}  
	
	@ExceptionHandler(SqlException.class)
	public ResponseEntity<?> handleSqlException(SqlException exception,WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>customValidationErrorHandling(MethodArgumentNotValidException exception){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Vallidation Error ", 
				exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
		// to handle global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), 
						exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	
}

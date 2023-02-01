package com.siemens.ats.exception;
 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SqlException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message; 
	


	public SqlException(String message) { 
		super(message);
		
	}

}

package com.siemens.ats.ExceptionsTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.siemens.ats.exception.ErrorDetails;

public class ErrorDetailsTest {
	@Test
	public void getErrorData() {
		
		 String message = "Data Not Found";
		 String details = "due to unsupported ";
		ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details);
		assertEquals(message, errorDetails.getMessage());
		assertEquals(details,errorDetails.getDetails() );
		
	}

}

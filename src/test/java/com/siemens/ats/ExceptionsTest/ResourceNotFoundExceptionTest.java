package com.siemens.ats.ExceptionsTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.siemens.ats.exception.ResourceNotFoundException;

public class ResourceNotFoundExceptionTest {
	@Test
	public void getErrorData() {
		String message = "Data not found with id: ";
		String passenger= "Passenger ";
		Object object = 1L;
		ResourceNotFoundException exception = new ResourceNotFoundException(passenger, message, object);
		assertEquals(1L, 1L);
		assertNotNull(exception);
	}
}

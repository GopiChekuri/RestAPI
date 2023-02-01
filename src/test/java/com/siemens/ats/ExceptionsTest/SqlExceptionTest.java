package com.siemens.ats.ExceptionsTest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.siemens.ats.exception.SqlException;

public class SqlExceptionTest {
	@Test
	public void getErrorData() {
		String message = "Entered email was already exists";
		SqlException sqlException = new SqlException(message);
		assertEquals(message,sqlException.getMessage() );
		
	}

}

package com.codemanship.becauseunit.exceptions;

import java.lang.reflect.Method;

public class InvalidTestMethodException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidTestMethodException(Method invalidMethod){
		super("\n" + invalidMethod.getDeclaringClass().getName() + "::" + invalidMethod.getName() + " is not a valid BecauseUnit test" +
				"\nIt must be public void with no parameters");
	}

}

package com.codemanship.becauseunit.exceptions;

import java.lang.reflect.Method;

public class InvalidParameterisedTestMethodException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidParameterisedTestMethodException(Method invalidMethod) {
		super("\n" + invalidMethod.getDeclaringClass().getName() + "::" + invalidMethod.getName() + " is not a valid BecauseUnit test" +
				"\nIt must be public void, have at least one parameter" +
				" and be in a class that contains a method called 'datafor_' " + ""
						+ "followed by the test method name");
	}

}

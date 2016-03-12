package com.codemanship.becauseunit.exceptions;

public class InvalidTestClassException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTestClassException(Class<?> testClass) {
		super(testClass.getName() + " is not a valid test class. Test classes with names that start with 'Because' must be public and have no constructor");
	}

}

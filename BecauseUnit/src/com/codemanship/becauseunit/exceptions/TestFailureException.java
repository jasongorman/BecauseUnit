package com.codemanship.becauseunit.exceptions;

public class TestFailureException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TestFailureException(String message){
		super(message);
	}
	
	public TestFailureException(){}

}

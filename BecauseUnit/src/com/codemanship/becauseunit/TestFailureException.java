package com.codemanship.becauseunit;

public class TestFailureException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TestFailureException(String message){
		super(message);
	}
	
	public TestFailureException(){}

}

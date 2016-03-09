package com.codemanship.becauseunit;

public class Checker {

	public static void check(boolean condition) throws TestFailureException {
		if(!condition) throw new TestFailureException();
	}
	
	public static void check(boolean condition, String message) throws TestFailureException {
		if(!condition) throw new TestFailureException(message);
	}

}

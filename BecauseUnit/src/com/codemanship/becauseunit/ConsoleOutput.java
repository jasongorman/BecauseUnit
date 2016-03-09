package com.codemanship.becauseunit;

import java.util.List;


public class ConsoleOutput implements Output {

	private final Writer writer;

	public ConsoleOutput(Writer consolewriter) {
		writer = consolewriter;
	}

	@Override
	public void writeTestResult(Test test) {
		String message = "";
		if(!test.hasFailed()) {
			message += "passed\t" + test.getName();
		} else {
			message += "failed\t" + test.getName();
			message += "\n" + printStackTrace(test);
		}
		writer.write(message);
	}
	
	public void writeSummary(List<Test> tests){
		int testsRun = tests.size();
		int testsFailed = 0;
		for (Test test : tests) {
			if(test.hasFailed()){
				testsFailed++;
			}
		}
		int testsPassed = testsRun - testsFailed;
		writer.write(String.format("TESTS RUN: %d    PASSED: %d     FAILED: %d", testsRun, testsPassed, testsFailed));
	}
	
	@Override
	public void writeHeader() {
		writer.write("BecauseUnit (TM) - Yet another unit testing framework, created by Jason Gorman for Codemanship, Copyright 2016"
				+ "\n" + "Running tests...");
	}

	private String printStackTrace(Test test) {
		String stackTrace = "";	
		
		Throwable cause = test.getException().getCause();
		
		if(cause.getMessage() != null)
			stackTrace = "----\t" + cause.getMessage();

		if(cause.getClass() != TestFailureException.class){
			stackTrace += "\n-----" + cause.getClass().getName();		
			
			
			for (StackTraceElement trace : test.getException().getStackTrace()) {
				stackTrace += "\n\t" + trace.toString();
			}
		}
		return stackTrace;
	}

}
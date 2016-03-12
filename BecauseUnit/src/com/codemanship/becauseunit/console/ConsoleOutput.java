package com.codemanship.becauseunit.console;

import java.util.List;

import com.codemanship.becauseunit.Output;
import com.codemanship.becauseunit.Test;
import com.codemanship.becauseunit.Writer;
import com.codemanship.becauseunit.exceptions.TestFailureException;


public class ConsoleOutput implements Output {

	private final Writer writer;

	public ConsoleOutput(Writer consolewriter) {
		writer = consolewriter;
	}

	@Override
	public void writeTestResult(Test test) {
		String message = "";
		if(!test.hasFailed()) {
			message += "passed\t" + test.getName() + "	" + formatData(test);
		} else {
			message += "failed\t" + test.getName() + "	" + formatData(test);
			message += "\n" + printStackTrace(test);
		}
		writer.write(message);
	}
	
	private String formatData(Test test) {
		String formattedData = "";
		Object[] data = test.getTestCaseData();
		if(data.length > 0){
			formattedData = "DATA: {" + data[0];
			for (int i = 1; i < data.length; i++) {
				formattedData += ", " + data[i];
			}
			formattedData += "} ";
		}
		return formattedData;
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
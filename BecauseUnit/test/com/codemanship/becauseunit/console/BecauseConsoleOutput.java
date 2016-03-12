package com.codemanship.becauseunit.console;

import java.util.ArrayList;
import java.util.List;

import com.codemanship.becauseunit.Output;
import com.codemanship.becauseunit.SimpleTest;
import com.codemanship.becauseunit.Test;
import com.codemanship.becauseunit.console.ConsoleOutput;
import com.codemanship.becauseunit.doubles.SpyWriter;
import com.codemanship.becauseunit.mothers.TestMother;

import static com.codemanship.becauseunit.Checker.*;

public class BecauseConsoleOutput {

	private SpyWriter writer;

	public void becauseWritesTestPassedResult() throws NoSuchMethodException, SecurityException{
		runTest("passingTest");
		check(writer.getLastWrite().startsWith("passed"));
	}
	
	public void becauseWritesTestFailedResult() throws NoSuchMethodException, SecurityException{
		runTest("failingTest");
		check(writer.getLastWrite().startsWith("failed"));
	}
	
	public void becauseResultIncludesTestName() throws NoSuchMethodException{
		runTest("passingTest");
		check(writer.getLastWrite().contains("com.codemanship.becauseunit.console.BecauseConsoleOutput::passingTest"));
	}
	
	public void becauseSummaryCountsPasses() throws NoSuchMethodException{
		SpyWriter writer = writeTestResults();
		check(writer.getLastWrite().contains("PASSED: 1"));
	}
	
	public void becauseSummaryCountsFails() throws NoSuchMethodException{
		SpyWriter writer = writeTestResults();
		check(writer.getLastWrite().contains("FAILED: 1"));
	}
	
	public void becauseSummaryCountsTestsRun() throws NoSuchMethodException{
		SpyWriter writer = writeTestResults();
		check(writer.getLastWrite().contains("TESTS RUN: 2"));
	}
	
	public void becauseHeaderShamelesslyPlugsFramework(){
		SpyWriter writer = new SpyWriter();
		Output output = new ConsoleOutput(writer);
		output.writeHeader();
		check(writer.getLastWrite().startsWith("BecauseUnit"));
	}

	private SpyWriter writeTestResults() throws NoSuchMethodException {
		List<Test> tests = new ArrayList<Test>();
		tests.add(runTest("passingTest"));
		tests.add(runTest("failingTest"));
		SpyWriter writer = new SpyWriter();
		Output output = new ConsoleOutput(writer);
		output.writeSummary(tests);
		return writer;
	}

	private SimpleTest runTest(String testMethodName) throws NoSuchMethodException {
		writer = new SpyWriter();
		Output output = new ConsoleOutput(writer);
		SimpleTest test = new TestMother().createTest(testMethodName, this.getClass(), this);
		test.run(output);
		return test;
	}
	
	public void passingTest(){}
	
	public void failingTest(){
		check(false);
	}
}

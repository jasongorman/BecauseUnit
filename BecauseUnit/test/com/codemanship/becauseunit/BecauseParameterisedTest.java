package com.codemanship.becauseunit;

import static com.codemanship.becauseunit.Checker.*;

import com.codemanship.becauseunit.console.ConsoleOutput;
import com.codemanship.becauseunit.doubles.SpyWriter;

public class BecauseParameterisedTest {
	
	private SpyWriter writer;

	public void becauseParameterTypesAreListed() throws NoSuchMethodException, SecurityException{
		ParameterisedTest test = createParameterisedTest("parameterised");
		check(test.getParameterTypes()[0].equals(String.class));
	}
	
	public void becauseFindsDataForParameterisedTest() throws NoSuchMethodException, SecurityException{
		ParameterisedTest test = createParameterisedTest("parameterised");
		check(test.getData().length == datafor_parameterised().length);
	}

	
	public void becauseRunningParameterisedTestInvokesTestMethodForEachSetOfData() throws NoSuchMethodException, SecurityException{
		ParameterisedTest test = createParameterisedTest("parameterised");
		runTest(test);
		check(writer.getLastWrite().contains("passed"));	
	}
	
	public void becauseFailsTestIfDataDoesntMatchParameters() throws NoSuchMethodException{
		Test test = createParameterisedTest("parameterisedUnmatched");
		runTest(test);
		check(writer.getLastWrite().contains("failed"));
	}

	private void runTest(Test test) {
		writer = new SpyWriter();
		Output output = new ConsoleOutput(writer );
		test.run(output);
	}
	
	public void parameterised(String param, boolean foo){
		check(param.equals("hello") && foo);
	}
	
	public Object[][] datafor_parameterised(){
		return new Object[][]{{"hello", true}};
	}
	
	public void parameterisedUnmatched(String param, boolean foo){
		check(param.equals("hello") && foo);
	}
	
	public Object[][] datafor_parameterisedUnmatched(){
		return new Object[][]{{"hello"}};
	}

	private ParameterisedTest createParameterisedTest(String testMethodName)
			throws NoSuchMethodException {
		ParameterisedTest test = new ParameterisedTest(this, this.getClass().getDeclaredMethod(testMethodName, String.class, boolean.class));
		return test;
	}
}

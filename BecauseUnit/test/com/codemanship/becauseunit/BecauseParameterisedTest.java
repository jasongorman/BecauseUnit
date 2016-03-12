package com.codemanship.becauseunit;

import static com.codemanship.becauseunit.Checker.*;

import com.codemanship.becauseunit.console.ConsoleOutput;
import com.codemanship.becauseunit.doubles.SpyWriter;

public class BecauseParameterisedTest {
	
	public void becauseParameterTypesAreListed() throws NoSuchMethodException, SecurityException{
		ParameterisedTest test = createParameterisedTest();
		check(test.getParameterTypes()[0].equals(String.class));
	}
	
	public void becauseFindsDataForParameterisedTest() throws NoSuchMethodException, SecurityException{
		ParameterisedTest test = createParameterisedTest();
		check(test.getData().length == datafor_parameterised().length);
	}

	
	public void becauseRunningParameterisedTestInvokesTestMethodForEachSetOfData() throws NoSuchMethodException, SecurityException{
		ParameterisedTest test = createParameterisedTest();
		SpyWriter writer = new SpyWriter();
		Output output = new ConsoleOutput(writer );
		test.run(output);
		check(writer.getLastWrite().contains("passed"));	
	}
	
	public void parameterised(String param, boolean foo){
		check(param.equals("hello") && foo);
	}
	
	public Object[][] datafor_parameterised(){
		return new Object[][]{{"hello", true}};
	}

	private ParameterisedTest createParameterisedTest()
			throws NoSuchMethodException {
		ParameterisedTest test = new ParameterisedTest(this, this.getClass().getDeclaredMethod("parameterised", String.class, boolean.class));
		return test;
	}
}

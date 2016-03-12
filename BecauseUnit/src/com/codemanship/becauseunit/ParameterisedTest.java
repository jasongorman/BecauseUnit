package com.codemanship.becauseunit;

import java.lang.reflect.Method;

import com.codemanship.becauseunit.exceptions.InvalidParameterisedTestMethodException;

public class ParameterisedTest extends SimpleTest {

	private Object[] testCaseData;

	public ParameterisedTest(Object testFixture, Method testMethod) {
		super(testFixture, testMethod);
	}

	public Class<?>[] getParameterTypes() {
		return getTestMethod().getParameterTypes();
	}

	public Object[][] getData() {
		Class<?> testClass = getTestFixture().getClass();
		try {
			Method dataMethod = testClass.getMethod("datafor_"+ getTestMethod().getName());
			return (Object[][])dataMethod.invoke(getTestFixture());
		} catch (Exception e) {
			throw new InvalidParameterisedTestMethodException(getTestMethod());
		} 
	}
	
	public void run(Output output) {
		Object[][] data = getData();
		for (int i = 0; i < data.length; i++) {
			failed = false;
			testCaseData = data[i];
			try {
				getTestMethod().invoke(getTestFixture(), data[i]);
			} catch (Exception e) {
				failed = true;
				exception = e;
			} finally {
				writeResult(output);
			}
			failed = exception != null;
		}	
	}
	
	public Object[] getTestCaseData(){
		return testCaseData;
	}


}

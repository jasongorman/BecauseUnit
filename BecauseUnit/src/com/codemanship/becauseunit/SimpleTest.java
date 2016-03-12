package com.codemanship.becauseunit;

import java.lang.reflect.Method;

public class SimpleTest implements Test {

	private Method testMethod;
	private Object testFixture;
	protected boolean failed = false;
	protected Exception exception;

	public SimpleTest(Object testFixture, Method testMethod) {
		this.testFixture = testFixture;
		this.testMethod = testMethod;
	}

	public String getName() {
		return testFixture.getClass().getName() + "::" + testMethod.getName();
	}

	public void run(Output output) {

			try {
				testMethod.invoke(testFixture);
			} catch (Exception e) {
				failed = true;
				exception = e;
			} finally {
				writeResult(output);
			}
	}

	protected void writeResult(Output output) {
		output.writeTestResult(this);
	}


	public boolean hasFailed() {
		return failed;
	}

	public Exception getException() {
		return exception;
	}

	protected Method getTestMethod() {
		return testMethod;
	}

	protected Object getTestFixture() {
		return testFixture;
	}

	@Override
	public Object[] getTestCaseData() {
		return new Object[]{};
	}

}

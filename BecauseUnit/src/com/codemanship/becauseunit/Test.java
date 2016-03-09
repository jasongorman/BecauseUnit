package com.codemanship.becauseunit;

import java.lang.reflect.Method;

public class Test {

	private Method testMethod;
	private Object testFixture;
	private boolean failed = false;
	private Exception exception;

	public Test(Object testFixture, Method testMethod) {
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

	private void writeResult(Output output) {
		output.writeTestResult(this);
	}


	public boolean hasFailed() {
		return failed;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}

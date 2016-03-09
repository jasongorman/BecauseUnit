package com.codemanship.becauseunit.mothers;

import java.lang.reflect.Method;

import com.codemanship.becauseunit.Test;

public class TestMother {

	public Test createTest(String testMethodName, Class<?> testClass, Object fixture)
			throws NoSuchMethodException {
				Method testMethod = testClass.getMethod(testMethodName);
				return new Test(fixture, testMethod);
	}

}
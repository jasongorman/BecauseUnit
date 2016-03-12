package com.codemanship.becauseunit.mothers;

import java.lang.reflect.Method;

import com.codemanship.becauseunit.SimpleTest;

public class TestMother {

	public SimpleTest createTest(String testMethodName, Class<?> testClass, Object fixture)
			throws NoSuchMethodException {
				Method testMethod = testClass.getMethod(testMethodName);
				return new SimpleTest(fixture, testMethod);
	}

}
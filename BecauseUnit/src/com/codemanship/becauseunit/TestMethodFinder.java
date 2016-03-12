package com.codemanship.becauseunit;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class TestMethodFinder {

	private ArrayList<Test> tests = new ArrayList<Test>();
	private Class<?> testClass;
	private Object testFixture;
	private TestMethodValidator validator;

	public TestMethodFinder(Class<?> testClass, TestMethodValidator validator) {
		this.testClass = testClass;
		try {
			testFixture = testClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		this.validator = validator;
	}

	public List<Test> findAll() {
		for (Method method : testClass.getDeclaredMethods()) {
			add(method);
		}
		return tests;
	}

	private void add(Method method) {
		if(method.getName().startsWith("because")){
			validator.validate(method);						
			tests.add(createTest(method));
		}
	}

	private Test createTest(Method method) {
		if(validator.isParameterised(method)){
			return new ParameterisedTest(testFixture, method);
		} else {
			return new SimpleTest(testFixture, method);
		}
	}

}

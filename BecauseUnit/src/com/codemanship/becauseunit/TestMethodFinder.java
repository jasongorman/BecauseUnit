package com.codemanship.becauseunit;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;


public class TestMethodFinder {

	private ArrayList<Test> tests = new ArrayList<Test>();
	private Class<?> testClass;
	private Object testFixture;

	public TestMethodFinder(Class<?> testClass) {
		this.testClass = testClass;
		try {
			testFixture = testClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public List<Test> findAll() {
		for (Method method : testClass.getDeclaredMethods()) {
			add(method);
		}
		return tests;
	}

	private void add(Method method) {
		if(method.getName().startsWith("because")){
			if(hasParameters(method) || !isVoid(method) || !isPublic(method)) {
				throw new InvalidTestMethodException(method);
			}
			tests.add(new Test(testFixture, method));
		}
	}

	private boolean isPublic(Method method) {
		return Modifier.isPublic(method.getModifiers());
	}

	private boolean isVoid(Method method) {
		return method.getReturnType() == void.class;
	}

	private boolean hasParameters(Method method) {
		return method.getParameterTypes().length > 0;
	}

}

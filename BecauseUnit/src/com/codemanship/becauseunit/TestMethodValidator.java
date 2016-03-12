package com.codemanship.becauseunit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.codemanship.becauseunit.exceptions.InvalidParameterisedTestMethodException;
import com.codemanship.becauseunit.exceptions.InvalidTestMethodException;

public class TestMethodValidator {

	public TestMethodValidator() {
		super();
	}

	protected void validate(Method method) {
		checkVisibility(method);
		checkReturnType(method);
		checkParameters(method);
	}

	private void checkParameters(Method method) {
		if(!isParameterised(method)){
			checkSimpleTestHasNoParams(method);
		} else {
			checkParameterisedTestHasParams(method);
		}
	}

	private void checkParameterisedTestHasParams(Method method) {
		if(!hasParameters(method)){
			throw new InvalidParameterisedTestMethodException(method);
		}
	}

	private void checkSimpleTestHasNoParams(Method method) {
		if(hasParameters(method)){
			throw new InvalidTestMethodException(method);
		}
	}

	private void checkReturnType(Method method) {
		if(!isVoid(method)) {
			throw new InvalidTestMethodException(method);
		}
	}

	private void checkVisibility(Method method) {
		if(!isPublic(method)) {
			throw new InvalidTestMethodException(method);
		}
	}

	protected boolean isParameterised(Method method) {
		Annotation annotation = method.getAnnotation(Parameterised.class);
		return annotation != null;
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
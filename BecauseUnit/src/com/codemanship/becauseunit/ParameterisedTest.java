package com.codemanship.becauseunit;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.codemanship.becauseunit.exceptions.InvalidParameterisedTestMethodException;

public class ParameterisedTest extends SimpleTest {

	private Object[] testCaseData;
	private HashMap<Class<?>, Class<?>> primitives;

	public ParameterisedTest(Object testFixture, Method testMethod) {
		super(testFixture, testMethod);
		buildPrimitiveTypesMap();
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
				if(!matchesParameters(data[i])){
					throw new InvalidParameterisedTestMethodException(getTestMethod());
				}
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
	
	private boolean matchesParameters(Object[] data) {
		Class<?>[] parameterTypes = getParameterTypes();
		if(data.length != parameterTypes.length){
			return false;
		}
		for (int i = 0; i < parameterTypes.length; i++) {
			if(!(boxed(parameterTypes[i]).isAssignableFrom(data[i].getClass())))
				return false;
		}
		return true;
	}

	private Class<?> boxed(Class<?> unboxed) {
		Class<?> boxedClass = primitives.get(unboxed);
		if(boxedClass == null)
			return unboxed;
		return boxedClass;
	}

	private void buildPrimitiveTypesMap() {
		primitives = new HashMap<Class<?>, Class<?>>();
		primitives.put(int.class, Integer.class);
		primitives.put(long.class, Long.class);
		primitives.put(float.class, Float.class);
		primitives.put(double.class, Double.class);
		primitives.put(boolean.class, Boolean.class);
		primitives.put(char.class, Character.class);
		primitives.put(byte.class, Byte.class);
		primitives.put(short.class, Short.class);
	}

	public Object[] getTestCaseData(){
		return testCaseData;
	}


}

package com.codemanship.becauseunit;
import java.util.List;

import com.codemanship.becauseunit.examples.ClassWithNonVoidTestMethod;
import com.codemanship.becauseunit.examples.ClassWithPrivateTestMethod;
import com.codemanship.becauseunit.examples.EmptyTestClass;
import com.codemanship.becauseunit.examples.ClassWithTestMethodWithParameters;
import com.codemanship.becauseunit.examples.TestClassWithOneNonTestMethod;
import com.codemanship.becauseunit.examples.TestClassWithOneTest;

import static com.codemanship.becauseunit.Checker.check;

public class BecauseTestMethodFinder {
	
	public void becauseFindsNoTestsInEmptyTestClass(){
		List<Test> tests = findTests(EmptyTestClass.class);
		check(tests.size() == 0, "Too many tests found");
	}
	
	public void becauseFindsOneTestInClassWithOneTestMethod(){
		List<Test> tests = findTests(TestClassWithOneTest.class);
		check(tests.size() == 1);
	}
	
	public void becauseOnlyIncludesMethodsStartingWithTest(){
		List<Test> tests = findTests(TestClassWithOneNonTestMethod.class);
		check(tests.size() == 0);
	}
	
	public void becauseTestMethodsMustNotHaveParameters(){
		try {
			findTests(ClassWithTestMethodWithParameters.class);
			check(false);
		} catch(InvalidTestMethodException e) {
			
		}
	}
	
	public void becauseTestMethodsMustBeVoid(){
		try {
			findTests(ClassWithNonVoidTestMethod.class);
			check(false);
		} catch(InvalidTestMethodException e) {
			
		}
	}
	
	public void becauseTestMethodsMustBePublic(){
		try {
			findTests(ClassWithPrivateTestMethod.class);
			check(false);
		} catch(InvalidTestMethodException e) {
			
		}
	}

	private List<Test> findTests(Class<?> testClass) {
		return new TestMethodFinder(testClass).findAll();
	}

}

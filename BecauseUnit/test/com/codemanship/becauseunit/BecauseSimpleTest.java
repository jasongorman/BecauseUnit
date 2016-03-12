package com.codemanship.becauseunit;

import static com.codemanship.becauseunit.Checker.*;

import com.codemanship.becauseunit.doubles.DummyOutput;
import com.codemanship.becauseunit.exceptions.TestFailureException;
import com.codemanship.becauseunit.mothers.TestMother;

public class BecauseSimpleTest extends TestMother {
	
	private final TestMother testMother = new TestMother();
	private Test test;

	public void becauseInitialisedWithTestMethod() throws NoSuchMethodException, SecurityException{
		Test test = testMother.createTest("becauseInitialisedWithTestMethod", BecauseSimpleTest.class, this);
		check(test.getName().equals("com.codemanship.becauseunit.BecauseSimpleTest::becauseInitialisedWithTestMethod") );
	}
	
	public void becauseTestFailsWhenCheckThrowsTestFailureException() throws NoSuchMethodException, SecurityException{
		runTest("failingTest");
		check(test.hasFailed());
	}

	
	public void becauseTestFailsWhenCatchesUnhandledException() throws NoSuchMethodException{
		runTest("testThatThrowsUnhandledException");
		check(test.hasFailed());
	}
	
	public void becauseTestPassesIfNoExceptionsCaught() throws NoSuchMethodException{
		runTest("passingTest");
		check(!test.hasFailed());
	}
	
	public void becauseFailingTestCapturesException() throws NoSuchMethodException{
		runTest("failingTest");
		check(test.getException().getCause().getClass() == TestFailureException.class);
	}
	
	private Test runTest(String testMethodName) throws NoSuchMethodException {
		test = testMother.createTest(testMethodName, BecauseSimpleTest.class, this);
		test.run(new DummyOutput());
		return test;
	}
	
	public void passingTest(){
		check(true);
	}
	
	public void failingTest(){
		check(false);
	}
	
	public void testThatThrowsUnhandledException(){
		throw new IllegalArgumentException();
	}

}

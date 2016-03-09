package com.codemanship.becauseunit;

import java.util.ArrayList;
import java.util.List;

import com.codemanship.becauseunit.doubles.SpyWriter;
import com.codemanship.becauseunit.doubles.TestLoaderStub;
import com.codemanship.becauseunit.mothers.TestMother;
import static com.codemanship.becauseunit.Checker.*;

public class BecauseRunner {
	
	public void becauseRunsAllTests() throws NoSuchMethodException{
		SpyWriter writer = new SpyWriter();
		Output output = new ConsoleOutput(writer);
		TestMother testMother = new TestMother();
		List<Test> tests = new ArrayList<Test>();
		tests.add(testMother.createTest("test1", this.getClass(), this));
		TestLoader loader = new TestLoaderStub(tests);
		Runner runner = new Runner(loader, output);
		runner.runAll("blah");
		check(writer.getLastWrite().contains("TESTS RUN: 1"));
	}
	
	public void test1(){
		
	}

}

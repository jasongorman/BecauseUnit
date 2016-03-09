package com.codemanship.becauseunit;

import java.util.List;

public class Runner {

	private final TestLoader loader;
	private Output output;
	private List<Test> tests;

	public Runner(TestLoader loader, Output output) {
		this.loader = loader;
		this.output = output;
	}

	public void runAll(String testClassPath) {
		output.writeHeader();
		tests = loader.load(testClassPath);		
		invokeAll(tests);		
		output.writeSummary(tests);
	}

	private void invokeAll(List<Test> tests) {
		for (Test test : tests) {
			test.run(output);
		}
	}

}
package com.codemanship.becauseunit.doubles;

import java.util.List;

import com.codemanship.becauseunit.Test;
import com.codemanship.becauseunit.TestLoader;

public class TestLoaderStub implements TestLoader {

	private List<Test> tests;

	public TestLoaderStub(List<Test> tests) {
		this.tests = tests;
	}

	@Override
	public List<Test> load(String classPath) {
		return tests;
	}

}

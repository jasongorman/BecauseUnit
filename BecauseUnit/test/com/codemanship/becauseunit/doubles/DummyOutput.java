package com.codemanship.becauseunit.doubles;

import java.util.List;

import com.codemanship.becauseunit.Output;
import com.codemanship.becauseunit.SimpleTest;
import com.codemanship.becauseunit.Test;

public class DummyOutput implements Output {

	@Override
	public void writeTestResult(Test test) {}

	@Override
	public void writeSummary(List<Test> tests) {}

	@Override
	public void writeHeader() {}

	public String getLastWrite() {
		// TODO Auto-generated method stub
		return "";
	}

}

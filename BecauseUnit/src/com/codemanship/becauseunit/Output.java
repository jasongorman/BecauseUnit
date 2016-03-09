package com.codemanship.becauseunit;

import java.util.List;

public interface Output {
	
	public abstract void writeTestResult(Test test);
	
	public abstract void writeSummary(List<Test> tests);

	public abstract void writeHeader();

}
package com.codemanship.becauseunit;

public interface Test {

	public abstract String getName();

	public abstract void run(Output output);

	public abstract boolean hasFailed();

	public abstract Exception getException();
	
	public abstract Object[] getTestCaseData();

}
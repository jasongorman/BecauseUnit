package com.codemanship.becauseunit.examples;

import com.codemanship.becauseunit.Parameterised;

public class ClassWithInvalidParameterisedTest {
	
	@Parameterised()
	public void becauseParameterised(){}
	
	public Object[][] datafor_becauseParameterised(){
		return new Object[][]{{1}, {2}, {3}};
	}

}

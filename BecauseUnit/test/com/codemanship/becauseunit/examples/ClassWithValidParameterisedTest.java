package com.codemanship.becauseunit.examples;

import com.codemanship.becauseunit.Parameterised;

public class ClassWithValidParameterisedTest {
	
	@Parameterised
	public void becauseValidParameterisedTest(int param){
		
	}
	
	public Object[][] datafor_becauseValidParameterisedTest(){
		return new Object[][]{{1}};
	}

}

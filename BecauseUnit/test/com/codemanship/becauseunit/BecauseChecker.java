package com.codemanship.becauseunit;

import static com.codemanship.becauseunit.Checker.*;

public class BecauseChecker {
	
	public void becauseTestFailureMessageIsEmbeddedInException(){
		String message = "failed";
		try {
			check(false, message);
		} catch (TestFailureException e) {
			check(e.getMessage().equals(message));
		}
	}

}

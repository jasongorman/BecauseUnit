package com.codemanship.becauseunit;

import static com.codemanship.becauseunit.Checker.check;

import java.io.File;

public class BecauseURLClassReader {	
	
	public void becauseConvertsFilePathIntoPackageName(){
		File testFile = new File("bin\\com\\codemanship\\becauseunit\\BecauseTestClassLoader.class");
		check(new URLClassReader().getPackageName("bin", testFile).equals("com.codemanship.becauseunit"));
	}
	
	public void becauseClassesWithNoPackageHaveZeroLengthPackageName(){
		File testFile = new File("bin\\BecauseTestClassLoader.class");
		check(new URLClassReader().getPackageName("bin", testFile).equals(""));
	}

}

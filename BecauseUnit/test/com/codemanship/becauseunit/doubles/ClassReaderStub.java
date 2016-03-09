package com.codemanship.becauseunit.doubles;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.codemanship.becauseunit.ClassReader;

public class ClassReaderStub implements ClassReader {

	private Class<?> testClass;

	public ClassReaderStub(Class<?> testClass) {
		this.testClass = testClass;
	}

	@Override
	public Class<?> read(String classPath, File testFile)
			throws MalformedURLException, ClassNotFoundException, IOException {
		return testClass;
	}

}

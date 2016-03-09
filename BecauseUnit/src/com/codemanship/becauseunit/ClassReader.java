package com.codemanship.becauseunit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public interface ClassReader {

	public abstract Class<?> read(String classPath, File testFile)
			throws MalformedURLException, ClassNotFoundException, IOException;

}
package com.codemanship.becauseunit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class URLClassReader implements ClassReader {

	public Class<?> read(String classPath, File testFile)
			throws MalformedURLException, ClassNotFoundException, IOException {
				URLClassLoader urlLoader = new URLClassLoader(new URL[]{testFile.toURI().toURL()});
				String packageName = getPackageName(classPath, testFile);
				Class<?> testClass = urlLoader.loadClass(packageName + "." + testFile.getName().substring(0, testFile.getName().length() - 6));
				urlLoader.close();
				return testClass;
			}

	String getPackageName(String classPath, File testFile) {
		String packageName = testFile.getAbsolutePath();
		if(hasNoPackageName(classPath, testFile, packageName)){
			return "";
		}
		packageName = packageName.substring(packageName.indexOf(classPath) + classPath.length(), packageName.length() - 1);
		packageName = packageName.substring(1, packageName.length() - testFile.getName().length());
		packageName = packageName.replace('\\', '.');
		return packageName;
	}

	private boolean hasNoPackageName(String classPath, File testFile,
			String packageName) {
		return packageName.contains("\\" + classPath + "\\" + testFile.getName());
	}

}
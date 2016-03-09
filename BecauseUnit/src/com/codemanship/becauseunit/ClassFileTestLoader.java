package com.codemanship.becauseunit;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ClassFileTestLoader implements TestLoader {
	
	private final ClassReader reader;

	public ClassFileTestLoader(ClassReader reader) {
		this.reader = reader;
	}

	@Override
	public List<Test> load(String classPath) {
		return loadTests(classPath);
	}
	
	private List<Test> loadTests(String classPath) {
		List<Test> tests = new ArrayList<Test>();
		for (Class<?> testClass : findTestClasses(classPath)) {
			tests.addAll(new TestMethodFinder(testClass).findAll());
		}
		return tests;
	}
	
	List<Class<?>> findTestClasses(String classPath){		
		List<File> testFiles = getTestFiles(classPath);
		List<Class<?>> testClasses = loadTestClasses(classPath, testFiles);
		return testClasses;		
	}

	void addTestClass(String classPath, List<Class<?>> testClasses,	File testFile) 
						throws MalformedURLException, ClassNotFoundException, IOException {
		
		Class<?> testClass = reader.read(classPath, testFile);
		validate(testClass);		
		testClasses.add(testClass);
	}

	List<File> getTestFiles(String classPath) {
		
		File file = new File(classPath);
		List<File> classFiles = new FileUtils().listFilesInDirectory(null, file);
		List<File> testFiles = new ArrayList<File>();
		
		filter(classFiles, testFiles);
		
		return testFiles;
	}
	
	private List<Class<?>> loadTestClasses(String classPath, List<File> testFiles) {
		
		List<Class<?>> testClasses = new ArrayList<Class<?>>();		
		
		for (File testFile : testFiles) {
			try {
				addTestClass(classPath, testClasses, testFile);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} 
		}
		
		return testClasses;
	}
	
	private void validate(Class<?> testClass) {
		Constructor<?>[] constructors = testClass.getConstructors();
		
		for (Constructor<?> constructor : constructors) {
			if(constructor.getParameterTypes().length > 0){			
				throw new InvalidTestClassException(testClass);
			}
		}
	}

	private void filter(List<File> classFiles, List<File> testFiles) {
		for (File anyFile : classFiles) {
			if(anyFile.getName().startsWith("Because") && anyFile.getName().endsWith(".class"))
				testFiles.add(anyFile);
		}
	}



}

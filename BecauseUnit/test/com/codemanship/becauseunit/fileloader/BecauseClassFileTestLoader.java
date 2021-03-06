package com.codemanship.becauseunit.fileloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.codemanship.becauseunit.ClassReader;
import com.codemanship.becauseunit.TestMethodValidator;
import com.codemanship.becauseunit.doubles.ClassReaderStub;
import com.codemanship.becauseunit.examples.TestClassWithConstructor;
import com.codemanship.becauseunit.exceptions.InvalidTestClassException;
import com.codemanship.becauseunit.fileloader.ClassFileTestLoader;
import com.codemanship.becauseunit.fileloader.URLClassReader;

import static com.codemanship.becauseunit.Checker.*;

public class BecauseClassFileTestLoader {
	
	public void becauseOnlyIncludesTestFiles(){
		List<File> testFiles = new ClassFileTestLoader(new URLClassReader(), new TestMethodValidator()).getTestFiles("bin");
		for (File file : testFiles) {
			check(file.getName().startsWith("Because") && file.getName().endsWith(".class"));
		}
	}
	
	public void becauseTestClassesMustHaveNoConstructor() throws MalformedURLException, ClassNotFoundException, IOException{
		try {
			ClassReader reader = new ClassReaderStub(TestClassWithConstructor.class);
			new ClassFileTestLoader(reader, new TestMethodValidator()).addTestClass(null, new ArrayList<Class<?>>(), null);
			check(false);
		} catch (InvalidTestClassException e){
			
		}
	}
	
	public void becauseFindsTestClassesForAllTestClassFilesInPath(){		
		ClassFileTestLoader loader = new ClassFileTestLoader(new URLClassReader(), new TestMethodValidator());
		List<File> testFiles = loader.getTestFiles("bin");
		List<Class<?>> testClasses = loader.findTestClasses("bin");
		for (File testFile : testFiles) {
			boolean hasTestClass = false;
			for (Class<?> testClass : testClasses) {
				if(testFile.getName().startsWith(testClass.getName().
						substring(new URLClassReader().getPackageName("bin", testFile).length() + 1, testClass.getName().length() - 1))){
					hasTestClass = true;
					break;
				}
			}
			check(hasTestClass);
		}
	}

}

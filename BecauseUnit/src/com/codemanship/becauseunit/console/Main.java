package com.codemanship.becauseunit.console;

import com.codemanship.becauseunit.Runner;
import com.codemanship.becauseunit.TestMethodValidator;
import com.codemanship.becauseunit.fileloader.ClassFileTestLoader;
import com.codemanship.becauseunit.fileloader.URLClassReader;



public class Main {

	public static void main(String[] args){		
		ConsoleOutput output = new ConsoleOutput(new ConsoleWriter());
		ClassFileTestLoader loader = new ClassFileTestLoader(new URLClassReader(), new TestMethodValidator());
		new Runner(loader, output).runAll(args[0]);			
	}


}

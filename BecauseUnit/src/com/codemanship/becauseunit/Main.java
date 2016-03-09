package com.codemanship.becauseunit;



public class Main {

	public static void main(String[] args){		
		ConsoleOutput output = new ConsoleOutput(new ConsoleWriter());
		ClassFileTestLoader loader = new ClassFileTestLoader(new URLClassReader());
		new Runner(loader, output).runAll(args[0]);			
	}


}

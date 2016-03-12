package com.codemanship.becauseunit.console;

import com.codemanship.becauseunit.Writer;

public class ConsoleWriter implements Writer {

	public void write(String message) {
		System.out.println(message);
	}

}
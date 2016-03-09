package com.codemanship.becauseunit;

public class ConsoleWriter implements Writer {

	public void write(String message) {
		System.out.println(message);
	}

}
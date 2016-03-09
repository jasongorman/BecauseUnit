package com.codemanship.becauseunit.doubles;

import com.codemanship.becauseunit.Writer;

public class SpyWriter implements Writer {
	
	private String lastWrite = "";

	@Override
	public void write(String message) {
		lastWrite = message;
	}

	public String getLastWrite() {
		return lastWrite;
	}

}

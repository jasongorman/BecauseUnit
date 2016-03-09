package com.codemanship.becauseunit;

import java.util.List;

public interface TestLoader {

	public abstract List<Test> load(String classPath);

}
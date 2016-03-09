package com.codemanship.becauseunit;

import static com.codemanship.becauseunit.Checker.check;

import java.io.File;
import java.util.List;

public class BecauseFileUtils {

	public void becauseFindsAllFilesInFolderAndSubfolders() {
		File dir = new File("testfiles");
		List<File> files = new FileUtils().listFilesInDirectory(null, dir );
		check(files.size() == 2);
	}

}
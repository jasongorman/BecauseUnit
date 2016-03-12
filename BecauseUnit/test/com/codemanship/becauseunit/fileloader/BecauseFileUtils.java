package com.codemanship.becauseunit.fileloader;

import static com.codemanship.becauseunit.Checker.check;

import java.io.File;
import java.util.List;

import com.codemanship.becauseunit.fileloader.FileUtils;

public class BecauseFileUtils {

	public void becauseFindsAllFilesInFolderAndSubfolders() {
		File dir = new File("testfiles");
		List<File> files = new FileUtils().listFilesInDirectory(null, dir );
		check(files.size() == 2);
	}

}
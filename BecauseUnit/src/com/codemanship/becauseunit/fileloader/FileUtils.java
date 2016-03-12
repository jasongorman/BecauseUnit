package com.codemanship.becauseunit.fileloader;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {

	public List<File> listFilesInDirectory(List<File> files, File directory) {
		if (files == null)
			files = new LinkedList<File>();
		
		if (!directory.isDirectory())
		{
			files.add(directory);
			return files;
		}
		
		for (File file : directory.listFiles())
			listFilesInDirectory(files, file);
		return files;
	}

}
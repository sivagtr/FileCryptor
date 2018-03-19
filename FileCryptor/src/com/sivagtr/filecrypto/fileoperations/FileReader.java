package com.sivagtr.filecrypto.fileoperations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.sivagtr.filecrypto.bridge.CryptHelper;
import com.sivagtr.filecrypto.exceptions.UnableToOperateFile;

/**
 * @author sivagtr
 *
 */
public class FileReader implements FileTemplate {

	@Override
	public CryptHelper processFile(File file, byte[] data) throws UnableToOperateFile {

		try {
			data = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
			return new CryptHelper(file, data);
		} catch (IOException ex) {
			throw new UnableToOperateFile(ex.getMessage());
		}
	}
}

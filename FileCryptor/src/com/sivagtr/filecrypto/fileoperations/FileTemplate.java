package com.sivagtr.filecrypto.fileoperations;

import java.io.File;

import com.sivagtr.filecrypto.bridge.CryptHelper;
import com.sivagtr.filecrypto.exceptions.UnableToOperateFile;

/**
 * @author sivagtr
 *
 */
public interface FileTemplate {

	public CryptHelper processFile(File file, byte[] data) throws UnableToOperateFile;
}

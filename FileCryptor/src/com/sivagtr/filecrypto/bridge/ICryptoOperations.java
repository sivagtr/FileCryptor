package com.sivagtr.filecrypto.bridge;

import java.io.File;

import com.sivagtr.filecrypto.exceptions.WrongPassword;

/**
 * @author sivagtr
 *
 */
public interface ICryptoOperations {
	final byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

	public boolean performCryptoOperation(File orignalFile, File encryptionFile, String hash) throws WrongPassword;

}

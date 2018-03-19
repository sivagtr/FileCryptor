package com.sivagtr.filecrypto.manager;

import java.io.File;

import com.sivagtr.filecrypto.crypto.DecryptFile;
import com.sivagtr.filecrypto.crypto.EncryptFile;
import com.sivagtr.filecrypto.exceptions.WrongPassword;
import com.sivagtr.filecrypto.hashing.PasswordHasher;

/**
 * @author sivagtr
 *
 */
public class OperationManager {

	private static class OperationManagerHelper {
		private static final OperationManager INSTANCE = new OperationManager();
	}
	private static final String SALT = "1M)z2L(s3P*q7X$n6S5k";
	public static OperationManager getInstance() {
		return OperationManagerHelper.INSTANCE;
	}
	private PasswordHasher hasher = new PasswordHasher();

	private EncryptFile encrypter = new EncryptFile();

	private DecryptFile decrypter = new DecryptFile();

	private OperationManager() {
		// To avoid Singleton Implementation Breakage.
	}

	public boolean decryptOperation(File encryptedFile, File decryptedFile, char[] password) throws WrongPassword {
		String hash = hasher.getHash(password, SALT);
		return decrypter.performCryptoOperation(encryptedFile, decryptedFile, hash);
	}

	public boolean encryptOperation(File orignalFile, File encryptionFile, char[] password) throws WrongPassword {
		String hash = hasher.getHash(password, SALT);
		return encrypter.performCryptoOperation(orignalFile, encryptionFile, hash);
	}

}

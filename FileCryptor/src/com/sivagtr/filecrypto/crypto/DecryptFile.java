package com.sivagtr.filecrypto.crypto;

import java.io.File;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sivagtr.filecrypto.bridge.CryptHelper;
import com.sivagtr.filecrypto.bridge.ICryptoOperations;
import com.sivagtr.filecrypto.exceptions.WrongPassword;
import com.sivagtr.filecrypto.fileoperations.FileReader;
import com.sivagtr.filecrypto.fileoperations.FileWriter;

/**
 * @author sivagtr
 *
 */
public class DecryptFile implements ICryptoOperations {

	FileReader reader = new FileReader();
	FileWriter writer = new FileWriter();

	@Override
	public boolean performCryptoOperation(File encryptedFile, File decryptionFile, String hash) throws WrongPassword {

		byte[] encryptedData = null;
		CryptHelper helper;
		try {
			helper = reader.processFile(encryptedFile, encryptedData);
			encryptedData = helper.getData();

			byte[] key = Arrays.copyOf(hash.getBytes(), 16);
			SecretKey secretKey = new SecretKeySpec(key, "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			byte[] decryptText = cipher.doFinal(encryptedData);

			writer.processFile(decryptionFile, decryptText);
			return true;

		} catch (BadPaddingException e) {
			throw new WrongPassword();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}

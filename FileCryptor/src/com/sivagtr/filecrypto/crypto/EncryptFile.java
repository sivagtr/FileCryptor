/**
 * 
 */
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
public class EncryptFile implements ICryptoOperations {

	FileReader reader = new FileReader();
	FileWriter writer = new FileWriter();

	@Override
	public boolean performCryptoOperation(File orignalFile, File encryptionFile, String hash) throws WrongPassword {

		byte[] origData = null;
		CryptHelper helper;
		try {
			helper = reader.processFile(orignalFile, origData);
			origData = helper.getData();

			byte[] key = Arrays.copyOf(hash.getBytes(), 16);
			SecretKey secretKey = new SecretKeySpec(key, "AES");

			IvParameterSpec ivspec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			byte[] ciphertext = cipher.doFinal(origData);

			writer.processFile(encryptionFile, ciphertext);
			return true;

		} catch (BadPaddingException e) {
			throw new WrongPassword();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}

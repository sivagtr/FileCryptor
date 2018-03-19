package com.sivagtr.filecrypto.bridge;

import java.io.File;

/**
 * @author sivagtr
 *
 */
public class CryptHelper {

	private File file;
	private byte[] data;

	public CryptHelper() {

	}

	public CryptHelper(File file, byte[] data) {
		super();
		this.file = file;
		this.data = data;
	}

	public byte[] getData() {
		return data;
	}

	public File getFile() {
		return file;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setFile(File file) {
		this.file = file;
	}

}

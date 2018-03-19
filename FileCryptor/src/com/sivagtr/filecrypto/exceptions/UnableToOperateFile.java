package com.sivagtr.filecrypto.exceptions;

/**
 * @author sivagtr
 *
 */
public class UnableToOperateFile extends Exception {

	private static final long serialVersionUID = 1L;
	String msg = null;

	public UnableToOperateFile(String msg) {
		this.msg = "Unable to perform operation on the file " + msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}

}

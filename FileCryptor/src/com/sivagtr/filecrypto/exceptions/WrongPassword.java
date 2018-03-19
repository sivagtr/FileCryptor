package com.sivagtr.filecrypto.exceptions;

/**
 * @author sivagtr
 *
 */
public class WrongPassword extends Exception {

	private static final long serialVersionUID = 1L;
	String msg = "Wrong Password !!";

	public WrongPassword() {
	}

	@Override
	public String getMessage() {
		return msg;
	}

}

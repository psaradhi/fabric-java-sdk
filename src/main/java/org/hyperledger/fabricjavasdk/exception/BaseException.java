package org.hyperledger.fabricjavasdk.exception;

public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	public BaseException(String message, Exception parent) {
		super(message, parent);
	}
}

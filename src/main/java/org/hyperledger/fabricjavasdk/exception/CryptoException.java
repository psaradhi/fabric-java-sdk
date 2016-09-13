package org.hyperledger.fabricjavasdk.exception;

public class CryptoException extends BaseException {

	private static final long serialVersionUID = 1L;

	public CryptoException(String message, Exception parent) {
		super(message, parent);
	}

}

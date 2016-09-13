package org.hyperledger.fabricjavasdk.exception;

public class RegistrationException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	public RegistrationException(String message, Exception parent) {
		super(message, parent);
	}

}

package com.britosw.producerapi.exception;

public class ApplicationInternalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApplicationInternalException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ApplicationInternalException(String message, String logMessage) {
		super(message, new Error(logMessage));
	}

	public ApplicationInternalException(String message) {
		super(message);
	}
}

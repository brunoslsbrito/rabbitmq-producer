package com.britosw.producerapi.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable throwable) {
		super(throwable.getMessage(), throwable.getCause());
	}

	public ApplicationException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ApplicationException(String message, String logMessage) {
		super(message, new Error(logMessage));
	}
}


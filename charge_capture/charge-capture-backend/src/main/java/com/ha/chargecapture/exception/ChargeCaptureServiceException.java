package com.ha.chargecapture.exception;

public class ChargeCaptureServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ChargeCaptureServiceException(String message) {
		super(message);
	}

	public ChargeCaptureServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

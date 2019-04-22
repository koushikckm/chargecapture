package com.ha.chargecapture.exception;

public class ChargeCaptureDaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ChargeCaptureDaoException(String message) {
		super(message);
	}

	public ChargeCaptureDaoException(String message, Throwable cause) {
		super(message, cause);
	}
}

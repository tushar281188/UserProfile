package com.test.fbcommunication.exceptions;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	public BusinessException(String exceptionMessage) {
		super(exceptionMessage);
	}
}

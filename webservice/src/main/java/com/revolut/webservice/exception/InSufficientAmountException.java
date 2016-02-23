package com.revolut.webservice.exception;

public class InSufficientAmountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InSufficientAmountException(String message) {
		super(message);
	}

	
}

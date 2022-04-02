package com.compass.projetodoacao.services.exception;

public class InvalidQuantityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidQuantityException(String msg) {
		super(msg);
	}
}

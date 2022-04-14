package com.compass.projetodoacao.services.exception;

public class InvalidAddressException  extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidAddressException(String msg) {
		super(msg);
	}
}

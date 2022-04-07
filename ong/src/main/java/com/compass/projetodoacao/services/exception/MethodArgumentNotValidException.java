package com.compass.projetodoacao.services.exception;

public class MethodArgumentNotValidException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MethodArgumentNotValidException(String msg) {
		super(msg);
	}
}

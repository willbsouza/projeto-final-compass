package com.compass.projetodoacao.controllers.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.compass.projetodoacao.services.exception.InvalidQuantityException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objetoNaoEncontrado(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setError("Não encontrado.");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> camposInvalidos(MethodArgumentNotValidException e, HttpServletRequest request){
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setError("Campo não pode ser vazio/nulo.");
		erro.setMessage("Campo incorreto: " + e.getFieldError().getField().toUpperCase());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> camposInvalidos(HttpMessageNotReadableException e, HttpServletRequest request){
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setError("Entrada inválida. Selecione uma entrada válida.");
		erro.setMessage("Selecione uma das entradas válidas a seguir: " + e.getCause().getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(InvalidQuantityException.class)
	public ResponseEntity<StandardError> camposInvalidos(InvalidQuantityException e, HttpServletRequest request){
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setError("Entrada inválida. Quantidade inválida.");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> camposInvalidos(DataIntegrityViolationException e, HttpServletRequest request){
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setError("Não é possível excluir.");
		erro.setMessage("Item possui relacionamentos. Não é possível excluir.");
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}


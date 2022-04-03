package com.compass.projetodoacao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll(){
		return ResponseEntity.ok(enderecoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Integer id){
		return ResponseEntity.ok(enderecoService.findById(id));
	}
}

package com.compass.projetodoacao.controllers;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.compass.projetodoacao.dto.EnderecoDTO;
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
	
	@PostMapping
	@Transactional
	public ResponseEntity<Endereco> save(@RequestBody @Valid EnderecoDTO enderecoDTO, UriComponentsBuilder uriBuilder){
		URI uri = uriBuilder.path("/enderecos").buildAndExpand(enderecoDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(enderecoService.saveEndereco(enderecoDTO));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Endereco> update(@PathVariable Integer id, @RequestBody @Valid EnderecoDTO enderecoDTO){
		return ResponseEntity.ok(enderecoService.update(id, enderecoDTO));
	}
}

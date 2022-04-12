package com.compass.projetodoacao.controllers;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.compass.projetodoacao.dto.EnderecoFormDTO;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.services.EnderecoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@ApiOperation(value = "Retorna lista de endereços cadastrados.")
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll(){
		return ResponseEntity.ok(enderecoService.findAll());
	}
	
	@ApiOperation(value = "Retorna um endereço ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Integer id){
		return ResponseEntity.ok(enderecoService.findById(id));
	}
	
	@ApiOperation(value = "Cadastra um endereço")
	@PostMapping
	@Transactional
	public ResponseEntity<Endereco> save(@RequestBody @Valid EnderecoFormDTO enderecoDTO, UriComponentsBuilder uriBuilder){
		URI uri = uriBuilder.path("/enderecos").buildAndExpand(enderecoDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(enderecoService.saveEndereco(enderecoDTO));
	}

	@ApiOperation(value = "Atualiza as informações de um endereço informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Endereco> update(@PathVariable Integer id, @RequestBody @Valid EnderecoFormDTO enderecoDTO){
		return ResponseEntity.ok(enderecoService.update(id, enderecoDTO));
	}
	
	@ApiOperation(value = "Exclui um endereço ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		enderecoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

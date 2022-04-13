package com.compass.projetodoacao.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.compass.projetodoacao.dto.TelefoneFormDTO;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.services.TelefoneService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

	@Autowired
	private TelefoneService telefoneService;
	
	@ApiOperation(value = "Retorna lista de telefones cadastrados.")
	@GetMapping
	public ResponseEntity<List<Telefone>> findAll(){
		return ResponseEntity.ok(telefoneService.findAll());
	}
	
	@ApiOperation(value = "Retorna um telefone ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<Telefone> findById(@PathVariable Integer id){
		return ResponseEntity.ok(telefoneService.findById(id));
	}
	
	@ApiOperation(value = "Cadastra um telefone")
	@PostMapping
	@Transactional
	public ResponseEntity<Telefone> save(@RequestBody @Valid TelefoneFormDTO telefoneDTO, UriComponentsBuilder uriBuilder){
		URI uri = uriBuilder.path("/telefones").buildAndExpand(telefoneDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(telefoneService.saveTelefone(telefoneDTO));
	}
	
	@ApiOperation(value = "Atualiza as informações de um telefone informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Telefone> update(@PathVariable Integer id, @RequestBody @Valid TelefoneFormDTO telefoneDTO){
		return ResponseEntity.ok(telefoneService.update(id, telefoneDTO));
	}
	
	@ApiOperation(value = "Exclui um telefone ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		telefoneService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}

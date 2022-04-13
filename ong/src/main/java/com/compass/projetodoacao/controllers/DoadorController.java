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

import com.compass.projetodoacao.dto.DoadorDTO;
import com.compass.projetodoacao.dto.DoadorFormDTO;
import com.compass.projetodoacao.dto.DoadorPostFormDTO;
import com.compass.projetodoacao.services.DoadorService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/doadores")
public class DoadorController {
	
	@Autowired
	private DoadorService doadorService;
	
	@ApiOperation(value = "Retorna lista de doadores cadastrados.")
	@GetMapping
	public ResponseEntity<List<DoadorDTO>> findAll(){
		return ResponseEntity.ok(doadorService.findAll());
	}
	
	@ApiOperation(value = "Retorna um doador ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<DoadorDTO> findById(@PathVariable Integer id){
		return ResponseEntity.ok(doadorService.findById(id));
	}
	
	@ApiOperation(value = "Cadastra um doador com endereço e telefone.")
	@PostMapping
	@Transactional
	public ResponseEntity<DoadorDTO> save(@RequestBody @Valid DoadorPostFormDTO doadorDTO, UriComponentsBuilder uriBuilder){
		URI uri = uriBuilder.path("/doadores").buildAndExpand(doadorDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(doadorService.save(doadorDTO));
	}
	
	@ApiOperation(value = "Atualiza as informações de um doador informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DoadorDTO> update(@PathVariable Integer id, @RequestBody @Valid DoadorFormDTO doadorDTO){
		return ResponseEntity.ok(doadorService.update(id, doadorDTO));
	}
	
	@ApiOperation(value = "Exclui um doador ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		doadorService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

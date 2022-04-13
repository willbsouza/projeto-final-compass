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

import com.compass.projetodoacao.dto.ONGDTO;
import com.compass.projetodoacao.dto.ONGFormDTO;
import com.compass.projetodoacao.dto.ONGPostFormDTO;
import com.compass.projetodoacao.services.ONGService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ongs")
public class ONGController {
	
	@Autowired
	private ONGService ongService;
	
	@ApiOperation(value = "Retorna lista de ONGs cadastradas.")
	@GetMapping
	public ResponseEntity<List<ONGDTO>> findAll() {
		return ResponseEntity.ok(ongService.findAll());
	}
	
	@ApiOperation(value = "Retorna uma ONG ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<ONGDTO> findById(@PathVariable Integer id){
		return ResponseEntity.ok(ongService.findById(id));
	}
	
	@ApiOperation(value = "Cadastra uma ONG com endereço e telefone.")
	@PostMapping
	@Transactional
	public ResponseEntity<ONGDTO> save(@RequestBody @Valid ONGPostFormDTO ongDTO, UriComponentsBuilder uriBuilder){
		URI uri = uriBuilder.path("/ongs").buildAndExpand(ongDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(ongService.save(ongDTO));
	}
	
	@ApiOperation(value = "Atualiza as informações de uma ONG informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ONGDTO> update(@PathVariable Integer id, @RequestBody @Valid ONGFormDTO ongDTO){
		return ResponseEntity.ok(ongService.update(id, ongDTO));
	}
	
	@ApiOperation(value = "Exclui uma ONG ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		ongService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

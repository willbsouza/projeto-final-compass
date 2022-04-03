package com.compass.projetodoacao.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.compass.projetodoacao.dto.DoadorDTO;
import com.compass.projetodoacao.dto.DoadorFormDTO;
import com.compass.projetodoacao.services.DoadorService;

@RestController
@RequestMapping("/doadores")
public class DoadorController {
	
	@Autowired
	private DoadorService doadorService;
	
	@GetMapping
	public ResponseEntity<List<DoadorDTO>> findAll(){
		return ResponseEntity.ok(doadorService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DoadorDTO> findById(@PathVariable Integer id){
		return ResponseEntity.ok(doadorService.findById(id));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DoadorDTO> save(@RequestBody @Valid DoadorFormDTO doadorDTO, UriComponentsBuilder uriBuilder){
		URI uri = uriBuilder.path("/doadores").buildAndExpand(doadorDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(doadorService.save(doadorDTO));
	}
}

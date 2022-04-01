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

import com.compass.projetodoacao.dto.ONGFormDTO;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.services.ONGService;

@RestController
@RequestMapping("/ongs")
public class ONGController {
	
	@Autowired
	private ONGService ongService;
	
	@GetMapping
	public ResponseEntity<List<ONG>> findAll() {
		return ResponseEntity.ok(ongService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ONG> findById(@PathVariable Integer id){
		return ResponseEntity.ok(ongService.findById(id));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ONG> save(@RequestBody @Valid ONGFormDTO ongDTO, UriComponentsBuilder uriBuilder){
		URI uri = uriBuilder.path("/doadores").buildAndExpand(ongDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(ongService.save(ongDTO));
	}
}

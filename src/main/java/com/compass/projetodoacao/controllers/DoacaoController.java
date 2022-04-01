package com.compass.projetodoacao.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.entities.Doacao;
import com.compass.projetodoacao.services.DoacaoService;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {
	
	@Autowired
	private DoacaoService doacaoService;
	
	@GetMapping
	public ResponseEntity<List<Doacao>> findAll(){
		return ResponseEntity.ok(doacaoService.findAll());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Doacao> save(@RequestBody @Valid DoacaoFormDTO doacaoDTO, UriComponentsBuilder uriBuilder){
		URI uri = uriBuilder.path("/doacoes/{id}").buildAndExpand(doacaoDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(doacaoService.save(doacaoDTO));
	}

}

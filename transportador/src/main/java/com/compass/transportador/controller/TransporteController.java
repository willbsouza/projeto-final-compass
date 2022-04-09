package com.compass.transportador.controller;

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

import com.compass.transportador.entity.Transporte;
import com.compass.transportador.service.TransporteService;

@RestController
@RequestMapping("/transportes")
public class TransporteController {
	
	@Autowired
	private TransporteService transporteService;
	
	@GetMapping
	public ResponseEntity<List<Transporte>> findAll(){
		return ResponseEntity.ok().body(transporteService.findAll());
	}
		
	@PostMapping
	@Transactional
	public ResponseEntity<Transporte> solicitarTransporte(@RequestBody @Valid Transporte transporte, UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/transportes/{id}").buildAndExpand(transporte.getId()).toUri();
		return ResponseEntity.created(uri).body(transporteService.save(transporte));
	}
}

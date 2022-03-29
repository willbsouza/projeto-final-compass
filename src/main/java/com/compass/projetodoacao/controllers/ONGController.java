package com.compass.projetodoacao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

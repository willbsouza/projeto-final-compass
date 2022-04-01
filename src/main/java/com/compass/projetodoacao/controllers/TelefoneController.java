package com.compass.projetodoacao.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.services.TelefoneService;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

	@Autowired
	private TelefoneService telefoneService;
	
	@GetMapping
	public ResponseEntity<List<Telefone>> findAll(){
		return ResponseEntity.ok(telefoneService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Telefone> findById(@PathVariable Integer id){
		return ResponseEntity.ok(telefoneService.findById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Telefone> update(@PathVariable Integer id, @RequestBody @Valid Telefone telefone){
		return ResponseEntity.ok(telefoneService.update(id, telefone));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		return ResponseEntity.noContent().build();
	}
	
}

package com.compass.projetodoacao.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return new ResponseEntity<List<ONGDTO>>(ongService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna uma ONG ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<ONGDTO> findById(@PathVariable Integer id){
		return new ResponseEntity<ONGDTO>(ongService.findById(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Cadastra uma ONG com endereço e telefone.")
	@PostMapping
	@Transactional
	public ResponseEntity<ONGDTO> save(@RequestBody @Valid ONGPostFormDTO ongDTO, UriComponentsBuilder uriBuilder){
		return new ResponseEntity<ONGDTO>(ongService.save(ongDTO), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Atualiza as informações de uma ONG informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ONGDTO> update(@PathVariable Integer id, @RequestBody @Valid ONGFormDTO ongDTO){
		return new ResponseEntity<ONGDTO>(ongService.update(id, ongDTO), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Exclui uma ONG ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		ongService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

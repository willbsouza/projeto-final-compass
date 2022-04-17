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

import com.compass.projetodoacao.dto.TelefoneFormDTO;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.services.TransporteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

	@Autowired
	private TransporteService telefoneService;
	
	@ApiOperation(value = "Retorna lista de telefones cadastrados.")
	@GetMapping
	public ResponseEntity<List<Telefone>> findAll(){
		return new ResponseEntity<List<Telefone>>(telefoneService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna um telefone ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<Telefone> findById(@PathVariable Integer id){
		return new ResponseEntity<Telefone>(telefoneService.findById(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Cadastra um telefone")
	@PostMapping
	@Transactional
	public ResponseEntity<Telefone> save(@RequestBody @Valid TelefoneFormDTO telefoneDTO){
		return new ResponseEntity<Telefone>(telefoneService.saveTelefone(telefoneDTO), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Atualiza as informações de um telefone informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Telefone> update(@PathVariable Integer id, @RequestBody @Valid TelefoneFormDTO telefoneDTO){
		return new ResponseEntity<Telefone>(telefoneService.update(id, telefoneDTO), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Exclui um telefone ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		telefoneService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

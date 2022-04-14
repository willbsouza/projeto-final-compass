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

import com.compass.projetodoacao.dto.DonatarioDTO;
import com.compass.projetodoacao.dto.DonatarioFormDTO;
import com.compass.projetodoacao.dto.DonatarioPostFormDTO;
import com.compass.projetodoacao.services.DonatarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/donatarios")
public class DonatarioController {
	
	@Autowired
	private DonatarioService donatarioService;
	
	@ApiOperation(value = "Retorna lista de donatários cadastrados.")
	@GetMapping
	public ResponseEntity<List<DonatarioDTO>> findAll(){
		return new ResponseEntity<List<DonatarioDTO>>(donatarioService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna um donatário ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<DonatarioDTO> findById(@PathVariable Integer id){
		return new ResponseEntity<DonatarioDTO>(donatarioService.findById(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Cadastra um donatário com endereço e telefone.")
	@PostMapping
	@Transactional
	public ResponseEntity<DonatarioDTO> save(@RequestBody @Valid DonatarioPostFormDTO donatarioDTO){
		return new ResponseEntity<DonatarioDTO>(donatarioService.save(donatarioDTO), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Atualiza as informações de um donatário informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DonatarioDTO> update(@PathVariable Integer id, @RequestBody @Valid DonatarioFormDTO donatarioDTO){
		return new ResponseEntity<DonatarioDTO>(donatarioService.update(id, donatarioDTO), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Exclui um donatário ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		donatarioService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

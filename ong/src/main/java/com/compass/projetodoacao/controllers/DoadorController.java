package com.compass.projetodoacao.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.projetodoacao.dto.DoadorDTO;
import com.compass.projetodoacao.dto.DoadorFormDTO;
import com.compass.projetodoacao.dto.DoadorPostFormDTO;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.services.DoadorService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/doadores")
@CrossOrigin("http://localhost:4200")
public class DoadorController {
	
	@Autowired
	private DoadorService doadorService;
	
	@ApiOperation(value = "Retorna lista de doadores cadastrados.")
	@GetMapping
	public ResponseEntity<List<DoadorDTO>> findAll(){
		return new ResponseEntity<List<DoadorDTO>>(doadorService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna um doador ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<DoadorDTO> findById(@PathVariable Integer id){
		return new ResponseEntity<DoadorDTO>(doadorService.findById(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Cadastra um doador com endereço e telefone.")
	@PostMapping
	@Transactional
	public ResponseEntity<DoadorDTO> save(@RequestBody @Valid DoadorPostFormDTO doadorDTO){
		return new ResponseEntity<DoadorDTO>(doadorService.save(doadorDTO), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Atualiza as informações de um doador informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DoadorDTO> update(@PathVariable Integer id, @RequestBody @Valid DoadorFormDTO doadorDTO){
		return new ResponseEntity<DoadorDTO>(doadorService.update(id, doadorDTO), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Exclui um doador ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		doadorService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Cadastra um endereço para um doador")
	@PutMapping("/{id}/enderecos")
	@Transactional
	public ResponseEntity<DoadorDTO> cadastrarEndereco(@PathVariable Integer id, @RequestBody @Valid Endereco endereco){
		return new ResponseEntity<DoadorDTO>(doadorService.salvarEndereco(id, endereco), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Cadastra um telefone para um doador")
	@PutMapping("/{id}/telefones")
	@Transactional
	public ResponseEntity<DoadorDTO> cadastrarTelefone(@PathVariable Integer id, @RequestBody @Valid Telefone telefone){
		return new ResponseEntity<DoadorDTO>(doadorService.salvarTelefone(id, telefone), HttpStatus.CREATED);
	}
}

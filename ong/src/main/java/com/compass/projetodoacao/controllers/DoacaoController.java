package com.compass.projetodoacao.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.compass.projetodoacao.dto.DoacaoDTO;
import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.services.DoacaoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

	@Autowired
	private DoacaoService doacaoService;

	@ApiOperation(value = "Retorna lista de doações cadastradas.")
	@GetMapping
	public ResponseEntity<List<DoacaoDTO>> findAll() {
		return ResponseEntity.ok(doacaoService.findAll());
	}
	
	@ApiOperation(value = "Retorna uma doação ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<DoacaoDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(doacaoService.findById(id));
	}

	@ApiOperation(value = "Cadastra uma doação")
	@PostMapping
	@Transactional
	public ResponseEntity<DoacaoDTO> save(@RequestBody @Valid DoacaoFormDTO doacaoDTO, UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/doacoes/{id}").buildAndExpand(doacaoDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(doacaoService.save(doacaoDTO));
	}
	
	@ApiOperation(value = "Atualiza as informações de uma doação informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DoacaoDTO> update(@PathVariable Integer id, @RequestBody @Valid DoacaoFormDTO doacaoDTO){
		return ResponseEntity.ok(doacaoService.update(id, doacaoDTO));
	}
	
	@ApiOperation(value = "Exclui uma doação ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		doacaoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

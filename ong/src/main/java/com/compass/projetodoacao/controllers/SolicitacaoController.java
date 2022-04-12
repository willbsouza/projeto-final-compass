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

import com.compass.projetodoacao.dto.SolicitacaoDTO;
import com.compass.projetodoacao.dto.SolicitacaoFormDTO;
import com.compass.projetodoacao.services.SolicitacaoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

	@Autowired
	private SolicitacaoService solicitacaoService;

	@ApiOperation(value = "Retorna lista de solicitações cadastradas.")
	@GetMapping
	public ResponseEntity<List<SolicitacaoDTO>> findAll() {
		return ResponseEntity.ok(solicitacaoService.findAll());
	}
	
	@ApiOperation(value = "Retorna uma solicitação ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<SolicitacaoDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(solicitacaoService.findById(id));
	}

	@ApiOperation(value = "Cadastra uma solicitação")
	@PostMapping
	@Transactional
	public ResponseEntity<SolicitacaoDTO> save(@RequestBody @Valid SolicitacaoFormDTO solicitacaoDTO, UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/solicitacoes/{id}").buildAndExpand(solicitacaoDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(solicitacaoService.save(solicitacaoDTO));
	}
	
	@ApiOperation(value = "Atualiza as informações de uma solicitação informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<SolicitacaoDTO> update(@PathVariable Integer id, @RequestBody @Valid SolicitacaoFormDTO solicitacaoDTO){
		return ResponseEntity.ok(solicitacaoService.update(id, solicitacaoDTO));
	}
	
	@ApiOperation(value = "Exclui uma solicitação ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		solicitacaoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

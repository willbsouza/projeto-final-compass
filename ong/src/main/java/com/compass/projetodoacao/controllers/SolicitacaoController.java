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

import com.compass.projetodoacao.dto.SolicitacaoDTO;
import com.compass.projetodoacao.dto.SolicitacaoFormDTO;
import com.compass.projetodoacao.dto.SolicitacaoPutFormDTO;
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
		return new ResponseEntity<List<SolicitacaoDTO>>(solicitacaoService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna uma solicitação ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<SolicitacaoDTO> findById(@PathVariable Integer id) {
		return new ResponseEntity<SolicitacaoDTO>(solicitacaoService.findById(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Cadastra uma solicitação")
	@PostMapping
	@Transactional
	public ResponseEntity<SolicitacaoDTO> save(@RequestBody @Valid SolicitacaoFormDTO solicitacaoDTO) {
		return new ResponseEntity<SolicitacaoDTO>(solicitacaoService.save(solicitacaoDTO), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Atualiza as informações de uma solicitação informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<SolicitacaoDTO> update(@PathVariable Integer id, @RequestBody @Valid SolicitacaoPutFormDTO solicitacaoDTO){
		return new ResponseEntity<SolicitacaoDTO>(solicitacaoService.update(id, solicitacaoDTO), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Exclui uma solicitação ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		solicitacaoService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

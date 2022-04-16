package com.compass.transportador.controller;

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

import com.compass.transportador.entity.Transporte;
import com.compass.transportador.service.TransporteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/transportes")
public class TransporteController {
	
	@Autowired
	private TransporteService transporteService;
	
	@ApiOperation(value = "Retorna lista de transportes solicitados.")
	@GetMapping
	public ResponseEntity<List<Transporte>> findAll(){
		return new ResponseEntity<List<Transporte>>(transporteService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna um transporte solicitado ao encontrar um Id de doação correspondente.")
	@GetMapping("/{idDoacao}")
	public ResponseEntity<Transporte> findByIdDoacao(@PathVariable Integer idDoacao){
		return new ResponseEntity<Transporte>(transporteService.findByIdDoacao(idDoacao), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Solicita um transporte.")
	@PostMapping
	@Transactional
	public ResponseEntity<Transporte> solicitarTransporte(@RequestBody @Valid Transporte transporte) {
		return new ResponseEntity<Transporte>(transporteService.save(transporte), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Atualiza um transporte em relação a um Id de doação informado.")
	@PutMapping("/{idDoacao}")
	@Transactional
	public ResponseEntity<Transporte> atualizarTransporte(@PathVariable Integer idDoacao, @RequestBody @Valid Transporte transporte) {
		return new ResponseEntity<Transporte>(transporteService.update(idDoacao, transporte), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Cancela um serviço de transporte com relação a um Id de doação.")
	@DeleteMapping("/{idDoacao}")
	@Transactional
	public ResponseEntity<Void> excluirTransporte(@PathVariable Integer idDoacao) {
		transporteService.deleteByIdDoacao(idDoacao);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

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

@RestController
@RequestMapping("/transportes")
public class TransporteController {
	
	@Autowired
	private TransporteService transporteService;
	
	@GetMapping
	public ResponseEntity<List<Transporte>> findAll(){
		return new ResponseEntity<List<Transporte>>(transporteService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Transporte> findById(@PathVariable Integer id){
		return new ResponseEntity<Transporte>(transporteService.findById(id), HttpStatus.OK);
	}
		
	@PostMapping
	@Transactional
	public ResponseEntity<Transporte> solicitarTransporte(@RequestBody @Valid Transporte transporte) {
		return new ResponseEntity<Transporte>(transporteService.save(transporte), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Transporte> atualizarTransporte(@PathVariable Integer id, @RequestBody @Valid Transporte transporte) {
		return new ResponseEntity<Transporte>(transporteService.update(id, transporte), HttpStatus.OK);
	}
	
	@DeleteMapping("/{idDoacao}")
	@Transactional
	public ResponseEntity<Void> excluirTransporte(@PathVariable Integer idDoacao) {
		transporteService.deleteByIdDoacao(idDoacao);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

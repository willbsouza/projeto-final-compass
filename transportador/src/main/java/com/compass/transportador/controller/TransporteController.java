package com.compass.transportador.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public Transporte solicitarTransporte(@RequestBody @Valid Transporte transporte) {
		return transporteService.save(transporte);
	}
	
	@GetMapping
	public List<Transporte> findAll(){
		return transporteService.findAll();
	}
}

package com.compass.projetodoacao.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doadores")
public class DoadorController {
	
	@GetMapping
	public String testar() {
		return "testando controller doador.";
	}

}

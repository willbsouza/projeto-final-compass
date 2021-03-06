package com.compass.projetodoacao.client;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.compass.projetodoacao.dto.TransporteDTO;

@Component
@FeignClient("transportador")
public interface TransporteClient {
	
	@PostMapping("/transportes")
	TransporteDTO solicitarTransporte(@RequestBody @Valid TransporteDTO transporte);

	@DeleteMapping("/transportes/{idDoacao}")
	void deletarTransporte(@PathVariable  Integer idDoacao);

	@PutMapping("/transportes/{idDoacao}")
	void atualizarTransporte(@PathVariable  Integer idDoacao, @RequestBody @Valid TransporteDTO instanciarTransporte);
	
	@GetMapping("/transportes/{idDoacao}")
	TransporteDTO findByIdDoacao(@PathVariable Integer idDoacao);
}

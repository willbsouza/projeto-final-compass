package com.compass.projetodoacao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.compass.projetodoacao.dto.TransporteDTO;

@Component
@FeignClient("transportador")
public interface TransporteClient {
	
	@PostMapping("/transportes")
	TransporteDTO solicitarTransporte(@RequestBody TransporteDTO transporte);
}

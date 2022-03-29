package com.compass.projetodoacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.entities.Doacao;
import com.compass.projetodoacao.entities.Doador;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.repositories.DoacaoRepository;
import com.compass.projetodoacao.repositories.DoadorRepository;
import com.compass.projetodoacao.repositories.ONGRepository;

@Service
public class DoacaoService {
	
	@Autowired
	private DoacaoRepository doacaoRepository;
	
	@Autowired
	private ONGRepository ongRepository;
	
	@Autowired
	private DoadorRepository doadorRepository;

	public Doacao save(DoacaoFormDTO doacaoDTO) {
		Doacao doacao = new Doacao();
		ONG ong = ongRepository.findById(doacaoDTO.getId_ong()).orElse(null); //Tratar exceção
		Doador doador = doadorRepository.findById(doacaoDTO.getId_doador()).orElse(null); //Tratar exceção
		
		
		return doacaoRepository.save(doacao);
	}

}

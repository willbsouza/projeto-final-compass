package com.compass.projetodoacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoadorFormDTO;
import com.compass.projetodoacao.dto.ONGFormDTO;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.repositories.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public Telefone saveTelefoneDoador(DoadorFormDTO doadorDTO) {
		Telefone telefone = new Telefone();
		telefone.setNumero(doadorDTO.getTelefone());
		return telefoneRepository.save(telefone);
	}

	public Telefone saveTelefoneONG(ONGFormDTO ongDTO) {
		Telefone telefone = new Telefone();
		telefone.setNumero(ongDTO.getTelefone());
		return telefoneRepository.save(telefone);
	}
}

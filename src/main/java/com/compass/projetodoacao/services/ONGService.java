package com.compass.projetodoacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.ONGFormDTO;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.repositories.ONGRepository;

@Service
public class ONGService {
	
	@Autowired
	private ONGRepository ongRepository;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@Autowired
	private EnderecoService enderecoService;

	public List<ONG> findAll() {
		return ongRepository.findAll();
	}

	public ONG findById(Integer id) {
		//Tratar exceção dps.
		return ongRepository.findById(id).orElse(null);
	}

	public ONG save(ONGFormDTO ongDTO) {
		
		Endereco endereco = enderecoService.saveEnderecoONG(ongDTO);
		Telefone telefone = telefoneService.saveTelefoneONG(ongDTO);
		
		ONG ong = new ONG();
		ong.adicionarEndereco(endereco);
		ong.adicionarTelefone(telefone);
		ong.setFilial(ongDTO.getFilialONG());
		return ongRepository.save(ong);
	}

}
